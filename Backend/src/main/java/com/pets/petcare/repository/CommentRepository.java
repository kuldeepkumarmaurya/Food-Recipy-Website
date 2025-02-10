package com.pets.petcare.repository;


import com.pets.petcare.Entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByPostIdAndIsDeletedFalse(Long postId);
}
