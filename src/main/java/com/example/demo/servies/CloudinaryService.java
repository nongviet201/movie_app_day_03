package com.example.demo.servies;

import com.cloudinary.Cloudinary;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface CloudinaryService {

    public Map uploadFile(MultipartFile file, String folderName) throws IOException;

    public Map uploadVideo(MultipartFile file, String folderName) throws IOException;
}
