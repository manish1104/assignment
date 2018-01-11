package com.sapient.fileprocessor.fileparser;

import java.io.File;
import org.apache.commons.io.FilenameUtils;

public enum SupportedFileTypes {
  TXT("txt"), CSV("csv"), MTD("mtd");

  private String value;

  SupportedFileTypes(String value) {
    this.value = value;
  }

  public static SupportedFileTypes of(String str) {
    for (SupportedFileTypes fileType : values()) {
      if (str.equals(fileType.value)) {
        return fileType;
      }
    }
    return null;
  }

  public static SupportedFileTypes of(File file) {
    return of(FilenameUtils.getExtension(file.getName()));
  }
}
