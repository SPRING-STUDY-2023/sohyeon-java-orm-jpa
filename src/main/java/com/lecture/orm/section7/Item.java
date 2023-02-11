package com.lecture.orm.section7;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Item extends BaseEntity { // 공통 Entity 상속

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
