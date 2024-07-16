/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.cli;

import java.util.Collections;
import java.util.List;
import smarthome.ddd.IAssembler;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.device.Device;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.RoomID;
import smarthome.mapper.actuator_vo_assembler.ActuatorVOAssemblerImpl;
import smarthome.mapper.actuator_vo_assembler.IActuatorVOAssembler;
import smarthome.service.IActuatorModelService;
import smarthome.service.IActuatorService;
import smarthome.service.IActuatorTypeService;
import smarthome.service.IDeviceService;
import smarthome.service.IRoomService;
import smarthome.utils.Validator;
import smarthome.utils.dto.ActuatorDTO;
import smarthome.utils.dto.ActuatorModelDTO;
import smarthome.utils.dto.ActuatorTypeDTO;
import smarthome.utils.dto.DeviceDTO;
import smarthome.utils.dto.RoomDTO;
import smarthome.utils.entry_dto.actuator_entry_dto.IActuatorEntryDTO;

public class AddActuatorToDeviceController {

  private final IRoomService roomService;
  private final IAssembler<Room, RoomDTO> roomAssembler;
  private final IDeviceService deviceService;
  private final IAssembler<Device, DeviceDTO> deviceAssembler;
  private final IActuatorModelService actuatorModelService;
  private final IAssembler<ActuatorModel, ActuatorModelDTO> actuatorModelAssembler;
  private final IActuatorTypeService actuatorTypeService;
  private final IAssembler<ActuatorType, ActuatorTypeDTO> actuatorTypeAssembler;
  private final IAssembler<IActuator, ActuatorDTO> actuatorAssembler;
  private final IActuatorService actuatorService;


  /**
   * Constructor for the GetListOfRoomsController class.
   *
   * @param roomService          The room service.
   * @param roomAssembler        The room assembler.
   * @param deviceService        The device service.
   * @param actuatorModelService The actuator model service.
   * @param actuatorTypeService  The actuator type service.
   * @param actuatorService      The actuator service.
   */
  public AddActuatorToDeviceController(IRoomService roomService,
      IAssembler<Room, RoomDTO> roomAssembler,
      IDeviceService deviceService,
      IAssembler<Device, DeviceDTO> deviceAssembler,
      IActuatorModelService actuatorModelService,
      IAssembler<ActuatorModel, ActuatorModelDTO> actuatorModelAssembler,
      IActuatorTypeService actuatorTypeService,
      IAssembler<ActuatorType, ActuatorTypeDTO> actuatorTypeAssembler,
      IAssembler<IActuator, ActuatorDTO> actuatorAssembler,
      IActuatorService actuatorService) {
    Validator.validateNotNull(roomService, "Room service");
    Validator.validateNotNull(roomAssembler, "Room assembler");
    Validator.validateNotNull(deviceService, "Device service");
    Validator.validateNotNull(deviceAssembler, "Device assembler");
    Validator.validateNotNull(actuatorModelService, "Actuator model service");
    Validator.validateNotNull(actuatorModelAssembler, "Actuator model assembler");
    Validator.validateNotNull(actuatorTypeService, "Actuator type service");
    Validator.validateNotNull(actuatorTypeAssembler, "Actuator type assembler");
    Validator.validateNotNull(actuatorAssembler, "Actuator assembler");
    Validator.validateNotNull(actuatorService, "Actuator service");

    this.roomService = roomService;
    this.roomAssembler = roomAssembler;
    this.deviceService = deviceService;
    this.deviceAssembler = deviceAssembler;
    this.actuatorModelService = actuatorModelService;
    this.actuatorModelAssembler = actuatorModelAssembler;
    this.actuatorTypeService = actuatorTypeService;
    this.actuatorTypeAssembler = actuatorTypeAssembler;
    this.actuatorAssembler = actuatorAssembler;
    this.actuatorService = actuatorService;

  }

  /**
   * Gets the list of rooms.
   *
   * @return The list of rooms.
   */
  public List<RoomDTO> getRooms() {

    List<Room> listOfRooms = roomService.getAllRooms();
    if (listOfRooms == null || listOfRooms.isEmpty()) {
      return Collections.emptyList();
    }
    List<RoomDTO> listOfRoomsDTO = roomAssembler.domainToDTO(listOfRooms);

    return List.copyOf(listOfRoomsDTO);
  }

  /**
   * Gets all devices from a room.
   *
   * @param roomDTO is the room to get the devices from.
   * @return a list of devices.
   */
  public List<DeviceDTO> getDevicesFromRoom(RoomDTO roomDTO) {
    RoomID roomID = new RoomID(roomDTO.roomId);

    if (roomService.getRoomById(roomID).isEmpty()) {
      throw new IllegalArgumentException("Room with ID " + roomID + " not found.");
    }

    List<Device> devices = deviceService.getDevicesByRoomId(roomID);

    List<DeviceDTO> deviceDTOList = deviceAssembler.domainToDTO(devices);

    return List.copyOf(deviceDTOList);
  }


  /**
   * Gets all actuator types.
   *
   * @return a list of actuator types.
   */
  public List<ActuatorTypeDTO> getActuatorTypes()  {
    List<ActuatorType> actuatorTypeList = actuatorTypeService.getAllActuatorTypes();
    if (actuatorTypeList.isEmpty()) {
      throw new IllegalArgumentException("No actuator types found.");
    }
    List<ActuatorTypeDTO> actuatorTypeDTOList = actuatorTypeAssembler.domainToDTO(
        actuatorTypeList);
    return List.copyOf(actuatorTypeDTOList);
  }

  /**
   * Gets all actuator models.
   *
   * @return a list of actuator models.
   */

  public List<ActuatorModelDTO> getActuatorModels(ActuatorTypeDTO actuatorTypeDTO) {
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorTypeDTO.actuatorTypeID);
    if (actuatorTypeService.getActuatorTypeByID(actuatorTypeID).isEmpty()) {
      throw new IllegalArgumentException("Actuator type with ID " + actuatorTypeID + " not found.");
    }

    List<ActuatorModel> actuatorModels = actuatorModelService.getActuatorModelsByActuatorTypeId(
        actuatorTypeID);
    if (actuatorModels == null || actuatorModels.isEmpty()) {
      throw new IllegalArgumentException("No actuator models found.");
    }
    List<ActuatorModelDTO> actuatorModelDTOList = actuatorModelAssembler.domainToDTO(
        actuatorModels);

    return List.copyOf(actuatorModelDTOList);
  }


  /**
   * Adds an actuator to a device.
   *
   * @param actuatorDataDTOImp is the actuator data DTO.
   * @return the actuator DTO.
   */
  public ActuatorDTO addActuatorToDevice(IActuatorEntryDTO actuatorDataDTOImp) {
    Validator.validateNotNull(actuatorDataDTOImp, "Actuator data DTO");

    IActuatorVOAssembler actuatorVOAssembler = new ActuatorVOAssemblerImpl();
    Object[] actuatorParameters = actuatorVOAssembler.getActuatorParameters(actuatorDataDTOImp);

    IActuator actuator = actuatorService.addActuator(actuatorParameters);

    return actuatorAssembler.domainToDTO(actuator);
  }


}
