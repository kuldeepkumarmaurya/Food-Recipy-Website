package com.pets.petcare.ServiceImpl;

import com.pets.petcare.DTO.LikeDTO;
import com.pets.petcare.Entity.LikeEntity;
import com.pets.petcare.Entity.RecipePostEntity;
import com.pets.petcare.Entity.RecipePostLikeEntity;
import com.pets.petcare.ServiceI.LikeServiceI;
import com.pets.petcare.repository.LikeRepository;
import com.pets.petcare.repository.RecipePostLikeRepository;
import com.pets.petcare.repository.RecipePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeServiceI {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private RecipePostLikeRepository recipePostLikeRepository;

    @Override
    public LikeDTO toggleLike(Long userId, Long postId) {
        // Check if the like already exists
        Optional<LikeEntity> likeEntityOptional = likeRepository.findByUserIdAndPostId(userId, postId);

        if (likeEntityOptional.isPresent()) {
            // If it exists, decrement the like count and delete the like entry
            LikeEntity likeEntity = likeEntityOptional.get();
            likeRepository.delete(likeEntity);

            // Update the like count in RecipePostEntity
            Optional<RecipePostLikeEntity> postLikeEntity = recipePostLikeRepository.findByPostId(postId);
            if (postLikeEntity.isPresent()){
                postLikeEntity.get().setLikeCount(postLikeEntity.get().getLikeCount() - 1);
                recipePostLikeRepository.save(postLikeEntity.get());
            }

            int likecont = postLikeEntity.get().getLikeCount();
            LikeDTO responc = new LikeDTO();
            responc.setLikeCount(String.valueOf(likecont));
            responc.setUserHasLiked(false);
            return responc;
        } else {
            // If it doesn't exist, create a new like entry
            LikeEntity newLike = LikeEntity.builder()
                    .userId(userId)
                    .postId(postId)
                    .build();
            likeRepository.save(newLike);

            // Update the like count in RecipePostEntity

            Optional<RecipePostLikeEntity> postLikeEntity = recipePostLikeRepository.findByPostId(postId);
            if (postLikeEntity.isPresent()){
                postLikeEntity.get().setLikeCount(postLikeEntity.get().getLikeCount() + 1);
                recipePostLikeRepository.save(postLikeEntity.get());
                int likecont = postLikeEntity.get().getLikeCount();
                LikeDTO responc = new LikeDTO();
                responc.setLikeCount(String.valueOf(likecont));
                responc.setUserHasLiked(true);
                return responc;
            }else {
                RecipePostLikeEntity newPostLikeEntity  = new RecipePostLikeEntity();
                newPostLikeEntity.setPostId(postId);
                newPostLikeEntity.setLikeCount(+1);
                recipePostLikeRepository.save(newPostLikeEntity);
                int likecont = newPostLikeEntity.getLikeCount();
                LikeDTO responc = new LikeDTO();
                responc.setLikeCount(String.valueOf(likecont));
                responc.setUserHasLiked(true);
                return responc;
            }
        }
    }

    @Override
    public String getlikecount(Long postId) {
        Optional<RecipePostLikeEntity> postLikeEntity = recipePostLikeRepository.findByPostId(postId);
        if(!postLikeEntity.isPresent()){
            return "0";
        }
        return String.valueOf(postLikeEntity.get().getLikeCount());
    }
}

