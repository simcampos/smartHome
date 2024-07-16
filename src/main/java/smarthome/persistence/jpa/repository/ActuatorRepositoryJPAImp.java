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
import smarthome.domain.actuator.IActuator;
import smarthome.domain.repository.IActuatorRepository;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.DeviceID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.ActuatorDataModel;
import smarthome.utils.visitor_pattern.IActuatorVisitorForDataModel;


public class ActuatorRepositoryJPAImp implements IActuatorRepository {

  private final EntityManagerFactory factory;
  private final IDataModelAssembler<ActuatorDataModel, IActuator> dataModelAssembler;
  private final IActuatorVisitorForDataModel actuatorVisitorForDataModel;

  /**
   * Constructor for RepositoryActuatorJPAImp
   *
   * @param dataModelAssembler          is the data model assembler
   * @param actuatorVisitorForDataModel is the actuator visitor for data model
   * @param factory
   */
  public ActuatorRepositoryJPAImp(
      IDataModelAssembler<ActuatorDataModel, IActuator> dataModelAssembler,
      IActuatorVisitorForDataModel actuatorVisitorForDataModel, EntityManagerFactory factory) {
    validateDataModelAssembler(dataModelAssembler);
    this.dataModelAssembler = dataModelAssembler;
    this.factory = factory;
    validateActuatorVisitorForDataModel(actuatorVisitorForDataModel);
    this.actuatorVisitorForDataModel = actuatorVisitorForDataModel;
  }

  /**
   * Method to validate the data model assembler
   *
   * @param dataModelAssembler is the data model assembler
   */
  private void validateDataModelAssembler(
      IDataModelAssembler<ActuatorDataModel, IActuator> dataModelAssembler) {
    if (dataModelAssembler == null) {
      throw new IllegalArgumentException("Data model converter cannot be null");
    }
  }

  /**
   * Method to validate the actuator visitor for data model
   *
   * @param actuatorVisitorForDataModel is the actuator visitor for data model
   */
  private void validateActuatorVisitorForDataModel(
      IActuatorVisitorForDataModel actuatorVisitorForDataModel) {
    if (actuatorVisitorForDataModel == null) {
      throw new IllegalArgumentException("Actuator visitor for data model cannot be null");
    }
  }

  /**
   * Saves the specified actuator entity to the database.
   *
   * @param entity is the domain entity to be saved.
   * @return the saved actuator entity
   * @throws IllegalArgumentException if the entity is null
   */
  @Override
  public IActuator save(IActuator entity) {
    if (entity == null) {
      throw new IllegalArgumentException("The provided entity must not be null.");
    }
    entity.accept(actuatorVisitorForDataModel);
    ActuatorDataModel actuatorDataModel = actuatorVisitorForDataModel.getActuatorDataModel();
    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.persist(actuatorDataModel);
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
   * Retrieves all actuator entities from the database.
   *
   * @return a list of actuator entities
   */
  @Override
  public List<IActuator> findAll() {
    EntityManager em = getEntityManager();
    try {
      Query query = em.createQuery("SELECT e FROM ActuatorDataModel e");
      List<ActuatorDataModel> listDataModel = query.getResultList();
      return dataModelAssembler.toDomain(listDataModel);
    } finally {
      em.close();
    }
  }

  /**
   * Finds an actuator entity by its identity.
   *
   * @param actuatorID is the identity of the actuator entity
   * @return an optional containing the actuator entity if found, otherwise an empty optional
   */
  @Override
  public Optional<IActuator> ofIdentity(ActuatorID actuatorID) {
    EntityManager em = getEntityManager();
    try {
      ActuatorDataModel actuatorDataModel = em.find(ActuatorDataModel.class, actuatorID);
      if (actuatorDataModel == null) {
        return Optional.empty();
      }
      IActuator actuator = dataModelAssembler.toDomain(actuatorDataModel);
      return Optional.of(actuator);
    } finally {
      em.close();
    }
  }

  /**
   * Checks if an actuator entity with the specified identity exists in the database.
   *
   * @param actuatorID is the identity of the actuator entity
   * @return true if the actuator entity exists, otherwise false
   */
  @Override
  public boolean containsOfIdentity(ActuatorID actuatorID) {
    return ofIdentity(actuatorID).isPresent();
  }

  /**
   * Returns a new instance of EntityManager.
   *
   * @return a new EntityManager instance
   */
  private EntityManager getEntityManager() {
    return factory.createEntityManager();
  }

  @Override
  public List<IActuator> ofDeviceID(DeviceID deviceID) {
    EntityManager em = getEntityManager();
    try {
      Query query = em.createQuery("SELECT e FROM ActuatorDataModel e WHERE e.deviceID = :deviceID");
      query.setParameter("deviceID", deviceID.getID());
      List<ActuatorDataModel> listDataModel = query.getResultList();
      return dataModelAssembler.toDomain(listDataModel);
    } finally {
      em.close();
    }
  }
}
