package com.example.TweetsApi.controller;

import com.example.TweetsApi.dto.TweetsRequest;
import com.example.TweetsApi.dto.TweetsResponse;
import com.example.TweetsApi.service.TweetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/tweetsapi/tweets")
@AllArgsConstructor
public class TweetsController {

    private final TweetService tweetService;

    @PostMapping
    ResponseEntity<Void> createTweet(@RequestBody TweetsRequest tweetsRequest) {
        tweetService.save(tweetsRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TweetsResponse>> getFeed() {
        return status(HttpStatus.OK).body(tweetService.getFeed());
    }

    @GetMapping("tweetid/{id}")
    public ResponseEntity<TweetsResponse> getTweet(@PathVariable Long id) {
        return status(HttpStatus.OK).body(tweetService.getTweet(id));
    }

    @GetMapping("user/{username}")
    public ResponseEntity<List<TweetsResponse>> getTweetsByUsername(@PathVariable String username) {
        return status(HttpStatus.OK).body(tweetService.getTweetsByUsername(username));
    }
}
