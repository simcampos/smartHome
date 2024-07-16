/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.device_type;

import org.springframework.stereotype.Component;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.TypeDescription;

@Component
public class DeviceTypeFactoryImpl implements IDeviceTypeFactory {

  /**
   * Creates a new DeviceType instance based on the provided type description.
   *
   * @param deviceTypeDescription The description of the device type to be created.
   * @return A new instance of DeviceType initialized with the given description.
   */
  @Override
  public DeviceType createDeviceType(TypeDescription deviceTypeDescription)
      throws IllegalArgumentException {
    return new DeviceType(deviceTypeDescription);
  }

  @Override
  public DeviceType createDeviceType(DeviceTypeID id, TypeDescription description)
      throws IllegalArgumentException {
    return new DeviceType(id, description);
  }
}

