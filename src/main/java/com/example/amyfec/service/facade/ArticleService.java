package com.example.amyfec.service.facade;

import com.example.amyfec.bean.Article;
import com.example.amyfec.bean.ArticleItem;

import java.util.List;

public interface ArticleService {
    int save(Article article);
    Article findByRef(String ref);
    List<Article> findAllUserArticles();
    Article findByTitre(String titre);
    Article findById(int id);
    public List<Article> findUserArticles(String username);
    public List<ArticleItem> findByAuteurUsername(String username);
    public int soumettreArticle(int id);
    //List<Article> findNewArticles();
    void saveEditorDecision(String ref, String avisEdideur);
    int evaluateArticle(String userUsername,String articleRef,String decision);
    int evaluateByEditor(String ref,String decision);
    public int addArticleEvaluater(String username,String articleRef);
    public void editArticle(Article article);
    int deleteByRef(String ref);
}
