package me.scpark.springdeveloper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    //findAll, findById, save, deleteById, deleteAll, ...


    public Optional<Member> findByName(String name);

//    public Optional<Member> findByEmail(String email);

}
