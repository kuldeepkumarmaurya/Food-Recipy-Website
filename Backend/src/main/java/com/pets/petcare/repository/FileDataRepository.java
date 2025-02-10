package com.pets.petcare.repository;

import com.pets.petcare.Entity.FileDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileDataEntity , Long> {

    Optional<FileDataEntity> findByName(String Name);
}
