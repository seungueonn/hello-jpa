package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {


            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setTeam(team);
            member.setType(MemberType.ADMIN);
            em.persist(member);


            em.flush();
            em.clear();

            String query = "select " +
                    "case   when m.age <= 10 then '학생 요금'" +
                      "     when m.age >= 60 then '경로 요금'" +
                            "else '일반 요금' " +
                    "end " +
                    "from Member m";

            em.createQuery(query);

            String query1 = "select coalesce(m.username,'이름 없는 회원') from Member m";
            em.createQuery(query1);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        emf.close();
    }
}
