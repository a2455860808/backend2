package me.scpark.springdeveloper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class QuizControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("quiz(): GET /quiz?code=1 -> 응답 코드는 201,응답 본문은 Created")
    @Test
    void getQuiz1() throws Exception {

        final String url = "/quiz";

        final ResultActions result = mockMvc.perform(get(url).param("code","1"));

        result.andExpect(status().isCreated()).andExpect(content().string("Created"));

    }

    @DisplayName("POST /quiz: value=1 -> 403 Forbidden")
    @Test

    void postQuiz1() throws Exception {

        final String url = "/quiz";

        final ResultActions result = mockMvc.perform(post(url)
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content(objectMapper.writeValueAsString(new Code(1))));

        result.andExpect(status().isForbidden())
                .andExpect(content().string("Forbidden"));
    }


    @DisplayName("GET /quiz?code=2 -> 400 Bad Request")
    @Test
    void getQuiz2() throws Exception {
        //given
        final String url = "/quiz";
        //when
        final ResultActions result = mockMvc.perform(get(url).param("code","2"));
        //then
        result.andExpect(status().isBadRequest()).andExpect(content().string("Bad Request"));

    }



    @DisplayName("POST /quiz: value=2 -> 200 OK!")
    @Test

    void postQuiz2() throws Exception {

        final String url = "/quiz";

        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Code(2))));

        result.andExpect(status().isOk())
                .andExpect(content().string("OK!"));
    }



    @DisplayName("GET /quiz?code=3 -> 200 OK")
    @Test
    void getQuiz3() throws Exception {
        //given
        final String url = "/quiz";
        //when
        final ResultActions result = mockMvc.perform(get(url).param("code","3"));
        //then
        result.andExpect(status().isOk()).andExpect(content().string("OK"));

    }



    @DisplayName("POST /quiz: value=3 -> 200 OK!")
    @Test

    void postQuiz3() throws Exception {

        final String url = "/quiz";

        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Code(3))));

        result.andExpect(status().isOk())
                .andExpect(content().string("OK!"));
    }


    @DisplayName("GET /quiz?code=4 -> 200 OK")
    @Test
    void getQuiz4() throws Exception {
        //given
        final String url = "/quiz";
        //when
        final ResultActions result = mockMvc.perform(get(url).param("code","4"));
        //then
        result.andExpect(status().isOk()).andExpect(content().string("OK"));

    }



    @DisplayName("POST /quiz: value=4 -> 200 OK!")
    @Test

    void postQuiz4() throws Exception {

        final String url = "/quiz";

        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Code(4))));

        result.andExpect(status().isOk())
                .andExpect(content().string("OK!"));
    }
}
