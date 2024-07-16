/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.service;

import java.util.List;
import java.util.Optional;
import smarthome.ddd.IService;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.SensorID;

public interface ISensorService extends IService {

  /**
   * Adds a new sensor to the repository.
   *
   * @param parameters the parameters of the sensor.
   * @return the sensor that was added.
   */
  ISensor addSensor(Object... parameters);

  /**
   * Get sensors by ID
   *
   * @param sensorID is the sensorID
   * @return the sensor
   */
  Optional<ISensor> getSensorByID(SensorID sensorID);

  /**
   * Get all sensors
   *
   * @return a list of sensors
   */
  List<ISensor> getAllSensors();

  List<ISensor> getSensorsByDeviceID(DeviceID deviceID);

}
