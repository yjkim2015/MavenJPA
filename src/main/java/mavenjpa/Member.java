package mavenjpa;


import javax.persistence.*;

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
    @JoinColumn(name="TEAM_ID")
    private Team team;

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
    }
}
