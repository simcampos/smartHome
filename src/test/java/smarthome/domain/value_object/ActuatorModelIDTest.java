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

class ActuatorModelIDTest {

  /**
   * Validates construction with valid arguments.
   */
  @Test
  void shouldGetValidObject_whenUsingValidStringInConstructor() {
    // Arrange
    String actuatorModelIDDescription = "switch";

    // Act
    ActuatorModelID result = new ActuatorModelID(actuatorModelIDDescription);

    // Assert
    assertNotNull(result);
  }

  /**
   * Should throw IllegalArgumentException when actuatorModelID description is null.
   */
  @Test
  void shouldThrowException_whenActuatorModelIDIsNull() {
    // Arrange
    String actuatorModelIDDescription = null;
    String expectedMessage = "The value of 'actuatorModelID' should not null, blank, or empty.";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new ActuatorModelID(actuatorModelIDDescription)
    );

    // Assert
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Should throw IllegalArgumentException when actuatorModelID description is blank.
   */
  @Test
  void shouldThrowException_whenActuatorModelIDIsBlank() {
    // Arrange
    String actuatorModelIDDescription = " ";
    String expectedMessage = "The value of 'actuatorModelID' should not null, blank, or empty.";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new ActuatorModelID(actuatorModelIDDescription)
    );

    // Assert
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Should return actuator model ID.
   */
  @Test
  void shouldReturnActuatorModelID() {
    // Arrange
    String actuatorModelIDDescription = "switch";
    ActuatorModelID actuatorModelID = new ActuatorModelID(actuatorModelIDDescription);
    // Act
    String result = actuatorModelID.getID();
    // Assert
    assertEquals(actuatorModelIDDescription, result);
  }

  /**
   * Should return true when actuator model ID is equal to itself.
   */
  @Test
  void shouldReturnTrue_WhenActuatorModelIDIsEqualToItself() {
    // Arrange
    String actuatorModelIDDescription = "switch";
    ActuatorModelID actuatorModelID = new ActuatorModelID(actuatorModelIDDescription);
    // Act
    boolean result = actuatorModelID.equals(actuatorModelID);
    // Assert
    assertTrue(result);
  }

  /**
   * Should return false when actuator model ID is different to another actuator model ID.
   */
  @Test
  void shouldReturnFalse_whenComparingTwoDifferentActuatorModelID() {
    // Arrange
    String actuatorModelIDDescription = "switch";
    ActuatorModelID actuatorModelID = new ActuatorModelID(actuatorModelIDDescription);
    ActuatorModelID actuatorModelID2 = new ActuatorModelID("light");
    // Act
    boolean result = actuatorModelID.equals(actuatorModelID2);
    // Assert
    assertFalse(result);
  }

  /**
   * Should return false when comparing actuator model ID to different object.
   */
  @Test
  void shouldReturnFalse_whenComparingActuatorModelIDToDifferentObject() {
    // Arrange
    String actuatorModelIDDescription = "switch";
    ActuatorModelID actuatorModelID = new ActuatorModelID(actuatorModelIDDescription);
    // Act
    boolean result = actuatorModelID.equals(new Object());
    // Assert
    assertFalse(result);
  }

  /**
   * Should return hashCode.
   */
  @Test
  void shouldReturnHashCode() {
    // Arrange
    String actuatorModelIDDescription = "switch";
    ActuatorModelID actuatorModelID = new ActuatorModelID(actuatorModelIDDescription);
    // Act
    int result = actuatorModelID.hashCode();
    // Assert
    assertEquals(actuatorModelIDDescription.hashCode(), result);
  }

  /**
   * Should return false when comparing actuator model ID to null.
   */
  @Test
  void shouldReturnFalse_whenComparingActuatorModelIDToNull() {
    // Arrange
    String actuatorModelIDDescription = "switch";
    ActuatorModelID actuatorModelID = new ActuatorModelID(actuatorModelIDDescription);
    // Act
    boolean result = actuatorModelID.equals(null);
    // Assert
    assertFalse(result);
  }
}