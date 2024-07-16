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
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.repository.IActuatorModelRepository;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.ActuatorModelDataModel;
public class ActuatorModelRepositoryJPAImpl implements IActuatorModelRepository {

  private final EntityManagerFactory factory;
  private final IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> dataModelAssembler;

  /**
   * RepositoryActuatorModelJPAImpl constructor
   *
   * @param dataModelAssembler IDataModelAssembler<ActuatorModelDataModel, ActuatorModel>
   */
  public ActuatorModelRepositoryJPAImpl(
      IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> dataModelAssembler,
      EntityManagerFactory factory) {
    validateDataModelConverter(dataModelAssembler);
    this.factory = factory;
    this.dataModelAssembler = dataModelAssembler;
  }

  /**
   * Method to validate actuator model data model converter
   *
   * @param entity IDataModelAssembler<ActuatorModelDataModel, ActuatorModel>
   */
  private void validateDataModelConverter(
      IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Data model converter cannot be null");
    }
  }

  /**
   * Method to get entity manager
   *
   * @return EntityManager
   */
  private EntityManager getEntityManager() {
    EntityManager manager = factory.createEntityManager();
    return manager;
  }

  /**
   * Method to save actuator model
   *
   * @param actuatorModel is the domain entity to be saved.
   * @return ActuatorModel
   */
  @Override
  public ActuatorModel save(ActuatorModel actuatorModel) {
    if (actuatorModel == null) {
      throw new IllegalArgumentException("The provided entity must not be null.");
    }
    ActuatorModelDataModel actuatorModelDataModel = new ActuatorModelDataModel(actuatorModel);
    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.persist(actuatorModelDataModel);
      tx.commit();
    } catch (RuntimeException e) {
      if (tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      em.close();
    }
    return actuatorModel;
  }

  /**
   * Method to find all actuator models
   *
   * @return List<ActuatorModel>
   */
  @Override
  public List<ActuatorModel> findAll() {
    EntityManager entityManager = getEntityManager();
    try {
      Query query =
          entityManager.createQuery(
              "SELECT ACTUATOR_MODEL FROM ActuatorModelDataModel ACTUATOR_MODEL");
      List<ActuatorModelDataModel> listDataModel = query.getResultList();
      List<ActuatorModel> listDomain = dataModelAssembler.toDomain(listDataModel);
      return listDomain;
    } finally {
      entityManager.close();
    }
  }

  /**
   * Method to find actuator model by identity
   *
   * @param actuatorModelID ModelPath
   * @return Optional<ActuatorModel>
   */
  @Override
  public Optional<ActuatorModel> ofIdentity(ModelPath actuatorModelID) {
    EntityManager entityManager = getEntityManager();
    try {
      ActuatorModelDataModel actuatorModelDataModel =
          entityManager.find(ActuatorModelDataModel.class, actuatorModelID);
      if (actuatorModelDataModel == null) {
        return Optional.empty();
      } else {
        ActuatorModel actuatorModel = dataModelAssembler.toDomain(actuatorModelDataModel);
        return Optional.of(actuatorModel);
      }
    } finally {
      entityManager.close();
    }
  }

  /**
   * Method to check if actuator model contains identity
   *
   * @param actuatorModelID ModelPath
   * @return boolean
   */
  @Override
  public boolean containsOfIdentity(ModelPath actuatorModelID) {
    Optional<ActuatorModel> actuatorModel = ofIdentity(actuatorModelID);
    return actuatorModel.isPresent();
  }

  /**
   * Method to find actuator model by actuator type id
   *
   * @param actuatorTypeID ActuatorTypeID
   * @return List<ActuatorModel>
   */
  @Override
  public List<ActuatorModel> findBy_actuatorTypeID(ActuatorTypeID actuatorTypeID) {
    EntityManager entityManager = getEntityManager();
    try {
      Query query =
          entityManager.createQuery(
              "SELECT ACTUATOR_MODEL FROM ActuatorModelDataModel ACTUATOR_MODEL WHERE ACTUATOR_MODEL.actuatorTypeID = :actuatorTypeID");
      query.setParameter("actuatorTypeID", actuatorTypeID.getID());
      List<ActuatorModelDataModel> listDataModel = query.getResultList();
      return dataModelAssembler.toDomain(listDataModel);
    } finally {
      entityManager.close();
    }
  }
}
