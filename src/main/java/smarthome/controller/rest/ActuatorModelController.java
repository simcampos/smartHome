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

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.service.IActuatorModelService;
import smarthome.utils.dto.ActuatorModelDTO;


@RestController
@RequestMapping("/actuator-models")
public class ActuatorModelController {

  private final IActuatorModelService actuatorModelService;
  private final IAssembler<ActuatorModel, ActuatorModelDTO> actuatorModelAssembler;

  /**
   * Instantiates a new Actuator model controller.
   *
   * @param actuatorModelService   the actuator model service
   * @param actuatorModelAssembler the actuator model assembler
   */
  @Autowired
  public ActuatorModelController(
      IActuatorModelService actuatorModelService,
      IAssembler<ActuatorModel, ActuatorModelDTO> actuatorModelAssembler) {
    this.actuatorModelService = actuatorModelService;
    this.actuatorModelAssembler = actuatorModelAssembler;
  }

  /**
   * Get all actuator models by actuator type ID.
   *
   * @param actuatorTypeID the actuator type ID
   * @return the actuator models by actuator type ID
   */
  @GetMapping(params = "actuatorTypeID")
  public ResponseEntity<CollectionModel<ActuatorModelDTO>> getActuatorModelsByActuatorTypeId(
      @RequestParam("actuatorTypeID") String actuatorTypeID) {

    ActuatorTypeID actuatorTypeIDObj = new ActuatorTypeID(actuatorTypeID);

    List<ActuatorModel> actuatorModels =
        actuatorModelService.getActuatorModelsByActuatorTypeId(actuatorTypeIDObj);

    if (actuatorModels.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Link selfLink = linkTo(
        methodOn(ActuatorModelController.class).getActuatorModelsByActuatorTypeId(
            actuatorTypeID)).withSelfRel();

    List<ActuatorModelDTO> actuatorModelDTOS = actuatorModelAssembler.domainToDTO(actuatorModels);
    CollectionModel<ActuatorModelDTO> resource = CollectionModel.of(actuatorModelDTOS, selfLink);
    return ResponseEntity.ok(resource);
  }

}
