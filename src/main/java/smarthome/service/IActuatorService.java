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
import smarthome.ddd.IActuatorValue;
import smarthome.ddd.IService;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ReadingValue;

public interface IActuatorService extends IService {

  /**
   * Adds a new actuator to the repository.
   *
   * @param parameters the parameters of the actuator.
   * @return the actuator that was added.
   */
  IActuator addActuator(Object... parameters);

  /**
   * Gets all actuators in the repository by the provided actuator ID.
   *
   * @param actuatorID the actuator ID to search for.
   * @return the actuator with the provided actuator ID.
   */
  Optional<IActuator> getActuatorByID(ActuatorID actuatorID);

  /**
   * Gets all actuators in the repository.
   *
   * @return a list of all actuators.
   */
  List<IActuator> getAllActuators();

  /**
   * Gets all actuators in the repository by the provided device ID.
   * @param deviceID the device ID to search for.
   * @return a list of all actuators with the provided device ID.
   */
  List<IActuator> getActuatorsByDeviceID(DeviceID deviceID);

  /**
   * Sets the value of the actuator with the provided actuator ID.
   * @return the value of the actuator.
   */
  IActuatorValue setValue(IActuator actuator , IActuatorValue value);
}
