package jpql;

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


            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setTeam(team);
            em.persist(member);


            em.flush();
            em.clear();

            String query = "select m from Member m left  join m.team t";
            String query2 = "select m from Member m , Team t where m.username = t.name";
            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();

            // 외부 조인에서 team name이 teamA인 경우 (on 이용 - 조인 대상 필터링)
            String queryOn = "select m from Member m left join m.team t on t.name='teamA'";
             em.createQuery(queryOn, Member.class)
                    .getResultList();

             // 외부 조인에서 team name = username 인 경우 (on 이용 - 연관관계 외부 조인)
            String queryNew = "select m from Member m left join m.team t on t.name=m.username";

            // Paging
//            List<Member> result = em.createQuery("select m from Member m order by m.age desc ",Member.class)
//                    .setFirstResult(1) // 시작 페이지 조회
//                    .setMaxResults(10) // 조회할 데이터 수
//                    .getResultList();


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
