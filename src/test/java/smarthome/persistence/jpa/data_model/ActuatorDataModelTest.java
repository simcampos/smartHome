/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.jpa.data_model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.persistence.data_model.ActuatorDataModel;

class ActuatorDataModelTest {

  /**
   * Test for the empty constructor of the {@link ActuatorDataModel} class.
   */
  @Test
  void shouldInstantiateActuatorDataModel_whenEmptyConstructorIsCalled() {
    // Arrange
    ActuatorDataModel actuatorDataModel = new ActuatorDataModel();

    // Act
    assertNotNull(actuatorDataModel);
  }

  /**
   * Test for the constructor of the {@link ActuatorDataModel} class.
   */
  @Test
  void shouldInstantiateActuatorDataModel_WhenAttributesAreValid() {
    //Arrange
    ActuatorID actuatorIDDouble = mock(ActuatorID.class);
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorName actuatorName = mock(ActuatorName.class);

    IActuator actuatorDouble = mock(IActuator.class);

    when(actuatorIDDouble.getID()).thenReturn("123");
    when(deviceID.getID()).thenReturn("123");
    when(modelPath.getID()).thenReturn("smart_home.domain.actuator.Switch.Switch");
    when(actuatorTypeID.getID()).thenReturn("Switch");
    when(actuatorName.getName()).thenReturn("SwitchActuator");

    when(actuatorDouble.getID()).thenReturn(actuatorIDDouble);
    when(actuatorDouble.getDeviceID()).thenReturn(deviceID);
    when(actuatorDouble.getModelPath()).thenReturn(modelPath);
    when(actuatorDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
    when(actuatorDouble.getName()).thenReturn(actuatorName);

    //Act
    ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuatorDouble);

    //Assert
    assertNotNull(actuatorDataModel);

  }

  /**
   * Test for the constructor of the {@link ActuatorDataModel} class when actuator is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenActuatorDataModelIsNull() {
    //Arrange
    IActuator actuatorDouble = null;

    String expectedMessage = "Actuator is required";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ActuatorDataModel(actuatorDouble));

    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Test for the getActuatorID method of the {@link ActuatorDataModel} class.
   */
  @Test
  void shouldReturnActuatorID_WhenGetActuatorIDIsCalled() {
    //Arrange
    ActuatorID actuatorIDDouble = mock(ActuatorID.class);
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorName actuatorName = mock(ActuatorName.class);

    IActuator actuatorDouble = mock(IActuator.class);

    when(actuatorIDDouble.getID()).thenReturn("123");
    when(deviceID.getID()).thenReturn("device1");
    when(modelPath.getID()).thenReturn("smart_home.domain.actuator.Switch.Switch");
    when(actuatorTypeID.getID()).thenReturn("Switch");
    when(actuatorName.getName()).thenReturn("Switch");

    when(actuatorDouble.getID()).thenReturn(actuatorIDDouble);
    when(actuatorDouble.getDeviceID()).thenReturn(deviceID);
    when(actuatorDouble.getModelPath()).thenReturn(modelPath);
    when(actuatorDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
    when(actuatorDouble.getName()).thenReturn(actuatorName);

    ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuatorDouble);

    String expected = actuatorDataModel.getActuatorID();

    //Act
    String result = actuatorDataModel.getActuatorID();

    //Assert
    assertEquals(expected, result);

  }

  /**
   * Test for the getDeviceID method of the {@link ActuatorDataModel} class.
   */
  @Test
  void shouldReturnDeviceID_WhenGetDeviceIDIsCalled() {
    //Arrange
    ActuatorID actuatorIDDouble = mock(ActuatorID.class);
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorName actuatorName = mock(ActuatorName.class);

    IActuator actuatorDouble = mock(IActuator.class);

    when(actuatorIDDouble.getID()).thenReturn("123");
    when(deviceID.getID()).thenReturn("device1");
    when(modelPath.getID()).thenReturn("smart_home.domain.actuator.Switch.Switch");
    when(actuatorTypeID.getID()).thenReturn("Switch");
    when(actuatorName.getName()).thenReturn("Switch");

    when(actuatorDouble.getID()).thenReturn(actuatorIDDouble);
    when(actuatorDouble.getDeviceID()).thenReturn(deviceID);
    when(actuatorDouble.getModelPath()).thenReturn(modelPath);
    when(actuatorDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
    when(actuatorDouble.getName()).thenReturn(actuatorName);

    ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuatorDouble);

    String expected = actuatorDataModel.getDeviceID();

    //Act
    String result = actuatorDataModel.getDeviceID();

    //Assert
    assertEquals(expected, result);

  }

  /**
   * Test for the getModelPath method of the {@link ActuatorDataModel} class.
   */
  @Test
  void shouldReturnModelPath_WhenGetModelPathIsCalled() {
    //Arrange
    ActuatorID actuatorIDDouble = mock(ActuatorID.class);
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorName actuatorName = mock(ActuatorName.class);

    IActuator actuatorDouble = mock(IActuator.class);

    when(actuatorIDDouble.getID()).thenReturn("123");
    when(deviceID.getID()).thenReturn("device1");
    when(modelPath.getID()).thenReturn("smart_home.domain.actuator.Switch.Switch");
    when(actuatorTypeID.getID()).thenReturn("Switch");
    when(actuatorName.getName()).thenReturn("Switch");

    when(actuatorDouble.getID()).thenReturn(actuatorIDDouble);
    when(actuatorDouble.getDeviceID()).thenReturn(deviceID);
    when(actuatorDouble.getModelPath()).thenReturn(modelPath);
    when(actuatorDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
    when(actuatorDouble.getName()).thenReturn(actuatorName);

    ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuatorDouble);

    String expected = actuatorDataModel.getModelPath();

    //Act
    String result = actuatorDataModel.getModelPath();

    //Assert
    assertEquals(expected, result);

  }


  /**
   * Test for the getActuatorTypeID method of the {@link ActuatorDataModel} class.
   */
  @Test
  void shouldReturnActuatorTypeID_WhenGetActuatorTypeIDIsCalled() {
    //Arrange
    ActuatorID actuatorIDDouble = mock(ActuatorID.class);
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorName actuatorName = mock(ActuatorName.class);

    IActuator actuatorDouble = mock(IActuator.class);

    when(actuatorIDDouble.getID()).thenReturn("123");
    when(deviceID.getID()).thenReturn("device1");
    when(modelPath.getID()).thenReturn("smart_home.domain.actuator.Switch.Switch");
    when(actuatorTypeID.getID()).thenReturn("Switch");
    when(actuatorName.getName()).thenReturn("Switch");

    when(actuatorDouble.getID()).thenReturn(actuatorIDDouble);
    when(actuatorDouble.getDeviceID()).thenReturn(deviceID);
    when(actuatorDouble.getModelPath()).thenReturn(modelPath);
    when(actuatorDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
    when(actuatorDouble.getName()).thenReturn(actuatorName);

    ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuatorDouble);

    String expected = actuatorDataModel.getActuatorTypeID();

    //Act
    String result = actuatorDataModel.getActuatorTypeID();

    //Assert
    assertEquals(expected, result);

  }


  /**
   * Test for the getActuatorName method of the {@link ActuatorDataModel} class.
   */
  @Test
  void shouldReturnActuatorName_WhenGetActuatorNameIsCalled() {
    //Arrange
    ActuatorID actuatorIDDouble = mock(ActuatorID.class);
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorName actuatorName = mock(ActuatorName.class);

    IActuator actuatorDouble = mock(IActuator.class);

    when(actuatorIDDouble.getID()).thenReturn("123");
    when(deviceID.getID()).thenReturn("device1");
    when(modelPath.getID()).thenReturn("smart_home.domain.actuator.Switch.Switch");
    when(actuatorTypeID.getID()).thenReturn("Switch");
    when(actuatorName.getName()).thenReturn("Switch");

    when(actuatorDouble.getID()).thenReturn(actuatorIDDouble);
    when(actuatorDouble.getDeviceID()).thenReturn(deviceID);
    when(actuatorDouble.getModelPath()).thenReturn(modelPath);
    when(actuatorDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
    when(actuatorDouble.getName()).thenReturn(actuatorName);

    ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuatorDouble);

    String expected = actuatorDataModel.getActuatorName();

    //Act
    String result = actuatorDataModel.getActuatorName();

    //Assert
    assertEquals(expected, result);

  }

  @Test
  void shouldReturnActuatorIntegerLowerLimit_WhenGetLowerLimitIsCalled() {
    //Arrange
    ActuatorID actuatorIDDouble = mock(ActuatorID.class);
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorName actuatorName = mock(ActuatorName.class);

    IActuator actuatorDouble = mock(IActuator.class);

    when(actuatorIDDouble.getID()).thenReturn("123");
    when(deviceID.getID()).thenReturn("device1");
    when(modelPath.getID()).thenReturn("smart_home.domain.actuator.Switch.Switch");
    when(actuatorTypeID.getID()).thenReturn("Switch");
    when(actuatorName.getName()).thenReturn("Switch");

    when(actuatorDouble.getID()).thenReturn(actuatorIDDouble);
    when(actuatorDouble.getDeviceID()).thenReturn(deviceID);
    when(actuatorDouble.getModelPath()).thenReturn(modelPath);
    when(actuatorDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
    when(actuatorDouble.getName()).thenReturn(actuatorName);

    ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuatorDouble);
    int integerLowerLimit = 3;
    String expected = String.valueOf(integerLowerLimit);

    actuatorDataModel.setIntegerLowerBond(integerLowerLimit);

    //Act
    String result = actuatorDataModel.getIntegerLowerBond();

    //Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldReturnActuatorIntegerUpperLimit_WhenGetUpperLimitIsCalled() {
    //Arrange
    ActuatorID actuatorIDDouble = mock(ActuatorID.class);
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorName actuatorName = mock(ActuatorName.class);

    IActuator actuatorDouble = mock(IActuator.class);

    when(actuatorIDDouble.getID()).thenReturn("123");
    when(deviceID.getID()).thenReturn("device1");
    when(modelPath.getID()).thenReturn("smart_home.domain.actuator.Switch.Switch");
    when(actuatorTypeID.getID()).thenReturn("Switch");
    when(actuatorName.getName()).thenReturn("Switch");

    when(actuatorDouble.getID()).thenReturn(actuatorIDDouble);
    when(actuatorDouble.getDeviceID()).thenReturn(deviceID);
    when(actuatorDouble.getModelPath()).thenReturn(modelPath);
    when(actuatorDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
    when(actuatorDouble.getName()).thenReturn(actuatorName);

    ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuatorDouble);
    int integerUpperLimit = 3;
    String expected = String.valueOf(integerUpperLimit);

    actuatorDataModel.setIntegerUpperBond(integerUpperLimit);

    //Act
    String result = actuatorDataModel.getIntegerUpperBond();

    //Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldReturnActuatorDecimalLowerLimit_WhenGetDecimalLowerLimitIsCalled() {
    //Arrange
    ActuatorID actuatorIDDouble = mock(ActuatorID.class);
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorName actuatorName = mock(ActuatorName.class);

    IActuator actuatorDouble = mock(IActuator.class);

    when(actuatorIDDouble.getID()).thenReturn("123");
    when(deviceID.getID()).thenReturn("device1");
    when(modelPath.getID()).thenReturn("smart_home.domain.actuator.Switch.Switch");
    when(actuatorTypeID.getID()).thenReturn("Switch");
    when(actuatorName.getName()).thenReturn("Switch");

    when(actuatorDouble.getID()).thenReturn(actuatorIDDouble);
    when(actuatorDouble.getDeviceID()).thenReturn(deviceID);
    when(actuatorDouble.getModelPath()).thenReturn(modelPath);
    when(actuatorDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
    when(actuatorDouble.getName()).thenReturn(actuatorName);

    ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuatorDouble);
    double decimalLowerLimit = 3.5;
    String expected = String.valueOf(decimalLowerLimit);

    actuatorDataModel.setDecimalLowerBond(decimalLowerLimit);

    //Act
    String result = actuatorDataModel.getDecimalLowerBond();

    //Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldReturnActuatorDecimalUpperLimit_WhenGetDecimalUpperLimitIsCalled() {
    //Arrange
    ActuatorID actuatorIDDouble = mock(ActuatorID.class);
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorName actuatorName = mock(ActuatorName.class);

    IActuator actuatorDouble = mock(IActuator.class);

    when(actuatorIDDouble.getID()).thenReturn("123");
    when(deviceID.getID()).thenReturn("device1");
    when(modelPath.getID()).thenReturn("smart_home.domain.actuator.Switch.Switch");
    when(actuatorTypeID.getID()).thenReturn("Switch");
    when(actuatorName.getName()).thenReturn("Switch");

    when(actuatorDouble.getID()).thenReturn(actuatorIDDouble);
    when(actuatorDouble.getDeviceID()).thenReturn(deviceID);
    when(actuatorDouble.getModelPath()).thenReturn(modelPath);
    when(actuatorDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
    when(actuatorDouble.getName()).thenReturn(actuatorName);

    ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuatorDouble);
    double decimalUpperLimit = 3.5;
    String expected = String.valueOf(decimalUpperLimit);

    actuatorDataModel.setDecimalUpperBond(decimalUpperLimit);

    //Act
    String result = actuatorDataModel.getDecimalUpperBond();

    //Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldReturnStringRepresentationOfData_WhenToStringIsCalled() {
    //Arrange
    String _actuatorID = "123";
    String _deviceID = "device1";
    String _modelPath = "smart_home.domain.actuator.Switch.Switch";
    String _actuatorTypeID = "Switch";
    String _actuatorName = "Switch";
    ActuatorID actuatorIDDouble = mock(ActuatorID.class);
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    ActuatorName actuatorName = mock(ActuatorName.class);

    IActuator actuatorDouble = mock(IActuator.class);

    when(actuatorIDDouble.getID()).thenReturn(_actuatorID);
    when(deviceID.getID()).thenReturn(_deviceID);
    when(modelPath.getID()).thenReturn(_modelPath);
    when(actuatorTypeID.getID()).thenReturn(_actuatorTypeID);
    when(actuatorName.getName()).thenReturn(_actuatorName);

    when(actuatorDouble.getID()).thenReturn(actuatorIDDouble);
    when(actuatorDouble.getDeviceID()).thenReturn(deviceID);
    when(actuatorDouble.getModelPath()).thenReturn(modelPath);
    when(actuatorDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
    when(actuatorDouble.getName()).thenReturn(actuatorName);

    ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuatorDouble);
    String expected = "ActuatorDataModel{" +
        "actuatorID='" + _actuatorID + '\'' +
        ", deviceID='" + _deviceID + '\'' +
        ", modelPath='" + _modelPath + '\'' +
        ", actuatorTypeID='" + _actuatorTypeID + '\'' +
        ", actuatorName='" + _actuatorName + '\'' +
        ", integerLowerBond='" + null + '\'' +
        ", integerUpperBond='" + null + '\'' +
        ", decimalLowerBond='" + null + '\'' +
        ", decimalUpperBond='" + null + '\'' +
        '}';

    //Act
    String result = actuatorDataModel.toString();

    //Assert
    assertEquals(expected, result);
  }


}
