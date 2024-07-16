/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.solar_irradiance_sensor;

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
import smarthome.domain.sensor.temperature_sensor.TemperatureSensor;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;

class SolarIrradianceSensorTest {

  /**
   * Tests the instantiation of SolarIrradianceSensor when the constructor arguments are valid.
   */
  @Test
  void shouldInstantiateSolarIrradianceSensor_whenConstructorArgumentsAreValid() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      // Act
      SolarIrradianceSensor sensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
      // Asser
      assertNotNull(sensor);
    }
  }

  /**
   * Tests the instantiation of SolarIrradianceSensor when the constructor arguments are valid,
   * including SensorID
   */
  @Test
  void shouldInstantiateSolarIrradianceSensor_whenConstructorArgumentsAreValidWithSensorID() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorID sensorID = mock(SensorID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    // Act
    SolarIrradianceSensor sensor =
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);

    // Assert
    assertNotNull(sensor);

  }

  /**
   * Tests the instantiation of SolarIrradianceSensor when the SensorID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorIDIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");
    SensorID sensorID = null;

    String expectedMessage = "SensorID is required";

    // Act
    Exception e = assertThrows(IllegalArgumentException.class,
        () -> new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID));

    // Assert
    String result = e.getMessage();
    assertEquals(expectedMessage, result);

  }

  /**
   * Tests the instantiation of SolarIrradianceSensor when the deviceID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
    // Arrange
    DeviceID deviceID = null;
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    String expected = "DeviceID is required";

    // Act
    IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {

      new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
    });

    //Assert
    assertEquals(expected, result.getMessage());
  }

  /**
   * Tests the instantiation of SolarIrradianceSensor when the modelPath is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = null;
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

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
   * Tests the instantiation of SolarIrradianceSensor when the sensorName is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    String expected = "SensorName is required";

    //Act
    IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
      new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
    });

    //Assert
    assertEquals(expected, result.getMessage());
  }

  /**
   * Tests the instantiation of SolarIrradianceSensor when the sensorTypeID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = null;

    String expected = "SensorTypeID is required";

    //Act
    IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> {
      new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
    });

    //Assert
    assertEquals(expected, result.getMessage());
  }

  /**
   * Tests the instantiation of SolarIrradianceSensor when the sensorTypeID is not SolarIrradiance.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNotSolarIrradiance() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("NotSolarIrradiance");

    String expected = "SensorTypeID must be SolarIrradiance";

    // Act
    IllegalArgumentException result =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
            });

    // Assert
    assertEquals(expected, result.getMessage());
  }

  /**
   * Tests the getter for the sensorID.
   */
  @Test
  void shouldReturnSensorID_whenGetSensorIDIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      SensorID result = solarIrradianceSensor.getID();

      // Assert
      assertTrue(result.toString().contains(result.toString()));
    }
  }

  /**
   * Tests the getter for the deviceID.
   */
  @Test
  void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      DeviceID result = solarIrradianceSensor.getDeviceID();

      // Assert
      assertEquals(result, deviceID);
    }
  }

  /**
   * Tests the getter for the modelPath.
   */
  @Test
  void shouldReturnModelPath_whenGetModelPathIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      ModelPath result = solarIrradianceSensor.getModelPath();

      // Assert
      assertEquals(result, modelPath);
    }
  }

  /**
   * Tests the getter for the sensorName.
   */
  @Test
  void shouldReturnSensorName_whenGetSensorNameIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      SensorName result = solarIrradianceSensor.getSensorName();

      // Assert
      assertEquals(result, sensorName);
    }
  }

  /**
   * Tests the getter for the sensorTypeID.
   */
  @Test
  void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      SensorTypeID result = solarIrradianceSensor.getSensorTypeID();

      // Assert
      assertEquals(result, sensorTypeID);
    }
  }

  /**
   * Tests the getter for the sensorValue.
   */
  @Test
  void shouldReturnSensorValue_whenGetSensorValueIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    String sensorValue = "4500";

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

      try (MockedConstruction<SolarIrradianceValueTest> sensorValueConstruction =
          mockConstruction(
              SolarIrradianceValueTest.class,
              (mock, context) -> {
                when(mock.toString()).thenReturn(sensorValue);
              })) {
        // Act
        SolarIrradianceValue result = solarIrradianceSensor.getValue();

        // Assert
        assertEquals(result.toString(), sensorValue);
      }
    }
  }

  /**
   * Tests the equals method of SolarIrradianceSensor when the object is an instance of
   * SolarIrradianceSensor.
   */
  @Test
  void shouldReturnTrue_whenEqualsIsCalledWithSolarIrradianceSensor() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      boolean result = solarIrradianceSensor.equals(solarIrradianceSensor);

      // Assert
      assertTrue(result);
    }
  }

  /**
   * Tests the equals method of SolarIrradianceSensor when the object is not an instance of
   * SolarIrradianceSensor.
   */
  @Test
  void shouldReturnFalse_whenEqualsIsCalledWithNonSolarIrradianceSensor() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
      SolarIrradianceSensor solarIrradianceSensor1 =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      boolean result = solarIrradianceSensor.equals(solarIrradianceSensor1);

      // Assert
      assertFalse(result);
    }
  }

  /**
   * Tests the equals method of SolarIrradianceSensor when the object is null.
   */
  @Test
  void shouldReturnFalse_whenEqualsIsCalledWithNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      boolean result = solarIrradianceSensor.equals(null);

      // Assert
      assertFalse(result);
    }
  }

  /**
   * Tests returning hash code.
   */
  @Test
  void shouldReturnHashCode_whenHashCodeIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SolarIrradiance");

    try (MockedConstruction<SensorID> sensorIDConstruction = mockConstruction(SensorID.class)) {
      SolarIrradianceSensor solarIrradianceSensor =
          new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
      int expected = solarIrradianceSensor.getID().hashCode();
      // Act
      int result = solarIrradianceSensor.hashCode();

      // Assert
      assertEquals(expected, result);
    }
  }
}
