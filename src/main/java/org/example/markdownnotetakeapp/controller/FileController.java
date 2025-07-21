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
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService markDownService;
    private final StorageService storageService;
    @PostMapping("/check")
    public ResponseEntity<List<GrammarResponseDto>> checkMarkdown(@RequestParam("file") MultipartFile file) throws IOException {
        if(!storageService.isValidExtension(file))
            throw new InvalidExtensionException("Invalid extension");

        return ResponseEntity.ok(markDownService.checkMarkdown(file));
    }

    @PostMapping("/convert-to-html")
    public ResponseEntity<String> postMethodName(@RequestParam("file") MultipartFile file) throws IOException {
        if(!storageService.isValidExtension(file))
            throw new InvalidExtensionException("Invalid extension");

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_HTML).body(markDownService.convertToHtml(file));
    }
    
}
