package spring.jpa.repository.second;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.jpa.model.Member;

import java.util.Optional;

@Repository
public interface SecondMemberRepository extends JpaRepository<Member,Long> {
    @Query("SELECT m FROM Member m WHERE m.id=:id ")
    Optional<Member> findById(@Param("id") Long id);
}
