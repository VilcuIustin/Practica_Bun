package com.example.Practica.service;

import com.example.Practica.model.Producator;
import com.example.Practica.repository.ProducatorRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class MediaService {

    private static String path = "/files";

    @Autowired
    ProducatorRepository producatorRepository;

    public String addMedia(MultipartFile mediaFile)  {
        try {
            if (!Files.exists(Paths.get(path)))
                Files.createDirectory(Paths.get(path));
            String filename;
            String extension;
            do{
                filename= new Date().getTime()+""+ RandomStringUtils.randomAlphanumeric(20);
                extension= FilenameUtils.getExtension(mediaFile.getOriginalFilename());
            }while (Files.exists(Paths.get(path).resolve(filename+"."+ extension)));

            Files.copy(mediaFile.getInputStream(), Paths.get(path).resolve(filename+"."+ extension));
            return Paths.get(path).resolve(filename+"."+ extension).toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
