package com.movies.io;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * {@link MoviesFileWriter} writes data set to file.
 * Created by Maja
 */
public class MoviesFileWriter {
    private static final Logger log = Logger.getLogger(MoviesFileWriter.class);
    public void writeToFile(String data, String path) throws IOException {
        Path thePath = Paths.get(path);
        if (!Files.exists(thePath)) {
            Files.createFile(thePath);
        }
        Files.write(thePath, data.getBytes());
        log.info("File saved to file: " +thePath );

    }

    public String fileToString(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    public void deleteFile(String path) throws IOException, SecurityException {
        Path thePath = FileSystems.getDefault().getPath(path);
        Files.delete(thePath);
        log.info("File at the location " + thePath+" deleted.");
    }

}
