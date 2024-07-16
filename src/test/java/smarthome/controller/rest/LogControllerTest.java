/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.domain.device.Device;
import smarthome.domain.device.IDeviceFactory;
import smarthome.domain.log.ILogFactory;
import smarthome.domain.log.Log;
import smarthome.domain.log.LogFactoryImpl;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.ILogRepository;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitID;

@SpringBootTest
@AutoConfigureMockMvc
class LogControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ILogFactory logFactory;

  @MockBean
  private IDeviceRepository deviceRepository;

  @Autowired
  private IDeviceFactory deviceFactory;

  @MockBean
  private ILogRepository logRepository;

  Log setupLog() {
    ILogFactory logFactory = new LogFactoryImpl();
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 0);
    ReadingValue readingValue = new ReadingValue("20");
    SensorID sensorID = new SensorID("1");
    SensorTypeID sensorTypeID = new SensorTypeID("PositionMeter");
    UnitID unitID = new UnitID("C");
    DeviceID deviceID2 = new DeviceID("2");
    return logFactory.createLog(deviceID2, sensorID, timeStamp, readingValue, sensorTypeID, unitID);
  }

  Log setupLogWithTimeAndValue(LocalDateTime timeStamp, String value) {
    ReadingValue readingValue = new ReadingValue(value);
    SensorID sensorID = new SensorID("1");
    SensorTypeID sensorTypeID = new SensorTypeID("Temperature");
    UnitID unitID = new UnitID("C");
    DeviceID deviceID2 = new DeviceID("2");
    return logFactory.createLog(deviceID2, sensorID, timeStamp, readingValue, sensorTypeID, unitID);
  }

  Device setupPowerMeter() {
    RoomID roomID = new RoomID("1");
    DeviceName deviceName = new DeviceName("PowerMeter");
    DeviceID deviceID = new DeviceID("123");
    DeviceTypeID deviceTypeID = new DeviceTypeID("PowerMeter");
    DeviceStatus deviceStatus = new DeviceStatus(true);

    return deviceFactory.createDevice(deviceID, roomID, deviceName, deviceStatus, deviceTypeID);
  }

  Device setupPowerSource() {
    RoomID roomID = new RoomID("1");
    DeviceName deviceName = new DeviceName("PowerSource");
    DeviceID deviceID = new DeviceID("1234");
    DeviceTypeID deviceTypeID = new DeviceTypeID("PowerSource");
    DeviceStatus deviceStatus = new DeviceStatus(true);

    return deviceFactory.createDevice(deviceID, roomID, deviceName, deviceStatus, deviceTypeID);
  }

  List<Log> setupReadingsGivenDeviceAndTimePeriod(
      Device device,
      SensorTypeID sensorTypeID,
      DatePeriod datePeriod,
      int timeBetweenReadings,
      String value) {
    DeviceID deviceID = device.getID();
    LocalDateTime startDate = datePeriod.getStartDate();
    LocalDateTime endDate = datePeriod.getEndDate();
    SensorID sensorID = new SensorID("1");
    UnitID unitID = new UnitID("Watt");

    List<Log> logs = new ArrayList<>();
    for (LocalDateTime date = startDate;
        date.isBefore(endDate);
        date = date.plusMinutes(timeBetweenReadings)) {
      ReadingValue readingValue = new ReadingValue(value);
      logs.add(logFactory.createLog(deviceID, sensorID, date, readingValue, sensorTypeID, unitID));
    }
    return logs;
  }

  /**
   * Test method to get Device Log (Readings) by Time Period
   */
  @Test
  void shouldGetLogFromDevice_WhenParametersAreValid() throws Exception {
    // Arrange
    String deviceIDStr = "123";
    String timeStart = "2020-03-01T13:45:30";
    String timeEnd = "2022-03-01T13:50:30";
    Log log = setupLog();
    List<Log> logs = List.of(log);

    when(logRepository.findByDeviceIDAndDatePeriodBetween(
        any(DeviceID.class), any(DatePeriod.class)))
        .thenReturn(logs);

    // Act & Assert
    mockMvc
        .perform(
            get("/logs")
                .param("deviceID", deviceIDStr)
                .param("timeStart", timeStart)
                .param("timeEnd", timeEnd))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].reading").value("20"));
  }

  /**
   * Should return empty list of logs when no logs are found
   */
  @Test
  void shouldReturnEmptyList_WhenNoLogsFound() throws Exception {
    // Arrange
    String deviceIDStr = "123";
    String timeStart = "2020-03-01T13:45:30";
    String timeEnd = "2022-03-01T13:50:30";

    when(logRepository.findByDeviceIDAndDatePeriodBetween(
        any(DeviceID.class), any(DatePeriod.class)))
        .thenReturn(new ArrayList<>());

    // Act & Assert
    mockMvc
        .perform(
            get("/logs")
                .param("deviceID", deviceIDStr)
                .param("timeStart", timeStart)
                .param("timeEnd", timeEnd))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(0)));
  }

  /**
   * Should return bad request when start date is after end date
   */
  @Test
  void shouldReturnBadRequest_WhenStartDateIsAfterEndDate() throws Exception {
    // Arrange
    String deviceIDStr = "123";
    String timeStart = "2022-03-01T13:45:30";
    String timeEnd = "2020-03-01T13:50:30";

    // Act & Assert
    mockMvc
        .perform(
            get("/logs")
                .param("deviceID", deviceIDStr)
                .param("timeStart", timeStart)
                .param("timeEnd", timeEnd))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Start date cannot be after end date."));
  }

  /**
   * Should return bad request when start date or end date is wrong format
   */
  @Test
  void shouldReturnBadRequest_WhenDateIsWrongFormat() throws Exception {
    // Arrange
    String deviceIDStr = "123";
    String timeStart = "2022-03-0113:45:30";
    String timeEnd = "2020-03-01T13:50:30";

    // Act & Assert
    mockMvc
        .perform(
            get("/logs")
                .param("deviceID", deviceIDStr)
                .param("timeStart", timeStart)
                .param("timeEnd", timeEnd))
        .andExpect(status().isBadRequest());
  }

  /**
   * Test method to get the maximum instantaneous temperature difference between a device and the
   * outside.
   */
  @Test
  void shouldReturnMaxTempDifference_WhenParametersAreValid() throws Exception {
    // Arrange
    // Create LogDataDTO
    String outsideDeviceIDStr = "123";
    String insideDeviceIDStr = "456";
    LocalDateTime initialTime = LocalDateTime.of(2021, 5, 1, 12, 0);
    LocalDateTime finalTime = LocalDateTime.of(2021, 5, 1, 13, 0);
    int timeDelta = 5;

    // Set up a single log for outside
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 5);
    Log log1 = setupLogWithTimeAndValue(timeStamp, "20");
    List<Log> outsideDeviceLogs = List.of(log1);

    // Set up a single log for inside
    LocalDateTime timeStamp2 = LocalDateTime.of(2021, 5, 1, 12, 5);
    Log log2 = setupLogWithTimeAndValue(timeStamp2, "25");
    List<Log> insideDeviceLogs = List.of(log2);

    String expectedTemperatureDifference = "5";

    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(
        any(DeviceID.class), any(SensorTypeID.class), any(DatePeriod.class)))
        .thenReturn(outsideDeviceLogs)
        .thenReturn(insideDeviceLogs);

    // Act & Assert
    mockMvc
        .perform(
            get("/logs/temperature-difference")
                .contentType(MediaType.APPLICATION_JSON)
                .param("outsideDeviceIDStr", outsideDeviceIDStr)
                .param("insideDeviceIDStr", insideDeviceIDStr)
                .param("initialTime", initialTime.toString())
                .param("finalTime", finalTime.toString())
                .param("timeDelta", String.valueOf(timeDelta)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(expectedTemperatureDifference));
  }

  /**
   * Test method to get the maximum instantaneous temperature difference between a device and the
   * outside when readings exist but are not within the time delta
   */
  @Test
  void shouldNotReturnMaxTempDifference_WhenReadingsNotWithinTimeDelta() throws Exception {
    // Arrange
    // Create LogDataDTO
    String outsideDeviceIDStr = "123";
    String insideDeviceIDStr = "456";
    LocalDateTime initialTime = LocalDateTime.of(2021, 5, 1, 12, 0);
    LocalDateTime finalTime = LocalDateTime.of(2021, 5, 1, 13, 0);
    int timeDelta = 5;

    // Set up a single log for outside
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 5);
    Log log1 = setupLogWithTimeAndValue(timeStamp, "20");
    List<Log> outsideDeviceLogs = List.of(log1);

    // Set up a single log for inside
    LocalDateTime timeStamp2 = LocalDateTime.of(2021, 5, 1, 13, 0);
    Log log2 = setupLogWithTimeAndValue(timeStamp2, "25");
    List<Log> insideDeviceLogs = List.of(log2);

    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(
        any(DeviceID.class), any(SensorTypeID.class), any(DatePeriod.class)))
        .thenReturn(outsideDeviceLogs)
        .thenReturn(insideDeviceLogs);

    // Act & Assert
    mockMvc
        .perform(
            get("/logs/temperature-difference")
                .contentType(MediaType.APPLICATION_JSON)
                .param("outsideDeviceIDStr", outsideDeviceIDStr)
                .param("insideDeviceIDStr", insideDeviceIDStr)
                .param("initialTime", initialTime.toString())
                .param("finalTime", finalTime.toString())
                .param("timeDelta", String.valueOf(timeDelta)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("No readings found within the given time interval"));
  }

  /**
   * Test method to get the maximum instantaneous temperature difference between a device and the
   * outside when no logs are found for outside
   */
  @Test
  void shouldNotReturnMaxTempDifference_WhenNoOutsideReadings() throws Exception {
    // Arrange
    // Create LogDataDTO
    String outsideDeviceIDStr = "123";
    String insideDeviceIDStr = "456";
    LocalDateTime initialTime = LocalDateTime.of(2021, 5, 1, 12, 0);
    LocalDateTime finalTime = LocalDateTime.of(2021, 5, 1, 13, 0);
    int timeDelta = 5;

    // Set up a single log for outside - empty
    List<Log> outsideDeviceLogs = new ArrayList<>();

    // Set up a single log for inside
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 5);
    Log log = setupLogWithTimeAndValue(timeStamp, "25");
    List<Log> insideDeviceLogs = List.of(log);

    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(
        any(DeviceID.class), any(SensorTypeID.class), any(DatePeriod.class)))
        .thenReturn(outsideDeviceLogs)
        .thenReturn(insideDeviceLogs);

    // Act & Assert
    mockMvc
        .perform(
            get("/logs/temperature-difference")
                .contentType(MediaType.APPLICATION_JSON)
                .param("outsideDeviceIDStr", outsideDeviceIDStr)
                .param("insideDeviceIDStr", insideDeviceIDStr)
                .param("initialTime", initialTime.toString())
                .param("finalTime", finalTime.toString())
                .param("timeDelta", String.valueOf(timeDelta)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("No readings found for the given time period"));
  }

  /**
   * Test method to get the maximum instantaneous temperature difference between a device and the
   * outside when no logs are found for inside
   */
  @Test
  void shouldNotReturnMaxTempDifference_WhenNoInsideReadings() throws Exception {
    // Arrange
    // Create LogDataDTO
    String outsideDeviceIDStr = "123";
    String insideDeviceIDStr = "456";
    LocalDateTime initialTime = LocalDateTime.of(2021, 5, 1, 12, 0);
    LocalDateTime finalTime = LocalDateTime.of(2021, 5, 1, 13, 0);
    int timeDelta = 5;

    // Set up a single log for outside - empty
    LocalDateTime timeStamp = LocalDateTime.of(2021, 5, 1, 12, 5);
    Log log = setupLogWithTimeAndValue(timeStamp, "25");
    List<Log> outsideDeviceLogs = List.of(log);

    // Set up a single log for inside
    List<Log> insideDeviceLogs = new ArrayList<>();

    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(
        any(DeviceID.class), any(SensorTypeID.class), any(DatePeriod.class)))
        .thenReturn(outsideDeviceLogs)
        .thenReturn(insideDeviceLogs);

    // Act & Assert
    mockMvc
        .perform(
            get("/logs/temperature-difference")
                .contentType(MediaType.APPLICATION_JSON)
                .param("outsideDeviceIDStr", outsideDeviceIDStr)
                .param("insideDeviceIDStr", insideDeviceIDStr)
                .param("initialTime", initialTime.toString())
                .param("finalTime", finalTime.toString())
                .param("timeDelta", String.valueOf(timeDelta)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("No readings found for the given time period"));
  }

  /**
   * Test method to get the peak power consumption in given time period
   */
  @Test
  void shouldReturnMaxPowerConsumption_WhenParametersAreValid() throws Exception {
    // Arrange
    LocalDateTime initialTime = LocalDateTime.of(2021, 5, 1, 12, 0);
    LocalDateTime finalTime = LocalDateTime.of(2021, 5, 1, 13, 0);

    DatePeriod datePeriod = new DatePeriod(initialTime, finalTime);

    Device powerMeter = setupPowerMeter();
    Device powerSource = setupPowerSource();
    SensorTypeID sensorTypeID = new SensorTypeID("InstantPowerConsumption");

    String readingValue = String.valueOf(20);

    List<Log> powerMeterLogs =
        setupReadingsGivenDeviceAndTimePeriod(
            powerMeter, sensorTypeID, datePeriod, 5, readingValue);
    List<Log> powerSourceLogs =
        setupReadingsGivenDeviceAndTimePeriod(
            powerSource, sensorTypeID, datePeriod, 5, readingValue);

    String expectedPowerConsumption = "40";
    when(deviceRepository.findByDeviceTypeID(powerMeter.getDeviceTypeID()))
        .thenReturn(List.of(powerMeter));
    when(deviceRepository.findByDeviceTypeID(powerSource.getDeviceTypeID()))
        .thenReturn(List.of(powerSource));

    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(
        powerMeter.getID(), sensorTypeID, datePeriod))
        .thenReturn(powerMeterLogs);
    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(
        powerSource.getID(), sensorTypeID, datePeriod))
        .thenReturn(powerSourceLogs);

    // Act & Assert
    mockMvc
        .perform(
            get("/logs/peak-power-consumption")
                .contentType(MediaType.APPLICATION_JSON)
                .param("initialTime", initialTime.toString())
                .param("finalTime", finalTime.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(expectedPowerConsumption));
  }

  /**
   * Test method to get the peak power consumption in given time period when no logs are found
   */
  @Test
  void shouldReturnZeroWhenNoLogsFound_WhenParametersAreValid() throws Exception {
    // Arrange
    LocalDateTime initialTime = LocalDateTime.of(2021, 5, 1, 12, 0);
    LocalDateTime finalTime = LocalDateTime.of(2021, 5, 1, 13, 0);

    DatePeriod datePeriod = new DatePeriod(initialTime, finalTime);

    Device powerMeter = setupPowerMeter();
    Device powerSource = setupPowerSource();
    SensorTypeID sensorTypeID = new SensorTypeID("InstantPowerConsumption");

    List<Log> powerMeterLogs = new ArrayList<>();
    List<Log> powerSourceLogs = new ArrayList<>();

    String expectedPowerConsumption = "0";
    when(deviceRepository.findByDeviceTypeID(powerMeter.getDeviceTypeID()))
        .thenReturn(List.of(powerMeter));
    when(deviceRepository.findByDeviceTypeID(powerSource.getDeviceTypeID()))
        .thenReturn(List.of(powerSource));

    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(
        powerMeter.getID(), sensorTypeID, datePeriod))
        .thenReturn(powerMeterLogs);
    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(
        powerSource.getID(), sensorTypeID, datePeriod))
        .thenReturn(powerSourceLogs);

    // Act & Assert
    mockMvc
        .perform(
            get("/logs/peak-power-consumption")
                .contentType(MediaType.APPLICATION_JSON)
                .param("initialTime", initialTime.toString())
                .param("finalTime", finalTime.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(expectedPowerConsumption));
  }

  /**
   * Test method to get the peak power consumption in given time period when logs are out of time
   *
   * @throws Exception
   */
  @Test
  void shouldReturnHighestReadingOfOneList_WhenLogsOutOfTimeDelta() throws Exception {
    // Arrange
    LocalDateTime initialTime = LocalDateTime.of(2021, 5, 1, 12, 0);
    LocalDateTime finalTime = LocalDateTime.of(2021, 5, 1, 13, 0);

    DatePeriod datePeriod = new DatePeriod(initialTime, finalTime);

    LocalDateTime initialTimeOutOfDelta = LocalDateTime.of(2021, 5, 1, 13, 16);
    LocalDateTime finalTimeOutOfDelta = LocalDateTime.of(2021, 5, 1, 14, 16);

    DatePeriod datePeriodOutOfDelta = new DatePeriod(initialTimeOutOfDelta, finalTimeOutOfDelta);

    Device powerMeter = setupPowerMeter();
    Device powerSource = setupPowerSource();
    SensorTypeID sensorTypeID = new SensorTypeID("InstantPowerConsumption");

    String readingValuePowerMeter = String.valueOf(30);
    String readingValuePowerSource = String.valueOf(20);

    List<Log> powerMeterLogs =
        setupReadingsGivenDeviceAndTimePeriod(
            powerMeter, sensorTypeID, datePeriod, 5, readingValuePowerMeter);
    List<Log> powerSourceLogs =
        setupReadingsGivenDeviceAndTimePeriod(
            powerSource, sensorTypeID, datePeriodOutOfDelta, 5, readingValuePowerSource);

    String expectedPowerConsumption = "30";
    when(deviceRepository.findByDeviceTypeID(powerMeter.getDeviceTypeID()))
        .thenReturn(List.of(powerMeter));
    when(deviceRepository.findByDeviceTypeID(powerSource.getDeviceTypeID()))
        .thenReturn(List.of(powerSource));

    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(
        powerMeter.getID(), sensorTypeID, datePeriod))
        .thenReturn(powerMeterLogs);
    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(
        powerSource.getID(), sensorTypeID, datePeriod))
        .thenReturn(powerSourceLogs);

    // Act & Assert
    mockMvc
        .perform(
            get("/logs/peak-power-consumption")
                .contentType(MediaType.APPLICATION_JSON)
                .param("initialTime", initialTime.toString())
                .param("finalTime", finalTime.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(expectedPowerConsumption));
  }

  /***
   * Test method to get the peak power consumption in given time period when only one list has values
   * @throws Exception
   */
  @Test
  void shouldReturnMaxPowerConsumption_WhenOnlyOneListHasValues() throws Exception {
    // Arrange
    LocalDateTime initialTime = LocalDateTime.of(2021, 5, 1, 12, 0);
    LocalDateTime finalTime = LocalDateTime.of(2021, 5, 1, 13, 0);

    DatePeriod datePeriod = new DatePeriod(initialTime, finalTime);

    Device powerMeter = setupPowerMeter();
    Device powerSource = setupPowerSource();
    SensorTypeID sensorTypeID = new SensorTypeID("InstantPowerConsumption");

    String readingValue = String.valueOf(20);

    List<Log> powerMeterLogs =
        setupReadingsGivenDeviceAndTimePeriod(
            powerMeter, sensorTypeID, datePeriod, 5, readingValue);
    List<Log> powerSourceLogs = new ArrayList<>();

    String expectedPowerConsumption = "20";
    when(deviceRepository.findByDeviceTypeID(powerMeter.getDeviceTypeID()))
        .thenReturn(List.of(powerMeter));
    when(deviceRepository.findByDeviceTypeID(powerSource.getDeviceTypeID()))
        .thenReturn(List.of(powerSource));

    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(
        powerMeter.getID(), sensorTypeID, datePeriod))
        .thenReturn(powerMeterLogs);
    when(logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(
        powerSource.getID(), sensorTypeID, datePeriod))
        .thenReturn(powerSourceLogs);

    // Act & Assert
    mockMvc
        .perform(
            get("/logs/peak-power-consumption")
                .contentType(MediaType.APPLICATION_JSON)
                .param("initialTime", initialTime.toString())
                .param("finalTime", finalTime.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value(expectedPowerConsumption));
  }

  /**
   * Should return the position of the blind roller
   */
  @Test
  void shouldReturnThePositionOfTheBlindRoller() throws Exception {
    // Arrange
    Log log = setupLog();

    String deviceIDStr = "2";
    String sensorTypeIDStr = "PercentagePosition";
    DeviceID deviceID = new DeviceID(deviceIDStr);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDStr);

    when(logRepository.findByDeviceIDAndSensorTypeID(deviceID, sensorTypeID))
        .thenReturn(List.of(log));

    // Act & Assert
    mockMvc
        .perform(
            get("/logs/get-position-blindRoller")
                .contentType(MediaType.APPLICATION_JSON)
                .param("deviceID", deviceIDStr)
                .param("sensorTypeID", sensorTypeIDStr))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").value("20"));
  }

  /**
   * Should return a message error when empty logs
   */
  @Test
  void shouldReturnEmptyList_WhenNoLogsFoundForBlindRoller() throws Exception {
    // Arrange
    String deviceIDStr = "2";
    String sensorTypeIDStr = "PercentagePosition";
    DeviceID deviceID = new DeviceID(deviceIDStr);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDStr);

    when(logRepository.findByDeviceIDAndSensorTypeID(deviceID, sensorTypeID)).thenReturn(List.of());

    // Act & Assert
    mockMvc
        .perform(
            get("/logs/get-position-blindRoller")
                .contentType(MediaType.APPLICATION_JSON)
                .param("deviceID", deviceIDStr)
                .param("sensorTypeID", sensorTypeIDStr))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value(
            "No log records found for the specified device and sensor type."));
  }
}
