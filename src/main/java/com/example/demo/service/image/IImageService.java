package com.example.demo.service.image;

import com.example.demo.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImageService {

    Image saveImage(MultipartFile file) throws IOException;
    Image findImageById(String id);
    void deleteImage(Image image);
}
