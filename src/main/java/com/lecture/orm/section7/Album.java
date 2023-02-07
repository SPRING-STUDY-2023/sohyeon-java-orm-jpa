package com.lecture.orm.section7;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 구현 테이블마다 테이블 전략
public class Album extends Item {

    private String artist;
}
