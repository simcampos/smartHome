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


class IntegerLimitsTest {

  /**
   * Tests if the object is instantiated when the limits are valid
   */
  @Test
  void shouldInstantiateSetIntegerActuatorLimits_whenLimitsAreValid() {
    // Arrange
    int lowerLimit = 1;
    int upperLimit = 100;

    // Act
    IntegerLimits result = new IntegerLimits(lowerLimit, upperLimit);

    // Assert
    assertNotNull(result);
  }

  /**
   * Tests if the object is instantiated when the limits are invalid
   */
  @Test
  void shouldThrowIllegalArgumentException_whenLimitsAreInvalid() {
    // Arrange
    int lowerLimit = 100;
    int upperLimit = 0;
    String expectedMessage = "Lower limit cannot be greater than upper limit";
    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new IntegerLimits(lowerLimit, upperLimit));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());

  }

  /**
   * Tests getLowerLimit
   */
  @Test
  void shouldReturnLowerLimit_whenGetLowerLimitIsCalled() {
    // Arrange
    int lowerLimit = 1;
    int upperLimit = 100;
    IntegerLimits integerLimits = new IntegerLimits(lowerLimit, upperLimit);

    // Act
    int result = integerLimits.getLowerLimit();

    // Assert
    assertEquals(result, lowerLimit);
  }

  /**
   * Tests getUpperLimit
   */
  @Test
  void shouldReturnUpperLimit_whenGetUpperLimitIsCalled() {
    // Arrange
    int lowerLimit = 1;
    int upperLimit = 100;
    IntegerLimits integerLimits = new IntegerLimits(lowerLimit, upperLimit);

    // Act
    int result = integerLimits.getUpperLimit();

    // Assert
    assertEquals(result, upperLimit);
  }

  /**
   * Tests equals when the object is the same
   */
  @Test
  void shouldReturnTrue_whenEqualsIsCalledWithSameObject() {
    // Arrange
    int lowerLimit = 1;
    int upperLimit = 100;
    IntegerLimits integerLimits = new IntegerLimits(lowerLimit, upperLimit);

    // Act
    boolean result = integerLimits.equals(integerLimits);

    // Assert
    assertTrue(result);
  }

  /**
   * Tests equals when the object is different
   */
  @Test
  void shouldReturnFalse_whenEqualsIsCalledWithDifferentObject() {
    // Arrange
    int lowerLimit = 0;
    int upperLimit = 100;
    IntegerLimits integerLimits = new IntegerLimits(lowerLimit, upperLimit);

    // Act
    boolean result = integerLimits.equals(new Object());

    // Assert
    assertFalse(result);
  }

  /**
   * Tests equals when the object is different but the limits are the same
   */
  @Test
  void shouldReturnTrue_whenEqualsIsCalledWithDifferentObjectButSameLimits() {
    // Arrange
    int lowerLimit = 0;
    int upperLimit = 100;
    IntegerLimits integerLimits = new IntegerLimits(lowerLimit, upperLimit);
    IntegerLimits integerLimits2 = new IntegerLimits(lowerLimit, upperLimit);

    // Act
    boolean result = integerLimits.equals(integerLimits2);

    // Assert
    assertTrue(result);
  }

  /**
   * Tests equals when the object is different and the lower limit is different
   */
  @Test
  void shouldReturnFalse_whenEqualsIsCalledWithDifferentObjectAndDifferentLowerLimit() {
    // Arrange
    int lowerLimit1 = 0;
    int lowerLimit2 = 1;
    int upperLimit = 100;
    IntegerLimits integerLimits = new IntegerLimits(lowerLimit1, upperLimit);
    IntegerLimits integerLimits2 = new IntegerLimits(lowerLimit2, upperLimit);

    // Act
    boolean result = integerLimits.equals(integerLimits2);

    // Assert
    assertFalse(result);
  }

  /**
   * Tests equals when the object is different and the upper limit is different
   */
  @Test
  void shouldReturnFalse_whenEqualsIsCalledWithDifferentObjectAndDifferentUpperLimit() {
    // Arrange
    int lowerLimit = 0;
    int upperLimit1 = 100;
    int upperLimit2 = 101;
    IntegerLimits integerLimits = new IntegerLimits(lowerLimit, upperLimit1);
    IntegerLimits integerLimits2 = new IntegerLimits(lowerLimit, upperLimit2);

    // Act
    boolean result = integerLimits.equals(integerLimits2);

    // Assert
    assertFalse(result);
  }

  @Test
  void shouldReturnTrueForHashCode_whenTwoObjectsAreEqual() {
    // Arrange
    int lowerLimit = 1;
    int upperLimit = 100;
    IntegerLimits integerLimits = new IntegerLimits(lowerLimit, upperLimit);
    IntegerLimits integerLimits2 = new IntegerLimits(lowerLimit, lowerLimit);

    boolean expected = integerLimits.equals(integerLimits2);

    // Act
    boolean result = integerLimits.hashCode() == integerLimits2.hashCode();
    // Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldReturnNotEquals_WhenTwoObjectsHaveDifferentHashCodes() {
    // Arrange
    int lowerLimit = 1;
    int upperLimit = 100;
    IntegerLimits integerLimits = new IntegerLimits(lowerLimit, upperLimit);
    IntegerLimits integerLimits2 = new IntegerLimits(lowerLimit, upperLimit);

    // Act
    int expected = Integer.hashCode(lowerLimit) - Integer.hashCode(upperLimit);
    int result = integerLimits2.hashCode();
    // Assert
    assertNotEquals(expected, result);
  }
}
