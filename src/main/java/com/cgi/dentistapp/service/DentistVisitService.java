package com.cgi.dentistapp.service;

import com.cgi.dentistapp.entity.DentistVisitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DentistVisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Transactional
    public void addVisit(String dentistName, Date visitTime) {
        DentistVisitEntity dentistVisitEntity = new DentistVisitEntity();
        System.out.println("SAVING STUFF");
        dentistVisitEntity.setName(dentistName);
        dentistVisitEntity.setDate(visitTime);
        visitRepository.save(dentistVisitEntity);
        List<DentistVisitEntity> yo = (List<DentistVisitEntity>) visitRepository.findAll();
        System.out.println("I WILL NOW SPILL MY SECRETS");
        System.out.println(dentistVisitEntity.getName());
        System.out.println(dentistVisitEntity.getDate());
        //TODO implementation
    }
}
