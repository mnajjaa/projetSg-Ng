package com.projetSpringBoot_Ng.projetSg_Ng.offres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OffreRepository extends JpaRepository<Offre, Integer> {
List<Offre> findAllById(Integer id);


    List<Offre> findAllByRecruteurId(Integer recruteurId);
}
