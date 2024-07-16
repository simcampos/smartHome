/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.log;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.LogID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitID;

class LogAggregateTest {

  /**
   * Test the constructor of the Log class. The constructor should create a new Log instance with
   * the specified device ID, sensor ID, timestamp, and value.
   */
  @Test
  void shouldCreateLogInstance_WhenValidArgumentsAreProvided() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");

    // Act
    Log log = new Log(deviceID, sensorID, localDateTime, value, description, unit);

    // Assert
    assertNotNull(log);
  }

  /**
   * Test the constructor of the Log class. The constructor should create a new Log instance with
   * the specified log ID, device ID, sensor ID, timestamp, and value.
   */
  @Test
  void shouldCreateLogInstance_WhenValidArgumentsAreProvidedForTheSecondConstructor() {
    // Arrange
    LogID logID = new LogID("logID");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");

    // Act
    Log log = new Log(logID, deviceID, sensorID, localDateTime, value, description, unit);

    // Assert
    assertNotNull(log);
  }

  /**
   * Test the constructor of the Log class. The constructor should throw an IllegalArgumentException
   * when a null log ID is provided.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenNullLogIDIsProvided() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");

    // Act & Assert
    IllegalArgumentException exception =
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
              new Log(null, deviceID, sensorID, localDateTime, value, description, unit);
            });

    // Assert
    assertEquals("Log ID is required", exception.getMessage());
  }

  /**
   * Test the constructor of the Log class. The second constructor should throw an
   * IllegalArgumentException when a null device ID is provided.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenNullDeviceIDIsProvidedForTheSecondConstructor() {
    // Arrange
    LogID logID = new LogID("logID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");

    // Act & Assert
    IllegalArgumentException exception =
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
              new Log(logID, null, sensorID, localDateTime, value, description, unit);
            });

    // Assert
    assertEquals("Device ID is required", exception.getMessage());
  }

  /**
   * Test the constructor of the Log class. The second constructor should throw an
   * IllegalArgumentException when a null sensor ID is provided.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenNullSensorIDIsProvidedForTheSecondConstructor() {
    // Arrange
    LogID logID = new LogID("logID");
    DeviceID deviceID = new DeviceID("deviceID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");

    // Act & Assert
    IllegalArgumentException exception =
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
              new Log(logID, deviceID, null, localDateTime, value, description, unit);
            });

    // Assert
    assertEquals("Sensor ID is required", exception.getMessage());
  }

  /**
   * Test the constructor of the Log class. The second constructor should throw an
   * IllegalArgumentException when a null local date time is provided.
   */
  @Test
  void
  shouldThrowIllegalArgumentException_WhenNullLocalDateTimeIsProvidedForTheSecondConstructor() {
    // Arrange
    LogID logID = new LogID("logID");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");

    // Act & Assert
    IllegalArgumentException exception =
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
              new Log(logID, deviceID, sensorID, null, value, description, unit);
            });

    // Assert
    assertEquals("Timestamp is required", exception.getMessage());
  }

  /**
   * Test the constructor of the Log class. The second constructor should throw an
   * IllegalArgumentException when a null value is provided.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenNullValueIsProvidedForTheSecondConstructor() {
    // Arrange
    LogID logID = new LogID("logID");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");

    // Act & Assert
    IllegalArgumentException exception =
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
              new Log(logID, deviceID, sensorID, localDateTime, null, description, unit);
            });

    // Assert
    assertEquals("Value is required", exception.getMessage());
  }

  /**
   * Test the constructor of the Log class. The second constructor should throw an
   * IllegalArgumentException when SensorTypeID is null
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenNullSensorTypeIDIsProvidedForTheSecondConstructor() {
    // Arrange
    LogID logID = new LogID("logID");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    UnitID unit = new UnitID("C");

    // Act & Assert
    IllegalArgumentException exception =
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
              new Log(logID, deviceID, sensorID, localDateTime, value, null, unit);
            });

    // Assert
    assertEquals("Description is required", exception.getMessage());
  }

  /**
   * Test the constructor of the Log class. The second constructor should throw an
   * IllegalArgumentException when UnitID is null
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenNullUnitIDIsProvidedForTheSecondConstructor() {
    // Arrange
    LogID logID = new LogID("logID");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");

    // Act & Assert
    IllegalArgumentException exception =
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
              new Log(logID, deviceID, sensorID, localDateTime, value, description, null);
            });

    // Assert
    assertEquals("Unit is required", exception.getMessage());
  }

  /**
   * Test the constructor of the Log class. The constructor should throw an IllegalArgumentException
   * when a null device ID is provided.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenNullDeviceIDIsProvided() {
    // Arrange
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");

    // Act & Assert
    IllegalArgumentException exception =
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
              new Log(null, sensorID, localDateTime, value, description, unit);
            });

    // Assert
    assertEquals("Device ID is required", exception.getMessage());
  }

  /**
   * Test the constructor of the Log class. The constructor should throw an IllegalArgumentException
   * when a null sensor ID is provided.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenNullSensorIDIsProvided() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");

    // Act & Assert
    IllegalArgumentException exception =
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
              new Log(deviceID, null, localDateTime, value, description, unit);
            });

    // Assert
    assertEquals("Sensor ID is required", exception.getMessage());
  }

  /**
   * Test the constructor of the Log class. The constructor should throw an IllegalArgumentException
   * when a null local date time is provided.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenNullLocalDateTimeIsProvided() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");

    // Act & Assert
    IllegalArgumentException exception =
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
              new Log(deviceID, sensorID, null, value, description, unit);
            });

    // Assert
    assertEquals("Timestamp is required", exception.getMessage());
  }

  /**
   * Test the constructor of the Log class. The constructor should throw an IllegalArgumentException
   * when a null value is provided.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenNullValueIsProvided() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");

    // Act & Assert
    IllegalArgumentException exception =
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
              new Log(deviceID, sensorID, localDateTime, null, description, unit);
            });

    // Assert
    assertEquals("Value is required", exception.getMessage());
  }

  /**
   * Test the constructor of the Log class. The constructor should throw an IllegalArgumentException
   * when SensorTypeID is null
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenNullSensorTypeIDIsProvided() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    UnitID unit = new UnitID("C");

    // Act & Assert
    IllegalArgumentException exception =
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
              new Log(deviceID, sensorID, localDateTime, value, null, unit);
            });

    // Assert
    assertEquals("Description is required", exception.getMessage());
  }

  /**
   * Test the constructor of the Log class. The constructor should throw an IllegalArgumentException
   * when UnitID is null
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenNullUnitIDIsProvided() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");

    // Act & Assert
    IllegalArgumentException exception =
        org.junit.jupiter.api.Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> {
              new Log(deviceID, sensorID, localDateTime, value, description, null);
            });

    // Assert
    assertEquals("Unit is required", exception.getMessage());
  }

  /**
   * Test the getID method for first constructor of the Log class.
   */
  @Test
  void shouldReturnLogID_WhenGetIDIsCalledForFirstConstructor() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");
    Log log = new Log(deviceID, sensorID, localDateTime, value, description, unit);

    // Act
    LogID result = log.getID();

    // Assert
    assertNotNull(result);
  }

  /**
   * Test the getID method for second constructor of the Log class.
   */
  @Test
  void shouldReturnLogID_WhenGetIDIsCalled() {
    // Arrange
    LogID logID = new LogID("logID");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");
    Log log = new Log(logID, deviceID, sensorID, localDateTime, value, description, unit);

    // Act
    LogID result = log.getID();

    // Assert
    assertEquals(logID, result);
  }

  /**
   * Test the getDeviceID method of the Log class.
   */
  @Test
  void shouldReturnDeviceID_WhenGetDeviceIDIsCalled() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");
    Log log = new Log(deviceID, sensorID, localDateTime, value, description, unit);

    // Act
    DeviceID result = log.getDeviceID();

    // Assert
    assertEquals(deviceID, result);
  }

  /**
   * Test the getSensorID method of the Log class.
   */
  @Test
  void shouldReturnSensorID_WhenGetSensorIDIsCalled() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");
    Log log = new Log(deviceID, sensorID, localDateTime, value, description, unit);

    // Act
    SensorID result = log.getSensorID();

    // Assert
    assertEquals(sensorID, result);
  }

  /**
   * Test the getTimeStamp method of the Log class.
   */
  @Test
  void shouldReturnTimeStamp_WhenGetTimeStampIsCalled() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");
    Log log = new Log(deviceID, sensorID, localDateTime, value, description, unit);

    // Act
    LocalDateTime result = log.getTimeStamp();

    // Assert
    assertEquals(localDateTime, result);
  }

  /**
   * Test the getValue method of the Log Class.
   */
  @Test
  void shouldReturnValue_WhenGetReadingValueIsCalled() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");
    Log log = new Log(deviceID, sensorID, localDateTime, value, description, unit);

    // Act
    ReadingValue result = log.getReadingValue();

    // Assert
    assertEquals(value, result);
  }

  /**
   * Test the getDescription method of the Log class.
   */
  @Test
  void shouldReturnDescription_WhenGetDescriptionIsCalled() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");
    Log log = new Log(deviceID, sensorID, localDateTime, value, description, unit);

    // Act
    SensorTypeID result = log.getDescription();

    // Assert
    assertEquals(description, result);
  }

  /**
   * Test the getUnit method of the Log class.
   */
  @Test
  void shouldReturnUnit_WhenGetUnitIsCalled() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");
    Log log = new Log(deviceID, sensorID, localDateTime, value, description, unit);

    // Act
    UnitID result = log.getUnit();

    // Assert
    assertEquals(unit, result);
  }

  /**
   * Test the equals method with Reflexivity
   */
  @Test
  void shouldReturnTrue_WhenComparingLogInstanceWithItself() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");
    Log log = new Log(deviceID, sensorID, localDateTime, value, description, unit);

    // Act
    boolean result = log.equals(log);

    // Assert
    assertTrue(result);
  }

  /**
   * Test the equals method with Symmetry for same LogID
   */
  @Test
  void shouldReturnTrue_WhenComparingTwoLogInstancesWithSameAttributes() {
    // Arrange
    LogID logID = new LogID("logID");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");
    Log log1 = new Log(logID, deviceID, sensorID, localDateTime, value, description, unit);
    Log log2 = new Log(logID, deviceID, sensorID, localDateTime, value, description, unit);

    // Act
    boolean result1 = log1.equals(log2);
    boolean result2 = log2.equals(log1);

    // Assert
    assertTrue(result1);
    assertTrue(result2);
  }

  /**
   * Test the equals method with Null Comparison
   */
  @Test
  void shouldReturnFalse_WhenComparingLogInstanceWithNull() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");
    Log log = new Log(deviceID, sensorID, localDateTime, value, description, unit);

    // Act
    boolean result = log.equals(null);

    // Assert
    assertFalse(result);
  }

  /**
   * Test the equals method of the Log class when one Log instance is compared with an object not from Loge type.
   */
  @Test
  void shouldReturnFalse_WhenLogInstancesIsNotEqualToObjectNotALogInstance() {
    // Arrange
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");
    Log log = new Log(deviceID, sensorID, localDateTime, value, description, unit);

    // Act
    boolean result = log.equals(new Object());

    // Assert
    assertFalse(result);
  }

  /**
   * Test the equals method of the Log class when two Log instances are not equal.
   */
  @Test
  void shouldReturnFalse_WhenTwoLogInstancesAreNotEqual() {
    // Arrange
    LogID logID1 = new LogID("logID1");
    LogID logID2 = new LogID("logID2");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");

    Log log1 = new Log(logID1, deviceID, sensorID, localDateTime, value, description, unit);
    Log log2 = new Log(logID2, deviceID, sensorID, localDateTime, value, description, unit);

    // Act
    boolean result = log1.equals(log2);

    // Assert
    assertFalse(result);
  }

  /**
   * Tests hash code
   */
  @Test
  void shouldReturnHashCode_WhenGetHashCodeIsCalled() {
    // Arrange
    LogID logID = new LogID("logID");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");
    Log log = new Log(logID, deviceID, sensorID, localDateTime, value, description, unit);
    int expected = logID.hashCode();

    // Act
    int result = log.hashCode();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Tests toString method
   */
  @Test
  void shouldReturnString_WhenToStringIsCalled() {
    // Arrange
    LogID logID = new LogID("logID");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorID sensorID = new SensorID("sensorID");
    LocalDateTime localDateTime = LocalDateTime.of(2021, 1, 1, 1, 1);
    ReadingValue value = new ReadingValue("20");
    SensorTypeID description = new SensorTypeID("temperature");
    UnitID unit = new UnitID("C");
    Log log = new Log(logID, deviceID, sensorID, localDateTime, value, description, unit);
    String expected =
        logID + " " + deviceID + " " + sensorID + " " + localDateTime + " " + value + " "
            + description + " " + unit;

    // Act
    String result = log.toString();

    // Assert
    assertEquals(expected, result);
  }
}
