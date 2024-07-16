/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.spring_data.actuator_type;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.repository.IActuatorTypeRepository;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.ActuatorTypeDataModel;
import smarthome.utils.Validator;

@Repository
public class ActuatorTypeSpringDataRepository implements IActuatorTypeRepository {

  private final IActuatorTypeSpringDataRepository repository;

  private final IDataModelAssembler<ActuatorTypeDataModel, ActuatorType> dataModelAssembler;


  /**
   * Constructs a new repository instance with the specified entity manager factory and data model
   * assembler.
   *
   * @param repository         the repository to use
   * @param dataModelAssembler the converter to transform data models to domain models and vice
   *                           versa
   */
  public ActuatorTypeSpringDataRepository(IActuatorTypeSpringDataRepository repository,
      IDataModelAssembler<ActuatorTypeDataModel, ActuatorType> dataModelAssembler) {
    Validator.validateNotNull(dataModelAssembler, "Data model assembler");
    Validator.validateNotNull(repository, "Repository");
    this.dataModelAssembler = dataModelAssembler;
    this.repository = repository;
  }

  /**
   * Saves the specified actuator type entity to the database.
   *
   * @param entity the actuator type entity to save
   * @return the saved actuator type entity
   * @throws IllegalArgumentException if the entity is null
   */
  @Override
  public ActuatorType save(ActuatorType entity) {
    Validator.validateNotNull(entity, "Actuator type");
    ActuatorTypeDataModel actuatorTypeDataModel = new ActuatorTypeDataModel(entity);
    repository.save(actuatorTypeDataModel);
    return entity;
  }

  /**
   * Finds all actuator type entities in the database.
   *
   * @return a list of all actuator type entities
   */
  @Override
  public List<ActuatorType> findAll() {
    List<ActuatorTypeDataModel> actuatorTypeDataModels = repository.findAll();
    return dataModelAssembler.toDomain(actuatorTypeDataModels);
  }

  /**
   * Method to find a domain entity by its unique identifier.
   *
   * @param objectID is the unique identifier of the domain entity.
   * @return the domain entity.
   */
  @Override
  public Optional<ActuatorType> ofIdentity(ActuatorTypeID objectID) {
    Optional<ActuatorTypeDataModel> actuatorTypeDataModel = repository.findById(objectID.getID());

    if (actuatorTypeDataModel.isPresent()) {
      ActuatorType domain = dataModelAssembler.toDomain(actuatorTypeDataModel.get());
      return Optional.of(domain);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Checks if an actuator type with the specified identity exists in the database.
   *
   * @param objectID the ID of the actuator type to check
   * @return true if the actuator type exists, false otherwise
   */
  @Override
  public boolean containsOfIdentity(ActuatorTypeID objectID) {
    return this.repository.existsById(objectID.getID());
  }
}
