package com.example.amyfec.bean;

import com.example.amyfec.security.bean.User;

import javax.persistence.*;

@Entity
public class ArticleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String ref;
    @ManyToOne
    private Article article;
    @ManyToOne
    private User auteur;
    @ManyToOne
    private User evaluateur;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getAuteur() {
        return auteur;
    }

    public void setAuteur(User auteur) {
        this.auteur = auteur;
    }

    public User getEvaluateur() {
        return evaluateur;
    }

    public void setEvaluateur(User evaluateur) {
        this.evaluateur = evaluateur;
    }
}
