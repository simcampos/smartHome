/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.log;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.LogID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.UnitID;

@Component
public class LogFactoryImpl implements ILogFactory {

  /**
   * Creates a new Log instance
   */

  @Override
  public Log createLog(
      DeviceID DeviceId, SensorID sensorId, LocalDateTime timeStamp, ReadingValue readingValue,
      SensorTypeID description, UnitID unit) {
    return new Log(DeviceId, sensorId, timeStamp, readingValue, description, unit);
  }

  /**
   * Creates a new Log instance with LogId
   */

  @Override
  public Log createLog(LogID logId, DeviceID DeviceId, SensorID sensorId, LocalDateTime timeStamp,
      ReadingValue readingValue, SensorTypeID description, UnitID unit) {
    return new Log(logId, DeviceId, sensorId, timeStamp, readingValue, description, unit);
  }
}

