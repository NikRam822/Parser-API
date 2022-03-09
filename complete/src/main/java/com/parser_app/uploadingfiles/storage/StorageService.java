package com.parser_app.uploadingfiles.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface StorageService {



	void store(InputStream fileStream, String fileName);

	Stream<Path> getAllPaths();

	Path getPathByFileName(String filename);

	Resource getURLByFileName(String filename);

	String readFile(Path filePath) throws IOException;


}
