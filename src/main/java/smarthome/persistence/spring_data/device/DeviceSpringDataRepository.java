/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.spring_data.device;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import smarthome.domain.device.Device;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.DeviceDataModel;
import smarthome.utils.Validator;

/**
 * Implements the device repository using Spring Data JPA. This class provides a concrete
 * implementation of the {@link IDeviceRepository} for device entity management.
 */
@Repository
public class DeviceSpringDataRepository implements IDeviceRepository {

  private final IDeviceSpringDataRepository repository;
  private final IDataModelAssembler<DeviceDataModel, Device> assembler;

  /**
   * Constructs a new DeviceSpringDataRepository with necessary dependencies.
   *
   * @param repository the Spring Data repository handling Device data models.
   * @param assembler the assembler to convert between Device domain objects and data models.
   */
  public DeviceSpringDataRepository(
      IDeviceSpringDataRepository repository,
      IDataModelAssembler<DeviceDataModel, Device> assembler) {
    Validator.validateNotNull(repository, "Device repository");
    this.repository = repository;
    Validator.validateNotNull(assembler, "Device data model assembler");
    this.assembler = assembler;
  }

  /**
   * Finds all devices associated with a specific room.
   *
   * @param roomId the unique identifier of the room.
   * @return a list of Device domain objects.
   */
  @Override
  public List<Device> findByRoomID(RoomID roomId) {
    List<DeviceDataModel> deviceDataModels = this.repository.findByRoomID(roomId.toString());
    return assembler.toDomain(deviceDataModels);
  }

  /**
   * Finds all devices associated with a specific device type.
   *
   * @param deviceTypeID the unique identifier of the device type.
   * @return a list of Device domain objects.
   */

  @Override
  public List<Device> findByDeviceTypeID(DeviceTypeID deviceTypeID) {
    List<DeviceDataModel> deviceDataModels = repository.findByDeviceTypeID(deviceTypeID.toString());
    return assembler.toDomain(deviceDataModels);
  }

  /**
   * Saves a new device entity to the database.
   *
   * @param entity the Device to save.
   * @return the saved Device domain object.
   */

  @Override
  public Device save(Device entity) {
    Validator.validateNotNull(entity, "Device");
    DeviceDataModel dataModel = new DeviceDataModel(entity);
    repository.save(dataModel);
    return entity;
  }

  /**
   * Updates an existing device entity in the database.
   *
   * @param entity the Device domain object to update.
   * @return the updated Device domain object, or null if update was not successful.
   */
  @Override
  public Device update(Device entity) {
    Optional<DeviceDataModel> optionalDeviceDataModel = repository.findById(entity.getID().getID());

    if (optionalDeviceDataModel.isEmpty()) {
      return null;
    }

    DeviceDataModel deviceDataModel = optionalDeviceDataModel.get();
    deviceDataModel.updateFromDomain(entity);

    DeviceDataModel savedDeviceDataModel = repository.save(deviceDataModel);
    return assembler.toDomain(savedDeviceDataModel);
  }

  /**
   * Retrieves all devices from the database.
   *
   * @return a list of Device domain objects.
   */
  @Override
  public List<Device> findAll() {
    List<DeviceDataModel> listDeviceDataModelSaved = repository.findAll();
    return assembler.toDomain(listDeviceDataModelSaved);
  }

  /**
   * Retrieves a device by its unique identifier.
   *
   * @param objectID the unique identifier of the device.
   * @return an Optional containing the found Device or empty if no device is found.
   */
  @Override
  public Optional<Device> ofIdentity(DeviceID objectID) {
    Optional<DeviceDataModel> dataModelSaved = repository.findById(objectID.getID());
    if (dataModelSaved.isPresent()) {
      Device domain = assembler.toDomain(dataModelSaved.get());
      return Optional.of(domain);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Checks whether a device with a specific identifier exists in the database.
   *
   * @param objectID the unique identifier of the device.
   * @return true if the device exists, false otherwise.
   */
  @Override
  public boolean containsOfIdentity(DeviceID objectID) {
    return repository.existsById(objectID.getID());
  }
}
