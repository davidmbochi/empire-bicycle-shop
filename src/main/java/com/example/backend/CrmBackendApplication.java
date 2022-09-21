package com.example.backend;

import com.example.backend.model.Role;
import com.example.backend.model.Users;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class CrmBackendApplication {

	private final RoleRepository roleRepository;
	private final UserRepository userRepository;

	@Autowired
	public CrmBackendApplication(RoleRepository roleRepository, UserRepository userRepository) {
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CommandLineRunner commandLineRunner(){
		return args -> {

			List<Users> users = List.of(
					new Users("alex",passwordEncoder().encode("@alex2022")),
					new Users("isaac",passwordEncoder().encode("@isaac2022"))
			);

			Role role_admin = roleRepository.findRoleByRoleName("ROLE_ADMIN");

			if (role_admin == null){
				Role roleAdmin = roleRepository.save(new Role("ROLE_ADMIN"));
				users.get(0).setRoles(List.of(roleAdmin));
				users.get(1).setRoles(List.of(roleAdmin));
			}else {
				users.get(0).setRoles(List.of(role_admin));
				users.get(1).setRoles(List.of(role_admin));
			}

//			userRepository.saveAll(users);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(CrmBackendApplication.class, args);
	}
}
