package com.sapient.fileprocessor;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class FileProcessorCache<K, V> {

  private Map<K, V> fileMap = null;

  FileProcessorCache(int timeToLive) {
    this.fileMap = new ConcurrentHashMap<K, V>();

    if (timeToLive > 0) {
      Thread t = new Thread(() -> {

        while (true) {
          try {
            Thread.sleep(timeToLive * 1000);
          } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
          }
          cleanup();
        }

      });

      t.setDaemon(true);
      t.start();
    }

  }

  private void cleanup() {
    Set<K> keyList = fileMap.keySet();
    
    System.out.println("Refreshing Cache...");
    
    keyList.forEach(k -> {
      fileMap.remove(k);
    });
  }

  public void put(K key, V value) {
    fileMap.put(key, value);
  }

  public V get(K key) {
    return fileMap.get(key);
  }

  public void remove(K key) {
    fileMap.remove(key);
  }

  public int size() {
    return fileMap.size();
  }

  public boolean containsKey(K key) {
    return fileMap.containsKey(key);
  }

  public boolean containsValue(V value) {
    return fileMap.containsValue(value);
  }

}
