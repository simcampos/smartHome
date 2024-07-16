/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.cli;

import java.util.List;
import java.util.Optional;
import smarthome.ddd.IAssembler;
import smarthome.domain.device.Device;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;
import smarthome.service.IDeviceService;
import smarthome.service.IRoomService;
import smarthome.utils.Validator;
import smarthome.utils.dto.DeviceDTO;
import smarthome.utils.dto.RoomDTO;
import smarthome.utils.entry_dto.DeviceEntryDTO;

/**
 * Controller class responsible for handling device addition to rooms within a smart home domain.
 */
public class AddDeviceToRoomController {

  private final IRoomService roomService;
  private final IAssembler<Room, RoomDTO> roomAssembler;
  private final IDeviceService deviceService;
  private final IAssembler<Device, DeviceDTO> deviceAssembler;

  /**
   * Constructs a new AddDeviceToRoomController with necessary service and assembler dependencies.
   * validates the room service, room assembler, device service, and device assembler.
   *
   * @param roomService       Service for managing room-related operations.
   * @param roomAssembler     Assembler for converting room entities to DTOs.
   * @param deviceServiceImpl Service for managing device-related operations.
   * @param deviceAssembler   Assembler for converting device entities to DTOs.
   */
  public AddDeviceToRoomController(IRoomService roomService,
      IAssembler<Room, RoomDTO> roomAssembler, IDeviceService deviceServiceImpl,
      IAssembler<Device, DeviceDTO> deviceAssembler) {
    Validator.validateNotNull(roomService, "Room service");
    Validator.validateNotNull(roomAssembler, "Room assembler");
    Validator.validateNotNull(deviceServiceImpl, "Device service");
    Validator.validateNotNull(deviceAssembler, "Device assembler");

    this.roomService = roomService;
    this.roomAssembler = roomAssembler;
    this.deviceService = deviceServiceImpl;
    this.deviceAssembler = deviceAssembler;
  }


  /**
   * Retrieves all rooms as a list of RoomDTOs.
   *
   * @return a list of RoomDTOs.
   */
  public List<RoomDTO> getAllRooms() {
    List<Room> rooms = roomService.getAllRooms();
    return roomAssembler.domainToDTO(rooms);
  }

  /**
   * Adds a device to a room identified by its ID, creating a new device entity in the process.
   *
   * @param deviceDataDTO The data needed to create a device.
   * @return A DeviceDTO representing the added device.
   * @throws IllegalArgumentException if the specified room does not exist.
   */
  public DeviceDTO addDeviceToRoom(DeviceEntryDTO deviceDataDTO) {

    RoomID roomIdVO = new RoomID(deviceDataDTO.roomID);
    DeviceName deviceNameVO = new DeviceName(deviceDataDTO.deviceName);
    DeviceTypeID deviceTypeIDVO = new DeviceTypeID(deviceDataDTO.deviceTypeDescription);

    Optional<Room> roomOptional = roomService.getRoomById(roomIdVO);
    if (roomOptional.isEmpty()) {
      throw new IllegalArgumentException("Room with ID " + roomIdVO + " not found.");
    }

    Device device = deviceService.addDevice(roomIdVO, deviceNameVO,
        deviceTypeIDVO);

    return deviceAssembler.domainToDTO(device);
  }
}
