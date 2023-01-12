package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

@Entity
@Table(name = "_Member")
public class Member {

    @Id @GeneratedValue
    private Long id;

    @Column(name = "USERNAME")
    private String name;

//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID",insertable = false, updatable = false) // 야매
//    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT") // 다대다
    @OneToMany(mappedBy = "member")
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


//    public void changeTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this); // 연관관계 편의 메소드
//    }
}
