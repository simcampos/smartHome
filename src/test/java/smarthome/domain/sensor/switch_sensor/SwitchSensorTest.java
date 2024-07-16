/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.switch_sensor;

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

class SwitchSensorTest {

  /**
   * Should create instance of SwitchSensor when constructor attributes are valid.
   */
  @Test
  void shouldCreateInstanceOfSwitchSensor_whenConstructorAttributesAreValid() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Switch");

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      // Act
      SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Assert
      assertNotNull(switchSensor);
    }

  }

  /**
   * Should return SwitchSensorID when getID is called.
   */
  @Test
  void shouldReturnSwitchSensorID_WhenGetIDIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Switch");

    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1234");

    String expectedID = "1234";

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {

      SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      SensorID result = switchSensor.getID();

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
    when(sensorTypeID.getID()).thenReturn("Switch");

    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1234");

    String expectedSensorName = "Sensor Name";

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      // Act
      SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

      SensorName result = switchSensor.getSensorName();

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
    when(sensorTypeID.getID()).thenReturn("Switch");

    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1234");

    String expectedModelPath = "Model Path";

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      // Act
      SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

      ModelPath result = switchSensor.getModelPath();

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
    when(sensorTypeID.getID()).thenReturn("Switch");

    String expectedSensorTypeID = "Switch";

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      // Act
      SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

      SensorTypeID result = switchSensor.getSensorTypeID();

      // Assert
      assertEquals(expectedSensorTypeID, result.getID());
    }
  }

  /**
   * Should return SwitchSensorValue when getValue is called.
   */
  @Test
  void shouldReturnValue_WhenGetValueMethodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Switch");

    SwitchSensorValue sensorValue = mock(SwitchSensorValue.class);
    when(sensorValue.toString()).thenReturn("On");


    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      // Act
      SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

      SwitchSensorValue result = switchSensor.getValue();

      // Assert
      String state = result.toString();
      assertTrue("On".equals(state) || "Off".equals(state), "State should be 'On' or 'Off'.");
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
    when(sensorTypeID.getID()).thenReturn("Switch");

    String expectedDeviceID = "Device ID";

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      // Act
      SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

      DeviceID result = switchSensor.getDeviceID();

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
    when(sensorTypeID.getID()).thenReturn("Switch");

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      boolean result = switchSensor.equals(switchSensor);

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
    when(sensorTypeID1.getID()).thenReturn("Switch");

    DeviceID deviceID2 = mock(DeviceID.class);
    ModelPath modelPath2 = mock(ModelPath.class);
    SensorName sensorName2 = mock(SensorName.class);
    SensorTypeID sensorTypeID2 = mock(SensorTypeID.class);
    when(sensorTypeID2.getID()).thenReturn("Switch");

    try (MockedConstruction<SensorID> sensorIDMockedConstruction1 = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {

      SwitchSensor switchSensor1 = new SwitchSensor(deviceID1, modelPath1, sensorTypeID1,
          sensorName1);
      SwitchSensor switchSensor2 = new SwitchSensor(deviceID2, modelPath2, sensorTypeID2,
          sensorName2);

      // Act
      boolean result = switchSensor1.equals(switchSensor2);

      // Assert
      assertFalse(result);

    }
  }

  /**
   * Should return false when object is not the instance of SwitchSensor.
   */
  @Test
  void shouldReturnFalse_WhenObjectIsNotTheInstanceOfSwitchSensor() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Switch");

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      boolean result = switchSensor.equals(new Object());

      // Assert
      assertFalse(result);
    }
  }

  /**
   * Should return sensor hashcode.
   */
  @Test
  void shouldReturnSwitchSensorHashCode_WhenHashCodeMethodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Switch");

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

      int expected = switchSensor.getID().hashCode();

      // Act
      int result = switchSensor.hashCode();

      // Assert
      assertEquals(expected, result);
    }
  }

  /**
   * Should return SwitchSensor in string format.
   */
  @Test
  void shouldReturnSwitchSensorInString_WhenToStringMethodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.getID()).thenReturn("Device ID");

    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.getID()).thenReturn("Model Path");

    SensorName sensorName = mock(SensorName.class);
    when(sensorName.getSensorName()).thenReturn("Sensor Name");

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Switch");

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

      String expected =
          "SwitchSensor: DeviceID= " + deviceID.getID() + " ModelPath= " + modelPath.getID()
              + " SensorTypeID= " + sensorTypeID.getID() + " SensorName= "
              + sensorName.getSensorName() + " SensorID= " + switchSensor.getID().getID();

      // Act
      String result = switchSensor.toString();

      // Assert
      assertEquals(expected, result);
    }
  }

  /**
   * Should instantiate SwitchSensor when sensorID is valid.
   */
  @Test
  void shouldInstantiateSwitchSensor_WhenSensorIDIsValid() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorName sensorName = mock(SensorName.class);
    SensorID sensorID = mock(SensorID.class);

    when(sensorTypeID.getID()).thenReturn("Switch");

    // Act
    SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName,
        sensorID);

    // Assert
    assertNotNull(switchSensor);
  }
}