package com.felipesouza.peturn.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {
    @Value("${peturn.images.directory}")
    private String imageDirectory;

    public String saveFile(MultipartFile file) throws IOException {
        Files.createDirectories(Paths.get(imageDirectory));
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String filename = String.format("%s.%s", UUID.randomUUID(), extension);
        Path path = Paths.get(imageDirectory, filename);
        file.transferTo(path);
        return filename;
    }

    public Resource getImage(String filename) {
        Path directory = Paths.get(imageDirectory)
                .toAbsolutePath()
                .normalize();
        Path path = directory.resolve(filename).normalize();

        if (!path.startsWith(directory)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found");
        }

        Resource resource = new FileSystemResource(path);
        if (!resource.exists() || !resource.isReadable()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found.");
        }

        return resource;
    }
}
