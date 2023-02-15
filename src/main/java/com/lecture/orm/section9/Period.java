package com.lecture.orm.section9;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class Period {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Period() {
    }
}
