/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smarthome.ddd.IAssembler;
import smarthome.domain.actuator.ActuatorFactoryImpl;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.actuator.IActuatorFactory;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.actuator_model.ActuatorModelFactoryImpl;
import smarthome.domain.actuator_model.IActuatorModelFactory;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.actuator_type.ActuatorTypeFactoryImpl;
import smarthome.domain.actuator_type.IActuatorTypeFactory;
import smarthome.domain.device.Device;
import smarthome.domain.device.DeviceFactoryImpl;
import smarthome.domain.device.IDeviceFactory;
import smarthome.domain.house.House;
import smarthome.domain.house.HouseFactoryImpl;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.repository.IActuatorModelRepository;
import smarthome.domain.repository.IActuatorRepository;
import smarthome.domain.repository.IActuatorTypeRepository;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.repository.IRoomRepository;
import smarthome.domain.repository.IUnitRepository;
import smarthome.domain.room.IRoomFactory;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.domain.unit.Unit;
import smarthome.domain.unit.UnitFactoryImpl;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.domain.value_object.postal_code.IPostalCodeFactory;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.mapper.ActuatorAssembler;
import smarthome.mapper.ActuatorModelAssembler;
import smarthome.mapper.ActuatorTypeAssembler;
import smarthome.mapper.DeviceAssembler;
import smarthome.mapper.RoomAssembler;
import smarthome.persistence.mem.DeviceRepository;
import smarthome.persistence.mem.HouseRepository;
import smarthome.persistence.mem.RoomRepository;
import smarthome.service.ActuatorModelServiceImpl;
import smarthome.service.ActuatorServiceImpl;
import smarthome.service.ActuatorTypeServiceImpl;
import smarthome.service.DeviceServiceImpl;
import smarthome.service.HouseServiceImpl;
import smarthome.service.IActuatorModelService;
import smarthome.service.IActuatorService;
import smarthome.service.IActuatorTypeService;
import smarthome.service.IDeviceService;
import smarthome.service.IHouseService;
import smarthome.service.IRoomService;
import smarthome.service.RoomServiceImpl;
import smarthome.utils.PathEncoder;
import smarthome.utils.dto.ActuatorDTO;
import smarthome.utils.dto.ActuatorModelDTO;
import smarthome.utils.dto.ActuatorTypeDTO;
import smarthome.utils.dto.DeviceDTO;
import smarthome.utils.dto.RoomDTO;
import smarthome.utils.entry_dto.actuator_entry_dto.ActuatorGenericDataDTOImp;
import smarthome.utils.entry_dto.actuator_entry_dto.ActuatorWithDecimalLimitsEntryDTOImp;
import smarthome.utils.entry_dto.actuator_entry_dto.ActuatorWithIntegerLimitsEntryDTOImp;
import smarthome.utils.entry_dto.actuator_entry_dto.IActuatorEntryDTO;

class AddActuatorToDeviceControllerTest {

  private IRoomRepository roomRepository;
  private IHouseRepository houseRepository;
  private IUnitRepository unitRepository;
  private IActuatorTypeRepository actuatorTypeRepository;
  private IActuatorRepository actuatorRepository;
  private IActuatorModelRepository actuatorModelRepository;
  private IDeviceRepository deviceRepository;
  private IRoomFactory roomFactory;
  private IAssembler<Room, RoomDTO> roomAssembler;
  private IActuatorFactory actuatorFactory;
  private IActuatorTypeFactory actuatorTypeFactory;
  private IActuatorModelFactory actuatorModelFactory;
  private IDeviceFactory deviceFactory;
  private IAssembler<ActuatorModel, ActuatorModelDTO> actuatorModelAssembler;
  private IRoomService roomServiceImpl;
  private IActuatorModelService actuatorModelServiceImpl;
  private IActuatorTypeService actuatorTypeServiceImpl;
  private IAssembler<ActuatorType, ActuatorTypeDTO> actuatorTypeAssembler;
  private IAssembler<IActuator, ActuatorDTO> actuatorAssembler;
  private IActuatorService actuatorService;
  private IDeviceService deviceServiceImpl;
  private IAssembler<Device, DeviceDTO> deviceAssembler;
  private IPostalCodeFactory postalCodeFactory;
  private IHouseFactory houseFactory;
  private IHouseService houseServiceImpl;

  @BeforeEach
  void setup() {
    roomRepository = new RoomRepository();
    houseRepository = new HouseRepository();
    deviceRepository = new DeviceRepository();
    unitRepository = mock(IUnitRepository.class);
    actuatorTypeRepository = mock(IActuatorTypeRepository.class);
    actuatorRepository = mock(IActuatorRepository.class);
    actuatorModelRepository = mock(IActuatorModelRepository.class);
    roomFactory = new RoomFactoryImpl();
    roomAssembler = new RoomAssembler();
    actuatorFactory = new ActuatorFactoryImpl();
    actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    actuatorModelFactory = new ActuatorModelFactoryImpl();
    deviceFactory = new DeviceFactoryImpl();
    actuatorModelAssembler = new ActuatorModelAssembler();
    roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);
    actuatorModelServiceImpl =
        new ActuatorModelServiceImpl(actuatorModelRepository, actuatorModelFactory);
    actuatorTypeServiceImpl =
        new ActuatorTypeServiceImpl(actuatorTypeRepository, actuatorTypeFactory, unitRepository);
    actuatorTypeAssembler = new ActuatorTypeAssembler();
    actuatorAssembler = new ActuatorAssembler();
    actuatorService =
        new ActuatorServiceImpl(
            actuatorRepository, actuatorFactory, deviceRepository, actuatorTypeRepository);
    deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);
    deviceAssembler = new DeviceAssembler();
    postalCodeFactory = new PostalCodeFactory();
    houseFactory = new HouseFactoryImpl();
    houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
  }

  private void loadHouseAndRoom() {
    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";
    double latitude = 41.178;
    double longitude = -8.608;
    String name = "Quarto da Maria";
    int width = 10;
    int length = 10;
    int height = 10;
    int floor = 2;
    RoomName roomName1 = new RoomName(name);
    GPS newGPS = new GPS(latitude, longitude);
    Address newAddress =
        new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
    House house = houseServiceImpl.addHouse(newAddress, newGPS);
    Dimension dimension = new Dimension(width, length, height);
    RoomFloor roomFloor = new RoomFloor(floor);
    roomServiceImpl.addRoom(roomName1, dimension, roomFloor);
  }

  private Device loadDevice(RoomID roomID) {
    String name1 = "Lampada";
    String nameDevice = "Ar Condicionado";
    DeviceName deviceName = new DeviceName(name1);
    DeviceName deviceName2 = new DeviceName(nameDevice);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");
    deviceServiceImpl.addDevice(roomID, deviceName, deviceTypeID);
    deviceServiceImpl.addDevice(roomID, deviceName2, deviceTypeID);
    List<Device> devices = deviceServiceImpl.getDevicesByRoomId(roomID);
    return devices.get(0);
  }

  /** Test to check if the AddActuatorToDeviceController is being created correctly. */
  @Test
  void shouldThrowExceptionWhenRoomServiceIsNull() throws InstantiationException {
    // Assert

    String expectedMessage = "Room service is required";

    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    null,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    actuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when room assembler is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_whenRoomAssemblerIsNull() throws InstantiationException {
    // Assert

    String expectedMessage = "Room assembler is required";

    // Act
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    roomServiceImpl,
                    null,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    actuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when device service is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_whenDeviceServiceIsNull() throws InstantiationException {
    // Assert

    String expectedMessage = "Device service is required";
    // Act
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    null,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    actuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when device assembler is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_whenDeviceAssemblerIsNull() throws InstantiationException {
    // Assert

    String expectedMessage = "Device assembler is required";
    // Act
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    null,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    actuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when actuator model service is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_whenActuatorModelServiceIsNull() throws InstantiationException {
    // Assert

    String expectedMessage = "Actuator model service is required";
    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    null,
                    actuatorModelAssembler,
                    actuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when actuator model assembler is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_whenActuatorModelAssemblerIsNull() throws InstantiationException {
    // Assert

    String expectedMessage = "Actuator model assembler is required";

    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    null,
                    actuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /** Should throw exception when actuator type service is null. */
  @Test
  void shouldThrowException_whenConfigurationServiceIsNull() {
    // Act
    try {
      new AddActuatorToDeviceController(
          roomServiceImpl,
          roomAssembler,
          deviceServiceImpl,
          deviceAssembler,
          actuatorModelServiceImpl,
          actuatorModelAssembler,
          actuatorTypeServiceImpl,
          actuatorTypeAssembler,
          actuatorAssembler,
          actuatorService);
    } catch (IllegalArgumentException e) {
      assertEquals("Please enter a valid actuator type service.", e.getMessage());
    }
  }

  /**
   * Should throw exception when actuator type service is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_whenActuatorTypeServiceIsNull() throws InstantiationException {
    // Assert

    String expectedMessage = "Actuator type service is required";

    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    null,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    actuatorService));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when actuator type assembler is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_whenActuatorTypeAssemblerIsNull() throws InstantiationException {
    // Assert

    String expectedMessage = "Actuator type assembler is required";
    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    actuatorTypeServiceImpl,
                    null,
                    actuatorAssembler,
                    actuatorService));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when actuator assembler is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_whenActuatorAssemblerIsNull() throws InstantiationException {
    // Assert

    String expectedMessage = "Actuator assembler is required";
    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    actuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    null,
                    actuatorService));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when actuator service is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_whenActuatorServiceIsNull() throws InstantiationException {
    // Assert

    String expectedMessage = "Actuator service is required";
    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddActuatorToDeviceController(
                    roomServiceImpl,
                    roomAssembler,
                    deviceServiceImpl,
                    deviceAssembler,
                    actuatorModelServiceImpl,
                    actuatorModelAssembler,
                    actuatorTypeServiceImpl,
                    actuatorTypeAssembler,
                    actuatorAssembler,
                    null));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when room service is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldReturnEmptyList_whenThereAreNoRooms() throws InstantiationException {
    // Arrange
    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            actuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);

    // Act
    List<RoomDTO> result = controller.getRooms();

    // Assert
    assertTrue(result.isEmpty());
  }

  /**
   * Should get rooms from house when given valid house ID.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldGetRoomsFromHouse_WhenGivenValidHouseID() throws InstantiationException {
    // Arrange
    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            actuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);
    loadHouseAndRoom();
    List<Room> rooms = roomRepository.findAll();
    List<RoomDTO> expectedRoomsDTOList = roomAssembler.domainToDTO(rooms);
    String expectedRoomDTOName = expectedRoomsDTOList.get(0).roomName;
    String expectedRoomDTOID = expectedRoomsDTOList.get(0).roomId;
    List<String> expectedList = List.of(expectedRoomDTOName, expectedRoomDTOID);

    // Act
    List<RoomDTO> roomsDTOList = controller.getRooms();
    String actualRoomDTOName = roomsDTOList.get(0).roomName;
    String actualRoomDTOID = roomsDTOList.get(0).roomId;
    List<String> actualList = List.of(actualRoomDTOName, actualRoomDTOID);

    // Assert
    assertEquals(expectedList, actualList);
  }

  /** Should throw exception when room ID does not exist in repository. */
  @Test
  void shouldThrowException_WhenRoomIDDoesNotExistInRepository() throws InstantiationException {
    // Arrange

    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            actuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);

    String roomID = "123";
    RoomID nonExistentRoomID = new RoomID(roomID);

    String roomName = "Quarto da Maria";
    String dimension = "10x10x10";
    int floor = 2;
    RoomDTO roomDTO = new RoomDTO(roomName, dimension, floor, nonExistentRoomID.toString());

    String expectedMessage = "Room not found for ID: " + nonExistentRoomID;

    // Act & Assert
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> controller.getDevicesFromRoom(roomDTO));

    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should get devices from room when given valid room ID.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldGetDevicesFromRoom_WhenParametersAreValid() throws InstantiationException {
    // Arrange
    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            actuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);
    loadHouseAndRoom();
    List<Room> rooms = roomRepository.findAll();
    RoomID roomID = rooms.get(0).getID();
    loadDevice(roomID);
    List<Device> devices = deviceServiceImpl.getDevicesByRoomId(roomID);
    List<DeviceDTO> deviceDTOListExpected = deviceAssembler.domainToDTO(devices);
    List<RoomDTO> roomsDTOList = controller.getRooms();
    RoomDTO roomDTO = roomsDTOList.get(0);
    String expectedDeviceDTOID = deviceDTOListExpected.get(0).deviceID;
    String expectedDeviceDTOName = deviceDTOListExpected.get(0).deviceName;
    List<String> expectedDeviceDTOList = List.of(expectedDeviceDTOID, expectedDeviceDTOName);
    // Act
    List<DeviceDTO> devicesDTOList = controller.getDevicesFromRoom(roomDTO);

    String actualDeviceDTOID = devicesDTOList.get(0).deviceID;
    String actualDeviceDTOName = devicesDTOList.get(0).deviceName;
    List<String> actualDeviceDTOList = List.of(actualDeviceDTOID, actualDeviceDTOName);
    // Assert
    assertEquals(expectedDeviceDTOList, actualDeviceDTOList);
  }

  /**
   * Should get available actuator types list.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldGetAvailableActuatorTypesList() throws InstantiationException {
    // Arrange

    TypeDescription typeDescription = new TypeDescription("BlindRoller");
    UnitID unit = new UnitID("Percent");
    ActuatorType type = actuatorTypeFactory.createActuatorType(typeDescription, unit);
    List<ActuatorType> list = List.of(type);

    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            actuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);

    when(actuatorTypeRepository.findAll()).thenReturn(list);
    List<ActuatorTypeDTO> expectedActuatorTypeDTOList = actuatorTypeAssembler.domainToDTO(list);

    // Act
    List<ActuatorTypeDTO> ActuatorTypeDTOList = controller.getActuatorTypes();

    // Assert
    assertEquals(
        expectedActuatorTypeDTOList.get(0).actuatorTypeID,
        ActuatorTypeDTOList.get(0).actuatorTypeID);
  }

  /**
   * Should get available actuator models list.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldGetAvailableActuatorModelsList() throws InstantiationException {
    // Arrange

    String description = "BlindRoller";
    TypeDescription typeDescription = new TypeDescription(description);
    UnitID unit = new UnitID("Percent");
    ActuatorType type = actuatorTypeFactory.createActuatorType(typeDescription, unit);
    List<ActuatorType> list = List.of(type);

    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            actuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);

    ActuatorTypeDTO typeDto = actuatorTypeAssembler.domainToDTO(type);

    ModelPath path = new ModelPath("modelPath");
    ActuatorModelName name = new ActuatorModelName("name");
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(description);
    ActuatorModel model = actuatorModelFactory.createActuatorModel(path, name, actuatorTypeID);
    List<ActuatorModel> listModel = List.of(model);

    when(actuatorModelRepository.findBy_actuatorTypeID(actuatorTypeID)).thenReturn(listModel);
    List<ActuatorModelDTO> expectedActuatorModelDTOList =
        actuatorModelAssembler.domainToDTO(listModel);
    when(actuatorTypeRepository.ofIdentity(actuatorTypeID)).thenReturn(Optional.of(type));

    // Act
    List<ActuatorModelDTO> ActuatorModelDTOList = controller.getActuatorModels(typeDto);

    // Assert
    assertEquals(
        expectedActuatorModelDTOList.get(0).actuatorModelPath,
        ActuatorModelDTOList.get(0).actuatorModelPath);
  }

  /**
   * Should throw exception when actuator type repository is empty.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowException_WhenActuatorTypeRepositoryIsEmpty() throws InstantiationException {

    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            actuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> controller.getActuatorTypes());

    // Assert
    assertEquals("No actuator types found.", exception.getMessage());
  }

  /**
   * should add blind roller actuator to device when parameters are valid.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldAddActuatorToDevice_whenParametersAreValidBlindRoller() throws InstantiationException {
    // Arrange
    loadHouseAndRoom();
    List<Room> rooms = roomRepository.findAll();
    RoomID roomID = rooms.get(0).getID();
    Device device = loadDevice(roomID);

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.blind_roller_actuator.BlindRollerActuator";
    actuatorModelPath = PathEncoder.encode(actuatorModelPath);
    String actuatorName = "BlindRoller";

    /* Create Unit */
    String unit = "Percent";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("-");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "BlindRoller";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType =
        actuatorTypeFactory.createActuatorType(
            new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorEntryDTO actuatorDataDTO =
        new ActuatorGenericDataDTOImp(deviceID, actuatorModelPath, actuatorName, actuatorTypeID);
    when(actuatorTypeRepository.ofIdentity(actuatorType.getID())).thenReturn(Optional.of(actuatorType));

    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            actuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);
    // Act
    ActuatorDTO actuatorDTO = controller.addActuatorToDevice(actuatorDataDTO);

    // Assert
    assertNotNull(actuatorDTO);
  }

  /**
   * Should add set integer actuator to device when parameters are valid.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldAddActuatorToDevice_whenParametersAreValidSetIntegerActuator(){
  // Arrange
  loadHouseAndRoom();
  List<Room> rooms = roomRepository.findAll();
  RoomID roomID = rooms.get(0).getID();
  Device device = loadDevice(roomID);

  String deviceID = device.getID().getID();
  String actuatorModelPath = "smarthome.domain.actuator.set_integer_actuator.SetIntegerActuator";
  actuatorModelPath = PathEncoder.encode(actuatorModelPath);
  String actuatorName = "SetInteger";
  String minLimit = "0";
  String maxLimit = "100";

  /* Create Unit */
  String unit = "Integer";
  UnitDescription unitDescription = new UnitDescription(unit);
  UnitSymbol unitSymbol = new UnitSymbol("-");
  UnitFactoryImpl unitFactory = new UnitFactoryImpl();
  Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

  /* Create ActuatorType */
  String strActuatorType = "SetInteger";
  ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
  ActuatorType actuatorType =
      actuatorTypeFactory.createActuatorType(
          new TypeDescription(strActuatorType), actuatorUnit.getID());
  String actuatorTypeID = actuatorType.getID().toString();

  /* Create ActuatorDataDTO */
  IActuatorEntryDTO actuatorDataDTO = new ActuatorWithIntegerLimitsEntryDTOImp(deviceID,
      actuatorModelPath, actuatorName, actuatorTypeID, minLimit, maxLimit);
  when(actuatorTypeRepository.ofIdentity(actuatorType.getID())).thenReturn(Optional.of(actuatorType));

  AddActuatorToDeviceController controller =
      new AddActuatorToDeviceController(
          roomServiceImpl,
          roomAssembler,
          deviceServiceImpl,
          deviceAssembler,
          actuatorModelServiceImpl,
          actuatorModelAssembler,
          actuatorTypeServiceImpl,
          actuatorTypeAssembler,
          actuatorAssembler,
          actuatorService);
  // Act
  ActuatorDTO actuatorDTO = controller.addActuatorToDevice(actuatorDataDTO);

  // Assert
  assertNotNull(actuatorDTO);
}

  /**
   * Should add set decimal actuator to device when parameters are valid.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldAddActuatorToDevice_whenParametersAreValidSetDecimalActuator() {
    // Arrange
    loadHouseAndRoom();
    List<Room> rooms = roomRepository.findAll();
    RoomID roomID = rooms.get(0).getID();
    Device device = loadDevice(roomID);

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.set_decimal_actuator.SetDecimalActuator";
    actuatorModelPath = PathEncoder.encode(actuatorModelPath);
    String actuatorName = "SetDecimal";
    double minLimit = 0;
    double maxLimit = 100;

    /* Create Unit */
    String unit = "Decimal";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("-");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "SetDecimal";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType =
        actuatorTypeFactory.createActuatorType(
            new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorEntryDTO actuatorDataDTO =
        new ActuatorWithDecimalLimitsEntryDTOImp(
            deviceID, actuatorModelPath, actuatorName, actuatorTypeID, minLimit, maxLimit);
    when(actuatorTypeRepository.ofIdentity(actuatorType.getID())).thenReturn(Optional.of(actuatorType));

    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            actuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);
    // Act
    ActuatorDTO actuatorDTO = controller.addActuatorToDevice(actuatorDataDTO);

    // Assert
    assertNotNull(actuatorDTO);
  }

  /**
   * Should add set integer actuator to device when parameters are valid.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldAddActuatorToDevice_whenParametersAreValidSwitchActuator() {
    // Arrange
    loadHouseAndRoom();
    List<Room> rooms = roomRepository.findAll();
    RoomID roomID = rooms.get(0).getID();
    Device device = loadDevice(roomID);

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.switch_actuator.SwitchActuator";
    actuatorModelPath = PathEncoder.encode(actuatorModelPath);
    String actuatorName = "Switch";

    /* Create Unit */
    String unit = "Status";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("-");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "Switch";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType =
        actuatorTypeFactory.createActuatorType(
            new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorEntryDTO actuatorDataDTO =
        new ActuatorGenericDataDTOImp(deviceID, actuatorModelPath, actuatorName, actuatorTypeID);
    when(actuatorTypeRepository.ofIdentity(actuatorType.getID())).thenReturn(Optional.of(actuatorType));

    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            actuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);
    // Act
    ActuatorDTO actuatorDTO = controller.addActuatorToDevice(actuatorDataDTO);

    // Assert
    assertNotNull(actuatorDTO);
  }

  /**
   * Should throw exception when actuator data DTO is null.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenActuatorDataDTOIsNull() {
    // Arrange
    loadHouseAndRoom();
    List<Room> rooms = roomRepository.findAll();
    RoomID roomID = rooms.get(0).getID();
    Device device = loadDevice(roomID);

    /* Create ActuatorDataDTO */
    IActuatorEntryDTO actuatorDataDTO =
        null;

    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            actuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);

    String expectedMessage = "Actuator data DTO is required";
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> controller.addActuatorToDevice(actuatorDataDTO));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should throw exception when device status is deactivated.
   *
   * @throws InstantiationException exception
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDeviceStatusIsDeactivated() {
    // Arrange
    loadHouseAndRoom();
    List<Room> rooms = roomRepository.findAll();
    RoomID roomID = rooms.get(0).getID();
    Device device = loadDevice(roomID);

    deviceServiceImpl.deactivateDeviceByID(device.getID());

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.blind_roller_actuator.BlindRollerActuator";
    actuatorModelPath = PathEncoder.encode(actuatorModelPath);
    String actuatorName = "BlindRoller";

    /* Create Unit */
    String unit = "Percent";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("-");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "BlindRoller";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType =
        actuatorTypeFactory.createActuatorType(
            new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorEntryDTO actuatorDataDTO =
        new ActuatorGenericDataDTOImp(deviceID, actuatorModelPath, actuatorName, actuatorTypeID);
    when(actuatorTypeRepository.ofIdentity(actuatorType.getID())).thenReturn(Optional.of(actuatorType));

    AddActuatorToDeviceController controller =
        new AddActuatorToDeviceController(
            roomServiceImpl,
            roomAssembler,
            deviceServiceImpl,
            deviceAssembler,
            actuatorModelServiceImpl,
            actuatorModelAssembler,
            actuatorTypeServiceImpl,
            actuatorTypeAssembler,
            actuatorAssembler,
            actuatorService);

    String expectedMessage = "Device with ID " + device.getID().getID() + " is deactivated.";
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> controller.addActuatorToDevice(actuatorDataDTO));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }
}
