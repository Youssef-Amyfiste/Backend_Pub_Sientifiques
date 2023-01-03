package com.example.amyfec.service.impl;

import com.example.amyfec.bean.Article;
import com.example.amyfec.bean.ArticleItem;
import com.example.amyfec.dao.ArticleItemDao;
import com.example.amyfec.service.facade.ArticleItemService;
import com.example.amyfec.service.facade.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleItemServiceImpl implements ArticleItemService {
    @Autowired
    ArticleItemDao articleItemDao;
    @Autowired
    ArticleService articleService;
    public void save(ArticleItem articleItem){
        this.articleItemDao.save(articleItem);
    }

    @Override
    public List<ArticleItem> findByAuteurUsername(String username) {
        return articleItemDao.findByAuteurUsername(username);
    }

    @Override
    public List<ArticleItem> findByArticleRef(String ref) {
        return this.articleItemDao.findByArticleRef(ref);
    }

    @Override
    @Transactional
    public int deleteByArticleRef(String ref) {
        return this.articleItemDao.deleteByArticleRef(ref);
    }

}
