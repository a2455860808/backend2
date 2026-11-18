package me.scpark.springdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.scpark.springdeveloper.domain.Article;
import me.scpark.springdeveloper.dto.AddArticleRequest;
import me.scpark.springdeveloper.dto.UpdateArticleRequest;
import me.scpark.springdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    //@Autowired
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest dto){
        return blogRepository.save(dto.toEntity());

    }

    public List<Article> getAll(){
        return blogRepository.findAll();
    }
    public Article getById(Long id){
        return blogRepository.findById(id).get();
    }
    public void deleteArticle(Long id){
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article updateArticle(Long id, UpdateArticleRequest request) {
        Article article = getById(id);
        article.update(request.title(), request.content());
        return article;
    }

}
