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

class SensorIDTest {

  /**
   * Tests the correct instantiation of a SensorID
   */
  @Test
  void shouldGetValidObject_whenUsingValidStringInConstructor() {
    // Arrange
    String sensorID = "SensorXPTO";

    // Act
    DeviceID result = new DeviceID(sensorID);

    // Assert
    assertNotNull(result);
  }

  /**
   * Tests if the exception is thrown with a null sensorID
   */
  @Test
  void shouldThrowException_whenSensorIdIsNull() {
    // Arrange
    String sensorID = null;
    String expectedMessage = "The value of 'sensorID' should not null, blank, or empty.";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new SensorID(sensorID)
    );

    // Assert
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests if the exception is thrown with a blank sensorID
   */
  @Test
  void shouldThrowException_whenSensorIdIsBlank() {
    // Arrange
    String sensorID = " ";
    String expectedMessage = "The value of 'sensorID' should not null, blank, or empty.";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new SensorID(sensorID)
    );

    // Assert
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests the ID getter
   */
  @Test
  void shouldGetSensorID() {
    // Arrange
    String idDescription = "HXPTO";
    SensorID sensorID = new SensorID(idDescription);

    String expected = "HXPTO";

    // Act
    String result = sensorID.getID();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Tests if a sensorID is equal to itself
   */
  @Test
  void shouldReturnTrue_whenSensorIdIsEqualToItself() {
    // Arrange
    String idDescription = "HXPTO";
    SensorID sensorID = new SensorID(idDescription);

    // Act
    boolean result = sensorID.equals(sensorID);

    // Assert
    assertTrue(result);
  }

  /**
   * Tests if a sensorID1 is equal to a sensorID2 if the ID of both is the same
   */
  @Test
  void shouldReturnTrue_whenSensorIdIsEqualToOtherSensorId() {
    // Arrange
    String idDescription = "HXPTO";
    SensorID sensorID1 = new SensorID(idDescription);
    SensorID sensorID2 = new SensorID(idDescription);

    // Act
    boolean result = sensorID1.equals(sensorID2);

    // Assert
    assertTrue(result);
  }

  /**
   * Tests if a sensorID1 is not equal to a sensorID2
   */
  @Test
  void shouldReturnTrue_whenSensorIdIsNotEqualToAnotherSensorId() {
    // Arrange
    String idDescription1 = "HXPTO";
    SensorID sensorID1 = new SensorID(idDescription1);

    String idDescription2 = "HRTHD";
    SensorID sensorID2 = new SensorID(idDescription2);

    // Act
    boolean result = sensorID1.equals(sensorID2);

    // Assert
    assertFalse(result);
  }

  /**
   * Tests if the sensorID is returned as an hashCode
   */
  @Test
  void shouldReturnHashCode() {
    // Arrange
    String idDescription = "HXPTO";
    SensorID sensorID = new SensorID(idDescription);

    int expected = idDescription.hashCode();

    // Act
    int result = sensorID.hashCode();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Tests if the sensorID is returned as a string
   */
  @Test
  void shouldReturnSensorID() {
    // Arrange
    String idDescription = "HXPTO";
    SensorID sensorID = new SensorID(idDescription);

    String expected = idDescription;

    // Act
    String result = sensorID.toString();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Tests if equals method returns false when the object is not an instance of SensorID
   */
  @Test
  void shouldReturnFalseWhenComparingSensorIDWithDifferentObject() {
    // Arrange
    String sensorID = "HXPTO";
    SensorID sensorIDObject = new SensorID(sensorID);

    // Act
    boolean result = sensorIDObject.equals(new Object());

    // Assert
    assertFalse(result);
  }
}
