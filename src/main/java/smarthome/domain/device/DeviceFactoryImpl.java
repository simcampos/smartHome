/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.device;

import org.springframework.stereotype.Component;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;

@Component
public class DeviceFactoryImpl implements IDeviceFactory {

  /**
   * Creates a new {@link Device} instance using the provided room ID, device name, and device
   * state.
   *
   * @return a newly created Device instance without ID
   */
  @Override
  public Device createDevice(RoomID roomID, DeviceName deviceName,
      DeviceTypeID deviceTypeID) {
    return new Device(roomID, deviceName, deviceTypeID);
  }

  /**
   * Creates and returns a new {@link Device} instance with the provided device ID, room ID, device
   * name, device state, and device type ID.
   *
   * @return a newly created Device instance with ID
   */

  @Override
  public Device createDevice(DeviceID deviceID, RoomID roomID, DeviceName deviceName,
      DeviceStatus deviceStatus, DeviceTypeID deviceTypeID) {
    return new Device(deviceID, roomID, deviceName, deviceStatus, deviceTypeID);
  }
}
