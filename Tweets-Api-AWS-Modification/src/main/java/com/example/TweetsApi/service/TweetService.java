package com.example.TweetsApi.service;

import com.example.TweetsApi.dto.TweetsRequest;
import com.example.TweetsApi.dto.TweetsResponse;
import com.example.TweetsApi.exceptions.TweetsNotFoundException;
import com.example.TweetsApi.mapper.TweetMapper;
import com.example.TweetsApi.model.Tweet;
import com.example.TweetsApi.model.User;
import com.example.TweetsApi.repository.TweetRepository;
import com.example.TweetsApi.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class TweetService {

    private final TweetRepository tweetRepository;
    private final TweetMapper tweetMapper;
    private final AuthService authService;
    private final UserRepository userRepository;
    public void save(TweetsRequest tweetsRequest) {
        tweetRepository.save(tweetMapper.map(tweetsRequest, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public TweetsResponse getTweet(Long id) {
        Tweet tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new TweetsNotFoundException(id.toString()));
        return tweetMapper.mapToDto(tweet);
    }

    @Transactional(readOnly = true)
    public List<TweetsResponse> getFeed() {
        Set<String> following = authService.getCurrentUser().getFollowing();
        List<User> userList = userRepository.findByUsernameIn(following);
        return tweetRepository.findByUserIn(userList)
                .stream()
                .map(tweetMapper::mapToDto)
                .collect(toList());
    }


    @Transactional(readOnly = true)
    public List<TweetsResponse> getTweetsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return tweetRepository.findByUser(user)
                .stream()
                .map(tweetMapper::mapToDto)
                .collect(toList());
    }
}
