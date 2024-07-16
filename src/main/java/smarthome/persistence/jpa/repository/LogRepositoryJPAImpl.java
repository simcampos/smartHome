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
import smarthome.domain.log.Log;
import smarthome.domain.repository.ILogRepository;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.LogID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.LogDataModel;
import smarthome.utils.Validator;

public class LogRepositoryJPAImpl implements ILogRepository {

  private final EntityManagerFactory factory;
  private final IDataModelAssembler<LogDataModel, Log> dataModelAssembler;

  /**
   * LogRepositoryJPAImpl constructor
   *
   * @param dataModelAssembler IDataModelAssembler object
   */
  public LogRepositoryJPAImpl(IDataModelAssembler<LogDataModel, Log> dataModelAssembler,
      EntityManagerFactory factory) {
    Validator.validateNotNull(dataModelAssembler, "Data model assembler");

    this.dataModelAssembler = dataModelAssembler;
    this.factory = factory;
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
   * Method to save log
   *
   * @param log is the domain entity to be saved.
   * @return Log
   */
  @Override
  public Log save(Log log) {
    if (log == null) {
      throw new IllegalArgumentException("Log cannot be null");
    }

    LogDataModel logDataModel = new LogDataModel(log);

    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.persist(logDataModel);
      tx.commit();
    } catch (RuntimeException e) {
      if (tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      em.close();
    }
    return log;
  }

  /**
   * Method to find all logs
   *
   * @return List<Log>
   */
  @Override
  public List<Log> findAll() {
    EntityManager em = getEntityManager();
    try {
      Query query = em.createQuery("SELECT e FROM LogDataModel e");

      List<LogDataModel> logDataModels = query.getResultList();
      List<Log> logs = dataModelAssembler.toDomain(logDataModels);

      return logs;
    } finally {
      em.close();
    }
  }

  /**
   * Method to find log by ID
   *
   * @param logID LogID object
   * @return Optional<Log>
   */
  @Override
  public Optional<Log> ofIdentity(LogID logID) {
    EntityManager em = getEntityManager();
    try {
      LogDataModel logDataModel = em.find(LogDataModel.class, logID);
      if (logDataModel == null) {
        return Optional.empty();
      }
      Log log = dataModelAssembler.toDomain(logDataModel);
      return Optional.of(log);
    } finally {
      em.close();
    }
  }

  /**
   * Method to check if log exists by identity
   *
   * @param logID LogID object
   * @return boolean
   */
  @Override
  public boolean containsOfIdentity(LogID logID) {
    Optional<Log> logDataModel = ofIdentity(logID);
    return logDataModel.isPresent();
  }

  /**
   * Method to find logs by device ID and time period
   *
   * @param deviceID DeviceID object
   * @param period   DatePeriod object
   * @return List<Log>
   */
  @Override
  public List<Log> findByDeviceIDAndDatePeriodBetween(DeviceID deviceID, DatePeriod period) {
    EntityManager em = getEntityManager();
    try {
      Query query =
          em.createQuery(
              "SELECT e FROM LogDataModel e WHERE e.deviceID = :deviceID AND e.timestamp BETWEEN :start AND :end");
      query.setParameter("deviceID", deviceID.getID());
      query.setParameter("start", period.getStartDate());
      query.setParameter("end", period.getEndDate());

      List<LogDataModel> logDataModels = query.getResultList();
      List<Log> logs = dataModelAssembler.toDomain(logDataModels);

      return logs;
    } finally {
      em.close();
    }
  }

  /**
   * Method to find logs by device ID, sensor type and time period
   *
   * @param deviceID     DeviceID object
   * @param sensorTypeID SensorTypeID object
   * @param period       DatePeriod object
   * @return List<Log>
   */
  @Override
  public List<Log> findByDeviceIDAndSensorTypeAndDatePeriodBetween(DeviceID deviceID,
      SensorTypeID sensorTypeID, DatePeriod period) {
    EntityManager em = getEntityManager();
    try {
      Query query =
          em.createQuery(
              "SELECT e FROM LogDataModel e WHERE e.deviceID = :deviceID AND e.description = :sensorTypeID AND e.timestamp BETWEEN :start AND :end");
      query.setParameter("deviceID", deviceID.getID());
      query.setParameter("sensorTypeID", sensorTypeID.getID());
      query.setParameter("start", period.getStartDate());
      query.setParameter("end", period.getEndDate());

      List<LogDataModel> logDataModels = query.getResultList();
      List<Log> logs = dataModelAssembler.toDomain(logDataModels);

      return logs;
    } finally {
      em.close();
    }
  }

  @Override
  public List<Log> findByDeviceIDAndSensorTypeID(DeviceID deviceID, SensorTypeID sensorTypeID) {
    EntityManager em = getEntityManager();
    try {
      Query query =
          em.createQuery(
              "SELECT e FROM LogDataModel e WHERE e.deviceID = :deviceID AND e.description = :sensorTypeID");
      query.setParameter("deviceID", deviceID.getID());
      query.setParameter("sensorTypeID", sensorTypeID.getID());

      List<LogDataModel> logDataModels = query.getResultList();
      List<Log> logs = dataModelAssembler.toDomain(logDataModels);

      return logs;
    } finally {
      em.close();
    }
  }
}
