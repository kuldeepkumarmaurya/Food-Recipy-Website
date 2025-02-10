package com.pets.petcare.repository;

import com.pets.petcare.Entity.UserFollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFollowRepository extends JpaRepository<UserFollowEntity, Long> {
    boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId);
    void deleteByFollowerIdAndFollowingId(Long followerId, Long followingId);
    List<UserFollowEntity> findByFollowerId(Long followerId);

    UserFollowEntity findByFollowerIdAndFollowingId(Long followerId, Long followingId);


    List<UserFollowEntity> findAllByFollowingId(Long id);

    List<UserFollowEntity> findAllByFollowerId(Long id);
}
