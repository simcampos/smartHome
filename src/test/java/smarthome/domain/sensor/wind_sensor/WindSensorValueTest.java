/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.wind_sensor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindSensorValueTest {

  /**
   * Test if the constructor is properly created.
   */
  @Test
  void shouldInstantiateWindSensorValue() {
    // Arrange
    double speed = 10.5;
    double direction = 6;

    // Act
    WindSensorValue result = new WindSensorValue(speed, direction);

    // Assert
    assertNotNull(result);
  }

  /**
   * Test if the constructor throws exception when the speed is negative.
   */
  @Test
  void shouldThrowException_whenSpeedIsNegative() {
    // Arrange
    double speed = -10.5;
    double direction = 6;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> new WindSensorValue(speed, direction));
  }

  /**
   * Test if the constructor throws exception when the direction is negative.
   */
  @Test
  void shouldThrowException_whenDirectionIsNegative() {
    // Arrange
    double speed = 10.5;
    double direction = -20.5;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> new WindSensorValue(speed, direction));
  }

  /**
   * Test if the constructor throws exception when the direction is greater than 2 * Math.PI.
   */
  @Test
  void shouldThrowException_whenDirectionIsGreaterThan2Pi() {
    // Arrange
    double speed = 10.5;
    double direction = 20;

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> new WindSensorValue(speed, direction));
  }

  /**
   * Test if the getSpeed method returns the correct speed.
   */
  @Test
  void shouldReturnSpeed() {
    // Arrange
    double speed = 10.5;
    double direction = 6;

    WindSensorValue windSensorValue = new WindSensorValue(speed, direction);

    // Act
    double result = windSensorValue.getSpeed();

    // Assert
    assertEquals(speed, result);
  }

  /**
   * Test if the getDirection method returns the correct direction.
   */
  @Test
  void shouldReturnDirection() {
    // Arrange
    double speed = 10.5;
    double direction = 6;

    WindSensorValue windSensorValue = new WindSensorValue(speed, direction);

    // Act
    double result = windSensorValue.getDirection();

    // Assert
    assertEquals(direction, result);
  }

  /**
   * Test if the equals method returns true when the objects are equal.
   */
  @Test
  void shouldReturnTrue_whenObjectsAreEqual() {
    // Arrange
    double speed = 10.5;
    double direction = 6;

    WindSensorValue windSensorValue1 = new WindSensorValue(speed, direction);
    WindSensorValue windSensorValue2 = new WindSensorValue(speed, direction);

    // Act
    boolean result = windSensorValue1.equals(windSensorValue2);

    // Assert
    assertTrue(result);
  }

  /**
   * Test if the equals method returns false when the objects are not equal.
   */
  @Test
  void shouldReturnFalse_whenObjectsAreNotEqual() {
    // Arrange
    double speed = 10.5;
    double direction = 6;

    WindSensorValue windSensorValue = new WindSensorValue(speed, direction);
    WindSensorValue other = new WindSensorValue(3, 3);

    // Act
    boolean result = windSensorValue.equals(other);

    // Assert
    assertFalse(result);
  }

  /**
   * Test if the equals method returns false when the object is not a WindSensorValue.
   */
  @Test
  void shouldReturnFalse_whenObjectsAreNotWindSensorValue() {
    // Arrange
    double speed = 10.5;
    double direction = 6;

    WindSensorValue windSensorValue = new WindSensorValue(speed, direction);

    // Act
    boolean result = windSensorValue.equals(new Object());

    // Assert
    assertFalse(result);
  }

  /**
   * Test if the hashCodes of two different objects are the same.
   */
  @Test
  void equalsObjectsShouldReturnTheSameHash() {
    // Arrange
    double speed = 10.5;
    double direction = 6;

    WindSensorValue windSensorValue = new WindSensorValue(speed, direction);
    WindSensorValue windSensorValue2 = new WindSensorValue(speed, direction);

    boolean expected = windSensorValue.equals(windSensorValue2);

    // Act
    boolean result = windSensorValue.hashCode() == windSensorValue2.hashCode();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Test if the hashCodes of two equal objects are equal.
   */
  @Test
  void shouldReturnHashCode_WhenHashCodeIsCalled() {
    // Arrange
    double speed = 10.5;
    double direction = 6;

    WindSensorValue windSensorValue = new WindSensorValue(speed, direction);
    WindSensorValue windSensorValue2 = new WindSensorValue(speed, direction);

    // Act
    int result = windSensorValue.hashCode();
    int result2 = windSensorValue2.hashCode();

    // Assert
    assertEquals(result, result2);
  }

  /**
   * Test if the hashCodes of two different objects are different.
   */
  @Test
  void shouldReturnNotEquals_WhenHashCodeCalledIsDifferent() {
    // Arrange
    WindSensorValue windSensorValue = new WindSensorValue(10.5, 3);
    int expected = Double.hashCode(10.5) - Double.hashCode(4);
    // Act
    int result = windSensorValue.hashCode();

    // Assert
    assertNotEquals(expected, result);
  }

  /**
   * Test if the hashCodes of two different objects are different, when one is zero.
   */
  @Test
  void shouldNotReturnZero_WhenValuesAreNonZero() {
    // Arrange
    WindSensorValue windSensorValue = new WindSensorValue(10.5, 3);

    // Act
    int hashCode = windSensorValue.hashCode();

    // Assert
    assertNotEquals(0, hashCode);
  }
}
