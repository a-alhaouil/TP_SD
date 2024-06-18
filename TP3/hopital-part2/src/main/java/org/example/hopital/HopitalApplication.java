package org.example.hopital;

import org.example.hopital.entities.Patient;
import org.example.hopital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class HopitalApplication {
    @Autowired
    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(HopitalApplication.class, args);
    }
//    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return (args) -> {
            patientRepository.save(new Patient(null,"Abdessamad",new Date(),false,134));
            patientRepository.save(new Patient(null,"Mohamed",new Date(),false,344));
            patientRepository.save(new Patient(null,"Imane",new Date(),false,124));

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };




    }
}
