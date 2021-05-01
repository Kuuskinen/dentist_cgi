package com.cgi.dentistapp.service;

import com.cgi.dentistapp.entity.DentistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DentistService {

    @Autowired
    DentistRepository dentistRepository;

    public List<String> getAllDentistsAsStrings() {
        List<DentistEntity> dentistEntities = dentistRepository.findAll();
        List<String> dentistNames = new ArrayList<>();
        for (DentistEntity dentistEntity : dentistEntities) {
            dentistNames.add(dentistEntity.getName());
        }
        return dentistNames;
    }

    public List<DentistEntity> getAllDentists() {
        return dentistRepository.findAll();
    }

}
