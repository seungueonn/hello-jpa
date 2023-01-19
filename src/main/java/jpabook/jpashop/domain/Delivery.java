package jpabook.jpashop.domain;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Address address;
    private DeliverStatus status;

    @OneToOne(mappedBy = "delivery" ,fetch = LAZY)
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DeliverStatus getStatus() {
        return status;
    }

    public void setStatus(DeliverStatus status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return Objects.equals(getId(), delivery.getId()) && Objects.equals(getAddress(), delivery.getAddress()) && getStatus() == delivery.getStatus() && Objects.equals(getOrder(), delivery.getOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAddress(), getStatus(), getOrder());
    }
}

