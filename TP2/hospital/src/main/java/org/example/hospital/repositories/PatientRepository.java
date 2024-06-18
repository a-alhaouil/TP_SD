package org.example.hospital.repositories;

import org.example.hospital.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends JpaRepository<Patient, Long>{
    Patient findByNom (String nom);
}
