package mavenjpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
           /*
            insert
            Member member = new Member();
            member.setId(1L);
            member.setName("HelloA");
            em.persist(member);*/

            /*
            select
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember id : " + findMember.getId());
            System.out.println("findMember name : " + findMember.getName());
            */

            /*
            delete
            em.remove(member);
            */

            /*
            update
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloB");
            */

            /*
            jpql
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList();

            for (Member member : result) {
                System.out.println("member.name : " + member.getName());
            }
            */

            tx.commit();

        }
        catch (Exception ex) {
            tx.rollback();
        }
        finally {
            em.close();

            emf.close();
        }
    }
}
