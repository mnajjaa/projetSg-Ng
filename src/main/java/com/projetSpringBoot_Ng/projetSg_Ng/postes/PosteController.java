package com.projetSpringBoot_Ng.projetSg_Ng.postes;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postes")
@RequiredArgsConstructor
public class PosteController {
    private final PosteService posteService;

    @GetMapping("/AllPostes")
    public ResponseEntity<List<PosteResponse>> getAllPostes() {
        return ResponseEntity.ok(posteService.findAllPostes());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PosteResponse> getPosteById(@PathVariable Integer id) {
        return ResponseEntity.ok(posteService.findById(id));
    }

    @GetMapping("/offre/{offreId}")
    public ResponseEntity<List<PosteResponse>> getPostesByOffre(@PathVariable Integer offreId) {
        return ResponseEntity.ok(posteService.findAllByOffreId(offreId));
    }

    @PostMapping
    public ResponseEntity<PosteResponse> createPoste(@RequestBody PosteRequest request, Authentication connectedUser) {
        return ResponseEntity.ok(posteService.save(request, connectedUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PosteResponse> updatePoste(@PathVariable Integer id, @Valid @RequestBody PosteRequest request) {
        return ResponseEntity.ok(posteService.updatePoste(id, request));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoste(@PathVariable Integer id) {
        posteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
