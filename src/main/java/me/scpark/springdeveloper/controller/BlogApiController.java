package me.scpark.springdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.scpark.springdeveloper.domain.Article;
import me.scpark.springdeveloper.dto.AddArticleRequest;
import me.scpark.springdeveloper.dto.ArticleResponse;
import me.scpark.springdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }


    @GetMapping("/api/article")
    public ResponseEntity<List<ArticleResponse>> getAllArticles() {
        List<Article> articleList = blogService.getAll();
        List<ArticleResponse> articleResponseList = new ArrayList<>();
        for (Article article : articleList) {
            ArticleResponse ar = new ArticleResponse(article);
            articleResponseList.add(ar);
        }
        return ResponseEntity.ok().body(articleResponseList);
    }







//    public BlogApiController(BlogService Service) {
//        this.blogService = Service;
//

//    }
}
