package com.projetSpringBoot_Ng.projetSg_Ng.postes;

import com.projetSpringBoot_Ng.projetSg_Ng.offres.Offre;
import org.springframework.stereotype.Service;

@Service
public class PosteMapper {

    // Convert PosteRequest to Poste
    public Poste toPoste(PosteRequest request, Offre offre) {
        return Poste.builder()
                .technologies(request.technologies())
                .formation(request.formation())
                .mission(request.mission())
                .nbreRecrutement(request.nbreRecrutement())
                .offre(offre)
                .build();
    }

    // Convert Poste to PosteResponse
    public PosteResponse toPosteResponse(Poste poste) {
        return PosteResponse.builder()
                .id(poste.getId())
                .technologies(poste.getTechnologies())
                .formation(poste.getFormation())
                .mission(poste.getMission())
                .nbreRecrutement(poste.getNbrRecrutement())
                .offreId(poste.getOffre() != null ? poste.getOffre().getId() : null)
                .build();
    }
}
