package com.example.Practica.service;

import com.example.Practica.dto.ProductDto;
import com.example.Practica.dto.UserPayload;
import com.example.Practica.model.Producator;
import com.example.Practica.model.Produs;
import com.example.Practica.repository.ProducatorRepository;
import com.example.Practica.repository.ProdusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    MediaService mediaService;
    @Autowired
    private ProdusRepository produsRepository;
    @Autowired
    private ProducatorRepository producatorRepository;

    public ResponseEntity addProduct(ProductDto productDto) {
        productDto.setDenumire(productDto.getDenumire().trim());
        productDto.setDescriere(productDto.getDescriere().trim());
        if (productDto.getDenumire().isEmpty())
            return new ResponseEntity("Alimentul trebuie sa contina o denumire", HttpStatus.BAD_REQUEST);
        if (productDto.getDescriere().isEmpty())
            return new ResponseEntity("Alimentul trebuie sa contina o descriere", HttpStatus.BAD_REQUEST);
        if (productDto.getPret() < 0)
            return new ResponseEntity("Alimentul trebuie sa contina un pret mai mare ca 0", HttpStatus.BAD_REQUEST);
//            Inlocuim cantitatea cu bool disponibil  ?????
        if (productDto.getReducere() < 0 || productDto.getReducere() > 100)
            return new ResponseEntity("Reducerea aplicata unui aliment trebuie sa fie intre 0-100%", HttpStatus.BAD_REQUEST);
        Produs produs = new Produs(productDto);
        Optional optional = producatorRepository.getProducatorById(productDto.getRestaurantId());
        if (optional.isEmpty())
            return new ResponseEntity("Producatorul nu a fost gasit", HttpStatus.BAD_REQUEST);
        Producator producator = (Producator) optional.get();
        if (producator.getProduse() == null)
            producator.setProduse(new ArrayList<>());
        produs.setProducator(producator);
        produsRepository.save(produs);
        producator.getProduse().add(produs);
        producatorRepository.save(producator);
        System.out.println(produs);

        return new ResponseEntity("Alimentul a fost adaugat", HttpStatus.OK);

    }

    public ResponseEntity addImageToProduct(MultipartFile image, Long productId) {

        Optional<Produs> optionalProduct = produsRepository.findById(productId);
        if (optionalProduct.isEmpty())
            return new ResponseEntity("Product does not exist!", HttpStatus.NOT_FOUND);
        Produs product = optionalProduct.get();
        String path = mediaService.addMedia(image);
        if (path.equals(""))
            return new ResponseEntity("It was a problem! Try again later.", HttpStatus.INTERNAL_SERVER_ERROR);
        product.setPath(path);
        produsRepository.save(product);
        return new ResponseEntity("saved file", HttpStatus.OK);

    }

    public ResponseEntity getProductPaginated(Long companyId, int page, int size) {
        Optional optionalProducer = producatorRepository.findById(companyId);
        if (optionalProducer.isEmpty())
            return new ResponseEntity("Producer not found!", HttpStatus.NOT_FOUND);
        Producator producator = (Producator) optionalProducer.get();
        return new ResponseEntity(producator.getProduse().stream().skip((page - 1) * size).limit(size), HttpStatus.OK);
    }
}
