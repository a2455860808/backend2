package me.scpark.springdeveloper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.apache.tomcat.util.net.openssl.OpenSSLStatus.getName;
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

    @Sql("/insert-members.sql")
    @DisplayName("通过memberRepository")
    @Test
    public void getMemberByName() {
        //given


        //when ： select
        Optional<Member> optMember = memberRepository.findByName("C");
        Member member = optMember.get();

        //then
        assertThat(member.getId()).isEqualTo(3);

    }

    @Sql("/insert-members.sql")
    @DisplayName("通过MemberRepository")
    @Test
    public void getMemberById() {

        //given



        //when



       Member member = memberRepository.findById(2L).get();
        //then
        assertThat(member.getName()).isEqualTo("B");
    }

    @DisplayName("通过memberRepository添加名字是小树的记录")
    @Test
    public void saveMember() {
        //given
        //Member member = new Member(1L,"小树");
        Member member = new Member("小树");

        //when
        memberRepository.save(member);

        //then
        List<Member> result = memberRepository.findAll();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("小树");

    }
}