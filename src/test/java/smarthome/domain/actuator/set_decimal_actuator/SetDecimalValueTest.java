/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator.set_decimal_actuator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for the SetDecimalValue class.
 */
class SetDecimalValueTest {

  /**
   * Verifies that SetDecimalValue is correctly instantiated.
   */
  @Test
  void shouldInstantiateSetDecimalValue() {
    // Act
    SetDecimalValue setDecimalValue = new SetDecimalValue(1.5);

    // Assert
    assertNotNull(setDecimalValue);
  }

  /**
   * Verifies that the toString method returns the expected string representation of the decimal
   * value.
   */
  @Test
  void shouldReturnStringValue() {
    // Arrange
    double value = 1.5;
    SetDecimalValue setDecimalValue = new SetDecimalValue(value);
    String expected = "1.5";

    // Act
    String result = setDecimalValue.toString();

    // Assert
    assertEquals(expected, result);
  }


  /**
   * Verifies that the getValue method returns the expected decimal value.
   */
  @Test
  void shouldGetValue() {
    // Arrange
    double value = 1.5;
    SetDecimalValue setDecimalValue = new SetDecimalValue(value);

    // Act
    double result = setDecimalValue.getValue();

    // Assert
    assertEquals(value, result);
  }

  /**
   * Verifies that the equals method returns true when comparing two SetDecimalValue objects with
   * the same value.
   */
  @Test
  void shouldReturnTrueWhenComparingEqualSetDecimalValueObjects() {
    // Arrange
    double value = 1.5;
    SetDecimalValue setDecimalValue1 = new SetDecimalValue(value);
    SetDecimalValue setDecimalValue2 = new SetDecimalValue(value);

    // Act
    boolean result = setDecimalValue1.equals(setDecimalValue2);

    // Assert
    assertTrue(result);
  }


  /**
   * Verifies that the equals method returns false when comparing two SetDecimalValue objects with
   * different values.
   */
  @Test
  void shouldReturnFalseWhenComparingDifferentSetDecimalValueObjects() {
    // Arrange
    double value1 = 0.001;
    double value2 = 0.002;
    SetDecimalValue setDecimalValue1 = new SetDecimalValue(value1);
    SetDecimalValue setDecimalValue2 = new SetDecimalValue(value2);

    // Act
    boolean result = setDecimalValue1.equals(setDecimalValue2);

    // Assert
    assertFalse(result);
  }

  /**
   * Verifies that the equals method returns false when comparing a SetDecimalValue object with a
   * different object type.
   */
  @Test
  void shouldReturnFalseWhenComparingSetDecimalValueWithDifferentObjectType() {
    // Arrange
    double value = 1.5;
    SetDecimalValue setDecimalValue = new SetDecimalValue(value);
    Object object = new Object();

    // Act
    boolean result = setDecimalValue.equals(object);

    // Assert
    assertFalse(result);
  }

  /**
   * Verifies that the hashCode method returns the expected hash code of the decimal value.
   */
  @Test
  void shouldReturnHashCode() {
    // Arrange
    double value = 1.5;
    SetDecimalValue setDecimalValue = new SetDecimalValue(value);
    int expected = Double.hashCode(value);

    // Act
    int result = setDecimalValue.hashCode();

    // Assert
    assertEquals(expected, result);
  }


}