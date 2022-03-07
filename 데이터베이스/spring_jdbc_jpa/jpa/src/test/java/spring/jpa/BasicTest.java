package spring.jpa;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.criterion.Projection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring.jpa.dto.MemberDto;
import spring.jpa.model.Member;
import spring.jpa.model.QMember;
import spring.jpa.model.Team;


import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static spring.jpa.model.QMember.member;

@SpringBootTest
@Transactional
public class BasicTest {
    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before(){

        queryFactory=new JPAQueryFactory(em);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
    }
    @Test
    public void jpql(){
        Member findMember = em.createQuery("select m from Member m where m.username=:username",Member.class)
                .setParameter("username","member1")
                .getSingleResult();
        assertThat(findMember.getUsername().equals("member1"));

    }

    @Test
    public void querydsl(){

        QMember m = new QMember("m");
        Member findMember = queryFactory
                .select(m)
                .from(m)
                .where(m.username.eq("member1"))
                .fetchOne();
        assertThat(findMember.getUsername().equals("member1"));
    }

    @Test
    public void tupleProjection(){
        List<Tuple> result = queryFactory
                .select(member.username,member.age)
                .from(member)
                .fetch();
        for(Tuple tuple:result){
            System.out.println(tuple.get(member.username));
            System.out.println(tuple.get(member.age));
        }
    }

    @Test
    public void findDtoByConstructor(){
        List<MemberDto> result = queryFactory
                .select(Projections.constructor(MemberDto.class,member.username,member.age))
                .from(member)
                .fetch();

        for(MemberDto dto:result){
            System.out.println(dto);
        }
    }

    @Test
    public void dynamicQueryByBooleanBuilder(){
        String username="member1";
        Integer age=10;

        List<Member> result = searchMember(username,age);
        for(Member member:result){
            System.out.println(member);
        }
    }

    private List<Member> searchMember(String username,Integer age){
        BooleanBuilder builder = new BooleanBuilder();
        if(username!=null){
            builder.and(member.username.eq(username));
        }
        if(age!=null){
            builder.and(member.age.eq(age));
        }

        return queryFactory
                .selectFrom(member)
                .where(builder)
                .fetch();

    }

}
