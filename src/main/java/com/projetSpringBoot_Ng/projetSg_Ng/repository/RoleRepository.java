package com.projetSpringBoot_Ng.projetSg_Ng.repository;

import com.projetSpringBoot_Ng.projetSg_Ng.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);

}
