package com.cgi.dentistapp.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class DentistVisitDTO {

    String visitId;

    @NotNull
    Long dentistId;

    @NotNull
    @DateTimeFormat(pattern="yyyy-MM-dd")
    LocalDate visitTime;

    @NotNull
    LocalTime visitClock;

    public DentistVisitDTO(Long dentistId, LocalDate visitTime, LocalTime visitClock) {
        this.dentistId = dentistId;
        this.visitTime = visitTime;
        this.visitClock = visitClock;
    }

    public LocalTime getVisitClock() {
        return visitClock;
    }

    public void setVisitClock(LocalTime visitClock) {
        this.visitClock = visitClock;
    }

    public DentistVisitDTO() {

    }

    public DentistVisitDTO(Long dentistId, LocalDate visitTime) {
        this.dentistId = dentistId;
        this.visitTime = visitTime;
    }

    public Long getDentistId() {
        return dentistId;
    }

    public void setDentistId(long dentistId) {
        this.dentistId = dentistId;
    }

    public LocalDate getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDate visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }
}
