package com.cgi.dentistapp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dentist_visit")
public class DentistVisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DATE", nullable = false)
    private Date date;

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "dentist_id")
    private DentistEntity dentistEntity;

    public String getName() {
        return dentistEntity.getName();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(long newId) {
        this.id = newId;
    }

    public DentistEntity getDentistEntity() {
        return dentistEntity;
    }

    public void setDentistEntity(DentistEntity dentistEntity) {
        this.dentistEntity = dentistEntity;
    }

    public Long getDentistId() {
        return dentistEntity.getId();
    }

    public void setDentistID(long newId) {
        this.dentistEntity.setId(newId);
    }
}
