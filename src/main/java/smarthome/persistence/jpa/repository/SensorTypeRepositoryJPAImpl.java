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
import smarthome.domain.repository.ISensorTypeRepository;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.SensorTypeDataModel;

public class SensorTypeRepositoryJPAImpl implements ISensorTypeRepository {

  private final IDataModelAssembler<SensorTypeDataModel, SensorType> dataModelConverter;
  private final EntityManagerFactory factory;

  /**
   * Creates an instance of {@link SensorTypeRepositoryJPAImpl} with the provided data model
   * converter.
   *
   * @param dataModelConverter the data model converter
   */
  public SensorTypeRepositoryJPAImpl(
      IDataModelAssembler<SensorTypeDataModel, SensorType> dataModelConverter,
      EntityManagerFactory factory) {
    validateDataModelConverter(dataModelConverter);

    this.dataModelConverter = dataModelConverter;
    this.factory = factory;
  }

  /**
   * Validates the data model converter
   *
   * @param entity the data model converter
   */
  private void validateDataModelConverter(
      IDataModelAssembler<SensorTypeDataModel, SensorType> entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Data model converter cannot be null.");
    }
  }

  /**
   * Get the entity manager
   *
   * @return the entity manager
   */
  private EntityManager getEntityManager() {
    EntityManager manager = this.factory.createEntityManager();

    return manager;
  }

  /**
   * Saves the sensor type in the database.
   *
   * @param sensorType is the domain entity to be saved.
   * @return SensorType
   */
  @Override
  public SensorType save(SensorType sensorType) {
    if (sensorType == null) {
      throw new IllegalArgumentException("Sensor Type cannot be null");
    }

    SensorTypeDataModel sensorTypeDataModel = new SensorTypeDataModel(sensorType);

    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.persist(sensorTypeDataModel);
      tx.commit();
    } catch (RuntimeException e) {
      if (tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      em.close();
    }
    return sensorType;
  }

  /**
   * Finds all sensorTypes.
   *
   * @return a list of sensorType.
   */
  @Override
  public List<SensorType> findAll() {
    EntityManager em = getEntityManager();
    try {
      Query query = em.createQuery("SELECT e FROM SensorTypeDataModel e");

      List<SensorTypeDataModel> listDataModel = query.getResultList();

      List<SensorType> listDomain = dataModelConverter.toDomain(listDataModel);

      return listDomain;
    } finally {
      em.close();
    }

  }

  /**
   * Find a SensorType by its identity.
   *
   * @param sensorTypeID the identity of the SensorType to find.
   * @return the SensorType if found, otherwise Optional.empty().
   */
  @Override
  public Optional<SensorType> ofIdentity(SensorTypeID sensorTypeID) {
    EntityManager em = getEntityManager();
    try {
      SensorTypeDataModel sensorTypeDataModel = em.find(SensorTypeDataModel.class, sensorTypeID);

      if (sensorTypeDataModel == null) {
        return Optional.empty();
      }

      SensorType sensorType = dataModelConverter.toDomain(sensorTypeDataModel);

      return Optional.of(sensorType);
    } finally {
      em.close();
    }
  }

  /**
   * Check if a SensorType exists by its identity.
   *
   * @param sensorTypeID the identity of the SensorType to check.
   * @return true if the SensorType exists, otherwise false.
   */
  @Override
  public boolean containsOfIdentity(SensorTypeID sensorTypeID) {
    Optional<SensorType> sensorDataModel = ofIdentity(sensorTypeID);

    return sensorDataModel.isPresent();
  }
}
