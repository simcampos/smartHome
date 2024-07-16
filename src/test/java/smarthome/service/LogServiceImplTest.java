/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.service;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import smarthome.domain.device.Device;
import smarthome.domain.log.ILogFactory;
import smarthome.domain.log.Log;
import smarthome.domain.log.LogFactoryImpl;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.ILogRepository;
import smarthome.domain.repository.ISensorRepository;
import smarthome.domain.repository.ISensorTypeRepository;
import smarthome.domain.repository.IUnitRepository;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TimeDelta;
import smarthome.domain.value_object.UnitID;

class LogServiceImplTest {

  private Log createMockLog(String value, LocalDateTime timestamp) {
    ReadingValue readingValue = mock(ReadingValue.class);
    when(readingValue.getValue()).thenReturn(value);

    Log log = mock(Log.class);
    when(log.getReadingValue()).thenReturn(readingValue);
    when(log.getTimeStamp()).thenReturn(timestamp);

    return log;
  }

  /**
   * Test that the LogServiceImpl class can be instantiated.
   */
  @Test
  void shouldInstantiateValidLog() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();

    // Act
    LogServiceImpl result = new LogServiceImpl(logRepository, deviceRepository, sensorRepository,
        sensorTypeRepository, unitRepository, logFactory);


    // Assert
    assertNotNull(result);
  }

  /**
   * Test that the LogServiceImpl adds a log when all IDs exist.
   */
  @Test
  void shouldAddLog_WhenAllIDsExist() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = mock(
        ILogFactory.class); // mock the logFactory to ensure proper behavior
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    DeviceID deviceID = mock(DeviceID.class);
    SensorID sensorID = mock(SensorID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    UnitID unitID = mock(UnitID.class);
    LocalDateTime localDateTime = LocalDateTime.now();
    ReadingValue readingValue = mock(ReadingValue.class);

    Log expectedLog = mock(Log.class);

    when(deviceRepository.containsOfIdentity(deviceID)).thenReturn(true);
    when(sensorRepository.containsOfIdentity(sensorID)).thenReturn(true);
    when(sensorTypeRepository.containsOfIdentity(sensorTypeID)).thenReturn(true);
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);
    when(logFactory.createLog(deviceID, sensorID, localDateTime, readingValue, sensorTypeID,
        unitID)).thenReturn(expectedLog);
    when(logRepository.save(expectedLog)).thenReturn(expectedLog);

    // Act
    Log actualLog = logService.addLog(deviceID, sensorID, localDateTime, readingValue, sensorTypeID,
        unitID);

    // Assert
    assertNotNull(actualLog);
    assertEquals(expectedLog, actualLog);
  }

  /**
   * Test that the LogServiceImpl throws an IllegalArgumentException when the deviceID does not
   * exist.
   */
  @Test
  void shouldThrowException_WhenDeviceIDDoesNotExist() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = mock(ILogFactory.class);
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    DeviceID deviceID = mock(DeviceID.class);
    SensorID sensorID = mock(SensorID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    UnitID unitID = mock(UnitID.class);
    LocalDateTime localDateTime = LocalDateTime.now();
    ReadingValue readingValue = mock(ReadingValue.class);

    when(deviceRepository.containsOfIdentity(deviceID)).thenReturn(false);

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> logService.addLog(deviceID, sensorID, localDateTime, readingValue, sensorTypeID,
            unitID));
    assertEquals("Device ID does not exist", exception.getMessage());
  }

  /**
   * Test that the LogServiceImpl throws an IllegalArgumentException when the sensorID does not
   * exist.
   */
  @Test
  void shouldThrowException_WhenSensorIDDoesNotExist() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = mock(ILogFactory.class);
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    DeviceID deviceID = mock(DeviceID.class);
    SensorID sensorID = mock(SensorID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    UnitID unitID = mock(UnitID.class);
    LocalDateTime localDateTime = LocalDateTime.now();
    ReadingValue readingValue = mock(ReadingValue.class);

    when(deviceRepository.containsOfIdentity(deviceID)).thenReturn(true);
    when(sensorRepository.containsOfIdentity(sensorID)).thenReturn(false);

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> logService.addLog(deviceID, sensorID, localDateTime, readingValue, sensorTypeID,
            unitID));
    assertEquals("Sensor ID does not exist", exception.getMessage());
  }

  /**
   * Test that the LogServiceImpl throws an IllegalArgumentException when the sensorTypeID does not
   * exist.
   */
  @Test
  void shouldThrowException_WhenSensorTypeIDDoesNotExist() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = mock(ILogFactory.class);
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    DeviceID deviceID = mock(DeviceID.class);
    SensorID sensorID = mock(SensorID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    UnitID unitID = mock(UnitID.class);
    LocalDateTime localDateTime = LocalDateTime.now();
    ReadingValue readingValue = mock(ReadingValue.class);

    when(deviceRepository.containsOfIdentity(deviceID)).thenReturn(true);
    when(sensorRepository.containsOfIdentity(sensorID)).thenReturn(true);
    when(sensorTypeRepository.containsOfIdentity(sensorTypeID)).thenReturn(false);

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> logService.addLog(deviceID, sensorID, localDateTime, readingValue, sensorTypeID,
            unitID));
    assertEquals("Sensor Type ID does not exist", exception.getMessage());
  }

  /**
   * Test that the LogServiceImpl throws an IllegalArgumentException when the unitID does not
   * exist.
   */
  @Test
  void shouldThrowException_WhenUnitIDDoesNotExist() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = mock(ILogFactory.class);
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    DeviceID deviceID = mock(DeviceID.class);
    SensorID sensorID = mock(SensorID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    UnitID unitID = mock(UnitID.class);
    LocalDateTime localDateTime = LocalDateTime.now();
    ReadingValue readingValue = mock(ReadingValue.class);

    when(deviceRepository.containsOfIdentity(deviceID)).thenReturn(true);
    when(sensorRepository.containsOfIdentity(sensorID)).thenReturn(true);
    when(sensorTypeRepository.containsOfIdentity(sensorTypeID)).thenReturn(true);
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(false);

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> logService.addLog(deviceID, sensorID, localDateTime, readingValue, sensorTypeID,
            unitID));
    assertEquals("Unit ID does not exist", exception.getMessage());
  }






  /**
   * Test that the LogServiceImpl class throws an IllegalArgumentException when the LogRepository is
   * null.
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenLogRepositoryIsNull() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    ILogRepository logRepository = null;
    String expectedMessage = "Log Repository is required";
    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new LogServiceImpl(logRepository, deviceRepository, sensorRepository,
            sensorTypeRepository, unitRepository, logFactory));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test if the List of logs is returned
   */
  @Test
  void shouldReturnLogs_whenDeviceReadingsByTimePeriodIsCalled() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    DeviceID deviceID = mock(DeviceID.class);
    DatePeriod period = mock(DatePeriod.class);

    Log log = mock(Log.class);
    Log log2 = mock(Log.class);

    List<Log> expectedLogs = List.of(log, log2);

    ILogRepository logRepository = mock(ILogRepository.class);
    when(logRepository.findByDeviceIDAndDatePeriodBetween(deviceID, period)).thenReturn(
        expectedLogs);

    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    // Act
    List<Log> actualLogs = logService.getDeviceReadingsByTimePeriod(deviceID, period);

    // Assert
    assertEquals(expectedLogs, actualLogs);
  }

  /**
   * Test if the List of logs is empty
   */
  @Test
  void shouldReturnEmptyList_whenDeviceReadingsByTimePeriodIsCalled() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    DeviceID deviceID = mock(DeviceID.class);
    DatePeriod period = mock(DatePeriod.class);

    List<Log> expectedLogs = emptyList();

    ILogRepository logRepository = mock(ILogRepository.class);
    when(logRepository.findByDeviceIDAndDatePeriodBetween(deviceID, period)).thenReturn(
        expectedLogs);

    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    // Act
    List<Log> actualLogs = logService.getDeviceReadingsByTimePeriod(deviceID, period);

    // Assert
    assertEquals(expectedLogs, actualLogs);
  }

  /**
   * Test if the List of logs is returned not empty
   */
  @Test
  void shouldReturnLogs_whenDeviceReadingsBySensorTypeAndTimePeriodIsCalled()
      throws Exception {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    DatePeriod period = mock(DatePeriod.class);

    Log log = mock(Log.class);
    Log log2 = mock(Log.class);

    List<Log> expectedLogs = List.of(log, log2);

    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(deviceID, sensorTypeID,
        period))
        .thenReturn(expectedLogs);

    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    // Act
    List<Log> actualLogs = logService.getDeviceReadingsBySensorTypeAndTimePeriod(deviceID,
        sensorTypeID, period);

    // Assert
    assertEquals(expectedLogs, actualLogs);
  }

  /**
   * Test if the List of logs is empty
   */
  @Test
  void shouldThrowException_whenDeviceReadingsBySensorTypeAndTimePeriodIsCalledAndGetsEmptyList() {
    // Arrange

    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    DatePeriod period = mock(DatePeriod.class);

    List<Log> emptyLogs = emptyList();

    ILogRepository logRepository = mock(ILogRepository.class);
    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(deviceID, sensorTypeID,
        period))
        .thenReturn(emptyLogs);

    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    String expectedMessage = "No readings found for the given time period";

    // Act
    Exception exception = assertThrows(Exception.class,
        () -> logService.getDeviceReadingsBySensorTypeAndTimePeriod(deviceID, sensorTypeID,
            period));

    // Assert
    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test for method getDifferenceBetweenReadings when single readings are within an interval of 5 minutes.
   */
  @Test
  void shouldReturnMaxDifferenceBetweenReadings_whenGetMaxDifferenceBetweenReadingsIsCalledAndReadingsAreWithin5MinutesInterval() throws Exception{
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    /* Mocking one log object and adding it to a list*/
    ReadingValue readingValue1 = mock(ReadingValue.class);
    when(readingValue1.getValue()).thenReturn("5");
    Log log1 = mock(Log.class);
    when(log1.getReadingValue()).thenReturn(readingValue1);
    when(log1.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 1));

    List<Log> list1 = List.of(log1);

    /* Mocking another log object and adding it to another list*/
    ReadingValue readingValue2 = mock(ReadingValue.class);
    when(readingValue2.getValue()).thenReturn("14");
    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue2);
    when(log2.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 5));

    List<Log> list2 = List.of(log2);

    ILogRepository logRepository = mock(ILogRepository.class);
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    TimeDelta timeDelta = mock(TimeDelta.class);
    when(timeDelta.getMinutes()).thenReturn(5);

    int expectedDifference = 9;

    // Act
    int actualDifference = logService.getMaxDifferenceBetweenReadingsThatAreWithinTimeDelta(list1, list2, timeDelta);

    // Assert
    assertEquals(expectedDifference, actualDifference);
  }

  /**
   * Test for method getDifferenceBetweenReadings when multiple readings are within an interval of 5 minutes.
   */
  @Test
  void shouldReturnMaxDifferenceBetweenReadings_whenGetMaxDifferenceBetweenReadingsIsCalledAndMultipleReadingsAreWithin5MinutesInterval() throws Exception {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    /* Mocking one log object and adding it to a list*/
    ReadingValue readingValue1 = mock(ReadingValue.class);
    when(readingValue1.getValue()).thenReturn("5");
    Log log1 = mock(Log.class);
    when(log1.getReadingValue()).thenReturn(readingValue1);
    when(log1.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 1));

    /* Mocking another log object and adding it to the second list*/
    ReadingValue readingValue2 = mock(ReadingValue.class);
    when(readingValue2.getValue()).thenReturn("14");
    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue2);
    when(log2.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 2));

    /* Mocking another log object and adding it to the second list*/
    ReadingValue readingValue3 = mock(ReadingValue.class);
    when(readingValue3.getValue()).thenReturn("20");
    Log log3 = mock(Log.class);
    when(log3.getReadingValue()).thenReturn(readingValue3);
    when(log3.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 3));

    List<Log> list1 = List.of(log1);

    List<Log> list2 = List.of(log2, log3);

    ILogRepository logRepository = mock(ILogRepository.class);
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    TimeDelta timeDelta = mock(TimeDelta.class);
    when(timeDelta.getMinutes()).thenReturn(5);

    int expectedDifference = 15;

    // Act
    int actualDifference = logService.getMaxDifferenceBetweenReadingsThatAreWithinTimeDelta(list1, list2, timeDelta);

    // Assert
    assertEquals(expectedDifference, actualDifference);
  }


  /**
   * Test for method getDifferenceBetweenReadings when the readings are not within an interval of 5
   * minutes.
   */
  @Test
  void shouldThrowException_whenGetMaxDifferenceBetweenReadingsIsCalledAndReadingsAreNotWithin5MinutesInterval() {
    // Arrange
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    /* Mocking one log object and adding it to a list*/
    ReadingValue readingValue1 = mock(ReadingValue.class);
    when(readingValue1.getValue()).thenReturn("5");
    Log log1 = mock(Log.class);
    when(log1.getReadingValue()).thenReturn(readingValue1);
    when(log1.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 1));

    List<Log> list1 = List.of(log1);

    /* Mocking another log object and adding it to another list*/
    ReadingValue readingValue2 = mock(ReadingValue.class);
    when(readingValue2.getValue()).thenReturn("14");
    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue2);
    when(log2.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 6));

    List<Log> list2 = List.of(log2);

    ILogRepository logRepository = mock(ILogRepository.class);
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    TimeDelta timeDelta = mock(TimeDelta.class);
    when(timeDelta.getMinutes()).thenReturn(5);

    String expectedMessage = "No readings found within the given time interval";

    // Act
    Exception exception = assertThrows(Exception.class,
        () -> logService.getMaxDifferenceBetweenReadingsThatAreWithinTimeDelta(list1, list2, timeDelta));

    // Assert
    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  void shouldReturnSumOfTwoReadingContainingInteger_WhenGetSumOfTwoIntegerReadingsCalled() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);
    ReadingValue readingValue = mock(ReadingValue.class);
    when(readingValue.getValue()).thenReturn("5");
    Log log = mock(Log.class);
    when(log.getReadingValue()).thenReturn(readingValue);

    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue);

    int expected = 10;
    // Act
    int result = logService.getSumOfTwoIntegerReadings(log, log2);
    // Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldThrowExeption_WhenGetSumOfTwoIntegerReadingsCalledOnNonIntengerReading() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);
    ReadingValue readingValue = mock(ReadingValue.class);
    when(readingValue.getValue()).thenReturn("testes");
    Log log = mock(Log.class);
    when(log.getReadingValue()).thenReturn(readingValue);

    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue);

    String expected = "Reading values are not integers";

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> logService.getSumOfTwoIntegerReadings(log, log2));
    // Assert
    String actual = exception.getMessage();
    assertEquals(expected, actual);
  }

  @Test
  void shouldReturnDifferenceOfTwoReadingContainingInteger_WhenGetDifferenceBetweenReadingsCalled() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);
    ReadingValue readingValue = mock(ReadingValue.class);
    when(readingValue.getValue()).thenReturn("5");
    Log log = mock(Log.class);
    when(log.getReadingValue()).thenReturn(readingValue);

    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue);

    int expected = 0;
    // Act
    int result = logService.getDifferenceBetweenReadings(log, log2);
    // Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldThrowExeption_WWhenGetDifferenceBetweenReadingsCalledWithNonIntengerReading() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);
    ReadingValue readingValue = mock(ReadingValue.class);
    when(readingValue.getValue()).thenReturn("testes");
    Log log = mock(Log.class);
    when(log.getReadingValue()).thenReturn(readingValue);

    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue);

    String expected = "Reading values are not integers";

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> logService.getDifferenceBetweenReadings(log, log2));
    // Assert
    String actual = exception.getMessage();
    assertEquals(expected, actual);
  }

  @Test
  void shouldReturnTheMaximumValueFromAListOfLogs_WhenGetMaximumValueFromListOfIntegersIsCalled() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);
    ReadingValue readingValue = mock(ReadingValue.class);
    when(readingValue.getValue()).thenReturn("5");
    Log log = mock(Log.class);
    when(log.getReadingValue()).thenReturn(readingValue);

    ReadingValue readingValue2 = mock(ReadingValue.class);
    when(readingValue2.getValue()).thenReturn("10");
    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue2);

    List<Log> logs = List.of(log, log2);

    int expected = 10;
    // Act
    int result = logService.getMaximumValueFromListOfIntegers(logs);
    // Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldReturnTheMaximumValueFromAListOfLogsWithNegativeAndZero_WhenGetMaximumValueFromListOfIntegersIsCalled() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);
    ReadingValue readingValue = mock(ReadingValue.class);
    when(readingValue.getValue()).thenReturn("5");
    Log log = mock(Log.class);
    when(log.getReadingValue()).thenReturn(readingValue);

    ReadingValue readingValue2 = mock(ReadingValue.class);
    when(readingValue2.getValue()).thenReturn("-10");
    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue2);

    ReadingValue readingValue3 = mock(ReadingValue.class);
    when(readingValue3.getValue()).thenReturn("0");
    Log log3 = mock(Log.class);
    when(log3.getReadingValue()).thenReturn(readingValue3);

    List<Log> logs = List.of(log, log2, log3);

    int expected = 5;
    // Act
    int result = logService.getMaximumValueFromListOfIntegers(logs);
    // Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldReturnMapWithAllPositionsOfTwoListsThatAreWithinTimeDelta_WhenGetPositionsOfReadingsWithinTimeDeltaIsCalled() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    ReadingValue readingValue = mock(ReadingValue.class);
    when(readingValue.getValue()).thenReturn("5");
    Log log = mock(Log.class);
    when(log.getReadingValue()).thenReturn(readingValue);
    when(log.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 1));

    ReadingValue readingValue2 = mock(ReadingValue.class);
    when(readingValue2.getValue()).thenReturn("10");
    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue2);
    when(log2.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 2));

    ReadingValue readingValue3 = mock(ReadingValue.class);
    when(readingValue3.getValue()).thenReturn("10");
    Log log3 = mock(Log.class);
    when(log3.getReadingValue()).thenReturn(readingValue2);
    when(log3.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 8));

    List<Log> list1 = List.of(log);
    List<Log> list2 = List.of(log2, log3);

    int timeDelta = 5;

    Map<Integer, Integer> expected = new HashMap<>();
    expected.put(0, 0);

    // Act
    Map<Integer, Integer> result = logService.calculateMapWithPositionsOfReadingsWithinTimeDelta(list1, list2,
        timeDelta);
    // Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldReturnEmptyMapWhenNoReadingsAreWithinTimeDelta_WhenGetPositionsOfReadingsWithinTimeDeltaIsCalled() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    ReadingValue readingValue = mock(ReadingValue.class);
    when(readingValue.getValue()).thenReturn("5");
    Log log = mock(Log.class);
    when(log.getReadingValue()).thenReturn(readingValue);
    when(log.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 1));

    ReadingValue readingValue2 = mock(ReadingValue.class);
    when(readingValue2.getValue()).thenReturn("10");
    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue2);
    when(log2.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 6));

    List<Log> list1 = List.of(log);
    List<Log> list2 = List.of(log2);

    int timeDelta = 5;

    Map<Integer, Integer> expected = new HashMap<>();

    // Act
    Map<Integer, Integer> result = logService.calculateMapWithPositionsOfReadingsWithinTimeDelta(list1, list2,
        timeDelta);
    // Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldReturnTrueWhenTwoLogsAreWithinTimeDelta_WheShouldReturnTrueWhenReadingIsWithinTimeDeltaIsCalled() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    ReadingValue readingValue = mock(ReadingValue.class);
    when(readingValue.getValue()).thenReturn("5");
    Log log = mock(Log.class);
    when(log.getReadingValue()).thenReturn(readingValue);
    when(log.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 1));

    ReadingValue readingValue2 = mock(ReadingValue.class);
    when(readingValue2.getValue()).thenReturn("10");
    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue2);
    when(log2.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 2));

    int timeDelta = 5;

    boolean expected = true;

    // Act
    boolean result = logService.shouldReturnTrueWhenReadingIsWithinTimeDelta(log, log2, timeDelta);
    // Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldReturnFalseWhenTwoLogsAreNotWithinTimeDelta_WheShouldReturnTrueWhenReadingIsWithinTimeDeltaIsCalled() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    ReadingValue readingValue = mock(ReadingValue.class);
    when(readingValue.getValue()).thenReturn("5");
    Log log = mock(Log.class);
    when(log.getReadingValue()).thenReturn(readingValue);
    when(log.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 1));

    ReadingValue readingValue2 = mock(ReadingValue.class);
    when(readingValue2.getValue()).thenReturn("10");
    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue2);
    when(log2.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 6));

    int timeDelta = 5;

    boolean expected = false;

    // Act
    boolean result = logService.shouldReturnTrueWhenReadingIsWithinTimeDelta(log, log2, timeDelta);
    // Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldReturnPeakPowerConsumptionWhenResultIsSumWithinDelta_WhenGetPeakPowerConsumptionIsCalled()
       {
    // Arrange
         ILogRepository logRepository = mock(ILogRepository.class);
         IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
         ISensorRepository sensorRepository = mock(ISensorRepository.class);
         ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
         IUnitRepository unitRepository = mock(IUnitRepository.class);
         ILogFactory logFactory = new LogFactoryImpl();
         LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
             sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    ReadingValue readingValue = mock(ReadingValue.class);
    when(readingValue.getValue()).thenReturn("5");
    Log log = mock(Log.class);
    when(log.getReadingValue()).thenReturn(readingValue);
    when(log.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 1));

    ReadingValue readingValue2 = mock(ReadingValue.class);
    when(readingValue2.getValue()).thenReturn("10");
    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue2);
    when(log2.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 2));

    List<Log> list1 = List.of(log);
    List<Log> list2 = List.of(log2);

    TimeDelta timeDelta = mock(TimeDelta.class);
    when(timeDelta.getMinutes()).thenReturn(5);

    int expected = 15;

    // Act
    int result = logService.getPeakPowerConsumption(list1, list2, timeDelta);
    // Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldReturnPeakPowerConsumptionWhenResultIsReadingFromFirstList_WhenGetPeakPowerConsumptionIsCalled()
       {
    // Arrange
         ILogRepository logRepository = mock(ILogRepository.class);
         IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
         ISensorRepository sensorRepository = mock(ISensorRepository.class);
         ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
         IUnitRepository unitRepository = mock(IUnitRepository.class);
         ILogFactory logFactory = new LogFactoryImpl();
         LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
             sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    ReadingValue readingValue = mock(ReadingValue.class);
    when(readingValue.getValue()).thenReturn("10");
    Log log = mock(Log.class);
    when(log.getReadingValue()).thenReturn(readingValue);
    when(log.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 1));

    ReadingValue readingValue1 = mock(ReadingValue.class);
    when(readingValue1.getValue()).thenReturn("2");
    Log log1 = mock(Log.class);
    when(log1.getReadingValue()).thenReturn(readingValue);
    when(log1.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 4));

    ReadingValue readingValue2 = mock(ReadingValue.class);
    when(readingValue2.getValue()).thenReturn("5");
    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue2);
    when(log2.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 6));

    List<Log> list1 = List.of(log);
    List<Log> list2 = List.of(log2);

    TimeDelta timeDelta = mock(TimeDelta.class);
    when(timeDelta.getMinutes()).thenReturn(5);

    int expected = 10;

    // Act
    int result = logService.getPeakPowerConsumption(list1, list2, timeDelta);
    // Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldReturnPeakPowerConsumptionWhenResultIsReadingFromSecondList_WhenGetPeakPowerConsumptionIsCalled()
       {
    // Arrange
         ILogRepository logRepository = mock(ILogRepository.class);
         IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
         ISensorRepository sensorRepository = mock(ISensorRepository.class);
         ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
         IUnitRepository unitRepository = mock(IUnitRepository.class);
         ILogFactory logFactory = new LogFactoryImpl();
         LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
             sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    ReadingValue readingValue = mock(ReadingValue.class);
    when(readingValue.getValue()).thenReturn("5");
    Log log = mock(Log.class);
    when(log.getReadingValue()).thenReturn(readingValue);
    when(log.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 1));

    ReadingValue readingValue2 = mock(ReadingValue.class);
    when(readingValue2.getValue()).thenReturn("10");
    Log log2 = mock(Log.class);
    when(log2.getReadingValue()).thenReturn(readingValue2);
    when(log2.getTimeStamp()).thenReturn(LocalDateTime.of(2024, 1, 1, 1, 8));

    List<Log> list1 = List.of(log);
    List<Log> list2 = List.of(log2);

    TimeDelta timeDelta = mock(TimeDelta.class);
    when(timeDelta.getMinutes()).thenReturn(5);

    int expected = 10;

    // Act
    int result = logService.getPeakPowerConsumption(list2, list1, timeDelta);
    // Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldReturnLogsOnGetReadingsInTimePeriodByListOfDevicesAndSensorTypeIsCalled() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    // Mock Log data
    Log log1 = createMockLog("5", LocalDateTime.of(2024, 1, 1, 1, 1));
    Log log2 = createMockLog("10", LocalDateTime.of(2024, 1, 1, 1, 2));
    List<Log> expectedLogs = List.of(log1, log2);

    // Mock Device and related data
    Device device = mock(Device.class);
    DeviceID deviceID = mock(DeviceID.class);
    when(device.getID()).thenReturn(deviceID);
    List<Device> devices = List.of(device);

    // Mock input parameters
    DatePeriod datePeriod = mock(DatePeriod.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(deviceID, sensorTypeID,
        datePeriod))
        .thenReturn(expectedLogs);

    // Act
    List<Log> result = logService.getReadingsInTimePeriodByListOfDevicesAndSensorType(devices,
        datePeriod, sensorTypeID);

    // Assert
    assertEquals(expectedLogs, result);
  }

  @Test
  void shouldReturnLogsForMultipleDevicesAndMultipleLogEntries() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    // Mock Log data (5 entries)
    List<Log> sampleslogs = Arrays.asList(
        createMockLog("2.5", LocalDateTime.of(2024, 1, 2, 10, 30)),
        createMockLog("18.3", LocalDateTime.of(2024, 1, 3, 14, 15)),
        createMockLog("7.2", LocalDateTime.of(2024, 1, 1, 12, 45)),
        createMockLog("11.0", LocalDateTime.of(2024, 1, 2, 18, 0)),
        createMockLog("4.8", LocalDateTime.of(2024, 1, 3, 9, 20))
    );

    List<Log> expectedLogs = new ArrayList<>();
    expectedLogs.addAll(sampleslogs);
    expectedLogs.addAll(sampleslogs);

    // Mock 3 Devices
    Device device1 = mock(Device.class, withSettings().defaultAnswer(Answers.RETURNS_DEEP_STUBS));
    DeviceID deviceID1 = mock(DeviceID.class);
    when(device1.getID()).thenReturn(deviceID1);

    Device device2 = mock(Device.class, withSettings().defaultAnswer(Answers.RETURNS_DEEP_STUBS));
    DeviceID deviceID2 = mock(DeviceID.class);
    when(device2.getID()).thenReturn(deviceID2);

    List<Device> devices = Arrays.asList(device1, device2);

    // Mock input parameters and repository behavior
    DatePeriod datePeriod = mock(DatePeriod.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(any(DeviceID.class),
        eq(sensorTypeID), eq(datePeriod)))
        .thenReturn(sampleslogs); // Return all logs for any device

    // Act
    List<Log> result = logService.getReadingsInTimePeriodByListOfDevicesAndSensorType(devices,
        datePeriod, sensorTypeID);

    // Assert
    assertEquals(expectedLogs, result);
  }

  @Test
  void shouldReturnEmptyListWhenNoLogsFound() {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    // Mock Devices (you can reuse the same devices from the previous example)
    Device device1 = mock(Device.class, withSettings().defaultAnswer(Answers.RETURNS_DEEP_STUBS));
    DeviceID deviceID1 = mock(DeviceID.class);
    when(device1.getID()).thenReturn(deviceID1);

    Device device2 = mock(Device.class, withSettings().defaultAnswer(Answers.RETURNS_DEEP_STUBS));
    DeviceID deviceID2 = mock(DeviceID.class);
    when(device2.getID()).thenReturn(deviceID2);

    Device device3 = mock(Device.class, withSettings().defaultAnswer(Answers.RETURNS_DEEP_STUBS));
    DeviceID deviceID3 = mock(DeviceID.class);
    when(device3.getID()).thenReturn(deviceID3);

    List<Device> devices = Arrays.asList(device1, device2, device3);

    // Mock input parameters
    DatePeriod datePeriod = mock(DatePeriod.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);

    // Mock repository to return an empty list
    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(any(DeviceID.class),
        eq(sensorTypeID), eq(datePeriod)))
        .thenReturn(Collections.emptyList());

    // Act
    List<Log> result = logService.getReadingsInTimePeriodByListOfDevicesAndSensorType(devices,
        datePeriod, sensorTypeID);

    // Assert
    assertTrue(result.isEmpty(), "Result should be an empty list when no logs are found");
  }

  @Test
  void shouldReturnDevicesReadingsByDeviceIDAndSensorTypeID () {
    //Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository,
        sensorRepository, sensorTypeRepository, unitRepository, logFactory);

    // Mock Log data
    Log log1 = createMockLog("5", LocalDateTime.of(2024, 1, 1, 1, 1));
    Log log2 = createMockLog("10", LocalDateTime.of(2024, 1, 1, 1, 2));
    List<Log> expectedLogs = List.of(log1, log2);

    // Mock Device and related data
    Device device = mock(Device.class);
    DeviceID deviceID = mock(DeviceID.class);
    when(device.getID()).thenReturn(deviceID);

    // Mock input parameters
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(logRepository.findByDeviceIDAndSensorTypeID(deviceID, sensorTypeID))
        .thenReturn(expectedLogs);

    // Act
    List<Log> result = logService.getDeviceReadingsByDeviceIDAndSensorTypeID(deviceID, sensorTypeID);

    // Assert
    assertEquals(expectedLogs, result);
  }
}
