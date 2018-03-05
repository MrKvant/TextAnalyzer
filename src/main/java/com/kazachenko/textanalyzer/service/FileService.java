package com.kazachenko.textanalyzer.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@Service
public class FileService {

    public String getFileText(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String text = new String(bytes, "UTF-8");
        return text;
    }

    public List<String> readFromPath(String filePath) {
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";

        try {
            Resource resource = new ClassPathResource(filePath);
            InputStream resourceInputStream = resource.getInputStream();

            br = new BufferedReader(new InputStreamReader(resourceInputStream, "UTF-8"));
        } catch (FileNotFoundException e) {
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> list = new ArrayList<>();
        try {
            while ((line = br.readLine()) != null) {
                list.addAll(Arrays.asList(line.split(csvSplitBy)));
            }
        } catch (IOException e) {
            System.exit(0);
        }
        return list;

    }
}
