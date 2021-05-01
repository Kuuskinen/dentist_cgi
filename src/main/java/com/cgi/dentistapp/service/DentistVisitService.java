package com.cgi.dentistapp.service;

import com.cgi.dentistapp.entity.DentistEntity;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DentistVisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Transactional
    public void addVisit(Long dentistId, Date visitTime) {
        DentistVisitEntity dentistVisitEntity = new DentistVisitEntity();
        dentistVisitEntity.setDentistEntity(new DentistEntity());
        dentistVisitEntity.getDentistEntity().setId(dentistId);
        dentistVisitEntity.setDate(visitTime);
        visitRepository.save(dentistVisitEntity);
    }

    public List<DentistVisitEntity> getAllVisits() {
        return visitRepository.findAll();
    }

    public void deleteVisit(String id) {
        System.out.println("RECEIVED");
        System.out.println(id);
        long idAsLong = Long.valueOf(id);
        visitRepository.delete(idAsLong);
    }
}
