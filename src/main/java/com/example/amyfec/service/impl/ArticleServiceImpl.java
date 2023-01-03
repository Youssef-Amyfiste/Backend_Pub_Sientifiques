package com.example.amyfec.service.impl;

import com.example.amyfec.bean.Article;
import com.example.amyfec.bean.ArticleItem;
import com.example.amyfec.bean.Auteur;
import com.example.amyfec.bean.AvisArticle;
import com.example.amyfec.dao.ArticleDao;
import com.example.amyfec.security.bean.User;
import com.example.amyfec.security.service.facade.UserService;
import com.example.amyfec.service.facade.ArticleItemService;
import com.example.amyfec.service.facade.ArticleService;
import com.example.amyfec.service.facade.AvisArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private String ref="A000";
    @Autowired
    ArticleDao articleDao;
    @Autowired
    AvisArticleService avisArticleService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleItemService articleItemService;
    List<Article> userArticles=new ArrayList<Article>();
    @Override
    public int save(Article article) {
        String[] resumeAsTab;
        User chercheur = userService.findByUsername(article.getManUsername());
        article.setDateCreation(new Date());
        articleDao.save(article);
        Article articleFullData = this.findByTitre(article.getTitre());
        articleFullData.setRef("ART00"+articleFullData.getId());
        articleFullData.setEtat("Déposé");
        resumeAsTab = article.getResume().split("\\s+");
        int taille=resumeAsTab.length;
        articleFullData.setTaille(taille);
        if (taille<4000){
            articleFullData.setType("Court");
        }
        else {
            articleFullData.setType("Long");
        }
        articleDao.save(articleFullData);
        ArticleItem articleItem= new ArticleItem();
        articleItem.setArticle(articleFullData);
        articleItem.setAuteur(chercheur);
        articleItemService.save(articleItem);
        return 1;
    }

    @Override
    public Article findByRef(String ref) {
       return articleDao.findByRef(ref);
    }

    @Override
    public List<Article> findAllUserArticles() {
        return articleDao.findAll();
    }

    @Override
    public Article findByTitre(String titre) {
        return this.articleDao.findByTitre(titre);
    }

    @Override
    public Article findById(int id) {
        return articleDao.findById(id);
    }
    public List<Article> findUserArticles(String username){
        if (userArticles!=null){
            userArticles.clear();
        }
        List<ArticleItem> userArticleItems = this.articleItemService.findByAuteurUsername(username);
        if (userArticleItems==null){
            return null;
        }
        userArticleItems.forEach(ai->{
            Article article=this.findById(ai.getArticle().getId());
        this.userArticles.add(article);
        });
        return userArticles;
    }
    public List<ArticleItem> findByAuteurUsername(String username){
        return this.articleItemService.findByAuteurUsername(username) ;
    }

    @Override
    public int soumettreArticle(int id) {
        Article article = this.findById(id);
        article.setSubmited(true);
        article.setDateSoumission(new Date());
        article.setDeposed(false);
        articleDao.save(article);
        return 1;
    }

    //@Override
    //public List<Article> findNewArticles() {return articleDao.findAllByDeposed(true);}

    @Override
    public void saveEditorDecision(String ref, String avisEdideur) {
        Article article = this.findByRef(ref);
        article.setAvisEditeur(avisEdideur);
        article.setOnLastDecision(false); //article n'apparait plus au tableaux de dernier decision d editeur
        this.articleDao.save(article);
    }

    @Override
    public int evaluateArticle(String userUsername, String articleRef, String decision) {
        User evaluateur = this.userService.findByUsername(userUsername);
        Article article =this.findByRef(articleRef);
        AvisArticle avisArticle=this.avisArticleService.findByEvaluateurUsernameAndArticleRef(userUsername,articleRef);
        //avisArticle.setEvaluateur(evaluateur);
        //avisArticle.setArticle(article);
        avisArticle.setDecision(decision);
        this.avisArticleService.save(avisArticle);
        article.setEvaluated(true);
        article.setDateEvaluation(new Date());
        this.articleDao.save(article);
        return 1;
    }

    @Override
    public int evaluateByEditor(String ref, String decision) {
        Article article=this.findByRef(ref);
        article.setAvisEditeur(decision);
        article.setEtat(decision);
        article.setOnLastDecision(false);
        this.articleDao.save(article);
        return 1;
    }
    public int addArticleEvaluater(String username,String articleRef){
        User evaluateur=this.userService.findByUsername(username);
        Article article=this.articleDao.findByRef(articleRef);
        if (article.getType().equals("Court") && article.getNbrEvaluateur()==3){
            return -1;
        }
        if (article.getType().equals("Court") && article.getNbrEvaluateur()==4){
            return -2;
        }
        if (article.getType().equals("Court") && article.getNbrEvaluateur()==2){
            article.setSubmited(true);
            article.setDateSoumission(new Date());
            article.setEtat("En cours de traitement");
            article.setDeposed(false);
        }
        if (article.getType().equals("Long") && article.getNbrEvaluateur()==3){
            article.setSubmited(true);
            article.setDateSoumission(new Date());
            article.setEtat("En cours de traitement");
            article.setDeposed(false);
        }
        AvisArticle avisArticle=new AvisArticle();
        avisArticle.setArticle(article);
        avisArticle.setEvaluateur(evaluateur);
        this.avisArticleService.save(avisArticle);
        article.setNbrEvaluateur(article.getNbrEvaluateur()+1);
        articleDao.save(article);
        return 1;
    }

    @Override
    public void editArticle(Article article) {
        Article articleSaved = this.findByRef(article.getRef());
        articleSaved.setTitre(article.getTitre());
        articleSaved.setContenu(article.getContenu());
        articleSaved.setResume(article.getResume());
        article.setDateModification(new Date());
        this.articleDao.save(articleSaved);
    }

    @Override
    @Transactional
    public int deleteByRef(String ref) {
        this.articleItemService.deleteByArticleRef(ref);
        this.avisArticleService.deleteByArticleRef(ref);
        return this.articleDao.deleteByRef(ref);
    }


}
