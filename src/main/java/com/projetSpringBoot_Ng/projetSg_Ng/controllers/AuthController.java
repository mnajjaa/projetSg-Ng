package com.projetSpringBoot_Ng.projetSg_Ng.controllers;

import com.projetSpringBoot_Ng.projetSg_Ng.dto.RegisterDto;
import com.projetSpringBoot_Ng.projetSg_Ng.models.Role;
import com.projetSpringBoot_Ng.projetSg_Ng.models.UserEntity;
import com.projetSpringBoot_Ng.projetSg_Ng.repository.RoleRepository;
import com.projetSpringBoot_Ng.projetSg_Ng.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
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
