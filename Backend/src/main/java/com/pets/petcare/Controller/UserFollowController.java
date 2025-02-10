package com.pets.petcare.Controller;

import com.pets.petcare.DTO.GetFollowingListDto;
import com.pets.petcare.ServiceImpl.UserFollowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/follow")
public class UserFollowController {

    @Autowired
    private UserFollowServiceImpl userFollowService;

    @PostMapping("/toggle")
    public ResponseEntity<String> followOrUnfollowUser(@RequestParam Long followerId, @RequestParam Long followingId) {
        String response = userFollowService.followOrUnfollowUser(followerId, followingId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/following/{userId}")
    public ResponseEntity<List<GetFollowingListDto>> getFollowing(@PathVariable Long userId) {
        List<GetFollowingListDto> followingIds = userFollowService.getFollowingIds(userId);
        return ResponseEntity.ok(followingIds);
    }

    @GetMapping("/check/follow")
    public ResponseEntity<Boolean> isUserFollowing(@RequestParam Long followerId, @RequestParam Long followingId) {
        boolean isFollowing = userFollowService.isUserFollowing(followerId, followingId);
        return ResponseEntity.ok(isFollowing);
    }}

