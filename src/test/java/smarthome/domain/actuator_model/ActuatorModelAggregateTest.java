/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator_model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;

class ActuatorModelAggregateTest {

  /**
   * Test of class ActuatorModel constructor with valid parameters
   */
  @Test
  void shouldReturnValidActuatorModel_WhenGivenValidParameters() {
    //Arrange
    ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
    ModelPath modelPath = new ModelPath(
        "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");

    //Act
    ActuatorModel actuatorModel = new ActuatorModel(modelPath, actuatorModelName, actuatorTypeID);

    //Assert
    assertNotNull(actuatorModel);
  }

  /**
   * Test of class ActuatorModel constructor with null ActuatorModelName
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenGivenNullActuatorModelName() {
    //Arrange
    ActuatorModelName actuatorModelName = null;
    ModelPath modelPath = new ModelPath(
        "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");

    String expectedMessage = "ActuatorModelName is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ActuatorModel(modelPath, actuatorModelName, actuatorTypeID));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test of class ActuatorModel constructor with null ModelPath
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenGivenNullModelPath() {
    //Arrange
    ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
    ModelPath modelPath = null;
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");
    String expectedMessage = "ModelPath is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ActuatorModel(modelPath, actuatorModelName, actuatorTypeID));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test equals method reflexivity
   */
  @Test
  void shouldReturnTrue_WhenGivenSameObject() {
    //Arrange
    ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
    ModelPath modelPath = new ModelPath(
        "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");

    ActuatorModel actuatorModel = new ActuatorModel(modelPath, actuatorModelName, actuatorTypeID);
    //Act
    boolean result = actuatorModel.equals(actuatorModel);

    //Assert
    assertTrue(result);
  }

  /**
   * Test equals method symmetry
   */

  @Test
  void shouldReturnTrue_WhenGivenTwoEqualObjects() {
    //Arrange
    ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
    ModelPath modelPath = new ModelPath(
        "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");

    ActuatorModel actuatorModel = new ActuatorModel(modelPath, actuatorModelName, actuatorTypeID);
    ActuatorModel actuatorModel2 = new ActuatorModel(modelPath, actuatorModelName, actuatorTypeID);

    //Act
    boolean result = actuatorModel.equals(actuatorModel2);

    //Assert
    assertTrue(result);
  }

  /**
   * Test equals method transitivity
   */
  @Test
  void shouldReturnTrue_WhenGivenThreeEqualObjects() {
    //Arrange
    ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
    ModelPath modelPath = new ModelPath(
        "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");

    ActuatorModel actuatorModel = new ActuatorModel(modelPath, actuatorModelName, actuatorTypeID);
    ActuatorModel actuatorModel2 = new ActuatorModel(modelPath, actuatorModelName, actuatorTypeID);
    ActuatorModel actuatorModel3 = new ActuatorModel(modelPath, actuatorModelName, actuatorTypeID);

    //Act
    boolean result = actuatorModel.equals(actuatorModel2) && actuatorModel2.equals(actuatorModel3);

    //Assert
    assertTrue(result);
  }

  /**
   * Test equals method non-nullity
   */
  @Test
  void shouldReturnFalse_WhenGivenNullObject() {
    //Arrange
    ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
    ModelPath modelPath = new ModelPath(
        "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");

    ActuatorModel actuatorModel = new ActuatorModel(modelPath, actuatorModelName, actuatorTypeID);

    //Act
    boolean result = actuatorModel.equals(null);

    //Assert
    assertFalse(result);
  }

  /**
   * Test equals method symmetry with different object of same class
   */
  @Test
  void shouldReturnFalse_WhenGivenDifferentObject() {
    //Arrange
    ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
    ModelPath modelPath = new ModelPath(
        "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator");
    ActuatorTypeID actuatorTypeID1 = new ActuatorTypeID("BlindRoller");

    ActuatorModelName actuatorModelName2 = new ActuatorModelName("SwitchActuator");
    ModelPath modelPath2 = new ModelPath(
        "SmartHomeDDD.domain.Actuator.SwitchActuator.SwitchActuator");
    ActuatorTypeID actuatorTypeID2 = new ActuatorTypeID("BlindRoller");

    ActuatorModel actuatorModel = new ActuatorModel(modelPath, actuatorModelName, actuatorTypeID1);
    ActuatorModel actuatorModel2 = new ActuatorModel(modelPath2, actuatorModelName2,
        actuatorTypeID2);

    //Act
    boolean result = actuatorModel.equals(actuatorModel2);

    //Assert
    assertFalse(result);
  }

  /**
   * Test equals method with different object
   */
  @Test
  void shouldReturnFalse_WhenComparedWithDifferentClassObject() {
    // Arrange
    ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
    ModelPath modelPath = new ModelPath(
        "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");
    ActuatorModel actuatorModel = new ActuatorModel(modelPath, actuatorModelName, actuatorTypeID);

    Object differentClassObject = new Object();

    // Act
    boolean result = actuatorModel.equals(differentClassObject);

    // Assert
    assertFalse(result);
  }


  /**
   * Test hashCode method consistency
   */
  @Test
  void shouldReturnSameHashCode_ForEqualObjects() {
    // Arrange
    ActuatorModelName actuatorModelName1 = new ActuatorModelName("Blind Roller");
    ModelPath modelPath1 = new ModelPath(
        "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator");
    ActuatorTypeID actuatorTypeID1 = new ActuatorTypeID("BlindRoller");
    ActuatorModel actuatorModel1 = new ActuatorModel(modelPath1, actuatorModelName1,
        actuatorTypeID1);

    ActuatorModelName actuatorModelName2 = new ActuatorModelName("Blind Roller");
    ModelPath modelPath2 = new ModelPath(
        "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator");
    ActuatorTypeID actuatorTypeID2 = new ActuatorTypeID("BlindRoller");
    ActuatorModel actuatorModel2 = new ActuatorModel(modelPath2, actuatorModelName2,
        actuatorTypeID2);

    // Act
    int hashCode1 = actuatorModel1.hashCode();
    int hashCode2 = actuatorModel2.hashCode();

    // Assert
    assertEquals(hashCode1, hashCode2);
  }

  /**
   * Test for hashCode method
   */
  @Test
  void shouldReturnHashCode_whenHashCodeIsCalled() {
    // Arrange
    ActuatorModelName actuatorModelName1 = new ActuatorModelName("Blind Roller");
    ModelPath modelPath1 = new ModelPath(
        "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator");
    ActuatorTypeID actuatorTypeID1 = new ActuatorTypeID("BlindRoller");
    ActuatorModel actuatorModel1 = new ActuatorModel(modelPath1, actuatorModelName1,
        actuatorTypeID1);

    int expectedHashCode = modelPath1.hashCode();

    // Act
    int hashCode = actuatorModel1.hashCode();

    // Assert
   assertEquals(expectedHashCode, hashCode);
  }

  /**
   * Test of class ActuatorModel getID method
   */
  @Test
  void shouldReturnActuatorModelID_WhenGetIDIsCalled() {
    //Arrange
    ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
    ModelPath modelPath = new ModelPath(
        "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");

    ActuatorModel actuatorModel = new ActuatorModel(modelPath, actuatorModelName, actuatorTypeID);

    //Act
    ModelPath result = actuatorModel.getID();

    //Assert
    assertNotNull(result);
  }


  /**
   * Test of class ActuatorModel toString method
   */
  @Test
  void shouldReturnObjectInStringFormat_WhenToStringIsCalled() {
    //Arrange
    ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
    ModelPath modelPath = new ModelPath(
        "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");
    ActuatorModel actuatorModel = new ActuatorModel(modelPath, actuatorModelName, actuatorTypeID);

    //Act
    String result = actuatorModel.toString();

    //Assert
    assertTrue(result.contains(actuatorModel.getID().toString()));
    assertTrue(result.contains(actuatorModelName.toString()));
    assertTrue(result.contains(modelPath.toString()));
  }


  /**
   * Test of class ActuatorModel getActuatorModelName method
   */
  @Test
  void shouldReturnActuatorModelName_WhenGetActuatorModelNameIsCalled() {
    //Arrange
    ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
    ModelPath modelPath = new ModelPath(
        "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");
    ActuatorModel actuatorModel = new ActuatorModel(modelPath, actuatorModelName, actuatorTypeID);

    //Act
    ActuatorModelName result = actuatorModel.getName();

    //Assert
    assertEquals(actuatorModelName, result);
  }


  /**
   * Test of class ActuatorModel getModelPath method
   */
  @Test
  void shouldReturnModelPath_WhenGetModelPathIsCalled() {
    //Arrange
    ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
    ModelPath modelPath = new ModelPath(
        "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");
    ActuatorModel actuatorModel = new ActuatorModel(modelPath, actuatorModelName, actuatorTypeID);

    //Act
    ModelPath result = actuatorModel.getID();

    //Assert
    assertEquals(modelPath, result);
  }

  /**
   * Test of class ActuatorModel getActuatorTypeID method
   */
  @Test
  void shouldReturnActuatorTypeID_WhenGetActuatorTypeIDIsCalled() {
    //Arrange
    ActuatorModelName actuatorModelName = new ActuatorModelName("Blind Roller");
    ModelPath modelPath = new ModelPath(
        "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");
    ActuatorModel actuatorModel = new ActuatorModel(modelPath, actuatorModelName, actuatorTypeID);

    //Act
    ActuatorTypeID result = actuatorModel.getActuatorTypeID();

    //Assert
    assertEquals(actuatorTypeID, result);
  }
}


