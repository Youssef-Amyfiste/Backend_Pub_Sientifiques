package com.example.amyfec.ws;

import com.example.amyfec.bean.Article;
import com.example.amyfec.bean.ArticleItem;
import com.example.amyfec.bean.AvisArticle;
import com.example.amyfec.service.facade.ArticleItemService;
import com.example.amyfec.service.facade.ArticleService;
import com.example.amyfec.service.facade.AvisArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleWs {
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleItemService articleItemService;
    @Autowired
    AvisArticleService avisArticleService;
    @PostMapping("/save")
    public int save(@RequestBody Article article) {
        return articleService.save(article);
    }
    @GetMapping("findByRef/ref/{ref}")
    public Article findByRef(@PathVariable String ref) {
        return articleService.findByRef(ref);
    }
    @GetMapping("/findAllUserArticles")
    public List<Article> findAllUserArticles() {
        return articleService.findAllUserArticles();
    }
    @GetMapping("/findUserArticles/username/{username}")
    public List<Article> findUserArticles(@PathVariable String username) {
        return articleService.findUserArticles(username);
    }
    @GetMapping("/findByAuteurUsername/username/{username}")
    public List<ArticleItem> findByAuteurUsername(@PathVariable String username) {
        return articleService.findByAuteurUsername(username);
    }
   @GetMapping("/soumettreArticle/id/{id}")
    public int soumettreArticle(@PathVariable int id) {

        return articleService.soumettreArticle(id);
    }
   /* @GetMapping("/findNewArticles")
    public List<Article> findNewArticles() {
        return articleService.findNewArticles();
    }*/
    @GetMapping("findByArticleRef/ref/{ref}")
    public List<ArticleItem> findByArticleRef(@PathVariable String ref) {
        return articleItemService.findByArticleRef(ref);
    }
    @GetMapping("/evaluateArticle/username/{userUsername}/articleRef/{articleRef}/decision/{decision}")
    public int evaluateArticle(@PathVariable String userUsername,@PathVariable String articleRef,@PathVariable String decision) {
       return articleService.evaluateArticle(userUsername, articleRef, decision);
    }
    @GetMapping("/evaluateByEditor/ref/{ref}/decision/{decision}")
    public int evaluateByEditor(@PathVariable String ref,@PathVariable String decision) {
        return articleService.evaluateByEditor(ref, decision);
    }
    @GetMapping("/addArticleEvaluater/username/{username}/ref/{articleRef}")
    public int addArticleEvaluater(@PathVariable String username,@PathVariable String articleRef) {
        return articleService.addArticleEvaluater(username, articleRef);
    }
@GetMapping("/findEvaluateurArticles/username/{username}")
    public List<Article> findEvaluateurArticles(@PathVariable String username) {
        return avisArticleService.findEvaluateurArticles(username);
    }
@GetMapping("/findByAvisArticleRef/ref/{ref}")
    public List<AvisArticle> findAvisArticleByArticleRef(@PathVariable String ref) {
        return avisArticleService.findAvisArticleByArticleRef(ref);
    }
    @PutMapping("/editArticle")
    public void editArticle(@RequestBody Article article) {
        articleService.editArticle(article);
    }
    @DeleteMapping("/deleteByRef/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return articleService.deleteByRef(ref);
    }
}
