/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.rest;

import static org.hamcrest.Matchers.is;

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
import smarthome.domain.device.Device;
import smarthome.domain.device.DeviceFactoryImpl;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.device_type.DeviceTypeFactoryImpl;
import smarthome.domain.house.House;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.ISensorRepository;
import smarthome.domain.repository.ISensorTypeRepository;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.sensor.ISensorFactory;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.sensor_type.SensorTypeFactoryImpl;
import smarthome.domain.unit.Unit;
import smarthome.domain.unit.UnitFactoryImpl;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.utils.PathEncoder;
import smarthome.utils.entry_dto.sensor_entry_dto.ISensorEntryDTO;
import smarthome.utils.entry_dto.sensor_entry_dto.SensorGenericEntryDTOImp;
import smarthome.utils.entry_dto.sensor_entry_dto.SensorWithDateEntryDTOImp;
import smarthome.utils.entry_dto.sensor_entry_dto.SensorWithGPSEntryDTOImp;


@SpringBootTest
@AutoConfigureMockMvc
class SensorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private IHouseFactory houseFactory;

  @Autowired
  private ISensorFactory sensorFactory;

  @MockBean
  private IDeviceRepository deviceRepository;

  @MockBean
  private ISensorTypeRepository sensorTypeRepository;

  @MockBean
  private ISensorRepository sensorRepository;


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

    Dimension dimension = new Dimension(10, 10, 10);

    int floor = 1;
    RoomFloor roomFloor = new RoomFloor(floor);

    RoomFactoryImpl roomFactory = new RoomFactoryImpl();
    return roomFactory.createRoom(houseID, roomName, dimension, roomFloor);
  }

  Device setupDevice() {
    // Arrange
    Room room = setupRoom();
    RoomID roomID = room.getID();

    String strDeviceName = "Light";
    DeviceName deviceName = new DeviceName(strDeviceName);

    String strDeviceType = "LightBulb";
    TypeDescription typeDescription = new TypeDescription(strDeviceType);
    DeviceTypeFactoryImpl deviceTypeFactory = new DeviceTypeFactoryImpl();
    DeviceType deviceType = deviceTypeFactory.createDeviceType(typeDescription);
    DeviceTypeID deviceTypeID = deviceType.getID();

    DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
    return deviceFactory.createDevice(roomID, deviceName, deviceTypeID);
  }

  ISensor setupGenericSensor(SensorGenericEntryDTOImp sensorDataDTO) {
    DeviceID deviceID = new DeviceID(sensorDataDTO.deviceID);
    ModelPath modelPath = new ModelPath(sensorDataDTO.sensorModelPath);
    SensorName sensorName = new SensorName(sensorDataDTO.sensorName);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorDataDTO.sensorTypeID);

    return sensorFactory.create(deviceID, modelPath, sensorTypeID, sensorName);
  }


  /**
   * Test to add a generic sensor to a device
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnSensorDTO_whenGenericSensorIsAddedToDevice() throws Exception {
    //Arrange
    Device device = setupDevice();

    String deviceIDStr = device.getID().toString();
    String sensorModelPath = "smarthome.domain.sensor.dew_point_sensor.DewPointSensor";
    sensorModelPath = PathEncoder.encode(sensorModelPath);
    String sensorName = "DewPoint";

    /* create unit */
    UnitDescription unit = new UnitDescription("Celsius");
    UnitSymbol strUnitSymbol = new UnitSymbol("C");
    Unit sensorUnit = new UnitFactoryImpl().createUnit(unit, strUnitSymbol);

    /* create sensor type */
    String strSensorType = "DewPoint";
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();

    SensorType sensorType = sensorTypeFactory.createSensorType(new TypeDescription(strSensorType),
        sensorUnit.getID());

    String sensorTypeIDStr = sensorType.getID().toString();

    /* create dataDTO */
    ISensorEntryDTO sensorDataDTO = new SensorGenericEntryDTOImp(deviceIDStr, sensorModelPath,
        sensorName, sensorTypeIDStr);

    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    //Act + Assert
    mockMvc.perform(post("/sensors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorDataDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.sensorName", is(sensorName)))
        .andExpect(jsonPath("$.sensorTypeID", is(sensorTypeIDStr)));
  }


  /**
   * Test to add a sensor with GPS to a device
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnSensorDTO_WhenSensorWithGPSIsAddedToDevice() throws Exception {
    //Arrange
    Device device = setupDevice();
    String deviceIDStr = device.getID().toString();

    String strSensorType = "SunsetTime";
    TypeDescription typeDescription = new TypeDescription(strSensorType);

    String unitDescription = "Celsius";
    UnitDescription unit = new UnitDescription(unitDescription);

    String unitSymbol = "C";
    UnitSymbol strUnitSymbol = new UnitSymbol(unitSymbol);

    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit sensorUnit = unitFactory.createUnit(unit, strUnitSymbol);

    String sensorModelPath = "smarthome.domain.sensor.sunset_time_sensor.SunsetTimeSensor";
    sensorModelPath = PathEncoder.encode(sensorModelPath);
    String sensorName = "SunSet";

    String latitude = "38.7143";
    String longitude = "-9.1459";

    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorType sensorType = sensorTypeFactory.createSensorType(typeDescription, sensorUnit.getID());

    ISensorEntryDTO sensorDataDTO = new SensorWithGPSEntryDTOImp(deviceIDStr, sensorModelPath, sensorType.getID().toString(),
        sensorName, latitude, longitude);

    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    // Act & Assert
    mockMvc.perform(post("/sensors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorDataDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.sensorName", is(sensorName)))
        .andExpect(jsonPath("$.sensorTypeID", is(strSensorType)));

  }

  /**
   * Test to add a sensor with date to a device
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnSensorDTO_WhenSensorWithDateIsAddedToDevice() throws Exception {
    //Arrange
    Device device = setupDevice();
    String deviceIDStr = device.getID().toString();

    String strSensorType = "ElectricConsumptionWh";
    TypeDescription typeDescription = new TypeDescription(strSensorType);

    String unitDescription = "Wh";
    UnitDescription unit = new UnitDescription(unitDescription);

    String unitSymbol = "Wh";
    UnitSymbol strUnitSymbol = new UnitSymbol(unitSymbol);

    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit sensorUnit = unitFactory.createUnit(unit, strUnitSymbol);

    String sensorModelPath = "smarthome.domain.sensor.electric_consumption_wh_sensor.ElectricConsumptionWhSensor";
    sensorModelPath = PathEncoder.encode(sensorModelPath);
    String sensorName = "ElectricConsumptionWh";

    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);

    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorType sensorType = sensorTypeFactory.createSensorType(typeDescription, sensorUnit.getID());

    ISensorEntryDTO sensorDataDTO = new SensorWithDateEntryDTOImp(deviceIDStr, sensorModelPath,
        sensorName, sensorType.getID().toString(), datePeriod.getStartDate().toString(),
        datePeriod.getEndDate().toString());

    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    // Act & Assert
    mockMvc.perform(post("/sensors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorDataDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.sensorName", is(sensorName)))
        .andExpect(jsonPath("$.sensorTypeID", is(strSensorType)));
  }

  @Test
  void shouldReturnBadRequest_WhenDeviceIDIsNull() throws Exception {
    //Arrange
    String deviceID = null;

    String sensorModelPath = "smarthome.domain.sensor.electric_consumption_wh_sensor.ElectricConsumptionWhSensor";
    String sensorName = "ElectricConsumptionWh";

    String strUnitDescription = "Wh";
    UnitDescription unitDescription = new UnitDescription(strUnitDescription);
    String strUnitSymbol = "Wh";
    UnitSymbol unitSymbol = new UnitSymbol(strUnitSymbol);
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);

    String strSensorType = "ElectricConsumptionWh";
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorType sensorType = sensorTypeFactory.createSensorType(new TypeDescription(strSensorType),
        unit.getID());
    String sensorTypeID = sensorType.getID().getID();

    ISensorEntryDTO sensorDataDTO = new SensorGenericEntryDTOImp(deviceID, sensorModelPath,
        sensorName, sensorTypeID);

    // Act & Assert
    mockMvc.perform(post("/sensors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Should throw a bad request when sensor model path is null
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_WhenSensorModelPathIsNull() throws Exception {
    //Arrange
    Device device = setupDevice();
    String deviceIDStr = device.getID().getID();

    String sensorModelPath = null;
    String sensorName = "ElectricConsumptionWh";

    String strUnitDescription = "Wh";
    UnitDescription unitDescription = new UnitDescription(strUnitDescription);
    String strUnitSymbol = "Wh";
    UnitSymbol unitSymbol = new UnitSymbol(strUnitSymbol);
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);

    String strSensorType = "ElectricConsumptionWh";
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorType sensorType = sensorTypeFactory.createSensorType(new TypeDescription(strSensorType),
        unit.getID());
    String sensorTypeID = sensorType.getID().getID();

    ISensorEntryDTO sensorDataDTO = new SensorGenericEntryDTOImp(deviceIDStr, sensorModelPath,
        sensorName, sensorTypeID);

    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/sensors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorDataDTO)))
        .andExpect(status().isBadRequest());

  }

  /**
   * Should throw a bad request when sensor typeID is null
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_WhenSensorTypeIDIsNull() throws Exception {
    //Arrange
    Device device = setupDevice();
    String deviceIDStr = device.getID().getID();

    String sensorModelPath = "smarthome.domain.sensor.electric_consumption_wh_sensor.ElectricConsumptionWhSensor";
    String sensorName = "ElectricConsumptionWh";

    String strUnitDescription = "Wh";
    UnitDescription unitDescription = new UnitDescription(strUnitDescription);
    String strUnitSymbol = "Wh";
    UnitSymbol unitSymbol = new UnitSymbol(strUnitSymbol);
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    unitFactory.createUnit(unitDescription, unitSymbol);

    String sensorTypeID = null;

    ISensorEntryDTO sensorDataDTO = new SensorGenericEntryDTOImp(deviceIDStr, sensorModelPath,
        sensorName, sensorTypeID);

    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(post("/sensors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Should throw a bad request when sensor typeID is invalid
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_WhenSensorTypeIDIsInvalid() throws Exception {
    //Arrange
    Device device = setupDevice();
    String deviceIDStr = device.getID().getID();

    String sensorModelPath = "smarthome.domain.sensor.electric_consumption_wh_sensor.ElectricConsumptionWhSensor";
    String sensorName = "ElectricConsumptionWh";

    String strUnitDescription = "Wh";
    UnitDescription unitDescription = new UnitDescription(strUnitDescription);
    String strUnitSymbol = "Wh";
    UnitSymbol unitSymbol = new UnitSymbol(strUnitSymbol);
    UnitFactoryImpl unitFactory = new UnitFactoryImpl();
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);

    String strSensorType = "Electric";
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorType sensorType = sensorTypeFactory.createSensorType(new TypeDescription(strSensorType),
        unit.getID());
    String sensorTypeID = sensorType.getID().getID();

    ISensorEntryDTO sensorDataDTO = new SensorGenericEntryDTOImp(deviceIDStr, sensorModelPath,
        sensorName, sensorTypeID);

    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    // Act & Assert
    mockMvc.perform(post("/sensors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(sensorDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Should return a sensor by ID
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnSensorByID() throws Exception {
    // Arrange
    Device device = setupDevice();
    String deviceIDStr = device.getID().toString();
    String sensorModelPath = "smarthome.domain.sensor.dew_point_sensor.DewPointSensor";
    String sensorName = "DewPoint";

    UnitDescription unit = new UnitDescription("Celsius");
    UnitSymbol strUnitSymbol = new UnitSymbol("C");
    Unit sensorUnit = new UnitFactoryImpl().createUnit(unit, strUnitSymbol);

    String strSensorType = "DewPoint";
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
    SensorType sensorType = sensorTypeFactory.createSensorType(new TypeDescription(strSensorType),
        sensorUnit.getID());
    String sensorTypeIDStr = sensorType.getID().toString();

    SensorGenericEntryDTOImp sensorDataDTO = new SensorGenericEntryDTOImp(deviceIDStr,
        sensorModelPath, sensorName, sensorTypeIDStr);
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    ISensor sensor = setupGenericSensor(sensorDataDTO);
    when(sensorRepository.ofIdentity(sensor.getID())).thenReturn(Optional.of(sensor));

    // Act + Assert
    mockMvc.perform(get("/sensors?id=" + sensor.getID().getID()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.deviceID", is(deviceIDStr)))
        .andExpect(jsonPath("$.sensorTypeID", is(sensorTypeIDStr)));
  }


  /**
   * Should return a not found status when sensor is not found
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldNotFound_WhenNoSensorIsFound() throws Exception {
    // Arrange

    // Act + Assert
    mockMvc.perform(get("/sensors/" ))
        .andExpect(status().isNotFound());
  }


  /**
   * Should return a list of sensors
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnAllSensors_WhenSensorIsFound() throws Exception {
    //Arrange
    Device device = setupDevice();

    String deviceIDStr = device.getID().toString();
    String sensorModelPath = "smarthome.domain.sensor.dew_point_sensor.DewPointSensor";
    String sensorName = "DewPoint";

    /* create unit */
    UnitDescription unit = new UnitDescription("Celsius");
    UnitSymbol strUnitSymbol = new UnitSymbol("C");
    Unit sensorUnit = new UnitFactoryImpl().createUnit(unit, strUnitSymbol);

    /* create sensor type */
    String strSensorType = "DewPoint";
    SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();

    SensorType sensorType = sensorTypeFactory.createSensorType(new TypeDescription(strSensorType),
        sensorUnit.getID());

    String sensorTypeIDStr = sensorType.getID().toString();

    /* create dataDTO */
    SensorGenericEntryDTOImp sensorDataDTO = new SensorGenericEntryDTOImp(deviceIDStr,
        sensorModelPath,
        sensorName, sensorTypeIDStr);
    SensorGenericEntryDTOImp sensorDataDTO2 = new SensorGenericEntryDTOImp(deviceIDStr,
        sensorModelPath,
        sensorName, sensorTypeIDStr);

    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));
    when(sensorTypeRepository.ofIdentity(sensorType.getID())).thenReturn(Optional.of(sensorType));

    ISensor sensor = setupGenericSensor(sensorDataDTO);
    ISensor sensor2 = setupGenericSensor(sensorDataDTO2);
    when(sensorRepository.findAll()).thenReturn(List.of(sensor, sensor2));

    //Act + Assert
    mockMvc.perform(get("/sensors")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.sensorDTOList[0].deviceID", is(deviceIDStr)))
        .andExpect(jsonPath("$._embedded.sensorDTOList[0].sensorTypeID", is(sensorTypeIDStr)))
        .andExpect(jsonPath("$._embedded.sensorDTOList[1].deviceID", is(deviceIDStr)))
        .andExpect(jsonPath("$._embedded.sensorDTOList[1].sensorTypeID", is(sensorTypeIDStr)));
  }

  /**
   * Should return a bad request when no sensors are found
   *
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnBadRequest_WhenNoSensorsAreFound() throws Exception {

    //Act + Assert
    mockMvc.perform(get("/sensors"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.self.href").exists());
  }


  /**
   * Should return a list of sensors by device ID
   * @throws Exception if the test fails
   */
  @Test
  void shouldReturnSensorsByDeviceID () throws Exception {
    //Arrange
    Device device = setupDevice();
    String deviceIDStr = device.getID().getID();
    String sensorModelPath = "smarthome.domain.sensor.dew_point_sensor.DewPointSensor";
    String strSensorName = "DewPoint";
    String strSensorType = "DewPoint";

    SensorGenericEntryDTOImp sensorDataGenericDTOImp = new SensorGenericEntryDTOImp(
        deviceIDStr, sensorModelPath, strSensorType, strSensorName
    );

    ISensor sensor = setupGenericSensor(sensorDataGenericDTOImp);
    when(sensorRepository.ofDeviceID(device.getID())).thenReturn(List.of(sensor));

    //Act + Assert
    mockMvc.perform(get("/sensors?deviceID=" + deviceIDStr))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].sensorID").value(sensor.getID().getID()))
        .andExpect(jsonPath("$[0].sensorName").value(sensor.getSensorName().getSensorName()));
  }
}