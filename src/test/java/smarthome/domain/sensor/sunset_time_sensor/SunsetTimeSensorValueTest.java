/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.sunset_time_sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import org.junit.jupiter.api.Test;

class SunsetTimeSensorValueTest {

  /**
   * Test to create a SunsetTimeSensorValue
   */
  @Test
  void shouldCreateSunsetTimeSensorValue_whenValueIsValid() {
    //Arrange
    LocalTime value = LocalTime.of(18, 30, 0);
    String expectedValue = "Sunset Time: 18:30:00";

    //Act
    SunsetTimeSensorValue sunsetTimeSensorValue = new SunsetTimeSensorValue(value);

    //Asset
    assertEquals(expectedValue, sunsetTimeSensorValue.toString());
  }

  /**
   * Test to throw IllegalArgumentException when value is null
   */
  @Test
  void shouldThrowIllegalArgumentException_whenValueIsNull() {
    //Arrange
    LocalTime value = null;
    String expectedMessage = "Time is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SunsetTimeSensorValue(value));

    //Asset
    assertEquals(expectedMessage, exception.getMessage());
  }

}