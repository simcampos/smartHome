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
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smarthome.ddd.IAssembler;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.service.IRoomService;
import smarthome.utils.dto.RoomDTO;
import smarthome.utils.entry_dto.DeviceEntryDTO;
import smarthome.utils.entry_dto.RoomEntryDTO;

@RestController
@RequestMapping("/rooms")
public class RoomController {

  private final IRoomService roomService;
  private final IAssembler<Room, RoomDTO> roomAssembler;

  /**
   * Constructor
   */
  @Autowired
  public RoomController(IRoomService roomService,
      IAssembler<Room, RoomDTO> roomAssembler) {
    this.roomAssembler = roomAssembler;
    this.roomService = roomService;
  }

  /**
   * Adds a new room to the house with the provided house ID.
   *
   * @param data
   * @return The room that was added.
   */
  @PostMapping
  public ResponseEntity<EntityModel<RoomDTO>> createRoom(
      @Valid @RequestBody RoomEntryDTO data) {
    RoomName name = new RoomName(data.name);
    RoomFloor floor = new RoomFloor(data.floor);
    Dimension dimension = new Dimension(data.width, data.length, data.height);
    Room room = roomService.addRoom(name, dimension, floor);
    RoomDTO roomDTO = roomAssembler.domainToDTO(room);

    Link selfLink = linkTo(methodOn(RoomController.class).getRoomById(roomDTO.roomId))
        .withRel("self");
    Link deviceLink = linkTo(methodOn(DeviceController.class).addDevice(new DeviceEntryDTO()))
        .withRel("device")
        .withType("method=POST");

    EntityModel<RoomDTO> response = EntityModel.of(roomDTO, selfLink, deviceLink);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  /**
   * Get all rooms
   *
   * @return a list of all rooms
   */
  @GetMapping
  public ResponseEntity<CollectionModel<List<RoomDTO>>> getAllRooms() {
    List<Room> rooms = roomService.getAllRooms();
    List<RoomDTO> roomDTOs = roomAssembler.domainToDTO(rooms);

    for (RoomDTO roomDTO : roomDTOs) {
      Link selfLink = linkTo(
          methodOn(RoomController.class).getRoomById(roomDTO.roomId)).withSelfRel()
          .withRel("self")
          .withTitle("Get room")
          .withType("GET");
      Link addDevice = linkTo(methodOn(DeviceController.class).addDevice(new DeviceEntryDTO()))
          .withRel("add-device")
          .withTitle("Add a device")
          .withType("POST");

      roomDTO.add(selfLink, addDevice);
    }

    CollectionModel<List<RoomDTO>> response = CollectionModel.of(List.of(roomDTOs));
    return ResponseEntity.ok(response);
  }

  /**
   * Get a room by ID
   *
   * @param idStr is the room ID
   * @return the room with the given ID
   */
  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<RoomDTO>> getRoomById(
      @PathVariable("id") String idStr) {
    RoomID id = new RoomID(idStr);
    Optional<Room> room = roomService.getRoomById(id);
    if (room.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } else {
      RoomDTO roomDTO = roomAssembler.domainToDTO(room.get());
      Link selfLink = linkTo(methodOn(RoomController.class).getRoomById(idStr))
          .withRel("self")
          .withType("GET")
          .withTitle("Get room");
      Link getAllDevices = linkTo(methodOn(DeviceController.class).listDevices(idStr, null))
          .withRel("room-devices")
          .withTitle("Get all room devices")
          .withType("GET");
      Link addDevice = linkTo(methodOn(DeviceController.class).addDevice(new DeviceEntryDTO()))
          .withRel("add-device")
          .withTitle("Add a device")
          .withType("POST");

      roomDTO.add(selfLink, getAllDevices, addDevice);
      EntityModel<RoomDTO> response = EntityModel.of(roomDTO);
      return ResponseEntity.ok(response);
    }
  }
}
