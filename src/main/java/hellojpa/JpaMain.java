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

            Member member = new Member();
            member.setName("hello");

            em.persist(member);

            em.flush();
            em.clear();

            //application clear 와 같은 의미
//          Member findMember = em.find(Member.class, member.getId());

            Member findMember = em.getReference(Member.class, member.getId()); // 가짜(프록시) 엔티티 객체 조회
            System.out.println("findMember = " + findMember.getId());
            System.out.println("findMember = " + findMember.getName()); // 레퍼런스에 없네? 하고 select 쿼리 날림
            // findMember는 계속 프록시 객체 프록시 유지.
            //
            em.flush();
            em.clear();

            System.out.println("findMember.getClass() = " + findMember.getClass());
            System.out.println("isLoaded=" + emf.getPersistenceUnitUtil().isLoaded(findMember));

            findMember.getName(); // 강제 초기화
            System.out.println("isLoaded=" + emf.getPersistenceUnitUtil().isLoaded(findMember));

            Hibernate.initialize(findMember); // 강제 초기화

            tx.commit();



        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }

    // Member를 조회할 때 Team도 함께 조회해야 할까?
    private static void printMember(Member member) {
        System.out.println("member.getName() = " + member.getName());
    }

    private static void printMemberAndTeam(Member member) {
        String userName = member.getName();
        System.out.println("userName = " + userName);
        Team team = member.getTeam();
        System.out.println("team = " + team.getName());
    }


}
