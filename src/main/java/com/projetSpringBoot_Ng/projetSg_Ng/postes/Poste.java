package com.projetSpringBoot_Ng.projetSg_Ng.postes;

import com.projetSpringBoot_Ng.projetSg_Ng.offres.Offre;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "poste")
public class Poste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String technologies;
    private String formation;
    private String mission;
    private Integer nbreRecrutement;

    @ManyToOne
    @JoinColumn(name = "offre_id")
    private Offre offre;

    public int getNbrRecrutement() {
        return nbreRecrutement;
    }
}
