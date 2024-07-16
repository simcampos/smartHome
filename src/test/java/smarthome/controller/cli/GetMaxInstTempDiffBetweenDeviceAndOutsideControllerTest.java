/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
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
import smarthome.domain.value_object.UnitID;
import smarthome.service.ILogService;
import smarthome.service.LogServiceImpl;

class GetMaxInstTempDiffBetweenDeviceAndOutsideControllerTest {

  /**
   * Test to check if the constructor of the GetMaxInstTempDiffBetweenDeviceAndOutsideController
   * class is instantiated correctly.
   */
  @Test
  void shouldInstantiateGetMaxInstTempDiffBetweenDeviceAndOutsideController() {
    //Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();

    ILogService logService = new LogServiceImpl(logRepository, deviceRepository, sensorRepository,
        sensorTypeRepository, unitRepository, logFactory);

    //Act
    GetMaxInstTempDiffBetweenDeviceAndOutsideController getMaxInstTempDiffBetweenDeviceAndOutsideController = new GetMaxInstTempDiffBetweenDeviceAndOutsideController(
        logService);

    //Assert
    assertNotNull(getMaxInstTempDiffBetweenDeviceAndOutsideController);
  }

  /**
   * Test to check if the constructor of the GetMaxInstTempDiffBetweenDeviceAndOutsideController
   * class throws an exception when the log service is null.
   */
  @Test
  void shouldThrowException_WhenLogServiceIsNull() {
    //Arrange
    ILogService logService = null;

    String expectedMessage = "Log Service is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new GetMaxInstTempDiffBetweenDeviceAndOutsideController(logService));

    String result = exception.getMessage();

    assertEquals(expectedMessage, result);
  }


  /**
   * Test to check if the method getMaxInstTempDiffBetweenDeviceAndOutside returns the correct
   * value.
   */
  @Test
  void shouldReturnCorrectValue_whenGetMaxInstTempDiffBetweenDeviceAndOutsideIsCalled()
      throws Exception {
    // Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    ILogService logService = new LogServiceImpl(logRepository, deviceRepository, sensorRepository,
        sensorTypeRepository, unitRepository, logFactory);

    GetMaxInstTempDiffBetweenDeviceAndOutsideController getMaxInstTempDiffBetweenDeviceAndOutsideController = new GetMaxInstTempDiffBetweenDeviceAndOutsideController(
        logService);

    /* Create DevicesDataDTO */
    String outsideDeviceIDStr = "1";
    String insideDeviceIDStr = "2";

    LocalDateTime initialTime = LocalDateTime.of(2021, 1, 1, 0, 0);
    LocalDateTime finalTime = LocalDateTime.of(2021, 1, 1, 1, 0);
    DatePeriod datePeriod = new DatePeriod(initialTime, finalTime);

    /* Create log data for outside device */
    DeviceID deviceID1 = new DeviceID(outsideDeviceIDStr);
    SensorID sensorID1 = new SensorID("1");
    LocalDateTime timeStamp1 = LocalDateTime.of(2021, 1, 1, 0, 10);
    LocalDateTime timeStamp2 = LocalDateTime.of(2021, 1, 1, 0, 15);
    ReadingValue readingValue1 = new ReadingValue("10");
    ReadingValue readingValue2 = new ReadingValue("15");
    SensorTypeID description = new SensorTypeID("Temperature");
    UnitID unit = new UnitID("Celsius");

    Log log1 = logFactory.createLog(deviceID1, sensorID1, timeStamp1, readingValue1, description,
        unit);
    Log log2 = logFactory.createLog(deviceID1, sensorID1, timeStamp2, readingValue2, description,
        unit);

    /* Create log data for inside device */
    DeviceID deviceID2 = new DeviceID(insideDeviceIDStr);
    SensorID sensorID2 = new SensorID("2");
    LocalDateTime timeStamp3 = LocalDateTime.of(2021, 1, 1, 0, 4);
    LocalDateTime timeStamp4 = LocalDateTime.of(2021, 1, 1, 0, 6);
    ReadingValue readingValue3 = new ReadingValue("5");
    ReadingValue readingValue4 = new ReadingValue("1");

    Log log3 = logFactory.createLog(deviceID2, sensorID2, timeStamp3, readingValue3, description,
        unit);
    Log log4 = logFactory.createLog(deviceID2, sensorID2, timeStamp4, readingValue4, description,
        unit);

    List<Log> outsideLogs = Arrays.asList(log1, log2);
    List<Log> insideLogs = Arrays.asList(log3, log4);

    // Configure mocks
    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(deviceID1, description,
        datePeriod)).thenReturn(outsideLogs);
    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(deviceID2, description,
        datePeriod)).thenReturn(insideLogs);

    int timeDelta = 5;
    int expected = 9;

    // Act
    int result = getMaxInstTempDiffBetweenDeviceAndOutsideController.getMaxInstTempDiffBetweenDeviceAndOutside(
        outsideDeviceIDStr, insideDeviceIDStr, initialTime, finalTime, timeDelta);

    // Assert
    assertEquals(expected, result);
  }


  /**
   * Test to check if the method getMaxInstTempDiffBetweenDeviceAndOutside returns the correct value
   * when the inside device has no readings.
   */
  @Test
  void shouldThrowException_whenInsideDeviceHasNoReadings() {
    //Arrange
    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    ILogService logService = new LogServiceImpl(logRepository, deviceRepository, sensorRepository,
        sensorTypeRepository, unitRepository, logFactory);

    GetMaxInstTempDiffBetweenDeviceAndOutsideController getMaxInstTempDiffBetweenDeviceAndOutsideController = new GetMaxInstTempDiffBetweenDeviceAndOutsideController(
        logService);

    /* Create DevicesDataDTO */
    String outsideDeviceIDStr = "1";
    String insideDeviceIDStr = "2";

    LocalDateTime initialTime = LocalDateTime.of(2021, 1, 1, 0, 0);
    LocalDateTime finalTime = LocalDateTime.of(2021, 1, 1, 1, 0);

    /* Create and save log data for outside device */
    DeviceID deviceID1 = new DeviceID(outsideDeviceIDStr);
    SensorID sensorID1 = new SensorID("1");
    LocalDateTime timeStamp1 = LocalDateTime.of(2021, 1, 1, 0, 10);
    LocalDateTime timeStamp2 = LocalDateTime.of(2021, 1, 1, 0, 15);
    ReadingValue readingValue1 = new ReadingValue("10");
    ReadingValue readingValue2 = new ReadingValue("15");
    SensorTypeID description = new SensorTypeID("Temperature");
    UnitID unit = new UnitID("Celsius");

    Log log1 = logFactory.createLog(deviceID1, sensorID1, timeStamp1, readingValue1, description,
        unit);
    logRepository.save(log1);
    Log log2 = logFactory.createLog(deviceID1, sensorID1, timeStamp2, readingValue2, description,
        unit);
    logRepository.save(log2);

    int timeDelta = 5;

    String expected = "No readings found for the given time period";

    // Act
    Exception result = assertThrows(Exception.class,
        () -> getMaxInstTempDiffBetweenDeviceAndOutsideController.getMaxInstTempDiffBetweenDeviceAndOutside(
            outsideDeviceIDStr, insideDeviceIDStr, initialTime, finalTime, timeDelta));

    // Assert
    String resultMessage = result.getMessage();
    assertEquals(expected, resultMessage);
  }

  /**
   * Test to check if the method getMaxInstTempDiffBetweenDeviceAndOutside returns the correct value
   * when the outside device has no readings.
   */
  @Test
  void shouldThrowException_whenOutsideDeviceHasNoReadings() {
    //Arrange


    /* Create DevicesDataDTO */
    String outsideDeviceIDStr = "1";
    String insideDeviceIDStr = "2";

    LocalDateTime initialTime = LocalDateTime.of(2021, 1, 1, 0, 0);
    LocalDateTime finalTime = LocalDateTime.of(2021, 1, 1, 1, 0);

    /* Create and save log data for inside device */
    DeviceID deviceID2 = new DeviceID(insideDeviceIDStr);
    SensorID sensorID2 = new SensorID("2");
    LocalDateTime timeStamp3 = LocalDateTime.of(2021, 1, 1, 0, 4);
    LocalDateTime timeStamp4 = LocalDateTime.of(2021, 1, 1, 0, 6);
    ReadingValue readingValue3 = new ReadingValue("5");
    ReadingValue readingValue4 = new ReadingValue("1");

    ILogRepository logRepository = mock(ILogRepository.class);
    IDeviceRepository deviceRepository = mock(IDeviceRepository.class);
    ISensorRepository sensorRepository = mock(ISensorRepository.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ILogFactory logFactory = new LogFactoryImpl();
    ILogService logService = new LogServiceImpl(logRepository, deviceRepository, sensorRepository,
        sensorTypeRepository, unitRepository, logFactory);

    GetMaxInstTempDiffBetweenDeviceAndOutsideController getMaxInstTempDiffBetweenDeviceAndOutsideController = new GetMaxInstTempDiffBetweenDeviceAndOutsideController(
        logService);

    Log log3 = logFactory.createLog(deviceID2, sensorID2, timeStamp3, readingValue3,
        new SensorTypeID("Temperature"),
        new UnitID("Celsius"));
    logRepository.save(log3);
    Log log4 = logFactory.createLog(deviceID2, sensorID2, timeStamp4, readingValue4,
        new SensorTypeID("Temperature"),
        new UnitID("Celsius"));
    logRepository.save(log4);

    int timeDelta = 5;

    String expected = "No readings found for the given time period";

    // Act
    Exception result = assertThrows(Exception.class,
        () -> getMaxInstTempDiffBetweenDeviceAndOutsideController.getMaxInstTempDiffBetweenDeviceAndOutside(
            outsideDeviceIDStr, insideDeviceIDStr, initialTime, finalTime, timeDelta));

    // Assert
    String resultMessage = result.getMessage();
    assertEquals(expected, resultMessage);
  }
}