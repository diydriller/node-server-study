package spring.jpa;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import spring.jpa.model.Member;

import javax.persistence.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ConcurrencyTest {



    @PersistenceUnit
    private EntityManagerFactory emf;


    void increasePopularity(Long userId){

        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();

        Member member = em.createQuery("select m from Member m where m.id=:id",Member.class)
                .setParameter("id",userId)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getSingleResult();
        long newPopularity=member.getPopularity()+1;
        em.createQuery("update Member m set m.popularity=:popularity")
                .setParameter("popularity",newPopularity)
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
        long userId=1L,loopNum=100;

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
                }
            });
        }
        latch.await();

        long lastPopularity=getMember(userId).getPopularity();

        System.out.println(firstPopularity+" "+lastPopularity);
    }


}
