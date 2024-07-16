/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.dew_point_sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DewPointValueTest {

  /**
   * Test if the class DewPointValue can be instantiated.
   */
  @Test
  void shouldInstantiateDewPointValue() {
    //Arrange
    int value = -70;

    // Act
    DewPointValue result = new DewPointValue(value);

    // Assert
    assertNotNull(result);
  }

  /**
   * Test if the class DewPointValue throws an exception when the value is higher than 70.
   */
  @Test
  void shouldThrowException_WhenValueIsLowerThanMinus70() {
    // Arrange
    int value = -71;

    String expectedMessage = "The value of the dew point cannot be lower than -70.";

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new DewPointValue(value));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());

  }

  /**
   * Test if the class DewPointValue throws an exception when the value is higher than 100.
   */
  @Test
  void shouldReturnStringValue() {
    // Arrange
    int value = 100;
    DewPointValue dewPointValue = new DewPointValue(value);

    String expected = "100";

    // Act
    String result = dewPointValue.toString();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Returns DewPointValue when toString is called.
   */
  @Test
  void shouldReturnDewPointValue_whenToStringIsCalled() {
    // Arrange
    int value = 100;
    DewPointValue dewPointValue = new DewPointValue(value);

    String expected = "100";

    // Act
    String result = dewPointValue.toString();

    // Assert
    assertEquals(expected, result);

  }


  /**
   * should return true when dew point values are equal.
   */
  @Test
  void shouldReturnTrue_whenDewPointValuesAreEqual() {
    // Arrange
    int value = 100;
    DewPointValue dewPointValue1 = new DewPointValue(value);
    DewPointValue dewPointValue2 = new DewPointValue(value);

    // Act
    boolean result = dewPointValue1.equals(dewPointValue2);

    // Assert
    assertTrue(result);
  }

  /**
   * should return false when dew point values are not equal.
   */
  @Test
  void shouldReturnFalse_whenDewPointValuesAreNotEqual() {
    // Arrange
    int value1 = 100;
    int value2 = 200;
    DewPointValue dewPointValue1 = new DewPointValue(value1);
    DewPointValue dewPointValue2 = new DewPointValue(value2);

    // Act
    boolean result = dewPointValue1.equals(dewPointValue2);

    // Assert
    assertFalse(result);
  }

  /**
   * should return false when object is not DewPointValue.
   */
  @Test
  void shouldReturnFalse_whenObjectIsNotDewPointValue() {
    // Arrange
    int value = 100;
    DewPointValue dewPointValue = new DewPointValue(value);

    // Act
    boolean result = dewPointValue.equals(new Object());

    // Assert
    assertFalse(result);
  }

  /**
   * should return hash code.
   */
  @Test
  void shouldReturnHashCode() {
    // Arrange
    int value = 100;
    DewPointValue dewPointValue = new DewPointValue(value);

    // Act
    int actual = dewPointValue.hashCode();

    // Assert
    assertEquals(100, actual);
  }
}