package com.example.demo.servies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface FileService {
    public Map uploadImage(MultipartFile file) throws IOException;


}
