package com.example.Practica.service;


import com.example.Practica.dto.ProducatorPayload;
import com.example.Practica.dto.UserPayload;
import com.example.Practica.model.Producator;
import com.example.Practica.model.Role;
import com.example.Practica.model.User;
import com.example.Practica.repository.CategoryRepository;
import com.example.Practica.repository.ProducatorRepository;
import com.example.Practica.repository.RoleRepository;
import com.example.Practica.repository.UserRepository;
import com.example.Practica.security.UserPrinciple;
import org.apache.commons.validator.EmailValidator;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    MediaService mediaService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProducatorRepository producatorRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    private List<String> categoryString = Arrays.asList(new String[]{"Pizza", "Burger", "Pasta", "Traditional", "FastFoods", "Coffee", "Cake"});


    public ResponseEntity addUser(UserPayload userPayload) {
        EmailValidator validator = EmailValidator.getInstance();
        if (!validator.isValid(userPayload.getEmail().trim()))
            return new ResponseEntity("Email invalid", HttpStatus.valueOf(400));
        if (userRepository.findByEmail(userPayload.getEmail()).isPresent())
            return new ResponseEntity("Exista deja un utilizator cu acest email", HttpStatus.BAD_REQUEST);
        Role role = new Role("ROLE_DEFAULT");
        role.setId(2L);
        if (userPayload.getSex() != 'F' && userPayload.getSex() != 'M')
            return new ResponseEntity("Invalid Sex", HttpStatus.valueOf(400));
        if (userPayload.getNume().trim().isEmpty() || userPayload.getPrenume().trim().isEmpty())
            return new ResponseEntity("Numele si/sau prenumele lipsesc!", HttpStatus.valueOf(400));
        User user = new User(userPayload);
        user.setRole(role);
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        userRepository.save(user);
        /* Aici o sa fie trimiterea emailului */
        return new ResponseEntity("Contul a fost creat! Ai primit un email pe adresa de email pentru a activa contul.", HttpStatus.OK);
    }


    public ResponseEntity addProducator(ProducatorPayload producatorNou) {
        System.out.println(producatorNou);
        EmailValidator validator = EmailValidator.getInstance();
        if (!validator.isValid(producatorNou.getEmail().trim()))
            return new ResponseEntity("Email invalid", HttpStatus.valueOf(400));
        if (!producatorRepository.findByEmail(producatorNou.getEmail()))
            return new ResponseEntity("Exista deja un Producator cu acest email", HttpStatus.BAD_REQUEST);
        if (producatorNou.getDenumire().trim().isEmpty() || producatorNou.getDenumire().trim().isEmpty())
            return new ResponseEntity("Denumirea producatorului lipseste!", HttpStatus.valueOf(400));
        if (producatorNou.getCategory().size() == 0)
            return new ResponseEntity("Restaurantul trebuie sa fie in minim o categorie!", HttpStatus.BAD_REQUEST);
        /* De adaugat validator pentru adresa.*/
        if (producatorNou.getCategory().containsAll(categoryString))
            return new ResponseEntity("Categorie invalida!", HttpStatus.BAD_REQUEST);

        Producator producator = new Producator(producatorNou, categoryRepository.findByName(producatorNou.getCategory()).get());
        producator.setPassword(BCrypt.hashpw(producatorNou.getPassword(), BCrypt.gensalt()));
        System.out.println(producator);
        System.out.println(categoryRepository.findByName(producatorNou.getCategory()).get());
        producatorRepository.save(producator);
        /* Aici o sa fie trimiterea emailului */
        return new ResponseEntity("Contul a fost creat! Emailul pentru setarea parolei a fost trimis catre companie.", HttpStatus.OK);
    }


    public ResponseEntity changeImage(MultipartFile image) {
        UserPrinciple principal = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalUser = userRepository.findById(principal.getId());
        if (optionalUser.isEmpty())
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        User user = optionalUser.get();
        String path = mediaService.addMedia(image);
        if (path.equals(""))
            return new ResponseEntity("It was a problem! Try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        user.setPath(path);
        userRepository.save(user);
        return new ResponseEntity("Profile picture is saved", HttpStatus.OK);

    }

    public ResponseEntity retriveData() {
        UserPrinciple principal = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalUser=userRepository.findById(principal.getId());
        if(optionalUser.isEmpty())
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);
        User user= optionalUser.get();
        long noPurchases= user.getLastPurchases().size();
        user.setLastPurchases(null);
        return new ResponseEntity(new Object[] {user,noPurchases }, HttpStatus.OK);

    }
}
