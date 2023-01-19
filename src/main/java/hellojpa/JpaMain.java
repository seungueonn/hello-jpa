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
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setName("member1");
            member.setWorkAddress(new Address("city", "street", "1999"));
            member.getFavoriteFoods().add("chicken");
            member.getFavoriteFoods().add("pizza");

            member.getAddressHistory().add(new Address("oldCity1", "oldStreet1", "1939"));
            member.getAddressHistory().add(new Address("oldCity2", "oldStreet2", "1939"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("===========START=============");
            Member findMember = em.find(Member.class, member.getId());
            System.out.println("===========LAZY LOADING=============");
            // collection 들은 lazy loading default
            List<Address> addressHistory = findMember.getAddressHistory();
            for (Address address : addressHistory) {
                System.out.println("address = " + address.getCity());
            }
            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for (String favoriteFood : favoriteFoods) {
                System.out.println("favoriteFood = " + favoriteFood);
            }

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
