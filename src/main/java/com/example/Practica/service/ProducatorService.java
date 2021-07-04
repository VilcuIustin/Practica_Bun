package com.example.Practica.service;

import com.example.Practica.model.Producator;
import com.example.Practica.repository.ProducatorRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;

@Service
public class ProducatorService {
    @Autowired
    MediaService mediaService;
    @Autowired
    private ProducatorRepository producatorRepository;

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
        return new ResponseEntity("this company does not exist!", HttpStatus.BAD_REQUEST);
    }

}
