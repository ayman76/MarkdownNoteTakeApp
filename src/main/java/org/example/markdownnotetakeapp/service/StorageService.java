package org.example.markdownnotetakeapp.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    boolean isValidExtension(MultipartFile file);
    void store(MultipartFile file);
    Stream<Path> loadAll();
}
