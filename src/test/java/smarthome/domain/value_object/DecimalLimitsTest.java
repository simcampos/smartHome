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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test cases for the SetDecimalActuatorLimits class.
 */
class DecimalLimitsTest {

  /**
   * Verifies that SetDecimalActuatorLimits is correctly instantiated when limits are valid.
   */
  @Test
  void shouldInstantiateSetDecimalActuatorLimits_whenLimitsAreValid() {
    // Arrange
    double lowerLimit = 1.5;
    double upperLimit = 9.5;

    // Act
    DecimalLimits decimalLimits = new DecimalLimits(lowerLimit, upperLimit);

    // Assert
    assertNotNull(decimalLimits);
  }

  /**
   * Verifies that an IllegalArgumentException is thrown when the lower limit is greater than the
   * upper limit.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenLowerLimitIsGreaterThanUpperLimit() {
    // Arrange
    double lowerLimit = 9.5;
    double upperLimit = 1.5;

    String expectedMessage = "Lower limit cannot be greater than upper limit";

    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> new DecimalLimits(lowerLimit, upperLimit));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Verifies that the limits can be equal.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenUpperLimitIsEqualsThanLowerLimit() {
    // Arrange
    double lowerLimit = 1.5;
    double upperLimit = 1.5;
    // Act
    DecimalLimits decimalLimits = new DecimalLimits(lowerLimit, upperLimit);
    // Assert
    assertNotNull(decimalLimits);
  }

  /**
   * Verifies that getLowerLimit method returns the lower limit.
   */
  @Test
  void shouldReturnLowerLimit_whenGetLowerLimitIsCalled() {
    // Arrange
    double lowerLimit = 1.5;
    double upperLimit = 9.5;
    DecimalLimits decimalLimits = new DecimalLimits(lowerLimit, upperLimit);

    // Act
    double result = decimalLimits.getLowerLimit();

    // Assert
    assertEquals(lowerLimit, result);
  }

  /**
   * Verifies that getUpperLimit method returns the upper limit.
   */
  @Test
  void shouldReturnUpperLimit_whenGetUpperLimitIsCalled() {
    // Arrange
    double lowerLimit = 1.5;
    double upperLimit = 9.5;
    DecimalLimits decimalLimits = new DecimalLimits(lowerLimit, upperLimit);

    // Act
    double result = decimalLimits.getUpperLimit();

    // Assert
    assertEquals(upperLimit, result);
  }

  /**
   * Verifies that the equals method returns true when called with the same object.
   */
  @Test
  void shouldReturnTrue_whenEqualsIsCalledWithSameObject() {
    // Arrange
    double lowerLimit = 1.5;
    double upperLimit = 9.5;
    DecimalLimits decimalLimits = new DecimalLimits(lowerLimit, upperLimit);

    // Act
    boolean result = decimalLimits.equals(decimalLimits);

    // Assert
    assertTrue(result);
  }

  /**
   * Verifies that the equals method returns false when called with a different object.
   */
  @Test
  void shouldReturnFalse_whenEqualsIsCalledWithDifferentObject() {
    // Arrange
    double lowerLimit = 1.5;
    double upperLimit = 9.5;
    DecimalLimits decimalLimits = new DecimalLimits(lowerLimit, upperLimit);

    // Act
    boolean result = decimalLimits.equals(new Object());

    // Assert
    assertFalse(result);
  }

  /**
   * Verifies that the equals method returns false when called with a different set of limits.
   */
  @Test
  void shouldReturnFalse_whenEqualsIsCalledWithDifferentLimits() {
    // Arrange
    double lowerLimit = 1.5;
    double upperLimit = 9.5;
    DecimalLimits decimalLimits = new DecimalLimits(lowerLimit, upperLimit);
    DecimalLimits decimalLimits2 = new DecimalLimits(1.4, 9.5);

    // Act
    boolean result = decimalLimits.equals(decimalLimits2);

    // Assert
    assertFalse(result);
  }

  /**
   * Verifies that the equals method returns true when called with a different object having the
   * same limits.
   */
  @Test
  void shouldReturnTrue_whenEqualsIsCalledWithDifferentObjectButSameLimits() {
    // Arrange
    double lowerLimit = 1.5;
    double upperLimit = 9.5;
    DecimalLimits decimalLimits = new DecimalLimits(lowerLimit, upperLimit);
    DecimalLimits decimalLimits2 = new DecimalLimits(lowerLimit, upperLimit);

    // Act
    boolean result = decimalLimits.equals(decimalLimits2);

    // Assert
    assertTrue(result);
  }

  /**
   * Verifies that the equals method returns false when called with a different object having a
   * different lower limit.
   */
  @Test
  void shouldReturnFalse_whenEqualsIsCalledWithDifferentObjectAndDifferentLowerLimit() {
    // Arrange
    double lowerLimit = 1.5;
    double upperLimit = 9.5;
    DecimalLimits decimalLimits = new DecimalLimits(lowerLimit, upperLimit);
    DecimalLimits decimalLimits2 = new DecimalLimits(1.4, upperLimit);

    // Act
    boolean result = decimalLimits.equals(decimalLimits2);

    // Assert
    assertFalse(result);
  }

  /**
   * Verifies that the equals method returns false when called with a different object having a
   * different upper limit.
   */
  @Test
  void shouldReturnFalse_whenEqualsIsCalledWithDifferentObjectAndDifferentUpperLimit() {
    // Arrange
    double lowerLimit = 1.5;
    double upperLimit = 9.5;
    DecimalLimits decimalLimits = new DecimalLimits(lowerLimit, upperLimit);
    DecimalLimits decimalLimits2 = new DecimalLimits(lowerLimit, 9.4);

    // Act
    boolean result = decimalLimits.equals(decimalLimits2);

    // Assert
    assertFalse(result);
  }

  /**
   * Test if the toString method returns the expected string.
   */
  @Test
  void shouldReturnToString_whenToStringIsCalled() {
    // Arrange
    double lowerLimit = 1.5;
    double upperLimit = 9.5;
    DecimalLimits decimalLimits = new DecimalLimits(lowerLimit, upperLimit);

    String expected = "Lower limit: " + lowerLimit + ", Upper limit: " + upperLimit;

    // Act
    String result = decimalLimits.toString();

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldReturnHashCode_whenHashCodeIsCalled() {
    // Arrange
    double lowerLimit = 1.5;
    double upperLimit = 9.5;
    DecimalLimits decimalLimits = new DecimalLimits(lowerLimit, upperLimit);

    // Act
    int result = decimalLimits.hashCode();

    // Assert
    assertEquals(Double.hashCode(lowerLimit) + Double.hashCode(upperLimit), result);
  }

  @Test
  void shouldReturnTrue_whenEqualsIsTrueHashCodeShouldBeTheSame() {
    // Arrange
    double lowerLimit = 1.5;
    double upperLimit = 9.5;
    DecimalLimits decimalLimits = new DecimalLimits(lowerLimit, upperLimit);
    DecimalLimits decimalLimits2 = new DecimalLimits(lowerLimit, upperLimit);
    boolean expected = decimalLimits.equals(decimalLimits2);
    // Act
    boolean result = decimalLimits.hashCode() == decimalLimits2.hashCode();

    // Assert
    assertEquals(expected, result);
  }
}
