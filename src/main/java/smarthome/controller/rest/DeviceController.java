/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import jakarta.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.device.Device;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.mapper.DeviceTypeAssembler;
import smarthome.service.IDeviceService;
import smarthome.service.IDeviceTypeService;
import smarthome.utils.dto.DeviceDTO;
import smarthome.utils.dto.DeviceTypeDTO;
import smarthome.utils.entry_dto.DeviceEntryDTO;
import smarthome.utils.entry_dto.actuator_entry_dto.ActuatorGenericDataDTOImp;
import smarthome.utils.entry_dto.actuator_entry_dto.ActuatorWithDecimalLimitsEntryDTOImp;
import smarthome.utils.entry_dto.actuator_entry_dto.ActuatorWithIntegerLimitsEntryDTOImp;
import smarthome.utils.entry_dto.sensor_entry_dto.SensorGenericEntryDTOImp;
import smarthome.utils.entry_dto.sensor_entry_dto.SensorWithDateEntryDTOImp;
import smarthome.utils.entry_dto.sensor_entry_dto.SensorWithGPSEntryDTOImp;

/** Class representing a REST controller for operations related to devices in the smart home. */
@RestController
@RequestMapping("/devices")
public class DeviceController {

  private final IDeviceService deviceService;
  private final IAssembler<Device, DeviceDTO> deviceAssembler;
  private final IDeviceTypeService deviceTypeService;
  private final DeviceTypeAssembler deviceTypeAssembler;

  /**
   * Constructor for the DeviceController class.
   *
   * @param deviceService The service for device operations.
   * @param deviceAssembler The assembler for converting device objects to DTOs.
   */
  @Autowired
  public DeviceController(
      IDeviceService deviceService,
      IAssembler<Device, DeviceDTO> deviceAssembler,
      IDeviceTypeService deviceTypeService,
      DeviceTypeAssembler deviceTypeAssembler) {
    this.deviceAssembler = deviceAssembler;
    this.deviceService = deviceService;
    this.deviceTypeService = deviceTypeService;
    this.deviceTypeAssembler = deviceTypeAssembler;
  }

  /**
   * Handles HTTP POST requests for adding a new device.
   *
   * @param data The data of the device to be added.
   * @return The response entity with the added device.
   */
  @PostMapping
  public ResponseEntity<EntityModel<DeviceDTO>> addDevice(@Valid @RequestBody DeviceEntryDTO data) {
    RoomID roomID = new RoomID(data.roomID);
    TypeDescription deviceTypeDescription = new TypeDescription(data.deviceTypeDescription);
    DeviceName deviceName = new DeviceName(data.deviceName);

    DeviceType deviceType = deviceTypeService.addDeviceType(deviceTypeDescription);

    Device device = deviceService.addDevice(roomID, deviceName, deviceType.getID());

    DeviceDTO deviceDTO = deviceAssembler.domainToDTO(device);

    Link selfLink = linkTo(methodOn(DeviceController.class).addDevice(data)).withRel("self")
        .withTitle("Add Device")
        .withType("POST");

    // Links to addActuator method in ActuatorController
    Link addGenericActuatorLink = linkTo(methodOn(ActuatorController.class).addActuator(new ActuatorGenericDataDTOImp())).withRel("add-actuator")
        .withTitle("Add Generic Actuator")
        .withType("POST");

    Link addIntegerActuatorLink = linkTo(methodOn(ActuatorController.class).addActuator(new ActuatorWithIntegerLimitsEntryDTOImp())).withRel("add-actuator")
        .withTitle("Add Set Integer Actuator")
        .withType("POST");

    Link addDecimalActuatorLink = linkTo(methodOn(ActuatorController.class).addActuator(new ActuatorWithDecimalLimitsEntryDTOImp())).withRel("add-actuator")
        .withTitle("Add Set Decimal Actuator")
        .withType("POST");


    // Links to addSensor method in SensorController
    Link addGenericSensorLink = linkTo(methodOn(SensorController.class).addSensor(new SensorGenericEntryDTOImp())).withRel("add-sensor")
        .withTitle("Add Generic Sensor")
        .withType("POST");

    Link addGpsSensorLink = linkTo(methodOn(SensorController.class).addSensor(new SensorWithGPSEntryDTOImp())).withRel("add-sensor")
        .withTitle("Add GPS Sensor")
        .withType("POST");

    Link addDateSensorLink = linkTo(methodOn(SensorController.class).addSensor(new SensorWithDateEntryDTOImp())).withRel("add-sensor")
        .withTitle("Add Date Sensor")
        .withType("POST");

    EntityModel<DeviceDTO> resource =
        EntityModel.of(
            deviceDTO, selfLink, addGenericActuatorLink, addIntegerActuatorLink, addDecimalActuatorLink, addGenericSensorLink, addGpsSensorLink, addDateSensorLink);

    return ResponseEntity.status(HttpStatus.CREATED).body(resource);
  }

  /**
   * Handles HTTP GET requests for retrieving a device by its ID.
   *
   * @param id The ID of the device to be retrieved.
   * @return The response entity (link HAETOS) with the retrieved device.
   */
  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<DeviceDTO>> getDevice(
      @PathVariable String id)
  {
    DeviceID deviceID = new DeviceID(id);
    Optional<Device> device = deviceService.getDeviceByID(deviceID);

    if (device.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    DeviceDTO deviceDTO = deviceAssembler.domainToDTO(device.get());

    // Link to self
    Link selfLink =
        linkTo(methodOn(DeviceController.class).getDevice(id))
            .withRel("self")
            .withTitle("Get Device")
            .withType("GET");

    Link deactivateDeviceLink = linkTo(methodOn(DeviceController.class).deactivateDevice(id)).withRel("deactivate-device")
        .withTitle("Deactivate Device")
        .withType("PUT");

    // Links to addActuator method in ActuatorController
    Link addGenericActuatorLink = linkTo(methodOn(ActuatorController.class).addActuator(new ActuatorGenericDataDTOImp())).withRel("add-actuator")
            .withTitle("Add Generic Actuator")
            .withType("POST");

    Link addIntegerActuatorLink = linkTo(methodOn(ActuatorController.class).addActuator(new ActuatorWithIntegerLimitsEntryDTOImp())).withRel("add-actuator")
            .withTitle("Add Set Integer Actuator")
            .withType("POST");

    Link addDecimalActuatorLink = linkTo(methodOn(ActuatorController.class).addActuator(new ActuatorWithDecimalLimitsEntryDTOImp())).withRel("add-actuator")
            .withTitle("Add Set Decimal Actuator")
            .withType("POST");


    // Links to addSensor method in SensorController
    Link addGenericSensorLink = linkTo(methodOn(SensorController.class).addSensor(new SensorGenericEntryDTOImp())).withRel("add-sensor")
        .withTitle("Add Generic Sensor")
        .withType("POST");

    Link addGpsSensorLink = linkTo(methodOn(SensorController.class).addSensor(new SensorWithGPSEntryDTOImp())).withRel("add-sensor")
        .withTitle("Add GPS Sensor")
        .withType("POST");

    Link addDateSensorLink = linkTo(methodOn(SensorController.class).addSensor(new SensorWithDateEntryDTOImp())).withRel("add-sensor")
        .withTitle("Add Date Sensor")
        .withType("POST");


    EntityModel<DeviceDTO> entityModel =
        EntityModel.of(deviceDTO, selfLink, deactivateDeviceLink, addGenericActuatorLink, addIntegerActuatorLink, addDecimalActuatorLink, addGenericSensorLink, addGpsSensorLink, addDateSensorLink);

    return ResponseEntity.ok(entityModel);
  }

  /** Handles HTTP GET requests for retrieving all devices. */
  @GetMapping
  public ResponseEntity<CollectionModel<DeviceDTO>> listDevices() {
    List<Device> devices = deviceService.getAllDevices();

    List<DeviceDTO> deviceDTOs = deviceAssembler.domainToDTO(devices);

    for (DeviceDTO deviceDTO : deviceDTOs) {
      Link getDeviceLink = linkTo(methodOn(DeviceController.class).getDevice(deviceDTO.deviceID)).withRel("get-device")
          .withTitle("Get Device")
          .withType("GET");
      deviceDTO.add(getDeviceLink);
    }

    Link selfLink = linkTo(methodOn(DeviceController.class).listDevices()).withRel("self")
        .withTitle("Get All Devices")
        .withType("GET");

    CollectionModel<DeviceDTO> resource =
        CollectionModel.of(
            deviceDTOs, selfLink);
    return ResponseEntity.ok(resource);
  }

  /** Handles HTTP GET requests for deactivating a device. */
  @PutMapping("/deactivate/{id}")
  public ResponseEntity<EntityModel<DeviceDTO>> deactivateDevice(@PathVariable String id) {
    DeviceID deviceID = new DeviceID(id);
    Optional<Device> device = deviceService.getDeviceByID(deviceID);

    if (device.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Device deactivatedDevice = deviceService.deactivateDeviceByID(deviceID);

    DeviceDTO deviceDTO = deviceAssembler.domainToDTO(deactivatedDevice);

    Link selfLink = linkTo(methodOn(DeviceController.class).deactivateDevice(id)).withRel("self")
        .withTitle("Deactivate Device")
        .withType("PUT");

    EntityModel<DeviceDTO> entityModel = EntityModel.of(deviceDTO, selfLink);

    return ResponseEntity.ok(entityModel);
  }


  @GetMapping("/grouped")
  public Map<DeviceTypeDTO, List<DeviceDTO>> getAllDevicesGroupedByFunctionality() {
    List<DeviceType> deviceTypes = deviceTypeService.getAllDeviceTypes();
    Map<DeviceTypeDTO, List<DeviceDTO>> devicesGroupedByFunctionality = new LinkedHashMap<>();
    for (DeviceType deviceType : deviceTypes) {
      List<Device> devices = deviceService.getDevicesByDeviceTypeID(deviceType.getID());
      DeviceTypeDTO deviceTypeDTO = deviceTypeAssembler.domainToDTO(deviceType);
      List<DeviceDTO> deviceDTOs = deviceAssembler.domainToDTO(devices);
      devicesGroupedByFunctionality.put(deviceTypeDTO, deviceDTOs);
    }
    return devicesGroupedByFunctionality;
  }


  /**
   * Get all devices in a room
   *
   * @param roomIdStr is the room ID
   * @param deviceTypeIdStr is the device type
   * @return a list of all devices in the room with the given ID
   */
  @GetMapping(params = {"room_id"})
  public ResponseEntity<CollectionModel<DeviceDTO>> listDevices(
      @RequestParam("room_id") String roomIdStr,
      @RequestParam(value = "device_type_id", required = false) String deviceTypeIdStr) {
    RoomID roomId = new RoomID(roomIdStr);
    List<DeviceDTO> deviceDTOs;

    List<Device> devices = deviceService.getDevicesByRoomId(roomId);

    if (deviceTypeIdStr != null) {
      DeviceTypeID deviceTypeId = new DeviceTypeID(deviceTypeIdStr);
      deviceDTOs = devices.stream()
          .filter(device -> device.getDeviceTypeID().equals(deviceTypeId))
          .map(deviceAssembler::domainToDTO)
          .toList();
    } else {
      deviceDTOs = devices.stream()
          .map(deviceAssembler::domainToDTO)
          .toList();
    }

    for (DeviceDTO deviceDTO : deviceDTOs) {
      Link getDeviceLink = linkTo(methodOn(DeviceController.class).getDevice(deviceDTO.deviceID)).withRel("get-device")
          .withTitle("Get Device")
          .withType("GET");
      deviceDTO.add(getDeviceLink);
    }

    Link selfLink = linkTo(methodOn(DeviceController.class).listDevices(roomIdStr,
        deviceTypeIdStr)).withRel("self")
        .withTitle("Get All Devices by Room ID and Device Type ID")
        .withType("GET");

    CollectionModel<DeviceDTO> resource = CollectionModel.of(deviceDTOs, selfLink);
    return ResponseEntity.ok(resource);
  }

}