package com.wishmaster.singapore.skiing;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Simple utility-class to get data from map file.
 * It is acceptable for this task purposes.
 *
 * @author Andrew
 */
public class MapFileLinesReader {

    private final String mapFileResource;

    public MapFileLinesReader(String mapFileResource) {
        this.mapFileResource = mapFileResource;
    }

    public List<String> getMapFileLines() throws IOException {
        return Files.readAllLines(getMapFilePath());
    }

    private Path getMapFilePath() throws IOException {
        try {
            return Paths.get(MapFileLinesReader.class.getResource(mapFileResource).toURI());
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }

}
