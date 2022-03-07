package spring.jpa.repository.first;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import spring.jpa.model.Member;

import java.util.Optional;


@Repository
public interface FirstMemberRepository extends JpaRepository<Member,Long> {
    @Query("SELECT m FROM Member m WHERE m.id=:id ")
    Optional<Member> findById(@Param("id") Long id);
}
