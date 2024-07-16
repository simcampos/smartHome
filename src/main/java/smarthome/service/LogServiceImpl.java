/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smarthome.domain.device.Device;
import smarthome.domain.log.ILogFactory;
import smarthome.domain.log.Log;
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
import smarthome.utils.Validator;

@Service
public class LogServiceImpl implements ILogService {

  private final ILogRepository logRepository;
  private final IDeviceRepository deviceRepository;
  private final ISensorRepository sensorRepository;
  private final ISensorTypeRepository sensorTypeRepository;
  private final IUnitRepository unitRepository;
  private final ILogFactory logFactory;
  private static final int VALUE_IF_NO_POWER_CONSUMPTION = 0;


  /**
   * Constructor of LogServiceImpl
   *
   * @param logRepository ILogRepository object
   */

  @Autowired
  public LogServiceImpl(ILogRepository logRepository, IDeviceRepository deviceRepository,
      ISensorRepository sensorRepository, ISensorTypeRepository sensorTypeRepository,
      IUnitRepository unitRepository, ILogFactory logFactory) {
    this.deviceRepository = deviceRepository;
    this.sensorRepository = sensorRepository;
    this.sensorTypeRepository = sensorTypeRepository;
    this.unitRepository = unitRepository;
    this.logFactory = logFactory;
    Validator.validateNotNull(logRepository, "Log Repository");
    this.logRepository = logRepository;
  }

  /**
   * Method to add a new log
   *
   * @param deviceID
   * @param sensorID
   * @param localDateTime
   * @param readingValue
   * @param sensorTypeID
   * @param unitID
   */
  @Override
  public Log addLog(DeviceID deviceID, SensorID sensorID, LocalDateTime localDateTime,
      ReadingValue readingValue, SensorTypeID sensorTypeID, UnitID unitID) {
    deviceIDexists(deviceID);
    sensorIDexists(sensorID);
    sensorTypeIDexists(sensorTypeID);
    unitIDexists(unitID);

    Log log = logFactory.createLog(deviceID, sensorID, localDateTime, readingValue, sensorTypeID,
        unitID);

    return logRepository.save(log);
  }

  /**
   * Method to check if the device ID exists
   *
   * @param deviceID DeviceID object
   */
  private void deviceIDexists(DeviceID deviceID) {
    if (!deviceRepository.containsOfIdentity(deviceID)) {
      throw new IllegalArgumentException("Device ID does not exist");
    }
  }

  /**
   * Method to check if the sensor ID exists
   *
   * @param sensorID SensorID object
   */
  private void sensorIDexists(SensorID sensorID) {
    if (!sensorRepository.containsOfIdentity(sensorID)) {
      throw new IllegalArgumentException("Sensor ID does not exist");
    }
  }

  /**
   * Method to check if the sensor type ID exists
   *
   * @param sensorTypeID SensorTypeID object
   */
  private void sensorTypeIDexists(SensorTypeID sensorTypeID) {
    if (!sensorTypeRepository.containsOfIdentity(sensorTypeID)) {
      throw new IllegalArgumentException("Sensor Type ID does not exist");
    }
  }

  /**
   * Method to check if the unit ID exists
   *
   * @param unitID UnitID object
   */
  private void unitIDexists(UnitID unitID) {
    if (!unitRepository.containsOfIdentity(unitID)) {
      throw new IllegalArgumentException("Unit ID does not exist");
    }
  }

  /**
   * Method to get device readings by time period
   *
   * @param deviceID DeviceID object
   * @param period   DatePeriod object
   * @return List of Log
   */

  @Override
  public List<Log> getDeviceReadingsByTimePeriod(DeviceID deviceID, DatePeriod period) {
    return logRepository.findByDeviceIDAndDatePeriodBetween(deviceID, period);
  }

  /**
   * Method to get device readings by sensor type and time period
   *
   * @param deviceID     DeviceID object
   * @param sensorTypeID SensorTypeID object
   * @param period       DatePeriod object
   * @return List of Log
   */
  @Override
  public List<Log> getDeviceReadingsBySensorTypeAndTimePeriod(DeviceID deviceID,
      SensorTypeID sensorTypeID, DatePeriod period) throws Exception {
    List<Log> deviceReadings = logRepository.findByDeviceIDAndSensorTypeAndDatePeriodBetween(
        deviceID, sensorTypeID, period);

    if (deviceReadings.isEmpty()) {
      throw new IllegalArgumentException(("No readings found for the given time period"));
    }

    return deviceReadings;
  }

  /**
   * Method to get the difference between the reading values of two lists, when the readings are
   * within an interval of 5 minutes.
   *
   * @param readings1 is one list of readings.
   * @param readings2 is another list of readings.
   * @return the list of the differences between the values, as Integers.
   */
  @Override
  public int getMaxDifferenceBetweenReadingsThatAreWithinTimeDelta(List<Log> readings1, List<Log> readings2, TimeDelta timeDelta)
      throws Exception {
    List<Integer> valueDifferences = new ArrayList<>();
    int timeDeltaMinutes = timeDelta.getMinutes();

    Map<Integer, Integer> positionMap = calculateMapWithPositionsOfReadingsWithinTimeDelta(readings1, readings2, timeDeltaMinutes);
    for (Map.Entry<Integer, Integer> entry : positionMap.entrySet()) {
      int difference = getDifferenceBetweenReadings(readings1.get(entry.getKey()), readings2.get(entry.getValue()));
      valueDifferences.add(difference);
    }
    if (valueDifferences.isEmpty()) {
      throw new IllegalArgumentException(("No readings found within the given time interval"));
    }else return Collections.max(valueDifferences);
  }

  /**
   * Method to get the peak power consumption of a device in a given time period.
   *
   * @param readings
   * @param readings2
   * @param timeDelta
   * @return
   */

  public int getPeakPowerConsumption(List<Log> readings, List<Log> readings2, TimeDelta timeDelta) {
    int maxListOne = VALUE_IF_NO_POWER_CONSUMPTION;
    int maxListTwo = VALUE_IF_NO_POWER_CONSUMPTION;
    if (!readings.isEmpty()) {
      maxListOne = getMaximumValueFromListOfIntegers(readings);
    }
    if (!readings2.isEmpty()) {
      maxListTwo = getMaximumValueFromListOfIntegers(readings2);
    }
    int maxWithinTimeDelta = getMaxSumOfAnyReadingsWithinDelta(readings, readings2, timeDelta);

    return Math.max(maxListOne, Math.max(maxListTwo, maxWithinTimeDelta));

  }

  /**
   * Method to get the maximum value from two lists of readings, when the readings are within a time
   * delta.
   *
   * @param readings1
   * @param readings2
   * @param timeDelta
   * @return
   */
  protected int getMaxSumOfAnyReadingsWithinDelta(List<Log> readings1, List<Log> readings2,
      TimeDelta timeDelta) {
    List<Integer> sumOfReadings = new ArrayList<>();
    int timeDeltaMinutes = timeDelta.getMinutes();

    Map<Integer, Integer> positionMap = calculateMapWithPositionsOfReadingsWithinTimeDelta(readings1, readings2,
        timeDeltaMinutes);
    for (Map.Entry<Integer, Integer> entry : positionMap.entrySet()) {
      int sum = getSumOfTwoIntegerReadings(readings1.get(entry.getKey()),
          readings2.get(entry.getValue()));
      sumOfReadings.add(sum);
    }
    if (sumOfReadings.isEmpty()) {
      return VALUE_IF_NO_POWER_CONSUMPTION;
    } else {
      return Collections.max(sumOfReadings);
    }
  }

  /**
   * Method to get the sum of two integer readings.
   *
   * @param reading1
   * @param reading2
   * @return
   */

  protected int getSumOfTwoIntegerReadings(Log reading1, Log reading2) {
    try {
      return Math.abs(Integer.parseInt(reading1.getReadingValue().getValue()) + Integer.parseInt(
          reading2.getReadingValue().getValue()));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Reading values are not integers");
    }
  }

  /**
   * Method to get the difference between two integer readings.
   *
   * @param reading1
   * @param reading2
   * @return
   */

  protected int getDifferenceBetweenReadings(Log reading1, Log reading2) {
    try {
      return Math.abs(Integer.parseInt(reading1.getReadingValue().getValue()) - Integer.parseInt(
          reading2.getReadingValue().getValue()));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Reading values are not integers");
    }
  }

  /**
   * Method to get the maximum value from a list of integer readings.
   *
   * @param readings
   * @return
   */
  protected int getMaximumValueFromListOfIntegers(List<Log> readings) {
    List<Integer> values = new ArrayList<>();
    for (Log reading : readings) {
      values.add(Integer.parseInt(reading.getReadingValue().getValue()));
    }
    return Collections.max(values);
  }

  /**
   * Method to get the positions of readings within a time delta.
   *
   * @param readings1
   * @param readings2
   * @param timeDelta
   * @return a map of the positions of readings within the time delta.
   */

  protected Map<Integer, Integer> calculateMapWithPositionsOfReadingsWithinTimeDelta(List<Log> readings1,
      List<Log> readings2, int timeDelta) {
    Map<Integer, Integer> positionMap = new HashMap<>();
    for (int i = 0; i < readings1.size(); i++) {
      for (int j = 0; j < readings2.size(); j++) {
        boolean isWithinTimeDelta = shouldReturnTrueWhenReadingIsWithinTimeDelta(readings1.get(i), readings2.get(j), timeDelta);
        if (isWithinTimeDelta) {
          positionMap.put(i, j);
        }
      }
    }
    return positionMap;
  }

  /**
   * Method to check if two readings are within a time delta.
   *
   * @param reading1
   * @param reading2
   * @param timeDelta
   * @return
   */
  protected boolean shouldReturnTrueWhenReadingIsWithinTimeDelta(Log reading1, Log reading2, int timeDelta) {
    int diffInMinutes = (int) ChronoUnit.MINUTES.between(reading1.getTimeStamp(),
        reading2.getTimeStamp());

    return Math.abs(diffInMinutes) < timeDelta;
  }

  public List<Log> getReadingsInTimePeriodByListOfDevicesAndSensorType(List<Device> devices,
      DatePeriod datePeriod,
      SensorTypeID sensorTypeID) {
    List<Log> readings = new ArrayList<>();
    for (Device device : devices) {
      DeviceID deviceID = device.getID();
      try {
        List<Log> deviceReadings = getDeviceReadingsBySensorTypeAndTimePeriod(deviceID,
            sensorTypeID, datePeriod);
        readings.addAll(deviceReadings);
      } catch (Exception ignored) {
        readings.addAll(new ArrayList<>());
      }
    }
    return readings;
  }

  /**
   * Method to get the list of readings of a device.
   * @param deviceID is the device ID.
   * @return List of Log
   */
  @Override
  public List<Log> getDeviceReadingsByDeviceIDAndSensorTypeID(DeviceID deviceID, SensorTypeID sensorTypeID) {
    return logRepository.findByDeviceIDAndSensorTypeID(deviceID, sensorTypeID);
  }

}

