/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class CvsLoaderTest {

  private CvsLoader loadDefaultConfiguration = new CvsLoader();

  @Test
  void shouldLoadCSV_WhenGivenValidPath(@TempDir Path tempDir) throws IOException {
    //Arrange
    Path csvFile = tempDir.resolve("test.csv");
    Files.write(csvFile, "col1,col2\nval1,val2".getBytes());
    //Act
    List<String[]> result = loadDefaultConfiguration.loadCVSFileToListOfStrings(csvFile.toString());
    //Assert
    assertEquals(2, result.size());
    assertArrayEquals(new String[]{"col1", "col2"}, result.get(0));
    assertArrayEquals(new String[]{"val1", "val2"}, result.get(1));
  }

  @Test
  void shouldReturnEmptyList_WhenCSVIsEmpty(@TempDir Path tempDir) throws IOException {
    //Arrange
    Path csvFile = tempDir.resolve("empty.csv");
    Files.write(csvFile, new byte[0]);
    //Act
    List<String[]> result = loadDefaultConfiguration.loadCVSFileToListOfStrings(csvFile.toString());
    //Assert
    assertTrue(result.isEmpty());
  }

  @Test
  void shouldReturnEmptyList_WhenNoCSVFound() {
    //Arrange
    List<String[]> expected = new ArrayList<>();
    //Act
    List<String[]> result = loadDefaultConfiguration.loadCVSFileToListOfStrings(
        "invalid/path/to/csv");
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldLoadCVSToList_WhenSpecialCharactsPresent(@TempDir Path tempDir) throws IOException {
    //Arrange
    Path csvFile = tempDir.resolve("special.csv");
    Files.write(csvFile, "col1,\"col2 with, comma\"\n\"val1\nnewline\",val2".getBytes());
    //Act
    List<String[]> result = loadDefaultConfiguration.loadCVSFileToListOfStrings(csvFile.toString());
    //Assert
    assertEquals(2, result.size());
    assertArrayEquals(new String[]{"col1", "col2 with, comma"}, result.get(0));
    assertArrayEquals(new String[]{"val1\nnewline", "val2"}, result.get(1));
  }

}