package com.kazachenko.textanalyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class WordService {

    @Autowired
    private FileService fileService;

    private List<String> SIMPLE_PRONOUNS;

    private List<String> COMPLEX_PRONOUNS;

    public Map<String, Long> getCountWords(String text) {

        SIMPLE_PRONOUNS = fileService.readFromPath("/static/csv/simple_pronouns.csv");
        COMPLEX_PRONOUNS = fileService.readFromPath("/static/csv/complex_pronouns.csv");

        //1. Make text of the same register
        text = text.toLowerCase();

        //2. Remove complex pronouns
        for (String str : COMPLEX_PRONOUNS) {
            text = text.replaceAll(str, "");
        }

        //3. Get words between delimiters (letter 'ё' don't have order in UTF-8)
        Pattern pat = Pattern.compile("[а-яё]+");
        Matcher matcher = pat.matcher(text);
        List<String> wordsList = new ArrayList<>();
        while (matcher.find()) {
            wordsList.add(matcher.group());
        }

        //4. Remove simple pronouns of wordsList
        for (String str : SIMPLE_PRONOUNS) {
            wordsList.removeIf(p-> p.equals(str));
        }

        //5. Count the number of words
        Map<String, Long> countMap = wordsList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


        //6. Sort a map and add to finalMap
        Map<String, Long> finalMap = new LinkedHashMap<>();
        countMap.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue()
                .reversed()).forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

        return finalMap;
    }

    public void checkBrackets(String text) {

    }
}
