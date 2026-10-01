package me.scpark.springdeveloper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public boid cleanup(){
        memberRepository.deleteAll();
    }

    @Test
    void getAllMembers() throws Exception {


        Member m = new Member("SCPARK");
        Member savedMember = memberRepository.save(m);

    final ResultActions result = mockMvc.perform(get("/member"))
            .accpet(MediaType.APPLICATION_JSON);


    result.andExpect(status().isOk))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("[0].name").value(savedMember.getName()));
    }
}