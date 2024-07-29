package com.projetSpringBoot_Ng.projetSg_Ng.auth;

import com.projetSpringBoot_Ng.projetSg_Ng.diplome.Diplome;
import com.projetSpringBoot_Ng.projetSg_Ng.diplome.DiplomeRepository;
import com.projetSpringBoot_Ng.projetSg_Ng.email.EmailService;
import com.projetSpringBoot_Ng.projetSg_Ng.email.EmailTemplateName;
import com.projetSpringBoot_Ng.projetSg_Ng.role.RoleRepository;
import com.projetSpringBoot_Ng.projetSg_Ng.security.JwtService;
import com.projetSpringBoot_Ng.projetSg_Ng.user.Token;
import com.projetSpringBoot_Ng.projetSg_Ng.user.TokenRepository;
import com.projetSpringBoot_Ng.projetSg_Ng.user.User;
import com.projetSpringBoot_Ng.projetSg_Ng.user.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final TokenRepository tokenRepository;
    private final DiplomeRepository diplomeRepository;


    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public void register(RegistrationRequest request) throws MessagingException {
        var roleName = request.getRole(); // Assuming role comes from the request
        var role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalStateException("ROLE " + roleName + " was not initiated"));

        // Build the user object
        var userBuilder = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(role));

        // Optional fields
        if (request.getPoste() != null) {
            userBuilder.poste(request.getPoste());
        }
        if (request.getAddresse() != null) {
            userBuilder.addresse(request.getAddresse());
        }
        if (request.getEcole() != null) {
            userBuilder.ecole(request.getEcole());
        }
        if (request.getNote() != null) {
            userBuilder.note(request.getNote());
        }

        var user = userBuilder.build();
        userRepository.save(user);

        // Save diplomas if present
        if (request.getDiplomes() != null) {
            request.getDiplomes().forEach(diplomeName -> {
                var diplome = Diplome.builder()
                        .diplome(diplomeName)
                        .user(user)
                        .build();
                diplomeRepository.save(diplome);
            });
        }

        sendValidationEmail(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("fullName", user.getFullName());

        var jwtToken = jwtService.generateToken(claims, (User) auth.getPrincipal());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

//    @Transactional
    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token)
                // todo exception has to be defined
                .orElseThrow(() -> new RuntimeException("Invalid token"));
        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired. A new token has been send to the same email address");
        }

        var user = userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);

        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }

    private String generateAndSaveActivationToken(User user) {
        // Generate a token
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);

        return generatedToken;
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);

        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Account activation"
        );
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();

        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }

        return codeBuilder.toString();
    }
}