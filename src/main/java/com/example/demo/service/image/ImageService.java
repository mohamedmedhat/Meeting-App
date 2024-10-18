package com.example.demo.service.image;

import com.example.demo.mapper.ImageMapper;
import com.example.demo.model.Image;
import com.example.demo.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class ImageService implements IImageService {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    @Override
    public Image saveImage(MultipartFile file) throws IOException {
        Image image = this.imageMapper.toEntity(file);
        return this.imageRepository.save(image);
    }

    @Override
    public Image findImageById(String id) {
        return this.imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image Not Found"));
    }

    @Override
    public void deleteImage(Image image) {
        Image currentImage = this.findImageById(image.getId());
        this.imageRepository.delete(image);
    }
}
