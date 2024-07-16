/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import smarthome.ddd.IAssembler;
import smarthome.domain.device.Device;
import smarthome.utils.Validator;
import smarthome.utils.dto.DeviceDTO;

@Component
public class DeviceAssembler implements IAssembler<Device, DeviceDTO> {

  /**
   * Converts a Device object to a DeviceDTO object.
   *
   * @param domainEntity The Device object to be converted.
   * @return The DeviceDTO object.
   */
  @Override
  public DeviceDTO domainToDTO(Device domainEntity) {
    Validator.validateNotNull(domainEntity, "Device");

    String deviceID = domainEntity.getID().toString();
    String roomID = domainEntity.getRoomID().toString();
    String deviceName = domainEntity.getName().getName();
    String deviceStatus = domainEntity.getDeviceStatus().toString();

    return new DeviceDTO(deviceID, roomID, deviceName, deviceStatus);
  }

  /**
   * Converts a list of Device objects to a list of DeviceDTO objects.
   *
   * @param domainEntities The list of Device objects to be converted.
   * @return The list of DeviceDTO objects.
   */
  @Override
  public List<DeviceDTO> domainToDTO(List<Device> domainEntities) {
    if (domainEntities == null) {
      throw new IllegalArgumentException("The list of Devices cannot be null.");
    }
    return domainEntities.stream().map(this::domainToDTO).toList();
  }
}
