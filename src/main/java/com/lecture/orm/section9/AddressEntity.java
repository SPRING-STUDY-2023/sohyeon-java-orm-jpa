package com.lecture.orm.section9;

import jakarta.persistence.*;

@Entity
@Table(name = "ADDRESS")
public class AddressEntity {

    @Id @GeneratedValue
    Long id;

    private Address address;

    public AddressEntity() {
    }
}
