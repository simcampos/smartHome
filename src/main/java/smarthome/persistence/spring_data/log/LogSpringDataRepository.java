/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.spring_data.log;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import smarthome.domain.log.Log;
import smarthome.domain.repository.ILogRepository;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.LogID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.LogDataModel;
import smarthome.utils.Validator;

@Repository
public class LogSpringDataRepository implements ILogRepository {

  ILogSpringDataRepository repository;
  IDataModelAssembler<LogDataModel, Log> assembler;

  /**
   * LogSpringDataRepository constructor
   *
   * @param repository ILogSpringDataRepository object
   * @param assembler  IDataModelAssembler object
   */
  public LogSpringDataRepository(
      ILogSpringDataRepository repository, IDataModelAssembler<LogDataModel, Log> assembler) {

    Validator.validateNotNull(repository, "Log repository");
    this.repository = repository;
    Validator.validateNotNull(assembler, "Log data model assembler");
    this.assembler = assembler;
  }


  /**
   * Method to save a domain entity.
   *
   * @param entity is the domain entity to be saved.
   * @return the saved domain entity.
   */
  @Override
  public Log save(Log entity) {
    Validator.validateNotNull(entity, "Log");

    LogDataModel dataModel = new LogDataModel(entity);

    repository.save(dataModel);
    return entity;
  }

  /**
   * Method to find all domain entities.
   *
   * @return
   */
  @Override
  public List<Log> findAll() {
    List<LogDataModel> listDataModelSaved = repository.findAll();
    return assembler.toDomain(listDataModelSaved);
  }

  /**
   * Method to find a domain entity by its unique identifier.
   *
   * @param objectID is the unique identifier of the domain entity.
   * @return the domain entity.
   */
  @Override
  public Optional<Log> ofIdentity(LogID objectID) {
    Optional<LogDataModel> dataModelSaved = repository.findById(objectID.getID());
    if (dataModelSaved.isPresent()) {
      Log domain = assembler.toDomain(dataModelSaved.get());
      return Optional.of(domain);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Method to delete a domain entity by its unique identifier.
   *
   * @param objectID is the unique identifier of the domain entity.
   * @return the domain entity.
   */
  @Override
  public boolean containsOfIdentity(LogID objectID) {
    return repository.existsById(objectID.getID());
  }

  /**
   * Method to find logs by device ID and time period
   *
   * @param deviceID DeviceID object
   * @param period   object which contains a LocalDateTime start and end
   * @return List of Log
   */
  @Override
  public List<Log> findByDeviceIDAndDatePeriodBetween(DeviceID deviceID, DatePeriod period) {
    List<LogDataModel> models =
        repository.findByDeviceIDAndTimestampBetween(
            deviceID.getID(), period.getStartDate(), period.getEndDate());
    return assembler.toDomain(models);
  }

  /**
   * Method to find logs by device ID, sensor type and time period
   *
   * @param deviceID     DeviceID object
   * @param sensorTypeID SensorTypeID object
   * @param period       DatePeriod object
   * @return List of Log
   */
  @Override
  public List<Log> findByDeviceIDAndSensorTypeAndDatePeriodBetween(DeviceID deviceID,
      SensorTypeID sensorTypeID, DatePeriod period) {
    List<LogDataModel> models =
        repository.findByDeviceIDAndDescriptionAndTimestampBetween(
            deviceID.getID(), sensorTypeID.toString(), period.getStartDate(), period.getEndDate());
    return assembler.toDomain(models);
  }

  @Override
  public List<Log> findByDeviceIDAndSensorTypeID(DeviceID deviceID, SensorTypeID sensorTypeID) {
    List<LogDataModel> models = repository.findByDeviceIDAndDescription(deviceID.getID(), sensorTypeID.toString());
    return assembler.toDomain(models);
  }
}
