package com.example.amyfec.service.facade;

import com.example.amyfec.bean.Article;
import com.example.amyfec.bean.AvisArticle;

import java.util.List;

public interface AvisArticleService {
    public void save(AvisArticle avisArticle);
    List<AvisArticle> findByEvaluateurUsername(String username);
    AvisArticle findByEvaluateurUsernameAndArticleRef(String username, String ref);
    public List<Article> findEvaluateurArticles(String username);
    List<AvisArticle> findAvisArticleByArticleRef(String ref);
    int deleteByArticleRef(String ref);
}
