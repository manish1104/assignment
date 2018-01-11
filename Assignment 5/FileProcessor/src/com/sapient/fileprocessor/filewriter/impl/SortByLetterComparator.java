package com.sapient.fileprocessor.filewriter.impl;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import com.sapient.fileprocessor.fileparser.TextProcessorResponse;

public class SortByLetterComparator
    implements Comparator<Map.Entry<String, TextProcessorResponse>> {

  @Override
  public int compare(Entry<String, TextProcessorResponse> o1,
      Entry<String, TextProcessorResponse> o2) {

    if (o1.getValue().getLetterCount() < o2.getValue().getLetterCount()) {
      return 1;
    } else if (o1.getValue().getLetterCount() > o2.getValue().getLetterCount()) {
      return -1;
    } else {
      return 0;
    }

  }
}
