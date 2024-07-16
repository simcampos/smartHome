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

class ActuatorNameTest {

  /**
   * Test the instantiation of a new actuator name.
   */
  @Test
  void shouldInstantiateANewActuatorName() {
    //Arrange
    String actuatorName = "Switch Actuator";

    //Act
    ActuatorName result = new ActuatorName(actuatorName);

    //Assert
    assertNotNull(result);
  }


  /**
   * Test the instantiation of a new actuator name with a blank value.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenActuatorNameIsBlank() {

    String actuatorName = " ";
    String expectedMessage = "The actuator name cannot be null, blank, or empty.";

    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new ActuatorName(actuatorName)
    );

    String exceptionMessage = exception.getMessage();

    assertTrue(exceptionMessage.contains(expectedMessage));
  }


  /**
   * Test the instantiation of a new actuator name with special characters.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenActuatorNameContainsSpecialCharacters() {

    String actuatorName = "Switch Actuator!";
    String expectedMessage = "The actuator name can only contain letters and numbers.";

    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new ActuatorName(actuatorName)
    );

    String exceptionMessage = exception.getMessage();

    assertTrue(exceptionMessage.contains(expectedMessage));
  }

  /**
   * Test the instantiation of a new actuator name with a null value.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenActuatorNameIsNull() {

    String actuatorName = null;
    String expectedMessage = "The actuator name cannot be null, blank, or empty.";

    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new ActuatorName(actuatorName)
    );

    String exceptionMessage = exception.getMessage();

    assertTrue(exceptionMessage.contains(expectedMessage));
  }

  /**
   * Test the instantiation of a new actuator name with a valid value.
   */
  @Test
  void shouldGetActuatorName() {

    String actuatorName = "Switch Actuator";
    ActuatorName actuatorNameVO = new ActuatorName(actuatorName);

    String result = actuatorNameVO.getName();

    assertEquals(result, actuatorName);
  }

  /**
   * Tests equals method with same object
   */
  @Test
  void shouldReturnTrue_whenComparingActuatorNameWithItself() {
    // Arrange
    String actuatorName = "Switch Actuator";
    ActuatorName actuatorNameVO = new ActuatorName(actuatorName);

    // Act
    boolean result = actuatorNameVO.equals(actuatorNameVO);

    // Assert
    assertTrue(result);
  }

  /**
   * Test the equals method when comparing two equal actuator names.
   */
  @Test
  void shouldReturnTrue_whenComparingTwoEqualActuatorNames() {
    // Arrange
    String actuatorName = "Switch Actuator";
    ActuatorName actuatorNameVO = new ActuatorName(actuatorName);
    ActuatorName actuatorNameVO2 = new ActuatorName(actuatorName);

    // Act
    boolean result = actuatorNameVO.equals(actuatorNameVO2);

    // Assert
    assertTrue(result);
  }

  /**
   * Test the equals method when comparing two different actuator names.
   */
  @Test
  void shouldReturnFalse_whenComparingTwoDifferentActuatorNames() {
    // Arrange
    String actuatorName = "Switch Actuator";
    String actuatorName2 = "Light Actuator";
    ActuatorName actuatorNameVO = new ActuatorName(actuatorName);
    ActuatorName actuatorNameVO2 = new ActuatorName(actuatorName2);

    // Act
    boolean result = actuatorNameVO.equals(actuatorNameVO2);

    // Assert
    assertFalse(result);
  }

  /**
   * Test the equals method when comparing an actuator name with a different object.
   */
  @Test
  void shouldReturnFalse_whenComparingActuatorNameWithDifferentObject() {
    // Arrange
    String actuatorName = "Switch Actuator";
    ActuatorName actuatorNameVO = new ActuatorName(actuatorName);
    Object object = new Object();

    // Act
    boolean result = actuatorNameVO.equals(object);

    // Assert
    assertFalse(result);
  }

  /**
   * Test the hashCode method.
   */
  @Test
  void shouldReturnHashCode_whenCallingHashCode() {
    // Arrange
    String actuatorName = "Switch Actuator";
    ActuatorName actuatorNameVO = new ActuatorName(actuatorName);
    int expectedHashCode = actuatorName.hashCode();

    // Act
    int result = actuatorNameVO.hashCode();

    // Assert
    assertEquals(expectedHashCode, result);
  }

}
