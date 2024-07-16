/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import smarthome.ddd.IService;
import smarthome.domain.device.Device;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;
import smarthome.utils.dto.DeviceDTO;

public interface IDeviceService extends IService {

  /**
   * Adds a new device to the repository.
   *
   * @param roomID  The room ID where the device is located.
   * @param deviceName The name of the device.
   * @param deviceTypeID The type of the device.
   * @return the device that was added.
   */
  Device addDevice(RoomID roomID, DeviceName deviceName,
      DeviceTypeID deviceTypeID);

  /**
   * Deactivates a device by its ID.
   *
   * @param deviceID The ID of the device to deactivate.
   * @return the device that was deactivated.
   */
  Device deactivateDeviceByID(DeviceID deviceID);

  /**
   * Get all devices in the repository.
   *
   * @return a list of all devices.
   */
  List<Device> getAllDevices();

  /**
   * Get a device by its ID.
   *
   * @param deviceId The ID of the device to get.
   * @return the device with the provided ID.
   */
  Optional<Device> getDeviceByID(DeviceID deviceId);

  /**
   * Get all devices in a room.
   *
   * @param roomId The ID of the room to get devices from.
   * @return a list of all devices in the room.
   */
  List<Device> getDevicesByRoomId(RoomID roomId);

  /**
   * Get devices grouped by temperature functionality from a Map.
   *
   * @param deviceMap The map of all devices grouped by functionality.
   * @return The list of devices grouped by temperature functionality.
   */
  List<DeviceDTO> getDevicesByTypeDescriptionFromMap(Map<DeviceType, List<DeviceDTO>> deviceMap,
      String typeDescription);

  /**
   * Filters the devices in a list by their room location.
   *
   * @param devicesDTO The list of devices to filter.
   * @param roomID     The room to filter by.
   * @return The list of devices in the room.
   */
  List<DeviceDTO> getDevicesFromListByRoomId(List<DeviceDTO> devicesDTO, RoomID roomID);

  List<Device> getDevicesByDeviceTypeID(DeviceTypeID deviceTypeID);
}
