/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.spring_data.sensor_type;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import smarthome.domain.repository.ISensorTypeRepository;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.SensorTypeDataModel;
import smarthome.utils.Validator;

@Repository
public class SensorTypeSpringDataRepository implements ISensorTypeRepository {

  ISensorTypeSpringDataRepository repository;
  IDataModelAssembler<SensorTypeDataModel, SensorType> assembler;

  /**
   * Constructor for SensorTypeSpringDataRepository
   *
   * @param repository sensor type spring data repository
   * @param assembler  data model assembler
   */
  public SensorTypeSpringDataRepository(ISensorTypeSpringDataRepository repository,
      IDataModelAssembler<SensorTypeDataModel, SensorType> assembler) {
    Validator.validateNotNull(repository, "Sensor type spring data repository");
    Validator.validateNotNull(assembler, "Data model assembler");

    this.repository = repository;
    this.assembler = assembler;
  }


  /**
   * Method to save a domain entity.
   *
   * @param sensorType is the domain entity to be saved.
   * @return the saved domain entity.
   */
  @Override
  public SensorType save(SensorType sensorType) {
    Validator.validateNotNull(sensorType, "Sensor type");

    SensorTypeDataModel dataModel = new SensorTypeDataModel(sensorType);

    repository.save(dataModel);

    return sensorType;
  }

  /**
   * Method to find all domain entities.
   *
   * @return list of domain entities
   */
  @Override
  public List<SensorType> findAll() {
    List<SensorTypeDataModel> listSensorTypeDataModelSaved = repository.findAll();

    return assembler.toDomain(listSensorTypeDataModelSaved);
  }

  /**
   * Method to find a domain entity by its unique identifier.
   *
   * @param sensorTypeID is the unique identifier of the domain entity.
   * @return the domain entity.
   */
  @Override
  public Optional<SensorType> ofIdentity(SensorTypeID sensorTypeID) {
    Optional<SensorTypeDataModel> sensorTypeDataModel = repository.findById(sensorTypeID.getID());

    if (sensorTypeDataModel.isPresent()) {
      SensorType domain = assembler.toDomain(sensorTypeDataModel.get());
      return Optional.of(domain);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Method to check if a domain entity exists by its unique identifier.
   *
   * @param sensorTypeID is the unique identifier of the domain entity.
   * @return true if the domain entity exists, false otherwise.
   */
  @Override
  public boolean containsOfIdentity(SensorTypeID sensorTypeID) {
    return this.repository.existsById(sensorTypeID.getID());
  }
}
