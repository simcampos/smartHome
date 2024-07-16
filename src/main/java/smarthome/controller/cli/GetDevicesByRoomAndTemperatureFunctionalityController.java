/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.cli;

import java.util.List;
import java.util.Map;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.value_object.RoomID;
import smarthome.service.IDeviceService;
import smarthome.utils.Validator;
import smarthome.utils.dto.DeviceDTO;
import smarthome.utils.dto.RoomDTO;

public class GetDevicesByRoomAndTemperatureFunctionalityController {

  private final IDeviceService deviceService;

  /**
   * Constructor for GetDevicesByRoomAndTemperatureFunctionalityController.
   *
   * @param deviceService The device service.
   */
  public GetDevicesByRoomAndTemperatureFunctionalityController(IDeviceService deviceService) {
    Validator.validateNotNull(deviceService, "Device service");
    this.deviceService = deviceService;
  }

  /**
   * Get devices by type description.
   *
   * @param map     The map of all devices grouped by functionality.
   * @param roomDTO The room to get the devices from.
   * @return The list of devices by type description.
   */
  public List<DeviceDTO> getDevicesByRoomAndTemperatureFunctionality(
      Map<DeviceType, List<DeviceDTO>> map, RoomDTO roomDTO) {
    Validator.validateNotNull(map, "A Map");
    Validator.validateNotNull(roomDTO, "Room DTO");

    List<DeviceDTO> temperatureDevicesDTO = deviceService.getDevicesByTypeDescriptionFromMap(map,
        "Temperature");

    return deviceService.getDevicesFromListByRoomId(temperatureDevicesDTO,
        new RoomID(roomDTO.roomId));
  }
}
