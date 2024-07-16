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
import smarthome.ddd.IRepository;
import smarthome.domain.sensor_type.ISensorTypeFactory;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.utils.Validator;

@Service
public class SensorTypeServiceImpl implements ISensorTypeService {

  private final IRepository<SensorTypeID, SensorType> sensorTypeRepository;
  private final ISensorTypeFactory sensorTypeFactory;
  private final IRepository<UnitID, Unit> unitRepository;

  /**
   * Constructor for SensorTypeService.
   *
   * @param sensorTypeRepository is the repository for sensor types.
   * @param sensorTypeFactory    is the factory for sensor types.
   * @param unitRepository       is the repository for units.
   */
  public SensorTypeServiceImpl(IRepository<SensorTypeID, SensorType> sensorTypeRepository,
      ISensorTypeFactory sensorTypeFactory, IRepository<UnitID, Unit> unitRepository) {
    Validator.validateNotNull(sensorTypeRepository, "Sensor type repository");
    this.sensorTypeRepository = sensorTypeRepository;
    Validator.validateNotNull(sensorTypeFactory, "Sensor type factory");
    this.sensorTypeFactory = sensorTypeFactory;
    Validator.validateNotNull(unitRepository, "Unit repository");
    this.unitRepository = unitRepository;

  }


  /**
   * Creates a new SensorType.
   *
   * @param name   The description of the sensor type.
   * @param unitID The unit of the sensor type.
   * @return The created SensorType object.
   */
  @Override
  public SensorType createSensorType(TypeDescription name, UnitID unitID) {
    if (!unitRepository.containsOfIdentity(unitID)) {
      throw new IllegalArgumentException("Please enter a valid unit type.");
    }
    SensorType sensorType = sensorTypeFactory.createSensorType(name, unitID);
    addSensorType(sensorType);
    return sensorType;
  }

  /**
   * Saves a SensorType.
   *
   * @param sensorType The SensorType to save.
   * @return The saved SensorType object.
   */
  @Override
  public SensorType addSensorType(SensorType sensorType) {
    if (sensorType == null) {
      throw new IllegalArgumentException("Please enter a valid sensor type.");
    }
    return sensorTypeRepository.save(sensorType);
  }

  /**
   * Finds a SensorType by its ID.
   *
   * @param sensorTypeID The ID of the SensorType to find.
   * @return The SensorType object.
   */
  @Override
  public Optional<SensorType> getSensorTypeByID(SensorTypeID sensorTypeID) {
    if (sensorTypeID == null) {
      throw new IllegalArgumentException("Please enter a valid sensor type ID.");
    }
    return sensorTypeRepository.ofIdentity(sensorTypeID);
  }

  /**
   * Finds all SensorTypes.
   *
   * @return A list of all SensorType objects.
   */
  @Override
  public List<SensorType> getAllSensorTypes() {
    return sensorTypeRepository.findAll();
  }
}
