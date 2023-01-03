package com.example.amyfec.dao;

import com.example.amyfec.bean.ArticleItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleItemDao extends JpaRepository<ArticleItem,Long> {
    List<ArticleItem> findByAuteurUsername(String username);
    List<ArticleItem> findByArticleRef(String ref);
    int deleteByArticleRef(String ref);
}
