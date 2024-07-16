/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import smarthome.domain.house.House;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.repository.IRoomRepository;
import smarthome.domain.room.IRoomFactory;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomName;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.utils.entry_dto.RoomEntryDTO;

@SpringBootTest
@AutoConfigureMockMvc
class RoomControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private IHouseRepository houseRepository;

  @MockBean
  private IRoomRepository roomRepository;

  @Autowired
  private IRoomFactory roomFactory;

  @Autowired
  private IHouseFactory houseFactory;

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
    House house = houseFactory.createHouse(address, gps);
    return house;
  }

  RoomEntryDTO setupRoomDataDTO(House house) {
    String name = "Living Room";
    int floor = 1;
    int width = 10;
    int length = 10;
    int height = 3;
    return new RoomEntryDTO(name, floor, width, length, height);
  }

  // given a RoomDataDTO create a Room object using RoomFactory
  Room setupRoom(RoomEntryDTO roomDataDTO) {
    HouseID houseID = new HouseID("1");
    RoomName name = new RoomName(roomDataDTO.name);
    RoomFloor floor = new RoomFloor(roomDataDTO.floor);
    Dimension dimension = new Dimension(roomDataDTO.width, roomDataDTO.length, roomDataDTO.height);
    return roomFactory.createRoom(houseID, name, dimension, floor);
  }

  /**
   * Verify that a Room is correctly added to the House
   */
  @Test
  void shouldReturnRoomDTO_whenRoomIsAddedToHouse() throws Exception {
    // Arrange
    House house = setupHouse();
    RoomEntryDTO roomDataDTO = setupRoomDataDTO(house);
    when(houseRepository.getTheHouse()).thenReturn(Optional.of(house));

    // Act & Assert
    mockMvc.perform(post("/rooms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(roomDataDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.roomName").value("Living Room"));
  }

  /**
   * Verify that a Room is not added to the House when the House does not exist
   */
  @Test
  void shouldReturnBadRequest_whenHouseDoesNotExist() throws Exception {
    // Arrange
    String houseIDStr = "1";
    HouseID houseID = new HouseID(houseIDStr);
    String name = "Living Room";
    int floor = 1;
    int width = 10;
    int length = 10;
    int height = 3;
    RoomEntryDTO roomDataDTO = new RoomEntryDTO(name, floor, width, length, height);
    when(houseRepository.ofIdentity(houseID)).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(post("/rooms")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(roomDataDTO)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Test getAllRooms method in RoomController other test method
   */
  @Test
  void shouldReturnAllRooms_whenGetAllRoomsIsCalled() throws Exception {
    //Arrange
    House house = setupHouse();
    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));

    RoomEntryDTO dto1 = setupRoomDataDTO(house);
    RoomEntryDTO dto2 = setupRoomDataDTO(house);

    Room room = setupRoom(dto1);
    Room room2 = setupRoom(dto2);

    when(roomRepository.findAll()).thenReturn(List.of(room, room2));

    //Act & Assert
    mockMvc.perform(get("/rooms"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._embedded.roomDTOList[0].roomName").value("Living Room"))
        .andExpect(jsonPath("$._embedded.roomDTOList[0]._links.['self']").exists())
        .andExpect(jsonPath("$._embedded.roomDTOList[0]._links.['add-device']").exists())
        .andExpect(jsonPath("$._embedded.roomDTOList[1].roomName").value("Living Room"))
        .andExpect(jsonPath("$._embedded.roomDTOList[1]._links.['self']").exists())
        .andExpect(jsonPath("$._embedded.roomDTOList[1]._links.['add-device']").exists());
  }

  /**
   * Test getRoomById method in RoomController
   */
  @Test
  void shouldReturnRoom_whenGetRoomById() throws Exception {
    // Arrange
    House house = setupHouse();
    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));

    RoomEntryDTO roomDataDTO = setupRoomDataDTO(house);
    Room room = setupRoom(roomDataDTO);
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.of(room));

    // Act & Assert
    mockMvc.perform(get("/rooms/" + room.getID()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.roomName").value("Living Room"))
        .andExpect(jsonPath("$._links.['self']").exists())
        .andExpect(jsonPath("$._links.['room-devices']").exists())
        .andExpect(jsonPath("$._links.['add-device']").exists());
  }

  /**
   * Test getRoomById method in RoomController when room does not exist
   */
  @Test
  void shouldReturnNotFound_whenRoomDoesNotExist() throws Exception {
    // Arrange
    House house = setupHouse();
    when(houseRepository.ofIdentity(house.getID())).thenReturn(Optional.of(house));

    RoomEntryDTO roomDataDTO = setupRoomDataDTO(house);
    Room room = setupRoom(roomDataDTO);
    when(roomRepository.ofIdentity(room.getID())).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(get("/rooms/" + room.getID()))
        .andExpect(status().isNotFound());
  }
}