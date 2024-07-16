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
import smarthome.domain.repository.IRoomRepository;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.RoomID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.RoomDataModel;
import smarthome.utils.Validator;

public class RoomRepositoryJPAImpl implements IRoomRepository {

  private final EntityManagerFactory factory;
  private final IDataModelAssembler<RoomDataModel, Room> dataModelConverter;

  /**
   * RepositoryRoomJPAImpl constructor
   *
   * @param dataModelConverter is the data model assembler.
   */
  public RoomRepositoryJPAImpl(IDataModelAssembler<RoomDataModel, Room> dataModelConverter,
      EntityManagerFactory factory) {
    Validator.validateNotNull(dataModelConverter, "Data model assembler");

    this.dataModelConverter = dataModelConverter;
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
   * Method to save room
   *
   * @param room is the domain entity to be saved.
   * @return Room
   */
  @Override
  public Room save(Room room) {
    if (room == null) {
      throw new IllegalArgumentException("Room cannot be null");
    }

    RoomDataModel roomDataModel = new RoomDataModel(room);

    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.persist(roomDataModel);
      tx.commit();
    } catch (RuntimeException e) {
      if (tx.isActive()) {
        tx.rollback();
      }
      throw e;
    } finally {
      em.close();
    }

    return room;
  }

  /**
   * Method to find all rooms
   *
   * @return List<Room>
   */
  @Override
  public List<Room> findAll() {
    EntityManager em = getEntityManager();
    try {
      Query query = em.createQuery(
          "SELECT e FROM RoomDataModel e");

      List<RoomDataModel> listDataModel = query.getResultList();

      List<Room> listDomain = dataModelConverter.toDomain(listDataModel);

      return listDomain;
    } finally {
      em.close();
    }
  }

  /**
   * Method to get room by identity
   *
   * @param objectID RoomID
   * @return Optional<Room>
   */
  @Override
  public Optional<Room> ofIdentity(RoomID objectID) {
    EntityManager em = getEntityManager();

    try {
      RoomDataModel roomDataModel = em.find(RoomDataModel.class, objectID);

      if (roomDataModel == null) {
        return Optional.empty();
      }

      Room room = dataModelConverter.toDomain(roomDataModel);

      return Optional.of(room);
    } finally {
      em.close();
    }
  }

  /**
   * Method to check if room contains identity
   *
   * @param objectID is the unique identifier of the domain entity.
   * @return boolean
   */
  @Override
  public boolean containsOfIdentity(RoomID objectID) {
    Optional<Room> roomDataModel = ofIdentity(objectID);

    return roomDataModel.isPresent();
  }

  /**
   * Method to update room
   *
   * @param room is the room to be updated.
   * @return the updated room.
   */
  @Override
  public Room update(Room room) {
    RoomDataModel roomDataModel = getEntityManager().find(RoomDataModel.class,
        room.getID().getID());

    if (roomDataModel != null) {
      boolean isUpdated = roomDataModel.updateFromDomain(room);

      if (isUpdated) {
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(roomDataModel);
        tx.commit();
        em.close();

        return room;
      } else {
        return null;
      }
    } else {
      return null;
    }

  }
}
