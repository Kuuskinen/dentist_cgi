package com.cgi.dentistapp.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class DentistVisitDTO {

    String dentistName;

    String visitId;

    @NotNull
    Long dentistId;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    Date visitTime;

    public DentistVisitDTO() {

    }

    public DentistVisitDTO(Long dentistId, Date visitTime) {
        this.dentistId = dentistId;
        this.visitTime = visitTime;
    }

    public Long getDentistId() {
        return dentistId;
    }

    public void setDentistId(long dentistId) {
        this.dentistId = dentistId;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }
}
