/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smarthome.domain.value_object.Dimension;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.RoomFloor;
import smarthome.domain.value_object.RoomID;
import smarthome.domain.value_object.RoomName;

class RoomTest {

  /**
   * Test that the Room class can be instantiated with valid parameters.
   */
  @Test
  void shouldInstantiateRoom_WhenConstructorIsCalledWithValidParameters() {
    //Arrange
    HouseID houseID = mock(HouseID.class);
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = mock(RoomFloor.class);

    //Act
    Room room = new Room(houseID, roomName, dimension, roomFloor);

    //Assert
    assertNotNull(room);
  }

  /**
   * Test that the Room class can be instantiated with valid parameters, including the RoomID.
   */
  @Test
  void shouldInstantiateRoom_WhenConstructorIsCalledWithValidParametersIncludingRoomID() {
    //Arrange
    HouseID houseID = mock(HouseID.class);
    RoomID roomID = mock(RoomID.class);
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = mock(RoomFloor.class);

    //Act
    Room room = new Room(houseID, roomName, dimension, roomFloor, roomID);

    //Assert
    assertNotNull(room);

  }

  /**
   * Test that the Room class throws an IllegalArgumentException when the constructor is called with
   * a null RoomID.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullRoomID() {
    //Arrange
    HouseID houseID = mock(HouseID.class);
    RoomID roomID = null;
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = mock(RoomFloor.class);

    String expected = "RoomID is required";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Room(houseID, roomName, dimension, roomFloor, roomID));

    //Assert
    assertEquals(expected, exception.getMessage());
  }

  /**
   * Test that the Room class throws an IllegalArgumentException when the constructor is called with
   * a null HouseID.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullHouseID() {
    //Arrange
    HouseID houseID = null;
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = mock(RoomFloor.class);
    String expected = "HouseID is required";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Room(houseID, roomName, dimension, roomFloor));

    //Assert
    assertEquals(expected, exception.getMessage());
  }

  /**
   * Test that the Room class throws an IllegalArgumentException when the constructor is called with
   * a null RoomName.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullRoomName() {
    //Arrange
    HouseID houseID = mock(HouseID.class);
    RoomName roomName = null;
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = mock(RoomFloor.class);
    String expected = "RoomName is required";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Room(houseID, roomName, dimension, roomFloor));

    //Assert
    assertEquals(expected, exception.getMessage());
  }

  /**
   * Test that the Room class throws an IllegalArgumentException when the constructor is called with
   * a null Dimension.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullDimension() {
    //Arrange
    HouseID houseID = mock(HouseID.class);
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = null;
    RoomFloor roomFloor = mock(RoomFloor.class);
    String expected = "Dimension is required";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Room(houseID, roomName, dimension, roomFloor));

    //Assert
    assertEquals(expected, exception.getMessage());
  }

  /**
   * Test that the Room class throws an IllegalArgumentException when the constructor is called with
   * a null RoomFloor.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullRoomFloor() {
    //Arrange
    HouseID houseID = mock(HouseID.class);
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = null;
    String expected = "RoomFloor is required";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new Room(houseID, roomName, dimension, roomFloor));

    //Assert
    assertEquals(expected, exception.getMessage());
  }

  /**
   * Tests if the houseID is returned correctly.
   */
  @Test
  void shouldReturnHouseID_WhenGetHouseIDisCalled() {
    //Arrange
    HouseID houseID = mock(HouseID.class);
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = mock(RoomFloor.class);

    Room room = new Room(houseID, roomName, dimension, roomFloor);

    //Act
    HouseID result = room.getHouseID();

    //Assert
    assertTrue(room.toString().contains(result.toString()));
  }

  /**
   * Tests if the room name is returned correctly.
   */
  @Test
  void shouldReturnRoomName_WhenGetRoomNameIsCalled() {
    //Arrange
    HouseID houseID = mock(HouseID.class);
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = mock(RoomFloor.class);

    Room room = new Room(houseID, roomName, dimension, roomFloor);

    //Act
    RoomName result = room.getName();

    //Assert
    assertTrue(room.toString().contains(result.toString()));
  }

  /**
   * Tests if the room dimension is returned correctly.
   */
  @Test
  void shouldReturnDimension_WhenGetDimensionIsCalled() {
    //Arrange
    HouseID houseID = mock(HouseID.class);
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = mock(RoomFloor.class);

    Room room = new Room(houseID, roomName, dimension, roomFloor);

    //Act
    Dimension result = room.getDimension();

    //Assert
    assertTrue(room.toString().contains(result.toString()));
  }

  /**
   * Tests if the room ID is returned correctly.
   */
  @Test
  void shouldReturnRoomFloor_WhenGetRoomFloorIsCalled() {
    //Arrange
    HouseID houseID = mock(HouseID.class);
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = mock(RoomFloor.class);

    Room room = new Room(houseID, roomName, dimension, roomFloor);

    //Act
    RoomFloor result = room.getFloor();

    //Assert
    assertTrue(room.toString().contains(result.toString()));
  }

  /**
   * Tests if the equals method returns false when comparing a room to a different class object.
   */
  @Test
  void shouldReturnFalse_WhenRoomIsComparedToNull() {
    //Arrange
    HouseID houseID = mock(HouseID.class);
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = mock(RoomFloor.class);

    Room room = new Room(houseID, roomName, dimension, roomFloor);

    //Act
    boolean result = room.equals(new Object());

    //Assert
    assertFalse(result);
  }

  /**
   * Tests if the equals method returns false when comparing two different rooms.
   */
  @Test
  void shouldReturnFalse_WhenTwoRoomsHaveDifferentIDs() {
    //Arrange
    HouseID houseID = mock(HouseID.class);
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = mock(RoomFloor.class);

    Room room1 = new Room(houseID, roomName, dimension, roomFloor);
    Room room2 = new Room(houseID, roomName, dimension, roomFloor);

    //Act
    boolean result = room1.equals(room2);

    //Assert
    assertFalse(result);
  }


  /**
   * Tests if the getID method returns the correct room ID.
   */
  @Test
  void shouldReturnRoomID() {
    //Arrange
    HouseID houseID = mock(HouseID.class);
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = mock(RoomFloor.class);

    Room room = new Room(houseID, roomName, dimension, roomFloor);
    //Act
    RoomID result = room.getID();

    //Assert
    assertNotNull(result);
  }

  /**
   * Tests if the equals method returns true when comparing the same room.
   */
  @Test
  void shouldReturnTrue_WhenObjectIsComparedToItself() {
    //Arrange
    HouseID houseID = mock(HouseID.class);
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = mock(RoomFloor.class);

    Room room = new Room(houseID, roomName, dimension, roomFloor);

    //Act
    boolean result = room.equals(room);

    //Assert
    assertTrue(result);
  }

  /**
   * Test of method hashcode.
   */
  @Test
  void shouldReturnHashCode_WhenHashCodeIsCalled() {
    // Arrange
    HouseID houseID = mock(HouseID.class);
    RoomName roomName = mock(RoomName.class);
    Dimension dimension = mock(Dimension.class);
    RoomFloor roomFloor = mock(RoomFloor.class);

    Room room = new Room(houseID, roomName, dimension, roomFloor);

    try (MockedConstruction<RoomID> mocked = mockConstruction(RoomID.class, (mock, context) -> {
      when(mock.toString()).thenReturn("1");
    })) {
      int expected = room.getID().hashCode();
      // Act
      int result = room.hashCode();

      // Assert
      assertEquals(expected, result);
    }
  }
}


