/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.spring_data.actuator_model;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.repository.IActuatorModelRepository;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.ActuatorModelDataModel;
import smarthome.utils.Validator;

@Repository
public class RepositoryActuatorModelSpringData implements IActuatorModelRepository {

  IRepositoryActuatorModelSpringData repositoryActuatorModelSpringData;

  IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> dataModelAssembler;

  /**
   * Constructor for RepositoryActuatorModelSpringData
   *
   * @param dataModelAssembler                data model assembler
   * @param repositoryActuatorModelSpringData repository actuator model spring data
   */
  public RepositoryActuatorModelSpringData(
      IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> dataModelAssembler,
      IRepositoryActuatorModelSpringData repositoryActuatorModelSpringData) {
    validateDataModelAssembler(dataModelAssembler);
    this.dataModelAssembler = dataModelAssembler;

    Validator.validateNotNull(repositoryActuatorModelSpringData,
        "Repository actuator model spring data");
    this.repositoryActuatorModelSpringData = repositoryActuatorModelSpringData;
  }

  /**
   * Method to validate data model assembler
   *
   * @param entity data model converter
   */
  private void validateDataModelAssembler(
      IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> entity) {
    Validator.validateNotNull(entity, "Data model converter");
  }

  /**
   * Method to save a domain entity.
   *
   * @param actuatorModel is the domain entity to be saved.
   * @return the saved domain entity.
   */
  @Override
  public ActuatorModel save(ActuatorModel actuatorModel) {
    Validator.validateNotNull(actuatorModel, "Actuator model");

    ActuatorModelDataModel actuatorModelDataModel = new ActuatorModelDataModel(actuatorModel);

    this.repositoryActuatorModelSpringData.save(actuatorModelDataModel);

    return actuatorModel;
  }

  /**
   * Method to find all domain entities.
   *
   * @return list of domain entities
   */
  @Override
  public List<ActuatorModel> findAll() {
    List<ActuatorModelDataModel> actuatorModelDataModels =
        this.repositoryActuatorModelSpringData.findAll();

    return dataModelAssembler.toDomain(actuatorModelDataModels);
  }

  /**
   * Method to find a domain entity by its unique identifier.
   *
   * @param modelID is the unique identifier of the domain entity.
   * @return the domain entity.
   */
  @Override
  public Optional<ActuatorModel> ofIdentity(ModelPath modelID) {
    Optional<ActuatorModelDataModel> actuatorModelDataModel =
        this.repositoryActuatorModelSpringData.findById(modelID.toString());

    if (actuatorModelDataModel.isPresent()) {
      return Optional.of(dataModelAssembler.toDomain(actuatorModelDataModel.get()));
    } else {
      return Optional.empty();
    }
  }

  /**
   * Method to check if a domain entity exists by its unique identifier.
   *
   * @param modelID is the unique identifier of the domain entity.
   * @return true if the domain entity exists, false otherwise.
   */
  @Override
  public boolean containsOfIdentity(ModelPath modelID) {

    return this.repositoryActuatorModelSpringData.existsById(modelID.getID());
  }

  /**
   * Method to find all domain entities by actuator type ID.
   *
   * @param actuatorModelID is the unique identifier of the domain entity.
   * @return list of domain entities
   */
  @Override
  public List<ActuatorModel> findBy_actuatorTypeID(ActuatorTypeID actuatorModelID) {
    List<ActuatorModelDataModel> actuatorModelDataModels =
        this.repositoryActuatorModelSpringData.findByActuatorTypeID(actuatorModelID.getID());

    return dataModelAssembler.toDomain(actuatorModelDataModels);
  }
}
