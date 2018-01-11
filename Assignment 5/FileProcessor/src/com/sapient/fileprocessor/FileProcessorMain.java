package com.sapient.fileprocessor;

import java.util.Date;
import com.sapient.fileprocessor.properties.ApplicationProperties;
import com.sapient.fileprocessor.properties.PropertyType;

public class FileProcessorMain {

	public static void main(String[] args) {

		FileProcessorCache<String, Date> fileProcessorCache = new FileProcessorCache<String, Date>(getCacheTimeToLive());
		FileProcessorApp fileProcessorApp = new FileProcessorApp(fileProcessorCache);
		try {
			fileProcessorApp.start();
		} catch (InterruptedException e) {
			System.out.println("Thread Execution Interrupted");
		}
	}
	
	private static int getCacheTimeToLive() {
	  return Integer.parseInt(ApplicationProperties.get().getProperty(PropertyType.CACHE_TIME_TO_LIVE));
	}

}
