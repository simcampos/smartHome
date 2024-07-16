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
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.shredzone.commons.suncalc.SunTimes;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;

class SunsetTimeSensorTest {

  /**
   * Test to check if SunsetTimeSensor is instantiated when all attributes are valid
   */
  @Test
  void shouldInstantiateSunsetTimeSensor_whenAttributesAreValid() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    SensorName sensorName = mock(SensorName.class);
    GPS gps = mock(GPS.class);

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {
      //Act
      SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
          sensorName, gps);

      //Assert
      assertNotNull(sunsetTimeSensor);
    }
  }

  /**
   * Test to check if SunsetTimeSensor is instantiated when all attributes are valid, including
   * SensorID
   */
  @Test
  void shouldInstantiateSunsetTimeSensor_whenAttributesAreValidIncludingSensorID() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    SensorName sensorName = mock(SensorName.class);
    GPS gps = mock(GPS.class);

    SensorID sensorID = mock(SensorID.class);

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
  void shouldThrowIllegalArgumentException_whenSensorIDIsNull() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    SensorName sensorName = mock(SensorName.class);
    GPS gps = mock(GPS.class);

    String expectedMessage = "SensorID is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps, null));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());

  }

  /**
   * Test to check if IllegalArgumentException is thrown when DeviceID is null
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
    //Arrange
    DeviceID deviceID = null;
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    SensorName sensorName = mock(SensorName.class);
    GPS gps = mock(GPS.class);

    String expectedMessage = "DeviceID is required";

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {
      //Act
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
          () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

      //Assert
      assertEquals(expectedMessage, exception.getMessage());
    }
  }

  /**
   * Test to check if IllegalArgumentException is thrown when SensorTypeID is null
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = null;

    SensorName sensorName = mock(SensorName.class);
    GPS gps = mock(GPS.class);

    String expectedMessage = "SensorTypeID is required";

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {
      //Act
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
          () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

      //Assert
      assertEquals(expectedMessage, exception.getMessage());
    }
  }

  /**
   * Test to check if IllegalArgumentException is thrown when SensorTypeID is not SunsetTime
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNotSunsetTime() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("WrongSensorTypeID");

    SensorName sensorName = mock(SensorName.class);
    GPS gps = mock(GPS.class);

    String expectedMessage = "SensorTypeID must be 'SunsetTime'.";

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {
      //Act
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
          () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

      //Assert
      assertEquals(expectedMessage, exception.getMessage());
    }
  }

  /**
   * Test to check if IllegalArgumentException is thrown when ModelPath is null
   */
  @Test
  void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = null;

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    SensorName sensorName = mock(SensorName.class);
    GPS gps = mock(GPS.class);

    String expectedMessage = "ModelPath is required";

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {
      //Act
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
          () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

      //Assert
      assertEquals(expectedMessage, exception.getMessage());
    }
  }

  /**
   * Test to check if IllegalArgumentException is thrown when SensorName is null
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    SensorName sensorName = null;
    GPS gps = mock(GPS.class);

    String expectedMessage = "SensorName is required";

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {
      //Act
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
          () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

      //Assert
      assertEquals(expectedMessage, exception.getMessage());
    }
  }

  /**
   * Test to check if IllegalArgumentException is thrown when GPS is null
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGPSIsNull() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    SensorName sensorName = mock(SensorName.class);
    GPS gps = null;

    String expectedMessage = "GPS is required";

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {
      //Act
      IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
          () -> new SunsetTimeSensor(deviceID, modelPath, sensorTypeID, sensorName, gps));

      //Assert
      assertEquals(expectedMessage, exception.getMessage());
    }
  }

  /**
   * Test to check if SunsetTimeSensorValue is returned for the current day
   */
  @Test
  void shouldReturnSunsetTimeForCurrentDay_whenGetValueIsCalledWithNoArguments() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    SensorName sensorName = mock(SensorName.class);
    GPS gps = mock(GPS.class);

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {
      SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
          sensorName, gps);

      LocalTime expectedSunsetTime = Objects.requireNonNull(
          SunTimes.compute().on(LocalDate.now()).at(gps.getLatitude(), gps.getLongitude()).execute()
              .getSet()).toLocalTime().truncatedTo(ChronoUnit.SECONDS);

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

      SunsetTimeSensorValue expected = mock(SunsetTimeSensorValue.class);
      when(expected.toString()).thenReturn(expectedSunsetTime.format(formatter));

      //Act
      SunsetTimeSensorValue sunsetTime = sunsetTimeSensor.getValue();

      //Assert
      assertEquals("Sunset Time: " + expected, sunsetTime.toString());
    }
  }

  /**
   * Test to check if SunsetTimeSensorValue is returned for a given day
   */
  @Test
  void shouldReturnSunsetTimeForGivenDay_whenGetValueIsCalledWithADate() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    SensorName sensorName = mock(SensorName.class);
    GPS gps = mock(GPS.class);

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {
      SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
          sensorName, gps);

      LocalDate date = LocalDate.now().plusDays(5);
      LocalTime expectedSunsetTime = Objects.requireNonNull(
              SunTimes.compute().on(date).at(gps.getLatitude(), gps.getLongitude()).execute().getSet())
          .toLocalTime().truncatedTo(ChronoUnit.SECONDS);

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

      SunsetTimeSensorValue expected = mock(SunsetTimeSensorValue.class);
      when(expected.toString()).thenReturn(expectedSunsetTime.format(formatter));

      //Act
      SunsetTimeSensorValue sunsetTime = sunsetTimeSensor.getValue(date);

      //Assert
      assertEquals("Sunset Time: " + expected, sunsetTime.toString());
    }
  }

  /**
   * Test to check if device ID is returned when getDeviceID is called
   */
  @Test
  void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    SensorName sensorName = mock(SensorName.class);
    GPS gps = mock(GPS.class);

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {
      SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
          sensorName, gps);

      //Act
      DeviceID actualDeviceID = sunsetTimeSensor.getDeviceID();

      //Assert
      assertEquals(deviceID, actualDeviceID);
    }
  }

  /**
   * Test to check if SensorTypeID is returned when getSensorTypeID is called
   */
  @Test
  void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() throws InstantiationException {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    SensorName sensorName = mock(SensorName.class);
    GPS gps = mock(GPS.class);

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {

      SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
          sensorName, gps);

      //Act
      SensorTypeID actualSensorTypeID = sunsetTimeSensor.getSensorTypeID();

      //Assert
      assertEquals(sensorTypeID, actualSensorTypeID);
    }
  }

  /**
   * Test to check if SensorName is returned when getSensorName is called
   */
  @Test
  void shouldReturnSensorName_whenGetSensorNameIsCalled() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    SensorName sensorName = mock(SensorName.class);
    GPS gps = mock(GPS.class);

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {

      SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
          sensorName, gps);

      //Act
      SensorName actualSensorName = sunsetTimeSensor.getSensorName();

      //Assert
      assertEquals(sensorName, actualSensorName);
    }
  }

  /**
   * Test to check if ModelPath is returned when getModelPath is called
   */
  @Test
  void shouldReturnModelPath_whenGetModelPathIsCalled() throws InstantiationException {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    SensorName sensorName = mock(SensorName.class);
    GPS gps = mock(GPS.class);

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {
      SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
          sensorName, gps);

      //Act
      ModelPath actualModelPath = sunsetTimeSensor.getModelPath();

      //Assert
      assertEquals(modelPath, actualModelPath);
    }
  }

  /**
   * Test to check if SensorID is returned when getSensorID is called
   */
  @Test
  void shouldReturnSensorID_whenGetSensorIDIsCalled() throws InstantiationException {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    SensorName sensorName = mock(SensorName.class);
    GPS gps = mock(GPS.class);

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {

      SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
          sensorName, gps);

      //Act
      SensorID actualSensorID = sunsetTimeSensor.getID();

      //Assert
      assertTrue(sunsetTimeSensor.toString().contains(actualSensorID.toString()));
    }
  }

  /**
   * Test to check if two SunsetTimeSensor objects with different IDs are equal
   */
  @Test
  void shouldReturnFalse_whenTwoSunsetTimeSensorsHaveDifferentIDs() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    SensorName sensorName1 = mock(SensorName.class);
    SensorName sensorName2 = mock(SensorName.class);
    GPS gps = mock(GPS.class);

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {

      SunsetTimeSensor sunsetTimeSensor1 = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
          sensorName1, gps);
      SunsetTimeSensor sunsetTimeSensor2 = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
          sensorName2, gps);

      //Act
      boolean result = sunsetTimeSensor1.equals(sunsetTimeSensor2);

      //Assert
      assertFalse(result);
    }
  }

  /**
   * Test to check if two SunsetTimeSensor objects with same ID are equal
   */
  @Test
  void shouldReturnTrue_whenTwoSunsetTimeSensorsHaveSameIDs() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    SensorName sensorName = mock(SensorName.class);
    GPS gps = mock(GPS.class);

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {

      SunsetTimeSensor sunsetTimeSensor1 = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
          sensorName, gps);

      //Act
      boolean result = sunsetTimeSensor1.equals(sunsetTimeSensor1);

      //Assert
      assertTrue(result);
    }
  }

  /**
   * Test to check if an object of another class is not equal to SunsetTimeSensor
   */
  @Test
  void shouldReturnFalse_whenObjectIsNotSunsetTimeSensor() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    SensorName sensorName = mock(SensorName.class);
    GPS gps = mock(GPS.class);

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {

      SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
          sensorName, gps);

      //Act
      boolean result = sunsetTimeSensor.equals(new Object());

      //Assert
      assertFalse(result);
    }
  }


  /**
   * Test to check if hashCode is returned
   */
  @Test
  void shouldReturnHashCode_whenHashCodeIsCalled() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    SensorName sensorName = mock(SensorName.class);
    GPS gps = mock(GPS.class);

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {

      SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
          sensorName, gps);

      int expected = sunsetTimeSensor.getID().hashCode();

      //Act
      int hashCode = sunsetTimeSensor.hashCode();

      //Assert
      assertEquals(expected, hashCode);
    }
  }

  /**
   * ShouldReturnGPS_whenGetGPSIsCalled
   */
  @Test
  void ShouldReturnGPS_whenGetGPSIsCalled() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");
    SensorName sensorName = mock(SensorName.class);
    GPS gps = mock(GPS.class);
    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(
        SensorID.class)) {
      SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
          sensorName, gps);
      //Act
      GPS actualGPS = sunsetTimeSensor.getGPS();
      //Assert
      assertEquals(gps, actualGPS);
    }
  }

  /**
   * Test to check if toString returns the expected value
   */
  @Test
  void shouldReturnStringValue_whenToStringIsCalled() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    SensorName sensorName = mock(SensorName.class);

    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("SunsetTime");

    GPS gps = mock(GPS.class);

    try (MockedConstruction<SensorID> sensorIdMockedConstruction = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.toString()).thenReturn("MockedSensorID");
        })) {
      SunsetTimeSensor sunsetTimeSensor = new SunsetTimeSensor(deviceID, modelPath, sensorTypeID,
          sensorName, gps);
      String expectedValue = sunsetTimeSensor.getValue().toString();

      SunsetTimeSensorValue sunsetTimeSensorValue = mock(SunsetTimeSensorValue.class);
      when(sunsetTimeSensorValue.toString()).thenReturn(expectedValue);

      String expected = "SunsetTimeSensor:" +
          " sunriseTimeValue=" + expectedValue +
          ", sensorTypeID=" + sensorTypeID +
          ", sensorID=MockedSensorID" +
          ", sensorName=" + sensorName +
          ", deviceID=" + deviceID +
          ", modelPath=" + modelPath +
          ", gps=" + gps;

      //Act
      String result = sunsetTimeSensor.toString();

      //Assert
      assertEquals(expected, result);
    }
  }

}