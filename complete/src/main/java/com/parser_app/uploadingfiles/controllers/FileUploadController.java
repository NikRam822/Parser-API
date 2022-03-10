package com.parser_app.uploadingfiles.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.parser_app.uploadingfiles.storage.StorageService;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }


    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<String> serveFile(@PathVariable String filename) throws IOException {
        String content = storageService.readFile(storageService.getPathByFileName(filename));
        return  ResponseEntity.ok().body(content);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        storageService.store(file.getInputStream(), file.getOriginalFilename());
        if (!file.getOriginalFilename().endsWith(".txt")) {
            return new ResponseEntity<>("Not txt", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }


}
