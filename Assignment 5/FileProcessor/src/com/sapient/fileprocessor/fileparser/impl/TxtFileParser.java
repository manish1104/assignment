package com.sapient.fileprocessor.fileparser.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.sapient.fileprocessor.fileparser.FileParser;
import com.sapient.fileprocessor.fileparser.TextProcessorResponse;

public class TxtFileParser implements FileParser {

  private int wordCount = 0;
  private int letterCount = 0;
  private int vowelCount = 0;
  private int specialCharCount = 0;

  public TextProcessorResponse parse(File file) {
    String line = "";

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {

      while ((line = br.readLine()) != null) {
        wordCount += getWordCount(line);
        letterCount += getLetterCount(line);
        vowelCount += getVowelCount(line);
        specialCharCount += getSpecialCharCount(line);
      }

    } catch (IOException e) {
      System.out.println("Unable to parse file: " + e.getMessage());
    }

    TextProcessorResponse response = new TextProcessorResponse();
    response.setWordCount(wordCount);
    response.setLetterCount(letterCount);
    response.setVowelCount(vowelCount);

    return response;
  }

  private int getWordCount(String line) {
    return line.split("\\s").length;
  }

  private int getLetterCount(String line) {
    return line.length();
  }

  private int getVowelCount(String line) {
    int count = 0;

    for (int i = 0; i < line.length(); i++) {
      if (isVowel(line.charAt(i))) {
        count++;
      }
    }

    return count;
  }

  private boolean isVowel(char c) {
    return (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
  }

  private int getSpecialCharCount(String line) {
    int count = 0;

    for (int i = 0; i < line.length(); i++) {
      if (isSymbol(line.charAt(i))) {
        count++;
      }
    }

    return count;
  }

  private boolean isSymbol(char c) {
    return (c == '@' || c == '#' || c == '$' || c == '*');
  }
}
