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
import smarthome.domain.device.Device;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.DeviceDataModel;

/**
 * A JPA repository implementation for Device entities, using the Jakarta Persistence API. This
 * repository is responsible for performing database operations on Device entities using a JPA
 * EntityManager.
 */

public class DeviceRepositoryJPAImpl implements IDeviceRepository {

  private final EntityManagerFactory factory;
  private final IDataModelAssembler<DeviceDataModel, Device> dataModelAssembler;

  /**
   * Constructs a new RepositoryDeviceJPAImpl.
   *
   * @param dataModelConverter A converter to transform DeviceDataModel objects to Device domain
   *                           objects.
   */

  public DeviceRepositoryJPAImpl(IDataModelAssembler<DeviceDataModel, Device> dataModelConverter,
      EntityManagerFactory factory) {
    validateDataModelAssembler(dataModelConverter);
    this.factory = factory;
    dataModelAssembler = dataModelConverter;
  }

  /**
   * Validates the data model assembler.
   */
  private void validateDataModelAssembler(
      IDataModelAssembler<DeviceDataModel, Device> dataModelConverter) {
    if (dataModelConverter == null) {
      throw new IllegalArgumentException("The data model converter must not be null.");
    }
  }

  /**
   * Retrieves an EntityManager instance from the EntityManagerFactory.
   *
   * @return EntityManager to be used for database operations.
   */
  private EntityManager getEntityManager() {
    return factory.createEntityManager();
  }

  /**
   * Saves a Device entity into the database.
   *
   * @param device The Device entity to be saved.
   * @return The saved Device entity.
   * @throws IllegalArgumentException if the device parameter is null.
   */
  @Override
  public Device save(Device device) {
    if (device == null) {
      throw new IllegalArgumentException("The provided entity must not be null.");
    }
    DeviceDataModel deviceDataModel = new DeviceDataModel(device);
    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();

    try {
      tx.begin();
      em.persist(deviceDataModel);
      tx.commit();
    } catch (RuntimeException e) {
      if (tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      em.close();
    }
    return device;
  }

  /**
   * Retrieves all Device entities from the database.
   *
   * @return A list of Device domain objects.
   */

  @Override
  public List<Device> findAll() {
    EntityManager em = getEntityManager();
    try {
      Query query = em.createQuery("SELECT e FROM DeviceDataModel e");
      List<DeviceDataModel> listDataModel = query.getResultList();
      return dataModelAssembler.toDomain(listDataModel);
    } finally {
      em.close();
    }
  }

  /**
   * Retrieves a Device entity from the database by its unique identifier.
   *
   * @param deviceID is the unique identifier of the domain entity.
   * @return An Optional containing the found Device, or an empty Optional if no device is found.
   */
  @Override
  public Optional<Device> ofIdentity(DeviceID deviceID) {
    EntityManager em = getEntityManager();
    try {
      DeviceDataModel deviceDataModel = em.find(DeviceDataModel.class, deviceID.getID());
      if (deviceDataModel == null) {
        return Optional.empty();
      } else {
        Device device = dataModelAssembler.toDomain(deviceDataModel);
        return Optional.of(device);
      }
    } finally {
      em.close();

    }

  }

  /**
   * Checks if a Device with a specific identity exists in the database.
   *
   * @param objectID The unique identifier of the Device.
   * @return true if a Device with the specified identifier exists, false otherwise.
   */

  @Override
  public boolean containsOfIdentity(DeviceID objectID) {
    return ofIdentity(objectID).isPresent();
  }

  /**
   * Retrieves devices associated with a specific room.
   *
   * @param roomId The identifier of the room.
   * @return A list of Device domain objects located in the specified room.
   */

  @Override
  public List<Device> findByRoomID(RoomID roomId) {
    EntityManager em = getEntityManager();
    try {
      Query query = em.createQuery("SELECT e FROM DeviceDataModel e WHERE e.roomID = :roomId");
      query.setParameter("roomId", roomId.getID());
      List<DeviceDataModel> listDataModel = query.getResultList();
      return dataModelAssembler.toDomain(listDataModel);
    } finally {
      em.close();
    }

  }

  /**
   * Updates a Device entity in the database.
   *
   * @param device The Device entity to be updated.
   * @return The updated Device entity.
   * @throws IllegalArgumentException if the device parameter is null.
   */
  @Override
  public Device update(Device device) {
    DeviceDataModel deviceDataModel = getEntityManager().find(DeviceDataModel.class,
        device.getID().getID());

    if (deviceDataModel != null) {
      boolean isUpdated = deviceDataModel.updateFromDomain(device);

      if (isUpdated) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
          tx.begin();
          em.merge(deviceDataModel);
          tx.commit();
        } catch (RuntimeException e) {
          if (tx.isActive()) {
            tx.rollback();
          }
          throw e;
        } finally {
          em.close();
        }
        return device;
      } else {
        return null;
      }
    }
    return null;
  }


  /**
   * Retrieves devices associated with a specific device type.
   *
   * @param deviceTypeID The identifier of the device type.
   * @return A list of Device domain objects with the specified device type.
   */
  @Override
  public List<Device> findByDeviceTypeID(DeviceTypeID deviceTypeID) {
    EntityManager em = getEntityManager();
    try {
      Query query = em.createQuery("SELECT e FROM DeviceDataModel e WHERE e.deviceTypeID = :deviceTypeID");
      query.setParameter("deviceTypeID", deviceTypeID.getID());
      List<DeviceDataModel> listDataModel = query.getResultList();
      return dataModelAssembler.toDomain(listDataModel);
    } finally {
      em.close();
    }
  }
}
