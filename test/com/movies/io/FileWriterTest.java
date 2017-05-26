package com.movies.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;

import com.movies.io.MoviesFileWriter;
import org.junit.Test;

public class FileWriterTest {

    private final MoviesFileWriter moviesWriter = new MoviesFileWriter();

    @Test
    public void testFileUtil () throws Exception {

        String fileName = "test.txt";
        String sampleData = "This is test text file.";
        File file = new File(fileName);
        assertFalse("file should not exist", file.exists());
        moviesWriter.writeToFile(sampleData, fileName);
        assertTrue("file should now exist", file.exists());
        assertEquals(sampleData, moviesWriter.fileToString(fileName));
        moviesWriter.deleteFile(fileName);
        assertFalse("file should not exist", file.exists());
    }

}
