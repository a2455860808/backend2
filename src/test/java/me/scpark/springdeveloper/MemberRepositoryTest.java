package me.scpark.springdeveloper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Sql("/insert-members.sql")
    @DisplayName("MemberRepository를 통해 member 테이블의 모든 레고드(3개) 가저오기.\")\n")
    @Test

    public void getAllMembers() {
        //given

        //when
        List<Member> members = memberRepository.findAll();
        //then
        assertThat(members.size()).isEqualTo(3);

    }

}