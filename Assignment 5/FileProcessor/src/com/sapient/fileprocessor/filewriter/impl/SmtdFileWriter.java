package com.sapient.fileprocessor.filewriter.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FilenameUtils;
import com.sapient.fileprocessor.fileparser.TextProcessorResponse;
import com.sapient.fileprocessor.fileparser.impl.MtdFileParser;
import com.sapient.fileprocessor.properties.ApplicationProperties;
import com.sapient.fileprocessor.properties.PropertyType;

public class SmtdFileWriter {

  private Map<String, TextProcessorResponse> aggregateMap =
      new HashMap<String, TextProcessorResponse>();

  public void write(String path) {
    parseFileAndSubDirectories(path);

    writeValuesToFile(sortMap(), path);
  }

  private void parseFileAndSubDirectories(String path) {
    File directory = new File(path);
    File[] fileList = directory.listFiles();

    if (fileList != null) {
      for (File file : fileList) {
        if (file.isFile() && FilenameUtils.getExtension(file.getName()).equals("mtd")) {
          parseFile(file);
        }
      }
    }
  }

  private void parseFile(File file) {
    MtdFileParser mtdFileParser = new MtdFileParser();
    TextProcessorResponse response = mtdFileParser.parse(file);

    aggregateMap.put(FilenameUtils.getBaseName(file.getName()), response);
  }

  private void writeValuesToFile(Map<String, TextProcessorResponse> outMap, String path) {

    String fileName = path + "\\" + getDirectoryName(path) + ".smtd";

    System.out.println("Writing File: " + fileName);
    StringBuffer sb = new StringBuffer();

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
      outMap.forEach((k, v) -> {

        switch (getSortOrder()) {
          case LETTER:
            sb.append("File: " + k + " NoOfLetters: " + v.getLetterCount());
            break;
          case WORD:
            sb.append("File: " + k + " NoOfWords: " + v.getWordCount());
            break;
          case VOWEL:
            sb.append("File: " + k + " NoOfVowels: " + v.getVowelCount());
        }
        
        sb.append("\n");
      });
      bw.write(sb.toString());
    } catch (IOException e) {
      System.out.println("Error Writing response to file" + e.getMessage());
    }

  }

  private SmtdSortOrder getSortOrder() {
    String sortOrder = ApplicationProperties.get().getProperty(PropertyType.SMTD_SORT_ORDER);
    return SmtdSortOrder.valueOf(sortOrder);
  }

  private Map<String, TextProcessorResponse> sortMap() {

    List<Map.Entry<String, TextProcessorResponse>> list =
        new LinkedList<Map.Entry<String, TextProcessorResponse>>(aggregateMap.entrySet());
    Comparator<Map.Entry<String, TextProcessorResponse>> comparator = null;

    switch (getSortOrder()) {
      case LETTER:
        comparator = new SortByLetterComparator();
        break;
      case VOWEL:
        comparator = new SortByVowelComparator();
        break;
      case WORD:
        comparator = new SortByWordComparator();
    }

    Collections.sort(list, comparator);

    Map<String, TextProcessorResponse> sortedMap =
        new LinkedHashMap<String, TextProcessorResponse>();

    for (Map.Entry<String, TextProcessorResponse> entry : list) {
      sortedMap.put(entry.getKey(), entry.getValue());
    }

    return sortedMap;
  }

  private String getDirectoryName(String path) {
    return FilenameUtils.getName(path);
  }

}
