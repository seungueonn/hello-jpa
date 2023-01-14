package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//@Entity
//@DiscriminatorValue("A") //DTYPE 에 명시되는 이름 변경
public class Album extends Item {

    private String artist;
}
