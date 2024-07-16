/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.rest;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import smarthome.domain.device.Device;
import smarthome.domain.device.IDeviceFactory;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.device_type.IDeviceTypeFactory;
import smarthome.domain.house.House;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.repository.IDeviceRepository;
import smarthome.domain.repository.IDeviceTypeRepository;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.repository.IRoomRepository;
import smarthome.domain.room.IRoomFactory;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.utils.entry_dto.DeviceEntryDTO;
import smarthome.utils.entry_dto.RoomEntryDTO;

@SpringBootTest
@AutoConfigureMockMvc
class DeviceControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private IHouseFactory houseFactory;

  @Autowired
  private IRoomFactory roomFactory;

  @Autowired
  private IDeviceFactory deviceFactory;

  @Autowired
  private IDeviceTypeFactory deviceTypeFactory;

  @MockBean
  private IDeviceRepository deviceRepository;

  @MockBean
  private IHouseRepository houseRepository;

  @MockBean
  private IRoomRepository roomRepository;

  @MockBean
  private IDeviceTypeRepository deviceTypeRepository;


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

  Room setupRoom(RoomEntryDTO roomDataDTO) {
    HouseID houseID = new HouseID("1");
    RoomName name = new RoomName(roomDataDTO.name);
    RoomFloor floor = new RoomFloor(roomDataDTO.floor);
    Dimension dimension = new Dimension(roomDataDTO.width, roomDataDTO.length, roomDataDTO.height);
    return roomFactory.createRoom(houseID, name, dimension, floor);
  }

  DeviceType setupDeviceType() {
    TypeDescription typeDescription = new TypeDescription("Bulb");
    return deviceTypeFactory.createDeviceType(typeDescription);
  }

  DeviceType setupDeviceTypeTwo() {
    TypeDescription typeDescription = new TypeDescription("Fan");
    return deviceTypeFactory.createDeviceType(typeDescription);
  }

  DeviceEntryDTO setupDeviceDataDTO(Room room, String deviceTypeDescription) {
    String deviceName = "Light";
    String roomIDStr = room.getID().toString();
    return new DeviceEntryDTO(deviceTypeDescription, deviceName,roomIDStr);
  }

  Device setupDevice(DeviceEntryDTO deviceDataDTO) {
    RoomID roomID = new RoomID(deviceDataDTO.roomID);
    DeviceName deviceName = new DeviceName(deviceDataDTO.deviceName);
    DeviceTypeID deviceTypeID = new DeviceTypeID(deviceDataDTO.deviceTypeDescription);
    return deviceFactory.createDevice(roomID, deviceName, deviceTypeID);
  }

  RoomEntryDTO setupRoomDataDTO() {
    String name = "Living Room";
    int floor = 1;
    int width = 10;
    int length = 10;
    int height = 3;
    return new RoomEntryDTO(name, floor, width, length, height);
  }


  /**
   * Verify that a Device is correctly added to the Room
   */
  @Test
  void shouldReturnDeviceDTO_whenDeviceIsAddedToRoom() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomEntryDTO roomDataDTO = setupRoomDataDTO();
    Room room = setupRoom(roomDataDTO);
    DeviceType deviceType = setupDeviceType();
    String deviceTypeDescription = "Bulb";
    DeviceEntryDTO deviceDataDTO = setupDeviceDataDTO(room, deviceTypeDescription);
    Device device = setupDevice(deviceDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceRepository.save(device)).thenReturn(device);

    // Act & Assert
    mockMvc.perform(post("/devices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(deviceDataDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.deviceName").value("Light"))
        .andExpect(jsonPath("$._links.self").exists());
  }

  /**
   * Verify that a Device is correctly retrieved by its ID
   */
  @Test
  void shouldReturnDeviceDTO_whenGetDeviceById() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomEntryDTO roomDataDTO = setupRoomDataDTO();
    Room room = setupRoom(roomDataDTO);
    DeviceType deviceType = setupDeviceType();
    String deviceTypeDescription = "Bulb";
    DeviceEntryDTO deviceDataDTO = setupDeviceDataDTO(room, deviceTypeDescription);
    Device device = setupDevice(deviceDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(get("/devices/" + device.getID())
         .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.deviceID").value(device.getID().toString()))
        .andExpect(jsonPath("$._links.self").exists());
  }

  /**
   * Verify that a Device is not added to the Room when the Room does not exist
   */
  @Test
  void shouldReturnBadRequest_whenRoomDoesNotExist() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomEntryDTO roomDataDTO = setupRoomDataDTO();
    DeviceType deviceType = setupDeviceType();
    String deviceTypeDescription = "Bulb";
    DeviceEntryDTO deviceDataDTO = setupDeviceDataDTO(setupRoom(roomDataDTO), deviceTypeDescription);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(roomRepository.ofIdentity(new RoomID(deviceDataDTO.roomID))).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(post("/devices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(deviceDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that a Device is not added to the Room when the DeviceType does not exist
   */
  @Test
  void shouldReturnBadRequest_whenDeviceTypeDoesNotExist() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomEntryDTO roomDataDTO = setupRoomDataDTO();
    String deviceTypeDescription = "Bulb";
    DeviceEntryDTO deviceDataDTO = setupDeviceDataDTO(setupRoom(roomDataDTO), deviceTypeDescription);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(new DeviceTypeID(deviceDataDTO.deviceTypeDescription)))
        .thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(post("/devices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(deviceDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Verify that a Device is not added to the Room when the House does not exist
   */
  @Test
  void shouldReturnBadRequest_whenHouseDoesNotExist() throws Exception {
    // Arrange
    RoomEntryDTO roomDataDTO = setupRoomDataDTO();
    String deviceTypeDescription = "Bulb";
    DeviceEntryDTO deviceDataDTO = setupDeviceDataDTO(setupRoom(roomDataDTO), deviceTypeDescription);

    when(houseRepository.ofIdentity(new HouseID("1"))).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(post("/devices")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(deviceDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Test getDeviceID method when the device does not exist
   */
  @Test
  void shouldReturnNotFound_whenDeviceDoesNotExist() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomEntryDTO roomDataDTO = setupRoomDataDTO();
    DeviceType deviceType = setupDeviceType();
    String deviceTypeDescription = "Bulb";
    DeviceEntryDTO deviceDataDTO = setupDeviceDataDTO(setupRoom(roomDataDTO), deviceTypeDescription);
    Device device = setupDevice(deviceDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(roomRepository.ofIdentity(setupRoom(roomDataDTO).getID())).thenReturn(Optional.of(setupRoom(roomDataDTO)));
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(get("/devices" + device.getID())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  /**
   * Test getDeviceID when deviceType does not exist
   */
  @Test
  void shouldReturnNotFound_whenDeviceTypeDoesNotExist() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomEntryDTO roomDataDTO = setupRoomDataDTO();
    DeviceType deviceType = setupDeviceType();
    String deviceTypeDescription = "Bulb";
    DeviceEntryDTO deviceDataDTO = setupDeviceDataDTO(setupRoom(roomDataDTO), deviceTypeDescription);
    Device device = setupDevice(deviceDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(get("/devices" + device.getID())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  /**
   * Test getAllDevice when devices are available
   */
  @Test
  void shouldReturnAllDevices_whenGetAllDevicesIsCalled() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomEntryDTO roomDataDTO = setupRoomDataDTO();

    Room room = setupRoom(roomDataDTO);
    DeviceType deviceType = setupDeviceType();
    String deviceTypeDescription = "Bulb";

    DeviceEntryDTO deviceDataDTO1 = setupDeviceDataDTO(room, deviceTypeDescription);
    DeviceEntryDTO deviceDataDTO2 = setupDeviceDataDTO(room, deviceTypeDescription);

    Device device = setupDevice(deviceDataDTO1);
    Device device2 = setupDevice(deviceDataDTO2);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(deviceRepository.findAll()).thenReturn(List.of(device, device2));

    int expectedSize = List.of(device, device2).size();

    // Act & Assert
    mockMvc.perform(get("/devices")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.deviceDTOList", hasSize(expectedSize)))
        .andExpect(jsonPath("$._links.self").exists());

  }

  /**
   * Test getAllDevices when no devices are available
   */
  @Test
  void shouldReturnNotFound_whenNoDevicesAvailable() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomEntryDTO roomDataDTO = setupRoomDataDTO();
    Room room = setupRoom(roomDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceRepository.findAll()).thenReturn(List.of());

    String expected = "{\"_links\":{\"self\":{\"href\":\"http://localhost/devices\",\"title\":\"Get All Devices\",\"type\":\"GET\"}}}";

    // Act & Assert
    MvcResult result = mockMvc.perform(get("/devices")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();

    // Assert
    assertEquals(expected, result.getResponse().getContentAsString());
  }

  /**
   * Test deactivateDevice when the device exists
   */
  @Test
  void shouldReturnDeviceDTO_whenDeactivateDevice() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomEntryDTO roomDataDTO = setupRoomDataDTO();
    DeviceType deviceType = setupDeviceType();
    Room room = setupRoom(roomDataDTO);
    String deviceTypeDescription = "Bulb";
    DeviceEntryDTO deviceDataDTO = setupDeviceDataDTO(room, deviceTypeDescription);
    Device device = setupDevice(deviceDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.of(device));

    // Act & Assert
    mockMvc.perform(put("/devices/deactivate/" + device.getID())
         .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.deviceID").value(device.getID().toString()))
        .andExpect(jsonPath("$.deviceStatus").value("OFF"))
        .andExpect(jsonPath("$._links.self").exists());
  }


  /**
   * Test deactivateDevice when the device does not exist
   */
  @Test
  void shouldReturnNotFound_whenDeactivateDeviceDoesNotExist() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomEntryDTO roomDataDTO = setupRoomDataDTO();
    DeviceType deviceType = setupDeviceType();
    String deviceTypeDescription = "Bulb";
    DeviceEntryDTO deviceDataDTO = setupDeviceDataDTO(setupRoom(roomDataDTO), deviceTypeDescription);
    Device device = setupDevice(deviceDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(roomRepository.ofIdentity(setupRoom(roomDataDTO).getID())).thenReturn(Optional.of(setupRoom(roomDataDTO)));
    when(deviceRepository.ofIdentity(device.getID())).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(put("/devicesdeactivate/" + device.getID())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }


  /**
   * Test getAllDevicesGroupedByFunctionality when there is a device with no device type
   */
  @Test
  void shouldReturnNotFound_whenThereIsNoDeviceType() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomEntryDTO roomDataDTO = setupRoomDataDTO();
    DeviceType deviceType = setupDeviceType();
    Room room = setupRoom(roomDataDTO);
    String deviceTypeDescription = "Bulb";
    DeviceEntryDTO deviceDataDTO = setupDeviceDataDTO(room, deviceTypeDescription);
    Device device = setupDevice(deviceDataDTO);

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.empty());
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceRepository.findAll()).thenReturn(List.of(device));

    // Act & Assert
    mockMvc.perform(get("/devicesgrouped")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }


  /**
   * Test getAllDevicesGroupedByFunctionality returns a map of devices grouped by
   * functionality, when devices have different type.
   */
  @Test
  void shouldReturnDevicesGroupedByFunctionality_whenDevicesHaveDifferentType() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomEntryDTO roomDataDTO = setupRoomDataDTO();
    Room room = setupRoom(roomDataDTO);
    DeviceType deviceType = setupDeviceType();
    DeviceType deviceType2 = setupDeviceTypeTwo();
    String deviceTypeDescription = "Bulb";
    String deviceTypeDescription2 = "Fan";

    Device device = setupDevice(setupDeviceDataDTO(room, deviceTypeDescription));
    Device deviceTwo = setupDevice(setupDeviceDataDTO(room, deviceTypeDescription2));
    Device deviceThree = setupDevice(setupDeviceDataDTO(room, deviceTypeDescription2));

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceTypeRepository.findAll()).thenReturn(List.of(deviceType, deviceType2));

    when(deviceRepository.findByDeviceTypeID(deviceType.getID())).thenReturn(List.of(device));
    when(deviceRepository.findByDeviceTypeID(deviceType2.getID())).thenReturn(
        List.of(deviceTwo, deviceThree));

    String expectedResponse =
        "{\"Bulb\":[{\"deviceID\":\"" + device.getID().getID() + "\",\"roomID\":\"" +
            room.getID().getID() + "\",\"deviceName\":\"Light\",\"deviceStatus\":\"ON\",\"links\":[]}],"
            + "\"Fan\":[{\"deviceID\":\"" + deviceTwo.getID().getID() + "\",\"roomID\":\""
            + room.getID().getID()
            + "\",\"deviceName\":\"Light\",\"deviceStatus\":\"ON\",\"links\":[]},{\"deviceID\":\""
            + deviceThree.getID().getID()
            + "\",\"roomID\":\"" + room.getID().getID()
            + "\",\"deviceName\":\"Light\",\"deviceStatus\":\"ON\",\"links\":[]}]}";
    // Act
    MvcResult result = mockMvc.perform(get("/devices/grouped")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();
    String jsonResponse = result.getResponse().getContentAsString();

    // Assert
    assertEquals(expectedResponse, jsonResponse);
  }

  /**
   * test getDeviceByRoomId method in RoomController
   */
  @Test
  void shouldReturnDevices_whenGetDevicesWithRoomIdParameter() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomEntryDTO roomDataDTO = setupRoomDataDTO();
    Room room = setupRoom(roomDataDTO);
    DeviceType deviceType = setupDeviceType();
    DeviceType deviceType2 = setupDeviceTypeTwo();
    String deviceTypeDescription = "Bulb";
    String deviceTypeDescription2 = "Fan";

    Device device = setupDevice(setupDeviceDataDTO(room, deviceTypeDescription));
    Device deviceTwo = setupDevice(setupDeviceDataDTO(room, deviceTypeDescription2));
    Device deviceThree = setupDevice(setupDeviceDataDTO(room, deviceTypeDescription2));

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(deviceTypeRepository.ofIdentity(deviceType2.getID())).thenReturn(Optional.of(deviceType2));
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceRepository.findByRoomID(room.getID())).thenReturn(List.of(device, deviceTwo, deviceThree));

    String linkDevice = "http://localhost/devices/" + device.getID().getID();
    String linkDeviceTwo = "http://localhost/devices/" + deviceTwo.getID().getID();
    String linkDeviceThree = "http://localhost/devices/" + deviceThree.getID().getID();

    String expected = "{\"_embedded\":"
        + "{\"deviceDTOList\":["
        + "{\"deviceID\":\"" + device.getID().getID() + "\",\"roomID\":\"" + room.getID().getID()
        + "\",\"deviceName\":\"Light\",\"deviceStatus\":\"ON\",\"_links\":{\"get-device\":{\"href\":\"" + linkDevice + "\",\"title\":\"Get Device\",\"type\":\"GET\"}}},"
        + "{\"deviceID\":\"" + deviceTwo.getID().getID() + "\",\"roomID\":\"" + room.getID().getID()
        + "\",\"deviceName\":\"Light\",\"deviceStatus\":\"ON\",\"_links\":{\"get-device\":{\"href\":\"" + linkDeviceTwo + "\",\"title\":\"Get Device\",\"type\":\"GET\"}}},"
        + "{\"deviceID\":\"" + deviceThree.getID().getID() + "\",\"roomID\":\"" + room.getID()
        .getID() + "\",\"deviceName\":\"Light\",\"deviceStatus\":\"ON\",\"_links\":{\"get-device\":{\"href\":\"" + linkDeviceThree +"\",\"title\":\"Get Device\",\"type\":\"GET\"}}}]},"
        + "\"_links\":"
        + "{\"self\":{\"href\":\"http://localhost/devices?room_id=" + room.getID().getID()
        + "{&device_type_id}\",\"title\":\"Get All Devices by Room ID and Device Type ID\",\"type\":\"GET\",\"templated\":true}}}";
    // Act & Assert
    MvcResult result = mockMvc.perform(
            get("/devices?room_id=" + room.getID().getID() + "&device_type_id")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.deviceDTOList", hasSize(3)))
        .andExpect(jsonPath("$._links.self").exists())
        .andReturn();
    // Assert
    assertEquals(expected, result.getResponse().getContentAsString());
  }

  @Test
  void shouldReturnDevicesOfGivenType_whenGetDevicesWithRoomIdAndTypeParameter() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomEntryDTO roomDataDTO = setupRoomDataDTO();
    Room room = setupRoom(roomDataDTO);
    DeviceType deviceType = setupDeviceType();
    DeviceType deviceType2 = setupDeviceTypeTwo();
    String deviceTypeDescription = "Bulb";
    String deviceTypeDescription2 = "Fan";

    Device device = setupDevice(setupDeviceDataDTO(room, deviceTypeDescription));
    Device deviceTwo = setupDevice(setupDeviceDataDTO(room, deviceTypeDescription2));
    Device deviceThree = setupDevice(setupDeviceDataDTO(room, deviceTypeDescription2));

    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));
    when(deviceTypeRepository.ofIdentity(deviceType.getID())).thenReturn(Optional.of(deviceType));
    when(deviceTypeRepository.ofIdentity(deviceType2.getID())).thenReturn(Optional.of(deviceType2));
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));
    when(deviceRepository.findByRoomID(room.getID())).thenReturn(
        List.of(device, deviceTwo, deviceThree));

    String linkDeviceTwo = "http://localhost/devices/" + deviceTwo.getID().getID();
    String linkDeviceThree = "http://localhost/devices/" + deviceThree.getID().getID();

    String expected = "{\"_embedded\":"
        + "{\"deviceDTOList\":["
        + "{\"deviceID\":\"" + deviceTwo.getID().getID() + "\",\"roomID\":\"" + room.getID().getID()
        + "\",\"deviceName\":\"Light\",\"deviceStatus\":\"ON\",\"_links\":{\"get-device\":{\"href\":\"" + linkDeviceTwo + "\",\"title\":\"Get Device\",\"type\":\"GET\"}}},"
        + "{\"deviceID\":\"" + deviceThree.getID().getID() + "\",\"roomID\":\"" + room.getID()
        .getID() + "\",\"deviceName\":\"Light\",\"deviceStatus\":\"ON\",\"_links\":{\"get-device\":{\"href\":\"" + linkDeviceThree +"\",\"title\":\"Get Device\",\"type\":\"GET\"}}}]},"
        + "\"_links\":"
        + "{\"self\":{\"href\":\"http://localhost/devices?room_id=" + room.getID().getID()
        + "&device_type_id=" + deviceType2.getID().getID() + "\",\"title\":\"Get All Devices by Room ID and Device Type ID\",\"type\":\"GET\"}}}";
    // Act & Assert
    MvcResult result = mockMvc.perform(
            get("/devices?room_id=" + room.getID().getID() + "&device_type_id=" + deviceType2.getID()
                .getID())
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.deviceDTOList", hasSize(2)))
        .andExpect(jsonPath("$._links.self").exists())
        .andReturn();

    // Assert
    assertEquals(expected, result.getResponse().getContentAsString());
  }

}
