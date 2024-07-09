package org.example.accountservice.web;

import org.example.accountservice.entities.BankAccount;
import org.example.accountservice.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRestController {
    private BankAccountRepository accountRepository;
    public AccountRestController(BankAccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @GetMapping("/accounts")
    public List<BankAccount> accountList() {
        return accountRepository.findAll();
    }
    @GetMapping("/accounts/{id}")
    public BankAccount bankAccountById(String id) {
        return accountRepository.findById(id).get();
    }
}


