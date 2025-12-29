package pairmatching.util.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    private static final int BUFFER_SIZE = 8192;

    private final java.io.FileReader fr;

    public FileReader(String fileName) throws IOException {
        fr = new java.io.FileReader(fileName);
    }

    public List<String> readLines() throws IOException {
        List<String> contents = new ArrayList<>();
        BufferedReader br = new BufferedReader(fr, BUFFER_SIZE);

        String line;
        while ((line = br.readLine()) != null) {
            contents.add(line);
        }
        br.close();

        return new ArrayList<>(contents);
    }
}
