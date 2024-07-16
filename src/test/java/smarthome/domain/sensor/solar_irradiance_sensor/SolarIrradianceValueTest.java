/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.solar_irradiance_sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class SolarIrradianceValueTest {

  /**
   * Tests the instantiation of SolarIrradianceValue when the constructor arguments are valid.
   */
  @Test
  void shouldInstantiateSolarIrradianceValue_whenConstructorArgumentsAreValid() {
    // Arrange
    int value = 1;

    // Act
    SolarIrradianceValue result = new SolarIrradianceValue(value);

    // Assert
    assertNotNull(result);
  }

  /**
   * Tests the toString method of SolarIrradianceValue.
   */
  @Test
  void shouldReturnStringValue_whenToStringIsCalled() {
    // Arrange
    int value = 1;
    SolarIrradianceValue solarIrradianceValue = new SolarIrradianceValue(value);

    // Act
    String result = solarIrradianceValue.toString();

    // Assert
    assertEquals(result, "1");
  }
}
