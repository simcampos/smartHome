/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.mem;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.repository.IActuatorRepository;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.DeviceID;
import smarthome.utils.Validator;


public class ActuatorRepository implements IActuatorRepository {

  /**
   * Map to store the Actuator data.
   */
  private final Map<ActuatorID, IActuator> DATA = new LinkedHashMap<>();

  /**
   * Method to save a domain actuator.
   *
   * @param actuator is the domain actuator to be saved.
   * @return the saved actuator.
   */
  @Override
  public IActuator save(IActuator actuator) {
    Validator.validateNotNull(actuator, "Actuator");

    if (containsOfIdentity(actuator.getID())) {
      throw new IllegalArgumentException("Actuator already exists.");
    } else {
      DATA.put(actuator.getID(), actuator);
    }
    return actuator;
  }

  /**
   * Method to find all domain actuators.
   *
   * @return the list of actuators.
   */
  @Override
  public List<IActuator> findAll() {
    List<IActuator> allActuators = DATA.values().stream().toList();
    return allActuators;
  }

  /**
   * Method to find a domain actuator by its unique identifier.
   *
   * @param actuatorID is the unique identifier of the domain entity.
   * @return the actuator by its ID or null.
   */
  @Override
  public Optional<IActuator> ofIdentity(ActuatorID actuatorID) {
    Optional<IActuator> actuator = Optional.ofNullable(DATA.get(actuatorID));
    return actuator;
  }

  /**
   * Method to check if a domain actuator exists by its unique identifier.
   *
   * @param actuatorID is the unique identifier of the domain entity.
   * @return the ID of the actuator or null.
   */
  @Override
  public boolean containsOfIdentity(ActuatorID actuatorID) {
    return DATA.containsKey(actuatorID);
  }

  /**
   * Method to find all domain actuators by deviceID.
   *
   * @param deviceID is the unique identifier of the device.
   * @return the list of actuators in the device.
   */
  @Override
  public List<IActuator> ofDeviceID(DeviceID deviceID) {
    List<IActuator> actuators = DATA.values().stream()
        .filter(actuator -> actuator.getDeviceID().equals(deviceID)).toList();
    return actuators;
  }

}
