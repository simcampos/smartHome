/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.instant_power_consumption_sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;

class InstantPowerConsumptionSensorTest {

  /**
   * Should create instance of InstantPowerConsumptionSensor when constructor attributes are valid.
   */
  @Test
  void shouldCreateInstanceOfInstantPowerConsumptionSensor_whenConstructorAttributesAreValid() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("InstantPowerConsumption");

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      // Act
      InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(
          deviceID, modelPath, sensorTypeID, sensorName);

      // Assert
      assertNotNull(instantPowerConsumptionSensor);
    }

  }

  /**
   * Should create instance of InstantPowerConsumptionSensor when constructor attributes are valid,
   * including SensorID.
   */
  @Test
  void shouldCreateInstanceOfInstantPowerConsumptionSensor_whenConstructorAttributesAreValidIncludingSensorID() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("InstantPowerConsumption");

    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1234");

    // Act
    InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(
        deviceID, modelPath, sensorTypeID, sensorName, sensorID);

    // Assert
    assertNotNull(instantPowerConsumptionSensor);

  }

  /**
   * Should return InstantPowerConsumptionSensorID when getID is called.
   */
  @Test
  void shouldReturnInstantPowerConsumptionSensorID_WhenGetIDIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("InstantPowerConsumption");

    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1234");

    String expectedID = "1234";

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {

      InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(
          deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      SensorID result = instantPowerConsumptionSensor.getID();

      // Assert
      assertEquals(expectedID, result.getID());
    }
  }

  /**
   * Should return SensorName when getSensorName is called.
   */
  @Test
  void shouldReturnSensorName_WhenGetSensorNameIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    when(sensorName.toString()).thenReturn("Sensor Name");

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("InstantPowerConsumption");

    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1234");

    String expectedSensorName = "Sensor Name";

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      // Act
      InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(
          deviceID, modelPath, sensorTypeID, sensorName);

      SensorName result = instantPowerConsumptionSensor.getSensorName();

      // Assert
      assertEquals(expectedSensorName, result.toString());
    }
  }

  /**
   * Should return ModelPath when getModelPath is called.
   */
  @Test
  void shouldReturnModelPath_WhenGetModelPathMethodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.toString()).thenReturn("Model Path");

    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("InstantPowerConsumption");

    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1234");

    String expectedModelPath = "Model Path";

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      // Act
      InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(
          deviceID, modelPath, sensorTypeID, sensorName);

      ModelPath result = instantPowerConsumptionSensor.getModelPath();

      // Assert
      assertEquals(expectedModelPath, result.toString());
    }
  }

  /**
   * Should return SensorTypeID when getSensorTypeID is called.
   */
  @Test
  void shouldReturnSensorTypeID_WhenGetSensorTypeIDMethodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("InstantPowerConsumption");

    String expectedSensorTypeID = "InstantPowerConsumption";

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      // Act
      InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(
          deviceID, modelPath, sensorTypeID, sensorName);

      SensorTypeID result = instantPowerConsumptionSensor.getSensorTypeID();

      // Assert
      assertEquals(expectedSensorTypeID, result.getID());
    }
  }

  /**
   * Should return InstantPowerConsumptionValue when getValue is called.
   */
  @Test
  void shouldReturnInstantPowerConsumptionValue_WhenGetValueMethodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("InstantPowerConsumption");

    InstantPowerConsumptionValue instantPowerConsumptionValue = mock(
        InstantPowerConsumptionValue.class);

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      // Act
      InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(
          deviceID, modelPath, sensorTypeID, sensorName);

      InstantPowerConsumptionValue result = instantPowerConsumptionSensor.getValue();

      // Assert
      assertNotNull(result);
    }
  }

  /**
   * Should return DeviceID when getDeviceID is called.
   */
  @Test
  void shouldReturnDeviceID_WhenGetDeviceIDMethodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.toString()).thenReturn("Device ID");

    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("InstantPowerConsumption");

    String expectedDeviceID = "Device ID";

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      // Act
      InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(
          deviceID, modelPath, sensorTypeID, sensorName);

      DeviceID result = instantPowerConsumptionSensor.getDeviceID();

      // Assert
      assertEquals(expectedDeviceID, result.toString());
    }
  }

  /**
   * Should return true when instances are same object.
   */
  @Test
  void shouldReturnTrue_WhenInstancesAreSameObject() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("InstantPowerConsumption");

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(
          deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      boolean result = instantPowerConsumptionSensor.equals(instantPowerConsumptionSensor);

      // Assert
      assertTrue(result);
    }
  }

  /**
   * Should return false when instances are not equal.
   */
  @Test
  void shouldReturnFalse_WhenInstancesAreNotEqual() {
    // Arrange
    DeviceID deviceID1 = mock(DeviceID.class);
    ModelPath modelPath1 = mock(ModelPath.class);
    SensorName sensorName1 = mock(SensorName.class);
    SensorTypeID sensorTypeID1 = mock(SensorTypeID.class);
    when(sensorTypeID1.getID()).thenReturn("InstantPowerConsumption");

    DeviceID deviceID2 = mock(DeviceID.class);
    ModelPath modelPath2 = mock(ModelPath.class);
    SensorName sensorName2 = mock(SensorName.class);
    SensorTypeID sensorTypeID2 = mock(SensorTypeID.class);
    when(sensorTypeID2.getID()).thenReturn("InstantPowerConsumption");

    try (MockedConstruction<SensorID> sensorIDMockedConstruction1 = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {

      InstantPowerConsumptionSensor instantPowerConsumptionSensor1 = new InstantPowerConsumptionSensor(
          deviceID1, modelPath1, sensorTypeID1, sensorName1);
      InstantPowerConsumptionSensor instantPowerConsumptionSensor2 = new InstantPowerConsumptionSensor(
          deviceID2, modelPath2, sensorTypeID2, sensorName2);

      // Act
      boolean result = instantPowerConsumptionSensor1.equals(instantPowerConsumptionSensor2);

      // Assert
      assertFalse(result);

    }
  }

  /**
   * Should return false when object is not the instance of InstantPowerConsumptionSensor.
   */
  @Test
  void shouldReturnFalse_WhenObjectIsNotTheInstanceOfInstantPowerConsumptionSensor() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("InstantPowerConsumption");

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(
          deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      boolean result = instantPowerConsumptionSensor.equals(new Object());

      // Assert
      assertFalse(result);
    }
  }

  /**
   * Should return sensor hashcode.
   */
  @Test
  void shouldReturnInstantPowerConsumptionSensorHashCode_WhenHashCodeMethodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("InstantPowerConsumption");

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      InstantPowerConsumptionSensor instantPowerConsumptionSensor = new InstantPowerConsumptionSensor(
          deviceID, modelPath, sensorTypeID, sensorName);

      int expected = instantPowerConsumptionSensor.getID().hashCode();

      // Act
      int result = instantPowerConsumptionSensor.hashCode();

      // Assert
      assertEquals(expected, result);
    }
  }
}