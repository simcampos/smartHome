/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import smarthome.domain.actuator.set_integer_actuator.SetIntegerActuator;
import smarthome.domain.actuator.switch_actuator.SwitchActuator;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.IntegerLimits;
import smarthome.domain.value_object.ModelPath;


class ActuatorFactoryImplTest {

  /**
   * Test of createActuator method, of class ImpActuatorFactory when the constructor has 4
   * parameters
   */

  @Test
  void shouldCreateAValidActuator_whenTheConstructorHas4Parameters() {
    //Arrange
    // Arrange
    DeviceID deviceIdMock = mock(DeviceID.class);
    ModelPath modelPathMock = mock(ModelPath.class);
    when(modelPathMock.toString()).thenReturn(
        "smarthome.domain.actuator.switch_actuator.SwitchActuator");

    ActuatorTypeID actuatorTypeIdMock = mock(ActuatorTypeID.class);
    when(actuatorTypeIdMock.getID()).thenReturn("Switch");

    ActuatorName actuatorNameMock = mock(ActuatorName.class);
    ActuatorFactoryImpl impActuatorFactory = new ActuatorFactoryImpl();

    // Act
    SwitchActuator actuator = (SwitchActuator) impActuatorFactory.create(deviceIdMock,
        modelPathMock, actuatorTypeIdMock, actuatorNameMock);

    // Assert
    assertNotNull(actuator);

  }

  /**
   * Test of createActuator method, of class ImpActuatorFactory when the constructor has more than 4
   * parameters
   */

  @Test
  void shouldCreateAValidActuator_whenTheConstructorHasMoreThan4Parameters() {
    //Arrange
    // Arrange
    DeviceID deviceIdMock = mock(DeviceID.class);
    ModelPath modelPathMock = mock(ModelPath.class);
    when(modelPathMock.toString()).thenReturn(
        "smarthome.domain.actuator.set_integer_actuator.SetIntegerActuator");

    ActuatorTypeID actuatorTypeIDMock = mock(ActuatorTypeID.class);
    when(actuatorTypeIDMock.getID()).thenReturn("SetInteger");
    ActuatorName actuatorNameMock = mock(ActuatorName.class);
    IntegerLimits integerLimitsMock = mock(IntegerLimits.class);

    ActuatorFactoryImpl impActuatorFactory = new ActuatorFactoryImpl();

    // Act
    SetIntegerActuator actuator = (SetIntegerActuator) impActuatorFactory.create(
        deviceIdMock, modelPathMock, actuatorTypeIDMock, actuatorNameMock, integerLimitsMock);

    // Assert
    assertNotNull(actuator);
  }

  /**
   * Test for providing a wrong model path, which should return null.
   */
  @Test
  void shouldThrowExceptionWhenModelPathNotFound() {
    //Arrange
    // Arrange
    DeviceID deviceIdMock = mock(DeviceID.class);
    ModelPath modelPathMock = mock(ModelPath.class);
    when(modelPathMock.toString()).thenReturn("smarthome.domain.Sensor.WrongSensor");

    ActuatorTypeID actuatorTypeIDMock = mock(ActuatorTypeID.class);
    ActuatorName actuatorNameMock = mock(ActuatorName.class);

    ActuatorFactoryImpl impActuatorFactory = new ActuatorFactoryImpl();

    String expectedMessage = "The model path is not valid.";

    // Act + Assert
    Exception e = assertThrows(IllegalArgumentException.class, () -> {
      impActuatorFactory.create(deviceIdMock, modelPathMock, actuatorTypeIDMock, actuatorNameMock);
    });

    // Assert
    String actualMessage = e.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }


  /**
   * Test of createActuator method, of class ImpActuatorFactory when the constructor has 4
   * parameters and the wrong object type is passed
   */
  @Test
  void shouldReturnNull_whenParameterTypesAreNotAssignableFromParameters() {
    // Arrange
    DeviceID deviceIdMock = mock(DeviceID.class);
    ModelPath modelPathMock = mock(ModelPath.class);
    when(modelPathMock.toString()).thenReturn(
        "smarthome.domain.actuator.set_integer_actuator.SetIntegerActuator");

    ActuatorTypeID actuatorTypeIdMock = mock(ActuatorTypeID.class);
    ActuatorName actuatorNameMock = mock(ActuatorName.class);
    String wrongObject = "wrong object";

    ActuatorFactoryImpl impActuatorFactory = new ActuatorFactoryImpl();

    // Act
    IActuator result = impActuatorFactory.create(deviceIdMock, modelPathMock,
        actuatorTypeIdMock, actuatorNameMock, wrongObject);

    // Assert
    assertNull(result);
  }

  /**
   * Test for providing less than 4 constructor parameters, which should return null.
   */
  @Test
  void shouldThrowException_whenWrongNumberOfConstructorParameters() {
    // Arrange
    DeviceID deviceIdMock = mock(DeviceID.class);
    ModelPath modelPathMock = mock(ModelPath.class);
    when(modelPathMock.toString()).thenReturn(
        "smarthome.domain.actuator.switch_actuator.SwitchActuator");

    ActuatorName actuatorNameMock = mock(ActuatorName.class);

    ActuatorFactoryImpl impActuatorFactory = new ActuatorFactoryImpl();

    String expectedMessage = "At least 4 parameters are required.";

    // Act + Assert
    Exception e = assertThrows(IllegalArgumentException.class, () -> {
      impActuatorFactory.create(deviceIdMock, modelPathMock, actuatorNameMock);
    });

    // Assert
    String actualMessage = e.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void shouldThrowException_WhenModelPathNotPresentInParameters() {
    // Arrange
    DeviceID deviceIdMock = mock(DeviceID.class);
    ActuatorTypeID actuatorTypeIdMock = mock(ActuatorTypeID.class);
    ActuatorName actuatorNameMock = mock(ActuatorName.class);
    GPS gpsMock = mock(GPS.class);

    ActuatorFactoryImpl impActuatorFactory = new ActuatorFactoryImpl();

    String expectedMessage = "Model path is required.";

    // Act + Assert
    Exception e = assertThrows(IllegalArgumentException.class, () -> {
      impActuatorFactory.create(deviceIdMock, actuatorTypeIdMock, actuatorNameMock, gpsMock);
    });

    // Assert
    String actualMessage = e.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }
}


