package me.scpark.springdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.scpark.springdeveloper.domain.Article;
import me.scpark.springdeveloper.dto.AddArticleRequest;
import me.scpark.springdeveloper.dto.UpdateArticleRequest;
import me.scpark.springdeveloper.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "spring.datasource.url=jdbc:h2:mem:blog-api-controller-test")
@AutoConfigureMockMvc
class BlogApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BlogRepository blogRepository;

    @BeforeEach
    void cleanUp() {
        blogRepository.deleteAll();
    }

    @DisplayName("POST /api/articles 成功创建文章")
    @Test
    void addArticle() throws Exception {
        String title = "11月4日";
        String content = "用JUnit实践了博客文章，创建请求是否成功处理";
        AddArticleRequest request = new AddArticleRequest(title, content);

        mockMvc.perform(post("/api/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content));

        assertThat(blogRepository.findAll())
                .extracting(Article::getTitle, Article::getContent)
                .containsExactly(tuple(title, content));
    }

    @DisplayName("GET /api/articles 成功查询全部文章")
    @Test
    void getAllArticles() throws Exception {
        blogRepository.save(new Article("标题1", "内容1"));
        blogRepository.save(new Article("标题2", "内容2"));

        mockMvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[?(@.title == '标题1')].content").value(containsInAnyOrder("内容1")))
                .andExpect(jsonPath("$[?(@.title == '标题2')].content").value(containsInAnyOrder("内容2")));
    }

    @DisplayName("GET /api/articles/{id} 成功查询单篇文章")
    @Test
    void getArticle() throws Exception {
        Article article = blogRepository.save(new Article("查询用的标题", "查询用的内容"));

        mockMvc.perform(get("/api/articles/{id}", article.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(article.getTitle()))
                .andExpect(jsonPath("$.content").value(article.getContent()));
    }

    @DisplayName("DELETE /api/articles/{id} 成功删除单篇文章")
    @Test
    void deleteArticle() throws Exception {
        Article article = blogRepository.save(new Article("待删除的标题", "待删除的内容"));
        Article other = blogRepository.save(new Article("保留的标题", "保留的内容"));

        mockMvc.perform(delete("/api/articles/{id}", article.getId()))
                .andExpect(status().isOk());

        assertThat(blogRepository.findAll())
                .extracting(Article::getId)
                .containsExactly(other.getId());
    }

    @DisplayName("PUT /api/articles/{id} 成功修改单篇文章")
    @Test
    void updateArticle() throws Exception {
        Article article = blogRepository.save(new Article("修改前的标题", "修改前的内容"));
        String title = "修改后的标题";
        String content = "修改后的内容";
        UpdateArticleRequest request = new UpdateArticleRequest(title, content);

        mockMvc.perform(put("/api/articles/{id}", article.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.content").value(content));

        Article updated = blogRepository.findById(article.getId()).orElseThrow();
        assertThat(updated.getTitle()).isEqualTo(title);
        assertThat(updated.getContent()).isEqualTo(content);
    }
}
