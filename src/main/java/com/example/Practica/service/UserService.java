package com.example.Practica.service;


import com.example.Practica.dto.ProducatorPayload;
import com.example.Practica.dto.UserPayload;
import com.example.Practica.model.Producator;
import com.example.Practica.model.Role;
import com.example.Practica.model.User;
import com.example.Practica.repository.ProducatorRepository;
import com.example.Practica.repository.RoleRepository;
import com.example.Practica.repository.UserRepository;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.apache.oro.text.perl.Perl5Util;

@Service
public class UserService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProducatorRepository producatorRepository;
    @Autowired
    private RoleRepository roleRepository;

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
        EmailValidator validator = EmailValidator.getInstance();
        if (!validator.isValid(producatorNou.getEmail().trim()))
            return new ResponseEntity("Email invalid", HttpStatus.valueOf(400));
        if (producatorRepository.findByEmail(producatorNou.getEmail()))
            return new ResponseEntity("Exista deja un Producator cu acest email", HttpStatus.BAD_REQUEST);
        if (producatorNou.getDenumire().trim().isEmpty() || producatorNou.getDenumire().trim().isEmpty())
            return new ResponseEntity("Denumirea producatorului lipseste!", HttpStatus.valueOf(400));

        /* De adaugat validator pentru adresa.*/
        Producator producator = new Producator(producatorNou);
        producator.setPassword(BCrypt.hashpw(producatorNou.getPassword(), BCrypt.gensalt()));
        producatorRepository.save(producator);
        /* Aici o sa fie trimiterea emailului */
        return new ResponseEntity("Contul a fost creat! Emailul pentru setarea parolei a fost trimis catre companie.", HttpStatus.OK);
    }


}
