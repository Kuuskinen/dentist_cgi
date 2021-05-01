package com.cgi.dentistapp.entity;

import org.springframework.beans.factory.annotation.Autowired;

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

    /*@Column(name = "DENTIST_ID", nullable = false)
    private String name;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dentist_id")
    private DentistEntity dentistEntity;

    public String getName() {
        return dentistEntity.getName();
    }

    public Date getDate() {
        return date;
    }

    public Date setDate(Date date){
        return this.date = date;
    }

    public Long getId() {
        return id;
    }

    public DentistEntity getDentistEntity() {
        return dentistEntity;
    }

    public void setDentistEntity(DentistEntity dentistEntity) {
        this.dentistEntity = dentistEntity;
    }
}
