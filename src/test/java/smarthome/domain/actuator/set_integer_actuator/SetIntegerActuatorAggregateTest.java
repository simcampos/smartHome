/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator.set_integer_actuator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import smarthome.ddd.IActuatorValue;
import smarthome.ddd.IValueObject;
import smarthome.domain.actuator.blind_roller_actuator.BlindRollerValue;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.IntegerLimits;
import smarthome.domain.value_object.ModelPath;
import smarthome.persistence.data_model.ActuatorDataModel;
import smarthome.utils.visitor_pattern.ActuatorVisitorForDataModelImpl;
import smarthome.utils.visitor_pattern.IActuatorVisitor;

class SetIntegerActuatorAggregateTest {

  /**
   * Test to verify that the SetIntegerActuator class can be instantiated when the constructor
   * arguments are valid.
   */
  @Test
  void shouldInstantiateSetIntegerActuator_whenConstructorArgumentsAreValid() {
    //Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(0, 100);

    //Act
    SetIntegerActuator sensor = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID,
        actuatorName, limits);

    //Assert
    assertNotNull(sensor);
  }

  /**
   * Test the second constructor.
   */
  @Test
  void shouldInstantiateSetIntegerActuator_whenSecondConstructorArgumentsAreValid() {
    //Arrange
    ActuatorID actuatorID = new ActuatorID("actuatorID");
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(0, 100);

    //Act
    SetIntegerActuator sensor = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID,
        actuatorName, limits, actuatorID);

    //Assert
    assertNotNull(sensor);
  }


  /**
   * Test to get the actuator ID.
   */
  @Test
  void shouldGetActuatorID_WhenGetIdIsCalled() {
    //Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(0, 100);

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    //Act
    ActuatorID result = setIntegerActuator.getID();

    //Assert
    Assertions.assertTrue(setIntegerActuator.toString().contains(result.toString()));
  }

  /**
   * Test to get the actuator name.
   */
  @Test
  void shouldGetActuatorName_WhenGetNameIsCalled() {
    //Arrange
    String name = "actuatorName";

    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName(name);
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(0, 100);

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    //Act
    ActuatorName result = setIntegerActuator.getName();

    //Assert
    assertEquals(result.getName(), name);
  }

  /**
   * Test to get the model path.
   */
  @Test
  void shouldGetModelPath_WhenGetModelPathIsCalled() {
    //Arrange
    String path = "modelPath";

    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath(path);
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(0, 100);

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    //Act
    ModelPath result = setIntegerActuator.getModelPath();

    //Assert
    assertEquals(result.getID(), path);

  }

  /**
   * Test to get the actuator type ID.
   */
  @Test
  void shouldGetActuatorTypeID_WhenGetActuatorTypeIDisCalled() {
    //Arrange
    String id = "SetInteger";

    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(id);
    IntegerLimits limits = new IntegerLimits(0, 100);

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    //Act
    ActuatorTypeID result = setIntegerActuator.getActuatorTypeID();

    //Assert
    assertEquals(result.getID(), id);

  }

  /**
   * Test to get the device ID.
   */
  @Test
  void shouldGetDeviceID_WhenGetDeviceIdIsCaleed() {
    //Arrange
    String id = "deviceID";

    DeviceID deviceID = new DeviceID(id);
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(0, 100);

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    //Act
    DeviceID result = setIntegerActuator.getDeviceID();

    //Assert
    assertEquals(result.getID(), id);
  }

  /**
   * Test to get the limits.
   */
  @Test
  void shouldGetLimits_WhenGetLimitsIsCalled() {
    //Arrange
    int lowerLimit = 0;
    int upperLimit = 100;

    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(lowerLimit, upperLimit);

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    //Act
    IntegerLimits result = setIntegerActuator.getLimits();

    //Assert
    assertEquals(result.getLowerLimit(), lowerLimit);
    assertEquals(result.getUpperLimit(), upperLimit);
  }

  /**
   * Test to set the value when it is valid and within range.
   */
  @Test
  void shouldSetValue_whenValueIsWithinRange() {
    //Arrange
    int value = 50;

    int lowerLimit = 0;
    int upperLimit = 100;

    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(lowerLimit, upperLimit);

    SetIntegerValue valueDouble = new SetIntegerValue(value);

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    //Act
    SetIntegerValue result = setIntegerActuator.setValue(valueDouble);

    //Assert
    assertEquals(result.toString(), value + "");
  }

  /**
   * Test to set the value when it is valid and equal to the upper limit.
   */
  @Test
  void shouldSetValue_whenValueIsLowerLimit() {
    //Arrange
    int value = 0;

    int lowerLimit = 0;
    int upperLimit = 100;

    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(lowerLimit, upperLimit);

    SetIntegerValue valueDouble = new SetIntegerValue(value);

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    //Act
    SetIntegerValue result = setIntegerActuator.setValue(valueDouble);

    //Assert
    assertEquals(result.toString(), value + "");
  }

  /**
   * Test to set the value when it is valid and equal to the upper limit.
   */
  @Test
  void shouldSetValue_whenValueIsUpperLimit() {
    //Arrange
    int value = 100;

    int lowerLimit = 0;
    int upperLimit = 100;

    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(lowerLimit, upperLimit);

    SetIntegerValue valueDouble = new SetIntegerValue(value);

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    //Act
    SetIntegerValue result = setIntegerActuator.setValue(valueDouble);

    //Assert
    assertEquals(result.toString(), value + "");
  }

  /**
   * Test to set the value when it is valid but below the lower limit.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenValueIsBelowLowerLimit() {
    //Arrange
    int value = -1;

    int lowerLimit = 0;
    int upperLimit = 100;

    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(lowerLimit, upperLimit);

    SetIntegerValue valueDouble = new SetIntegerValue(value);

    String expectedMessage = "Value cannot be less than the lower limit.";

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    //Act & Assert
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
        () -> {
          setIntegerActuator.setValue(valueDouble);
        });

    assertEquals(exception.getMessage(), expectedMessage);
  }

  /**
   * Test to set the value when it is valid but above the upper limit.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenValueIsAboveUpperLimit() {
    //Arrange
    int value = 101;

    int lowerLimit = 0;
    int upperLimit = 100;

    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(lowerLimit, upperLimit);

    SetIntegerValue valueDouble = new SetIntegerValue(value);

    String expectedMessage = "Value cannot be greater than the upper limit.";

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    //Act & Assert
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
        () -> {
          setIntegerActuator.setValue(valueDouble);
        });

    assertEquals(exception.getMessage(), expectedMessage);
  }

  /**
   * Test to set the value when it is null
   */
  @Test
  void shouldThrowIllegalArgumentException_whenValueIsNull() {
    //Arrange
    int lowerLimit = 0;
    int upperLimit = 100;

    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(lowerLimit, upperLimit);

    SetIntegerValue value = null;

    String expectedMessage = "Value cannot be null";

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    //Act & Assert
    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
        () -> {
          setIntegerActuator.setValue(value);
        });

    assertEquals(exception.getMessage(), expectedMessage);
  }

  /**
   * Test to set the value when it is not an instance of SetIntegerValue
   */
  @Test
  void shouldThrowIllegalArgumentException_whenValueIsNotInstanceOfSetIntegerValue() {
    //Arrange
    int value = 1;
    IActuatorValue valueDouble = new BlindRollerValue(value);

    int lowerLimit = 0;
    int upperLimit = 100;

    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(lowerLimit, upperLimit);

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    //Act
    IValueObject result = setIntegerActuator.setValue(valueDouble);

    //Assert
    Assertions.assertNull(result);
  }

  /**
   * Test method equals when the instance is compared to itself.
   */
  @Test
  void shouldReturnTrue_whenInstanceIsComparedToItself() {
    //Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(0, 100);

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    //Act
    boolean result = setIntegerActuator.equals(setIntegerActuator);

    //Assert
    assertTrue(result);
  }

  /**
   * Test of method equals when the instances are not equal.
   */
  @Test
  void shouldReturnFalse_whenInstancesAreNotEqual() {
    //Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(0, 100);

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    SetIntegerActuator setIntegerActuator2 = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    //Act
    boolean result = setIntegerActuator.equals(setIntegerActuator2);

    //Assert
    assertFalse(result);
  }

  /**
   * Test of method equals when the instance is compared to an object of a different class.
   */
  @Test
  void shouldReturnFalse_whenInstanceIsComparedToAnObjectOfDifferentClass() {
    //Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(0, 100);

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    //Act
    boolean result = setIntegerActuator.equals(new Object());

    //Assert
    assertFalse(result);
  }

  /**
   * Test of method toString.
   */
  @Test
  void shouldReturnString_whenToStringIsCalled() {
    //Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(0, 100);

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);
    SetIntegerValue value = setIntegerActuator.setValue(new SetIntegerValue(50));
    ActuatorID actuatorID = setIntegerActuator.getID();

    String expectedString =
        "SetIntegerActuator:" + "ActuatorID:" + actuatorID + ", ActuatorName:" + actuatorName
            + ", ModelPath:" + modelPath + ", ActuatorTypeID:" + actuatorTypeID + ", DeviceID="
            + deviceID + ", Value:" + value + ", Limits:" + limits;

    //Act
    String result = setIntegerActuator.toString();

    //Assert
    assertEquals(result, expectedString);
  }

  /**
   * Test of method hashcode.
   */
  @Test
  void shouldReturnHashCode_whenHashCodeIsCalled() {
    //Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    ActuatorName actuatorName = new ActuatorName("actuatorName");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(0, 100);

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);
    ActuatorID actuatorID = setIntegerActuator.getID();
    int expected = actuatorID.getID().hashCode();

    //Act
    int result = setIntegerActuator.hashCode();

    //Assert
    assertEquals(expected, result);
  }

  /**
   * Test of accept method, of class {@link SetIntegerActuator}.
   */
  @Test
  void shouldReturnString_WhenAcceptIsCalled() {
    //Arrange
    DeviceID deviceID = new DeviceID("1");
    ModelPath modelPath = new ModelPath(
        "SmartHomeDDD.domain.Actuator.SetIntegerActuator.SetIntegerActuator");
    ActuatorName actuatorName = new ActuatorName("SetInteger");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID("SetInteger");
    IntegerLimits limits = new IntegerLimits(1, 9);

    ActuatorDataModel actuatorDataModel = new ActuatorDataModel();

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    IActuatorVisitor visitor = new ActuatorVisitorForDataModelImpl(actuatorDataModel);

    String expected = setIntegerActuator.toString();

    //Act
    String result = setIntegerActuator.accept(visitor);

    //Assert
    assertEquals(expected, result);
  }

}

