package com.example.TweetsApi.service;

import com.example.TweetsApi.dto.UserLoginRequest;
import com.example.TweetsApi.dto.UserRegistration;
import com.example.TweetsApi.model.User;
import com.example.TweetsApi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.Instant;

@Service
@AllArgsConstructor
@Transactional
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;


    public void signup(UserRegistration userRegistration) {
        User user = new User();
        user.setUsername(userRegistration.getUserId());
        user.setPassword(passwordEncoder.encode(userRegistration.getUserPassword()));
        user.setCreated(Instant.now());
        user.setFirstName((userRegistration.getUserFirstName()));
        user.setLastName(userRegistration.getUserLastName());
        user.setBio(userRegistration.getUserBio());

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }

    public void login(UserLoginRequest userLoginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequest.getUserId(),
                userLoginRequest.getUserPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        if(session != null) {
            session.invalidate();
        }
        for(Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
    }


    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
}
