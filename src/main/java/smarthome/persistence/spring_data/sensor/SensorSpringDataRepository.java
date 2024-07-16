/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.spring_data.sensor;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import smarthome.domain.repository.ISensorRepository;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.SensorID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.SensorDataModel;
import smarthome.utils.Validator;
import smarthome.utils.visitor_pattern.ISensorVisitorForDataModel;

@Repository
public class SensorSpringDataRepository implements ISensorRepository {

  private final ISensorSpringDataRepository repository;

  private final IDataModelAssembler<SensorDataModel, ISensor> assembler;

  private final ISensorVisitorForDataModel sensorVisitorForDataModel;

  /**
   * Constructs a new repository instance with the specified entity manager factory and data model
   * assembler.
   *
   * @param repository         the repository to use
   * @param dataModelAssembler the converter to transform data models to domain models and vice
   *                           versa
   */
  public SensorSpringDataRepository(ISensorSpringDataRepository repository,
      IDataModelAssembler<SensorDataModel, ISensor> dataModelAssembler,
      ISensorVisitorForDataModel sensorVisitorForDataModel) {
    Validator.validateNotNull(dataModelAssembler, "Data model assembler");
    assembler = dataModelAssembler;
    Validator.validateNotNull(repository, "Repository");
    this.repository = repository;
    Validator.validateNotNull(sensorVisitorForDataModel, "Actuator visitor for data model");
    this.sensorVisitorForDataModel = sensorVisitorForDataModel;
  }

  /**
   * Saves the specified sensor entity to the database.
   *
   * @param sensor the sensor entity to save
   * @return the saved sensor entity
   * @throws IllegalArgumentException if the entity is null
   */
  @Override
  public ISensor save(ISensor sensor) {
    Validator.validateNotNull(sensor, "Sensor");
    sensor.accept(sensorVisitorForDataModel);
    SensorDataModel sensorDataModel = sensorVisitorForDataModel.getSensorDataModel();
    repository.save(sensorDataModel);
    return sensor;
  }

  /**
   * Finds all sensor entities in the database.
   *
   * @return a list of all sensor entities
   */
  @Override
  public List<ISensor> findAll() {
    List<SensorDataModel> sensorDataModels = this.repository.findAll();
    List<ISensor> sensors = assembler.toDomain(sensorDataModels);
    return sensors;
  }

  /**
   * Finds the sensor entity with the specified identity.
   *
   * @param objectID the identity of the sensor entity to find
   * @return the sensor entity with the specified identity
   */
  @Override
  public Optional<ISensor> ofIdentity(SensorID objectID) {
    Optional<SensorDataModel> sensorDataModel = this.repository.findById(objectID.getID());

    if (sensorDataModel.isPresent()) {
      SensorDataModel sensorDataModel1 = sensorDataModel.get();
      ISensor sensor = assembler.toDomain(sensorDataModel1);
      return Optional.of(sensor);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Checks if the repository contains the specified sensor entity.
   *
   * @param objectID the identity of the sensor entity to check
   * @return true if the repository contains the sensor entity, false otherwise
   */
  @Override
  public boolean containsOfIdentity(SensorID objectID) {
    return this.repository.existsById(objectID.getID());
  }


  @Override
  public List<ISensor> ofDeviceID(DeviceID deviceID) {
    List<SensorDataModel> listSensorDataModel = repository.findByDeviceID(deviceID.getID());

    return this.assembler.toDomain(listSensorDataModel);
  }
}

