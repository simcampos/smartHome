/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.sunset_time_sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.shredzone.commons.suncalc.SunTimes;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.visitor_pattern.ISensorVisitor;

class SunsetTimeSensorAggregateTest {

  /**
   * Test to check if SunsetTimeSensor is instantiated when all attributes are valid
   */
  @Test
  void shouldInstantiateSunsetTimeSensor_whenAttributesAreValid() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);

    //Act
    SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
        sensorName, gps);

    //Assert
    assertNotNull(sunsetTimeSensor);
  }

  /**
   * Test to check if SunsetTimeSensor is instantiated when all attributes are valid, including
   * SensorID
   */
  @Test
  void shouldInstantiateSunsetTimeSensor_whenAttributesAreValidIncludingSensorID() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);
    SensorID sensorID = new SensorID(UUID.randomUUID().toString());

    //Act
    SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
        sensorName, gps, sensorID);

    //Assert
    assertNotNull(sunsetTimeSensor);
  }

  /**
   * Test to check if IllegalArgumentException is thrown when DeviceID is null
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenDeviceIDIsNull() {
    //Arrange
    DeviceID deviceID = null;
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);

    String expectedMessage = "DeviceID is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if IllegalArgumentException is thrown when SensorTypeID is null
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenSensorTypeIDIsNull() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = null;
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);

    String expectedMessage = "SensorTypeID is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if IllegalArgumentException is thrown when SensorTypeID is not SunsetTime
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenSensorTypeIDIsNotSunsetTime() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("Wrong Sensor TypeID");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);

    String expectedMessage = "SensorTypeID must be 'SunsetTime'.";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if IllegalArgumentException is thrown when ModelPath is null
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenModelPathIsNull() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = null;
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);

    String expectedMessage = "ModelPath is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if IllegalArgumentException is thrown when SensorName is null
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenSensorNameIsNull() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = null;
    GPS gps = new GPS(0, 0);

    String expectedMessage = "SensorName is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if IllegalArgumentException is thrown when GPS is null
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenGPSIsNull() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = null;

    String expectedMessage = "GPS is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }


  /**
   * Test to check if SunsetTimeSensor is instantiated when all attributes are valid
   */
  @Test
  void shouldInstantiateSunsetTimeSensor_whenAttributesAreValidWithSensorID() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);
    SensorID sensorID = new SensorID(UUID.randomUUID().toString());

    //Act
    SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
        sensorName, gps, sensorID);

    //Assert
    assertNotNull(sunsetTimeSensor);
  }

  /**
   * Test to check if IllegalArgumentException is thrown when SensorID is null
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenSensorIDIsNull() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);
    SensorID sensorID = null;

    String expectedMessage = "SensorID is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps, sensorID));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if IllegalArgumentException is thrown when DeviceID is null with SensorID
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenDeviceIDIsNullWithSensorID() {
    //Arrange
    DeviceID deviceID = null;
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);
    SensorID sensorID = new SensorID(UUID.randomUUID().toString());

    String expectedMessage = "DeviceID is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps, sensorID));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if IllegalArgumentException is thrown when SensorTypeID is null with SensorID
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenSensorTypeIDIsNullWithSensorID() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = null;
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);
    SensorID sensorID = new SensorID(UUID.randomUUID().toString());

    String expectedMessage = "SensorTypeID is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps, sensorID));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if IllegalArgumentException is thrown when ModelPath is null with SensorID
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenModelPathIsNullWithSensorID() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = null;
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);
    SensorID sensorID = new SensorID(UUID.randomUUID().toString());

    String expectedMessage = "ModelPath is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps, sensorID));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if IllegalArgumentException is thrown when SensorName is null with SensorID
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenSensorNameIsNullWithSensorID() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = null;
    GPS gps = new GPS(0, 0);
    SensorID sensorID = new SensorID(UUID.randomUUID().toString());

    String expectedMessage = "SensorName is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps, sensorID));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if IllegalArgumentException is thrown when GPS is null with SensorID
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenGPSIsNullWithSensorID() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = null;
    SensorID sensorID = new SensorID(UUID.randomUUID().toString());

    String expectedMessage = "GPS is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps, sensorID));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if SunsetTimeSensorValue is returned for the current day
   */
  @Test
  void shouldReturnSunsetTimeForCurrentDay_whenGetValueIsCalledWithNoArguments() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);

    SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
        sensorName, gps);

    LocalTime expectedSunsetTime = Objects.requireNonNull(
        SunTimes.compute().on(LocalDate.now()).at(gps.getLatitude(), gps.getLongitude()).execute()
            .getSet()).toLocalTime().truncatedTo(ChronoUnit.SECONDS);

    SunsetTimeSensorValue expected = new SunsetTimeSensorValue(expectedSunsetTime);

    //Act
    SunsetTimeSensorValue sunsetTime = sunsetTimeSensor.getValue();

    //Assert
    assertEquals(expected.toString(), sunsetTime.toString());
  }

  /**
   * Test to check if SunsetTimeSensorValue is returned for a given day
   */
  @Test
  void shouldReturnSunsetTimeForGivenDay_whenGetValueIsCalledWithADate() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);

    SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
        sensorName, gps);

    LocalDate date = LocalDate.now().plusDays(5);
    LocalTime expectedSunsetTime = Objects.requireNonNull(
            SunTimes.compute().on(date).at(gps.getLatitude(), gps.getLongitude()).execute().getSet())
        .toLocalTime().truncatedTo(ChronoUnit.SECONDS);

    SunsetTimeSensorValue expected = new SunsetTimeSensorValue(expectedSunsetTime);

    //Act
    SunsetTimeSensorValue sunsetTime = sunsetTimeSensor.getValue(date);

    //Assert
    assertEquals(expected.toString(), sunsetTime.toString());
  }

  /**
   * Test to check if device ID is returned when getDeviceID is called
   */
  @Test
  void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);

    SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
        sensorName, gps);

    //Act
    DeviceID actualDeviceID = sunsetTimeSensor.getDeviceID();

    //Assert
    assertEquals(deviceID, actualDeviceID);
  }

  /**
   * Test to check if SensorTypeID is returned when getSensorTypeID is called
   */
  @Test
  void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() throws InstantiationException {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);

    SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
        sensorName, gps);

    //Act
    SensorTypeID actualSensorTypeID = sunsetTimeSensor.getSensorTypeID();

    //Assert
    assertEquals(sensorTypeID, actualSensorTypeID);
  }

  /**
   * Test to check if SensorName is returned when getSensorName is called
   */
  @Test
  void shouldReturnSensorName_whenGetSensorNameIsCalled() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);

    SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
        sensorName, gps);

    //Act
    SensorName actualSensorName = sunsetTimeSensor.getSensorName();

    //Assert
    assertEquals(sensorName, actualSensorName);
  }

  /**
   * Test to check if ModelPath is returned when getModelPath is called
   */
  @Test
  void shouldReturnModelPath_whenGetModelPathIsCalled() throws InstantiationException {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);

    SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
        sensorName, gps);

    //Act
    ModelPath actualModelPath = sunsetTimeSensor.getModelPath();

    //Assert
    assertEquals(modelPath, actualModelPath);
  }

  /**
   * Test to check if SensorID is returned when getSensorID is called
   */
  @Test
  void shouldReturnSensorID_whenGetSensorIDIsCalled() throws InstantiationException {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);

    SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
        sensorName, gps);

    //Act
    SensorID actualSensorID = sunsetTimeSensor.getID();

    //Assert
    assertTrue(sunsetTimeSensor.toString().contains(actualSensorID.toString()));
  }

  /**
   * Test to check if two SunsetTimeSensor objects with different IDs are equal
   */
  @Test
  void shouldReturnFalse_whenTwoSunsetTimeSensorsHaveDifferentIDs() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName1 = new SensorName("sensorName1");
    SensorName sensorName2 = new SensorName("sensorName2");
    GPS gps = new GPS(0, 0);

    SunsetTimeSensor sunsetTimeSensor1 = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
        sensorName1, gps);
    SunsetTimeSensor sunsetTimeSensor2 = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
        sensorName2, gps);

    //Act
    boolean result = sunsetTimeSensor1.equals(sunsetTimeSensor2);

    //Assert
    assertFalse(result);
  }

  /**
   * Test to check if two SunsetTimeSensor objects with same ID are equal
   */
  @Test
  void shouldReturnTrue_whenTwoSunsetTimeSensorsHaveSameIDs() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);

    SunsetTimeSensor sunsetTimeSensor1 = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
        sensorName, gps);

    //Act
    boolean result = sunsetTimeSensor1.equals(sunsetTimeSensor1);

    //Assert
    assertTrue(result);
  }

  /**
   * Test to check if an object of another class is not equal to SunsetTimeSensor
   */
  @Test
  void shouldReturnFalse_whenObjectIsNotSunsetTimeSensor() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);

    SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
        sensorName, gps);

    //Act
    boolean result = sunsetTimeSensor.equals(new Object());

    //Assert
    assertFalse(result);
  }


  /**
   * Test to check if hashCode is returned
   */
  @Test
  void shouldReturnHashCode_whenHashCodeIsCalled() {
    //Arrange
    DeviceID deviceID = new DeviceID(UUID.randomUUID().toString());
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);

    SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
        sensorName, gps);

    int expected = sunsetTimeSensor.getID().hashCode();

    //Act
    int hashCode = sunsetTimeSensor.hashCode();

    //Assert
    assertEquals(expected, hashCode);
  }

  @Test
  void shouldAcceptVisitorAndReturnInstanceOfObjectInString() {
    //Arrange
    DeviceID deviceID = new DeviceID("2");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("SunsetTime");
    SensorName sensorName = new SensorName("sensorName");
    GPS gps = new GPS(0, 0);

    SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
        sensorName, gps);
    ISensorVisitor visitor = mock(ISensorVisitor.class);

    String expected = sunsetTimeSensor.toString();
    //Act
    String result = sunsetTimeSensor.accept(visitor);
    //Assert
    assertEquals(expected, result);
    assertNotNull(sunsetTimeSensor);
  }

}