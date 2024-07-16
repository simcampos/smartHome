/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import smarthome.domain.actuator.ActuatorFactoryImpl;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.actuator.IActuatorFactory;
import smarthome.domain.actuator.set_decimal_actuator.SetDecimalActuator;
import smarthome.domain.actuator.set_integer_actuator.SetIntegerActuator;
import smarthome.domain.actuator.switch_actuator.SwitchActuator;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DecimalLimits;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.IntegerLimits;
import smarthome.domain.value_object.ModelPath;
import smarthome.persistence.data_model.ActuatorDataModel;

class ActuatorDataModelAssemblerTest {

  @Test
  void shouldInstantiateGenericActuatorWhenGivenValidParameters() {
    // Arrange
    String deviceIDValue = "some-device-id";
    String modelPathValue = "smarthome.domain.actuator.switch_actuator.SwitchActuator";
    String actuatorNameValue = "actuatorName";
    String actuatorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    ActuatorName actuatorName = new ActuatorName(actuatorNameValue);
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDValue);

    IActuator actuator = new SwitchActuator(deviceID, modelPath, actuatorTypeID, actuatorName);

    ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuator);
    IActuatorFactory actuatorFactory = new ActuatorFactoryImpl();
    ActuatorDataModelAssembler actuatorDataModelAssembler = new ActuatorDataModelAssembler(
        actuatorFactory);
    // Act
    IActuator actuator2 = actuatorDataModelAssembler.toDomain(actuatorDataModel);

    // Assert
    assertEquals(actuator, actuator2);
  }

  @Test
  void shouldInstantiateActuatorWithIntegerLimitsWhenGivenValidParameters() {
    // Arrange
    String deviceIDValue = "some-device-id";
    String modelPathValue = "smarthome.domain.actuator.set_integer_actuator.SetIntegerActuator";
    String actuatorNameValue = "actuatorName";
    String actuatorTypeIDValue = "SetInteger";
    Integer min = 0;
    Integer max = 100;

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    ActuatorName actuatorName = new ActuatorName(actuatorNameValue);
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDValue);
    IntegerLimits integerLimits = new IntegerLimits(min, max);

    IActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName,
        integerLimits);

    ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuator);
    actuatorDataModel.setIntegerLowerBond(min);
    actuatorDataModel.setIntegerUpperBond(max);
    IActuatorFactory actuatorFactory = new ActuatorFactoryImpl();
    ActuatorDataModelAssembler actuatorDataModelAssembler = new ActuatorDataModelAssembler(
        actuatorFactory);
    // Act
    IActuator actuator2 = actuatorDataModelAssembler.toDomain(actuatorDataModel);

    // Assert
    assertEquals(actuator, actuator2);
  }

  @Test
  void shouldInstantiateActuatorWithDecimalLimitsWhenGivenValidParameters() {
    // Arrange
    String deviceIDValue = "some-device-id";
    String modelPathValue = "smarthome.domain.actuator.set_decimal_actuator.SetDecimalActuator";
    String actuatorNameValue = "actuatorName";
    String actuatorTypeIDValue = "SetDecimal";
    Double min = 0.0;
    Double max = 100.0;

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    ActuatorName actuatorName = new ActuatorName(actuatorNameValue);
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeIDValue);
    DecimalLimits decimalLimits = new DecimalLimits(min, max);

    IActuator actuator = new SetDecimalActuator(deviceID, modelPath, actuatorTypeID, actuatorName,
        decimalLimits);

    ActuatorDataModel actuatorDataModel = new ActuatorDataModel(actuator);
    actuatorDataModel.setDecimalLowerBond(min);
    actuatorDataModel.setDecimalUpperBond(max);
    IActuatorFactory actuatorFactory = new ActuatorFactoryImpl();
    ActuatorDataModelAssembler actuatorDataModelAssembler = new ActuatorDataModelAssembler(
        actuatorFactory);
    // Act
    IActuator actuator2 = actuatorDataModelAssembler.toDomain(actuatorDataModel);

    // Assert
    assertEquals(actuator, actuator2);
  }


}