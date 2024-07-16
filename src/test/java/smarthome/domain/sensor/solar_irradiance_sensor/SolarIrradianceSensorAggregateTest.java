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

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.visitor_pattern.ISensorVisitor;

class SolarIrradianceSensorAggregateTest {

  /**
   * Tests the instantiation of SolarIrradianceSensor when the constructor arguments are valid.
   */
  @Test
  void shouldInstantiateSolarIrradianceSensor_whenConstructorArgumentsAreValid() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

    // Act
    SolarIrradianceSensor sensor =
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Assert
    assertNotNull(sensor);
  }

  /**
   * Tests the instantiation of SolarIrradianceSensor when the constructor arguments are valid,
   * including the sensorID.
   */
  @Test
  void shouldInstantiateSolarIrradianceSensor_whenConstructorArgumentsAreValidIncludingSensorID() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");
    SensorID sensorID = new SensorID("ID");

    // Act
    SolarIrradianceSensor sensor =
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);

    // Assert
    assertNotNull(sensor);
  }

  /**
   * Tests the instantiation of SolarIrradianceSensor when the deviceID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
    // Arrange
    DeviceID deviceID = null;
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

    String expectedMessage = "DeviceID is required";

    // Act
    Exception e =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
            });

    // Assert
    String actualMessage = e.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests the instantiation of SolarIrradianceSensor when the modelPath is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = null;
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

    String expectedMessage = "ModelPath is required";

    // Act
    Exception e =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
            });

    // Assert
    String actualMessage = e.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests the instantiation of SolarIrradianceSensor when the sensorName is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

    String expectedMessage = "SensorName is required";

    // Act
    Exception e =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
            });

    // Assert
    String actualMessage = e.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests the instantiation of SolarIrradianceSensor when the sensorTypeID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = null;

    String expectedMessage = "SensorTypeID is required";

    // Act
    Exception e =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
            });

    // Assert
    String actualMessage = e.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests the instantiation of SolarIrradianceSensor with SensorID.
   */
  @Test
  void shouldInstantiateSolarIrradianceSensor_whenSensorIDIsProvided() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");
    SensorID sensorID = new SensorID("ID");

    // Act
    SolarIrradianceSensor sensor =
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);

    // Assert
    assertNotNull(sensor);
  }

  /**
   * Tests the instantiation of SolarIrradianceSensor when the sensorID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorIDIsNull() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");
    SensorID sensorID = null;

    String expectedMessage = "SensorID is required";

    // Act
    Exception e =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);
            });

    // Assert
    String actualMessage = e.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests the instantiation of SolarIrradianceSensor when the DeviceID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceIDIsNullIncludingSensorID() {
    // Arrange
    DeviceID deviceID = null;
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");
    SensorID sensorID = new SensorID("ID");

    String expectedMessage = "DeviceID is required";

    // Act
    Exception e =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);
            });

    // Assert
    String actualMessage = e.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests the instantiation of SolarIrradianceSensor when the ModelPath is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenModelPathIsNullIncludingSensorID() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = null;
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");
    SensorID sensorID = new SensorID("ID");

    String expectedMessage = "ModelPath is required";

    // Act
    Exception e =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);
            });

    // Assert
    String actualMessage = e.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests the instantiation of SolarIrradianceSensor when the SensorName is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorNameIsNullIncludingSensorID() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");
    SensorID sensorID = new SensorID("ID");

    String expectedMessage = "SensorName is required";

    // Act
    Exception e =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);
            });

    // Assert
    String actualMessage = e.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests the instantiation of SolarIrradianceSensor when the SensorTypeID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNullIncludingSensorID() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = null;
    SensorID sensorID = new SensorID("ID");

    String expectedMessage = "SensorTypeID is required";

    // Act
    Exception e =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);
            });

    // Assert
    String actualMessage = e.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests the instantiation of SolarIrradianceSensor when the sensorTypeID is not SolarIrradiance.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNotSolarIrradiance() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("NotSolarIrradiance");

    String expectedMessage = "SensorTypeID must be SolarIrradiance";

    // Act
    Exception e =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
            });

    // Assert
    String actualMessage = e.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests the getter for the sensorID.
   */
  @Test
  void shouldReturnSensorID_whenGetSensorIDIsCalled() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

    SolarIrradianceSensor solarIrradianceSensor =
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    SensorID sensorID = solarIrradianceSensor.getID();

    // Assert
    assertNotNull(sensorID);
  }

  /**
   * Tests the getter for the deviceID.
   */
  @Test
  void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

    SolarIrradianceSensor solarIrradianceSensor =
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    DeviceID result = solarIrradianceSensor.getDeviceID();

    // Assert
    assertEquals(result, deviceID);
  }

  /**
   * Tests the getter for the modelPath.
   */
  @Test
  void shouldReturnModelPath_whenGetModelPathIsCalled() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

    SolarIrradianceSensor solarIrradianceSensor =
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    ModelPath result = solarIrradianceSensor.getModelPath();

    // Assert
    assertEquals(result, modelPath);
  }

  /**
   * Tests the getter for the sensorName.
   */
  @Test
  void shouldReturnSensorName_whenGetSensorNameIsCalled() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

    SolarIrradianceSensor solarIrradianceSensor =
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    SensorName result = solarIrradianceSensor.getSensorName();

    // Assert
    assertEquals(result, sensorName);
  }

  /**
   * Tests the getter for the sensorTypeID.
   */
  @Test
  void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

    SolarIrradianceSensor solarIrradianceSensor =
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    SensorTypeID result = solarIrradianceSensor.getSensorTypeID();

    // Assert
    assertEquals(result, sensorTypeID);
  }

  /**
   * Tests the getter for the sensorValue.
   */
  @Test
  void shouldReturnSensorValue_whenGetSensorValueIsCalled() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

    SolarIrradianceSensor solarIrradianceSensor =
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

    String expectedValue = "4500";

    // Act
    SolarIrradianceValue result = solarIrradianceSensor.getValue();

    // Assert
    assertEquals(result.toString(), expectedValue);
  }

  /**
   * Tests the equals method of SolarIrradianceSensor when the object is an instance of
   * SolarIrradianceSensor.
   */
  @Test
  void shouldReturnTrue_whenEqualsIsCalledWithSolarIrradianceSensor() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

    SolarIrradianceSensor solarIrradianceSensor =
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    boolean result = solarIrradianceSensor.equals(solarIrradianceSensor);

    // Assert
    assertTrue(result);
  }

  /**
   * Tests the equals method of SolarIrradianceSensor when the object is not an instance of
   * SolarIrradianceSensor.
   */
  @Test
  void shouldReturnFalse_whenEqualsIsCalledWithNonSolarIrradianceSensor() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

    SolarIrradianceSensor solarIrradianceSensor1 =
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
    SolarIrradianceSensor solarIrradianceSensor2 =
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    boolean result = solarIrradianceSensor1.equals(solarIrradianceSensor2);

    // Assert
    assertFalse(result);
  }

  /**
   * Tests returning hash code.
   */
  @Test
  void shouldReturnHashCode_whenHashCodeIsCalled() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

    SolarIrradianceSensor solarIrradianceSensor =
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);
    int expected = solarIrradianceSensor.getID().hashCode();
    // Act
    int result = solarIrradianceSensor.hashCode();

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldAcceptVisitorAndReturnInstanceOfObjectInString() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorName sensorName = new SensorName("sensorName");
    SensorTypeID sensorTypeID = new SensorTypeID("SolarIrradiance");

    SolarIrradianceSensor sensor =
        new SolarIrradianceSensor(deviceID, modelPath, sensorTypeID, sensorName);

    String expected = sensor.toString();
    ISensorVisitor visitor = mock(ISensorVisitor.class);
    //Act
    String result = sensor.accept(visitor);

    // Assert
    assertEquals(expected, result);
  }
}
