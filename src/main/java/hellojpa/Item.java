package hellojpa;

import javax.persistence.*;


/*
  Album, Item, Movie가 Item을 상속 받는 방식은 단일 테이블 전략이다.
  SINGLE_TABLE 단일 테이블 전략
    장점 : 조인이 필요 없으므로 일반적으로 조회 성능이 빠름, 조회 쿼리가 단순함

    단점 : 자식 엔티티가 매핑한 컬럼은 모두 null 허용, 단일 테이블에 모든 것을 저장하므로 테이블이 커질수 있으며,
    상황에 따라서 조회성능이 오히려 느려질 수 있다.


  JOINED 조인 전략
    장점 : 테이블의 정규화, 외래키 참조 무결성 제약조건 활용가능, 저장공간 효율화
    단점 : 조회시 조인을 많이 사용, 성능저하, 조회쿼리 복잡함, 데이터 저장시 insert sql 2번 호출


  TABLE_PER_CLASS -> ITEM 테이블은 만들지 않고, 각 하위클래스에서 ITEM의 NAME, PRICE를 갖게한다.
  but 이 전략은 insert할떄는 편리하지만 select할때는 매우 지저분하다.
  이 방법은 추천하지않는다.

    관계형 데이터베이스는 상속 관계 x

    슈퍼타입 서브타입 관계라는 모델링 기법이 객체 상속과 유사

    상속관계 매핑 : 객체의 상속과 구조와 db의 슈퍼타입 서프타입 관계를 매핑
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn //DTYPE이 추가됨 DEFAULT로 ENTTIY명이 들어감
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

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
}
