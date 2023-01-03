package com.example.amyfec.dao;

import com.example.amyfec.bean.Article;
import com.example.amyfec.security.bean.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao extends JpaRepository<Article,Long> {
    Article findByRef(String ref);
    List<Article> findAll();
    Article findByTitre(String titre);
    Article findById(int id);
   // List<Article findAllByDeposed(boolean b);
    int deleteByRef(String ref);
}
