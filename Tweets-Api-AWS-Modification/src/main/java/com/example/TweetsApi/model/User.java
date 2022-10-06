package com.example.TweetsApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;
    @NotBlank(message = "UserId is required")
    @Column(unique = true)
    private String username;
    @NotBlank(message = "User Password is required")
    private String password;

    private String firstName;
    private String lastName;
    private String bio;
    private Integer follower_count = 0;
    private Integer following_count = 0;
    private Instant created;
    @ElementCollection
    private Set<String> following;
}