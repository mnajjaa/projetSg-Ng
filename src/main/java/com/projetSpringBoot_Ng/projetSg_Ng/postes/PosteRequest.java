package com.projetSpringBoot_Ng.projetSg_Ng.postes;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PosteRequest(
        @NotEmpty(message = "Les technologies sont obligatoires")
        @NotNull(message = "Les technologies ne peuvent pas être vides")
        String technologies,
        @NotEmpty(message = "La formation est obligatoire")
        @NotNull(message = "La formation ne peut pas être vide")
        String formation,
        @NotEmpty(message = "La mission est obligatoire")
        @NotNull(message = "La mission ne peut pas être vide")
        String mission,
        Integer nbreRecrutement
) {}
