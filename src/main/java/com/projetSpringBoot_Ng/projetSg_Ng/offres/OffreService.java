package com.projetSpringBoot_Ng.projetSg_Ng.offres;
import com.projetSpringBoot_Ng.projetSg_Ng.postes.Poste;
import com.projetSpringBoot_Ng.projetSg_Ng.postes.PosteMapper;
import com.projetSpringBoot_Ng.projetSg_Ng.postes.PosteRepository;
import com.projetSpringBoot_Ng.projetSg_Ng.postes.PosteRequest;
import com.projetSpringBoot_Ng.projetSg_Ng.user.User;
import com.projetSpringBoot_Ng.projetSg_Ng.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class OffreService {
    private final OffreRepository offreRepository;
    private final UserRepository userRepository;
    private final OffreMapper offreMapper;
    private final PosteMapper posteMapper;
    private final PosteRepository posteRepository;

    public String getOffreNameById(Integer offreId) {
        Offre offre = offreRepository.findById(offreId)
                .orElseThrow(() -> new EntityNotFoundException("Offre not found with id " + offreId));
        return offre.getNomOffre();
    }

    // check the github repo for refactor
    public Integer save(OffreRequest request, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Offre offre = offreMapper.toOffre(request);
        offre.setRecruteur(user);
        offreRepository.save(offre);

    //        List<Poste> postes = posteMapper.toPostes(request.poste(), offre);
    //        posteRepository.saveAll(postes);

        return offre.getId();
    }




    public OffreResponse findById(Integer offreId) {
       return offreRepository.findById(offreId)
               .map(offreMapper::toOffreResponse)
               .orElseThrow(() -> new EntityNotFoundException("Offre not found"+ offreId));
    }

    public List<OffreResponse> findAllOffres() {
        List<Offre> offres = offreRepository.findAll();
        return offres.stream()
                .map(offreMapper::toOffreResponse)
                .collect(Collectors.toList());
    }

//    public List<OffreResponse> findAllOffresByRecruter(Authentication connectedUser) {
//        User user = (User) connectedUser.getPrincipal();
//        List<Offre> offres = offreRepository.findAllById(user.getId());
//        return offres.stream()
//                .map(offreMapper::toOffreResponse)
//                .collect(Collectors.toList());
//    }

    public List<OffreResponse> findAllOffresByRecruter(Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        List<Offre> offres = offreRepository.findAllByRecruteurId(user.getId());
        return offres.stream()
                .map(offreMapper::toOffreResponse)
                .collect(Collectors.toList());
    }

    public OffreResponse updateOffre(Integer id, OffreRequest request, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Offre existingOffre = offreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Offre not found with id " + id));

        existingOffre.setNomOffre(request.nomOffre());
        existingOffre.setDescription(request.description());
        existingOffre.setLastModifiedBy(user.getId());

        // Save the updated Offre
        offreRepository.save(existingOffre);

        return offreMapper.toOffreResponse(existingOffre);
    }


    public void deleteOffre(Integer id) {
        Offre existingOffre = offreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Offre not found"));
        offreRepository.delete(existingOffre);
    }




}
