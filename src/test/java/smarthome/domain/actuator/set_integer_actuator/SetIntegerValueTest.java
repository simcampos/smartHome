/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator.set_integer_actuator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SetIntegerValueTest {

  /**
   * Test for SetIntegerValue instantiation
   */
  @Test
  void shouldInstantiateSetIntegerValue() {
    // Arrange
    int value = 1;

    // Act
    SetIntegerValue result = new SetIntegerValue(value);

    // Assert
    assertNotNull(result);
  }

  /**
   * Test for method toString
   */
  @Test
  void shouldReturnStringValue() {
    // Arrange
    int value = 1;
    SetIntegerValue setIntegerValue = new SetIntegerValue(value);

    String expected = "1";

    // Act
    String result = setIntegerValue.toString();

    // Assert
    assertEquals(result, expected);
  }

  /**
   * Test method equals when the instance is compared to itself.
   */
  @Test
  void shouldReturnTrue_whenComparedToItself() {
    // Arrange
    int value = 1;
    SetIntegerValue setIntegerValue = new SetIntegerValue(value);

    // Act
    boolean result = setIntegerValue.equals(setIntegerValue);

    // Assert
    assertTrue(result);
  }

  /**
   * Test of method equals when the instances are not equal.
   */
  @Test
  void shouldReturnFalse_whenInstancesAreNotEqual() {
    // Arrange
    int value1 = 1;
    int value2 = 2;
    SetIntegerValue setIntegerValue1 = new SetIntegerValue(value1);
    SetIntegerValue setIntegerValue2 = new SetIntegerValue(value2);

    // Act
    boolean result = setIntegerValue1.equals(setIntegerValue2);

    // Assert
    assertFalse(result);
  }

  /**
   * Test of method equals when the instance is compared to an object of a different class.
   */
  @Test
  void shouldReturnFalse_whenComparedWithDifferentClass() {
    // Arrange
    int value = 1;
    SetIntegerValue setIntegerValue = new SetIntegerValue(value);

    // Act
    boolean result = setIntegerValue.equals(new Object());

    // Assert
    assertFalse(result);
  }

  /**
   * Tests return of hashcode
   */
  @Test
  void shouldReturnTheSameHashCode_whenEqualsObjects() {
    // Arrange
    int value = 1;
    SetIntegerValue setIntegerValue = new SetIntegerValue(value);

    int expected = Integer.hashCode(value);

    // Act
    int result = setIntegerValue.hashCode();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Test if the hash code is 0 when the value is 0
   */
  @Test
  void shouldReturnHashCode_whenValueIsZero() {
    // Arrange
    int value = 0;

    SetIntegerValue setIntegerValue = new SetIntegerValue(value);

    int expected = 0;

    // Act
    int result = setIntegerValue.hashCode();

    // Assert
    assertEquals(expected, result);
  }
}
