package org.example.markdownnotetakeapp.service.impl;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.example.markdownnotetakeapp.model.GrammarResponseDto;
import org.example.markdownnotetakeapp.service.FileService;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<GrammarResponseDto> checkMarkdown(MultipartFile file) throws IOException {
        String content = file.getResource().getContentAsString(StandardCharsets.UTF_8);
        JLanguageTool jLanguageTool = new JLanguageTool(new AmericanEnglish());
        List<RuleMatch> ruleMatches = jLanguageTool.check(content);
        return ruleMatches.stream().map(GrammarResponseDto::new).toList();
    }

    @Override
    public String convertToHtml(MultipartFile file) throws IOException {
        try{

        
        String content = file.getResource().getContentAsString(StandardCharsets.UTF_8);
        Parser parser = Parser.builder().build();
        Node doc = parser.parse(content);
        HtmlRenderer renderer = HtmlRenderer.builder().build();

        return renderer.render(doc);
        }
        catch(IOException exception){
            throw new RuntimeException();
        }
    }
    
}
