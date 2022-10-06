package com.example.TweetsApi.repository;

import com.example.TweetsApi.model.Tweet;
import com.example.TweetsApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findByUserIn(List<User> userList);

    List<Tweet> findByUser(User user);
}
