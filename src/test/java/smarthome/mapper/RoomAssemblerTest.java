/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;
import smarthome.utils.dto.RoomDTO;

class RoomAssemblerTest {

  /**
   * Test if the constructor of the RoomAssembler class can be called.
   */
  @Test
  void shouldInstantiateANewRoomAssembler() {
    // Arrange
    RoomAssembler roomAssembler = new RoomAssembler();

    // Act + Assert
    assertNotNull(roomAssembler);
  }

  /**
   * Test if the domainToDTO method returns a RoomDTO object when the room is valid.
   */
  @Test
  void shouldReturnARoomDTO_whenGivenARoom() {
    // Arrange
    String roomName = "Test Room";
    String dimension = "Width: 10, Height: 10, Depth: 10 ";
    int roomFloor = 1;
    String roomID = "1";

    Room room = mock(Room.class);

    when(room.getName()).thenReturn(mock(RoomName.class));
    when(room.getName().toString()).thenReturn(roomName);

    when(room.getDimension()).thenReturn(mock(Dimension.class));
    when(room.getDimension().toString()).thenReturn(dimension);

    when(room.getFloor()).thenReturn(mock(RoomFloor.class));
    when(room.getFloor().getFloor()).thenReturn(roomFloor);

    when(room.getID()).thenReturn(mock(RoomID.class));
    when(room.getID().toString()).thenReturn(roomID);

    RoomAssembler roomAssembler = new RoomAssembler();

    String expected = roomName + " " + dimension + " " + roomFloor + " " + roomID;

    // Act
    RoomDTO roomDTO = roomAssembler.domainToDTO(room);

    // Assert
    assertEquals(expected, roomDTO.toString());

  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the Room is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenRoomIsNull() {
    // Arrange
    Room room = null;
    RoomAssembler roomAssembler = new RoomAssembler();

    String expected = "Room is required";

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> roomAssembler.domainToDTO(room));

    String result = exception.getMessage();
    assertEquals(expected, result);
  }


  @Test
  void shouldReturnANewRoomDTOList_whenGivenARoomList() {
    // Arrange
    String roomName = "Test Room";
    String dimension = "Width: 10, Height: 10, Depth: 10 ";
    int roomFloor= 2;
    String roomID = "1";

    String roomName2 = "Test Room 2";
    String dimension2 = "Width: 10, Height: 20, Depth: 30 ";
    int roomFloor2 = 2;
    String roomID2 = "2";

    Room room = mock(Room.class);

    when(room.getName()).thenReturn(mock(RoomName.class));
    when(room.getName().toString()).thenReturn(roomName);

    when(room.getDimension()).thenReturn(mock(Dimension.class));
    when(room.getDimension().toString()).thenReturn(dimension);

    when(room.getFloor()).thenReturn(mock(RoomFloor.class));
    when(room.getFloor().getFloor()).thenReturn(roomFloor);

    when(room.getID()).thenReturn(mock(RoomID.class));
    when(room.getID().toString()).thenReturn(roomID);

    Room room2 = mock(Room.class);

    when(room2.getName()).thenReturn(mock(RoomName.class));
    when(room2.getName().toString()).thenReturn(roomName2);

    when(room2.getDimension()).thenReturn(mock(Dimension.class));
    when(room2.getDimension().toString()).thenReturn(dimension2);

    when(room2.getFloor()).thenReturn(mock(RoomFloor.class));
    when(room2.getFloor().getFloor()).thenReturn(roomFloor2);

    when(room2.getID()).thenReturn(mock(RoomID.class));
    when(room2.getID().toString()).thenReturn(roomID2);

    List<Room> rooms = Arrays.asList(room, room2);

    RoomAssembler roomAssembler = new RoomAssembler();

    RoomDTO roomDTO = new RoomDTO(roomName, dimension, roomFloor, roomID);
    RoomDTO roomDTO2 = new RoomDTO(roomName2, dimension2, roomFloor2, roomID2);

    List<RoomDTO> expected = List.of(roomDTO, roomDTO2);

    // Act
    List<RoomDTO> result = roomAssembler.domainToDTO(rooms);

    // Assert
    assertEquals(expected.toString(), result.toString());
  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the list of Rooms is
   * null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenRoomListIsNull() {
    // Arrange
    List<Room> rooms = null;
    RoomAssembler roomAssembler = new RoomAssembler();

    String expected = "The list of Rooms cannot be null.";

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> roomAssembler.domainToDTO(rooms));

    String result = exception.getMessage();
    assertEquals(expected, result);
  }

  /**
   * Should return empty list of RoomDTO when given empty list of Room.
   */
  @Test
  void shouldReturnEmptyList_whenGivenEmptyList() {
    // Arrange
    List<Room> rooms = List.of();
    RoomAssembler roomAssembler = new RoomAssembler();

    // Act
    List<RoomDTO> result = roomAssembler.domainToDTO(rooms);

    // Assert
    assertEquals(0, result.size());
  }
}
