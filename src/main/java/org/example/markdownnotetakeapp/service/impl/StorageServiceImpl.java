package org.example.markdownnotetakeapp.service.impl;

import org.example.markdownnotetakeapp.service.StorageService;
import org.example.markdownnotetakeapp.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {

    private static final List<String> EXTENSION = new ArrayList<>(Arrays.asList("md", "txt"));
    @Override
    public boolean isValidExtension(MultipartFile file) {
        String extension = FileUtils.getFileExtension(file);
        return EXTENSION.contains(extension);
    }


}
