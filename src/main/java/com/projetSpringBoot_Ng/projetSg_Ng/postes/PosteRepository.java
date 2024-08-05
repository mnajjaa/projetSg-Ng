package com.projetSpringBoot_Ng.projetSg_Ng.postes;
import com.projetSpringBoot_Ng.projetSg_Ng.offres.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PosteRepository extends JpaRepository<Poste, Integer>{

    //re-check for pagination
    List<Poste> findByOffre(Offre offre);

    List<Poste> findByOffreId(Integer offreId);
}
