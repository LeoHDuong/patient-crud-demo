package com.springboot.patientdemo.web;

import com.openapi.gen.springboot.api.PatientsApiDelegate;
import com.openapi.gen.springboot.dto.PatientRequest;
import com.openapi.gen.springboot.dto.PatientResponse;
import com.springboot.patientdemo.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PatientsApiDelegateImpl implements PatientsApiDelegate {

    private final PatientService patientService;

    @Override
    public ResponseEntity<PatientResponse> createPatient(PatientRequest patientRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(patientRequest));
    }

    @Override
    public ResponseEntity<Void> deletePatient(Integer patientId) {
        patientService.deleteById(patientId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<PatientResponse>> listPatients(Integer limit) {
        if (limit == null) {
            limit = 10;
        }
        List<PatientResponse> patients = patientService.findAll(limit);
        return ResponseEntity.ok(patients);
    }

    @Override
    public ResponseEntity<PatientResponse> showPatientById(Integer patientId) {
        return ResponseEntity.ok(patientService.findById(patientId));
    }

    @Override
    public ResponseEntity<PatientResponse> updatePatient(Integer patientId, PatientRequest patientRequest) {
        return ResponseEntity.ok(patientService.updatePatientById(patientId, patientRequest));
    }
}
