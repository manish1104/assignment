package com.sapient.fileprocessor.filewriter;

public enum WriteFileType {
	MTD("mtd"), DMTD("dmtd"), SMTD("smtd");

	private String value;

	WriteFileType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
