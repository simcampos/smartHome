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
import org.springframework.stereotype.Component;
import smarthome.domain.repository.ISensorModelRepository;
import smarthome.domain.sensor_model.ISensorModelFactory;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorModelName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.Validator;

@Component
public class SensorModelServiceImpl implements ISensorModelService {

  private final ISensorModelRepository sensorModelRepository;
  private final ISensorModelFactory factorySensorModel;

  /**
   * Constructor for SensorModelService.
   *
   * @param sensorModelRepository is the repository for sensor models.
   * @param factorySensorModel    is the factory for sensor models.
   */
  public SensorModelServiceImpl(ISensorModelRepository sensorModelRepository,
      ISensorModelFactory factorySensorModel) {
    Validator.validateNotNull(sensorModelRepository, "Sensor model repository");
    this.sensorModelRepository = sensorModelRepository;
    Validator.validateNotNull(factorySensorModel, "Sensor model factory");
    this.factorySensorModel = factorySensorModel;

  }


  /**
   * Creates a new SensorModel and saves it in the repository and saves it.
   *
   * @param sensorModelName The name of the sensor model.
   * @param sensorPath      The path of the sensor model.
   * @param sensorTypeID    The type of the sensor model.
   * @return The created sensor model.
   */
  @Override
  public SensorModel createSensorModel(SensorModelName sensorModelName, ModelPath sensorPath,
      SensorTypeID sensorTypeID) {
    SensorModel sensorModel = factorySensorModel.createSensorModel(sensorModelName, sensorPath,
        sensorTypeID);
    sensorModelRepository.save(sensorModel);
    return sensorModel;
  }


  /**
   * Get all sensor models.
   *
   * @return List of all sensor models.
   */
  @Override
  public List<SensorModel> getAllSensorModels() {
    return sensorModelRepository.findAll();
  }

  /**
   * Get a sensor model by its path.
   *
   * @param modelPath The path of the sensor model.
   * @return The sensor model.
   */
  @Override
  public Optional<SensorModel> getSensorModel(ModelPath modelPath) {
    return sensorModelRepository.ofIdentity(modelPath);
  }

  /**
   * Get all sensor models by sensor type ID.
   *
   * @param sensorTypeID The sensor type ID.
   * @return List of sensor models.
   */
  @Override
  public List<SensorModel> getSensorModelsBySensorTypeId(SensorTypeID sensorTypeID) {
    return sensorModelRepository.findBySensorTypeId(sensorTypeID);
  }
}
