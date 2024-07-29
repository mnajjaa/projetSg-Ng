package com.projetSpringBoot_Ng.projetSg_Ng.diplome;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiplomeRepository extends JpaRepository<Diplome, Integer> {
    @Override
    Optional<Diplome> findById(Integer integer);
    Optional<Diplome> findByUserEmail(String email);
}
