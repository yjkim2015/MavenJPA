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

            /*
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeamId(team.getId());

            em.persist(member);


                이런식으로 하면 문제점..
                객체를 테이블에 맞추어 데이터 중심으로 모델링하면, 협력 관계를 만들 수 없다.
                이유:
                테이블은 외래 키로 조인을 사용해서 연관된 테이블을 찾는다.
                객체는 참조를 사용해서 연관된 객체를 찾는다.
                테이블과 객체 사이에는 이런 큰 간격이 있다.

            Member findMember = em.find(Member.class, member.getId());

            Long findTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, findTeamId);
            */

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);

            em.persist(member);

            //영속성 컨텍스트가 아닌 db에서 강제로 끌어와서 보고싶으면
            //em.flush();
            //em.clear();
            Member findMember = em.find(Member.class, member.getId());

            Team findTeam = findMember.getTeam();

            System.out.println("findTeam : " + findTeam.getName());


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
