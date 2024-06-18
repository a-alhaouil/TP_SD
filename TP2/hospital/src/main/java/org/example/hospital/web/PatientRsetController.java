package org.example.hospital.web;

import org.example.hospital.entities.Patient;
import org.example.hospital.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientRsetController {
    @Autowired
    private PatientRepository PatientRepository;
    @GetMapping("/patients")
    public List<Patient> patientList(){
        return PatientRepository.findAll();
    }
}
