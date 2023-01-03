package com.example.amyfec.bean;

import com.example.amyfec.security.bean.User;

import javax.persistence.*;

@Entity
public class AvisArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String ref;
    @ManyToOne
    private Article article;
    @ManyToOne
    private User evaluateur;
    private String decision;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

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

    public User getEvaluateur() {
        return evaluateur;
    }

    public void setEvaluateur(User evaluateur) {
        this.evaluateur = evaluateur;
    }
}
