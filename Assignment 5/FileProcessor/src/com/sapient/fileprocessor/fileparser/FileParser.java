package com.sapient.fileprocessor.fileparser;

import java.io.File;

public interface FileParser {
  TextProcessorResponse parse(File file);
}
