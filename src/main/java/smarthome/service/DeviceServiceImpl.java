/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import smarthome.ddd.IRepository;
import smarthome.domain.device.Device;
import smarthome.domain.device.IDeviceFactory;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;
import smarthome.utils.Validator;
import smarthome.utils.dto.DeviceDTO;

@Service
public class DeviceServiceImpl implements IDeviceService {

  private final IDeviceRepository deviceRepository;
  private final IDeviceFactory deviceFactory;
  private final IRepository<RoomID, Room> roomRepository;

  /**
   * Constructor for DeviceService.
   *
   * @param deviceRepository is the repository for the device.
   * @param deviceFactory    is the factory for the device.
   * @param roomRepository   is the repository for the room.
   */
  public DeviceServiceImpl(IDeviceRepository deviceRepository, IDeviceFactory deviceFactory,
      IRepository<RoomID, Room> roomRepository) {
    Validator.validateNotNull(deviceRepository, "Device repository");
    this.deviceRepository = deviceRepository;
    Validator.validateNotNull(deviceFactory, "Device factory");
    this.deviceFactory = deviceFactory;
    Validator.validateNotNull(roomRepository, "Room repository");
    this.roomRepository = roomRepository;
  }


  /**
   * Adds a new device to the room with the provided room ID.
   *
   * @param roomID       is the room ID where the device is located.
   * @param deviceName   is the name of the device.
   * @return the newly created device.
   */
  public Device addDevice(RoomID roomID, DeviceName deviceName,
      DeviceTypeID deviceTypeID) {
    Optional<Room> roomOptional = roomRepository.ofIdentity(roomID);
    if (roomOptional.isEmpty()) {
      throw new IllegalArgumentException("Room with ID " + roomID + " not found.");
    }

    Device device = deviceFactory.createDevice(roomID, deviceName, deviceTypeID);
    deviceRepository.save(device);
    return device;
  }

  /**
   * Deactivates the device with the provided device ID.
   *
   * @param deviceID is the unique identifier of the device.
   * @return the updated device.
   */
  public Device deactivateDeviceByID(DeviceID deviceID) {
    Optional<Device> deviceOptional = getDeviceByID(deviceID);
    if (deviceOptional.isPresent()) {
      Device device = deviceOptional.get();
      device.deactivateDevice();
      deviceRepository.update(device);
      return device;
    } else {
      throw new IllegalArgumentException("Device with ID " + deviceID + " not found.");
    }
  }

  /**
   * Returns all the devices in the repository.
   *
   * @return a list of devices.
   */
  @Override
  public List<Device> getAllDevices() {
    return deviceRepository.findAll();
  }

  /**
   * Returns the device with the provided device ID.
   *
   * @param deviceId is the unique identifier of the device.
   * @return an optional containing the device if found, empty otherwise.
   */
  @Override
  public Optional<Device> getDeviceByID(DeviceID deviceId) {
    return deviceRepository.ofIdentity(deviceId);
  }

  /**
   * Returns the devices in the room with the provided room ID.
   *
   * @param roomId is the unique identifier of the room.
   * @return a list of devices in the room.
   */
  @Override
  public List<Device> getDevicesByRoomId(RoomID roomId) throws EntityNotFoundException {
    List<Device> devices = deviceRepository.findByRoomID(roomId);
    if (devices == null) {
      throw new EntityNotFoundException("No devices found for room ID: " + roomId);
    }
    return devices;
  }

  /**
   * Returns the devices with the provided device type ID.
   *
   * @param deviceTypeID is the unique identifier of the device type.
   * @return a list of devices with the provided device type ID.
   */
  public List<Device> getDevicesByDeviceTypeID(DeviceTypeID deviceTypeID) {
    return deviceRepository.findByDeviceTypeID(deviceTypeID);
  }

  /**
   * Get devices grouped by temperature functionality from a Map.
   *
   * @param deviceMap The map of all devices grouped by functionality.
   * @return The list of devices grouped by temperature functionality.
   */
  @Override
  public List<DeviceDTO> getDevicesByTypeDescriptionFromMap(
      Map<DeviceType, List<DeviceDTO>> deviceMap, String typeDescription) {
    Validator.validateNotNull(deviceMap, "Device map");
    Validator.validateNotNull(typeDescription, "Type description");

    List<DeviceDTO> devicesByTypeDescription = new ArrayList<>();

    for (Map.Entry<DeviceType, List<DeviceDTO>> entry : deviceMap.entrySet()) {

      DeviceType deviceType = entry.getKey();
      if (deviceType.getDescription().toString().equals(typeDescription)) {
        List<DeviceDTO> deviceDTOList = entry.getValue();
        devicesByTypeDescription.addAll(deviceDTOList);
      }
    }

    return devicesByTypeDescription;
  }

  /**
   * Filters the devices in a list by their room location.
   *
   * @param devicesDTO The list of devices to filter.
   * @param roomID     The room to filter by.
   * @return The list of devices in the room.
   */
  @Override
  public List<DeviceDTO> getDevicesFromListByRoomId(List<DeviceDTO> devicesDTO, RoomID roomID) {
    Validator.validateNotNull(devicesDTO, "List of DevicesDTO");
    Validator.validateNotNull(roomID, "A Room ID");

    List<DeviceDTO> devicesFromRoom = new ArrayList<>();

    for (DeviceDTO deviceDTO : devicesDTO) {
      if (deviceDTO.roomID.equals(roomID.getID())) {
        devicesFromRoom.add(deviceDTO);
      }
    }
    return devicesFromRoom;
  }

}
