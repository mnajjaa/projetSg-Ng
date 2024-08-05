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


    @GetMapping
    public ResponseEntity<List<OffreResponse>> getAllOffres() {
        return ResponseEntity.ok(offreService.findAllOffres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OffreResponse> getOffreById(@PathVariable Integer id) {
        return ResponseEntity.ok(offreService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createOffre(@Valid @RequestBody OffreRequest request, Authentication connectedUser) {
        System.out.println("createOffre endpoint hit");
        System.out.println("Request: " + request);
        System.out.println("Connected User: " + connectedUser);
        return ResponseEntity.ok(offreService.save(request, connectedUser));
    }


    @PutMapping("/{id}")
    public ResponseEntity<OffreResponse> updateOffre(@PathVariable Integer id, @Valid @RequestBody OffreRequest request, Authentication connectedUser) {
        return ResponseEntity.ok(offreService.updateOffre(id, request, connectedUser));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffre(@PathVariable Integer id) {
        offreService.deleteOffre(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recruter")
    public ResponseEntity<List<OffreResponse>> getAllOffresByRecruter(Authentication connectedUser) {
        return ResponseEntity.ok(offreService.findAllOffresByRecruter(connectedUser));
    }




}
