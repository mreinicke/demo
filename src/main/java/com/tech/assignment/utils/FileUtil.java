package com.tech.assignment.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.readAllBytes;

/**
 * Created by a543086 on 10/05/2015.
 */
public class FileUtil {
    public static String readFileToString(Class<?> resourceClass, String resourceName) throws Exception {
        String retVal;

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        InputStream is = resourceClass.getResourceAsStream(resourceName);

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

//    public static Path getResourcePath(Class<?> resourceClass, String resourceName) throws Exception {
//        InputStream is = resourceClass.getResourceAsStream(resourceName);
//        URL url = resourceClass.getResourceAsStream(resourceName);
//        return Paths.get(url.toURI());
//    }
}
