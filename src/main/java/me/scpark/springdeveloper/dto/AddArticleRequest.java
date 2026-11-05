package me.scpark.springdeveloper.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.scpark.springdeveloper.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    //public String getTitle() {return title;}
    //public String getContent() {return content;}

    //public AddArticleRequest(){
    //    super();
    //}

    //public AddArticleRequest(String title, String content) {
    //    this.title = title;
    //    this.content = content;
    //}
    public Article toEntity(){
//        return new Article(this.title, this.content);
        return Article.builder().title(title).content(content).build();
    }
}
