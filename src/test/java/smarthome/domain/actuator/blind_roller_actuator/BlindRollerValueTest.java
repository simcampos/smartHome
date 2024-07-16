/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator.blind_roller_actuator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


class BlindRollerValueTest {

  /**
   * Test constructor
   */
  @Test
  void shouldCreateBlindRollerValue() {
    // Arrange
    int value = 100;

    // Act
    BlindRollerValue blindRollerValue = new BlindRollerValue(value);

    // Assert
    assertNotNull(blindRollerValue);
  }

  /**
   * Test constructor
   */
  @Test
  void shouldCreateBlindRollerWithValueZero() {
    // Arrange
    int value = 0;

    // Act
    BlindRollerValue blindRollerValue = new BlindRollerValue(value);

    // Assert
    assertNotNull(blindRollerValue);
  }

  /**
   * Throws exception if value is less than 0
   */
  @Test
  void shouldThrowException_WhenValueIsLessThanZero() {
    // Arrange
    int value = -1;

    // Act + Assert
    assertThrows(IllegalArgumentException.class, () -> new BlindRollerValue(value));
  }

  /**
   * Throws exception if value is greater than 100
   */
  @Test
  void shouldThrowException_WhenValueIsGreaterThanHundred() {
    // Arrange
    int value = 101;

    // Act + Assert
    assertThrows(IllegalArgumentException.class, () -> new BlindRollerValue(value));
  }

  /**
   * Test for method toString
   */
  @Test
  void shouldReturnStringValue() {
    // Arrange
    int value = 1;
    BlindRollerValue blindRollerValue = new BlindRollerValue(value);

    String expected = "1";

    // Act
    String result = blindRollerValue.toString();

    // Assert
    assertEquals(expected, result);
  }


}
