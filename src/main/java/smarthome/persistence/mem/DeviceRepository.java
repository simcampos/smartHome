/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.mem;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Repository;
import smarthome.domain.device.Device;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;
import smarthome.utils.Validator;

public class DeviceRepository implements IDeviceRepository {

  /**
   * Map to store the device data.
   */
  private final Map<DeviceID, Device> DATA = new LinkedHashMap<>();

  /**
   * Method to save a domain entity.
   *
   * @param device is the domain entity to be saved.
   * @return the saved domain entity.
   */
  @Override
  public Device save(Device device) {
    Validator.validateNotNull(device, "Device");

    if (containsOfIdentity(device.getID())) {
      throw new IllegalArgumentException("Device already exists.");
    } else {
      DATA.put(device.getID(), device);
    }
    return device;
  }

  /**
   * Method to find all domain entities.
   *
   * @return
   */
  @Override
  public List<Device> findAll() {
    List<Device> allDevices = DATA.values().stream().toList();
    return allDevices;
  }

  /**
   * Method to find a domain entity by its unique identifier.
   *
   * @param deviceID is the unique identifier of the domain entity.
   * @return
   */
  @Override
  public Optional<Device> ofIdentity(DeviceID deviceID) {
    Optional<Device> device = Optional.ofNullable(DATA.get(deviceID));
    return device;
  }

  /**
   * Method to check if a domain entity exists by its unique identifier.
   *
   * @param deviceID is the unique identifier of the domain entity.
   * @return
   */
  @Override
  public boolean containsOfIdentity(DeviceID deviceID) {
    return DATA.containsKey(deviceID);
  }

  /**
   * Method to find all devices in a room.
   *
   * @param roomId is the unique identifier of the room.
   * @return a list of devices in the room.
   */

  @Override
  public List<Device> findByRoomID(RoomID roomId) {
    List<Device> devices = DATA.values().stream()
        .filter(device -> device.getRoomID().equals(roomId)).toList();
    return devices;
  }

  /**
   * Method to update a device
   */
  @Override
  public Device update(Device device) {
    Validate.notNull(device, "Device");
    if (containsOfIdentity(device.getID())) {
      DATA.put(device.getID(), device);
    } else {
      throw new IllegalArgumentException("Device does not exist.");
    }
    return device;
  }

  @Override
  public List<Device> findByDeviceTypeID(DeviceTypeID deviceTypeID) {
    List<Device> devices = DATA.values().stream()
        .filter(device -> device.getDeviceTypeID().equals(deviceTypeID)).toList();
    return devices;
  }
}
