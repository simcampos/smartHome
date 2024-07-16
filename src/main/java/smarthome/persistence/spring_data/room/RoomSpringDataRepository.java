/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.spring_data.room;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import smarthome.domain.repository.IRoomRepository;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.RoomID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.RoomDataModel;
import smarthome.utils.Validator;

@Repository
public class RoomSpringDataRepository implements IRoomRepository {

  IRoomSpringDataRepository repository;
  IDataModelAssembler<RoomDataModel, Room> assembler;

  /**
   * Constructor of the RoomSpringDataRepository.
   *
   * @param repository is the room spring repository.
   * @param assembler  is the room data model assembler.
   */
  public RoomSpringDataRepository(IRoomSpringDataRepository repository,
      IDataModelAssembler<RoomDataModel, Room> assembler) {
    Validator.validateNotNull(repository, "Room repository");
    this.repository = repository;
    Validator.validateNotNull(assembler, "Room data model assembler");
    this.assembler = assembler;
  }

  /**
   * Method to save a domain entity.
   *
   * @param entity is the domain entity to be saved.
   * @return the saved domain entity.
   */
  @Override
  public Room save(Room entity) {
    Validator.validateNotNull(entity, "Room");

    RoomDataModel dataModel = new RoomDataModel(entity);

    repository.save(dataModel);
    return entity;
  }

  /**
   * Method to find all domain entities.
   *
   * @return a list with all domain entities.
   */
  @Override
  public List<Room> findAll() {
    List<RoomDataModel> listRoomDataModelSaved = repository.findAll();
    List<Room> listDomain = assembler.toDomain(listRoomDataModelSaved);
    return listDomain;
  }

  /**
   * Method to find a domain entity by its unique identifier.
   *
   * @param objectID is the unique identifier of the domain entity.
   * @return the domain entity.
   */
  @Override
  public Optional<Room> ofIdentity(RoomID objectID) {
    Optional<RoomDataModel> dataModelSaved = repository.findById(objectID.getID());

    if (dataModelSaved.isPresent()) {
      Room domain = assembler.toDomain(dataModelSaved.get());
      return Optional.of(domain);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Method to check if a domain entity exists by its unique identifier.
   *
   * @param objectID is the unique identifier of the domain entity.
   * @return true if the domain entity exists, false otherwise.
   */
  @Override
  public boolean containsOfIdentity(RoomID objectID) {
    return repository.existsById(objectID.getID());
  }

  /**
   * Method to update a domain entity.
   *
   * @param room is the room to be updated.
   * @return the updated room.
   */
  @Override
  public Room update(Room room) {
    Optional<RoomDataModel> optionalRoomDataModel = repository.findById(room.getID().getID());

    if (optionalRoomDataModel.isEmpty()) {
      return null;
    }

    RoomDataModel roomDataModel = optionalRoomDataModel.get();
    boolean isUpdated = roomDataModel.updateFromDomain(room);

    if (!isUpdated) {
      return null;
    }

    RoomDataModel savedRoomDataModel = repository.save(roomDataModel);
    return assembler.toDomain(savedRoomDataModel);
  }
}
