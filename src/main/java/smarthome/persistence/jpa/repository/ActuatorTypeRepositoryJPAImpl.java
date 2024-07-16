/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.repository.IActuatorTypeRepository;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.ActuatorTypeDataModel;


public class ActuatorTypeRepositoryJPAImpl implements IActuatorTypeRepository {

  private final EntityManagerFactory factory;
  private final IDataModelAssembler<ActuatorTypeDataModel, ActuatorType> dataModelAssembler;

  /**
   * Constructs a new repository instance with the specified entity manager factory and data model
   * converter.
   *
   * @param dataModelAssembler the converter to transform data models to domain models and vice
   *                           versa
   */
  public ActuatorTypeRepositoryJPAImpl(
      IDataModelAssembler<ActuatorTypeDataModel, ActuatorType> dataModelAssembler,
      EntityManagerFactory factory) {
    validateDataModelAssembler(dataModelAssembler);
    this.factory = factory;
    this.dataModelAssembler = dataModelAssembler;
  }

  /**
   * Validates the data model assembler.
   *
   * @param dataModelAssembler the data model assembler to validate
   * @throws IllegalArgumentException if the data model assembler is null
   */
  private void validateDataModelAssembler(
      IDataModelAssembler<ActuatorTypeDataModel, ActuatorType> dataModelAssembler) {
    if (dataModelAssembler == null) {
      throw new IllegalArgumentException("The data model assembler must not be null.");
    }
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
    if (entity == null) {
      throw new IllegalArgumentException("The provided entity must not be null.");
    }
    ActuatorTypeDataModel actuatorTypeDataModel = new ActuatorTypeDataModel(entity);
    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.persist(actuatorTypeDataModel);
      tx.commit();
    } catch (RuntimeException e) {
      if (tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      em.close();
    }
    return entity;
  }

  /**
   * Retrieves all actuator type entities from the database.
   *
   * @return a list of actuator type entities
   */
  @Override
  public List<ActuatorType> findAll() {
    EntityManager em = getEntityManager();
    try {
      Query query = em.createQuery("SELECT e FROM ActuatorTypeDataModel e");
      List<ActuatorTypeDataModel> listDataModel = query.getResultList();
      return dataModelAssembler.toDomain(listDataModel);
    } finally {
      em.close();
    }
  }

  /**
   * Finds an actuator type entity by its identity.
   *
   * @param objectID the ID of the actuator type
   * @return an optional containing the found actuator type, or an empty optional if no actuator
   * type is found
   */
  @Override
  public Optional<ActuatorType> ofIdentity(ActuatorTypeID objectID) {
    EntityManager em = getEntityManager();
    try {
      ActuatorTypeDataModel actuatorTypeDataModel = em.find(ActuatorTypeDataModel.class, objectID);
      if (actuatorTypeDataModel == null) {
        return Optional.empty();
      }
      ActuatorType actuatorType = dataModelAssembler.toDomain(actuatorTypeDataModel);
      return Optional.of(actuatorType);
    } finally {
      em.close();
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
    return ofIdentity(objectID).isPresent();
  }

  /**
   * Returns a new instance of EntityManager.
   *
   * @return a new EntityManager instance
   */
  private EntityManager getEntityManager() {
    return factory.createEntityManager();
  }
}
