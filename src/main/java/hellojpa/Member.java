package hellojpa;


import mavenjpa.Team;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private long id;
    @Column(name = "USERNAME")
    private String username;

/*    @Column(name = "TEAM_ID")
    private Long teamId;*/

    //멤버 입장에선 many이지만 팀은 1개니까
    //fetch type lazy인 경우 프록시 객체를 조회한다. 즉, 멤버 클래스만 디비에서 조회한다.
    //즉, team의 데이터를 실제로 사용하기 위해 쓰는순간 쿼리가 발생한다.

    /*
        member와 team의 조회가 함께 자주 사용된다면 성능상의 이점을 가져가기 위해
        Lazy말고 Eager를 써라. 그렇다면 조인을 사용해 가져온다.

        프록시와 즉시로딩 주의

        실무에서 지연로딩만 사용해라!!

        즉시 로딩을 적용하면 예상하지 못한 SQL이 발생한다.

        즉시 로딩은 JPQL에서 N+1 문제를 일으킨다.

        em.find에서는 pk위주로 찾아서 jpa가 알아서 최적화를 한다..
        but jpql은 sql그대로를 번역하기 떄문에... eager로 해도 쿼리가 각각 나간다.

        @ManyToOne, @OneToOne은 기본이 즉시 로딩 -> Lazy로 설정
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TEAM_ID", insertable = false, updatable = false)
    private Team team;


    /**
     * 1:1 관계
     */
    @OneToOne
    @JoinColumn(name="LOCKER_ID")
    private Locker locker;


    /*
        for 다대다 but -> 다대다는 일대다 로 풀어줘야함. 실무에서 쓰지않음
     */
    //@ManyToMany
    //@JoinTable(name = "MEMBER_PRODUCT")
    //private List<Product> products = new ArrayList<>();

    // 위의 ManyToMany를 OneToMany로 다대일로 풀어준다.
    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();


    @Embedded
    private Address homeAddress;

    @Embedded
    private Period workPeriod;

    //값 타입 컬렉션
    /*
        값 타입을 하나 이상 저장할 때 사용
        @ElementCollection, @CollectionTable 사용
        데이터베이스는 컬렉션을 같은 테이블에 저장할 수 없다.
        컬렉션을 저장하기 위한 별도의 테이블이 필요함
     */
    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns = @JoinColumn(name="MEMBER_ID"))
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

   /* @ElementCollection
    @CollectionTable(name = "ADDRESS", joinColumns = @JoinColumn(name="MEMBER_ID"))
    private List<Address> addressHistory = new ArrayList<>();
*/

   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "MEMBER_ID")
   private List<AddressEntity> addressHistory = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   /* public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }*/

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
        //양방향 연관관계 양쪽 설정 [연관 관계 편의 메소드]
        // 현재 team이 패키지가 달라서...  주석해놨음
        //team.getMembers().add(this);
    }
}
