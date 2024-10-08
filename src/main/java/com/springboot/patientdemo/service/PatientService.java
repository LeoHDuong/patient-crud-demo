package com.springboot.patientdemo.service;

import com.springboot.dto.PatientRequest;
import com.springboot.dto.PatientResponse;

import java.util.List;

public interface PatientService {
    List<PatientResponse> findAll(int page, int limit);
    PatientResponse findById(String id);
    PatientResponse createPatient(PatientRequest patientRequest);
    PatientResponse updatePatientById(String id, PatientRequest patientRequest);
    void deleteById(String id);
}
