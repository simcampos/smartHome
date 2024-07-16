/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.instant_power_consumption_sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class InstantPowerConsumptionValueTest {

  /**
   * Test to check if the InstantPowerConsumptionValue can be instantiated.
   */
  @Test
  void shouldInstantiateInstantPowerConsumptionValue() {
    //arrange
    double instantPowerConsumptionValue = 0.0;

    //act
    InstantPowerConsumptionValue result = new InstantPowerConsumptionValue(
        instantPowerConsumptionValue);

    //assert
    assertNotNull(result);
  }

  /**
   * Test to check if the InstantPowerConsumptionValue throws an exception when the value is
   * negative.
   */
  @Test
  void shouldThrowExceptionWhenInstantPowerConsumptionValueIsNegative() {
    //arrange
    double instantPowerConsumptionValue = -1.0;

    String expectedMessage = "The value of the instant power consumption cannot be lower than 0.";

    //act
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new InstantPowerConsumptionValue(instantPowerConsumptionValue);
    });
    String actualMessage = exception.getMessage();

    //assert
    assertTrue(actualMessage.contains(expectedMessage));

  }

  /**
   * Test to check if the InstantPowerConsumptionValue returns the value in string.
   */
  @Test
  void shouldReturnInstantPowerConsumptionInString() {
    //arrange
    double instantPowerConsumptionValue = 25.0;
    InstantPowerConsumptionValue instantPowerConsumptionValueObject = new InstantPowerConsumptionValue(
        instantPowerConsumptionValue);

    String expectedValue = "25.0";
    //act
    String actualValue = instantPowerConsumptionValueObject.toString();

    //assert
    assertEquals(expectedValue, actualValue);
  }

}