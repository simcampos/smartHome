/*
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */

package smarthome.controller.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import smarthome.domain.actuator.IActuator;
import smarthome.domain.actuator.IActuatorFactory;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.actuator_type.ActuatorTypeFactoryImpl;
import smarthome.domain.device.Device;
import smarthome.domain.device.DeviceFactoryImpl;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.device_type.DeviceTypeFactoryImpl;
import smarthome.domain.house.House;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.log.Log;
import smarthome.domain.log.LogFactoryImpl;
import smarthome.domain.repository.IActuatorRepository;
import smarthome.domain.repository.IActuatorTypeRepository;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.ILogRepository;
import smarthome.domain.repository.ISensorRepository;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.sensor.SensorFactoryImpl;
import smarthome.domain.unit.Unit;
import smarthome.domain.unit.UnitFactoryImpl;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.ReadingValue;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.service.IActuatorService;
import smarthome.service.ILogService;
import smarthome.service.SensorServiceImpl;
import smarthome.utils.PathEncoder;
import smarthome.utils.entry_dto.actuator_entry_dto.ActuatorGenericDataDTOImp;
import smarthome.utils.entry_dto.actuator_entry_dto.ActuatorWithIntegerLimitsEntryDTOImp;
import smarthome.utils.entry_dto.actuator_entry_dto.ActuatorValueEntryDTO;
import smarthome.utils.entry_dto.actuator_entry_dto.IActuatorEntryDTO;

@SpringBootTest
@AutoConfigureMockMvc
class ActuatorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private IHouseFactory houseFactory;

  @Autowired
  private IActuatorFactory actuatorFactory;

  @MockBean
  private IDeviceRepository deviceRepository;

  @MockBean
  private IActuatorTypeRepository actuatorTypeRepository;

  @MockBean
  private IActuatorRepository actuatorRepository;

  @MockBean
  private ILogRepository logRepository;

  @Autowired
  private ILogService logService;

  @Autowired
  private IActuatorService actuatorService;
  @MockBean
  private ISensorRepository sensorRepository;
  @Autowired
  private SensorServiceImpl sensorServiceImpl;


  House setupHouse() {
    // Arrange
    String street = "Rua de Sao Bento";
    String doorNumber = "123";
    String postalCode = "1200-109";
    String countryCode = "PT";
    double latitude = 38.7143;
    double longitude = -9.1459;
    Address address = new Address(street, doorNumber, postalCode, countryCode,
        new PostalCodeFactory());
    GPS gps = new GPS(latitude, longitude);
    return houseFactory.createHouse(address, gps);
  }

  Room setupRoom() {
    // Arrange
    House house = setupHouse();

    HouseID houseID = house.getID();
    String strRoomName = "Living Room";
    RoomName roomName = new RoomName(strRoomName);
    int floor = 1;
    RoomFloor roomFloor = new RoomFloor(floor);
    Dimension dimension = new Dimension(10, 10, 10);

    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    return roomFactory.createRoom(houseID, roomName, dimension, roomFloor);
  }

  Device setupDevice() {
    // Arrange
    Room room = setupRoom();

    RoomID roomID = room.getID();
    String strDeviceName = "LivingRoomLight1";
    DeviceName deviceName = new DeviceName(strDeviceName);

    String strTypeDescription = "Light";
    TypeDescription typeDescription = new TypeDescription(strTypeDescription);
    DeviceTypeFactoryImpl deviceTypeFactory = new DeviceTypeFactoryImpl();
    DeviceType deviceType = deviceTypeFactory.createDeviceType(typeDescription);
    DeviceTypeID deviceTypeID = deviceType.getID();

    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    return deviceFactory.createDevice(roomID, deviceName, deviceTypeID);
  }

  IActuator setupGenericActuator(ActuatorGenericDataDTOImp actuatorDataDTO) {
    DeviceID deviceID = new DeviceID(actuatorDataDTO.deviceID);
    ModelPath modelPath = new ModelPath(actuatorDataDTO.actuatorModelPath);
    ActuatorName actuatorName = new ActuatorName(actuatorDataDTO.actuatorName);
    ActuatorTypeID actuatorTypeID = new ActuatorTypeID(actuatorDataDTO.actuatorTypeID);

    return actuatorFactory.create(deviceID, modelPath, actuatorTypeID, actuatorName);
  }

  /**
   * Verify that a generic Actuator is correctly added to the Device
   */
  @Test
  void shouldReturnActuatorDTO_whenGenericActuatorIsAddedToDevice() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.switch_actuator.SwitchActuator";
    actuatorModelPath = PathEncoder.encode(actuatorModelPath);
    String actuatorName = "Light";

    /* Create Unit */
    String unit = "On/Off";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("I/O");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "Switch";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());

    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorEntryDTO actuatorDataDTO = new ActuatorGenericDataDTOImp(deviceID, actuatorModelPath,
        actuatorTypeID, actuatorName);

    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));
    when(actuatorTypeRepository.ofIdentity(actuatorType.getID())).thenReturn(
        Optional.of(actuatorType));

    // Act & Assert
    MvcResult result = mockMvc.perform(post("/actuators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isCreated())
        .andReturn();

    // Assert
    String content = result.getResponse().getContentAsString();
    assertTrue(content.contains(deviceID));
    assertTrue(content.contains(actuatorName));
  }

  /**
   * Verify that a SetIntegerActuator is correctly added to the Device
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnActuatorDTO_whenSetIntegerActuatorIsAddedToDevice() throws Exception {
    // Arrange
    Device device = setupDevice();

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
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorEntryDTO actuatorDataDTO = new ActuatorWithIntegerLimitsEntryDTOImp(deviceID,
        actuatorModelPath, actuatorTypeID, actuatorName, minLimit, maxLimit);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));
    when(actuatorTypeRepository.ofIdentity(actuatorType.getID())).thenReturn(
        Optional.of(actuatorType));

    // Act & Assert
    MvcResult result = mockMvc.perform(post("/actuators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isCreated())
        .andReturn();

    // Assert
    String content = result.getResponse().getContentAsString();
    assertTrue(content.contains(deviceID));
    assertTrue(content.contains(actuatorName));
  }

  /**
   * Verify that an Actuator is not added to the Device when the DeviceID is null
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_whenDeviceIDIsNull() throws Exception {
    // Arrange
    String actuatorModelPath = "smarthome.domain.actuator.switch_actuator.SwitchActuator";
    String actuatorName = "Light";

    /* Create Unit */
    String unit = "On/Off";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("I/O");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "Switch";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorEntryDTO actuatorDataDTO = new ActuatorGenericDataDTOImp(null, actuatorModelPath,
        actuatorTypeID, actuatorName);

    // Act & Assert
    mockMvc.perform(post("/actuators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that an Actuator is not added to the Device when the ActuatorModelPath is null
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_whenActuatorModelPathIsNull() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorName = "Light";

    /* Create Unit */
    String unit = "On/Off";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("I/O");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "Switch";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorEntryDTO actuatorDataDTO = new ActuatorGenericDataDTOImp(deviceID, null,
        actuatorTypeID,
        actuatorName);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/actuators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that an Actuator is not added to the Device when the ActuatorName is null
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_whenActuatorNameIsNull() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.switch_actuator.SwitchActuator";

    /* Create Unit */
    String unit = "On/Off";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("I/O");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "Switch";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorEntryDTO actuatorDataDTO = new ActuatorGenericDataDTOImp(deviceID, actuatorModelPath,
        actuatorTypeID, null);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/actuators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that an Actuator is not added to the Device when the ActuatorTypeID is null
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_whenActuatorTypeIDIsNull() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.switch_actuator.SwitchActuator";
    String actuatorName = "Light";

    /* Create ActuatorDataDTO */
    IActuatorEntryDTO actuatorDataDTO = new ActuatorGenericDataDTOImp(deviceID, actuatorModelPath,
        null, actuatorName);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/actuators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that an Actuator is not added to the Device when the ActuatorTypeID is invalid
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_whenActuatorTypeIDIsInvalid() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.switch_actuator.SwitchActuator";
    String actuatorName = "Light";

    /* Create ActuatorDataDTO */
    IActuatorEntryDTO actuatorDataDTO = new ActuatorGenericDataDTOImp(deviceID, actuatorModelPath,
        "InvalidActuatorTypeID", actuatorName);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/actuators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that a Set Integer Actuator is not added to the Device when the lower limit is null
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_whenMinLimitIsNull() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.set_integer_actuator.SetIntegerActuator";
    String actuatorName = "SetInteger";
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
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorEntryDTO actuatorDataDTO = new ActuatorWithIntegerLimitsEntryDTOImp(deviceID,
        actuatorModelPath, actuatorTypeID, actuatorName, null, maxLimit);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/actuators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that a Set Integer Actuator is not added to the Device when the upper limit is null
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_whenMaxLimitIsNull() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.set_integer_actuator.SetIntegerActuator";
    String actuatorName = "SetInteger";
    String minLimit = "0";

    /* Create Unit */
    String unit = "Integer";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("-");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "SetInteger";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorEntryDTO actuatorDataDTO = new ActuatorWithIntegerLimitsEntryDTOImp(deviceID,
        actuatorModelPath, actuatorTypeID, actuatorName, minLimit, null);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/actuators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that a Set Integer Actuator is not added to the Device when the lower limit is invalid
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_whenMinLimitIsInvalid() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.set_integer_actuator.SetIntegerActuator";
    String actuatorName = "SetInteger";
    String minLimit = "Invalid";
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
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorEntryDTO actuatorDataDTO = new ActuatorWithIntegerLimitsEntryDTOImp(deviceID,
        actuatorModelPath, actuatorTypeID, actuatorName, minLimit, maxLimit);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/actuators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that a Set Integer Actuator is not added to the Device when the upper limit is invalid
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_whenMaxLimitIsInvalid() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceID = device.getID().getID();
    String actuatorModelPath = "smarthome.domain.actuator.set_integer_actuator.SetIntegerActuator";
    String actuatorName = "SetInteger";
    String minLimit = "0";
    String maxLimit = "Invalid";

    /* Create Unit */
    String unit = "Integer";
    UnitDescription unitDescription = new UnitDescription(unit);
    UnitSymbol unitSymbol = new UnitSymbol("-");
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit actuatorUnit = unitFactory.createUnit(unitDescription, unitSymbol);

    /* Create ActuatorType */
    String strActuatorType = "SetInteger";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType), actuatorUnit.getID());
    String actuatorTypeID = actuatorType.getID().toString();

    /* Create ActuatorDataDTO */
    IActuatorEntryDTO actuatorDataDTO = new ActuatorWithIntegerLimitsEntryDTOImp(deviceID,
        actuatorModelPath, actuatorTypeID, actuatorName, minLimit, maxLimit);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/actuators")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Should return an actuator by ID
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnActuatorByID() throws Exception {
    //Arrange
    Device device = setupDevice();

    String deviceIDStr = device.getID().toString();
    String actuatorModelPath = "smarthome.domain.actuator.blind_roller_actuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";

    /* create unit */
    UnitDescription unit = new UnitDescription("Percent");
    UnitSymbol strUnitSymbol = new UnitSymbol("%");
    Unit actuatorUnit = new UnitFactoryImpl().createUnit(unit, strUnitSymbol);

    /* create sensor type */
    String strActuatorType = "BlindRoller";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();

    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType),
        actuatorUnit.getID());

    String actuatorTypeIDStr = actuatorType.getID().toString();

    /* create dataDTO */
    ActuatorGenericDataDTOImp actuatorDataDTO = new ActuatorGenericDataDTOImp(deviceIDStr,
        actuatorModelPath,
        actuatorName, actuatorTypeIDStr);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));
    when(actuatorTypeRepository.ofIdentity(actuatorType.getID())).thenReturn(
        Optional.of(actuatorType));

    IActuator actuator = setupGenericActuator(actuatorDataDTO);
    when(actuatorRepository.ofIdentity(actuator.getID())).thenReturn(Optional.of(actuator));

    //Act + Assert
    MvcResult result = mockMvc.perform(get("/actuators/" + actuator.getID().getID()))
        .andExpect(status().isOk())
        .andReturn();

    // Assert
    String content = result.getResponse().getContentAsString();
    assertTrue(content.contains(actuator.getID().getID()));
  }

  /**
   * Should return an empty list when no actuator is found
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnEmptyList_whenNoActuatorIsFound() throws Exception {
    //Act + Assert
    mockMvc.perform(get("/actuators/InvalidID"))
        .andExpect(status().isNotFound());
  }

  /**
   * Should return all actuators when actuators are found
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnAllActuators_whenActuatorsAreFound() throws Exception {
    //Arrange
    Device device = setupDevice();

    String deviceIDStr = device.getID().toString();
    String actuatorModelPath = "smarthome.domain.actuator.blind_roller_actuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";

    /* create unit */
    UnitDescription unit = new UnitDescription("Percent");
    UnitSymbol strUnitSymbol = new UnitSymbol("%");
    Unit actuatorUnit = new UnitFactoryImpl().createUnit(unit, strUnitSymbol);

    /* create sensor type */
    String strActuatorType = "BlindRoller";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();

    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType),
        actuatorUnit.getID());

    String actuatorTypeIDStr = actuatorType.getID().toString();

    /* create dataDTO */
    ActuatorGenericDataDTOImp actuatorDataDTO = new ActuatorGenericDataDTOImp(deviceIDStr,
        actuatorModelPath,
        actuatorName, actuatorTypeIDStr);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));
    when(actuatorTypeRepository.ofIdentity(actuatorType.getID())).thenReturn(
        Optional.of(actuatorType));

    IActuator actuator = setupGenericActuator(actuatorDataDTO);
    when(actuatorRepository.findAll()).thenReturn(List.of(actuator));

    //Act + Assert
    MvcResult result = mockMvc.perform(get("/actuators"))
        .andExpect(status().isOk())
        .andReturn();

    // Assert
    String content = result.getResponse().getContentAsString();
    assertTrue(content.contains(actuator.getID().getID()));
  }

  @Test
  void shouldReturnEmptyList_whenNoActuatorsAreFound() throws Exception {
    //Act + Assert
    mockMvc.perform(get("/actuators"))
        .andExpect(status().isOk());
  }

  /**
   * Should return a list of actuators by device ID
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnActuatorsByDevicesByID() throws Exception {
    //Arrange
    Device device = setupDevice();
    String strDeviceID = device.getID().getID();
    String modelPathStr = "smarthome.domain.actuator.blind_roller_actuator.BlindRollerActuator";
    String strActuatorName = "BlindRoller";
    String strActuatorTypeID = "BlindRoller";

    ActuatorGenericDataDTOImp actuatorDataDTO = new ActuatorGenericDataDTOImp(strDeviceID,
        modelPathStr, strActuatorTypeID, strActuatorName);

    IActuator actuator = setupGenericActuator(actuatorDataDTO);
    when(actuatorRepository.ofDeviceID(device.getID())).thenReturn(List.of(actuator));

    //Act + Assert
    MvcResult result = mockMvc.perform(get("/actuators?deviceID=" + strDeviceID))
        .andExpect(status().isOk())
        .andReturn();

    // Assert
    String content = result.getResponse().getContentAsString();
    assertTrue(content.contains(actuator.getID().getID()));
  }

  /**
   * Should close a blind roller
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldCloseBlindRoller_WhenActuatorIsFound() throws Exception {
    //Arrange
    Device device = setupDevice();

    String deviceIDStr = device.getID().toString();
    String actuatorModelPath = "smarthome.domain.actuator.blind_roller_actuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";

    /* create unit */
    UnitDescription unit = new UnitDescription("Percent");
    UnitSymbol strUnitSymbol = new UnitSymbol("%");
    Unit actuatorUnit = new UnitFactoryImpl().createUnit(unit, strUnitSymbol);

    /* create actuator type */
    String strActuatorType = "BlindRoller";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();

    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType),
        actuatorUnit.getID());

    String actuatorTypeIDStr = actuatorType.getID().toString();

    /* create sensor */
    ModelPath sensorModelPath = new ModelPath(
        "smarthome.domain.sensor.percentage_position_sensor.PercentagePositionSensor");
    SensorTypeID sensorTypeID = new SensorTypeID("PercentagePosition");
    SensorName sensorName = new SensorName("Percent");
    ISensor sensor = new SensorFactoryImpl().create(device.getID(), sensorModelPath, sensorTypeID,
        sensorName);
    when(sensorRepository.ofIdentity(sensor.getID())).thenReturn(Optional.of(sensor));


    /* create dataDTO */
    ActuatorGenericDataDTOImp actuatorDataDTO = new ActuatorGenericDataDTOImp(deviceIDStr,
        actuatorModelPath,
        actuatorName, actuatorTypeIDStr);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));
    when(actuatorTypeRepository.ofIdentity(actuatorType.getID())).thenReturn(
        Optional.of(actuatorType));

    IActuator actuator = setupGenericActuator(actuatorDataDTO);
    when(actuatorService.getActuatorByID(actuator.getID())).thenReturn(Optional.of(actuator));

    /* create actuator value DTO */
    int valueToSet = 0;
    ActuatorValueEntryDTO actuatorValueDTO = new ActuatorValueEntryDTO(deviceIDStr,
        actuator.getID().getID(),
        valueToSet);

    /* create List of Log */
    SensorID sensorID = sensor.getID();
    ReadingValue readingValue = new ReadingValue("100");
    LocalDateTime timeStamp = LocalDateTime.now();

    Log log1 = new LogFactoryImpl().createLog(device.getID(), sensorID, timeStamp, readingValue,
        sensorTypeID, actuatorUnit.getID());
    when(logService.getDeviceReadingsByDeviceIDAndSensorTypeID(device.getID(),
        sensorTypeID)).thenReturn(List.of(log1));

    //Act + Assert
    MvcResult result = mockMvc.perform(post("/actuators/set-blindRoller")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorValueDTO)))
        .andExpect(status().isCreated())
        .andReturn();

    // Assert
    String content = result.getResponse().getContentAsString();
    System.out.println(content);
    assertTrue(content.contains(actuator.getID().getID()));
    assertTrue(content.contains("\"actuatorValue\":\"" + valueToSet + "\""));
  }

  /**
   * Should open a blind roller
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldOpenBlindRoller_WhenActuatorIsFound() throws Exception {
    //Arrange
    Device device = setupDevice();

    String deviceIDStr = device.getID().toString();
    String actuatorModelPath = "smarthome.domain.actuator.blind_roller_actuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";

    /* create unit */
    UnitDescription unit = new UnitDescription("Percent");
    UnitSymbol strUnitSymbol = new UnitSymbol("%");
    Unit actuatorUnit = new UnitFactoryImpl().createUnit(unit, strUnitSymbol);

    /* create actuator type */
    String strActuatorType = "BlindRoller";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();

    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType),
        actuatorUnit.getID());

    String actuatorTypeIDStr = actuatorType.getID().toString();

    /* create sensor */
    ModelPath sensorModelPath = new ModelPath(
        "smarthome.domain.sensor.percentage_position_sensor.PercentagePositionSensor");
    SensorTypeID sensorTypeID = new SensorTypeID("PercentagePosition");
    SensorName sensorName = new SensorName("Percent");
    ISensor sensor = new SensorFactoryImpl().create(device.getID(), sensorModelPath, sensorTypeID,
        sensorName);
    when(sensorRepository.ofIdentity(sensor.getID())).thenReturn(Optional.of(sensor));


    /* create dataDTO */
    ActuatorGenericDataDTOImp actuatorDataDTO = new ActuatorGenericDataDTOImp(deviceIDStr,
        actuatorModelPath,
        actuatorName, actuatorTypeIDStr);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));
    when(actuatorTypeRepository.ofIdentity(actuatorType.getID())).thenReturn(
        Optional.of(actuatorType));

    IActuator actuator = setupGenericActuator(actuatorDataDTO);
    when(actuatorService.getActuatorByID(actuator.getID())).thenReturn(Optional.of(actuator));

    /* create actuator value DTO */
    int valueToSet = 100;
    ActuatorValueEntryDTO actuatorValueDTO = new ActuatorValueEntryDTO(deviceIDStr,
        actuator.getID().getID(),
        valueToSet);

    /* create List of Log */
    SensorID sensorID = sensor.getID();
    ReadingValue readingValue = new ReadingValue("0");
    LocalDateTime timeStamp = LocalDateTime.now();

    Log log1 = new LogFactoryImpl().createLog(device.getID(), sensorID, timeStamp, readingValue,
        sensorTypeID, actuatorUnit.getID());
    when(logService.getDeviceReadingsByDeviceIDAndSensorTypeID(device.getID(),
        sensorTypeID)).thenReturn(List.of(log1));

    //Act + Assert
    MvcResult result = mockMvc.perform(post("/actuators/set-blindRoller")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorValueDTO)))
        .andExpect(status().isCreated())
        .andReturn();

    // Assert
    String content = result.getResponse().getContentAsString();
    assertTrue(content.contains(actuator.getID().getID()));
    assertTrue(content.contains("\"actuatorValue\":\"" + valueToSet + "\""));
  }

  /**
   * Should return not found when actuator is not found
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnNotFound_WhenActuatorIsNotFound() throws Exception {
    // Arrange
    ActuatorValueEntryDTO actuatorValueDTO = new ActuatorValueEntryDTO("invalidDeviceID",
        "invalidActuatorID",
        0);

    // Act + Assert
    mockMvc.perform(post("/set-blindRoller")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorValueDTO)))
        .andExpect(status().isNotFound());
  }

  /**
   * Should return NoLogRecordsFoundException when no logs are found
   */
  @Test
  void shouldReturnNoLogRecordsFoundException_WhenNoLogsAreFound() throws Exception {
    // Arrange
    Device device = setupDevice();

    String deviceIDStr = device.getID().toString();
    String actuatorModelPath = "smarthome.domain.actuator.blind_roller_actuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";

    /* create unit */
    UnitDescription unit = new UnitDescription("Percent");
    UnitSymbol strUnitSymbol = new UnitSymbol("%");
    Unit actuatorUnit = new UnitFactoryImpl().createUnit(unit, strUnitSymbol);

    /* create actuator type */
    String strActuatorType = "BlindRoller";
    ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();

    ActuatorType actuatorType = actuatorTypeFactory.createActuatorType(
        new TypeDescription(strActuatorType),
        actuatorUnit.getID());

    String actuatorTypeIDStr = actuatorType.getID().toString();

    /* create sensor */
    ModelPath sensorModelPath = new ModelPath(
        "smarthome.domain.sensor.percentage_position_sensor.PercentagePositionSensor");
    SensorTypeID sensorTypeID = new SensorTypeID("PercentagePosition");
    SensorName sensorName = new SensorName("Percent");
    ISensor sensor = new SensorFactoryImpl().create(device.getID(), sensorModelPath, sensorTypeID,
        sensorName);
    when(sensorRepository.ofIdentity(sensor.getID())).thenReturn(Optional.of(sensor));

    /* create dataDTO */
    ActuatorGenericDataDTOImp actuatorDataDTO = new ActuatorGenericDataDTOImp(deviceIDStr,
        actuatorModelPath,
        actuatorName, actuatorTypeIDStr);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));
    when(actuatorTypeRepository.ofIdentity(actuatorType.getID())).thenReturn(
        Optional.of(actuatorType));

    IActuator actuator = setupGenericActuator(actuatorDataDTO);
    when(actuatorService.getActuatorByID(actuator.getID())).thenReturn(Optional.of(actuator));

    /* create actuator value DTO */
    int valueToSet = 0;
    ActuatorValueEntryDTO actuatorValueDTO = new ActuatorValueEntryDTO(deviceIDStr,
        actuator.getID().getID(),
        valueToSet);

    // Act + Assert
    mockMvc.perform(post("/actuators/set-blindRoller")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(actuatorValueDTO)))
        .andExpect(status().isNotFound());
  }
}
