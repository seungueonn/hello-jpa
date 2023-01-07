package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name="USER")
public class Member {

    @Id
    private Long id;
    @Column(unique = true,length = 10)
    private String name;
    private int age;

    public Member() {
        //jpa 객체는 기본 생성자 필요
    }
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
