import hellojpa.Member;
import hellojpa.Team;

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
            member.setName("member1");

            em.persist(member);

            Team team = new Team();
            team.setName("teamA");
            team.getMembers().add(member);

            em.persist(team);

//            Team team = new Team();
//            team.setName("TeamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setName("member1");
////            member.changeTeam(team);   // **
//            team.addMember(member);     // changeTeam과 같은 내용. 연관관계 주인은 같음. 둘 중 하나만 하면 됨
//            em.persist(member);

//            team.getMembers().add(member); // ** 를 하지 않으면, 아까 persist(team)된
                                            // team 객체 그대로 (list 비어있는 상태).
                                            // -> Member.setTeam 메소드에 편의 메소드 추가 -> setTeam -> changeTeam

            // TODO : 따라서, 양방향 연관관계 세팅하면, 양쪽에다가 값을 다 입력해야함

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId());  // 1차 캐시
            List<Member> members = findTeam.getMembers();

            System.out.println("==========");
            for (Member m : members) {
                System.out.println("m = " + m.getName());
            }
            System.out.println("==========");






            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();

    }



}
