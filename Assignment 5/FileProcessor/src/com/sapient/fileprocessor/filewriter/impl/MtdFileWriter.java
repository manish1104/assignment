package com.sapient.fileprocessor.filewriter.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.sapient.fileprocessor.fileparser.TextProcessorResponse;
import com.sapient.fileprocessor.filewriter.FileWriterNew;

public class MtdFileWriter implements FileWriterNew {

	@Override
	public void write(File file, TextProcessorResponse processorResponse) {
		String absolutePath = file.getAbsolutePath();
		String filePath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));

		String fileName = filePath + "\\" + getFileNameWithoutExtension(file) + ".mtd";

		System.out.println("Writing File: " + fileName);

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
			bw.write("LetterCount: " + String.valueOf(processorResponse.getLetterCount()) + "\n");
			bw.write("VowelCount: " + String.valueOf(processorResponse.getVowelCount()) + "\n");
			bw.write("WordCount: " + String.valueOf(processorResponse.getWordCount()));
		} catch (IOException e) {
			System.out.println("Error Writing response to file" + e.getMessage());
		}
	}

	public static String getFileNameWithoutExtension(File file) {

		int index = file.getName().lastIndexOf('.');
		if (index > 0 && index <= file.getName().length() - 2) {
			return (file.getName().substring(0, index));
		}

		return "";

	}

}
