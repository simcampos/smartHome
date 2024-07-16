/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.repository;

import smarthome.ddd.IRepository;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.DeviceID;
import java.util.List;

public interface IActuatorRepository extends IRepository<ActuatorID, IActuator> {

  /**
   * Method to find all actuators of a device.
   * @param deviceID is the unique identifier of the device.
   * @return a list of actuators of the device.
   */
  List<IActuator> ofDeviceID(DeviceID deviceID);
}
