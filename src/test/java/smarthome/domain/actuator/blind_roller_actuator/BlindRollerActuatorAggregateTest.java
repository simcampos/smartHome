/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator.blind_roller_actuator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import smarthome.ddd.IValueObject;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.persistence.data_model.ActuatorDataModel;
import smarthome.utils.visitor_pattern.ActuatorVisitorForDataModelImpl;
import smarthome.utils.visitor_pattern.IActuatorVisitor;

class BlindRollerActuatorAggregateTest {

  /**
   * Test of constructor, of class BlindRollerActuator.
   */
  @Test
  void shouldCreateBlindRollerActuator_WhenConstructorArgumentsAreValid() {
    //Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

    //Act
    BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject,
        modelPathObject, actuatorTypeIDObject, actuatorNameObject);

    //Assert
    assertNotNull(blindRollerActuator);
  }

  /**
   * Test the second constructor, of class BlindRollerActuator.
   */
  @Test
  void shouldCreateBlindRollerActuator_WhenSecondConstructorArgumentsAreValid() {
    //Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";
    String actuatorID = "1";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);
    ActuatorID actuatorIDObject = new ActuatorID(actuatorID);

    //Act
    BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject,
        modelPathObject, actuatorTypeIDObject, actuatorNameObject, actuatorIDObject);

    //Assert
    assertNotNull(blindRollerActuator);
  }

  /**
   * Test for invalid deviceID
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDeviceIDIsNullForSecondConstructor() {
    //Arrange
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = null;
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);
    ActuatorID actuatorIDObject = new ActuatorID("1");

    String expectedMessage = "DeviceID is required";

    //Act+Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject,
          actuatorNameObject, actuatorIDObject);
    });

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test for invalid deviceID
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDeviceIDIsNull() {
    //Arrange
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = null;
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

    String expectedMessage = "DeviceID is required";

    //Act+Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject,
          actuatorNameObject);
    });

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test for invalid actuatorTypeID
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenActuatorTypeIDIsNull() {
    //Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = null;

    String expectedMessage = "ActuatorTypeID is required";

    //Act+Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject,
          actuatorNameObject);
    });

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);

  }

  /**
   * Test for invalid actuatorTypeID
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenActuatorTypeIDIsNullForSecondConstructor() {
    //Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = null;
    ActuatorID actuatorID = new ActuatorID("1");

    String expectedMessage = "ActuatorTypeID is required";

    //Act+Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject,
          actuatorNameObject, actuatorID);
    });

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);

  }

  /**
   * Test for invalid actuatorName
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenActuatorNameIsNullForSecondConstructor() {
    //Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = null;
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);
    ActuatorID actuatorID = new ActuatorID("1");

    String expectedMessage = "ActuatorName is required";

    //Act+Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject,
          actuatorNameObject, actuatorID);
    });

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);

  }

  /**
   * Test for invalid actuatorName
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenActuatorNameIsNull() {
    //Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = null;
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

    String expectedMessage = "ActuatorName is required";

    //Act+Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject,
          actuatorNameObject);
    });

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);

  }

  /**
   * Test for invalid modelPath
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenModelPathIsNullForSecondConstructor() {
    //Arrange
    String deviceID = "1";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = null;
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);
    ActuatorID actuatorID = new ActuatorID("1");

    String expectedMessage = "ModelPath is required";

    //Act+Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject,
          actuatorNameObject, actuatorID);
    });

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);

  }

  /**
   * Test for invalid modelPath
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenModelPathIsNull() {
    //Arrange
    String deviceID = "1";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = null;
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

    String expectedMessage = "ModelPath is required";

    //Act+Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject,
          actuatorNameObject);
    });

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);

  }

  /**
   * Test for invalid modelPath
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenActuatorIDIsNullForSecondConstructor() {
    //Arrange
    String deviceID = "1";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(
        "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator");
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);
    ActuatorID actuatorID = null;

    String expectedMessage = "ActuatorID is required";

    //Act+Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new BlindRollerActuator(deviceIDObject, modelPathObject, actuatorTypeIDObject,
          actuatorNameObject, actuatorID);
    });

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);

  }


  /**
   * Test of getID method, of class BlindRollerActuator.
   */
  @Test
  void shouldReturnActuatorID_WhenGetIDIsCalled() {
    // Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

    BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject,
        modelPathObject, actuatorTypeIDObject, actuatorNameObject);

    // Act
    ActuatorID result = blindRollerActuator.getID();

    // Assert
    assertNotNull(result);

  }


  /**
   * Test of getName method, of class BlindRollerActuator.
   */
  @Test
  void shouldReturnActuatorName_WhenGetNameIsCalled() {
    //Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

    BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject,
        modelPathObject, actuatorTypeIDObject, actuatorNameObject);

    //Act
    ActuatorName result = blindRollerActuator.getName();

    //Assert
    assertEquals(actuatorName, result.getName());
  }

  /**
   * Test of getModelPath method, of class BlindRollerActuator.
   */
  @Test
  void shouldReturnModelPath_WhenGetModelPathIsCalled() {
    //Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

    String expected = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";

    BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject,
        modelPathObject, actuatorTypeIDObject, actuatorNameObject);

    //Act
    ModelPath result = blindRollerActuator.getModelPath();

    //Assert
    assertEquals(expected, result.toString());
  }

  /**
   * Test of getActuatorTypeID method, of class BlindRollerActuator.
   */
  @Test
  void shouldReturnActuatorTypeID_WhenGetActuatorTypeIDIsCalled() {
    //Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

    String expected = "BlindRoller";

    BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject,
        modelPathObject, actuatorTypeIDObject, actuatorNameObject);

    //Act
    ActuatorTypeID result = blindRollerActuator.getActuatorTypeID();

    //Assert
    assertEquals(expected, result.toString());

  }

  /**
   * Test of getDeviceID method, of class BlindRoller
   */
  @Test
  void shouldReturnDeviceID_WhenGetDeviceIDIsCalled() {
    //Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

    String expected = "1";

    BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject,
        modelPathObject, actuatorTypeIDObject, actuatorNameObject);

    //Act
    DeviceID result = blindRollerActuator.getDeviceID();

    //Assert
    assertEquals(expected, result.toString());
  }

  /**
   * Test of setValue, of class BlindRollerActuator.
   */
  @Test
  void shouldReturnActuatorValue_WhenSetValueIsCalled() {
    //Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";

    int value = 50;

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

    BlindRollerValue blindRollerValue = new BlindRollerValue(value);

    BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject,
        modelPathObject, actuatorTypeIDObject, actuatorNameObject);

    //Act
    IValueObject result = blindRollerActuator.setValue(blindRollerValue);

    //Assert
    assertNotNull(result);
  }

  /**
   * Test of setValue when is null, of class BlindRollerActuator.
   */
  @Test
  void shouldReturnNull_WhenSetValueIsNull() {
    //Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

    BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject,
        modelPathObject, actuatorTypeIDObject, actuatorNameObject);

    //Act
    IValueObject result = blindRollerActuator.setValue(null);

    //Assert
    assertNull(result);
  }

  /**
   * Test equals method should return true when instances are same object.
   */
  @Test
  void shouldReturnTrue_WhenInstancesAreSameObject() {
    //Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

    BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject,
        modelPathObject, actuatorTypeIDObject, actuatorNameObject);

    //Act
    boolean result = blindRollerActuator.equals(blindRollerActuator);

    //Assert
    assertTrue(result);
  }

  /**
   * Test equals method should return false when instances are different objects.
   */
  @Test
  void shouldReturnFalse_WhenInstancesAreDifferentObjects() {
    //Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

    BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject,
        modelPathObject, actuatorTypeIDObject, actuatorNameObject);

    BlindRollerActuator blindRollerActuator2 = new BlindRollerActuator(deviceIDObject,
        modelPathObject, actuatorTypeIDObject, actuatorNameObject);

    //Act
    boolean result = blindRollerActuator.equals(blindRollerActuator2);

    //Assert
    assertFalse(result);
  }

  /**
   * Test equals method should return false when an object is not an instance of switch actuator.
   */
  @Test
  void shouldReturnFalse_WhenObjectIsNotInstanceOfBlindRollerActuator() {
    //Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

    BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject,
        modelPathObject, actuatorTypeIDObject, actuatorNameObject);

    Object object = new Object();

    //Act
    boolean result = blindRollerActuator.equals(object);

    //Assert
    assertFalse(result);
  }

  /**
   * Test hashcode method
   */
  @Test
  void shouldReturnHashCode_WhenGetHashCodeIsCalled() {
    //Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

    BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject,
        modelPathObject, actuatorTypeIDObject, actuatorNameObject);

    ActuatorID actuatorID = blindRollerActuator.getID();
    int expected = actuatorID.hashCode();
    //Act
    int result = blindRollerActuator.hashCode();

    //Assert
    assertEquals(expected, result);
  }

  /**
   * Test toString method
   */
  @Test
  void shouldReturnString_WhenToStringIsCalled() {
    //Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "BlindRoller";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

    BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDObject,
        modelPathObject, actuatorTypeIDObject, actuatorNameObject);

    String expected =
        "BlindRollerActuator:" + "actuatorID=" + blindRollerActuator.getID() + ", deviceID="
            + blindRollerActuator.getDeviceID() + ", modelPath="
            + blindRollerActuator.getModelPath() + ", actuatorTypeID="
            + blindRollerActuator.getActuatorTypeID() + ", actuatorName="
            + blindRollerActuator.getName();

    //Act
    String result = blindRollerActuator.toString();

    //Assert
    assertEquals(expected, result);
  }

  /**
   * Test of accept method, of class BlindRollerActuator.
   */
  @Test
  void shouldReturnString_WhenAcceptIsCalled() {
    //Arrange
    DeviceID deviceID = new DeviceID("1");
    ModelPath modelPath = new ModelPath(
        "SmartHomeDDD.domain.Actuator.blind_roller_actuator.BlindRollerActuator");
    ActuatorName actuatorName = new ActuatorName("BlindRoller");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("BlindRoller");

    BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName);

    IActuatorVisitor visitor = new ActuatorVisitorForDataModelImpl(new ActuatorDataModel());

    String expected = blindRollerActuator.toString();

    //Act
    String result = blindRollerActuator.accept(visitor);

    //Assert
    assertEquals(expected, result);
  }
}
