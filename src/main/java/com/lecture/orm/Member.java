package com.lecture.orm;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    // name = "" : 테이블 칼럼명 지정
    // updatable = false : 테이블 변경 금지
    // nullable = false : NOT NULL
    // columnDefinition = "..." : 칼럼 정보 직접 지정
    @Column(name = "name", updatable = false, nullable = false)
    private String username;

    // BigDecimal : 큰 수 값
    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 최신 버전!
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob
    private String description;

    @Transient // 객체 내에서만 사용 (테이블 칼럼 X)
    private int temp;

    public Member() {
    }
}
