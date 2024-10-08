package com.springboot.patientdemo.service;

import com.springboot.dto.PatientRequest;
import com.springboot.dto.PatientResponse;
import com.springboot.patientdemo.entity.Patient;
import com.springboot.patientdemo.dao.PatientRepository;
import com.springboot.patientdemo.exception.UserNotFound;
import com.springboot.patientdemo.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMapper patientMapper;

    @Override
    public List<PatientResponse> findAll(int page, int limit) {
        List<Patient> patients = patientRepository.findAll(PageRequest.of(page, limit)).getContent();
        return patients.stream()
                .map(patientMapper::toPatientResponse)
                .collect(Collectors.toList());
    }


    @Override
    public PatientResponse findById(String id) {
        Optional<Patient> result = patientRepository.findById(id);
        if (result.isPresent()) {
            return patientMapper.toPatientResponse(result.get());
        }
        else {
            throw new UserNotFound("Patient not found with id: " + id);
        }
    }

    @Override
    public PatientResponse createPatient(PatientRequest patientRequest) {
        return patientMapper.toPatientResponse(patientRepository.save(patientMapper.toPatient(patientRequest)));
    }

    public PatientResponse updatePatientById(String id, PatientRequest patientRequest) {

        Optional<Patient> patient = patientRepository.findById(id);

        if (patient.isPresent()) {
            patientMapper.updatePatient(patient.get(), patientRequest);
            return patientMapper.toPatientResponse(patientRepository.save(patient.get()));
        }

        else {
            throw new UserNotFound("Patient not found with id: " + id);
        }

    }

    @Override
    public void deleteById(String id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
        }
        else {
            throw new UserNotFound("Patient not found with id: " + id);
        }
    }
}
