package com.springboot.patientdemo.service;

import com.springboot.patientdemo.dao.PatientRepository;
import com.springboot.patientdemo.dto.request.PatientRequest;
import com.springboot.patientdemo.dto.response.PatientResponse;
import com.springboot.patientdemo.entity.Patient;
import com.springboot.patientdemo.exception.UserNotFound;
import com.springboot.patientdemo.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper2;

    @Override
    public List<PatientResponse> findAll() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(patientMapper2::toPatientResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponse findById(int id) {
        Optional<Patient> result = patientRepository.findById(id);
        if (result.isPresent()) {
            System.out.println(result.get().toString());
            System.out.println(patientMapper2.toPatientResponse(result.get()).toString());
            return patientMapper2.toPatientResponse(result.get());
        }
        else {
            throw new UserNotFound("Patient not found with id: " + id);
        }
    }

    @Override
    public PatientResponse createPatient(PatientRequest patientRequest) {
        return patientMapper2.toPatientResponse(patientRepository.save(patientMapper2.toPatient(patientRequest)));
    }

    public PatientResponse updatePatientById(int id, PatientRequest patientRequest) {

        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isPresent()) {
            patientMapper2.updatePatient(patient.get(), patientRequest);
            return patientMapper2.toPatientResponse(patientRepository.save(patient.get()));
        }

        else {
            throw new UserNotFound("Patient not found with id: " + id);
        }

    }

    @Override
    public void deleteById(int id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
        }
        else {
            throw new UserNotFound("Patient not found with id: " + id);
        }
    }
}
