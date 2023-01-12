package jpabook.jpashop.domain;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id
    @GeneratedValue
    private Long id;

    private String city;
    private String street;
    private String zipcode;
    private DeliverStatus status;

    @OneToOne(mappedBy = "delivery" )
    private Order order;
}

