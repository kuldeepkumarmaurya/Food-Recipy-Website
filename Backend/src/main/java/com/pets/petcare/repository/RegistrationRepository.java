package com.pets.petcare.repository;

import com.pets.petcare.Entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<RegistrationEntity , Long> {
    Optional<RegistrationEntity> findByEmail(String email);

    Optional<RegistrationEntity> findByEmailAndVarifiedTrue(String email);
}
