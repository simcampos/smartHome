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

class SensorTypeDescriptionTest {

  /**
   * Test the constructor
   */
  @Test
  void shouldInstantiateSensorTypeDescriptionWhenDescriptionIsValid() {
    //Arrange
    String description = "Temperature Sensor";
    //Act
    SensorTypeDescription sensorTypeDescription = new SensorTypeDescription(description);
    //Assert
    assertNotNull(sensorTypeDescription);
  }

  /**
   * Should throw IllegalArgumentException when description is null.
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenDescriptionIsNull() {
    //Arrange
    String description = null;
    String expectedMessage = "The value of 'description' should not null, blank, or empty.";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorTypeDescription(description));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should throw IllegalArgumentException when description is blank.
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenDescriptionIsBlank() {
    //Arrange
    String description = " ";
    String expectedMessage = "The value of 'description' should not null, blank, or empty.";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorTypeDescription(description));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should throw IllegalArgumentException when description has more than 50 characters.
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenDescriptionIsMoreThan50Characters() {
    //Arrange
    String description = "Temperature Sensor Temperature Sensor Temperature Sensor Temperature Sensor";
    String expectedMessage = "The description cannot have more than 50 characters.";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorTypeDescription(description));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should instantiate SensorTypeDescription when description has 50 characters.
   */
  @Test
  void shouldInstantiateSensorTypeDescriptionWhenDescriptionHas50Characters() {
    //Arrange
    String description = "This is a description with exactly 50 characters..";
    //Act
    SensorTypeDescription sensorTypeDescription = new SensorTypeDescription(description);
    //Assert
    assertNotNull(sensorTypeDescription);
  }

  /**
   * Should return description when getID is called.
   */
  @Test
  void shouldReturnDescriptionWhenGetIDIsCalled() {
    //Arrange
    String description = "Temperature Sensor";
    SensorTypeDescription sensorTypeDescription = new SensorTypeDescription(description);
    //Act
    String result = sensorTypeDescription.getID();
    //Assert
    assertEquals(description, result);
  }

  /**
   * Should return true when two sensor type description are equal.
   */
  @Test
  void shouldReturnTrueWhenTwoSensorTypeDescriptionAreEqual() {
    //Arrange
    String description = "Temperature Sensor";
    SensorTypeDescription sensorTypeDescription1 = new SensorTypeDescription(description);
    SensorTypeDescription sensorTypeDescription2 = new SensorTypeDescription(description);
    //Act
    boolean result = sensorTypeDescription1.equals(sensorTypeDescription2);
    //Assert
    assertTrue(result);
  }

  /**
   * Should return false when two sensor type description are not equal.
   */
  @Test
  void shouldReturnFalseWhenTwoSensorTypeDescriptionAreNotEqual() {
    //Arrange
    String description1 = "Temperature Sensor";
    String description2 = "Humidity Sensor";
    SensorTypeDescription sensorTypeDescription1 = new SensorTypeDescription(description1);
    SensorTypeDescription sensorTypeDescription2 = new SensorTypeDescription(description2);
    //Act
    boolean result = sensorTypeDescription1.equals(sensorTypeDescription2);
    //Assert
    assertFalse(result);
  }

  /**
   * Should return false when object is not sensor type description.
   */
  @Test
  void shouldReturnFalseWhenObjectIsNotSensorTypeDescription() {
    //Arrange
    String description = "Temperature Sensor";
    SensorTypeDescription sensorTypeDescription = new SensorTypeDescription(description);
    //Act
    boolean result = sensorTypeDescription.equals(new Object());
    //Assert
    assertFalse(result);
  }

  /**
   * Should return hashCode when hashCode is called.
   */
  @Test
  void shouldReturnHashCodeWhenHashCodeIsCalled() {
    //Arrange
    String description = "Temperature Sensor";
    SensorTypeDescription sensorTypeDescription = new SensorTypeDescription(description);
    //Act
    int result = sensorTypeDescription.hashCode();
    //Assert
    assertEquals(description.hashCode(), result);
  }
}