/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.spring_data.actuator;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.repository.IActuatorRepository;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.DeviceID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.ActuatorDataModel;
import smarthome.utils.Validator;
import smarthome.utils.visitor_pattern.IActuatorVisitorForDataModel;

@Repository
public class ActuatorSpringDataRepository implements IActuatorRepository {

  private final IActuatorSpringDataRepository repository;
  private final IDataModelAssembler<ActuatorDataModel, IActuator> assembler;
  private final IActuatorVisitorForDataModel actuatorVisitorForDataModel;

  /**
   * Constructs a new repository instance with the specified entity manager factory and data model
   * assembler.
   *
   * @param repository         the repository to use
   * @param dataModelAssembler the converter to transform data models to domain models and vice
   *                           versa
   */
  public ActuatorSpringDataRepository(IActuatorSpringDataRepository repository,
      IDataModelAssembler<ActuatorDataModel, IActuator> dataModelAssembler,
      IActuatorVisitorForDataModel actuatorVisitorForDataModel) {
    Validator.validateNotNull(dataModelAssembler, "Data model assembler");
    assembler = dataModelAssembler;
    Validator.validateNotNull(repository, "Repository");
    this.repository = repository;
    Validator.validateNotNull(actuatorVisitorForDataModel, "Actuator visitor for data model");
    this.actuatorVisitorForDataModel = actuatorVisitorForDataModel;
  }

  /**
   * Saves the specified actuator entity to the database.
   *
   * @param actuator the actuator entity to save
   * @return the saved actuator entity
   * @throws IllegalArgumentException if the entity is null
   */
  @Override
  public IActuator save(IActuator actuator) {
    Validator.validateNotNull(actuator, "Actuator");
    actuator.accept(actuatorVisitorForDataModel);
    ActuatorDataModel actuatorDataModel = actuatorVisitorForDataModel.getActuatorDataModel();
    repository.save(actuatorDataModel);
    return actuator;
  }

  /**
   * Finds all actuator entities in the database.
   *
   * @return a list of all actuator entities
   */
  @Override
  public List<IActuator> findAll() {
    List<ActuatorDataModel> actuatorDataModels = this.repository.findAll();
    List<IActuator> actuators = assembler.toDomain(actuatorDataModels);
    return actuators;
  }

  /**
   * Finds the actuator entity with the specified identity.
   *
   * @param objectID the identity of the actuator entity to find
   * @return an optional containing the actuator entity if found, empty otherwise
   * @throws IllegalArgumentException if the identity is null
   */
  @Override
  public Optional<IActuator> ofIdentity(ActuatorID objectID) {
    Optional<ActuatorDataModel> actuatorDataModel = repository.findById(objectID.getID());
    if (actuatorDataModel.isPresent()) {
      ActuatorDataModel actualActuatorDataModel = actuatorDataModel.get();
      IActuator actuator = assembler.toDomain(actualActuatorDataModel);
      return Optional.of(actuator);
    } else {
      return Optional.empty();
    }

  }

  /**
   * Checks if the actuator entity with the specified identity exists.
   *
   * @param objectID the identity of the actuator entity to check
   * @return true if the actuator entity exists, false otherwise
   */
  @Override
  public boolean containsOfIdentity(ActuatorID objectID) {
    return this.repository.existsById(objectID.getID());
  }

  /**
   * Finds all actuator entities with the specified device ID.
   * @param deviceID is the unique identifier of the device.
   * @return a list of all actuator entities with the specified device ID.
   */
  @Override
  public List<IActuator> ofDeviceID(DeviceID deviceID) {
    List<ActuatorDataModel> listActuatorDataModel = repository.findByDeviceID(deviceID.getID());

    return this.assembler.toDomain(listActuatorDataModel);

  }
}
