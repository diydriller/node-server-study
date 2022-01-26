package spring.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.jpa.model.Member;

import javax.persistence.LockModeType;
import java.util.Optional;


public interface MemberRepository  extends JpaRepository<Member,Long> {


    @Query("SELECT m FROM Member m WHERE m.id=:id ")
    Optional<Member> findById(@Param("id") Long id);
}
