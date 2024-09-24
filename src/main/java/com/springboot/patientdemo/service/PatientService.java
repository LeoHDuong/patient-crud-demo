package com.springboot.patientdemo.service;

import com.openapi.gen.springboot.dto.PatientRequest;
import com.openapi.gen.springboot.dto.PatientResponse;
import com.springboot.patientdemo.entity.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {
    List<PatientResponse> findAll(int limit);
    PatientResponse findById(int id);
    PatientResponse createPatient(PatientRequest patientRequest);
    PatientResponse updatePatientById(int id, PatientRequest patientRequest);
    void deleteById(int id);
}
