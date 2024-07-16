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

class SensorTypeIDTest {

  /**
   * Tests the correct instantiation of a SensorTypeID.
   */
  @Test
  void shouldGetValidObject_whenUsingValidStringInConstructor() {
    // Arrange
    String sensorTypeIDDescription = "Sensor2GKA";
    // Act
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDDescription);
    // Assert
    assertNotNull(sensorTypeID);
  }

  /**
   * Tests if the exception is thrown with a null sensorTypeID.
   */
  @Test
  void shouldThrowException_whenSensorTypeIdIsNull() {
    // Arrange
    String sensorTypeIDDescription = null;
    String expectedMessage = "'sensorTypeID' must be a non-empty string.";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new SensorTypeID(sensorTypeIDDescription)
    );

    // Assert
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }


  /**
   * Tests if the exception is thrown with a blank sensorTypeID.
   */
  @Test
  void shouldThrowException_whenSensorTypeIdIsBlank() {
    // Arrange
    String sensorTypeIDDescription = " ";
    String expectedMessage = "'sensorTypeID' must be a non-empty string.";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new SensorTypeID(sensorTypeIDDescription)
    );

    // Assert
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests if the exception is thrown with an empty sensorTypeID.
   */
  @Test
  void shouldThrowException_whenSensorTypeIdIsEmpty() {
    // Arrange
    String sensorTypeIDDescription = "";
    String expectedMessage = "'sensorTypeID' must be a non-empty string.";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new SensorTypeID(sensorTypeIDDescription)
    );

    // Assert
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests if the sensor type ID is returned correctly.
   */
  @Test
  void shouldReturnSensorTypeID() {
    //Arrange
    String sensorTypeIDDescription = "Sensor2GKA";
    SensorTypeID sensorTypeIDObject = new SensorTypeID(sensorTypeIDDescription);

    String expected = "Sensor2GKA";

    //Act
    String result = sensorTypeIDObject.getID();

    //Assert
    assertEquals(expected, result);
  }

  /**
   * Tests if the equals method returns true when the sensor type ID is compared to itself.
   */
  @Test
  void shouldReturnTrue_WhenSensorTypeIDIsEqualToItself() {
    // Arrange
    String sensorTypeIDDescription = "Sensor2GKA";
    SensorTypeID sensorTypeID1 = new SensorTypeID(sensorTypeIDDescription);

    // Act
    boolean result = sensorTypeID1.equals(sensorTypeID1);

    // Assert
    assertTrue(result);
  }

  /**
   * Tests if the equals method returns true when the sensor type ID is compared to another sensor
   * type ID with the same ID.
   */
  @Test
  void shouldReturnTrue_WhenSensorTypeIDIsEqualToOtherSensorTypeId() {
    //Arrange
    String sensorTypeIDDescription = "Sensor2GKA";
    SensorTypeID sensorTypeID1 = new SensorTypeID(sensorTypeIDDescription);
    SensorTypeID sensorTypeID2 = new SensorTypeID(sensorTypeIDDescription);

    //act
    boolean result = sensorTypeID1.equals(sensorTypeID2);

    //Assert
    assertTrue(result);

  }

  /**
   * Tests if the equals method returns false when the sensor type ID is compared to another sensor
   * type ID with a different ID.
   */
  @Test
  void shouldReturnFalse_WhenSensorTypeIDisDifferentFromOtherSensorTYpeID() {
    //Arrange
    String sensorTypeIDDescription = "Sensor2AVB";
    String sensorTypeID2Description = "Sensor2PDC";

    SensorTypeID sensorTypeID1 = new SensorTypeID(sensorTypeIDDescription);
    SensorTypeID sensorTypeID2 = new SensorTypeID(sensorTypeID2Description);

    //Act
    boolean result = sensorTypeID1.equals(sensorTypeID2);

    //Assert
    assertFalse(result);

  }

  /**
   * Tests if the hashcode is returned correctly.
   */
  @Test
  void shouldReturnHashCode() {
    //Arrange
    String sensorTypeIDDescription = "SensorAVB";

    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDDescription);

    int expected = sensorTypeIDDescription.hashCode();

    //Act
    int result = sensorTypeID.hashCode();

    //Assert
    assertEquals(expected, result);
  }

  /**
   * Tests if the sensorTypeID object returns correct string representation.
   */
  @Test
  void shouldReturnSensorTypeIDInString() {
    // Arrange
    String sensorTypeIDDescription = "Sensor2GKA";

    String expected = "Sensor2GKA";

    // Act
    SensorTypeID sensorTypeIDObject = new SensorTypeID(sensorTypeIDDescription);

    // Assert
    assertEquals(expected, sensorTypeIDObject.toString());
  }

  /**
   * Tests if equals method returns false when the object is not an instance of SensorTypeID
   */
  @Test
  void shouldReturnFalseWhenComparingSensorTypeIDWithDifferentObject() {
    // Arrange
    String sensorTypeID = "Sensor2GKA";
    SensorTypeID sensorTypeIDObject = new SensorTypeID(sensorTypeID);

    // Act
    boolean result = sensorTypeIDObject.equals(new Object());

    // Assert
    assertFalse(result);
  }
}