/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.persistence.data_model.RoomDataModel;

class RoomDataModelAssemblerTest {

  /**
   * Test of RoomDataModelAssembler constructor.
   */
  @Test
  void shouldInstantiateRoomDataModelAssembler_whenRoomFactoryIsValid() {
    // Arrange
    RoomFactoryImpl roomFactory = mock(RoomFactoryImpl.class);

    // Act
    RoomDataModelAssembler roomDataModelAssembler = new RoomDataModelAssembler(roomFactory);

    // Assert
    assertNotNull(roomDataModelAssembler);
  }

  /**
   * Test of RoomDataModelAssembler constructor when RoomFactory is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenRoomFactoryIsNull() {
    // Arrange
    RoomFactoryImpl roomFactory = null;
    String expectedMessage = "RoomFactory cannot be null.";

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new RoomDataModelAssembler(roomFactory));

    // Assert
    String actualMessage = exception.getMessage();
    assertNotNull(expectedMessage, actualMessage);
  }

  /**
   * Test of toDomain method when given valid RoomDataModel.
   */
  @Test
  void shouldReturnRoom_whenGivenValidRoomDataModel() {
    // Arrange
    RoomFactoryImpl roomFactoryDouble = mock(RoomFactoryImpl.class);
    when(roomFactoryDouble.createRoom(any(HouseID.class), any(RoomName.class), any(Dimension.class),
        any(
            RoomFloor.class), any(RoomID.class))).thenReturn(mock(Room.class));

    RoomDataModelAssembler roomDataModelAssembler = new RoomDataModelAssembler(roomFactoryDouble);

    RoomID roomIDDouble = mock(RoomID.class);
    when(roomIDDouble.getID()).thenReturn("1L");

    HouseID houseIDDouble = mock(HouseID.class);
    when(houseIDDouble.getID()).thenReturn("HouseID");

    RoomName roomNameDouble = mock(RoomName.class);
    when(roomNameDouble.getRoomName()).thenReturn("RoomName");

    Dimension dimensionDouble = mock(Dimension.class);
    when(dimensionDouble.getWidth()).thenReturn(1);
    when(dimensionDouble.getDepth()).thenReturn(1);
    when(dimensionDouble.getHeight()).thenReturn(1);

    RoomFloor roomFloorDouble = mock(RoomFloor.class);
    when(roomFloorDouble.getFloor()).thenReturn(1);

    RoomDataModel roomDataModelDouble = mock(RoomDataModel.class);
    when(roomDataModelDouble.getRoomName()).thenReturn("RoomName");
    when(roomDataModelDouble.getRoomID()).thenReturn("1L");
    when(roomDataModelDouble.getHouseID()).thenReturn("HouseID");
    when(roomDataModelDouble.getFloor()).thenReturn(1);
    when(roomDataModelDouble.getWidth()).thenReturn(1);
    when(roomDataModelDouble.getDepth()).thenReturn(1);
    when(roomDataModelDouble.getHeight()).thenReturn(1);

    Room expected = roomFactoryDouble.createRoom(houseIDDouble, roomNameDouble, dimensionDouble,
        roomFloorDouble, roomIDDouble);

    // Act
    Room room = roomDataModelAssembler.toDomain(roomDataModelDouble);

    // Assert
    assertEquals(expected, room);
  }

  /**
   * Test of toDomain method when given null RoomDataModel.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullRoomDataModel() {
    // Arrange
    RoomFactoryImpl roomFactoryDouble = mock(RoomFactoryImpl.class);
    RoomDataModelAssembler roomDataModelAssembler = new RoomDataModelAssembler(roomFactoryDouble);

    RoomDataModel roomDataModelDouble = null;
    String expectedMessage = "Room Data Model is required";

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> roomDataModelAssembler.toDomain(roomDataModelDouble));

    // Assert
    String actualMessage = exception.getMessage();
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test of toDomain when given valid list of Data Models
   */
  @Test
  void shouldReturnListOfRooms_whenGivenListOfRoomDataModels() {
    // Arrange
    RoomFactoryImpl roomFactoryDouble = mock(RoomFactoryImpl.class);
    when(roomFactoryDouble.createRoom(any(HouseID.class), any(RoomName.class), any(Dimension.class),
        any(RoomFloor.class), any(RoomID.class))).thenReturn(mock(Room.class));

    RoomDataModelAssembler roomDataModelAssembler = new RoomDataModelAssembler(roomFactoryDouble);

    RoomID roomIDDouble = mock(RoomID.class);
    when(roomIDDouble.getID()).thenReturn("1L");

    HouseID houseIDDouble = mock(HouseID.class);
    when(houseIDDouble.getID()).thenReturn("HouseID");

    RoomName roomNameDouble = mock(RoomName.class);
    when(roomNameDouble.getRoomName()).thenReturn("RoomName");

    Dimension dimensionDouble = mock(Dimension.class);
    when(dimensionDouble.getWidth()).thenReturn(1);
    when(dimensionDouble.getDepth()).thenReturn(1);
    when(dimensionDouble.getHeight()).thenReturn(1);

    RoomFloor roomFloorDouble = mock(RoomFloor.class);
    when(roomFloorDouble.getFloor()).thenReturn(1);

    RoomDataModel roomDataModelDouble = mock(RoomDataModel.class);
    when(roomDataModelDouble.getRoomName()).thenReturn("RoomName");
    when(roomDataModelDouble.getRoomID()).thenReturn("1L");
    when(roomDataModelDouble.getHouseID()).thenReturn("HouseID");
    when(roomDataModelDouble.getFloor()).thenReturn(1);
    when(roomDataModelDouble.getWidth()).thenReturn(1);
    when(roomDataModelDouble.getDepth()).thenReturn(1);
    when(roomDataModelDouble.getHeight()).thenReturn(1);

    List<RoomDataModel> roomDataModels = List.of(roomDataModelDouble);

    Room expected = roomFactoryDouble.createRoom(houseIDDouble, roomNameDouble, dimensionDouble,
        roomFloorDouble, roomIDDouble);

    // Act
    List<Room> rooms = roomDataModelAssembler.toDomain(roomDataModels);

    // Assert
    assertEquals(expected, rooms.get(0));
  }
}
