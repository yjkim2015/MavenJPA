package hellojpa;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

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
           /* Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과함께 사라지다");
            movie.setPrice(10000);


            em.persist(movie);

            em.flush();
            em.close();

            Movie findMovie = em.find(Movie.class, movie.getId());

            System.out.println("findMovie : " + findMovie);*/

           Member member = new Member();
           member.setUsername("user1");
           member.setCreatedBy("kim");
           member.setCreatedDate(LocalDateTime.now());

            em.persist(member);
            em.flush();
            em.clear();

            Member findMember = em.getReference(Member.class, member.getId());
            System.out.println("findMember = " + findMember.getClass());
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.username = " + findMember.getUsername());
           /*
            프록시 기초
            em.find() vs em.getReference()
            em.find() 데이터베이스를 통해서 실제 엔티티 객체 조회
            em.getReference() : 데이터 베이스 조회를 미루는 가짜 (프록시) 엔티티 객체 조회

            프록시 특징
            실제 클래스를 상속 받아서 만들어짐
            실제 클래스와 겉 모양이 같다.
            사용하는 입장에서는 진짜 객체인지 프록시 객체인지 구분하지 않고 사용하면 됨(이론상)
            프록시 객체는 실제 객체의 참조(target)을 보관

            프롟시 객체를 호출하면 프록시 객체는 실제 객체의 메소드 호출

            프록시 특징
            * 프록시 객체는 처음 사용할 때 한 번만 초기화


            * 프록시 객체를 초기화 할 때, 프록시 객체가 실제 엔티티로 바뀌는 것은 아님!!!!!!,
            * 초기화되면 프록시 객체를 통해서 실제 엔티티에 접근 가능

            * 프록시 객체는 원본 엔티티를 상속받음, 따라서 타입 체크시 주의해야함
            * (== 비교 실패, 대신 instanceof 사용)
            *

            * 영속성 컨텍스트에 찾는 엔티티가 이미 있으면 em.getReference()를 호출해도
            * 실제 엔티티 반환
            *

            * 영속성 컨텍스트의 도움을 받을 수 없는 준영속 상태일 때, 프록시 초기화 문제 발생
            */



           /*
                cascade 에 대해 알아보자
                영속성 전이 : CASCADE
                연관관계랑은 전혀 관련 없음


            */
           Parent parent = new Parent();
           Child child1 = new Child();
           Child child2 = new Child();

            parent.addChild(child1);
            parent.addChild(child2);

            /*

            persist 3번 하고싶지않아 에바아니냐고 parent 하나만 넣으면 샤샤샥슈슈슉 하게해죠
            em.persist(parent);
            em.persist(child1);
            em.persist(child2);




            em.persist(parent);
            em.flush();
            em.clear();


            Parent findParent = em.find(Parent.class, parent.getId());
            findParent.getChildList().remove(0);
                      */


            /*
                값 타입 컬렉션 사용

                Member member = new Member();
                member.setUsername("member1");
                member.setHomeAddress(new Address("homeCity", "street", "10000"));

                member.getFavoriteFoods().add("치킨");
                member.getFavoriteFoods().add("족발");
                member.getFavoriteFoods().add("피자");


                member.getAddressHistory().add(new Address("old1", "street", "10000");
                member.getAddressHistory().add(new Address("old2", "street", "10000");

                em.persist(member);

                em.flush();
                em.clear();

                Member findMember = em.find(Member.class, member.getId());
                List<Address> addressHistory = findMember.getAddressHistory();
                for (Address address : addressHistory) {
                    System.out.println("address : " + address);
                }

                값 타임 컬렉션은 기본이 지연 로딩이다.

                // homeCity -> newCity 변경 불가
                findMember.getHomeAddress().setCity("");

                Address a = findMember.getHomeAddress();
                findMember.setHomeAddress(new Address("newCity", a.getStreet, a.getZipCode());

                //치킨 -> 한식 변경
                findMember.getFavoriteFoods().remove("치킨");
                findMember.getFavoriteFoods().add("한식");

                findMember.getAddressHistory().remove("new Address("old1", "street","10000");
                findMember.getAddressHistory().remove("new Address("newCity1", "street","10000");


                값 타입 컬렉션에 변경 사항이 발생하면 주인 엔티티와 연관된 모든 데이터를 삭제하고,
                값 타입 컬렉션에 있는 현재 값을 모두 다시 저장한다.

                결론 : 값 타입 컬렉션 쓰지말고 대안으로 일대다 관계를 고려해라
                @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)


                member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
                member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));

                값 타입은 정말 값 타입이라 판단될 떄만 사용
             */
            

            tx.commit();
        }
        catch (Exception ex) {
            ex.getStackTrace();
            tx.rollback();
        }
        finally {

        }
    }
}
