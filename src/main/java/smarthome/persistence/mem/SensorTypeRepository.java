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
import smarthome.domain.repository.ISensorTypeRepository;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.Validator;


public class SensorTypeRepository implements ISensorTypeRepository {

  private final Map<SensorTypeID, SensorType> DATA = new LinkedHashMap<>();


  /**
   * Save a SensorType. If the SensorType is null, throw an IllegalArgumentException.
   *
   * @param sensorType the SensorType to save.
   * @return the saved SensorType.
   */
  @Override
  public SensorType save(SensorType sensorType) {
    Validator.validateNotNull(sensorType, "SensorType");

    if (containsOfIdentity(sensorType.getID())) {
      throw new IllegalArgumentException("SensorType already exists.");
    } else {
      DATA.put(sensorType.getID(), sensorType);
    }
    return sensorType;
  }

  /**
   * Find all SensorTypes.
   *
   * @return a list of all SensorTypes.
   */
  @Override
  public List<SensorType> findAll() {
    List<SensorType> allSensorTypes = DATA.values().stream().toList();
    return allSensorTypes;
  }

  /**
   * Find a SensorType by its identity.
   *
   * @param sensorTypeID the identity of the SensorType to find.
   * @return the SensorType if found, otherwise Optional.empty().
   */
  @Override
  public Optional<SensorType> ofIdentity(SensorTypeID sensorTypeID) {
    Optional<SensorType> sensorType = Optional.ofNullable(DATA.get(sensorTypeID));
    return sensorType;
  }

  /**
   * Check if a SensorType exists by its identity.
   *
   * @param sensorTypeID the identity of the SensorType to check.
   * @return true if the SensorType exists, otherwise false.
   */
  @Override
  public boolean containsOfIdentity(SensorTypeID sensorTypeID) {
    return DATA.containsKey(sensorTypeID);
  }

}
