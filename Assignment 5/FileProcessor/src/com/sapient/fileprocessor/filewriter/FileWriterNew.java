package com.sapient.fileprocessor.filewriter;

import java.io.File;

import com.sapient.fileprocessor.fileparser.TextProcessorResponse;

public interface FileWriterNew {

	public void write(File file, TextProcessorResponse processorResponse);
}
