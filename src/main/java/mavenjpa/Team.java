package mavenjpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    //연관 관계의 주인이 Member의 team이라고 명시
    //select 전용 insert, update 등에 사용x
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();


    /*아래와 같은 방법으로 직접 팀에서 멤버를 넣어도된다.*/
    public void addMember(Member member) {
        member.setTeam(this);
        members.add(member);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
