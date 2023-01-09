package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
@SequenceGenerator(name="member_seq_generator",
sequenceName = "member_seq")
//@Table(name="USER")
public class Member {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "member_seq_generator")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY :: mysql 의 경우 id long(int) 형이여야함
    private Long id;

    @Column(name = "name",nullable = false)
    private String username;

    public Member() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
