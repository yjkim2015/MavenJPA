package hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker {

    @Id @GeneratedValue
    private Long id;

    private String usernam;


    /**
     * 양방향 일대일
     */
    @OneToOne(mappedBy = "locker")
    private Member member;

}
