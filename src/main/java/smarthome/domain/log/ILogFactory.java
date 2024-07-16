/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.log;

import java.time.LocalDateTime;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.LogID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitID;

public interface ILogFactory {

  /**
   * Creates a new Log instance with the provided log message.
   */
  Log createLog(
      DeviceID DeviceId, SensorID sensorId, LocalDateTime timeStamp, ReadingValue readingValue,
      SensorTypeID description, UnitID unit);

  /**
   * Creates a new Log instance with the provided log message.
   */
  Log createLog(
      LogID logId,
      DeviceID DeviceId,
      SensorID sensorId,
      LocalDateTime timeStamp,
      ReadingValue readingValue, SensorTypeID description, UnitID unit);
}
