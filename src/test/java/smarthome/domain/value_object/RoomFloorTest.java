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

class RoomFloorTest {

  /**
   * Test to check if the object is created with valid arguments.
   */
  @Test
  void shouldInstantiateRoomFloorWhenFloorIsPositive() {
    // Arrange
    int floor = 0;
    // Act
    RoomFloor roomFloor = new RoomFloor(floor);
    // Assert
    assertNotNull(roomFloor);

  }

  /**
   * Test to check if the object is created with invalid floor.
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenFloorIsBellowAllowedRange() {
    // Arrange
    int floor = -36;
    String expectedMessage = "Invalid floor number.";
    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new RoomFloor(floor));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if the object is created with floor equal to 163, which is invalid.
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenFloorIsAboveAllowedRange() {
    // Arrange
    int floor = 163;
    String expectedMessage = "Invalid floor number.";
    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new RoomFloor(floor));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to check if the object returned correctly.
   */
  @Test
  void shouldReturnFloorWhenGetFloorIsCalled() {
    // Arrange
    int floor = 0;
    RoomFloor roomFloor = new RoomFloor(floor);
    // Act
    int result = roomFloor.getFloor();
    // Assert
    assertEquals(floor, result);
  }

  /**
   * Test to check if the object with the same floor is equal.
   */
  @Test
  void shouldReturnTrueWhenComparingTwoRoomFloorsWithSameFloor() {
    // Arrange
    int floor = 0;
    RoomFloor roomFloor1 = new RoomFloor(floor);
    RoomFloor roomFloor2 = new RoomFloor(floor);
    // Act
    boolean result = roomFloor1.equals(roomFloor2);
    // Assert
    assertTrue(result);
  }

  /**
   * Should return Room floor in a string.
   */
  @Test
  void shouldReturnFloorWhenToStringIsCalled() {
    // Arrange
    int floor = 0;
    RoomFloor roomFloor = new RoomFloor(floor);
    String expected = "RoomFloor: floor=0";
    // Act
    String result = roomFloor.toString();
    // Assert
    assertEquals(expected, result);
  }

  /**
   * Test to check if the same object is equal to itself.
   */
  @Test
  void shouldReturnTrueWhenComparingSameRoomFloor() {
    // Arrange
    int floor = 0;
    RoomFloor roomFloor = new RoomFloor(floor);
    // Act
    boolean result = roomFloor.equals(roomFloor);
    // Assert
    assertTrue(result);
  }

  /**
   * Should return the room floor.
   */
  @Test
  void shouldReturnCorrectFloorWhenGetFloorIsCalled() {
    // Arrange
    int floor = 5;
    RoomFloor roomFloor = new RoomFloor(floor);
    // Act
    int result = roomFloor.getFloor();
    // Assert
    assertEquals(floor, result);
  }

  /**
   * Test to check if two objects with different floors are not equal.
   */
  @Test
  void shouldReturnFalseWhenComparingRoomFloorWithDifferentFloor() {
    // Arrange
    int floor1 = 5;
    int floor2 = 6;
    RoomFloor roomFloor1 = new RoomFloor(floor1);
    RoomFloor roomFloor2 = new RoomFloor(floor2);
    // Act
    boolean result = roomFloor1.equals(roomFloor2);
    // Assert
    assertFalse(result);
  }

  /**
   * Test hashCode method.
   */
  @Test
  void shouldReturnExpectedHashCode_whenCallingHashCode() {
    // Arrange
    int floor = 5;
    RoomFloor roomFloor = new RoomFloor(floor);

    // Act
    int result = roomFloor.hashCode();

    // Assert
    assertEquals(floor, result);
  }

  /**
   * Tests if equals method returns false when the object is not an instance of RoomFloor
   */
  @Test
  void shouldReturnFalseWhenComparingRoomFloorWithDifferentObject() {
    // Arrange
    int floor = 5;
    RoomFloor roomFloor = new RoomFloor(floor);
    Object object = new Object();
    // Act
    boolean result = roomFloor.equals(object);
    // Assert
    assertFalse(result);
  }
}