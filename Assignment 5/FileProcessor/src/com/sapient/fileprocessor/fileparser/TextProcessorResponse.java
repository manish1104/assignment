package com.sapient.fileprocessor.fileparser;

public class TextProcessorResponse {
	private int wordCount;
	private int vowelCount;
	private int letterCount;

	public int getWordCount() {
		return wordCount;
	}

	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

	public int getVowelCount() {
		return vowelCount;
	}

	public void setVowelCount(int vowelCount) {
		this.vowelCount = vowelCount;
	}

	public int getLetterCount() {
		return letterCount;
	}

	public void setLetterCount(int letterCount) {
		this.letterCount = letterCount;
	}

  @Override
  public String toString() {
    return "TextProcessorResponse [wordCount=" + wordCount + ", vowelCount=" + vowelCount
        + ", letterCount=" + letterCount + "]";
  }
	
	

}
