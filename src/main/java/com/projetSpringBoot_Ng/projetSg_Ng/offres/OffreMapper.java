package com.projetSpringBoot_Ng.projetSg_Ng.offres;

import org.springframework.stereotype.Service;

@Service
public class OffreMapper {
    public Offre toOffre(OffreRequest request) {

        return Offre.builder()
                .nomOffre(request.nomOffre())
                .description(request.description())
                .build();
    }
}
