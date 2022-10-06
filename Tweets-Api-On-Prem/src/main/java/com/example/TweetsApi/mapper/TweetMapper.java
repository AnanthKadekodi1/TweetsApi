package com.example.TweetsApi.mapper;

import com.example.TweetsApi.dto.TweetsRequest;
import com.example.TweetsApi.dto.TweetsResponse;
import com.example.TweetsApi.model.Tweet;
import com.example.TweetsApi.model.User;
import com.example.TweetsApi.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class TweetMapper {

    @Autowired
    private AuthService authService;

    @Mapping(target = "postTime", expression = "java(java.time.Instant.now())")
    @Mapping(target = "content", source = "tweetsRequest.content")
    @Mapping(target = "likeCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Tweet map(TweetsRequest tweetsRequest, User user);


    @Mapping(target = "id", source = "tweetId")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "userName", source = "user.username")
    public abstract TweetsResponse mapToDto(Tweet tweet);

}
