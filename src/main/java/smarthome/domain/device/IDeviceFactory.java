/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.device;

import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;

public interface IDeviceFactory {

  /**
   * Creates and returns a new {@link Device} instance with the provided room ID, device name, and
   * device state.
   *
   * @param roomID      The room ID where the device is located.
   * @param deviceName  The name of the device
   * @return a newly created Device instance without ID
   */
  Device createDevice(
      RoomID roomID, DeviceName deviceName, DeviceTypeID deviceTypeID);

  /**
   * Creates and returns a new {@link Device} instance with the provided device ID, room ID, device
   * name, device state, and device type ID.
   *
   * @param deviceID    The device ID
   * @param roomID     The room ID where the device is located
   * @param deviceName The name of the device
   * @param deviceState The state of the device
   * @param deviceTypeID The device type ID
   * @return a newly created Device instance with ID
   */

  Device createDevice(DeviceID deviceID, RoomID roomID, DeviceName deviceName,
      DeviceStatus deviceState, DeviceTypeID deviceTypeID);
}