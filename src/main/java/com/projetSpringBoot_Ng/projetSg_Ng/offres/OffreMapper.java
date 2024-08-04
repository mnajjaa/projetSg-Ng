package com.projetSpringBoot_Ng.projetSg_Ng.offres;

import com.projetSpringBoot_Ng.projetSg_Ng.postes.Poste;
import com.projetSpringBoot_Ng.projetSg_Ng.postes.PosteRequest;
import com.projetSpringBoot_Ng.projetSg_Ng.postes.PosteResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OffreMapper {
    public Offre toOffre(OffreRequest request) {

        return Offre.builder()
                .nomOffre(request.nomOffre())
                .description(request.description())
                .build();
    }

    public List<Poste> toPostes(List<PosteRequest> requests, Offre offre) {
        return requests.stream()
                .map(request -> Poste.builder()
                        .technologies(request.technologies())
                        .formation(request.formation())
                        .mission(request.mission())
                        .nbreRecrutement(request.nbreRecrutement())
                        .offre(offre)
                        .build())
                .collect(Collectors.toList());
    }

    public OffreResponse toOffreResponse(Offre offre) {
        int totalNbrRecrutement = offre.getPostes().stream()
                .mapToInt(Poste::getNbrRecrutement)
                .sum();

        return OffreResponse.builder()
                .id(offre.getId())
                .nomOffre(offre.getNomOffre())
                .description(offre.getDescription())
                .nbrRecrutement(totalNbrRecrutement)
                .build();
    }



}
