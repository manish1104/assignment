package com.sapient.fileprocessor.filewriter.impl;

import com.sapient.fileprocessor.fileparser.InvalidFileTypeException;
import com.sapient.fileprocessor.filewriter.FileWriterNew;
import com.sapient.fileprocessor.filewriter.WriteFileType;

public class FileWriterFactory {
	public FileWriterNew create(WriteFileType fileType) {

		switch (fileType) {
		case MTD:
			return new MtdFileWriter();
		case SMTD:
			return null;
		case DMTD:
			return null;
		default:
			throw new InvalidFileTypeException("FileType not supported: " + fileType);
		}

	}
}
