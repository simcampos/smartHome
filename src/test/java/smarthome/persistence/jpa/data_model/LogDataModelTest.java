/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.jpa.data_model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import smarthome.domain.log.Log;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.LogID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitID;
import smarthome.persistence.data_model.LogDataModel;

class LogDataModelTest {

  /**
   * Test for the empty constructor of the LogDataModel class.
   */
  @Test
  void shouldInstantiateLogDataModel_whenEmptyConstructorIsCalled() {
    // Arrange
    LogDataModel logDataModel = new LogDataModel();

    // Act
    assertNotNull(logDataModel);
  }

  /**
   * Test for the constructor of the LogDataModel class.
   */
  @Test
  void shouldInstantiateLogDataModel_whenLogIsValid() {
    // Arrange
    Log logDouble = mock(Log.class);
    when(logDouble.getID()).thenReturn(mock(LogID.class));
    when(logDouble.getDeviceID()).thenReturn(mock(DeviceID.class));
    when(logDouble.getSensorID()).thenReturn(mock(SensorID.class));
    when(logDouble.getTimeStamp()).thenReturn(mock(LocalDateTime.class));
    when(logDouble.getReadingValue()).thenReturn(mock(ReadingValue.class));
    when(logDouble.getDescription()).thenReturn(mock(SensorTypeID.class));
    when(logDouble.getUnit()).thenReturn(mock(UnitID.class));

    // Act
    LogDataModel logDataModel = new LogDataModel(logDouble);

    // Assert
    assertNotNull(logDataModel);
  }

  /**
   * Test for the getLogID method of the LogDataModel class.
   */
  @Test
  void shouldReturnLogID_whenGetLogIDIsCalled() {
    // Arrange
    LogID logIDDouble = mock(LogID.class);
    DeviceID deviceIDDouble = mock(DeviceID.class);
    SensorID sensorIDDouble = mock(SensorID.class);
    LocalDateTime timestampDouble = mock(LocalDateTime.class);
    ReadingValue readingValueDouble = mock(ReadingValue.class);
    SensorTypeID descriptionDouble = mock(SensorTypeID.class);
    UnitID unitDouble = mock(UnitID.class);

    Log logDouble = mock(Log.class);
    when(logDouble.getID()).thenReturn(logIDDouble);
    when(logDouble.getDeviceID()).thenReturn(deviceIDDouble);
    when(logDouble.getSensorID()).thenReturn(sensorIDDouble);
    when(logDouble.getTimeStamp()).thenReturn(timestampDouble);
    when(logDouble.getReadingValue()).thenReturn(readingValueDouble);
    when(logDouble.getDescription()).thenReturn(descriptionDouble);
    when(logDouble.getUnit()).thenReturn(unitDouble);

    LogDataModel logDataModel = new LogDataModel(logDouble);

    //Act
    String logID = logDataModel.getLogID();

    //Assert
    assertEquals(logIDDouble.getID(), logID);
  }

  /**
   * Test for the getDeviceID method of the LogDataModel class.
   */
  @Test
  void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
    // Arrange
    LogID logIDDouble = mock(LogID.class);
    DeviceID deviceIDDouble = mock(DeviceID.class);
    SensorID sensorIDDouble = mock(SensorID.class);
    LocalDateTime timestampDouble = mock(LocalDateTime.class);
    ReadingValue readingValueDouble = mock(ReadingValue.class);
    SensorTypeID descriptionDouble = mock(SensorTypeID.class);
    UnitID unitDouble = mock(UnitID.class);

    Log logDouble = mock(Log.class);
    when(logDouble.getID()).thenReturn(logIDDouble);
    when(logDouble.getDeviceID()).thenReturn(deviceIDDouble);
    when(logDouble.getSensorID()).thenReturn(sensorIDDouble);
    when(logDouble.getTimeStamp()).thenReturn(timestampDouble);
    when(logDouble.getReadingValue()).thenReturn(readingValueDouble);
    when(logDouble.getDescription()).thenReturn(descriptionDouble);
    when(logDouble.getUnit()).thenReturn(unitDouble);

    LogDataModel logDataModel = new LogDataModel(logDouble);

    //Act
    String deviceID = logDataModel.getDeviceID();

    //Assert
    assertEquals(deviceIDDouble.getID(), deviceID);
  }

  /**
   * Test for the getSensorID method of the LogDataModel class.
   */
  @Test
  void shouldReturnSensorID_whenGetSensorIDIsCalled() {
    // Arrange
    LogID logIDDouble = mock(LogID.class);
    DeviceID deviceIDDouble = mock(DeviceID.class);
    SensorID sensorIDDouble = mock(SensorID.class);
    LocalDateTime timestampDouble = mock(LocalDateTime.class);
    ReadingValue readingValueDouble = mock(ReadingValue.class);
    SensorTypeID descriptionDouble = mock(SensorTypeID.class);
    UnitID unitDouble = mock(UnitID.class);

    Log logDouble = mock(Log.class);
    when(logDouble.getID()).thenReturn(logIDDouble);
    when(logDouble.getDeviceID()).thenReturn(deviceIDDouble);
    when(logDouble.getSensorID()).thenReturn(sensorIDDouble);
    when(logDouble.getTimeStamp()).thenReturn(timestampDouble);
    when(logDouble.getReadingValue()).thenReturn(readingValueDouble);
    when(logDouble.getDescription()).thenReturn(descriptionDouble);
    when(logDouble.getUnit()).thenReturn(unitDouble);

    LogDataModel logDataModel = new LogDataModel(logDouble);

    //Act
    String sensorID = logDataModel.getSensorID();

    //Assert
    assertEquals(sensorIDDouble.getID(), sensorID);
  }

  /**
   * Test for the getTimestamp method of the LogDataModel class.
   */
  @Test
  void shouldReturnTimestamp_whenGetTimestampIsCalled() {
    // Arrange
    LogID logIDDouble = mock(LogID.class);
    DeviceID deviceIDDouble = mock(DeviceID.class);
    SensorID sensorIDDouble = mock(SensorID.class);
    LocalDateTime timestampDouble = mock(LocalDateTime.class);
    ReadingValue readingValueDouble = mock(ReadingValue.class);
    SensorTypeID descriptionDouble = mock(SensorTypeID.class);
    UnitID unitDouble = mock(UnitID.class);

    Log logDouble = mock(Log.class);
    when(logDouble.getID()).thenReturn(logIDDouble);
    when(logDouble.getDeviceID()).thenReturn(deviceIDDouble);
    when(logDouble.getSensorID()).thenReturn(sensorIDDouble);
    when(logDouble.getTimeStamp()).thenReturn(timestampDouble);
    when(logDouble.getReadingValue()).thenReturn(readingValueDouble);
    when(logDouble.getDescription()).thenReturn(descriptionDouble);
    when(logDouble.getUnit()).thenReturn(unitDouble);

    LogDataModel logDataModel = new LogDataModel(logDouble);

    //Act
    LocalDateTime timestamp = logDataModel.getTimestamp();

    //Assert
    assertEquals(timestampDouble, timestamp);
  }

  /**
   * Test for the getReadingValue method of the LogDataModel class.
   */
  @Test
  void shouldReturnReadingValue_whenGetReadingValueIsCalled() {
    // Arrange
    LogID logIDDouble = mock(LogID.class);
    DeviceID deviceIDDouble = mock(DeviceID.class);
    SensorID sensorIDDouble = mock(SensorID.class);
    LocalDateTime timestampDouble = mock(LocalDateTime.class);
    ReadingValue readingValueDouble = mock(ReadingValue.class);
    SensorTypeID descriptionDouble = mock(SensorTypeID.class);
    UnitID unitDouble = mock(UnitID.class);

    Log logDouble = mock(Log.class);
    when(logDouble.getID()).thenReturn(logIDDouble);
    when(logDouble.getDeviceID()).thenReturn(deviceIDDouble);
    when(logDouble.getSensorID()).thenReturn(sensorIDDouble);
    when(logDouble.getTimeStamp()).thenReturn(timestampDouble);
    when(logDouble.getReadingValue()).thenReturn(readingValueDouble);
    when(logDouble.getDescription()).thenReturn(descriptionDouble);
    when(logDouble.getUnit()).thenReturn(unitDouble);

    LogDataModel logDataModel = new LogDataModel(logDouble);

    //Act
    String readingValue = logDataModel.getReadingValue();

    //Assert
    assertEquals(readingValueDouble.getValue(), readingValue);
  }

  /**
   * Test for the getDescription method of the LogDataModel class.
   */
  @Test
  void shouldReturnDescription_whenGetDescriptionIsCalled() {
    // Arrange
    LogID logIDDouble = mock(LogID.class);
    DeviceID deviceIDDouble = mock(DeviceID.class);
    SensorID sensorIDDouble = mock(SensorID.class);
    LocalDateTime timestampDouble = mock(LocalDateTime.class);
    ReadingValue readingValueDouble = mock(ReadingValue.class);
    SensorTypeID descriptionDouble = mock(SensorTypeID.class);
    UnitID unitDouble = mock(UnitID.class);

    Log logDouble = mock(Log.class);
    when(logDouble.getID()).thenReturn(logIDDouble);
    when(logDouble.getDeviceID()).thenReturn(deviceIDDouble);
    when(logDouble.getSensorID()).thenReturn(sensorIDDouble);
    when(logDouble.getTimeStamp()).thenReturn(timestampDouble);
    when(logDouble.getReadingValue()).thenReturn(readingValueDouble);
    when(logDouble.getDescription()).thenReturn(descriptionDouble);
    when(logDouble.getUnit()).thenReturn(unitDouble);

    LogDataModel logDataModel = new LogDataModel(logDouble);

    //Act
    String description = logDataModel.getDescription();

    //Assert
    assertEquals(descriptionDouble.getID(), description);
  }

  /**
   * Test for the getUnit method of the LogDataModel class.
   */
  @Test
  void shouldReturnUnit_whenGetUnitIsCalled() {
    // Arrange
    LogID logIDDouble = mock(LogID.class);
    DeviceID deviceIDDouble = mock(DeviceID.class);
    SensorID sensorIDDouble = mock(SensorID.class);
    LocalDateTime timestampDouble = mock(LocalDateTime.class);
    ReadingValue readingValueDouble = mock(ReadingValue.class);
    SensorTypeID descriptionDouble = mock(SensorTypeID.class);
    UnitID unitDouble = mock(UnitID.class);

    Log logDouble = mock(Log.class);
    when(logDouble.getID()).thenReturn(logIDDouble);
    when(logDouble.getDeviceID()).thenReturn(deviceIDDouble);
    when(logDouble.getSensorID()).thenReturn(sensorIDDouble);
    when(logDouble.getTimeStamp()).thenReturn(timestampDouble);
    when(logDouble.getReadingValue()).thenReturn(readingValueDouble);
    when(logDouble.getDescription()).thenReturn(descriptionDouble);
    when(logDouble.getUnit()).thenReturn(unitDouble);

    LogDataModel logDataModel = new LogDataModel(logDouble);

    //Act
    String unit = logDataModel.getUnit();

    //Assert
    assertEquals(unitDouble.getID(), unit);
  }
}
