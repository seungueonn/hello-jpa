package hellojpa;

import hellojpa.Member;
import hellojpa.Team;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);

//            CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("name"), "kim"));

            CriteriaQuery<Member> cq = query.select(m);

            String username = "kim";
            // 동적 쿼리 가능
            if (username != null) {
                cq = cq.where(cb.equal(m.get("name"), "kim"));
            }

            List<Member> resultList = em.createQuery(cq).getResultList();

            // 그치만 실무에서 안쓴다!

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
