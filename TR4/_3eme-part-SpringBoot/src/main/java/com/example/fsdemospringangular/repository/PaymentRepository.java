package com.example.fsdemospringangular.repository;

import com.example.fsdemospringangular.entities.Payment;
import com.example.fsdemospringangular.entities.PaymentStatus;
import com.example.fsdemospringangular.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
    List<Payment> findByStudentCode(String code);
    List <Payment> findByStatus(PaymentStatus status);
    List<Payment> findByType(PaymentType type);

}
