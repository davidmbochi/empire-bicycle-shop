package com.example.backend;

import com.example.backend.model.Role;
import com.example.backend.model.User;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class CrmBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository,
										PasswordEncoder passwordEncoder,
										RoleRepository roleRepository){
		return args ->{
			List<Role> roles =List.of(
					new Role("ROLE_ADMIN"),
					new Role("ROLE_USER")
			);

			roleRepository.saveAll(roles);

			List<User> users = List.of(
					new User("alex",passwordEncoder.encode("1234")),
					new User("isaac",passwordEncoder.encode("1234")),
					new User("patrick",passwordEncoder.encode("1234")),
					new User("robert",passwordEncoder.encode("1234"))
			);

			userRepository.saveAll(users);

			User alex = userRepository.findUserByUserName("alex");
			Role admin = roleRepository.findRoleByRoleName("ROLE_ADMIN");
			alex.getRoles().add(admin);
			userRepository.save(alex);

			User isaac = userRepository.findUserByUserName("isaac");
			Role user = roleRepository.findRoleByRoleName("ROLE_USER");
			isaac.getRoles().add(user);
			userRepository.save(isaac);

			User patrick = userRepository.findUserByUserName("patrick");
			patrick.getRoles().add(admin);
			userRepository.save(patrick);

			User robert = userRepository.findUserByUserName("robert");
			robert.getRoles().add(admin);
			userRepository.save(robert);
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
