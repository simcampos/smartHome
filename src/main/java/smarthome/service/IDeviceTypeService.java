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
import smarthome.ddd.IService;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.TypeDescription;

public interface IDeviceTypeService extends IService {

  /**
   * Adds a new device type to the repository.
   *
   * @param deviceTypeName the name of the device type.
   * @return the device type that was added.
   */
  DeviceType addDeviceType(TypeDescription deviceTypeName);

  /**
   * Gets all device types in the repository.
   *
   * @return a list of all device types.
   */
  List<DeviceType> getAllDeviceTypes();

  /**
   * Gets a device type by its ID.
   *
   * @param deviceTypeID the ID of the device type.
   * @return the device type with the provided ID.
   */
  Optional<DeviceType> getDeviceTypeByID(DeviceTypeID deviceTypeID);
}
