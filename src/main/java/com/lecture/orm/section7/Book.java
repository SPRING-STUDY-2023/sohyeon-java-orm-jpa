package com.lecture.orm.section7;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 조인 전략
@DiscriminatorColumn(name = "WantedName") // DTYPE 칼럼 추가 (default 데이터: Entity 명)
public class Book extends Item {

    private String author;
    private String isbn;
}
