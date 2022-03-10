package com.parser_app.uploadingfiles.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path storagePath;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.storagePath = Paths.get(properties.getLocation());
    }

    @Override
    public void store(InputStream fileStream, String fileName) {
        try {

            Path destinationFile = this.storagePath.resolve(
                    Paths.get(fileName))
                    .normalize().toAbsolutePath();


            Files.copy(fileStream, destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    @Override
    public Stream<Path> getAllPaths() {
        try {
            return Files.walk(this.storagePath, 1)
                    .filter(path -> !path.equals(this.storagePath))
                    .map(this.storagePath::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path getPathByFileName(String fileName) {
        return storagePath.resolve(fileName);
    }

    @Override
    public Resource getURLByFileName(String fileName) {
        try {
            Path PathOfFile = getPathByFileName(fileName);
            Resource urlResource = new UrlResource(PathOfFile.toUri());
            if (urlResource.exists() || urlResource.isReadable()) {
                return urlResource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + fileName);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + fileName, e);
        }
    }

    @Override
    public String readFile(Path filePath) throws IOException {

        List<String> text = Files.readAllLines(filePath);

        // System.out.println(text.size());


        return generateJson(text);
    }

    private String generateJson(List<String> text) {
        StringBuilder str = new StringBuilder();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        for (int i = 0; i < text.size(); i++) {
            int deep = count(text.get(i), '#');
            if (deep > 0) {
                FileStruct fileStruct = new FileStruct(text.get(i).replaceAll("#", ""), readContent(text, i), deep);

                str.append(gson.toJson(fileStruct));
            }
        }

        return str.toString();
    }

    private String readContent(List<String> text, int numberOfString) {
        StringBuilder content = new StringBuilder();
        for (int i = numberOfString + 1; i < text.size(); i++) {
            int deep = count(text.get(i), '#');

            if (deep == 0) {
                content.append(text.get(i));
            } else {
                return content.toString();
            }

        }

        return content.toString();
    }

    private int count(String s, char c) {
        return (int) s.chars().filter(x -> x == c).count();
    }
}
