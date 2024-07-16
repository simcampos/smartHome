/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils.dto;

import org.springframework.hateoas.RepresentationModel;
import smarthome.ddd.IDTO;

/**
 * Data Transfer Object (DTO) representing information about a device.
 */
public class DeviceDTO extends RepresentationModel<DeviceDTO> implements IDTO {

  /**
   * The unique identifier of the device.
   */
  public final String deviceID;
  /**
   * The unique identifier of the room.
   */
  public final String roomID;
  /**
   * The name of the device.
   */
  public final String deviceName;
  /**
   * The status of the device.
   */
  public final String deviceStatus;

  /**
   * Constructs a new DeviceDTO object with the specified device details.
   *
   * @param deviceID     The unique identifier of the device.
   * @param roomID       The unique identifier of the room.
   * @param deviceName   The name of the device.
   * @param deviceStatus The status of the device.
   */
  public DeviceDTO(String deviceID, String roomID, String deviceName, String deviceStatus) {
    this.deviceID = deviceID;
    this.roomID = roomID;
    this.deviceName = deviceName;
    this.deviceStatus = deviceStatus;
  }

  @Override
  public String toString() {
    return deviceID + " " + roomID + " " + deviceName + " " + deviceStatus;
  }
}
