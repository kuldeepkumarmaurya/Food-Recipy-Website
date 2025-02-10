package com.pets.petcare.ServiceImpl;

import com.pets.petcare.DTO.CommentResponceDto;
import com.pets.petcare.Entity.CommentEntity;
import com.pets.petcare.Entity.RecipePostEntity;
import com.pets.petcare.Entity.RegistrationEntity;
import com.pets.petcare.ServiceI.CommentServiceI;
import com.pets.petcare.repository.CommentRepository;
import com.pets.petcare.repository.RecipePostRepository;
import com.pets.petcare.repository.RegistrationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentServiceI {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RecipePostRepository recipePostRepository;

    @Autowired
    private RegistrationRepository userRepository;

    // Method to add a comment to a post
    public CommentEntity addComment(Long postId, Long userId, String commentText) {
        Optional<RecipePostEntity> postOptional = recipePostRepository.findById(postId);
        Optional<RegistrationEntity> userOptional = userRepository.findById(userId);

        if (postOptional.isPresent() && userOptional.isPresent()) {
            CommentEntity comment = new CommentEntity();
            comment.setPost(postOptional.get());
            comment.setUser(userOptional.get());
            comment.setCommentText(commentText);
            comment.setCreatedAt(LocalDateTime.now().truncatedTo(java.time.temporal.ChronoUnit.MILLIS));

            // Now save the comment to the database
            return commentRepository.save(comment);
        } else {
            throw new RuntimeException("Post or User not found");
        }
    }

    // Method to retrieve comments for a post
    public List<CommentResponceDto> getCommentsByPostId(Long postId) {
        List<CommentEntity> allComments = commentRepository.findByPostIdAndIsDeletedFalse(postId);
        List<CommentResponceDto> responseDtoList = new ArrayList<>();

        for (CommentEntity comment : allComments) {
            CommentResponceDto responseDto = new CommentResponceDto();
            responseDto.setCommentText(comment.getCommentText());
            responseDto.setTimeAgo(timeAgo(comment.getCreatedAt().toString()));
            responseDto.setUserName(comment.getUser().getName());
            responseDtoList.add(responseDto);
        }
        return responseDtoList;
    }

    // Soft delete a comment
    public void deleteComment(Long commentId) {
        Optional<CommentEntity> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            CommentEntity comment = commentOptional.get();
            comment.setDeleted(true);  // Mark the comment as deleted
            commentRepository.save(comment);
        } else {
            throw new RuntimeException("Comment not found");
        }
    }

    // Method to calculate time ago for comments
    public static String timeAgo(String timestampFromDB) {
        // Parse the timestamp from the database to LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;  // Using default ISO format
        LocalDateTime commentTime = LocalDateTime.parse(timestampFromDB, formatter);

        // Get the current time
        LocalDateTime currentTime = LocalDateTime.now();

        // Calculate the difference between the current time and the comment time
        Duration duration = Duration.between(commentTime, currentTime);

        // Get the time difference in various units
        long seconds = duration.getSeconds();
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        long months = days / 30;  // Approximation
        long years = days / 365;  // Approximation

        // Determine the appropriate time unit to display
        if (seconds < 60) {
            return seconds + " seconds ago";
        } else if (minutes < 60) {
            return minutes + " minutes ago";
        } else if (hours < 24) {
            return hours + " hours ago";
        } else if (days < 30) {
            return days + " days ago";
        } else if (months < 12) {
            return months + " months ago";
        } else {
            return years + " years ago";
        }
    }
}

