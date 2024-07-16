/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.device_type.DeviceType;
import smarthome.service.IDeviceTypeService;
import smarthome.utils.dto.DeviceTypeDTO;

@RestController
@RequestMapping("/device-types")
public class DeviceTypeController {

  private final IDeviceTypeService deviceTypeService;

  private final IAssembler<DeviceType, DeviceTypeDTO> deviceTypeAssembler;

  /**
   * Constructor for the DeviceTypeController class.
   *
   * @param deviceTypeService   The service for the device type.
   * @param deviceTypeAssembler The assembler for the device type.
   */
  @Autowired
  public DeviceTypeController(IDeviceTypeService deviceTypeService,
      IAssembler<DeviceType, DeviceTypeDTO> deviceTypeAssembler) {
    this.deviceTypeService = deviceTypeService;
    this.deviceTypeAssembler = deviceTypeAssembler;
  }

  /**
   * Get all device types.
   *
   * @return ResponseEntity<CollectionModel < DeviceTypeDTO>> is the response entity
   */
  @GetMapping
  public ResponseEntity<CollectionModel<DeviceTypeDTO>> getDeviceTypes() {
    List<DeviceType> deviceTypeList = deviceTypeService.getAllDeviceTypes();

    List<DeviceTypeDTO> deviceTypeDTOList = deviceTypeAssembler.domainToDTO(deviceTypeList);
    CollectionModel<DeviceTypeDTO> resource = CollectionModel.of(deviceTypeDTOList,
        WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(DeviceTypeController.class).getDeviceTypes())
            .withSelfRel());
    return ResponseEntity.ok(resource);
  }
}
