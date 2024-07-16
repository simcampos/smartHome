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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import smarthome.ddd.IAssembler;
import smarthome.domain.device.Device;
import smarthome.domain.device.DeviceFactoryImpl;
import smarthome.domain.device.IDeviceFactory;
import smarthome.domain.house.House;
import smarthome.domain.house.HouseFactoryImpl;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.repository.IRoomRepository;
import smarthome.domain.repository.ISensorModelRepository;
import smarthome.domain.repository.ISensorRepository;
import smarthome.domain.repository.ISensorTypeRepository;
import smarthome.domain.repository.IUnitRepository;
import smarthome.domain.room.IRoomFactory;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.sensor.ISensorFactory;
import smarthome.domain.sensor.SensorFactoryImpl;
import smarthome.domain.sensor_model.ISensorModelFactory;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.sensor_model.SensorModelFactoryImpl;
import smarthome.domain.sensor_type.ISensorTypeFactory;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.sensor_type.SensorTypeFactoryImpl;
import smarthome.domain.unit.IUnitFactory;
import smarthome.domain.unit.UnitFactoryImpl;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.domain.value_object.SensorModelName;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.domain.value_object.postal_code.IPostalCodeFactory;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.mapper.SensorAssembler;
import smarthome.mapper.SensorModelAssembler;
import smarthome.mapper.SensorTypeAssembler;
import smarthome.service.DeviceServiceImpl;
import smarthome.service.HouseServiceImpl;
import smarthome.service.IDeviceService;
import smarthome.service.IHouseService;
import smarthome.service.IRoomService;
import smarthome.service.ISensorModelService;
import smarthome.service.ISensorService;
import smarthome.service.ISensorTypeService;
import smarthome.service.RoomServiceImpl;
import smarthome.service.SensorModelServiceImpl;
import smarthome.service.SensorServiceImpl;
import smarthome.service.SensorTypeServiceImpl;
import smarthome.service.UnitServiceImpl;
import smarthome.utils.PathEncoder;
import smarthome.utils.dto.SensorDTO;
import smarthome.utils.dto.SensorModelDTO;
import smarthome.utils.dto.SensorTypeDTO;
import smarthome.utils.entry_dto.sensor_entry_dto.SensorGenericEntryDTOImp;

class AddSensorToDeviceControllerTest {

  private IHouseRepository houseRepository;
  private IHouseService houseServiceImpl;
  private IHouseFactory houseFactory;
  private IPostalCodeFactory postalCodeFactory;
  private IRoomRepository roomRepository;
  private IRoomFactory roomFactory;
  private IRoomService roomServiceImpl;
  private IDeviceRepository deviceRepository;
  private IDeviceFactory deviceFactory;
  private IDeviceService deviceServiceImpl;
  private IUnitRepository unitRepository;
  private IUnitFactory unitFactory;
  private ISensorRepository sensorRepository;
  private ISensorFactory sensorFactory;
  private IAssembler<ISensor, SensorDTO> sensorAssembler;
  private ISensorService sensorServiceImpl;
  private ISensorTypeRepository sensorTypeRepository;
  private ISensorTypeFactory sensorTypeFactory;
  private ISensorTypeService sensorTypeServiceImpl;
  private IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler;
  private ISensorModelRepository sensorModelRepository;
  private ISensorModelFactory sensorModelFactory;
  private IAssembler<SensorModel, SensorModelDTO> sensorModelAssembler;
  private ISensorModelService sensorModelServiceImpl;

  @BeforeEach
  void setUp() {
    houseRepository = mock(IHouseRepository.class);
    houseFactory = new HouseFactoryImpl();
    houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
    postalCodeFactory = new PostalCodeFactory();

    roomRepository = mock(IRoomRepository.class);
    roomFactory = new RoomFactoryImpl();
    roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory, houseRepository);

    deviceRepository = mock(IDeviceRepository.class);
    deviceFactory = new DeviceFactoryImpl();
    deviceServiceImpl = new DeviceServiceImpl(deviceRepository, deviceFactory, roomRepository);

    unitRepository = mock(IUnitRepository.class);
    unitFactory = new UnitFactoryImpl();

    sensorRepository = mock(ISensorRepository.class);
    sensorFactory = new SensorFactoryImpl();
    sensorAssembler = new SensorAssembler();

    sensorTypeRepository = mock(ISensorTypeRepository.class);
    sensorTypeFactory = new SensorTypeFactoryImpl();
    sensorTypeAssembler = new SensorTypeAssembler();
    sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory,
        unitRepository);

    System.out.println("SensorTypeRepository: " + sensorTypeRepository); // Ensure this is not null
    sensorServiceImpl = new SensorServiceImpl(sensorRepository, sensorFactory, deviceRepository,
        sensorTypeRepository);
    System.out.println("SensorServiceImpl created"); // Ensure this is reached

    sensorModelRepository = mock(ISensorModelRepository.class);
    sensorModelFactory = new SensorModelFactoryImpl();
    sensorModelAssembler = new SensorModelAssembler();
    sensorModelServiceImpl = new SensorModelServiceImpl(sensorModelRepository, sensorModelFactory);
  }


  private House loadHouse() {
    String street = "Rua Do Isep";
    String doorNumber = "122A";
    String countryCode = "PT";
    String postalCode = "4000-007";
    double latitude = 41.178;
    double longitude = -8.608;
    GPS newGPS = new GPS(latitude, longitude);
    Address newAddress =
        new Address(street, doorNumber, postalCode, countryCode, postalCodeFactory);
    House house = houseServiceImpl.addHouse(newAddress, newGPS);
    when(houseRepository.getTheHouse()).thenReturn(Optional.of(house));
    return house;
  }

  private Room loadRoom() {
    String name = "Quarto da Maria";
    int width = 10;
    int length = 10;
    int height = 10;
    int floor = 2;
    RoomName roomName1 = new RoomName(name);
    Dimension dimension = new Dimension(width, length, height);
    RoomFloor roomFloor = new RoomFloor(floor);
    Room room = roomServiceImpl.addRoom(roomName1, dimension, roomFloor);
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    return room;
  }

  private Device loadDevice(RoomID roomID) {
    String name1 = "Lampada";
    String nameDevice = "Ar Condicionado";
    DeviceName deviceName = new DeviceName(name1);
    DeviceName deviceName2 = new DeviceName(nameDevice);
    DeviceStatus deviceStatus = new DeviceStatus(true);
    DeviceTypeID deviceTypeID = new DeviceTypeID("1");
    Device device1 = deviceServiceImpl.addDevice(roomID, deviceName, deviceTypeID);
    Device device2 = deviceServiceImpl.addDevice(roomID, deviceName2, deviceTypeID);
    when(deviceRepository.ofIdentity(device1.getID())).thenReturn(Optional.of(device1));
    when(deviceRepository.findByRoomID(roomID)).thenReturn(List.of(device1));
    List<Device> devices = deviceServiceImpl.getDevicesByRoomId(roomID);

    return devices.get(0);
  }
  
  /**
   * Test to check if the AddSensorToDeviceController is being created correctly.
   */
  @Test
  void shouldCreateAddSensorToDeviceController() throws InstantiationException {
    // Act
    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(
            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    // Assert
    assertNotNull(addSensorToDeviceController);
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the SensorModelService
   * is null.
   */
  @Test
  void shouldThrowException_whenSensorModelServiceIsNull() {
    // Act
    String expectedMessage = "Sensor model service is required";

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddSensorToDeviceController(
                    null,
                    sensorModelAssembler,
                    sensorTypeServiceImpl,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorServiceImpl));
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the
   * SensorModelAssembler is null.
   */
  @Test
  void shouldThrowException_whenSensorModelAssemblerIsNull() throws InstantiationException {
    // Arrange
    SensorModelAssembler sensorModelAssembler = null;
    String message = "Sensor model assembler is required";
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddSensorToDeviceController(
                    sensorModelServiceImpl,
                    sensorModelAssembler,
                    sensorTypeServiceImpl,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorServiceImpl));
    // Assert
    assertEquals(message, exception.getMessage());
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the SensorTypeService
   * is null.
   */
  @Test
  void shouldThrowException_whenSensorTypeServiceIsNull() throws InstantiationException {
    // Arrange
    SensorTypeServiceImpl sensorTypeServiceImpl = null;

    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddSensorToDeviceController(
                    sensorModelServiceImpl,
                    sensorModelAssembler,
                    sensorTypeServiceImpl,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorServiceImpl));
    // Assert
    assertEquals("Sensor type service is required", exception.getMessage());
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the SensorTypeAssembler
   * is null.
   */
  @Test
  void shouldThrowException_whenSensorTypeAssemblerIsNull() throws InstantiationException {
    // Arrange
    SensorTypeAssembler sensorTypeAssembler = null;
    String message = "Sensor type assembler is required";
    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddSensorToDeviceController(
                    sensorModelServiceImpl,
                    sensorModelAssembler,
                    sensorTypeServiceImpl,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorServiceImpl));
    // Assert
    assertEquals(message, exception.getMessage());
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the SensorAssembler is
   * null.
   */
  @Test
  void shouldThrowException_whenSensorAssemblerIsNull() throws InstantiationException {
    // Arrange
    SensorAssembler sensorAssembler = null;
    String message = "Sensor assembler is required";

    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddSensorToDeviceController(
                    sensorModelServiceImpl,
                    sensorModelAssembler,
                    sensorTypeServiceImpl,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorServiceImpl));
    // Assert
    assertEquals(message, exception.getMessage());
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning null when the SensorService is
   * null.
   */
  @Test
  void shouldThrowException_whenSensorServiceIsNull() throws InstantiationException {
    // Arrange
    SensorServiceImpl sensorServiceImpl = null;
    String message = "Sensor service is required";

    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new AddSensorToDeviceController(
                    sensorModelServiceImpl,
                    sensorModelAssembler,
                    sensorTypeServiceImpl,
                    sensorTypeAssembler,
                    sensorAssembler,
                    sensorServiceImpl));
    // Assert
    assertEquals(message, exception.getMessage());
  }

  /**
   * Test to get available sensor models list when the sensor model repository is not empty.
   */
  @Test
  void shouldGetAvailableSensorModelsList() throws InstantiationException {
    // Arrange
    
    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unitID = new UnitID("Celsius");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);
    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    SensorModel sensorModel =
        sensorModelServiceImpl.createSensorModel(
            new SensorModelName("TemperatureSensor"),
            new ModelPath("smart_home.domain.sensor.temperature_sensor.TemperatureSensor"),
            sensorType.getID());
    when(sensorModelRepository.findBySensorTypeId(sensorType.getID())).thenReturn(
        List.of(sensorModel));
    when(sensorModelRepository.findAll()).thenReturn(List.of(sensorModel));

    // Act
    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(
            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    SensorTypeDTO sensorTypeDTO = sensorTypeAssembler.domainToDTO(sensorType);

    List<SensorModelDTO> sensorModelDTOList =
        addSensorToDeviceController.getSensorModels(sensorTypeDTO);

    List<SensorModel> sensorModels = sensorModelRepository.findAll();
    List<SensorModelDTO> expectedSensorModelDTOList =
        sensorModelAssembler.domainToDTO(sensorModels);

    // Assert
    assertEquals(
        expectedSensorModelDTOList.get(0).modelPath, sensorModelDTOList.get(0).modelPath);
  }

  @Test
  void shouldThrowExceptionWhenSensorModelRepositoryIsEmpty() {
    // Arrange
    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitDescription unitDescription = new UnitDescription("Celsius");
    UnitID unitID = new UnitID("Celsius");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);
    UnitSymbol unitSymbol = new UnitSymbol("C");
    UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);
    unitServiceImpl.addunitType(unitDescription, unitSymbol);
    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    // Act
    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(
            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);
    SensorTypeDTO sensorTypeDTO = sensorTypeAssembler.domainToDTO(sensorType);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> addSensorToDeviceController.getSensorModels(sensorTypeDTO));

    // Assert
    assertEquals("No sensor models found.", exception.getMessage());
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning a list of sensor models when the
   * sensor model list is null.
   */
  @Test
  void shouldThrowExceptionWhenSensorModelListIsNull() {
    // Arrange
    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitDescription unitDescription = new UnitDescription("Celsius");
    UnitID unitID = new UnitID("Celsius");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);
    UnitSymbol unitSymbol = new UnitSymbol("C");
    UnitServiceImpl unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);
    unitServiceImpl.addunitType(unitDescription, unitSymbol);
    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));
    when(sensorModelRepository.findBySensorTypeId(sensorType.getID())).thenReturn(null);

    // Act
    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(
            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);
    SensorTypeDTO sensorTypeDTO = sensorTypeAssembler.domainToDTO(sensorType);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> addSensorToDeviceController.getSensorModels(sensorTypeDTO));

    // Assert
    assertEquals("No sensor models found.", exception.getMessage());
  }

  /**
   * Test if exception is thrown when there are no sensor types in the repository.
   *
   * @throws InstantiationException
   */
  @Test
  void shouldThrowExceptionWhenSensorTypeRepositoryIsEmpty() {
    // Act
    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(
            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class, () -> addSensorToDeviceController.getSensorTypes());

    // Assert
    assertEquals("No sensor types found.", exception.getMessage());
  }

  /**
   * Test to check if the AddSensorToDeviceController is returning a list of sensor types.
   */
  @Test
  void shouldReturnListOfSensorTypes_whenSensorTypeRepositoryIsNotEmpty()
      throws InstantiationException {
    // Arrange
    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unitID = new UnitID("Celsius");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);
    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));
    when(sensorTypeRepository.findAll()).thenReturn(List.of(sensorType));

    //Act
    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(
            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    List<SensorTypeDTO> sensorTypeDTOList = addSensorToDeviceController.getSensorTypes();
    List<SensorTypeDTO> sensorTypeDTO =
        sensorTypeAssembler.domainToDTO(sensorTypeRepository.findAll());

    // Assert
    assertEquals(sensorTypeDTO.get(0).sensorTypeID, sensorTypeDTOList.get(0).sensorTypeID);
  }

  /**
   * Test if exception is thrown when the sensor type doesn't exist in the repository.
   */
  @Test
  void shouldReturnEmptyList_whenThereAreNoSensorTypes() throws InstantiationException {
    
    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unitID = new UnitID("Celsius");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);
    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    TypeDescription nonExistintTypeDescription = new TypeDescription("Humidity");
    UnitID unitID2 = new UnitID("Percent");
    when(unitRepository.containsOfIdentity(unitID2)).thenReturn(true);

    SensorType nonExistintSensorType =
        sensorTypeServiceImpl.createSensorType(nonExistintTypeDescription, unitID2);

    SensorTypeDTO sensorTypeDTO = sensorTypeAssembler.domainToDTO(nonExistintSensorType);

    // Act
    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(
            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> addSensorToDeviceController.getSensorModels(sensorTypeDTO));

    // Assert
    assertEquals(
        "Sensor type with ID " + nonExistintTypeDescription + " not found.",
        exception.getMessage());
  }

  /**
   * Test addSensorToDevice method with valid parameters. Adding temperature sensor.
   */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForTemperatureSensor()
      throws InstantiationException {
    // Arrange
    
    House house = loadHouse();
    Room room = loadRoom();
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unitID = new UnitID("Celsius");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);
    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath = "smarthome.domain.sensor.temperature_sensor.TemperatureSensor";
    modelPath = PathEncoder.encode(modelPath);
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorGenericEntryDTOImp sensorDataGenericDTOImp =
        new SensorGenericEntryDTOImp(
            device.getID().getID(), modelPath, sensorType.getID().getID(), sensorName);

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(
            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /**
   * Test addSensorToDevice method with valid parameters. Adding HumiditySensor sensor.
   */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForHumiditySensor()
      throws InstantiationException {
    // Arrange
    
    House house = loadHouse();
    Room room = loadRoom();
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("Humidity");
    UnitID unitID = new UnitID("Percent");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);
    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath = "smarthome.domain.sensor.humidity_sensor.HumiditySensor";
    modelPath = PathEncoder.encode(modelPath);
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorGenericEntryDTOImp sensorDataGenericDTOImp =
        new SensorGenericEntryDTOImp(
            device.getID().getID(), modelPath, sensorType.getID().getID(), sensorName);

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(
            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /**
   * Test addSensorToDevice method with valid parameters. Adding AveragePowerConsumptionSensor
   * sensor.
   */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForAveragePowerConsumptionSensor()
      throws InstantiationException {
    // Arrange
    
    House house = loadHouse();
    Room room = loadRoom();
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("AveragePowerConsumption");
    UnitID unitID = new UnitID("Watt");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath =
        "smarthome.domain.sensor.average_power_consumption_sensor.AveragePowerConsumptionSensor";
    modelPath = PathEncoder.encode(modelPath);
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorGenericEntryDTOImp sensorDataGenericDTOImp =
        new SensorGenericEntryDTOImp(
            device.getID().getID(), modelPath, sensorType.getID().getID(), sensorName);

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(
            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /**
   * Test addSensorToDevice method with valid parameters. Adding Switch sensor.
   */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForSwitchSensor()
      throws InstantiationException {
    // Arrange
    
    House house = loadHouse();
    Room room = loadRoom();
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("Switch");
    UnitID unitID = new UnitID("Watt");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath = "smarthome.domain.sensor.switch_sensor.SwitchSensor";
    modelPath = PathEncoder.encode(modelPath);

    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorGenericEntryDTOImp sensorDataGenericDTOImp =
        new SensorGenericEntryDTOImp(
            device.getID().getID(), modelPath, sensorType.getID().getID(), sensorName);

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(
            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /**
   * Test addSensorToDevice method with valid parameters. Adding DewPointSensor sensor.
   */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForDewPointSensor()
      throws InstantiationException {
    // Arrange
    
    House house = loadHouse();
    Room room = loadRoom();
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("DewPoint");
    UnitID unitID = new UnitID("Celsius");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath = "smarthome.domain.sensor.dew_point_sensor.DewPointSensor";
    modelPath = PathEncoder.encode(modelPath);
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorGenericEntryDTOImp sensorDataGenericDTOImp =
        new SensorGenericEntryDTOImp(
            device.getID().getID(), modelPath, sensorType.getID().getID(), sensorName);

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(
            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /**
   * Test addSensorToDevice method with valid parameters. Adding Solar Irradiance sensor.
   */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForSolarIrradianceSensor()
      throws InstantiationException {
    // Arrange
    
    House house = loadHouse();
    Room room = loadRoom();
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("SolarIrradiance");
    UnitID unitID = new UnitID("WattBySquareMeter");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath = "smarthome.domain.sensor.solar_irradiance_sensor.SolarIrradianceSensor";
    modelPath = PathEncoder.encode(modelPath);
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorGenericEntryDTOImp sensorDataGenericDTOImp =
        new SensorGenericEntryDTOImp(
            device.getID().getID(), modelPath, sensorType.getID().getID(), sensorName);

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(
            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /**
   * Test addSensorToDevice method with valid parameters. Adding PercentagePositionSensor sensor.
   */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForPercentagePositionSensor()
      throws InstantiationException {
    // Arrange
    
    House house = loadHouse();
    Room room = loadRoom();
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("PercentagePosition");
    UnitID unitID = new UnitID("Percent");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath =
        "smarthome.domain.sensor.percentage_position_sensor.PercentagePositionSensor";
    modelPath = PathEncoder.encode(modelPath);
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorGenericEntryDTOImp sensorDataGenericDTOImp =
        new SensorGenericEntryDTOImp(
            device.getID().getID(), modelPath, sensorType.getID().getID(), sensorName);

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(
            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /**
   * Test addSensorToDevice method with valid parameters. Adding Instance Power Consumption sensor.
   */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForInstantPowerConsumptionSensor()
      throws InstantiationException {
    // Arrange
    
    House house = loadHouse();
    Room room = loadRoom();
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("InstantPowerConsumption");
    UnitID unitID = new UnitID("Watt");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath =
        "smarthome.domain.sensor.instant_power_consumption_sensor.InstantPowerConsumptionSensor";
    modelPath = PathEncoder.encode(modelPath);
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorGenericEntryDTOImp sensorDataGenericDTOImp =
        new SensorGenericEntryDTOImp(
            device.getID().getID(), modelPath, sensorType.getID().getID(), sensorName);

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(
            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /**
   * Test addSensorToDevice method with valid parameters. Adding Percentage Position Sensor.
   */
  @Test
  void shouldAddSensorToDevice_whenParametersAreValidForInstantElectricConsumptionSensor()
      throws InstantiationException {
    // Arrange
    
    House house = loadHouse();
    Room room = loadRoom();
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("PercentagePosition");
    UnitID unitID = new UnitID("Percent");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath =
        "smarthome.domain.sensor.percentage_position_sensor.PercentagePositionSensor";
    modelPath = PathEncoder.encode(modelPath);
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorGenericEntryDTOImp sensorDataGenericDTOImp =
        new SensorGenericEntryDTOImp(
            device.getID().getID(), modelPath, sensorType.getID().getID(), sensorName);

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(
            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));
    // Act
    SensorDTO sensorDTO = addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp);

    // Assert
    assertEquals(sensorDataGenericDTOImp.deviceID, sensorDTO.deviceID);
  }

  /**
   * Test addSensorToDevice method with invalid parameters.
   */
  @Test
  void shouldThrowException_whenParametersAreInvalid() throws InstantiationException {
    // Arrange
    
    House house = loadHouse();
    Room room = loadRoom();
    Device device = loadDevice(room.getID());

    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unitID = new UnitID("Celsius");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorGenericEntryDTOImp sensorDataGenericDTOImp = null;

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(
            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp));

    // Assert
    assertEquals("Sensor data DTO is required", exception.getMessage());
  }

  /**
   * Test addSensorToDevice method with device status is deactivated
   */
  @Test
  void shouldThrowException_whenDeviceStatusIsOff() throws InstantiationException {
    // Arrange
    
    House house = loadHouse();
    Room room = loadRoom();
    Device device = loadDevice(room.getID());

    deviceServiceImpl.deactivateDeviceByID(device.getID());

    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unitID = new UnitID("Celsius");
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);

    String modelPath = "smarthome.domain.sensor.temperature_sensor.TemperatureSensor";
    modelPath = PathEncoder.encode(modelPath);
    String sensorName = "Sensor";

    sensorTypeAssembler.domainToDTO(sensorType);

    SensorGenericEntryDTOImp sensorDataGenericDTOImp =
        new SensorGenericEntryDTOImp(
            device.getID().getID(), modelPath, sensorType.getID().getID(), sensorName);

    AddSensorToDeviceController addSensorToDeviceController =
        new AddSensorToDeviceController(
            sensorModelServiceImpl,
            sensorModelAssembler,
            sensorTypeServiceImpl,
            sensorTypeAssembler,
            sensorAssembler,
            sensorServiceImpl);

    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    // Act
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> addSensorToDeviceController.addSensorToDevice(sensorDataGenericDTOImp));

    // Assert
    assertEquals("Device with ID " + device.getID() + " is deactivated.", exception.getMessage());
  }
}
