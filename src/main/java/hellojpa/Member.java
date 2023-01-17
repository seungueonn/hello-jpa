package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

@Entity
@Table(name = "member")
public class Member  {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    //Period
    @Embedded
    private Period period;
    // address
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name="city",column = @Column(name="HOME_CITY")),
            @AttributeOverride( name="street",column = @Column(name="HOME_STREET")),
            @AttributeOverride( name="zipcode",column = @Column(name="HOME_ZIPCODE"))})
    private Address homeAddress;
    private Address workAddress;

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

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }


}
