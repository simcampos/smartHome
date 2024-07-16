/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SensorModelNameTest {

  /**
   * Test the constructor of the class SensorModelName.
   */
  @Test
  void shouldInstantiateSensorModelName_whenGivenValidParameters() {
    // Arrange
    SensorModelName sensorModelName;
    String name = "SensorModelName";

    // Act
    sensorModelName = new SensorModelName(name);

    // Assert
    assertNotNull(sensorModelName);
  }

  /**
   * Test the constructor of the class SensorModelName with an empty name.
   */
  @Test
  void shouldThrowException_whenNameIsEmpty() {
    // Arrange
    String name = " ";

    // Act+Assert
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorModelName(name));
    assertEquals("The device name cannot be null, blank, or empty.", exception.getMessage());
  }

  /**
   * Test the constructor of the class SensorModelName with a null name.
   */
  @Test
  void shouldThrowException_whenNameIsNull() {
    // Arrange
    String name = null;

    // Act+Assert
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorModelName(name));
    assertEquals("The device name cannot be null, blank, or empty.", exception.getMessage());
  }

  /**
   * Test the constructor of the class SensorModelName with a name that has more than 5 characters.
   */
  @Test
  void shouldThrowException_whenNameContainsSpecialCharacters() {
    // Arrange
    String name = "SensorModelName!";

    // Act+Assert
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorModelName(name));
    assertEquals("The device name can only contain letters and numbers.", exception.getMessage());
  }

  /**
   * Test the getSensorModelName method of the class SensorModel
   */
  @Test
  void shouldReturnSensorModelName_whenGetSensorModelNameIsCalled() {
    // Arrange
    SensorModelName sensorModelName;
    String name = "SensorModelName";

    // Act
    sensorModelName = new SensorModelName(name);

    // Assert
    assertEquals(name, sensorModelName.getSensorModelName());
  }

  /**
   * Test if equals method returns true with the same object.
   */
  @Test
  void shouldReturnTrue_whenComparingSensorModelNameWithItself() {
    // Arrange
    SensorModelName sensorModelName;
    String name = "SensorModelName";
    sensorModelName = new SensorModelName(name);

    // Act
    boolean result = sensorModelName.equals(sensorModelName);

    // Assert
    assertTrue(result);
  }

  /**
   * Test the equals method of the class SensorModelName.
   */
  @Test
  void shouldReturnTrue_whenComparingTwoEqualSensorModelName() {
    // Arrange
    SensorModelName sensorModelName1;
    SensorModelName sensorModelName2;
    String name = "SensorModelName";

    // Act
    sensorModelName1 = new SensorModelName(name);
    sensorModelName2 = new SensorModelName(name);

    // Assert
    assertEquals(sensorModelName1, sensorModelName2);
  }

  /**
   * Test the equals method of the class SensorModelName with two different sensor model names.
   */
  @Test
  void shouldReturnFalse_whenComparingTwoDifferentSensorModelName() {
    // Arrange
    SensorModelName sensorModelName1;
    SensorModelName sensorModelName2;
    String name1 = "SensorModelName1";
    String name2 = "SensorModelName2";

    // Act
    sensorModelName1 = new SensorModelName(name1);
    sensorModelName2 = new SensorModelName(name2);

    // Assert
    assertNotEquals(sensorModelName1, sensorModelName2);
  }

  /**
   * Test the equals method of the class SensorModelName with the same sensor model name.
   */
  @Test
  void shouldReturnFalse_whenComparingSensorModelNameWithDifferentObject() {
    // Arrange
    SensorModelName sensorModelName;
    String name = "SensorModelName";
    sensorModelName = new SensorModelName(name);

    // Act
    boolean result = sensorModelName.equals(new Object());

    // Assert
    assertFalse(result);
  }

  /**
   * Test hashCode method of the class SensorModel
   */
  @Test
  void shouldReturnHashCode_whenCallingHashCode() {
    // Arrange
    String name = "SensorModelName";
    SensorModelName sensorModelName = new SensorModelName(name);

    int expected = name.hashCode();

    // Act
    int result = sensorModelName.hashCode();

    // Assert
    assertEquals(expected, result);

  }

  @Test
  void shouldReturnStringRepresentation_whenCallingToString() {
    // Arrange
    String name = "SensorModelName";
    SensorModelName sensorModelName = new SensorModelName(name);

    String expected = name;

    // Act
    String result = sensorModelName.toString();

    // Assert
    assertEquals(expected, result);
  }
}