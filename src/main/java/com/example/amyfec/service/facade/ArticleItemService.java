package com.example.amyfec.service.facade;

import com.example.amyfec.bean.ArticleItem;

import java.util.List;

public interface ArticleItemService {
    public void save(ArticleItem articleItem);
    List<ArticleItem> findByAuteurUsername(String username);
    List<ArticleItem> findByArticleRef(String ref);
    int deleteByArticleRef(String ref);
}
