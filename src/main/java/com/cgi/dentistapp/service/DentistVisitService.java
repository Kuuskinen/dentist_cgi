package com.cgi.dentistapp.service;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.entity.DentistEntity;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DentistVisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private DentistService dentistService;

    @Transactional
    public void addVisit(Long dentistId, LocalDateTime visitTime) {
        DentistVisitEntity dentistVisitEntity = new DentistVisitEntity();
        dentistVisitEntity.setDentistEntity(new DentistEntity());
        dentistVisitEntity.getDentistEntity().setId(dentistId);

        Date convertedToDate = convertToDateViaInstant(visitTime);
        dentistVisitEntity.setDate(convertedToDate);

        visitRepository.save(dentistVisitEntity);
    }

    public List<DentistVisitEntity> getAllVisits() {
        return visitRepository.findAll();
    }

    public void deleteVisit(String id) {
        long idAsLong = Long.valueOf(id);
        visitRepository.delete(idAsLong);
    }

    public DentistVisitEntity visitToChange(String id) {
        long idAsLong = Long.valueOf(id);
        return visitRepository.findOne(idAsLong);
    }

    public void changeVisit(Long visitId, DentistVisitDTO dentistVisitDTO) {
        DentistVisitEntity dentistVisitEntity = visitRepository.findOne(visitId);

        LocalDateTime localDateTime = LocalDateTime.of(dentistVisitDTO.getVisitTime(),dentistVisitDTO.getVisitClock());

        Date convertedToDate = convertToDateViaInstant(localDateTime);
        dentistVisitEntity.setDate(convertedToDate);

        DentistEntity newDentist = dentistService.getDentist(dentistVisitDTO.getDentistId());
        
        dentistVisitEntity.setDentistEntity(newDentist);
        visitRepository.save(dentistVisitEntity);
    }

    //REF: https://www.baeldung.com/java-date-to-localdate-and-localdatetime
    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    //REF: https://www.baeldung.com/java-date-to-localdate-and-localdatetime
    Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }
}
