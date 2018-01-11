package com.sapient.fileprocessor.properties;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class ApplicationProperties {

  private static Properties p = new Properties();
  private static final String PROPERTIES_FILE_PATH = "resources/application.properties";
  private static final Logger logger = Logger.getLogger(ApplicationProperties.class.getName());
  private static ApplicationProperties applicationProperties = null;
  private int timeToLive;

  private ApplicationProperties() {

    loadProperties();
    this.timeToLive =
        Integer.parseInt(p.getProperty(PropertyType.PROPERTIES_TIME_TO_LIVE.name().trim()));

    Thread t = new Thread(() -> {

      while (true) {
        try {
          Thread.sleep(timeToLive * 1000);
        } catch (InterruptedException ex) {
          System.out.println(ex.getMessage());
        }

        loadProperties();
      }

    });

    t.setDaemon(true);
    t.start();

  }

  private void loadProperties() {
    try (FileReader reader = new FileReader(PROPERTIES_FILE_PATH)) {
      System.out.println("Loading/Refreshing Application properties...");
      p.load(reader);
    } catch (IOException ex) {
      logger.severe("Unable to read application properties" + ex.getMessage());
    }
  }

  public static ApplicationProperties get() {
    if (applicationProperties == null) {
      initializeProperties();
    }

    return applicationProperties;
  }

  private static synchronized void initializeProperties() {
    applicationProperties = new ApplicationProperties();
  }

  public String getProperty(PropertyType property) {
    return p.getProperty(property.name());
  }
}
