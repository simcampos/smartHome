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

class ActuatorIDTest {

  /**
   * Tests the correct instantiation of a ActuatorID.
   */
  @Test
  void shouldGetValidObject_whenUsingValidStringInConstructor() {
    // Arrange
    String actuatorIDDescription = "Actuator2GKA";

    // Act
    ActuatorID result = new ActuatorID(actuatorIDDescription);

    // Assert
    assertNotNull(result);
  }

  /**
   * Tests if the exception is thrown with a null actuatorID.
   */
  @Test
  void shouldThrowException_whenActuatorIdIsNull() {
    // Arrange
    String actuatorIDDescription = null;
    String expectedMessage = "'actuatorID' must be a non-empty string.";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new ActuatorID(actuatorIDDescription)
    );

    // Assert
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests if the exception is thrown with a blank actuatorID.
   */
  @Test
  void shouldThrowException_whenActuatorIdIsBlank() {
    // Arrange
    String actuatorIDDescription = " ";
    String expectedMessage = "'actuatorID' must be a non-empty string.";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new ActuatorID(actuatorIDDescription)
    );

    // Assert
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests if the ActuatorID is correctly returned.
   */
  @Test
  void shouldReturnActuatorID() {
    // Arrange
    String actuatorIDDescription = "Actuator2GKA";
    ActuatorID actuatorID = new ActuatorID(actuatorIDDescription);

    String expected = "Actuator2GKA";

    // Act
    String actuatorIDReturned = actuatorID.getID();

    // Assert
    assertEquals(expected, actuatorIDReturned);
  }

  /**
   * Tests if the equals method returns true when the ActuatorID is compared to itself.
   */
  @Test
  void shouldReturnTrue_WhenActuatorIDIsEqualToItself() {
    // Arrange
    String actuatorIDDescription = "Actuator2GKA";
    ActuatorID actuatorID = new ActuatorID(actuatorIDDescription);

    // Act
    boolean result = actuatorID.equals(actuatorID);

    // Assert
    assertTrue(result);
  }

  /**
   * Tests if the equals method returns true when the ActuatorID is compared to another ActuatorID
   * with the same ID.
   */
  @Test
  void shouldReturnTrue_WhenActuatorIDIsEqualToOtherActuatorID() {
    // Arrange
    String actuatorIDDescription = "Actuator2GKA";
    ActuatorID actuatorID1 = new ActuatorID(actuatorIDDescription);
    ActuatorID actuatorID2 = new ActuatorID(actuatorIDDescription);

    // Act
    boolean result = actuatorID1.equals(actuatorID2);

    // Assert
    assertTrue(result);
  }

  /**
   * Tests if the equals method returns false when the ActuatorID is compared to another ActuatorID
   * with a different ID.
   */
  @Test
  void shouldReturnFalse_WhenActuatorIDIsNotEqualToOtherActuatorID() {
    // Arrange
    String actuatorIDDescription1 = "Actuator2GKA";
    String actuatorIDDescription2 = "Actuator2GKB";

    ActuatorID actuatorID1 = new ActuatorID(actuatorIDDescription1);
    ActuatorID actuatorID2 = new ActuatorID(actuatorIDDescription2);

    // Act
    boolean result = actuatorID1.equals(actuatorID2);

    // Assert
    assertFalse(result);
  }

  /**
   * Tests if the hashCode method returns the same value for two ActuatorID with the same ID.
   */
  @Test
  void shouldReturnHashCode() {
    // Arrange
    String actuatorIDDescription = "Actuator2GKA";
    ActuatorID actuatorID = new ActuatorID(actuatorIDDescription);

    int expected = actuatorIDDescription.hashCode();

    // Act
    int result = actuatorID.hashCode();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Tests if the toString method returns the ActuatorID in a string.
   */
  @Test
  void shouldReturnActuatorIdInString() {
    // Arrange
    String actuatorIDDescription = "Actuator2GKA";
    ActuatorID actuatorID = new ActuatorID(actuatorIDDescription);

    String expected = "ActuatorID: Actuator2GKA";

    // Act
    String actuatorIDReturned = actuatorID.toString();

    // Assert
    assertEquals(expected, actuatorIDReturned);
  }

  /**
   * Test equals method with different object.
   */
  @Test
  void shouldReturnFalse_WhenObjectIsDifferent() {
    // Arrange
    String actuatorIDDescription = "Actuator2GKA";
    ActuatorID actuatorID = new ActuatorID(actuatorIDDescription);
    Object obj = new Object();
    // Act
    boolean result = actuatorID.equals(obj);
    // Assert
    assertFalse(result);
  }


}