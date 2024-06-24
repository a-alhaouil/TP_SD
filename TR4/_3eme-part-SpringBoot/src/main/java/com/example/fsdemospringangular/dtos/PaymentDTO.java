package com.example.fsdemospringangular.dtos;

import com.example.fsdemospringangular.entities.PaymentStatus;
import com.example.fsdemospringangular.entities.PaymentType;
import com.example.fsdemospringangular.entities.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString @Builder
public class PaymentDTO {
    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status;
}
