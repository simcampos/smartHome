/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.rest;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.actuator.blind_roller_actuator.BlindRollerValue;
import smarthome.utils.exceptions.NoLogRecordsFoundException;
import smarthome.domain.log.Log;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.mapper.actuator_vo_assembler.IActuatorVOAssembler;
import smarthome.service.IActuatorService;
import smarthome.service.ILogService;
import smarthome.utils.dto.ActuatorDTO;
import smarthome.utils.entry_dto.actuator_entry_dto.ActuatorValueEntryDTO;
import smarthome.utils.entry_dto.actuator_entry_dto.IActuatorEntryDTO;

@RestController
@RequestMapping("/actuators")
public class ActuatorController {

  private final IActuatorService actuatorService;
  private final IAssembler<IActuator, ActuatorDTO> actuatorAssembler;
  private final ILogService logService;
  private final IActuatorVOAssembler actuatorVOAssembler;

  /**
   * Constructor
   *
   * @param actuatorService is the service for the Actuator.
   * @param actuatorAssembler is the assembler for the Actuator.
   */
  @Autowired
  public ActuatorController(
      IActuatorService actuatorService,
      IAssembler<IActuator, ActuatorDTO> actuatorAssembler,
      ILogService logService, IActuatorVOAssembler actuatorVOAssembler) {
    this.actuatorService = actuatorService;
    this.actuatorAssembler = actuatorAssembler;
    this.logService = logService;
    this.actuatorVOAssembler = actuatorVOAssembler;
  }

  /**
   * Method to add an Actuator to a Device.
   *
   * @param actuatorDataDTO is the Actuator data.
   * @return the Actuator data transfer object.
   */
  @PostMapping
  public ResponseEntity<EntityModel<ActuatorDTO>> addActuator(
      @RequestBody @Valid IActuatorEntryDTO actuatorDataDTO) {
    Object[] actuatorParameters = actuatorVOAssembler.getActuatorParameters(actuatorDataDTO);

    IActuator actuator = actuatorService.addActuator(actuatorParameters);
    ActuatorDTO actuatorDTO = actuatorAssembler.domainToDTO(actuator);

    WebMvcLinkBuilder linkToSelf =
        WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(ActuatorController.class).getActuatorByID(actuatorDTO.id));

    WebMvcLinkBuilder linkToAddActuator =
        WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(ActuatorController.class).addActuator(null));

    EntityModel<ActuatorDTO> resource =
        EntityModel.of(
            actuatorDTO,
            linkToSelf.withSelfRel(),
            linkToAddActuator.withRel("add-actuator"));

    return ResponseEntity.status(HttpStatus.CREATED).body(resource);
  }

  /**
   * Method to get all Actuators.
   *
   * @return a collection of Actuator data transfer objects.
   */
  @GetMapping
  public ResponseEntity<CollectionModel<EntityModel<ActuatorDTO>>> getAllActuators() {
    List<IActuator> actuators = actuatorService.getAllActuators();
    List<ActuatorDTO> actuatorDTOs = actuatorAssembler.domainToDTO(actuators);

    // Transform each ActuatorDTO into EntityModel<ActuatorDTO>
    List<EntityModel<ActuatorDTO>> resources =
        actuatorDTOs.stream()
            .map(
                actuatorDTO -> {
                  WebMvcLinkBuilder linkToActuator =
                      WebMvcLinkBuilder.linkTo(
                          WebMvcLinkBuilder.methodOn(ActuatorController.class)
                              .getActuatorByID(actuatorDTO.id));

                  return EntityModel.of(actuatorDTO, linkToActuator.withRel("get-actuator-by-id"));
                })
            .collect(Collectors.toList());

    // Link to the collection itself
    WebMvcLinkBuilder linkToSelf =
        WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(ActuatorController.class).getAllActuators());

    // Creating CollectionModel containing all EntityModel<ActuatorDTO>
    CollectionModel<EntityModel<ActuatorDTO>> collectionModel =
        CollectionModel.of(resources, linkToSelf.withSelfRel());

    return ResponseEntity.ok(collectionModel);
  }

  /**
   * Method to get an Actuator by its ID.
   *
   * @param id is the Actuator ID.
   * @return the Actuator data transfer object.
   */
  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<ActuatorDTO>> getActuatorByID(@PathVariable("id") String id) {
    Optional<IActuator> actuator = actuatorService.getActuatorByID(new ActuatorID(id));

    if (actuator.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    ActuatorDTO actuatorDTO = actuatorAssembler.domainToDTO(actuator.get());

    // Create link to self
    WebMvcLinkBuilder linkToSelf =
        WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(ActuatorController.class).getActuatorByID(id));

    // Create link to get all Actuators
    WebMvcLinkBuilder linkToActuators =
        WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(ActuatorController.class).getAllActuators());

    EntityModel<ActuatorDTO> resource =
        EntityModel.of(
            actuatorDTO,
            linkToSelf.withSelfRel().withRel("get-actuator-by-id"),
            linkToActuators.withRel("get-actuators"));

    return ResponseEntity.status(HttpStatus.OK).body(resource);
  }

  /**
   * Method to get all Actuators by Device ID.
   * @param strDeviceID is the Device ID.
   * @return a collection of Actuator data transfer objects.
   */
  @GetMapping(params = "deviceID")
  public ResponseEntity<List<EntityModel<ActuatorDTO>>> getActuatorsByDeviceID(
      @RequestParam("deviceID") String strDeviceID) {
    DeviceID deviceID = new DeviceID(strDeviceID);

    List<IActuator> actuators = actuatorService.getActuatorsByDeviceID(deviceID);

    List<ActuatorDTO> actuatorDTOs = actuatorAssembler.domainToDTO(actuators);
    List<EntityModel<ActuatorDTO>> resources = new java.util.ArrayList<>(List.of());

    // Create link to self
    WebMvcLinkBuilder linkToSelf =
        WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(ActuatorController.class)
                .getActuatorsByDeviceID(strDeviceID));

    for (ActuatorDTO actuatorDTO : actuatorDTOs) {
      WebMvcLinkBuilder linkToActuator =
          WebMvcLinkBuilder.linkTo(
              WebMvcLinkBuilder.methodOn(ActuatorController.class).getActuatorByID(actuatorDTO.id));

      EntityModel<ActuatorDTO> resource =
          EntityModel.of(
              actuatorDTO,
              linkToSelf.withSelfRel().withRel("get-actuator-by-device-id"),
              linkToActuator.withRel("get-actuator-by-id"));

      resources.add(resource);
    }

    return ResponseEntity.status(HttpStatus.OK).body(resources);
  }

  /**
   * Method to close a blind roller.
   *
   * @param actuatorValueDTO is the Actuator value data transfer object.
   * @return the Actuator value data transfer object.
   */
  @PostMapping("/set-blindRoller")
  public ResponseEntity<EntityModel<ActuatorDTO>> setBlindRoller(
      @RequestBody @Valid ActuatorValueEntryDTO actuatorValueDTO) {
    SensorTypeID sensorTypeID = new SensorTypeID("PercentagePosition");
    BlindRollerValue blindRollerValueObject = new BlindRollerValue(actuatorValueDTO.value);
    ActuatorID actuatorID = new ActuatorID(actuatorValueDTO.actuatorID);
    DeviceID deviceID = new DeviceID(actuatorValueDTO.deviceID);

    Optional<IActuator> actuator = actuatorService.getActuatorByID(actuatorID);

    if (actuator.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    List<Log> logRecords =
        logService.getDeviceReadingsByDeviceIDAndSensorTypeID(deviceID, sensorTypeID);
    int index = logRecords.size();

    if (index == 0) {
      throw new NoLogRecordsFoundException(
          "No log records found for the specified device and sensor type.");
    }

    actuatorService.setValue(actuator.get(), blindRollerValueObject);

    /* Create ActuatorDTO*/
    ActuatorDTO actuatorDTO = actuatorAssembler.domainToDTO(actuator.get());
    actuatorDTO.actuatorValue = String.valueOf(actuatorValueDTO.value);

    // Create link to self
    WebMvcLinkBuilder linkToSelf =
        WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(ActuatorController.class)
                .setBlindRoller(actuatorValueDTO));

    WebMvcLinkBuilder linkToActuator =
        WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(ActuatorController.class).getActuatorByID(deviceID.getID()));

    EntityModel<ActuatorDTO> resource =
        EntityModel.of(
            actuatorDTO,
            linkToSelf.withSelfRel().withRel("close-blind-roller"),
            linkToActuator.withRel("get-actuator-by-id"));

    return ResponseEntity.status(HttpStatus.CREATED).body(resource);
  }
}
