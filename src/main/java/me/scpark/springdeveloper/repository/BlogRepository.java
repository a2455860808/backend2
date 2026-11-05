package me.scpark.springdeveloper.repository;

import me.scpark.springdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;


//@Repository
public interface BlogRepository extends JpaRepository<Article, Long> {
}
