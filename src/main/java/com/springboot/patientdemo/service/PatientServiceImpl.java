package com.springboot.patientdemo.service;

import com.springboot.patientdemo.dao.PatientRepository;
import com.springboot.patientdemo.entity.Patient;
import com.springboot.patientdemo.exception.UserNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findById(int id) {
        Optional<Patient> result = patientRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        else {
            throw new UserNotFound("Patient not found with id: " + id);
        }
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
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
