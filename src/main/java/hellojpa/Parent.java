package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String name;

    //CacadeType.all로하면 em.persist에 parent 하나만하면 child가 알아서 샤샤샥 쇼쇼쇽
    /*
        CASE 종류
        ALL 모두
        PERSIST : 영속
        REMOVE : 삭제
        MERGE : 병함
        REFRESH : REFRESH
        DETACH : DETACH

        하나의 부모가 자식들을 관리할때 써야함 EX) 게시판 첨부물

        orphanRemoval 고아객체 삭제
        * 참조하는 곳이 하나일 때 사용해야함

     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> childList = new ArrayList<>();

    public void addChild(Child child) {
        childList.add(child);
        child.setParent(this);
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

    public List<Child> getChildList() {
        return childList;
    }

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }
}
