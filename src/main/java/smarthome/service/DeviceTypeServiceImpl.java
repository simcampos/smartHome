/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import smarthome.ddd.IRepository;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.device_type.IDeviceTypeFactory;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.utils.Validator;

@Service
public class DeviceTypeServiceImpl implements IDeviceTypeService {

  private final IRepository<DeviceTypeID, DeviceType> deviceTypeRepository;
  private final IDeviceTypeFactory deviceTypeFactory;

  /**
   * Constructor for the DeviceTypeService class.
   *
   * @param deviceTypeRepository The repository for the device type.
   * @param deviceTypeFactory    The factory for the device type.
   */
  public DeviceTypeServiceImpl(IRepository<DeviceTypeID, DeviceType> deviceTypeRepository,
      IDeviceTypeFactory deviceTypeFactory) {
    Validator.validateNotNull(deviceTypeRepository, "Device type repository");
    this.deviceTypeFactory = deviceTypeFactory;
    Validator.validateNotNull(deviceTypeFactory, "Device type factory");
    this.deviceTypeRepository = deviceTypeRepository;
  }

  /**
   * Adds a device type.
   *
   * @param deviceTypeName The name of the device type.
   * @return The device type.
   */
  @Override
  public DeviceType addDeviceType(TypeDescription deviceTypeName) {
    Validator.validateNotNull(deviceTypeName, "Device type");
    DeviceType deviceType = deviceTypeFactory.createDeviceType(deviceTypeName);
    deviceTypeRepository.save(deviceType);
    return deviceType;

  }

  /**
   * Finds all device types.
   *
   * @return A list of all device types.
   */
  @Override
  public List<DeviceType> getAllDeviceTypes() {
    return deviceTypeRepository.findAll();
  }

  /**
   * Finds a device type by its ID.
   *
   * @param deviceTypeID The ID of the device type.
   * @return The device type.
   */
  @Override
  public Optional<DeviceType> getDeviceTypeByID(DeviceTypeID deviceTypeID) {
    if (deviceTypeID == null) {
      throw new IllegalArgumentException("Please enter a valid device type ID.");
    }
    return deviceTypeRepository.ofIdentity(deviceTypeID);
  }
}
