/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.rest;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.unit.Unit;
import smarthome.service.IUnitService;
import smarthome.utils.dto.UnitDTO;

@RestController
@RequestMapping("/units")
public class UnitController {

  @NotNull
  private final IUnitService unitService;

  @NotNull
  private final IAssembler<Unit, UnitDTO> unitAssembler;

  /**
   * Constructor for UnitController
   *
   * @param unitService   is the service for unit
   * @param unitAssembler is the assembler for unit
   */
  @Autowired
  public UnitController(IUnitService unitService, IAssembler<Unit, UnitDTO> unitAssembler) {
    this.unitService = unitService;
    this.unitAssembler = unitAssembler;
  }

  /**
   * Get all units
   *
   * @return ResponseEntity<CollectionModel < UnitDTO>> is the response entity
   */
  @GetMapping
  public ResponseEntity<CollectionModel<UnitDTO>> getUnits() {
    List<Unit> unitList = unitService.getAllunitTypes();
    List<UnitDTO> unitDTOList = unitAssembler.domainToDTO(unitList);

    CollectionModel<UnitDTO> resource = CollectionModel.of(unitDTOList,
        WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(UnitController.class).getUnits())
            .withSelfRel());
    return ResponseEntity.ok(resource);
  }
}
