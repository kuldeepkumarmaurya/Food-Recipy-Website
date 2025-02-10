package com.pets.petcare.ServiceImpl;

import com.pets.petcare.DTO.GetFollowingListDto;
import com.pets.petcare.Entity.RegistrationEntity;
import com.pets.petcare.Entity.UserFollowEntity;
import com.pets.petcare.repository.RegistrationRepository;
import com.pets.petcare.repository.UserFollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserFollowServiceImpl {

    @Autowired
    private UserFollowRepository userFollowRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    public String followOrUnfollowUser(Long followerId, Long followingId) {
        if (userFollowRepository.existsByFollowerIdAndFollowingId(followerId, followingId)) {
            UserFollowEntity followEntity = userFollowRepository.findByFollowerIdAndFollowingId(followerId, followingId);
            userFollowRepository.delete(followEntity);
            return "Unfollowed successfully.";
        } else {
            UserFollowEntity follow = new UserFollowEntity();
            follow.setFollowerId(followerId);
            follow.setFollowingId(followingId);
            userFollowRepository.save(follow);
            return "Followed successfully.";
        }
    }

    public List<GetFollowingListDto> getFollowingIds(Long userId) {
        List<UserFollowEntity> follows = userFollowRepository.findByFollowerId(userId);


        List<GetFollowingListDto> followingListDtoList = new ArrayList<>();

        for (UserFollowEntity followEntity: follows ){
            Optional<RegistrationEntity> user = registrationRepository.findById(followEntity.getFollowingId());
            if (user.isPresent()){
                GetFollowingListDto responce = new GetFollowingListDto();
                responce.setUserId(user.get().getRegId());
                responce.setName(user.get().getName());
                responce.setProfilepic(user.get().getProfilepic());
                followingListDtoList.add(responce);
            }
        }

        return followingListDtoList;
    }

    public boolean isUserFollowing(Long followerId, Long followingId) {
        // Logic to check if followerId is following followingId
        // For example, using a method in your repository:
        return userFollowRepository.existsByFollowerIdAndFollowingId(followerId, followingId);
    }
}
