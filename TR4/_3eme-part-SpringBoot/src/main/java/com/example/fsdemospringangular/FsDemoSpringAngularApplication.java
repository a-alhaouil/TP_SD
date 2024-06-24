package com.example.fsdemospringangular;

import com.example.fsdemospringangular.entities.Payment;
import com.example.fsdemospringangular.entities.PaymentStatus;
import com.example.fsdemospringangular.entities.PaymentType;
import com.example.fsdemospringangular.entities.Student;
import com.example.fsdemospringangular.repository.PaymentRepository;
import com.example.fsdemospringangular.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class FsDemoSpringAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(FsDemoSpringAngularApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository, PaymentRepository paymentRepository){
		return args -> {
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
						  .firstName("Abdessamad").code("112233").programId("IAAD")
				  .build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Little_Dream").code("112244").programId("IAAD")
					.build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Mohamed").code("112255").programId("IAADM2")
					.build());
			studentRepository.save(Student.builder().id(UUID.randomUUID().toString())
					.firstName("Yasmina").code("112266").programId("IAAD")
					.build());
			PaymentType[] paymentTypes=PaymentType.values();
			Random random =new Random();
			studentRepository.findAll().forEach(st->{
				for (int i = 0; i <10 ; i++) {
					int index =random.nextInt(paymentTypes.length);
					Payment payment=Payment.builder()
							.amount(1000+(int)(Math.random()*20000))
							.type(paymentTypes[index])
							.status(PaymentStatus.CREATED)
							.date(LocalDate.now())
							.student(st)
							.build();
					paymentRepository.save(payment);
				}
			});
		};
	}

}
