package com.pets.petcare.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipePostLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reciprPostLikeId;

    private Long postId;

    // Other fields...

    @Column(nullable = false)
    private int likeCount = 0; // Track like count

    public Long getReciprPostLikeId() {
        return reciprPostLikeId;
    }

    public void setReciprPostLikeId(Long reciprPostLikeId) {
        this.reciprPostLikeId = reciprPostLikeId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }


    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}

