package me.scpark.springdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.scpark.springdeveloper.domain.Article;
import me.scpark.springdeveloper.dto.AddArticleRequest;
import me.scpark.springdeveloper.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        blogRepository.findAll();
        return blogRepository.findAll();
    }
}
