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
import org.springframework.stereotype.Service;
import smarthome.ddd.IActuatorValue;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.actuator.IActuatorFactory;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.device.Device;
import smarthome.domain.repository.IActuatorRepository;
import smarthome.domain.repository.IActuatorTypeRepository;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.ILogRepository;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ReadingValue;
import smarthome.utils.Validator;

@Service
public class ActuatorServiceImpl implements IActuatorService {

  private final IActuatorRepository actuatorRepository;
  private final IActuatorFactory actuatorFactory;
  private final IDeviceRepository deviceRepository;
  private final IActuatorTypeRepository actuatorTypeRepository;

  /**
   * Constructs an ActuatorService with the specified repositories and factory.
   *
   * @param actuatorRepository The repository for storing actuators.
   * @param actuatorFactory    The factory for creating actuators.
   * @param deviceRepository   The repository for accessing devices.
   * @param actuatorTypeRepository The repository for accessing actuator types.
   */
  public ActuatorServiceImpl(
      IActuatorRepository actuatorRepository,
      IActuatorFactory actuatorFactory,
      IDeviceRepository deviceRepository, IActuatorTypeRepository actuatorTypeRepository) {
    Validator.validateNotNull(actuatorRepository, "Actuator repository");
    this.actuatorRepository = actuatorRepository;
    Validator.validateNotNull(actuatorFactory, "Actuator factory");
    this.actuatorFactory = actuatorFactory;
    Validator.validateNotNull(deviceRepository, "Device repository");
    this.deviceRepository = deviceRepository;
    Validator.validateNotNull(actuatorTypeRepository, "Actuator type repository");
    this.actuatorTypeRepository = actuatorTypeRepository;
  }

  /**
   * Adds a new actuator to the repository and saves it.
   *
   * @param parameters The parameters needed to create the actuator.The first parameter should * be
   *                   of type DeviceID. The rest of the parameters should be the required
   *                   parameters to create * an actuator object.
   * @return The created and saved actuator object.
   */
  @Override
  public IActuator addActuator(Object... parameters) {
    DeviceID deviceID = (DeviceID) parameters[0];
    ActuatorTypeID actuatorTypeID = (ActuatorTypeID) parameters[2];

    validateDeviceID(deviceID);
    validateActuatorTypeID(actuatorTypeID);

    IActuator actuator = actuatorFactory.create(parameters);
    actuatorRepository.save(actuator);

    return actuator;
  }

  /**
   * Validates the device ID.
   *
   * @param deviceID The ID of the device to validate.
   */
  private void validateDeviceID(DeviceID deviceID) {
    Optional<Device> deviceOptional = deviceRepository.ofIdentity(deviceID);
    if (deviceOptional.isEmpty()) {
      throw new IllegalArgumentException("Device with ID " + deviceID + " not found.");
    }
    if (!deviceOptional.get().getDeviceStatus().getStatus()) {
      throw new IllegalArgumentException("Device with ID " + deviceID + " is deactivated.");
    }
  }

  /**
   * Validates the actuator type ID.
   *
   * @param actuatorTypeID The ID of the actuator type to validate.
   */
  private void validateActuatorTypeID(ActuatorTypeID actuatorTypeID) {
    Optional<ActuatorType> actuatorTypeOptional = actuatorTypeRepository.ofIdentity(actuatorTypeID);
    if (actuatorTypeOptional.isEmpty()) {
      throw new IllegalArgumentException("ActuatorType with ID " + actuatorTypeID + " not found.");
    }
  }

  /**
   * Retrieves an actuator by its ID.
   *
   * @param actuatorID The ID of the actuator to retrieve.
   * @return An Optional containing the retrieved actuator, or empty if not found.
   */
  @Override
  public Optional<IActuator> getActuatorByID(ActuatorID actuatorID) {
    return actuatorRepository.ofIdentity(actuatorID);
  }

  /**
   * Retrieves all actuators stored in the system.
   *
   * @return A list of all actuators.
   */
  @Override
  public List<IActuator> getAllActuators() {
    return actuatorRepository.findAll();
  }

  @Override
  public List<IActuator> getActuatorsByDeviceID(DeviceID deviceID) {
    return actuatorRepository.ofDeviceID(deviceID);
  }

  @Override
  public IActuatorValue setValue(IActuator actuator, IActuatorValue valueToSet) {
    int newValue = Integer.parseInt(valueToSet.toString());

    if(newValue >= 0 && newValue <=100) {
      actuator.setValue(valueToSet);
    }
    else {
      throw new IllegalArgumentException("New value must be between 0 and 100.");
    }

    return valueToSet;
  }

}
