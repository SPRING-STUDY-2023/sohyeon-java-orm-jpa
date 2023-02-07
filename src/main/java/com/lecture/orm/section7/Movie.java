package com.lecture.orm.section7;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 단일 테이블 전략
public class Movie extends Item {

    private String director;
    private String actor;
}
