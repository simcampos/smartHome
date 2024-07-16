/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.cli;

import java.util.List;
import smarthome.ddd.IAssembler;
import smarthome.domain.device.Device;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.RoomID;
import smarthome.service.IDeviceService;
import smarthome.service.IRoomService;
import smarthome.utils.Validator;
import smarthome.utils.dto.DeviceDTO;
import smarthome.utils.dto.RoomDTO;

public class GetDevicesFromRoomController {

  private final IRoomService roomService;
  private final IDeviceService deviceService;
  private final IAssembler<Room, RoomDTO> roomAssembler;
  private final IAssembler<Device, DeviceDTO> deviceAssembler;

  /**
   * Constructor for GetDevicesFromRoomController.
   *
   * @param roomService     is the service for the room.
   * @param deviceService   is the service for the device.
   * @param roomAssembler   is the assembler for the room.
   * @param deviceAssembler is the assembler for the device.
   */
  public GetDevicesFromRoomController(IRoomService roomService, IDeviceService deviceService,
      IAssembler<Room, RoomDTO> roomAssembler, IAssembler<Device, DeviceDTO> deviceAssembler) {
    Validator.validateNotNull(roomService, "Room service");
    Validator.validateNotNull(deviceService, "Device service");
    Validator.validateNotNull(roomAssembler, "Room assembler");
    Validator.validateNotNull(deviceAssembler, "Device assembler");

    this.deviceAssembler = deviceAssembler;
    this.deviceService = deviceService;
    this.roomAssembler = roomAssembler;
    this.roomService = roomService;

  }

  /**
   * Gets all rooms.
   *
   * @return a list of rooms.
   */
  public List<RoomDTO> getRooms() {
    List<Room> rooms = roomService.getAllRooms();

    List<RoomDTO> roomDTOList = roomAssembler.domainToDTO(rooms);

    return List.copyOf(roomDTOList);
  }

  /**
   * Gets all devices from a room.
   *
   * @param roomDTO is the room to get the devices from.
   * @return a list of devices.
   */
  public List<DeviceDTO> getDevicesFromRoom(RoomDTO roomDTO) {
    RoomID roomID = new RoomID(roomDTO.roomId);

    if (roomService.getRoomById(roomID).isEmpty()) {
      throw new IllegalArgumentException("Room with ID " + roomID + " not found.");
    }

    List<Device> devices = deviceService.getDevicesByRoomId(roomID);

    List<DeviceDTO> deviceDTOList = deviceAssembler.domainToDTO(devices);

    return List.copyOf(deviceDTOList);
  }


}
