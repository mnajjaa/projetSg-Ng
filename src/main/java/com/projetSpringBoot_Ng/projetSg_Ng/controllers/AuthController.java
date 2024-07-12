package com.projetSpringBoot_Ng.projetSg_Ng.controllers;

import com.projetSpringBoot_Ng.projetSg_Ng.dto.AuthResponseDto;
import com.projetSpringBoot_Ng.projetSg_Ng.dto.LoginDto;
import com.projetSpringBoot_Ng.projetSg_Ng.dto.RegisterDto;
import com.projetSpringBoot_Ng.projetSg_Ng.models.Role;
import com.projetSpringBoot_Ng.projetSg_Ng.models.UserEntity;
import com.projetSpringBoot_Ng.projetSg_Ng.repository.RoleRepository;
import com.projetSpringBoot_Ng.projetSg_Ng.repository.UserRepository;
import com.projetSpringBoot_Ng.projetSg_Ng.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwytGenerator;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwytGenerator = jwtGenerator;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwytGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
if(userRepository.existsByUsername(registerDto.getUsername())){
    return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
}
        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

}
