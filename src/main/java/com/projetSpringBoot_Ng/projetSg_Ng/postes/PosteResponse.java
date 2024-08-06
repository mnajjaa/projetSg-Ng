package com.projetSpringBoot_Ng.projetSg_Ng.postes;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PosteResponse {
    private Integer id;
    private String technologies;
    private String formation;
    private String mission;
    private Integer nbreRecrutement;
    private Integer offreId;


}
