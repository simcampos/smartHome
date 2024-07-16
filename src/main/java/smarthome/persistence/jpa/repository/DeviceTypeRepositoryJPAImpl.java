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
import java.util.List;
import java.util.Optional;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.repository.IDeviceTypeRepository;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.DeviceTypeDataModel;


public class DeviceTypeRepositoryJPAImpl implements IDeviceTypeRepository {

  private final IDataModelAssembler<DeviceTypeDataModel, DeviceType> dataModelAssembler;
  private final EntityManagerFactory factory;

  /**
   * Creates an instance of {@link DeviceTypeRepositoryJPAImpl} with the provided data model
   * assembler.
   */
  public DeviceTypeRepositoryJPAImpl(
      IDataModelAssembler<DeviceTypeDataModel, DeviceType> dataModelAssembler,
      EntityManagerFactory factory) {
    validateDataModelAssembler(dataModelAssembler);
    this.dataModelAssembler = dataModelAssembler;
    this.factory = factory;
  }

  /**
   * Validates the data model assembler
   *
   * @param entity the data model assembler
   */
  private void validateDataModelAssembler(
      IDataModelAssembler<DeviceTypeDataModel, DeviceType> entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Data model assembler cannot be null.");
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
   * Saves the device type in the database.
   *
   * @param deviceType is the domain entity to be saved.
   * @return DeviceType
   */
  @Override
  public DeviceType save(DeviceType deviceType) {
    if (deviceType == null) {
      throw new IllegalArgumentException("Device Type cannot be null");
    }

    DeviceTypeDataModel model = new DeviceTypeDataModel(deviceType);

    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.persist(model);
      tx.commit();
    } catch (RuntimeException e) {
      if (tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      em.close();
    }
    return deviceType;
  }

  /**
   * Finds all device types
   *
   * @return a list of device types
   */
  @Override
  public List<DeviceType> findAll() {
    EntityManager em = getEntityManager();
    try {
      List<DeviceTypeDataModel> deviceTypeDataModels =
          em.createQuery("SELECT d FROM DeviceTypeDataModel d", DeviceTypeDataModel.class)
              .getResultList();
      return dataModelAssembler.toDomain(deviceTypeDataModels);
    } finally {
      em.close();
    }
  }

  /**
   * Finds a device type by its ID
   *
   * @param deviceTypeID the ID of the device type
   * @return the device type if found, otherwise Optional.empty()
   */
  @Override
  public Optional<DeviceType> ofIdentity(DeviceTypeID deviceTypeID) {
    EntityManager em = getEntityManager();
    try {
      DeviceTypeDataModel deviceTypeDataModel = em.find(DeviceTypeDataModel.class, deviceTypeID);
      if (deviceTypeDataModel == null) {
        return Optional.empty();
      }
      return Optional.of(dataModelAssembler.toDomain(deviceTypeDataModel));
    } finally {
      em.close();
    }
  }

  /**
   * Verifies if a device type exists by {@link java.math.BigDecimal
   *
   * @param deviceTypeID the ID of the device type
   * @return true if the device type exists, otherwise false
   */
  @Override
  public boolean containsOfIdentity(DeviceTypeID deviceTypeID) {
    Optional<DeviceType> deviceTypeModel = ofIdentity(deviceTypeID);

    return deviceTypeModel.isPresent();
  }
}
