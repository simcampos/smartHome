/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.repository;

import java.util.List;
import smarthome.ddd.IRepository;
import smarthome.domain.log.Log;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.LogID;
import smarthome.domain.value_object.SensorTypeID;


public interface ILogRepository extends IRepository<LogID, Log> {


  /**
   * Method to find logs by device and time period
   *
   * @param deviceID DeviceID object
   * @param period   DatePeriod object
   * @return List of Log
   */
  List<Log> findByDeviceIDAndDatePeriodBetween(DeviceID deviceID, DatePeriod period);

  /**
   * Method to find logs by device and sensor type and time period
   *
   * @param deviceID     DeviceID object
   * @param sensorTypeID SensorTypeID object
   * @param period       DatePeriod object
   * @return List of Log
   */
  List<Log> findByDeviceIDAndSensorTypeAndDatePeriodBetween(DeviceID deviceID,
      SensorTypeID sensorTypeID, DatePeriod period);

  /**
   * Method to find logs by device ID
   * @return List of Log
   */
  List<Log> findByDeviceIDAndSensorTypeID(DeviceID deviceID, SensorTypeID sensorTypeID);
}
