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
import org.springframework.stereotype.Repository;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.repository.ISensorRepository;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.SensorID;
import smarthome.utils.Validator;


public class SensorRepository implements ISensorRepository {

  private final Map<SensorID, ISensor> DATA = new LinkedHashMap<>();

  /**
   * Method to save a domain entity.
   *
   * @param Sensor is the domain entity to be saved.
   * @return the saved domain entity.
   */
  @Override
  public ISensor save(ISensor Sensor) {
    Validator.validateNotNull(Sensor, "Sensor");

    if (containsOfIdentity(Sensor.getID())) {
      throw new IllegalArgumentException("Sensor already exists.");
    } else {
      DATA.put(Sensor.getID(), Sensor);
    }
    return Sensor;
  }

  /**
   * Method to find all domain entities.
   *
   * @return a list of all domain entities.
   */
  @Override
  public List<ISensor> findAll() {
    List<ISensor> allSensors = DATA.values().stream().toList();
    return allSensors;
  }

  /**
   * Method to find a domain entity by its unique identifier.
   *
   * @param SensorID is the unique identifier of the domain entity.
   * @return the domain entity if found, otherwise Optional.empty().
   */
  @Override
  public Optional<ISensor> ofIdentity(SensorID SensorID) {
    Optional<ISensor> Sensor = Optional.ofNullable(DATA.get(SensorID));
    return Sensor;
  }

  /**
   * Method to check if a domain entity exists by its unique identifier.
   *
   * @param SensorID is the unique identifier of the domain entity.
   * @return true if the domain entity exists, otherwise false.
   */
  @Override
  public boolean containsOfIdentity(SensorID SensorID) {
    return DATA.containsKey(SensorID);
  }

  /**
   * Method to find all Sensors in a device.
   *
   * @param deviceID is the unique identifier of the room.
   * @return a list of Sensors in the device.
   */
  public List<ISensor> findByDeviceId(DeviceID deviceID) {
    List<ISensor> Sensors = DATA.values().stream()
        .filter(Sensor -> Sensor.getDeviceID().equals(deviceID)).toList();
    return Sensors;
  }

  @Override
  public List<ISensor> ofDeviceID(DeviceID deviceID) {
    List<ISensor> sensors = DATA.values().stream()
        .filter(sensor -> sensor.getDeviceID().equals(deviceID)).toList();
    return sensors;
  }
}
