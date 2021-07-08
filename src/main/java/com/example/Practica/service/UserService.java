package com.example.Practica.service;


import com.example.Practica.dto.CosDto;
import com.example.Practica.dto.ProducatorPayload;
import com.example.Practica.dto.UserPayload;
import com.example.Practica.model.*;
import com.example.Practica.repository.*;
import com.example.Practica.security.EmailService;
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
import java.util.Date;
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
    @Autowired
    private ProdusRepository productRepository;
    @Autowired
    private CosRepository cosRepository;
    @Autowired
    EmailService emailService;

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
        emailService.sendMail(user.getEmail(),"New Account","Bine ai venit pe platforma, "+ user.getNume()+" "+user.getPrenume() +
                "! Speram ca experienta ta aici sa fie la fel cum isi doreste Ionut Lengher (Imi place sa mananc) sa fie o saorma... Peste Medie! ");

        userRepository.save(user);
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
        emailService.sendMail(producator.getEmail(),"New Account","Bine ai venit pe platforma, "+ producator.getDenumire() +
                "! Echipa Peste Medie va doreste spor la vanzari si sa aveti cati mai multi clienti! Inca ceva. Parola default este "+ producatorNou.getPassword()
                + " va rugam sa o schimbati cat mai repede posibil utilizand urmatorul link." );

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
        Optional<User> optionalUser = userRepository.findById(principal.getId());

        if (optionalUser.isEmpty())
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);

        User user = optionalUser.get();
        long noPurchases = user.getLastPurchases().size();
        user.setLastPurchases(null);
        return new ResponseEntity(user, HttpStatus.OK);

    }


    public ResponseEntity buy(CosDto cosDto) {
        UserPrinciple principal = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalUser = userRepository.findById(principal.getId());

        if (optionalUser.isEmpty())
            return new ResponseEntity("User not found", HttpStatus.NOT_FOUND);

        User user = optionalUser.get();

        if (cosDto.getProduse().size() == 0)
            return new ResponseEntity("No products to be bought", HttpStatus.BAD_REQUEST);

        List<Produs> products = productRepository.findAllById(cosDto.getProduse());
        float price = 0.0f;

        for (Produs product : products)
            price += product.getPret() * (1.0f - product.getReducere() / 100);

        Cos cos= new Cos(price, products,new Date());
        cosRepository.save(cos);
        user.getLastPurchases().add(cos);
        userRepository.save(user);

        return new ResponseEntity("Transaction succeed ",HttpStatus.OK);

    }
}
