package com.projetSpringBoot_Ng.projetSg_Ng.offres;
import com.projetSpringBoot_Ng.projetSg_Ng.user.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/offres")
@RequiredArgsConstructor
@Tag(name = "Offres")
public class OffreController {
    private final OffreService offreService;
    private final UserRepository userRepository;


    @PostMapping
    public ResponseEntity<Integer> createOffre(@Valid @RequestBody OffreRequest request, Authentication connectedUser) {
        System.out.println("createOffre endpoint hit");
        System.out.println("Request: " + request);
        System.out.println("Connected User: " + connectedUser);
        return ResponseEntity.ok(offreService.save(request, connectedUser));
    }




}
