package com.example.fsdemospringangular.services;

import com.example.fsdemospringangular.entities.Payment;
import com.example.fsdemospringangular.entities.PaymentStatus;
import com.example.fsdemospringangular.entities.PaymentType;
import com.example.fsdemospringangular.entities.Student;
import com.example.fsdemospringangular.repository.PaymentRepository;
import com.example.fsdemospringangular.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
@Transactional
public class PaymentService {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;


    public PaymentService(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }
    public Payment savePayment( MultipartFile file, LocalDate date, double amount ,
                               PaymentType type
            , String studentcode) throws IOException {
        Path folderPath = Paths.get(System.getProperty("user.home"),"data","payments");
        if (!Files.exists(folderPath)){
            Files.createDirectories(folderPath);
        }
        String fileName= UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"),"data","payments",fileName+".pdf");
        Files.copy(file.getInputStream(),filePath);
        Student student=studentRepository.findByCode(studentcode);
        Payment payment=Payment.builder()
                .date(date).type(type).student(student)
                .amount(amount)
                .file(filePath.toUri().toString())
                .status(PaymentStatus.CREATED)
                .build();
        return paymentRepository.save(payment);
    }
    public Payment updatePaymentStatus( PaymentStatus status,  Long id){
        Payment payment=paymentRepository.findById(id).get();
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }
    public byte[] getPaymentFile ( Long paymentId) throws IOException {
        Payment payment=paymentRepository.findById(paymentId).get();
        return Files.readAllBytes(Path.of(URI.create(payment.getFile())));
    }
}
