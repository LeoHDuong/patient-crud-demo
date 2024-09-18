package com.springboot.patientdemo.service;

import com.springboot.patientdemo.entity.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {
    List<Patient> findAll();
    Patient findById(int id);
    Patient save(Patient patient);
    void deleteById(int id);
}
