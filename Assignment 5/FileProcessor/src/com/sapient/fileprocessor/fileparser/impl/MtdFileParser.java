package com.sapient.fileprocessor.fileparser.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import com.sapient.fileprocessor.fileparser.FileParser;
import com.sapient.fileprocessor.fileparser.TextProcessorResponse;

public class MtdFileParser implements FileParser {

  public TextProcessorResponse parse(File file) {
    String line = "";

    TextProcessorResponse response = new TextProcessorResponse();

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      while ((line = br.readLine()) != null) {

        StringTokenizer tokenizer = new StringTokenizer(line, ":");

        String token = tokenizer.nextToken();

        if (token.equals("LetterCount")) {
          response.setLetterCount(Integer.valueOf(tokenizer.nextToken().trim()));
        } else if (token.equals("VowelCount")) {
          response.setVowelCount(Integer.valueOf(tokenizer.nextToken().trim()));;
        } else if (token.equals("WordCount")) {
          response.setWordCount(Integer.valueOf(tokenizer.nextToken().trim()));
        }
      }

    } catch (IOException e) {
      System.out.println("There has been an exception while parsing file");
    }

    return response;
  }

}
