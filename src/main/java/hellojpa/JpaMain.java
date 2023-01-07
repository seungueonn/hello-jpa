package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setId(101L);
            member.setName("helloJPA");

            System.out.println("======BEFORE======");
            em.persist(member);
            System.out.println("======AFTER======");

            Member findMember = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L); // 1차 캐시에 있음, 조회 sql 안뜸
            System.out.println("findMember = " + findMember.getId());
            System.out.println("findMember = " + findMember.getName());
            // 조회 성공. em.persist() 이후 영속 컨텍스트로 이동, 1차 캐시에 저장, 1차캐시에서 불러옴


            // TODO : create
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("helloB");
//
//            em.persist(member);

            // TODO : read
//            Member findMember = em.find(Member.class, 1L);

            // TODO : update

//            findMember.setName("helloJPA");

            // TODO : delete
//            em.remove(findMember);

            tx.commit(); //쓰기 지연

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();





    }
}
