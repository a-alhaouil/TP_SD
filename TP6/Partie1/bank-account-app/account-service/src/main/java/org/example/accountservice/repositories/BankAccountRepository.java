package org.example.accountservice.repositories;

import org.example.accountservice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

}
