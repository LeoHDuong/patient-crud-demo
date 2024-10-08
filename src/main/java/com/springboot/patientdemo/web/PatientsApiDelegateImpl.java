package com.springboot.patientdemo.web;

import com.springboot.api.PatientsApiDelegate;
import com.springboot.dto.PatientRequest;
import com.springboot.dto.PatientResponse;
import com.springboot.patientdemo.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RequiredArgsConstructor
@Component
@CrossOrigin(origins = "http://localhost:3000")
public class PatientsApiDelegateImpl implements PatientsApiDelegate {

    private final PatientService patientService;

    @Override
    public ResponseEntity<PatientResponse> createPatient(PatientRequest patientRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(patientRequest));
    }

    @Override
    public ResponseEntity<Void> deletePatient(String patientId) {
        patientService.deleteById(patientId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<PatientResponse>> listPatients(Integer page, Integer limit) {
        List<PatientResponse> patients = patientService.findAll(page, limit);
        return ResponseEntity.ok(patients);
    }

    @Override
    public ResponseEntity<PatientResponse> showPatientById(String patientId) {
        return ResponseEntity.ok(patientService.findById(patientId));
    }

    @Override
    public ResponseEntity<PatientResponse> updatePatient(String patientId, PatientRequest patientRequest) {
        return ResponseEntity.ok(patientService.updatePatientById(patientId, patientRequest));
    }
}
