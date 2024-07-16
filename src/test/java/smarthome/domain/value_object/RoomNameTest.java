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

class RoomNameTest {

  /**
   * Tests the RoomName constructor with a valid room name.
   */
  @Test
  void shouldGetValidObject_whenUsingValidRoomName() {
    // Arrange
    String validRoomName = "Living Room 2";

    // Act
    RoomName roomName = new RoomName(validRoomName);

    // Assert
    assertNotNull(roomName);
  }

  /**
   * Tests the RoomName constructor with a null room name.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenRoomNameNull() {
    // Arrange
    String nullRoomName = null;
    String expectedMessage = "The room name cannot be null, blank, or empty.";

    // Act
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new RoomName(nullRoomName)
    );

    // Assert
    String exceptionMessage = exception.getMessage();

    assertTrue(exceptionMessage.contains(expectedMessage));
  }

  /**
   * Tests the RoomName constructor with a blank room name.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenRoomNameBlank() {
    // Arrange
    String blankRoomName = " ";
    String expectedMessage = "The room name cannot be null, blank, or empty.";

    // Act
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new RoomName(blankRoomName)
    );

    // Assert
    String exceptionMessage = exception.getMessage();

    assertTrue(exceptionMessage.contains(expectedMessage));
  }

  /**
   * Tests the RoomName constructor with an empty room name.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenRoomNameEmpty() {
    // Arrange
    String emptyRoomName = "";
    String expectedMessage = "The room name cannot be null, blank, or empty.";

    // Act
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new RoomName(emptyRoomName)
    );

    // Assert
    String exceptionMessage = exception.getMessage();

    assertTrue(exceptionMessage.contains(expectedMessage));
  }

  /**
   * Tests the RoomName constructor with a room name containing special characters.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenRoomNameContainsSpecialCharacters() {
    // Arrange
    String roomNameWithSpecialCharacters = "Living Room 2!";
    String expectedMessage = "The room name can only contain letters and numbers.";

    // Act
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new RoomName(roomNameWithSpecialCharacters)
    );

    // Assert
    String exceptionMessage = exception.getMessage();

    assertTrue(exceptionMessage.contains(expectedMessage));
  }

  /**
   * Tests the getRoomName method.
   */
  @Test
  void shouldGetRoomName_whenCallingGetRoomName() {
    // Arrange
    String roomName = "Kitchen 1";
    RoomName roomNameObject = new RoomName(roomName);

    // Act
    String actualRoomName = roomNameObject.getRoomName();

    // Assert
    assertEquals(actualRoomName, roomName);
  }

  /**
   * Tests the equals method with the same object.
   */
  @Test
  void shouldReturnTrue_whenComparingSameObject() {
    // Arrange
    String roomName = "Kitchen 1";
    RoomName roomNameObject = new RoomName(roomName);

    // Act
    boolean result = roomNameObject.equals(roomNameObject);

    // Assert
    assertTrue(result);
  }

  /**
   * Tests the equals method with a different object with the same name.
   */
  @Test
  void shouldReturnTrue_whenComparingDifferentObjectWithSameName() {
    // Arrange
    String roomName = "Kitchen 1";
    RoomName roomNameObject = new RoomName(roomName);
    RoomName roomNameObject2 = new RoomName(roomName);

    // Act
    boolean result = roomNameObject.equals(roomNameObject2);

    // Assert
    assertTrue(result);
  }

  /**
   * Tests the equals method with a different object.
   */
  @Test
  void shouldReturnFalse_whenComparingDifferentObject() {
    // Arrange
    String roomName = "Kitchen 1";
    RoomName roomNameObject = new RoomName(roomName);
    RoomName roomNameObject2 = new RoomName("Living Room 2");

    // Act
    boolean result = roomNameObject.equals(roomNameObject2);

    // Assert
    assertFalse(result);
  }

  /**
   * Tests the toString method.
   */
  @Test
  void shouldReturnRoomName_whenCallingToString() {
    // Arrange
    String roomName = "Kitchen 1";
    RoomName roomNameObject = new RoomName(roomName);

    // Act
    String actualRoomName = roomNameObject.toString();

    // Assert
    assertEquals(actualRoomName, roomName);
  }

  /**
   * Tests the hashCode method.
   */

  @Test
  void shouldReturnHashCode_whenCallingHashCode() {
    // Arrange
    String roomName = "Kitchen 1";
    RoomName roomNameObject = new RoomName(roomName);

    int expectedHashCode = roomName.hashCode();

    // Act
    int actualHashCode = roomNameObject.hashCode();

    // Assert
    assertEquals(expectedHashCode, actualHashCode);
  }

  /**
   * Tests if equals method returns false when the object is not an instance of RoomName
   */
  @Test
  void shouldReturnFalseWhenComparingRoomNameWithDifferentObject() {
    // Arrange
    String roomName = "Kitchen 1";
    RoomName roomNameObject = new RoomName(roomName);

    // Act
    boolean result = roomNameObject.equals(new Object());

    // Assert
    assertFalse(result);
  }
}
