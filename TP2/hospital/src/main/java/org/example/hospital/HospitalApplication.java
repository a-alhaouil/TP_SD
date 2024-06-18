package org.example.hospital;

import org.example.hospital.entities.*;
import org.example.hospital.repositories.ConsultationRepository;
import org.example.hospital.repositories.MedecinRepository;
import org.example.hospital.repositories.PatientRepository;
import org.example.hospital.repositories.RendezVousRepository;
import org.example.hospital.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}
	@Bean
	CommandLineRunner start(
			IHospitalService hospitalService,
			PatientRepository patientRepository,
			ConsultationRepository consultationRepository,
			RendezVousRepository rendezVousRepository,
			MedecinRepository medecinRepository) {
		return args -> {
			Stream.of("Abdessamad", "Hassan", "Najat")
					.forEach(name -> {
						Patient patient = new Patient();
						patient.setNom(name);
						patient.setDateNaissance(new Date());
						patient.setMalade(false);
						hospitalService.savePatient(patient);
					});
			Stream.of("Abdessamad", "Hanan", "Yasmine")
					.forEach(name -> {
						Medecin medcin = new Medecin();
						medcin.setNom(name);
						medcin.setEmail(name+"@gmail.com");
						medcin.setSpecialite(Math.random()>0.5?"cardio":"dentiste");
						hospitalService.saveMedecin(medcin);
					});
			Patient patient=patientRepository.findById(1L).orElse(null);
			Patient patient1=patientRepository.findByNom("Abdessamad");
			Medecin medecin=medecinRepository.findByNom("Yasmine");
			RendezVous rendezVous=new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patient);
			RendezVous saveDRDV = hospitalService.saveRDV(rendezVous);
			System.out.println(saveDRDV.getId());

			RendezVous rendezVous1=rendezVousRepository.findAll().get(0);
			Consultation consultation=new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRendezVous(rendezVous1);
			consultation.setRapport("Rapport de consultaion ....");
			hospitalService.saveConsultation(consultation);

		};
	}
}
