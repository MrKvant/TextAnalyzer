package com.kazachenko.textanalyzer.controller;

import com.kazachenko.textanalyzer.service.BracketsService;
import com.kazachenko.textanalyzer.service.FileService;
import com.kazachenko.textanalyzer.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class TextController {

    @Autowired
    public FileService fileService;

    @Autowired
    public WordService wordService;

    @Autowired
    public BracketsService bracketsService;

    @RequestMapping(value = "/searchWords", method = RequestMethod.POST)
    public Map<String, Long> searchWords(@RequestParam("inputFile") MultipartFile file) {

        String text = null;

        try {
            text = fileService.getFileText(file);
        } catch (IOException e) { e.printStackTrace();}
        Map<String, Long> countWordsMap = wordService.getCountWords(text);

        // Get only top 10 words
        int i = 10;
        Map<String, Long> top10Map = new LinkedHashMap<>();
        for (Map.Entry<String, Long> entry : countWordsMap.entrySet()) {
            if (i>0) {
                --i;
            } else {
                break;
            }
            String key = entry.getKey();
            Long value = entry.getValue();
            top10Map.put(key, value);
        }

        return top10Map;

    }

    @RequestMapping(value = "/checkBrackets", method = RequestMethod.POST)
    public String checkBrackets(@RequestParam("inputFile") MultipartFile file) {

        String text = null;

        try {
            text = fileService.getFileText(file);
        } catch (IOException e) { e.printStackTrace();}

        boolean correct = bracketsService.validate(text);

        return "Скобки расставлены " + (correct ? "" : "не") + "правильно";

    }



}
