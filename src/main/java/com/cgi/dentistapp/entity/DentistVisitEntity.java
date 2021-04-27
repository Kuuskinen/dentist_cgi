package com.cgi.dentistapp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dentist_visit")
public class DentistVisitEntity {

    //THIS WILL DETERMINE WHAT WILL GO WHERE

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //My code
    @Column(name = "NAME", length = 50, nullable = false)
    private String name;

    @Column(name = "DATE", nullable = false)
    private Date date;
    //TODO implementation

    public String getName() {
        return name;
    }

    //Ref: https://www.w3schools.com/java/java_encapsulation.asp
    //Didn't remember setter exactly
    public String setName(String newName) {
        return this.name = newName;
    }

    public Date getDate() {
        return date;
    }

    public Date setDate(Date date){
        return this.date = date;
    }
}
