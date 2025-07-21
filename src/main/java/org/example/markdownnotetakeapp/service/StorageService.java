package org.example.markdownnotetakeapp.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    boolean isValidExtension(MultipartFile file);
}
