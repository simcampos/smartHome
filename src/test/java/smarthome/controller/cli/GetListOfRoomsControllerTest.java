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

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.ddd.IAssembler;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.repository.IRoomRepository;
import smarthome.domain.room.IRoomFactory;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomName;
import smarthome.mapper.RoomAssembler;
import smarthome.persistence.mem.HouseRepository;
import smarthome.persistence.mem.RoomRepository;
import smarthome.service.IRoomService;
import smarthome.service.RoomServiceImpl;
import smarthome.utils.dto.RoomDTO;

class GetListOfRoomsControllerTest {

  /**
   * Test to check if the GetListOfRoomsController is being created correctly.
   */
  @Test
  void shouldCreateGetListOfRoomsController() {
    //Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = mock(IHouseRepository.class);

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);

    //Act
    GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(
        roomServiceImpl, roomAssembler);

    //Assert
    assertNotNull(getListOfRoomsController);
  }


  /**
   * Test to check if the GetListOfRoomsController is returning null when the RoomService is null.
   */
  @Test
  void shouldReturnNull_whenRoomServiceIsNull() {
    //Arrange
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();

    IRoomService roomServiceImpl = null;

    String expectedMessage = "Room service is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(
          roomServiceImpl, roomAssembler);
    });

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);

  }

  /**
   * Test to check if the GetListOfRoomsController is returning null when the RoomAssembler is
   * null.
   */
  @Test
  void shouldReturnNull_whenRoomAssemblerIsNull() {
    //Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = null;
    IHouseRepository houseRepository = mock(IHouseRepository.class);

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);

    String expectedMessage = "Room assembler is required";

    //Act
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(
          roomServiceImpl, roomAssembler);
    });

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test to check if the GetListOfRoomsController is returning an empty list when there are no
   * rooms.
   */
  @Test
  void shouldReturnEmptyList_whenThereAreNoRooms() {
    //Arrange
    IRoomRepository roomRepository = mock(IRoomRepository.class);
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = mock(IHouseRepository.class);

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);

    GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(
        roomServiceImpl, roomAssembler);

    //Act
    List<RoomDTO> roomDTOList = getListOfRoomsController.getRooms();

    //Assert
    assertTrue(roomDTOList.isEmpty());

  }


  /**
   * Test to check if the GetListOfRoomsController is returning a list of rooms when there are
   * rooms.
   */
  @Test
  void shouldReturnListOfRooms_WhenGetRoomsIsCalled() {
    //Arrange
    IRoomRepository roomRepository = new RoomRepository();
    IRoomFactory roomFactory = new RoomFactoryImpl();
    IAssembler<Room, RoomDTO> roomAssembler = new RoomAssembler();
    IHouseRepository houseRepository = new HouseRepository();

    IRoomService roomServiceImpl = new RoomServiceImpl(roomRepository, roomFactory,
        houseRepository);

    GetListOfRoomsController getListOfRoomsController = new GetListOfRoomsController(
        roomServiceImpl, roomAssembler);

    HouseID houseID = new HouseID("1");
    RoomName roomName = new RoomName("Living Room");
    Dimension dimension = new Dimension(10, 10, 10);
    RoomFloor roomFloor = new RoomFloor(1);

    Room room = roomFactory.createRoom(houseID, roomName, dimension, roomFloor);
    roomRepository.save(room);

    List<RoomDTO> expectedRoomDTOList = new ArrayList<>();

    expectedRoomDTOList.add(roomAssembler.domainToDTO(room));

    //Act
    List<RoomDTO> roomDTOList = getListOfRoomsController.getRooms();

    //Assert
    assertEquals(expectedRoomDTOList.get(0).roomId, roomDTOList.get(0).roomId);

  }

}
