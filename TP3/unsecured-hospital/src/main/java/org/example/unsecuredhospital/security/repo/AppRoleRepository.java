package org.example.unsecuredhospital.security.repo;

import org.example.unsecuredhospital.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {

}
