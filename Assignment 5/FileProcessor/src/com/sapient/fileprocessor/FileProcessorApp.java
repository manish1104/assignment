package com.sapient.fileprocessor;

import java.io.File;
import java.util.Date;
import com.sapient.fileprocessor.fileparser.SupportedFileTypes;
import com.sapient.fileprocessor.fileparser.TextProcessorResponse;
import com.sapient.fileprocessor.fileparser.impl.TxtFileParser;
import com.sapient.fileprocessor.filewriter.FileWriterNew;
import com.sapient.fileprocessor.filewriter.WriteFileType;
import com.sapient.fileprocessor.filewriter.impl.DmtdFileWriter;
import com.sapient.fileprocessor.filewriter.impl.FileWriterFactory;
import com.sapient.fileprocessor.filewriter.impl.SmtdFileWriter;
import com.sapient.fileprocessor.properties.ApplicationProperties;
import com.sapient.fileprocessor.properties.PropertyType;

public class FileProcessorApp {

  FileProcessorCache<String, Date> fileProcessorCache = null;

  FileProcessorApp(FileProcessorCache<String, Date> fileProcessorCache) {
    this.fileProcessorCache = fileProcessorCache;
  }

  public void start() throws InterruptedException {

    while (true) {
      parseFileAndSubDirectories(
          ApplicationProperties.get().getProperty(PropertyType.ROOT_FOLDER_PATH));
      Thread.sleep(5000);
    }

  }

  private void parseFileAndSubDirectories(String rootPath) {

    File directory = new File(rootPath);
    File[] fileList = directory.listFiles();

    if (fileList != null) {
      for (File file : fileList) {
        if (file.isFile() && isProcessable(file) && isSuportedFileType(file) != null) {
          parseFile(file);
        } else {
          parseFileAndSubDirectories(file.getAbsolutePath());
        }
      }
    }

  }

  private boolean isProcessable(File file) {

    if (!fileProcessorCache.containsKey(file.getAbsolutePath())) {
      fileProcessorCache.put(file.getAbsolutePath(), new Date());
      return true;
    } else if (fileProcessorCache.containsKey(file.getAbsolutePath()) && isUpdatable(file)) {
      fileProcessorCache.put(file.getAbsolutePath(), new Date());
      return true;
    } else {
      return false;
    }

  }

  private boolean isUpdatable(File file) {
    Date date = fileProcessorCache.get(file.getAbsolutePath());
    Date currentDate = new Date();

    if (date != null) {
      if (currentDate.getTime() - date.getTime() > 30000) {
        return true;
      }
    }

    return false;
  }

  private void parseFile(File file) {

    Thread t = new Thread(() -> {
      SupportedFileTypes fileType = SupportedFileTypes.of(file);
      if (fileType != null) {
        System.out.println("Processing File: " + file.getAbsolutePath());
        TxtFileParser processor = new TxtFileParser();
        TextProcessorResponse processorResponse = processor.parse(file);
        processReports(file, processorResponse);
      }
    });

    t.start();
  }

  private void processReports(File file, TextProcessorResponse processorResponse) {
    FileWriterFactory fileWriterFactory = new FileWriterFactory();
    FileWriterNew mtdFileWriter = fileWriterFactory.create(WriteFileType.MTD);
    DmtdFileWriter dmtdFileWriter = new DmtdFileWriter();
    SmtdFileWriter smtdFileWriter = new SmtdFileWriter();

    mtdFileWriter.write(file, processorResponse);
    dmtdFileWriter.write(file.getParent());
    smtdFileWriter.write(file.getParent());
  }

  private Boolean isSuportedFileType(File file) {
    return SupportedFileTypes.of(file) != null ? true : false;
  }

}
