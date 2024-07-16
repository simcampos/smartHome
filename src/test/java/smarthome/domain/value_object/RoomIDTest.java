/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RoomIDTest {

  /**
   * Tests the correct instantiation of a RoomID
   */
  @Test
  void shouldGetValidObject_whenUsingValidStringInConstructor() {
    // Arrange
    String roomID = "Room12";
    // Act
    RoomID roomID1 = new RoomID(roomID);
    // Assert
    assertNotNull(roomID1);

  }

  /**
   * Tests if the exception is thrown with a null roomID.
   */
  @Test
  void shouldThrowException_whenRoomIdIsNull() {
    // Arrange
    String roomID = null;
    String expectedMessage = "'roomID' must be a non-empty string.";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new RoomID(roomID)
    );

    // Assert
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests if the exception is thrown with a blank roomID.
   */
  @Test
  void shouldThrowException_whenRoomIdIsBlank() {
    // Arrange
    String roomID = " ";
    String expectedMessage = "'roomID' must be a non-empty string.";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new RoomID(roomID)
    );

    // Assert
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests if the RoomID is correctly returned.
   */
  @Test
  void shouldReturnRoomID() {
    // Arrange
    String roomID = "Room12";
    RoomID roomID1 = new RoomID(roomID);

    String expected = "Room12";

    // Act
    String result = roomID1.getID();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Tests if the RoomID is equal to itself.
   */
  @Test
  void shouldReturnTrue_WhenRoomIdIsEqualToItself() {
    // Arrange
    String roomID = "Room12";
    RoomID roomID1 = new RoomID(roomID);

    // Act
    boolean result = roomID1.equals(roomID1);

    // Assert
    assertTrue(result);
  }

  /**
   * Tests if the RoomID is equal to another RoomID.
   */
  @Test
  void shouldReturnTrue_WhenRoomIdIsEqualToOtherRoomId() {
    // Arrange
    String roomID = "Room12";
    RoomID roomID1 = new RoomID(roomID);
    RoomID roomID2 = new RoomID(roomID);

    // Act
    boolean result = roomID1.equals(roomID2);

    // Assert
    assertTrue(result);
  }

  /**
   * Tests if the RoomID is different from another RoomID.
   */
  @Test
  void shouldReturnFalse_WhenRoomIdIsDifferentFromOtherRoomId() {
    // Arrange
    String roomID1Description = "Room12";
    String roomID2Description = "Room13";

    RoomID roomID1 = new RoomID(roomID1Description);
    RoomID roomID2 = new RoomID(roomID2Description);

    // Act
    boolean result = roomID1.equals(roomID2);

    // Assert
    assertFalse(result);
  }

  /**
   * Tests if the object in the hashcode is the same as the RoomID.
   */
  @Test
  void shouldReturnHashCode() {
    // Arrange
    String roomIDDescription = "Room12";
    RoomID roomID = new RoomID(roomIDDescription);

    // Act
    int result = roomID.hashCode();

    // Assert
    assertEquals(roomIDDescription.hashCode(), result);
  }

  @Test
  void shouldReturnRoomIDInString() {
    // Arrange
    String roomIDDescription = "Room12";
    RoomID roomID = new RoomID(roomIDDescription);
    String expected = "Room12";

    // Act
    String result = roomID.toString();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Tests if equals method returns false when the object is not an instance of RoomID
   */
  @Test
  void shouldReturnFalse_whenObjectIsNotInstanceOfRoomID() {
    // Arrange
    String roomIDDescription = "Room12";
    RoomID roomID = new RoomID(roomIDDescription);
    int floor = 5;
    RoomFloor roomFloor = new RoomFloor(floor);

    // Act
    boolean result = roomID.equals(new Object());

    // Assert
    assertFalse(result);
  }

}