package com.wishmaster.singapore.skiing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Simple utility-class to get input data.
 * It is acceptable for this task purposes.
 *
 * @author Andrew
 */
public final class URLDataReaderUtil {

    private URLDataReaderUtil() {
    }

    public static String getDataAsString(String dataUrl) throws IOException {
        URL url = new URL(dataUrl);
        URLConnection connection = url.openConnection();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder data = new StringBuilder(connection.getContentLength());
            in.lines().forEach((string) -> data.append(string).append('\n'));
            return data.toString();
        }
    }

}
