package com.springboot.patientdemo.service;

import com.springboot.patientdemo.dto.request.PatientRequest;
import com.springboot.patientdemo.dto.response.PatientResponse;
import com.springboot.patientdemo.entity.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService {
    List<PatientResponse> findAll();
    PatientResponse findById(int id);
    PatientResponse createPatient(PatientRequest patientRequest);
    PatientResponse updatePatientById(int id, PatientRequest patientRequest);
    void deleteById(int id);
}
