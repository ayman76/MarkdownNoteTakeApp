package org.example.markdownnotetakeapp.utils;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

    public static String getFileExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if(fileName != null && !fileName.isEmpty()){
            int dotIndex = fileName.lastIndexOf(".");
            if(dotIndex > 0 && dotIndex < fileName.length()-1){
                return fileName.substring(dotIndex+1);
            }
        }
        return null;
    }
}
