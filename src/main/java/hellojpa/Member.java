package hellojpa;


import mavenjpa.Team;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private long id;
    @Column(name = "USERNAME")
    private String username;

/*    @Column(name = "TEAM_ID")
    private Long teamId;*/

    //멤버 입장에선 many이지만 팀은 1개니까
    @ManyToOne
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
