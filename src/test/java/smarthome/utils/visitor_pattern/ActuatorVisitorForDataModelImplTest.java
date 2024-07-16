/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils.visitor_pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import smarthome.domain.actuator.blind_roller_actuator.BlindRollerActuator;
import smarthome.domain.actuator.set_decimal_actuator.SetDecimalActuator;
import smarthome.domain.actuator.set_integer_actuator.SetIntegerActuator;
import smarthome.domain.actuator.switch_actuator.SwitchActuator;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DecimalLimits;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.IntegerLimits;
import smarthome.domain.value_object.ModelPath;
import smarthome.persistence.data_model.ActuatorDataModel;

class ActuatorVisitorForDataModelImplTest {


  /**
   * Method to test the constructor of the ActuatorVisitorForDataModelImpl class.
   */
  @Test
  void shouldCreateActuatorVisitorForDataModel_whenConstructorIsCalled() {
    //Arrange
    ActuatorDataModel actuatorDataModel = new ActuatorDataModel();

    //Act
    ActuatorVisitorForDataModelImpl actuatorVisitor = new ActuatorVisitorForDataModelImpl(
        actuatorDataModel);

    //Assert
    assertNotNull(actuatorVisitor);
  }


  /**
   * Test if throws exception when ActuatorDataModel is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenActuatorDataModelIsNull() {
    //Arrange
    ActuatorDataModel actuatorDataModel = null;

    String expectedMessage = "Actuator Data Model is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ActuatorVisitorForDataModelImpl(actuatorDataModel));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }


  /**
   * Method to test the getActuatorDataModel method.
   */
  @Test
  void shouldReturnActuatorModel_whenGetActuatorDataModelIsCalled() {
    //Arrange
    ActuatorDataModel actuatorDataModel = new ActuatorDataModel();

    ActuatorVisitorForDataModelImpl actuatorVisitor = new ActuatorVisitorForDataModelImpl(
        actuatorDataModel);

    //Act
    ActuatorDataModel result = actuatorVisitor.getActuatorDataModel();

    //Assert
    assertNotNull(result);
  }

  /**
   * Method to test the visitorSetIntegerActuator method.
   */
  @Test
  void shouldSetSwitchActuatorData_whenVisitorSetSwitchActuatorIsCalled() {
    //Arrange
    ActuatorDataModel actuatorDataModel = new ActuatorDataModel();
    ActuatorVisitorForDataModelImpl actuatorVisitor = new ActuatorVisitorForDataModelImpl(
        actuatorDataModel);

    String _actuatorID = "test_actuatorID";
    String _deviceID = "test_deviceID";
    String _modelPath = "test_modelPath";
    String _actuatorTypeID = "test_actuatorTypeID";
    String _actuatorName = "test_actuatorName";

    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.getID()).thenReturn(_deviceID);
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.getID()).thenReturn(_modelPath);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn(_actuatorTypeID);
    ActuatorName actuatorName = mock(ActuatorName.class);
    when(actuatorName.getName()).thenReturn(_actuatorName);
    ActuatorID actuatorID = mock(ActuatorID.class);
    when(actuatorID.getID()).thenReturn(_actuatorID);

    SwitchActuator switchActuator = mock(SwitchActuator.class);
    when(switchActuator.getDeviceID()).thenReturn(deviceID);
    when(switchActuator.getModelPath()).thenReturn(modelPath);
    when(switchActuator.getActuatorTypeID()).thenReturn(actuatorTypeID);
    when(switchActuator.getName()).thenReturn(actuatorName);
    when(switchActuator.getID()).thenReturn(actuatorID);

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
    String result = actuatorVisitor.visitorSwitchActuator(switchActuator);
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldSetIntegerActuatorData_whenVisitorSetIntegerActuatorIsCalled() {
    //Arrange
    ActuatorDataModel actuatorDataModel = new ActuatorDataModel();
    ActuatorVisitorForDataModelImpl actuatorVisitor = new ActuatorVisitorForDataModelImpl(
        actuatorDataModel);

    String _actuatorID = "test_actuatorID";
    String _deviceID = "test_deviceID";
    String _modelPath = "test_modelPath";
    String _actuatorTypeID = "test_actuatorTypeID";
    String _actuatorName = "test_actuatorName";
    int lowerLimit = 0;
    int upperLimit = 100;

    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.getID()).thenReturn(_deviceID);
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.getID()).thenReturn(_modelPath);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn(_actuatorTypeID);
    ActuatorName actuatorName = mock(ActuatorName.class);
    when(actuatorName.getName()).thenReturn(_actuatorName);
    ActuatorID actuatorID = mock(ActuatorID.class);
    when(actuatorID.getID()).thenReturn(_actuatorID);
    IntegerLimits limits = mock(IntegerLimits.class);
    when(limits.getLowerLimit()).thenReturn(lowerLimit);
    when(limits.getUpperLimit()).thenReturn(upperLimit);

    SetIntegerActuator setIntegerActuator = mock(SetIntegerActuator.class);
    when(setIntegerActuator.getDeviceID()).thenReturn(deviceID);
    when(setIntegerActuator.getModelPath()).thenReturn(modelPath);
    when(setIntegerActuator.getActuatorTypeID()).thenReturn(actuatorTypeID);
    when(setIntegerActuator.getName()).thenReturn(actuatorName);
    when(setIntegerActuator.getID()).thenReturn(actuatorID);
    when(setIntegerActuator.getLimits()).thenReturn(limits);

    String expected = "ActuatorDataModel{" +
        "actuatorID='" + _actuatorID + '\'' +
        ", deviceID='" + _deviceID + '\'' +
        ", modelPath='" + _modelPath + '\'' +
        ", actuatorTypeID='" + _actuatorTypeID + '\'' +
        ", actuatorName='" + _actuatorName + '\'' +
        ", integerLowerBond='" + 0 + '\'' +
        ", integerUpperBond='" + 100 + '\'' +
        ", decimalLowerBond='" + null + '\'' +
        ", decimalUpperBond='" + null + '\'' +
        '}';
    //Act
    String result = actuatorVisitor.visitorSetIntegerActuator(setIntegerActuator);
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldSetDecimalActuatorData_whenVisitorSetDecimalActuatorIsCalled() {
    //Arrange
    ActuatorDataModel actuatorDataModel = new ActuatorDataModel();
    ActuatorVisitorForDataModelImpl actuatorVisitor = new ActuatorVisitorForDataModelImpl(
        actuatorDataModel);

    String _actuatorID = "test_actuatorID";
    String _deviceID = "test_deviceID";
    String _modelPath = "test_modelPath";
    String _actuatorTypeID = "test_actuatorTypeID";
    String _actuatorName = "test_actuatorName";
    double lowerLimit = 0.0;
    double upperLimit = 100.0;

    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.getID()).thenReturn(_deviceID);
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.getID()).thenReturn(_modelPath);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn(_actuatorTypeID);
    ActuatorName actuatorName = mock(ActuatorName.class);
    when(actuatorName.getName()).thenReturn(_actuatorName);
    ActuatorID actuatorID = mock(ActuatorID.class);
    when(actuatorID.getID()).thenReturn(_actuatorID);
    DecimalLimits limits = mock(DecimalLimits.class);
    when(limits.getLowerLimit()).thenReturn(lowerLimit);
    when(limits.getUpperLimit()).thenReturn(upperLimit);

    SetDecimalActuator setDecimalActuator = mock(SetDecimalActuator.class);
    when(setDecimalActuator.getDeviceID()).thenReturn(deviceID);
    when(setDecimalActuator.getModelPath()).thenReturn(modelPath);
    when(setDecimalActuator.getActuatorTypeID()).thenReturn(actuatorTypeID);
    when(setDecimalActuator.getName()).thenReturn(actuatorName);
    when(setDecimalActuator.getID()).thenReturn(actuatorID);
    when(setDecimalActuator.getLimits()).thenReturn(limits);

    String expected = "ActuatorDataModel{" +
        "actuatorID='" + _actuatorID + '\'' +
        ", deviceID='" + _deviceID + '\'' +
        ", modelPath='" + _modelPath + '\'' +
        ", actuatorTypeID='" + _actuatorTypeID + '\'' +
        ", actuatorName='" + _actuatorName + '\'' +
        ", integerLowerBond='" + null + '\'' +
        ", integerUpperBond='" + null + '\'' +
        ", decimalLowerBond='" + 0.0 + '\'' +
        ", decimalUpperBond='" + 100.0 + '\'' +
        '}';
    //Act
    String result = actuatorVisitor.visitorSetDecimalActuator(setDecimalActuator);
    //Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldSetBlindRollerActuatorData_whenVisitorBlindRollerActuatorIsCalled() {
    //Arrange
    ActuatorDataModel actuatorDataModel = new ActuatorDataModel();
    ActuatorVisitorForDataModelImpl actuatorVisitor = new ActuatorVisitorForDataModelImpl(
        actuatorDataModel);

    String _actuatorID = "test_actuatorID";
    String _deviceID = "test_deviceID";
    String _modelPath = "test_modelPath";
    String _actuatorTypeID = "test_actuatorTypeID";
    String _actuatorName = "test_actuatorName";

    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.getID()).thenReturn(_deviceID);
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.getID()).thenReturn(_modelPath);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn(_actuatorTypeID);
    ActuatorName actuatorName = mock(ActuatorName.class);
    when(actuatorName.getName()).thenReturn(_actuatorName);
    ActuatorID actuatorID = mock(ActuatorID.class);
    when(actuatorID.getID()).thenReturn(_actuatorID);

    BlindRollerActuator blindRollerActuator = mock(BlindRollerActuator.class);
    when(blindRollerActuator.getDeviceID()).thenReturn(deviceID);
    when(blindRollerActuator.getModelPath()).thenReturn(modelPath);
    when(blindRollerActuator.getActuatorTypeID()).thenReturn(actuatorTypeID);
    when(blindRollerActuator.getName()).thenReturn(actuatorName);
    when(blindRollerActuator.getID()).thenReturn(actuatorID);

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
    String result = actuatorVisitor.visitorBlindRollerActuator(blindRollerActuator);

    //Assert
    assertEquals(expected, result);
  }

}
