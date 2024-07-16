/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.wind_sensor;

import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WindSensorTest {

  /**
   * Tests the instantiation of WindSensor when the constructor arguments are valid.
   */
  @Test
  void shouldInstantiateWindSensor_whenConstructorArgumentsAreValid() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Wind");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act
      WindSensor windSensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
      // Assert
      assertNotNull(windSensor);
    }
  }

  /**
   * Tests the instantiation of WindSensor when the deviceID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
    // Arrange
    DeviceID deviceID = null;
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Wind");

    String expectedMessage = "DeviceID is required";

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act + Assert
      Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
      });

      String actualMessage = exception.getMessage();

      //Assert
      assertEquals(expectedMessage, actualMessage);

    }
  }

  /**
   * Tests the instantiation of WindSensor when the modelPath is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = null;
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Wind");

    String expectedMessage = "ModelPath is required";

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act + Assert
      Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
      });

      String actualMessage = exception.getMessage();

      //Assert
      assertEquals(expectedMessage, actualMessage);
    }
  }

  /**
   * Tests the instantiation of WindSensor when the sensorName is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Wind");

    String expectedMessage = "SensorName is required";

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act + Assert
      Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
      });

      String actualMessage = exception.getMessage();

      //Assert
      assertEquals(expectedMessage, actualMessage);
    }
  }

  /**
   * Tests the instantiation of WindSensor when the sensorTypeID is null.
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
        new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
      });

      String actualMessage = exception.getMessage();

      //Assert
      assertEquals(expectedMessage, actualMessage);
    }
  }

  /**
   * Tests the instantiation of WindSensor when the sensorTypeID is not "Wind".
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNotWind() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("WrongID");

    String expectedMessage = "SensorTypeID must be 'Wind'";

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act + Assert
      Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
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

    when(sensorTypeID.getID()).thenReturn("Wind");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act
      WindSensor sensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);

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
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Wind");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act
      WindSensor sensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);

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

    when(sensorTypeID.getID()).thenReturn("Wind");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act
      WindSensor sensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);

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

    when(sensorTypeID.getID()).thenReturn("Wind");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act
      WindSensor sensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);

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

    when(sensorTypeID.getID()).thenReturn("Wind");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act
      WindSensor sensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);

      SensorTypeID result = sensor.getSensorTypeID();
      // Assert
      assertEquals(sensorTypeID, result);
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

    when(sensorTypeID.getID()).thenReturn("Wind");

    {
      WindSensor sensor;
      try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class,
          (mock, context) -> {
          })) {
        sensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
      }
      try (MockedConstruction<WindSensorValue> sensorValueConstruction = mockConstruction(
          WindSensorValue.class, (mock, context) -> {
          })) {
        // Act
        WindSensorValue result = sensor.getValue();
        // Assert
        List<WindSensorValue> constructed = sensorValueConstruction.constructed();
        assertEquals(constructed.get(0), result);
      }
    }
  }

  /**
   * Tests the equality of two WindSensors when they have the same deviceID.
   */
  @Test
  void shouldReturnFlase_whenTwoWindSensorsHaveDifferentDeviceIDs() {
    // Arrange
    DeviceID deviceID1 = mock(DeviceID.class);
    DeviceID deviceID2 = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Wind");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act
      WindSensor windSensor1 = new WindSensor(deviceID1, modelPath, sensorTypeID, sensorName);
      WindSensor windSensor2 = new WindSensor(deviceID2, modelPath, sensorTypeID, sensorName);

      boolean result = windSensor1.equals(windSensor2);
      // Assert
      assertFalse(result);
    }
  }

  /**
   * Tests the equality if Object is not a WindSensor.
   */
  @Test
  void shouldReturnFalse_WhenObjectIsNotAWindSensor() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Wind");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act
      WindSensor windSensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);

      boolean result = windSensor.equals(new Object());
      // Assert
      assertFalse(result);
    }
  }

  /**
   * Test if the hashCode method returns the value for WindSensor.
   */
  @Test
  void shouldReturnHashCode_whenHashCodeIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("Wind");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act
      WindSensor windSensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);

      int expected = windSensor.getID().hashCode();

      int result = windSensor.hashCode();

      // Assert
      assertEquals(expected, result);
    }
  }

  /**
   * Test if the toString method returns the value for WindSensor.
   */
  @Test
  void shouldReturnStringValue_whenToStringIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.getID()).thenReturn("DeviceID");
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.getID()).thenReturn("ModelPath");
    SensorName sensorName = mock(SensorName.class);
    when(sensorName.getSensorName()).thenReturn("SensorName");
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Wind");

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("MockedSensorID");
        })) {

      WindSensor windSensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName);
      String expected =
          "WindSensor: DeviceID= " + deviceID.getID() + " ModelPath= " + modelPath.getID()
              + " SensorTypeID= " + sensorTypeID.getID() + " SensorName= "
              + sensorName.getSensorName() + " SensorID= " + windSensor.getID().getID();

      // Act
      String result = windSensor.toString();

      // Assert
      assertEquals(expected, result);
    }
  }

  /**
   * Tests the instantiation of WindSensor when the sensorID is null.
   */
  @Test
  void shouldInstantiateWindSensor_WhenSensorIDIsValid() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorID sensorID = mock(SensorID.class);

    when(sensorTypeID.getID()).thenReturn("Wind");

    //Act
    WindSensor windSensor = new WindSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);

    //Assert
    assertNotNull(windSensor);
  }
}