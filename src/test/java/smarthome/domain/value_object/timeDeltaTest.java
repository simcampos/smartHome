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

class timeDeltaTest {

  @Test
  void shouldInstantiateTimeDelta_whenGivenValidParameters() {
    // Arrange
    int minutes = 10;

    // Act
    TimeDelta timeDelta = new TimeDelta(minutes);

    // Assert
    assertNotNull(timeDelta);
  }

  @Test
  void shouldThrowIllegalArgumentException_whenGivenNegativeMinutes() {
    // Arrange
    int minutes = -10;

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new TimeDelta(minutes));

    // Assert
    assertEquals("Minutes must be a positive integer", exception.getMessage());
  }

  @Test
  void shouldReturnMinutes_whenGetMinutesIsCalled() {
    // Arrange
    int minutes = 10;
    TimeDelta timeDelta = new TimeDelta(minutes);

    // Act
    int result = timeDelta.getMinutes();

    // Assert
    assertEquals(minutes, result);
  }

  @Test
  void shouldReturnTrue_whenComparingTwoEqualTimeDeltas() {
    // Arrange
    int minutes = 10;
    TimeDelta timeDelta1 = new TimeDelta(minutes);
    TimeDelta timeDelta2 = new TimeDelta(minutes);

    // Act
    boolean result = timeDelta1.equals(timeDelta2);

    // Assert
    assertTrue(result);
  }

  @Test
  void shouldReturnFalse_whenComparingTwoDifferentTimeDeltas() {
    // Arrange
    int minutes1 = 10;
    int minutes2 = 20;
    TimeDelta timeDelta1 = new TimeDelta(minutes1);
    TimeDelta timeDelta2 = new TimeDelta(minutes2);

    // Act
    boolean result = timeDelta1.equals(timeDelta2);

    // Assert
    assertFalse(result);
  }

  @Test
  void shouldReturnHashCode_whenHashCodeIsCalled() {
    // Arrange
    int minutes = 10;
    TimeDelta timeDelta = new TimeDelta(minutes);

    // Act
    int result = timeDelta.hashCode();

    // Assert
    assertEquals(minutes, result);
  }

  @Test
  void shouldReturnString_whenToStringIsCalled() {
    // Arrange
    int minutes = 10;
    TimeDelta timeDelta = new TimeDelta(minutes);
    String expected = "timeDelta:" + "minutes=" + minutes;

    // Act
    String result = timeDelta.toString();

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void equalObjectsShouldHaveSameHashCode() {
    // Arrange
    int minutes = 10;
    TimeDelta timeDelta1 = new TimeDelta(minutes);
    TimeDelta timeDelta2 = new TimeDelta(minutes);

    // Act
    int hashCode1 = timeDelta1.hashCode();
    int hashCode2 = timeDelta2.hashCode();

    // Assert
    assertEquals(hashCode1, hashCode2);
  }

  @Test
  void shouldReturnFalseWhenComparingTimeDeltaToOtherObject() {
    // Arrange
    int minutes = 10;
    TimeDelta timeDelta = new TimeDelta(minutes);
    Object object = new Object();

    // Act
    boolean result = timeDelta.equals(object);

    // Assert
    assertFalse(result);
  }
}