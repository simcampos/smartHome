/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CvsLoader {

  /**
   * Method to load CSV files from the given path
   *
   * @param cvsName
   * @return
   */
  public List<String[]> loadCVSFileToListOfStrings(String cvsName) {
    List<String[]> rows = new ArrayList<>();
    try (CSVReader reader = new CSVReader(new FileReader(cvsName))) {
      rows = reader.readAll();
    } catch (IOException | CsvException e) {
      return rows;
    }
    return rows;
  }

}
