package com.example.demo.mapper;

import com.example.demo.model.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class ImageMapper {
    @Value("${IMAGE_UPLOAD_DIR}")
    private String uploadDir;

    public Image toEntity(MultipartFile file) throws IOException {
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String filePath = uploadDir + file.getOriginalFilename();
        file.transferTo(new File(filePath));
        Image image = new Image();
        image.setFilename(file.getOriginalFilename());
        image.setFiletype(file.getContentType());
        image.setFilePath("/images/" + file.getOriginalFilename());
        return image;
    }
}
