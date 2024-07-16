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

class ActuatorModelNameTest {

  /**
   * Test the constructor of the class ActuatorModelName.
   */
  @Test
  void shouldInstantiateActuatorModelName_whenGivenValidParameters() {
    // Arrange
    ActuatorModelName ActuatorModelName;
    String name = "ActuatorModelName";

    // Act
    ActuatorModelName = new ActuatorModelName(name);

    // Assert
    assertNotNull(ActuatorModelName);
  }

  /**
   * Test the constructor of the class ActuatorModelName with a null name.
   */
  @Test
  void shouldThrowException_whenNameIsNull() {
    // Arrange
    String name = null;

    // Act+Assert
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new ActuatorModelName(name));
    assertEquals("The device name cannot be null, blank, or empty.", exception.getMessage());
  }

  /**
   * Test the constructor of the class ActuatorModelName with a name that has more than 5
   * characters.
   */
  @Test
  void shouldThrowException_whenNameContainsSpecialCharacters() {
    // Arrange
    String name = "ActuatorModelName!";

    // Act+Assert
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new ActuatorModelName(name));
    assertEquals("The device name can only contain letters and numbers.", exception.getMessage());
  }

  /**
   * Test if the constructor throws IllegalArgumentException when name is blank
   */
  @Test
  void shouldThrowException_whenNameIsEmpty() {
    // Arrange
    String name = "";

    // Act+Assert
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new ActuatorModelName(name));
    assertEquals("The device name cannot be null, blank, or empty.", exception.getMessage());
  }

  /**
   * Test the getActuatorModelName method of the class ActuatorModel
   */
  @Test
  void shouldReturnActuatorModelName_whenGetActuatorModelNameIsCalled() {
    // Arrange
    ActuatorModelName ActuatorModelName;
    String name = "ActuatorModelName";

    // Act
    ActuatorModelName = new ActuatorModelName(name);

    // Assert
    assertEquals(name, ActuatorModelName.getActuatorModelName());
  }

  /**
   * Tests if equals method returns true with same object
   */
  @Test
  void shouldReturnTrue_whenComparingActuatorModelNameWithItself() {
    // Arrange
    ActuatorModelName actuatorModelName;
    String name = "ActuatorModelName";


    actuatorModelName = new ActuatorModelName(name);

    // Act
    boolean result = actuatorModelName.equals(actuatorModelName);

    // Assert
    assertTrue(result);
  }

  /**
   * Test the equals method of the class ActuatorModelName.
   */
  @Test
  void shouldReturnTrue_whenComparingTwoEqualActuatorModelName() {
    // Arrange
    ActuatorModelName actuatorModelName1;
    ActuatorModelName actuatorModelName2;
    String name = "ActuatorModelName";

    // Act
    actuatorModelName1 = new ActuatorModelName(name);
    actuatorModelName2 = new ActuatorModelName(name);

    // Assert
    assertEquals(actuatorModelName1, actuatorModelName2);
  }

  /**
   * Test the equals method of the class ActuatorModelName with two different Actuator model names.
   */
  @Test
  void shouldReturnFalse_whenComparingTwoDifferentActuatorModelName() {
    // Arrange
    ActuatorModelName ActuatorModelName1;
    ActuatorModelName ActuatorModelName2;
    String name1 = "ActuatorModelName1";
    String name2 = "ActuatorModelName2";

    // Act
    ActuatorModelName1 = new ActuatorModelName(name1);
    ActuatorModelName2 = new ActuatorModelName(name2);

    // Assert
    assertNotEquals(ActuatorModelName1, ActuatorModelName2);
  }

  /**
   * Test the equals method of the class ActuatorModelName with the same Actuator model name.
   */
  @Test
  void shouldReturnFalse_whenComparingActuatorModelNameWithDifferentObject() {
    // Arrange
    ActuatorModelName actuatorModelName;
    String name = "ActuatorModelName";

    actuatorModelName = new ActuatorModelName(name);

    // Act
    boolean result = actuatorModelName.equals(new Object());

    // Assert
    assertFalse(result);
  }

  /**
   * Test the hashCode method of the class ActuatorModelName.
   */
  @Test
  void shouldReturnHashCode_whenCallingHashCode() {
    // Arrange
    String name = "ActuatorModelName";
    ActuatorModelName actuatorModelName = new ActuatorModelName(name);

    int expectedHashCode = name.hashCode();

    // Act
    int result = actuatorModelName.hashCode();

    // Assert
    assertEquals(expectedHashCode, result);
  }
}