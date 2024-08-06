package com.projetSpringBoot_Ng.projetSg_Ng.offres;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OffreResponse {
    private Integer id;
    private String nomOffre;
    private String description;
    private Integer nbrRecrutement;



}
