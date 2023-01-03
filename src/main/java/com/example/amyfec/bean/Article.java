package com.example.amyfec.bean;



import com.example.amyfec.security.bean.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO )
    private int id;
    private String ref;
    private String manUsername;
    private String titre;
    private String type;
    private String etat;
    private String motif;
    private int taille;
    private String resume;
    private boolean isPublished=false;
    private boolean isSubmited=false;
    private boolean isEvaluated=false;
    private boolean isDeposed=true;
    private boolean onLastDecision=true;
    //private List<String> motsCle;
    private String contenu;
    @OneToMany(mappedBy = "article")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<AvisArticle> avisArticle;
    private String avisEditeur;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateSoumission;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateModification;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateRevision;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateEvaluation;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;
    private boolean isReviewd;
    @OneToOne
    private User editeur;
    @OneToMany(mappedBy = "article")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<ArticleItem> articleItems;
    private int nbrEvaluateur=0;

    public Date getDateEvaluation() {
        return dateEvaluation;
    }

    public void setDateEvaluation(Date dateEvaluation) {
        this.dateEvaluation = dateEvaluation;
    }

    public int getNbrEvaluateur() {
        return nbrEvaluateur;
    }

    public void setNbrEvaluateur(int nbrEvaluateur) {
        this.nbrEvaluateur = nbrEvaluateur;
    }

    public boolean isOnLastDecision() {
        return onLastDecision;
    }

    public void setOnLastDecision(boolean onLastDecision) {
        this.onLastDecision = onLastDecision;
    }

    public boolean isEvaluated() {
        return isEvaluated;
    }

    public void setEvaluated(boolean evaluated) {
        isEvaluated = evaluated;
    }

    public boolean isDeposed() {
        return isDeposed;
    }

    public void setDeposed(boolean deposed) {
        isDeposed = deposed;
    }

    public String getManUsername() {
        return manUsername;
    }

    public void setManUsername(String manUsername) {
        this.manUsername = manUsername;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public boolean isSubmited() {
        return isSubmited;
    }

    public void setSubmited(boolean submited) {
        isSubmited = submited;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public List<AvisArticle> getAvisArticle() {
        return avisArticle;
    }

    public void setAvisArticle(List<AvisArticle> avisArticle) {
        this.avisArticle = avisArticle;
    }

    public String getAvisEditeur() {
        return avisEditeur;
    }

    public void setAvisEditeur(String avisEditeur) {
        this.avisEditeur = avisEditeur;
    }

    public Date getDateSoumission() {
        return dateSoumission;
    }

    public void setDateSoumission(Date dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    public Date getDateRevision() {
        return dateRevision;
    }

    public void setDateRevision(Date dateRevision) {
        this.dateRevision = dateRevision;
    }

    public boolean isReviewd() {
        return isReviewd;
    }

    public void setReviewd(boolean reviewd) {
        isReviewd = reviewd;
    }

    public User getEditeur() {
        return editeur;
    }

    public void setEditeur(User editeur) {
        this.editeur = editeur;
    }


    public List<ArticleItem> getArticleItems() {
        return articleItems;
    }

    public void setArticleItems(List<ArticleItem> articleItems) {
        this.articleItems = articleItems;
    }
}
