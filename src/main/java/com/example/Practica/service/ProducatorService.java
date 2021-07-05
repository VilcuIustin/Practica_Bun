package com.example.Practica.service;

import com.example.Practica.dto.ReviewDto;
import com.example.Practica.model.Producator;
import com.example.Practica.model.Produs;
import com.example.Practica.model.Review;
import com.example.Practica.model.User;
import com.example.Practica.repository.ProducatorRepository;
import com.example.Practica.repository.ReviewRepository;
import com.example.Practica.repository.UserRepository;
import com.example.Practica.security.UserPrinciple;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Date;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class ProducatorService {
    @Autowired
    MediaService mediaService;
    @Autowired
    private ProducatorRepository producatorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public ResponseEntity getProducatorPaginated(int page, int size) {
        return new ResponseEntity(producatorRepository.getProducatoriPaginated(page, size), HttpStatus.OK);
    }

    public ResponseEntity addBackgroundtoCompany(MultipartFile image, Long companyId) {
        // sa adaug o verificare pentru tipul de fisiere
        Optional<Producator> optionalEmployee = producatorRepository.getProducatorById(companyId);
        if (optionalEmployee.isPresent()) {
            long now = System.currentTimeMillis();
            Producator producator = optionalEmployee.get();
            String path = mediaService.addMedia(image);
            if (path.equals(""))
                return new ResponseEntity("It was a problem! Try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
            producator.setPoza(path);
            producatorRepository.save(producator);
            return new ResponseEntity("saved file", HttpStatus.OK);

        }
        return new ResponseEntity("this company does not exist!", HttpStatus.NOT_FOUND);
    }

    //check if the number of times that he bought from a restaurant match with the
    // number of reviews
    public ResponseEntity addReview(ReviewDto reviewDto) {
        UserPrinciple principle=(UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> optionalUser=userRepository.findById(principle.getId());
        if( optionalUser.isEmpty())
            return new ResponseEntity("No User Found",HttpStatus.NOT_FOUND);
        User user= optionalUser.get();
//        if(!user.getLastPurchases().stream().anyMatch(new Predicate<Produs>() {
//            @Override
//            public boolean test(Produs produs) {
//                return produs.getProducator().getId()==reviewDto.getRestaurantId();
//            }
//        }))
//            return new ResponseEntity("You have no command at this restaurant. Buy from it first to write a review",HttpStatus.BAD_REQUEST);
        Optional<Producator> restaurantOptional=producatorRepository.findById(reviewDto.getRestaurantId());

        if(restaurantOptional.isEmpty())
            return new ResponseEntity("No Restaurant Found",HttpStatus.NOT_FOUND);

        Review review = new Review(reviewDto, user);
        reviewRepository.save(review);
               Producator producator= restaurantOptional.get();
        producator.addNewReviewWithStars(review);
        producatorRepository.save(producator);
        return new ResponseEntity("Review added",HttpStatus.OK);


    }
}
