package com.sapient.fileprocessor.filewriter.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.io.FilenameUtils;
import com.sapient.fileprocessor.fileparser.TextProcessorResponse;
import com.sapient.fileprocessor.fileparser.impl.MtdFileParser;

public class DmtdFileWriter {

  private TextProcessorResponse aggregateValues = new TextProcessorResponse();

  public void write(String path) {
    parseFileAndSubDirectories(path);

    writeValuesToFile(path);
  }

  private void parseFileAndSubDirectories(String rootPath) {
    File directory = new File(rootPath);
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
    aggregateValues.setLetterCount(response.getLetterCount() + aggregateValues.getLetterCount());
    aggregateValues.setWordCount(response.getWordCount() + aggregateValues.getWordCount());
    aggregateValues.setVowelCount(response.getVowelCount() + aggregateValues.getVowelCount());
  }

  private void writeValuesToFile(String path) {
    String fileName = path + "\\" + getDirectoryName(path) + ".dmtd";

    System.out.println("Writing File: " + fileName);

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
      bw.write("TotalLetterCount: " + String.valueOf(aggregateValues.getLetterCount()) + "\n");
      bw.write("TotalVowelCount: " + String.valueOf(aggregateValues.getVowelCount()) + "\n");
      bw.write("TotalWordCount: " + String.valueOf(aggregateValues.getWordCount()));
    } catch (IOException e) {
      System.out.println("Error Writing response to file" + e.getMessage());
    }
  }

  private String getDirectoryName(String path) {
    return FilenameUtils.getName(path);
  }
}
