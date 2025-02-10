package com.pets.petcare.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private RecipePostEntity post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private RegistrationEntity user;

    @Column(nullable = false)
    private String commentText;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private boolean isDeleted = false; // Soft delete feature

}
