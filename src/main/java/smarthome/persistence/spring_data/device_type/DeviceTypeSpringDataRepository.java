/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.spring_data.device_type;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.repository.IDeviceTypeRepository;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.DeviceTypeDataModel;
import smarthome.utils.Validator;

@Repository
public class DeviceTypeSpringDataRepository implements IDeviceTypeRepository {

  IDeviceTypeSpringDataRepository repository;
  IDataModelAssembler<DeviceTypeDataModel, DeviceType> assembler;

  public DeviceTypeSpringDataRepository(IDeviceTypeSpringDataRepository repository,
      IDataModelAssembler<DeviceTypeDataModel, DeviceType> assembler) {
    Validator.validateNotNull(repository, "Repository");
    this.repository = repository;
    Validator.validateNotNull(assembler, "Assembler");
    this.assembler = assembler;
  }

  /**
   * Method to save a domain entity.
   *
   * @param entity is the domain entity to be saved.
   * @return the saved domain entity.
   */
  @Override
  public DeviceType save(DeviceType entity) {
    Validator.validateNotNull(entity, "DeviceType");

    DeviceTypeDataModel dataModel = new DeviceTypeDataModel(entity);
    repository.save(dataModel);
    return entity;
  }

  /**
   * Method to find all domain entities.
   *
   * @return a list of all domain entities.
   */
  @Override
  public List<DeviceType> findAll() {
    List<DeviceTypeDataModel> listDeviceTypeDataModelSaved = repository.findAll();
    List<DeviceType> listDomain = assembler.toDomain(listDeviceTypeDataModelSaved);
    return listDomain;
  }

  /**
   * Method to find a domain entity by its unique identifier.
   *
   * @param objectID is the unique identifier of the domain entity.
   * @return the domain entity.
   */
  @Override
  public Optional<DeviceType> ofIdentity(DeviceTypeID objectID) {
    Optional<DeviceTypeDataModel> dataModelSaved = repository.findById(objectID.getID());
    if (dataModelSaved.isPresent()) {
      DeviceType domain = assembler.toDomain(dataModelSaved.get());
      return Optional.of(domain);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Method to check if a domain entity exists by its unique identifier.
   *
   * @param objectId is the unique identifier of the domain entity.
   * @return true if the domain entity exists, false otherwise.
   */
  @Override
  public boolean containsOfIdentity(DeviceTypeID objectId) {
    return this.repository.existsById(objectId.getID());
  }
}
