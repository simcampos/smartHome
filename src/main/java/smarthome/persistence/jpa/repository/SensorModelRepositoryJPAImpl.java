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
import smarthome.domain.repository.ISensorModelRepository;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.SensorModelDataModel;

public class SensorModelRepositoryJPAImpl implements ISensorModelRepository {

  private final EntityManagerFactory factory;
  private final IDataModelAssembler<SensorModelDataModel, SensorModel> dataModelConverter;

  /**
   * RepositorySensorModelJPAImpl constructor
   *
   * @param dataModelConverter IDataModelConverter<SensorModelDataModel, SensorModel>
   */
  public SensorModelRepositoryJPAImpl(
      IDataModelAssembler<SensorModelDataModel, SensorModel> dataModelConverter,
      EntityManagerFactory factory) {
    validateDataModelConverter(dataModelConverter);
    this.dataModelConverter = dataModelConverter;
    this.factory = factory;
  }


  /**
   * Method to validate data model converter
   *
   * @param dataModelConverter IDataModelConverter<SensorModelDataModel, SensorModel>
   */

  private void validateDataModelConverter(
      IDataModelAssembler<SensorModelDataModel, SensorModel> dataModelConverter) {
    if (dataModelConverter == null) {
      throw new IllegalArgumentException("Data Model Converter cannot be null.");
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
   * Method to save sensor model
   *
   * @param sensorModel SensorModel
   * @return SensorModel saved
   */

  @Override
  public SensorModel save(SensorModel sensorModel) {
    if (sensorModel == null) {
      throw new IllegalArgumentException("Sensor Model is required.");
    }

    SensorModelDataModel sensorModelDataModel = new SensorModelDataModel(sensorModel);
    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      em.persist(sensorModelDataModel);
      tx.commit();
    } catch (RuntimeException e) {
      if (tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      em.close();
    }
    return sensorModel;
  }


  /**
   * Method to find all sensor models
   *
   * @return List<SensorModel> list of sensor models
   */
  @Override
  public List<SensorModel> findAll() {
    EntityManager entityManager = getEntityManager();
    try {
      Query query = entityManager.createQuery(
          "SELECT e FROM SensorModelDataModel e");
      List<SensorModelDataModel> listDataModel = query.getResultList();
      List<SensorModel> listDomain = dataModelConverter.toDomain(listDataModel);
      return listDomain;
    } finally {
      entityManager.close();

    }
  }


  /**
   * Method to find sensor model by identity
   *
   * @param objectID ModelPath
   * @return Optional<SensorModel>
   */

  @Override
  public Optional<SensorModel> ofIdentity(ModelPath objectID) {
    EntityManager entityManager = getEntityManager();
    try {
      SensorModelDataModel sensorModelDataModel = entityManager.find(SensorModelDataModel.class,
          objectID);
      if (sensorModelDataModel != null) {
        return Optional.empty();
      } else {
        SensorModel sensorModel = dataModelConverter.toDomain(sensorModelDataModel);
        return Optional.of(sensorModel);
      }
    } finally {
      entityManager.close();
    }
  }

  /**
   * Method to check if the sensor model exists
   *
   * @param objectID ModelPath
   * @return boolean true if the sensor model exists
   */
  @Override
  public boolean containsOfIdentity(ModelPath objectID) {

    Optional<SensorModel> sensorModelDataModel = ofIdentity(objectID);

    return sensorModelDataModel.isPresent();

  }

  /**
   * Method to find sensor model by sensor type id
   *
   * @param sensorTypeID SensorTypeID
   * @return List<SensorModel> list of sensor models
   */

  @Override
  public List<SensorModel> findBySensorTypeId(SensorTypeID sensorTypeID) {

    EntityManager entityManager = getEntityManager();
    try {
      Query query = entityManager.createQuery(
          "SELECT e FROM SensorModelDataModel e WHERE e._sensorTypeID = :sensorTypeID");
      query.setParameter("sensorTypeID", sensorTypeID);
      List<SensorModelDataModel> listDataModel = query.getResultList();
      List<SensorModel> listDomain = dataModelConverter.toDomain(listDataModel);
      return listDomain;
    } finally {
      entityManager.close();
    }
  }

}

