package com.cgi.dentistapp.service;

import com.cgi.dentistapp.entity.DentistVisitEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends CrudRepository<DentistVisitEntity, Long> {
}

