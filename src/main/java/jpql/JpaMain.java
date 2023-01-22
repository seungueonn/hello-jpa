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
            //enum type은 패키지까지 다 넣어주어야함
//            String query = "select m.username,'HELLO',true  From Member m " +
//                    "where m.type=jpql.MemberType.ADMIN";

            // 아니면 setParameter 사용
            String query = "select m.username,'Hello',true From Member m " +
                    "where m.type = :userType";
            List<Object[]> resultList = em.createQuery(query)
                    .setParameter("userType",MemberType.ADMIN)
                    .getResultList();

            for (Object[] o : resultList) {
                System.out.println("o = " + o[0].toString());
                System.out.println("o = " + o[1].toString());
                System.out.println("o = " + o[2].toString());
            }

            // Entity 타입 조회
//            em.createQuery("select i from Item i where type(i) = Book",Item.class)


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
