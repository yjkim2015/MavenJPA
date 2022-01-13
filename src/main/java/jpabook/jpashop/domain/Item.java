package jpabook.jpashop.domain;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//상속 관계로 할 시에는 추상클래스로 사용해야한다.
@Entity
//만약 싱글 전략으로 취하면, ITEM 테이블만 생성된다.
//JOINED로 해야 관련된 테이블ㄹ들이 생성됨
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class Item extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
