/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.percentage_position_sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

/**
 * Test cases for the PercentagePositionSensor class.
 */
class PercentagePositionSensorTest {

  /**
   * Test to verify that an instance of PercentagePositionSensor is created when the constructor
   * arguments are valid.
   */
  @Test
  void shouldInstantiatePercentagePositionSensor_whenConstructorArgumentsAreValid() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("PercentagePosition");

    try (MockedConstruction<SensorID> mocked = mockConstruction(SensorID.class)) {

      // Act
      PercentagePositionSensor percentagePositionSensor =
          new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Assert
      assertNotNull(percentagePositionSensor);
    }
  }

  /**
   * Test to verify with mock that an IllegalArgumentException is thrown when ModelPath is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = null;
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("PercentagePosition");

    // Act & Assert
    IllegalArgumentException thrown =
        assertThrows(
            IllegalArgumentException.class,
            () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName));
    assertEquals("ModelPath is required", thrown.getMessage());
  }

  /**
   * Test to verify with mock that an IllegalArgumentException is thrown when SensorName is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    String expectedMessage = "SensorName is required";

    try (MockedConstruction<SensorID> sensorIdMockedConstruction =
        mockConstruction(SensorID.class)) {

      // Act & Assert
      IllegalArgumentException thrown =
          assertThrows(
              IllegalArgumentException.class,
              () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName));
      assertEquals(expectedMessage, thrown.getMessage());
    }
  }

  /**
   * Test to verify with mock that an IllegalArgumentException is thrown when SensorTypeID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = null;

    String expectedMessage = "SensorTypeID is required";

    try (MockedConstruction<SensorID> sensorIdMockedConstruction =
        mockConstruction(SensorID.class)) {

      // Act & Assert
      IllegalArgumentException thrown =
          assertThrows(
              IllegalArgumentException.class,
              () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName));
      assertEquals(expectedMessage, thrown.getMessage());
    }
  }

  /**
   * Test to verify with mock that an IllegalArgumentException is thrown when DeviceID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
    // Arrange
    DeviceID deviceID = null;
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("PercentagePosition");

    String expectedMessage = "DeviceID is required";

    try (MockedConstruction<SensorID> sensorIdMockedConstruction =
        mockConstruction(SensorID.class)) {
      // Act & Assert
      IllegalArgumentException thrown =
          assertThrows(
              IllegalArgumentException.class,
              () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName));
      assertEquals(expectedMessage, thrown.getMessage());
    }
  }

  /**
   * Test to verify that the correct value is returned when getValue is called.
   */
  @Test
  void shouldReturnSensorValue_whenGetValueIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("PercentagePosition");

    int expected = 14;

    try (MockedConstruction<SensorID> sensorIdMockedConstruction =
        mockConstruction(SensorID.class)) {

      // Act
      PercentagePositionSensor percentagePositionSensor =
          new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

      int value = Integer.parseInt(percentagePositionSensor.getValue().toString());

      // Assert
      assertEquals(expected, value);
    }
  }

  /**
   * Test to verify with mock that the correct ID is returned when getID is called.
   */
  @Test
  void shouldReturnSensorID_whenGetIDIsCalled() {
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorID sensorID = mock(SensorID.class);

    when(sensorTypeID.getID()).thenReturn("PercentagePosition");

    try (MockedConstruction<SensorID> sensorIdMockedConstruction =
        mockConstruction(SensorID.class)) {

      PercentagePositionSensor percentagePositionSensor =
          new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);

      // Act
      SensorID result = percentagePositionSensor.getID();

      // Assert
      assertNotNull(result);
    }
  }

  /**
   * Test to verify that the correct SensorTypeID is returned when getSensorTypeID is called.
   */
  @Test
  void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("PercentagePosition");

    try (MockedConstruction<SensorID> sensorIdMockedConstruction =
        mockConstruction(SensorID.class)) {

      PercentagePositionSensor percentagePositionSensor =
          new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      SensorTypeID result = percentagePositionSensor.getSensorTypeID();

      // Assert
      assertEquals(sensorTypeID, result);
    }
  }

  /**
   * Test to verify that the correct DeviceID is returned when getDeviceID is called.
   */
  @Test
  void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("PercentagePosition");

    try (MockedConstruction<SensorID> sensorIdMockedConstruction =
        mockConstruction(SensorID.class)) {

      PercentagePositionSensor percentagePositionSensor =
          new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      DeviceID result = percentagePositionSensor.getDeviceID();

      // Assert
      assertEquals(deviceID, result);
    }
  }

  /**
   * Test to verify with mock that the correct ModelPath is returned when getModelPath is called.
   */
  @Test
  void shouldReturnSensorModelPath_whenGetModelPathIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("PercentagePosition");

    try (MockedConstruction<SensorID> sensorIdMockedConstruction =
        mockConstruction(SensorID.class)) {

      PercentagePositionSensor percentagePositionSensor =
          new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      ModelPath result = percentagePositionSensor.getModelPath();

      // Assert
      assertEquals(modelPath, result);
    }
  }

  /**
   * Test to verify with mock that the correct SensorName is returned when getName is called.
   */
  @Test
  void shouldReturnSensorName_whenGetNameIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    when(sensorTypeID.getID()).thenReturn("PercentagePosition");

    try (MockedConstruction<SensorID> sensorIdMockedConstruction =
        mockConstruction(SensorID.class)) {

      PercentagePositionSensor percentagePositionSensor =
          new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

      // Act
      SensorName result = percentagePositionSensor.getSensorName();

      // Assert
      assertEquals(sensorName, result);
    }
  }

  /**
   * Test to verify that the correct SensorID is returned when getID is called.
   */
  @Test
  void shouldReturnFalse_WhenInstancesAreNotEqual() {
    // Arrange
    DeviceID deviceID1 = mock(DeviceID.class);
    ModelPath modelPath1 = mock(ModelPath.class);
    SensorName sensorName1 = mock(SensorName.class);
    SensorTypeID sensorTypeID1 = mock(SensorTypeID.class);
    when(sensorTypeID1.getID()).thenReturn("PercentagePosition");
    SensorID sensorID1 = mock(SensorID.class);

    DeviceID deviceID2 = mock(DeviceID.class);
    ModelPath modelPath2 = mock(ModelPath.class);
    SensorName sensorName2 = mock(SensorName.class);
    SensorTypeID sensorTypeID2 = mock(SensorTypeID.class);
    when(sensorTypeID2.getID()).thenReturn("PercentagePosition");
    SensorID sensorID2 = mock(SensorID.class);

    PercentagePositionSensor percentagePositionSensor1 =
        new PercentagePositionSensor(deviceID1, modelPath1, sensorTypeID1, sensorName1, sensorID1);
    PercentagePositionSensor percentagePositionSensor2 =
        new PercentagePositionSensor(deviceID2, modelPath2, sensorTypeID2, sensorName2, sensorID2);

    // Act
    boolean result = percentagePositionSensor1.equals(percentagePositionSensor2);

    // Assert
    assertFalse(result);
  }

  /**
   * Test to verify that the correct SensorID is returned when getID is called.
   */
  @Test
  void shouldReturnPercentagePositionSensorHashCode_WhenHashCodeMethodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("PercentagePosition");

    try (MockedConstruction<SensorID> sensorIdMockedConstruction =
        mockConstruction(SensorID.class)) {
      PercentagePositionSensor percentagePositionSensor =
          new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName);

      SensorID percentagePositionSensorID = percentagePositionSensor.getID();
      int expected = percentagePositionSensorID.hashCode();
      // Act
      int result = percentagePositionSensor.hashCode();

      // Assert
      assertEquals(expected, result);
    }
  }

  /**
   * Test to verify that the correct SensorID is returned when getID is called.
   */
  @Test
  void shouldReturnPercentagePositionSensorInString_WhenToStringMethodIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("PercentagePosition");
    SensorID sensorID = mock(SensorID.class);

    PercentagePositionSensor percentagePositionSensor =
        new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);

    // Act
    String result = percentagePositionSensor.toString();

    // Assert
    assertNotNull(result);
  }

  /**
   * Instantiates a sensor when sensor ID is valid
   */
  @Test
  void shouldInstantiatePercentagePositionSensor_WhenValidateSensorIDIsValid() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("PercentagePosition");

    SensorID sensorID = mock(SensorID.class);

    // Act
    PercentagePositionSensor percentagePositionSensor =
        new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);

    // Assert
    assertNotNull(percentagePositionSensor);
  }

  /**
   * Should return false when comparing with a null object
   */
  @Test
  void shouldReturnFalse_WhenComparingObjectWithNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorID sensorID = mock(SensorID.class);

    when(sensorTypeID.getID()).thenReturn("PercentagePosition");

    PercentagePositionSensor percentagePositionSensor =
        new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);

    // Act
    boolean result = percentagePositionSensor.equals(null);

    // Assert
    assertFalse(result);
  }

  /**
   * Should throw an exception when sensor ID is null
   */
  @Test
  void shouldThrowIllegalArgumentException_whenValidateSensorIDIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorID sensorID = null;

    when(sensorTypeID.getID()).thenReturn("PercentagePosition");

    // Act & Assert
    IllegalArgumentException thrown =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new PercentagePositionSensor(
                    deviceID, modelPath, sensorTypeID, sensorName, sensorID));
    assertEquals("SensorID is required", thrown.getMessage());
  }

  /**
   * Test to check if IllegalArgumentException is thrown when SensorTypeID is not equal to
   * PercentagePosition
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNotPercentagePosition() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("Temperature");

    String expectedMessage = "SensorTypeID must be 'PercentagePosition'";

    // Act & Assert
    IllegalArgumentException thrown =
        assertThrows(
            IllegalArgumentException.class,
            () -> new PercentagePositionSensor(deviceID, modelPath, sensorTypeID, sensorName));
    assertEquals(expectedMessage, thrown.getMessage());
  }
}
