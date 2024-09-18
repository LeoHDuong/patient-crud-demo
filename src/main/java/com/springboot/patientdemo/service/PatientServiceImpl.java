package com.springboot.patientdemo.service;

import com.springboot.patientdemo.dao.PatientRepository;
import com.springboot.patientdemo.dto.request.PatientRequest;
import com.springboot.patientdemo.dto.response.PatientResponse;
import com.springboot.patientdemo.entity.Patient;
import com.springboot.patientdemo.exception.UserNotFound;
import com.springboot.patientdemo.mapper.PatientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientResponse> findAll() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(PatientMapper::toPatientResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponse findById(int id) {
        Optional<Patient> result = patientRepository.findById(id);
        if (result.isPresent()) {
            return PatientMapper.toPatientResponse(result.get());
        }
        else {
            throw new UserNotFound("Patient not found with id: " + id);
        }
    }

    @Override
    public PatientResponse createPatient(PatientRequest patientRequest) {
        return PatientMapper.toPatientResponse(patientRepository.save(PatientMapper.toPatient(patientRequest)));
    }

    public PatientResponse updatePatientById(int id, PatientRequest patientRequest) {

        Optional<Patient> existingPatient = patientRepository.findById(id);

        if (existingPatient.isPresent()) {
            Patient patient = PatientMapper.toPatient(patientRequest);
            existingPatient.get().setFirstName(patient.getFirstName());
            existingPatient.get().setLastName(patient.getLastName());
            existingPatient.get().setGender(patient.getGender());
            existingPatient.get().setAge(patient.getAge());
            existingPatient.get().setEmail(patient.getEmail());
            existingPatient.get().setPhoneNumber(patient.getPhoneNumber());
            return PatientMapper.toPatientResponse(patientRepository.save(existingPatient.get()));
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
