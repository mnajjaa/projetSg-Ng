package com.projetSpringBoot_Ng.projetSg_Ng.postes;

import com.projetSpringBoot_Ng.projetSg_Ng.offres.Offre;
import com.projetSpringBoot_Ng.projetSg_Ng.offres.OffreRepository;
import com.projetSpringBoot_Ng.projetSg_Ng.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PosteService {
    private final PosteRepository posteRepository;
    private final OffreRepository offreRepository;
    private final PosteMapper posteMapper;


    public List<PosteResponse> findAllPostes() {
        List<Poste> postes = posteRepository.findAll();
        return postes.stream()
                .map(posteMapper::toPosteResponse)
                .collect(Collectors.toList());
    }
    public PosteResponse findById(Integer id) {
        Poste poste = posteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Poste not found with id " + id));
        return posteMapper.toPosteResponse(poste);
    }

    public List<PosteResponse> findAllByOffreId(Integer offreId) {
        List<Poste> postes = posteRepository.findByOffreId(offreId);
        return postes.stream()
                .map(posteMapper::toPosteResponse)
                .collect(Collectors.toList());
    }

    public PosteResponse save(PosteRequest request, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Offre offre = offreRepository.findById(request.offreId())
                .orElseThrow(() -> new EntityNotFoundException("Offre not found with id " + request.offreId()));

        // Use the updated toPoste method that takes both PosteRequest and Offre
        Poste poste = posteMapper.toPoste(request, offre);
        posteRepository.save(poste);

        return posteMapper.toPosteResponse(poste);
    }

    public PosteResponse updatePoste(Integer id, PosteRequest request) {
        Poste existingPoste = posteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Poste not found with id " + id));

        existingPoste.setTechnologies(request.technologies());
        existingPoste.setFormation(request.formation());
        existingPoste.setMission(request.mission());
        existingPoste.setNbrRecrutement(request.nbreRecrutement());

        // Save the updated Poste
        posteRepository.save(existingPoste);

        return posteMapper.toPosteResponse(existingPoste);
    }


    public void delete(Integer id) {
        posteRepository.deleteById(id);
    }
}
