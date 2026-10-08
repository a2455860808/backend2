package me.scpark.springdeveloper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    //findAll, findById, save, deleteById, deleteAll, ...


    public Optional<Member> findByName(String name);

}
