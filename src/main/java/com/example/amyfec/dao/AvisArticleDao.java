package com.example.amyfec.dao;

import com.example.amyfec.bean.AvisArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvisArticleDao extends JpaRepository<AvisArticle,Long> {
    List<AvisArticle> findByEvaluateurUsername(String username);
    AvisArticle findByEvaluateurUsernameAndArticleRef(String username, String ref);
    List<AvisArticle> findByArticleRef(String ref);
    int deleteByArticleRef(String ref);
}
