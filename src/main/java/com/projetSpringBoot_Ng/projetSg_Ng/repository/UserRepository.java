package com.projetSpringBoot_Ng.projetSg_Ng.repository;

import com.projetSpringBoot_Ng.projetSg_Ng.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
Optional<UserEntity> findByUsername(String username);
Boolean existsByUsername(String username);
}
