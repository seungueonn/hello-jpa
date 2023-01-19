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
            System.out.println("===========COLLECTIONS EDIT=============");

            // homeCity -> newCity
            // findMember.getWorkAddress().setCity("newCity")
            // 값 타입은 setter로 수정하면 안됨. new Address() 해야함
            // 인스턴스 자체를 바꿔넣어야함

            Address a = findMember.getWorkAddress();
            findMember.setWorkAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            //chicken -> 한식
            findMember.getFavoriteFoods().remove("chicken");
            findMember.getFavoriteFoods().add("한식");

            findMember.getAddressHistory().remove(new Address("oldCity1", "oldStreet1", "1939"));
            findMember.getAddressHistory().add(new Address("newCity1", "newStreet1", "new1939"));
            // equals and hashCode를 잘 해놔야 지워짐

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
