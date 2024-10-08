package com.projetSpringBoot_Ng.projetSg_Ng;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import com.projetSpringBoot_Ng.projetSg_Ng.role.Role;
import com.projetSpringBoot_Ng.projetSg_Ng.role.RoleRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
@EnableAsync
public class ProjetSgNgApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetSgNgApplication.class, args);
	}

		@Bean
		public CommandLineRunner runner(RoleRepository roleRepository) {
			return args -> {
				if (roleRepository.findByName("USER").isEmpty()) {
					roleRepository.save(Role.builder().name("USER").build());
				}
				if (roleRepository.findByName("RECRUITER").isEmpty()) {
					roleRepository.save(Role.builder().name("RECRUITER").build());
				}
			};
		}

}
