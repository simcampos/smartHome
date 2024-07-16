/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidatorTest {

  @Test
  void shouldNotThrowException_WhenObjectIsNotNull() {
    //Arrange
    String objectName = "Sensor Model";
    //Act
    Validator.validateNotNull(new Object(), objectName);
    //Assert
    assertTrue(true);
  }

  @Test
  void shouldThrowIllegalArgumentException_WhenObjectIsNull() {
    //Arrange
    String objectName = "Sensor Model";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> Validator.validateNotNull(null, objectName));
    //Assert
    assertEquals(objectName + " is required", exception.getMessage());
  }
}
