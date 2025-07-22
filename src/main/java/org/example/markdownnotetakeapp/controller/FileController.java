package org.example.markdownnotetakeapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.markdownnotetakeapp.exception.InvalidExtensionException;
import org.example.markdownnotetakeapp.model.GrammarResponseDto;
import org.example.markdownnotetakeapp.service.FileService;
import org.example.markdownnotetakeapp.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;
    private final StorageService storageService;
    @PostMapping("/check")
    public ResponseEntity<List<GrammarResponseDto>> checkMarkdown(@RequestParam("file") MultipartFile file) throws IOException {
        if(!storageService.isValidExtension(file))
            throw new InvalidExtensionException("Invalid extension");

        return ResponseEntity.ok(fileService.checkMarkdown(file));
    }

    @PostMapping("/convert-to-html")
    public ResponseEntity<String> postMethodName(@RequestParam("file") MultipartFile file) throws IOException {
        if(!storageService.isValidExtension(file))
            throw new InvalidExtensionException("Invalid extension");

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_HTML).body(fileService.convertToHtml(file));
    }
    
    @PostMapping("store")
    public ResponseEntity<Map<String, String>> store(@RequestParam("file") MultipartFile file) {

        if(!storageService.isValidExtension(file))
            throw new InvalidExtensionException("Invalid Extenstion");

        storageService.store(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("Message", "File was saved successfully."));
    }

    @GetMapping("list-file")
    public ResponseEntity<List<String>> listFile() {
        return ResponseEntity.ok().body(storageService.loadAll().map(path -> path.getFileName().toString()).toList());
    }
    
    
}
