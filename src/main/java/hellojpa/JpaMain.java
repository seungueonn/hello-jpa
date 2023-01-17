package hellojpa;

import hellojpa.Member;
import hellojpa.Team;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Address address = new Address("city", "street", "10000");

            Member member1 = new Member();
            member1.setName("member1");
            member1.setWorkAddress(address);
            em.persist(member1);


//            member1.getWorkAddress().setCity("newCity"); // embedded 타입 같은 값 타입을 여러 엔티티에서 공유하면 위험함
            // -> set method를 private 으로 변경
            // -> 변경하려면 그냥 새로 통으로 갈아야함

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();

        }

    }



}
