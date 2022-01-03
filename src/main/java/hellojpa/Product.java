package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/*
    for 다대다
 */
@Entity
public class Product {
    @Id @GeneratedValue
    private Long id;

    private String name;

    /* ManytoMany는 실무에서 사용하지않는다 oneToMany로 바꿔라
    //@ManyToMany(mappedBy = "products")
    //private List<Member> members = new ArrayList<>();
    */
    @OneToMany(mappedBy = "product")
    private List<MemberProduct> memberProducts = new ArrayList<>();

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
}
