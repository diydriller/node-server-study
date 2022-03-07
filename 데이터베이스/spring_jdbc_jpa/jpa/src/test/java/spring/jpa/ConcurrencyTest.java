package spring.jpa;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;
import spring.jpa.config.FirstJpaConfiguration;
import spring.jpa.model.Member;

import javax.persistence.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:mysql://localhost:3306/spring_jpa",
        "spring.datasource.username=root",
        "spring.sql.init.mode=never"
})
public class ConcurrencyTest {

    @PersistenceUnit
    private EntityManagerFactory emf;


    void increasePopularity(Long userId){

        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();

        Member member = em.createQuery("select m from Member m where m.id=:id",Member.class)
                .setParameter("id",userId)
                //.setLockMode(LockModeType.OPTIMISTIC)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getSingleResult();
        long newPopularity=member.getPopularity()+1;
        em.createQuery("update Member m set m.popularity=:popularity where m.id=:id")
                .setParameter("popularity",newPopularity)
                .setParameter("id",userId)
                .executeUpdate();

        em.getTransaction().commit();
        em.close();
    }


    Member getMember(Long userId){
        EntityManager em=emf.createEntityManager();
        Member member=em.createQuery("select m from Member m where m.id=:id",Member.class)
                .setParameter("id",userId)
                .getSingleResult();
        em.close();
        return member;
    }

    @Test
    @Transactional
    void popularityTest() throws Exception {
        long userId=1L,loopNum=1;

        long firstPopularity=getMember(userId).getPopularity();


        ExecutorService service = Executors.newFixedThreadPool(5);
        CountDownLatch latch = new CountDownLatch((int) loopNum);

        for(int i=0;i<loopNum;i++){
            service.execute(()->{
                try {
                    increasePopularity(userId);
                    latch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                    latch.countDown();
                }
            });
        }
        latch.await();

        long lastPopularity=getMember(userId).getPopularity();

        System.out.println(firstPopularity+" "+lastPopularity);
    }

    @Test
    void optimisticTest(){
        EntityManager em1=emf.createEntityManager();
        em1.getTransaction().begin();

        EntityManager em2=emf.createEntityManager();
        em2.getTransaction().begin();

        Member member1 = em1.createQuery("select m from Member m where m.id=:id",Member.class)
                .setParameter("id",1L)
                .setLockMode(LockModeType.OPTIMISTIC)
                .getSingleResult();
        Member member2 = em2.createQuery("select m from Member m where m.id=:id",Member.class)
                .setParameter("id",1L)
                .setLockMode(LockModeType.OPTIMISTIC)
                .getSingleResult();

        member2.setUsername("park");
        em2.persist(member2);
        em2.getTransaction().commit();
        em2.close();

        member1.setUsername("hyun");
        em1.persist(member1);
        em1.getTransaction().commit();
        em1.close();


    }


}
