/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.humidity_sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;

/**
 * Test class for the HumiditySensor class.
 */
class HumiditySensorTest {

  /**
   * Test case for instantiating HumiditySensor with valid constructor arguments.
   */
  @Test
  void shouldInstantiateHumiditySensor_whenConstructorArgumentsAreValid() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Humidity");

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      // Act
      HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
          sensorName);
      // Assert
      assertNotNull(humiditySensor);
    }
  }

  /**
   * Test case for getting SensorID.
   */
  @Test
  void shouldReturnSensorID_whenGetIDIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Humidity");

    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1234");

    String expectedID = "1234";

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {

      HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
          sensorName);

      // Act
      SensorID result = humiditySensor.getID();
      // Assert
      assertEquals(expectedID, result.getID());

    }
  }

  /**
   * Test case for getting SensorName.
   */
  @Test
  void shouldReturnSensorName_whenGetSensorNameIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    when(sensorName.toString()).thenReturn("SensorName");
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Humidity");

    String expectedSensorName = "SensorName";

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      // Act
      HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
          sensorName);
      SensorName result = humiditySensor.getSensorName();

      // Assert
      assertEquals(expectedSensorName, result.toString());
    }
  }

  /**
   * Test case for getting ModelPath.
   */
  @Test
  void shouldReturnModelPath_whenGetModelPathMethodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    when(modelPath.toString()).thenReturn("ModelPath");

    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Humidity");

    SensorID sensorID = mock(SensorID.class);
    when(sensorID.getID()).thenReturn("1234");

    String expectedModelPath = "ModelPath";

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {

      // Act
      HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
          sensorName);
      ModelPath result = humiditySensor.getModelPath();

      // Assert
      assertEquals(expectedModelPath, result.toString());
    }
  }

  /**
   * Test case for getting SensorTypeID.
   */
  @Test
  void shouldReturnSensorTypeID_whenGetSensorTypeIDMethodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Humidity");

    String expectedSensorTypeID = "Humidity";

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      // Act
      HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
          sensorName);
      SensorTypeID result = humiditySensor.getSensorTypeID();

      // Assert
      assertEquals(expectedSensorTypeID, result.getID());
    }
  }

  /**
   * Test case for getting DeviceID.
   */
  @Test
  void shouldReturnDeviceID_whenGetDeviceIDMethodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    when(deviceID.toString()).thenReturn("Device ID");

    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Humidity");

    String expectedDeviceID = "Device ID";

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      // Act
      HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
          sensorName);
      DeviceID result = humiditySensor.getDeviceID();

      // Assert
      assertEquals(expectedDeviceID, result.toString());
    }
  }

  /**
   * Test case for verifying equality of two objects.
   */
  @Test
  void shouldReturnTrue_WhenInstancesAreSameObject() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Humidity");

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
          sensorName);

      // Act
      boolean result = humiditySensor.equals(humiditySensor);

      // Assert
      assertTrue(result);
    }
  }

  /**
   * Test case for verifying inequality of two objects.
   */
  @Test
  void shouldReturnFalse_WhenInstancesAreNotEqual() {
    // Arrange
    DeviceID deviceID1 = mock(DeviceID.class);
    ModelPath modelPath1 = mock(ModelPath.class);
    SensorName sensorName1 = mock(SensorName.class);
    SensorTypeID sensorTypeID1 = mock(SensorTypeID.class);
    when(sensorTypeID1.getID()).thenReturn("Humidity");

    DeviceID deviceID2 = mock(DeviceID.class);
    ModelPath modelPath2 = mock(ModelPath.class);
    SensorName sensorName2 = mock(SensorName.class);
    SensorTypeID sensorTypeID2 = mock(SensorTypeID.class);
    when(sensorTypeID2.getID()).thenReturn("Humidity");

    try (MockedConstruction<SensorID> sensorIDMockedConstruction1 = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      HumiditySensor humiditySensor1 = new HumiditySensor(deviceID1, modelPath1, sensorTypeID1,
          sensorName1);
      HumiditySensor humiditySensor2 = new HumiditySensor(deviceID2, modelPath2, sensorTypeID2,
          sensorName2);

      // Act
      boolean result = humiditySensor1.equals(humiditySensor2);

      // Assert
      assertFalse(result);
    }
  }

  /**
   * Test case for getting hashCode of HumiditySensor.
   */
  @Test
  void shouldReturnHumiditySensorHashCode_WhenHashCodeMethodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Humidity");

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("1234");
        })) {
      HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
          sensorName);
      int expected = humiditySensor.getID().hashCode();
      // Act
      int result = humiditySensor.hashCode();

      // Assert
      assertEquals(expected, result);
    }
  }

  /**
   * Test case for throwing IllegalArgumentException when ModelPath is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = null;
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
        })) {
      // Act
      try {
        new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);
      } catch (IllegalArgumentException e) {
        // Assert
        assertEquals("ModelPath is required", e.getMessage());
      }
    }
  }

  /**
   * Test case for throwing IllegalArgumentException when SensorTypeID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = null;
    String expected = "SensorTypeID is required";
    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
        })) {
      // Act
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
          () -> new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName));
      // Assert
      assertEquals(expected, exception.getMessage());
    }
  }

  /**
   * Test case for throwing IllegalArgumentException when DeviceID is not null and type is
   * different.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceIDNotNullAndTypeDifferent() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Switch");

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
        })) {
      // Act
      try {
        new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);
      } catch (IllegalArgumentException e) {
        // Assert
        assertEquals("SensorTypeID must be of type 'Humidity'", e.getMessage());
      }
    }
  }

  /**
   * Test case for throwing IllegalArgumentException when SensorName is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
        })) {
      // Act
      try {
        new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);
      } catch (IllegalArgumentException e) {
        // Assert
        assertEquals("SensorName is required", e.getMessage());
      }
    }
  }

  /**
   * Test case for getting value of HumiditySensor.
   */
  @Test
  void shouldReturnValue_WhenGetValueMethodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Humidity");

    {
      HumiditySensor sensor;

      try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(
          SensorID.class, (mock, context) -> {
          })) {
        sensor = new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);
      }
      try (MockedConstruction<HumiditySensorValue> sensorValueConstruction = mockConstruction(
          HumiditySensorValue.class, (mock, context) -> {

          })) {
        HumiditySensorValue result = sensor.getValue();
        // Assert
        List<HumiditySensorValue> values = sensorValueConstruction.constructed();
        assertEquals(values.get(0), result);
      }
    }
  }

  /**
   * Test case for throwing IllegalArgumentException when DeviceID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
    // Arrange
    DeviceID deviceID = null;
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Humidity");

    try (MockedConstruction<SensorID> sensorIDMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
        })) {
      // Act
      try {
        new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName);
      } catch (IllegalArgumentException e) {
        // Assert
        assertEquals("DeviceID is required", e.getMessage());
      }
    }
  }

  /**
   * Test case for verifying inequality with null object.
   */
  @Test
  void shouldReturnFalse_WhenComparingObjectWithNull() {
    //Arrange
    DeviceID deviceIDDouble = mock(DeviceID.class);
    ModelPath modelPathDouble = mock(ModelPath.class);
    SensorName sensorNameDouble = mock(SensorName.class);
    SensorTypeID sensorTypeIDDouble = mock(SensorTypeID.class);

    when(sensorTypeIDDouble.getID()).thenReturn("Humidity");

    try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class)) {
      HumiditySensor humiditySensor = new HumiditySensor(deviceIDDouble, modelPathDouble,
          sensorTypeIDDouble, sensorNameDouble);
      //Act
      boolean result = humiditySensor.equals(null);
      //Assert
      assertFalse(result);
    }
  }

  /**
   * Test case to verify the instantiation of a HumiditySensor when the SensorID is valid.
   */
  @Test
  void shouldInstantiateHumiditySensor_WhenSensorIDIsValid() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorID sensorID = mock(SensorID.class);

    when(sensorTypeID.getID()).thenReturn("Humidity");

    // Act
    HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
        sensorName, sensorID);

    // Assert
    assertNotNull(humiditySensor);
  }
}




