/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.cli;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import smarthome.ddd.IAssembler;
import smarthome.domain.device.Device;
import smarthome.domain.device_type.DeviceType;
import smarthome.service.IDeviceService;
import smarthome.service.IDeviceTypeService;
import smarthome.utils.Validator;
import smarthome.utils.dto.DeviceDTO;

public class GetListOfAllDevicesGroupedByFunctionalityController {

  private final IDeviceService deviceService;
  private final IDeviceTypeService deviceTypeService;
  private final IAssembler<Device, DeviceDTO> deviceAssembler;


  /**
   * Constructor for GetListOfAllDevicesGroupedByFunctionality.
   *
   * @param deviceService     is the service for the device.
   * @param deviceAssembler   is the assembler for the device.
   * @param deviceTypeService is the service for the device type.
   */
  public GetListOfAllDevicesGroupedByFunctionalityController(IDeviceService deviceService,
      IAssembler<Device, DeviceDTO> deviceAssembler, IDeviceTypeService deviceTypeService) {
    Validator.validateNotNull(deviceService, "Device service");
    Validator.validateNotNull(deviceAssembler, "Device assembler");
    Validator.validateNotNull(deviceTypeService, "Device type service");

    this.deviceAssembler = deviceAssembler;
    this.deviceService = deviceService;
    this.deviceTypeService = deviceTypeService;
  }


  public Map<DeviceType, List<DeviceDTO>> getDevicesDTOGroupedByFunctionality() {
    List<Device> devices = deviceService.getAllDevices();

    if (devices.isEmpty()) {
      throw new IllegalArgumentException("No devices found.");
    }

    Map<DeviceType, List<DeviceDTO>> devicesGroupedByFunctionality = new LinkedHashMap<>();

    for (Device device : devices) {

      Optional<DeviceType> deviceType = deviceTypeService.getDeviceTypeByID(
          device.getDeviceTypeID());

      if (deviceType.isPresent()) {
        DeviceType deviceTypeObj = deviceType.get();

        if (devicesGroupedByFunctionality.containsKey(deviceTypeObj)) {
          devicesGroupedByFunctionality.get(deviceTypeObj)
              .add(deviceAssembler.domainToDTO(device));
        } else {
          List<DeviceDTO> newDeviceList = new ArrayList<>();
          newDeviceList.add(deviceAssembler.domainToDTO(device));
          devicesGroupedByFunctionality.put(deviceTypeObj, newDeviceList);
        }
      } else {
        throw new IllegalArgumentException("DeviceType not found.");
      }
    }
    return devicesGroupedByFunctionality;
  }
}
