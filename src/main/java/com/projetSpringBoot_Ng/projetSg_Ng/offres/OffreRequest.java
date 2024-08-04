package com.projetSpringBoot_Ng.projetSg_Ng.offres;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import com.projetSpringBoot_Ng.projetSg_Ng.postes.PosteRequest;

import java.util.List;


public record OffreRequest(
//        Integer id,
        @NotNull(message = "Le nom de l'offre est obligatoire")
        @NotEmpty(message = "Le nom de l'offre ne peut pas être vide")
        String nomOffre,
        @NotNull(message = "La description de l'offre est obligatoire")
        @NotEmpty(message = "La description de l'offre ne peut pas être vide")
        String description,
        @NotNull (message = "Les postes sont obligatoires")
        List<PosteRequest> poste

) { }
