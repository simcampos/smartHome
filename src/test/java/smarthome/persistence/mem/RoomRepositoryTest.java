/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.mem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import smarthome.domain.room.Room;
import smarthome.domain.value_object.RoomID;


class RoomRepositoryTest {

  /**
   * Test for the save room method when room is valid
   */
  @Test
  void shouldReturnRoomAfterSavingIt_whenRoomIsValid() {
    // Arrange
    Room roomDouble = mock(Room.class);
    RoomRepository roomRepository = new RoomRepository();

    // Act
    Room room = roomRepository.save(roomDouble);

    // Assert
    assertEquals(roomDouble, room);
  }

  /**
   * Test for the save room method when room is null
   */
  @Test
  void shouldThrowException_whenRoomIsNull() {
    // Arrange
    Room roomDouble = null;
    RoomRepository roomRepository = new RoomRepository();

    String expectedMessage = "Room is required";

    // Act
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        roomRepository.save(roomDouble)
    );

    // Assert
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test for the save room method when room is null
   */
  @Test
  void shouldThrowException_whenRoomAlreadyExists() {
    // Arrange
    Room roomDouble = mock(Room.class);
    RoomRepository roomRepository = new RoomRepository();

    roomRepository.save(roomDouble);

    String expectedMessage = "Room already exists";

    // Act
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        roomRepository.save(roomDouble)
    );

    // Assert
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test for the find all rooms method when there are rooms
   */
  @Test
  void shouldReturnAllRooms() {
    // Arrange
    RoomID roomID1 = new RoomID("1");
    Room roomDouble1 = mock(Room.class);
    when(roomDouble1.getID()).thenReturn(roomID1);

    RoomID roomID2 = new RoomID("2");
    Room roomDouble2 = mock(Room.class);
    when(roomDouble2.getID()).thenReturn(roomID2);

    RoomRepository roomRepository = new RoomRepository();
    roomRepository.save(roomDouble1);
    roomRepository.save(roomDouble2);

    // Act
    List<Room> rooms = roomRepository.findAll();

    // Assert
    assertEquals(roomDouble2, rooms.get(1));
  }

  /**
   * Test for the find all rooms method when no room exist
   */
  @Test
  void shouldReturnEmptyList_whenNoRoomsExist() {
    // Arrange
    RoomRepository roomRepository = new RoomRepository();

    // Act
    List<Room> rooms = roomRepository.findAll();

    // Assert
    assertTrue(rooms.isEmpty());
  }

  /**
   * Test for the ofIdentity method when room exists
   */
  @Test
  void shouldReturnRoomById_whenRoomExists() {
    // Arrange
    RoomID roomID = new RoomID("1");
    Room roomDouble = mock(Room.class);
    when(roomDouble.getID()).thenReturn(roomID);

    RoomRepository roomRepository = new RoomRepository();
    roomRepository.save(roomDouble);

    // Act
    Room room = roomRepository.ofIdentity(roomID).get();

    // Assert
    assertEquals(roomDouble, room);
  }

  /**
   * Test for the ofIdentity method when room does not exist
   */
  @Test
  void shouldReturnException_whenRoomDoesNotExist() {
    // Arrange
    RoomID roomID1 = new RoomID("1");
    Room roomDouble = mock(Room.class);
    when(roomDouble.getID()).thenReturn(roomID1);

    RoomRepository roomRepository = new RoomRepository();
    roomRepository.save(roomDouble);

    RoomID roomID2 = new RoomID("2");

    // Act
    Optional<Room> room = roomRepository.ofIdentity(roomID2);

    // Assert
    assertEquals(Optional.empty(), room);
  }

  /**
   * Test for the containsOfIdentity method when room exists
   */
  @Test
  void shouldReturnTrue_whenRoomIdExists() {
    // Arrange
    RoomID roomID = new RoomID("1");
    Room roomDouble = mock(Room.class);
    when(roomDouble.getID()).thenReturn(roomID);

    RoomRepository roomRepository = new RoomRepository();
    roomRepository.save(roomDouble);

    // Act
    boolean result = roomRepository.containsOfIdentity(roomID);

    // Assert
    assertTrue(result);
  }

  /**
   * Test for the containsOfIdentity method when room does not exist
   */
  @Test
  void shouldReturnFalse_whenRoomIdDoesNotExists() {
    // Arrange
    RoomID roomID = new RoomID("1");
    Room roomDouble = mock(Room.class);
    when(roomDouble.getID()).thenReturn(roomID);

    RoomRepository roomRepository = new RoomRepository();
    roomRepository.save(roomDouble);

    RoomID roomID2 = new RoomID("2");
    // Act
    boolean result = roomRepository.containsOfIdentity(roomID2);

    // Assert
    assertFalse(result);
  }
}
