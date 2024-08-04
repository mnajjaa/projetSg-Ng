package com.projetSpringBoot_Ng.projetSg_Ng.postes;

import com.projetSpringBoot_Ng.projetSg_Ng.offres.Offre;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PosteMapper {
    public Poste toPoste(PosteRequest request, Offre offre) {
        return Poste.builder()
                .technologies(request.technologies())
                .formation(request.formation())
                .mission(request.mission())
                .nbreRecrutement(request.nbreRecrutement())
                .offre(offre)
                .build();
    }

    public List<Poste> toPostes(List<PosteRequest> requests, Offre offre) {
        return requests.stream()
                .map(request -> toPoste(request, offre))
                .collect(Collectors.toList());
    }
}
