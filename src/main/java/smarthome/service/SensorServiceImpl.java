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
import smarthome.domain.device.Device;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.ISensorRepository;
import smarthome.domain.repository.ISensorTypeRepository;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.sensor.ISensorFactory;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.Validator;

@Service
public class SensorServiceImpl implements ISensorService {

  private final ISensorRepository sensorRepository;
  private final ISensorFactory sensorFactory;
  private final IDeviceRepository deviceRepository;
  private final ISensorTypeRepository sensorTypeRepository;

  /**
   * Constructor for SensorService.
   *
   * @param sensorRepository     is the repository for sensors.
   * @param sensorFactory        is the factory for sensors.
   * @param deviceRepository     is the repository for devices.
   * @param sensorTypeRepository is the repository for sensor types.
   */

  public SensorServiceImpl(
      ISensorRepository sensorRepository,
      ISensorFactory sensorFactory,
      IDeviceRepository deviceRepository, ISensorTypeRepository sensorTypeRepository) {

    Validator.validateNotNull(sensorRepository, "Sensor repository");
    this.sensorRepository = sensorRepository;
    Validator.validateNotNull(sensorFactory, "Sensor factory");
    this.sensorFactory = sensorFactory;
    Validator.validateNotNull(deviceRepository, "Device repository");
    this.deviceRepository = deviceRepository;
    Validator.validateNotNull(sensorTypeRepository, "Sensor type repository");
    this.sensorTypeRepository = sensorTypeRepository;

  }


  /**
   * Adds a sensor to the repository and saves it.
   *
   * @param parameters The parameters required to create a sensor object. The first parameter should
   *                   be of type DeviceID. The rest of the parameters should be the required
   *                   parameters to create a sensor object.
   * @return The created and saved Sensor object.
   */
  @Override
  public ISensor addSensor(Object... parameters) {
    DeviceID deviceID = (DeviceID) parameters[0];
    SensorTypeID sensorTypeID = (SensorTypeID) parameters[2];
    validateSensorTypeID(sensorTypeID);
    validateDeviceID(deviceID);

    ISensor sensor = sensorFactory.create(parameters);
    sensorRepository.save(sensor);

    return sensor;
  }

  /**
   * Retrieves a sensor by its ID
   *
   * @param sensorID is the sensorID
   * @return An Optional containing the retrieved sensor, or empty if not found.
   */
  @Override
  public Optional<ISensor> getSensorByID(SensorID sensorID) {
    return sensorRepository.ofIdentity(sensorID);
  }

  /**
   * Retrieves all sensors
   *
   * @return a list of all sensors
   */
  @Override
  public List<ISensor> getAllSensors() {
    return sensorRepository.findAll();
  }

  @Override
  public List<ISensor> getSensorsByDeviceID(DeviceID deviceID) {
    return sensorRepository.ofDeviceID(deviceID);
  }

  /**
   * Validates the device ID.
   *
   * @param deviceID The device ID to be validated.
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
   * Validates the sensor type ID.
   *
   * @param sensorTypeID The sensor type ID to be validated.
   */
  private void validateSensorTypeID(SensorTypeID sensorTypeID) {
    Optional<SensorType> sensorTypeOptional = sensorTypeRepository.ofIdentity(sensorTypeID);
    if (sensorTypeOptional.isEmpty()) {
      throw new IllegalArgumentException("SensorType with ID " + sensorTypeID + " not found.");
    }
  }


}
