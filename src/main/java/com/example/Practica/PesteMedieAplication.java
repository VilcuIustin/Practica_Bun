package com.example.Practica;


import com.example.Practica.model.Category;
import com.example.Practica.repository.CategoryRepository;
import com.example.Practica.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PesteMedieAplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(PesteMedieAplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//		Role admin = new Role("ROLE_ADMIN");
//		Role roleDefault = new Role("ROLE_DEFAULT");
//
//		roleRepository.save(admin);
//		roleRepository.save(roleDefault);
//		List<String> categoryString=
//				Arrays.asList(new String[] {"Pizza","Burger","Pasta",
//						"Traditional","FastFoods","Coffee", "Cake"});
//		for(String s: categoryString){
//			Category category = new Category(s);
//			categoryRepository.save(category);
//		}


    }

}
