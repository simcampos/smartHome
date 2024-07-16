/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.service.ISensorTypeService;
import smarthome.utils.dto.SensorTypeDTO;
import smarthome.utils.entry_dto.SensorTypeEntryDTO;

@RestController
@RequestMapping("/sensor-types")
public class SensorTypeController {

  @NotNull
  private final ISensorTypeService sensorTypeService;
  @NotNull
  private final IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler;

  /**
   * Constructor of the SensorTypeController
   *
   * @param sensorTypeService   the service of the sensor type
   * @param sensorTypeAssembler the assembler of the sensor type
   */
  @Autowired
  public SensorTypeController(ISensorTypeService sensorTypeService,
      IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler) {
    this.sensorTypeService = sensorTypeService;
    this.sensorTypeAssembler = sensorTypeAssembler;
  }

  /**
   * Creates a new sensor type
   *
   * @param sensorTypeDataDTO the data of the sensor type
   * @return the created sensor type
   */
  @PostMapping
  public ResponseEntity<EntityModel<SensorTypeDTO>> createSensorType(
      @RequestBody @Valid SensorTypeEntryDTO sensorTypeDataDTO) {
    TypeDescription typeDescription = new TypeDescription(sensorTypeDataDTO.description);
    UnitID unitID = new UnitID(sensorTypeDataDTO.unitID);

    SensorType sensorType = sensorTypeService.createSensorType(typeDescription, unitID);
    SensorTypeDTO sensorTypeDTO = sensorTypeAssembler.domainToDTO(sensorType);

    Link linkToSelf = WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(SensorTypeController.class).createSensorType(sensorTypeDataDTO))
        .withSelfRel();

    EntityModel<SensorTypeDTO> resource = EntityModel.of(sensorTypeDTO, linkToSelf);

    return ResponseEntity.status(HttpStatus.CREATED).body(resource);
  }


  /**
   * Get all sensor types in the system and return them as a list
   *
   * @return the list of sensor types
   */
  @GetMapping
  public ResponseEntity<CollectionModel<SensorTypeDTO>> getSensorTypes() {

    List<SensorType> sensorTypeList = sensorTypeService.getAllSensorTypes();
    List<SensorTypeDTO> sensorTypeDTOList = sensorTypeAssembler.domainToDTO(sensorTypeList);

    for (SensorTypeDTO sensorTypeDTO : sensorTypeDTOList) {
      sensorTypeDTO.add(WebMvcLinkBuilder.linkTo(
              WebMvcLinkBuilder.methodOn(SensorModelController.class)
                  .getSensorModelsBySensorTypeId(sensorTypeDTO.sensorTypeID))
          .withRel("sensor-models")
          .withTitle("Get sensor models of Type")
          .withType("GET"));
      sensorTypeDTO.add(WebMvcLinkBuilder.linkTo(
              WebMvcLinkBuilder.methodOn(SensorTypeController.class)
                  .getSensorTypeByID(sensorTypeDTO.sensorTypeID))
          .withRel("self")
          .withTitle("Get sensor type")
          .withType("GET"));
    }

    CollectionModel<SensorTypeDTO> resource = CollectionModel.of(
        sensorTypeDTOList);

    return ResponseEntity.ok(resource);
  }

  /**
   * Get a sensor type by its ID
   *
   * @param id the ID of the sensor type
   * @return the sensor type
   */
  @GetMapping("/{id}")
  public ResponseEntity<SensorTypeDTO> getSensorTypeByID(@Valid @PathVariable("id") String id) {
    SensorTypeID sensorTypeID = new SensorTypeID(id);
    Optional<SensorType> sensorType = sensorTypeService.getSensorTypeByID(sensorTypeID);
    if (sensorType.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    SensorTypeDTO dto = sensorTypeAssembler.domainToDTO(sensorType.get());

    // HATEOAS Links
    dto.add(WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(SensorTypeController.class).getSensorTypeByID(id))
        .withRel("self")
        .withTitle("Get sensor type")
        .withType("GET"));
    dto.add(WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(SensorModelController.class).getSensorModelsBySensorTypeId(id))
        .withRel("sensor-models")
        .withTitle("Get sensor models of Type")
        .withType("GET"));

    return ResponseEntity.ok(dto);
  }





}
