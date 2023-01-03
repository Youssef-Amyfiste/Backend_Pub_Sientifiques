package com.example.amyfec.service.impl;

import com.example.amyfec.bean.Article;
import com.example.amyfec.bean.AvisArticle;
import com.example.amyfec.dao.AvisArticleDao;
import com.example.amyfec.service.facade.ArticleService;
import com.example.amyfec.service.facade.AvisArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvisArticleServiceImpl implements AvisArticleService {
    @Autowired
    AvisArticleDao avisArticleDao;
    @Autowired
    ArticleService articleService;
    List<Article> articleList=new ArrayList<>();
    @Override
    public void save(AvisArticle avisArticle) {
        this.avisArticleDao.save(avisArticle);
    }

    @Override
    public List<AvisArticle> findByEvaluateurUsername(String username) {
        return this.avisArticleDao.findByEvaluateurUsername(username);
    }

    @Override
    public AvisArticle findByEvaluateurUsernameAndArticleRef(String username, String ref) {
        return this.avisArticleDao.findByEvaluateurUsernameAndArticleRef(username,ref);
    }

    public List<Article> findEvaluateurArticles(String username){
       if (articleList!=null){
           this.articleList.clear();
       }
       List<AvisArticle> avisArticles=this.findByEvaluateurUsername(username);
       if (avisArticles==null){
           return null;
       }
       avisArticles.forEach(av->{
           Article article=this.articleService.findById(av.getArticle().getId());
           this.articleList.add(article);
       });
       return articleList;
    }

    @Override
    public List<AvisArticle> findAvisArticleByArticleRef(String ref) {
        return this.avisArticleDao.findByArticleRef(ref);
    }

    @Override
    @Transactional
    public int deleteByArticleRef(String ref) {
        return this.avisArticleDao.deleteByArticleRef(ref);
    }

}
