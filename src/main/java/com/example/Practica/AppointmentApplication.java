package com.example.Practica;


import com.example.Practica.model.Role;
import com.example.Practica.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppointmentApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(AppointmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		Role admin = new Role("ROLE_ADMIN");
//		Role roleDefault = new Role("ROLE_DEFAULT");
//
//		roleRepository.save(admin);
//		roleRepository.save(roleDefault);

	}

}
