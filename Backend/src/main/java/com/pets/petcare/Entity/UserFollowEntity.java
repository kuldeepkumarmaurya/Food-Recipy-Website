package com.pets.petcare.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_follows")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFollowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long followerId; // ID of the user who follows
    private Long followingId; // ID of the user being followed

}
