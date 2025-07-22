package org.example.markdownnotetakeapp.service.impl;

import org.example.markdownnotetakeapp.controller.FileController;
import org.example.markdownnotetakeapp.exception.EmptyFileException;
import org.example.markdownnotetakeapp.service.StorageService;
import org.example.markdownnotetakeapp.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

    @Value("${file.path}")
    private String path;

    private static final List<String> EXTENSION = new ArrayList<>(Arrays.asList("md", "txt"));

    private Path rootLocation;

    @PostConstruct
    public void init(){
        if(path.trim().isEmpty())
            throw new IllegalArgumentException("Path is empty");

        this.rootLocation = Paths.get(path).toAbsolutePath();

       try {
        Files.createDirectories(rootLocation);
        } catch (Exception e) {
            throw new RuntimeException("Could not create storage location", e);
        }
    }


    @Override
    public boolean isValidExtension(MultipartFile file) {
        String extension = FileUtils.getFileExtension(file);
        return EXTENSION.contains(extension);
    }
    @Override
    public void store(MultipartFile file) {
        
        try{
            if(file.isEmpty())
                throw new EmptyFileException("File is empty");
            
            Path destinationPath = this.rootLocation
                                        .resolve(UUID.randomUUID().toString())
                                        .normalize().toAbsolutePath();
            
            if(!destinationPath.getParent().equals(this.rootLocation.toAbsolutePath()))
                throw new IllegalArgumentException("Destination path is invalid");
            
            try{
                InputStream inputStream = file.getInputStream();
                Files.copy(inputStream, destinationPath, StandardCopyOption.REPLACE_EXISTING); 
            }catch(Exception e){
                    throw new RuntimeException();
            }
        }catch(Exception e){
            throw new RuntimeException();
        }
    }
    @Override
    public Stream<Path> loadAll() {
        try{

            return Files.walk(this.rootLocation, 1)
                        .filter(path -> !path.equals(this.rootLocation))
                        .map(this.rootLocation::relativize);

        }catch(IOException  e){
             throw new UncheckedIOException("Failed to read storage location: " + rootLocation, e);
        }
        
    }


}
