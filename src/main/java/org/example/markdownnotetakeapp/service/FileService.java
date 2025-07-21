package org.example.markdownnotetakeapp.service;

import org.example.markdownnotetakeapp.model.GrammarResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    List<GrammarResponseDto> checkMarkdown(MultipartFile file) throws IOException;
    String convertToHtml(MultipartFile file) throws IOException;
}
