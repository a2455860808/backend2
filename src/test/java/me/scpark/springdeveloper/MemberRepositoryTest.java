package me.scpark.springdeveloper;

import jakarta.transaction.Transactional;
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

    @Sql("/insert-members.sql")
    @DisplayName("删除member中的id为3的记录")
    @Test
    public void deleteById3() {
        //given

        //when
        memberRepository.deleteById(3L);

        //then
        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(2);
    }


    @Sql("/insert-members.sql")
    @DisplayName("删除成员表中所有记录")
    @Test
    public void deleteAll() {

        //given
        List<Member> members = memberRepository.findAll();
        assertThat(members.size()).isEqualTo(3);
        //when
        memberRepository.deleteAll();
        //then
        members = memberRepository.findAll();
        //assertThat(members.size()).isEqualTo(0);
        assertThat(members.size()).isZero();
    }
    @DisplayName("一次性插入成员名为A，B的记录")
    @Test
    public void saveMembers() {
        //given
        List<Member> members = List.of(new Member("A"), new Member("B"));

        //when
        memberRepository.saveAll(members);                            //删除

        //then                                                             方法2：
        assertThat(memberRepository.findAll().size()).isEqualTo(2);//members = memberRepository.findAll();
                                                                           //assertThat(members.size()).isEqualTo(2);

    }

    @Sql("/insert-members.sql")
    @DisplayName("通过变更检测的功能将ID为2的记录名称变更为'B'和'BB'")
    //@Transactional
    @Test
    public void update(){
        //given
        Member member = memberRepository.findById(2L).get();

        //when
        member.setName("BB");

        //then
        member = memberRepository.save(member);
        assertThat(member.getName()).isEqualTo("BB");

    }
}