/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ActuatorTypeIDTest {

  /**
   * Tests the correct instantiation of a ActuatorTypeID.
   */
  @Test
  void shouldGetValidObject_whenUsingValidStringInConstructor() {
    // Arrange
    String actuatorTypeIDDescription = "switch";

    // Act
    ActuatorTypeID result = new ActuatorTypeID(actuatorTypeIDDescription);

    // Assert
    assertNotNull(result);
  }

  @Test
  void shouldThrowException_whenActuatorTypeIDIsNull() {
    // Arrange
    String actuatorIDDescription = null;
    String expectedMessage = "The value of 'actuatorTypeID' should not null, blank, or empty.";

    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class, () -> new ActuatorTypeID(actuatorIDDescription));

    // Assert
    String actualMessage = exception.getMessage();

    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests if the exception is thrown with a blank actuatorTypeID.
   */
  @Test
  void shouldThrowException_whenActuatorTypeIDIsBlank() {
    // Arrange
    String actuatorTypeIDDescription = " ";
    String expectedMessage = "The value of 'actuatorTypeID' should not null, blank, or empty.";

    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class, () -> new ActuatorTypeID(actuatorTypeIDDescription));

    // Assert
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests if the ActuatorTypeID is correctly returned.
   */
  @Test
  void shouldReturnActuatorTypeID() {
    // Arrange
    String actuatorTypeIDDescription = "switch";
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDDescription);

    String expected = "switch";

    // Act
    String actuatorIDReturned = actuatorTypeID.getID();

    // Assert
    Assertions.assertEquals(expected, actuatorIDReturned);
  }

  /**
   * Tests if the equals method returns true when the ActuatorTypeID is compared to itself.
   */
  @Test
  void shouldReturnTrue_WhenActuatorTypeIDIsEqualToItself() {
    // Arrange
    String actuatorTypeIDDescription = "switch";
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDDescription);

    // Act
    boolean result = actuatorTypeID.equals(actuatorTypeID);

    // Assert
    Assertions.assertTrue(result);
  }

  /**
   * Tests if the equals method returns true when the ActuatorTypeID is compared to another
   * ActuatorTypeID with the same ID.
   */
  @Test
  void shouldReturnTrue_WhenActuatorIDIsEqualToOtherActuatorTypeID() {
    // Arrange
    String actuatorIDDescription = "switch";
    ActuatorTypeID actuatorTypeID1 = new ActuatorTypeID(actuatorIDDescription);
    ActuatorTypeID actuatorTypeID2 = new ActuatorTypeID(actuatorIDDescription);

    // Act
    boolean result = actuatorTypeID1.equals(actuatorTypeID2);

    // Assert
    Assertions.assertTrue(result);
  }

  /**
   * Tests if the equals method returns false when the ActuatorTypeID is compared to another
   * ActuatorID with a different ID.
   */
  @Test
  void shouldReturnFalse_WhenActuatorIDIsNotEqualToOtherActuatorTypeID() {
    // Arrange
    String actuatorTypeIDDescription1 = "switchA";
    String actuatorTypeIDDescription2 = "switchB";

    ActuatorTypeID actuatorTypeID1 = new ActuatorTypeID(actuatorTypeIDDescription1);
    ActuatorTypeID actuatorTypeID2 = new ActuatorTypeID(actuatorTypeIDDescription2);

    // Act
    boolean result = actuatorTypeID1.equals(actuatorTypeID2);

    // Assert
    Assertions.assertFalse(result);
  }

  /**
   * Tests if the hashCode method returns the same value for two ActuatorID with the same ID.
   */
  @Test
  void shouldReturnHashCode() {
    // Arrange
    String actuatorTypeIDDescription = "switch";
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDDescription);

    int expected = actuatorTypeIDDescription.hashCode();

    // Act
    int result = actuatorTypeID.hashCode();

    // Assert
    Assertions.assertEquals(expected, result);
  }

  /**
   * Should return false when an object is null.
   */
  @Test
  void shouldReturnFalse_WhenOneObjectIsNull() {
    // Arrange
    String actuatorTypeIDDescription = "switch";
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDDescription);

    // Act
    boolean result = actuatorTypeID.equals(null);

    // Assert
    Assertions.assertFalse(result);
  }

  @Test
  void shouldReturnActuatorTypeID_WhenToStringIsCalled() {
    // Arrange
    String actuatorTypeIDDescription = "switch";
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDDescription);

    String expected = "switch";

    // Act
    String result = actuatorTypeID.toString();

    // Assert
    Assertions.assertEquals(expected, result);
  }
}
