/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.temperature_sensor;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

class TemperatureSensorTest {

  /**
   * Tests the instantiation of TemperatureSensor when the constructor arguments are valid.
   */
  @Test
  void shouldInstantiateTemperatureSensor_whenConstructorArgumentsAreValid() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Temperature");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act
      TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID,
          sensorName);

      // Assert
      assertNotNull(sensor);
    }
  }

  /**
   * Tests the instantiation of TemperatureSensor when the constructor arguments are valid,
   * including SensorId.
   */
  @Test
  void shouldInstantiateTemperatureSensor_whenConstructorArgumentsAreValidWithSensorId() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorID sensorID = mock(SensorID.class);

    when(sensorTypeID.getID()).thenReturn("Temperature");

    // Act
    TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName,
        sensorID);

    // Assert
    assertNotNull(sensor);
  }

  /**
   * Tests the instantiation of TemperatureSensor when the SensorID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorIDIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorID sensorID = null;

    when(sensorTypeID.getID()).thenReturn("Temperature");

    String expectedMessage = "SensorID is required";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);
    });

    //Assert
    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Tests the instantiation of TemperatureSensor when the deviceID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
    // Arrange
    DeviceID deviceID = null;
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Temperature");

    String expectedMessage = "DeviceID is required";

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act + Assert
      Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);
      });

      String actualMessage = exception.getMessage();

      //Assert
      assertEquals(expectedMessage, actualMessage);

    }
  }

  /**
   * Tests the instantiation of TemperatureSensor when the modelPath is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = null;
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Temperature");

    String expectedMessage = "ModelPath is required";

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act + Assert
      Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);
      });

      String actualMessage = exception.getMessage();

      //Assert
      assertEquals(expectedMessage, actualMessage);

    }
  }

  /**
   * Tests the instantiation of TemperatureSensor when the sensorName is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Temperature");

    String expectedMessage = "SensorName is required";

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act + Assert
      Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);
      });

      String actualMessage = exception.getMessage();

      //Assert
      assertEquals(expectedMessage, actualMessage);

    }
  }

  /**
   * Tests the instantiation of TemperatureSensor when the sensorTypeID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = null;

    String expectedMessage = "SensorTypeID is required";

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act + Assert
      Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);
      });

      String actualMessage = exception.getMessage();

      //Assert
      assertEquals(expectedMessage, actualMessage);

    }
  }

  /**
   * Tests the getter for the sensorID.
   */
  @Test
  void shouldReturnSensorID_whenGetIDIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Temperature");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID,
          sensorName);

      // Act
      SensorID result = sensor.getID();

      // Assert
      assertNotNull(result);
    }
  }

  /**
   * Tests the getter for the sensor name.
   */
  @Test
  void shouldReturnSensorName_whenGetSensorNameIsCalled() {
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Temperature");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID,
          sensorName);

      // Act
      SensorName result = sensor.getSensorName();

      // Assert
      assertEquals(sensorName, result);
    }
  }

  /**
   * Tests the getter for the model path.
   */
  @Test
  void shouldReturnModelPath_whenGetModelPathIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Temperature");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID,
          sensorName);

      // Act
      ModelPath result = sensor.getModelPath();

      // Assert
      assertEquals(modelPath, result);
    }
  }

  /**
   * Tests the getter for the device ID.
   */
  @Test
  void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Temperature");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID,
          sensorName);

      // Act
      DeviceID result = sensor.getDeviceID();

      // Assert
      assertEquals(deviceID, result);
    }
  }

  /**
   * Tests the getter for the sensor type ID.
   */
  @Test
  void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Temperature");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID,
          sensorName);

      // Act
      SensorTypeID result = sensor.getSensorTypeID();

      // Assert
      assertEquals(sensorTypeID, result);
    }
  }

  /**
   * Tests the getter for the sensor type ID when the sensor type ID is not of type 'Temperature'.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNotTemperature() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("WrongID");

    String expectedMessage = "SensorTypeID must be of type 'Temperature'";

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act + Assert
      Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);
      });

      String actualMessage = exception.getMessage();

      //Assert
      assertEquals(expectedMessage, actualMessage);

    }
  }

  /**
   * Tests the getter for the sensor value.
   */
  @Test
  void shouldReturnSensorValue_whenGetValueIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Temperature");

    TemperatureSensor sensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    TemperatureSensorValue result = sensor.getValue();

    // Assert
    double value = Double.parseDouble(result.toString());
    assertTrue(value >= -273.15);
  }

  /**
   * Tests if two TemperatureSensor objects have the same DeviceID.
   */
  @Test
  void shouldReturnFalse_whenTwoTemperatureSensorsHaveDifferentDeviceIDs() {
    // Arrange
    DeviceID deviceID1 = new DeviceID("1");
    DeviceID deviceID2 = new DeviceID("2");
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Temperature");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      TemperatureSensor temperatureSensor1 = new TemperatureSensor(deviceID1, modelPath,
          sensorTypeID, sensorName);
      TemperatureSensor temperatureSensor2 = new TemperatureSensor(deviceID2, modelPath,
          sensorTypeID, sensorName);

      // Act
      boolean result = temperatureSensor1.equals(temperatureSensor2);

      // Assert
      assertFalse(result);
    }
  }

  /**
   * Tests the equality of two TemperatureSensor objects.
   */
  @Test
  void shouldReturnFalse_whenObjectIsNotTemperatureSensor() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Temperature");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      TemperatureSensor temperatureSensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID,
          sensorName);

      // Act
      boolean result = temperatureSensor.equals(new Object());

      // Assert
      assertFalse(result);
    }
  }


  /**
   * Test if the hashCode method returns the value for TemperatureSensor.
   */
  @Test
  void shouldReturnHashCode_whenHashCodeIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Temperature");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      TemperatureSensor temperatureSensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID,
          sensorName);

      // Act
      int expected = temperatureSensor.getID().hashCode();

      int result = temperatureSensor.hashCode();

      // Assert
      assertEquals(expected, result);
    }
  }

  /**
   * Tests the toString method for TemperatureSensor.
   */
  @Test
  void shouldReturnToString_whenToStringIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Temperature");

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.toString()).thenReturn("MockedSensorID");
        })) {

      TemperatureSensor temperatureSensor = new TemperatureSensor(deviceID, modelPath, sensorTypeID,
          sensorName);
      String expectedValue = temperatureSensor.getValue().toString();

      TemperatureSensorValue temperatureSensorValue = mock(TemperatureSensorValue.class);
      when(temperatureSensorValue.toString()).thenReturn(expectedValue);

      String expected = "TemperatureSensor:" +
          " modelPath=" + modelPath +
          ", sensorName=" + sensorName +
          ", sensorID=MockedSensorID" +
          ", sensorTypeID=" + sensorTypeID +
          ", temperatureValue=" + expectedValue +
          ", deviceID=" + deviceID;

      // Act
      String result = temperatureSensor.toString();

      // Assert
      assertEquals(expected, result);
    }
  }
}
