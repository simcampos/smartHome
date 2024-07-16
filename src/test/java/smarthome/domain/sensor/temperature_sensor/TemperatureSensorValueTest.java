/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.temperature_sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TemperatureSensorValueTest {

  /**
   * Test if the constructor is properly created.
   *
   * @throws InstantiationException If the sensor type does not exist.
   */
  @Test
  void shouldInstantiateTemperatureSensorValue() {
    //Arrange
    double temperature = 10.5;

    //Act
    TemperatureSensorValue result = new TemperatureSensorValue(temperature);

    //Assert
    assertNotNull(result);
  }

  /**
   * Test if the constructor throws an exception when the temperature is lower than the minimum.
   *
   * @throws InstantiationException If the sensor type does not exist.
   */
  @Test
  void shouldThrowExceptionWhenTemoreatureIsLowerThanMinimum() {

    //Arrange
    double temperature = -273.16;

    String expectedMessage = "Temperature value must be above or equal to -273.15";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new TemperatureSensorValue(temperature));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test if the getTemperature method returns the correct temperature.
   */
  @Test
  void shouldReturnStringValueOfTemperature() {

    //Arrange
    double temperature =
        -273.15;

    TemperatureSensorValue temperatureSensorValue = new TemperatureSensorValue(temperature);

    String expectedValue = "-273.15";

    //Act
    String result = new TemperatureSensorValue(temperature).toString();

    //Assert
    assertEquals(expectedValue, result);
  }

  /**
   * Test if the equals method returns true when the temperature values are equal.
   */
  @Test
  void shouldReturnTrue_whenTemperatureSensorValuesAreEqual() {

    //Arrange
    double temperature = 10.5;

    TemperatureSensorValue temperatureSensorValue1 = new TemperatureSensorValue(temperature);
    TemperatureSensorValue temperatureSensorValue2 = new TemperatureSensorValue(temperature);

    //Act
    boolean result = temperatureSensorValue1.equals(temperatureSensorValue2);

    //Assert
    assertTrue(result);
  }

  /**
   * Test if the equals method returns false when the temperature values are not equal.
   */
  @Test
  void shouldReturnFalse_whenTemperatureSensorValuesAreNotEqual() {

    //Arrange
    double temperature1 = 10.5;
    double temperature2 = 20.5;

    TemperatureSensorValue temperatureSensorValue1 = new TemperatureSensorValue(temperature1);
    TemperatureSensorValue temperatureSensorValue2 = new TemperatureSensorValue(temperature2);

    //Act
    boolean result = temperatureSensorValue1.equals(temperatureSensorValue2);

    //Assert
    assertFalse(result);
  }

  /**
   * Test if the equals method returns false when the object is not a TemperatureSensorValue.
   */
  @Test
  void shouldReturnFalse_whenObjectIsNotTemperatureSensorValue() {

    //Arrange
    double temperature = 10.5;

    TemperatureSensorValue temperatureSensorValue = new TemperatureSensorValue(temperature);

    //Act
    boolean result = temperatureSensorValue.equals(new Object());

    //Assert
    assertFalse(result);
  }

  /**
   * Test if the hashCode method returns the value for TemperatureSensorValue.
   */
  @Test
  void shouldReturnHashCodeOfTemperatureSensorValue() {

    //Arrange
    double temperature = 10.5;

    TemperatureSensorValue temperatureSensorValue = new TemperatureSensorValue(temperature);

    //Act
    int result = temperatureSensorValue.hashCode();

    //Assert
    assertEquals(temperatureSensorValue.hashCode(), result);
  }

  /**
   * Test if the hashCode method returns the value for TemperatureSensorValue.
   */
  @Test
  void shouldReturnEqualHashCodeForIdenticalObjects() {

    //Arrange
    double temperature = 10;

    TemperatureSensorValue temperatureSensorValue = new TemperatureSensorValue(temperature);
    TemperatureSensorValue temperatureSensorValue2 = new TemperatureSensorValue(temperature);
    boolean expected = temperatureSensorValue.equals(temperatureSensorValue2);
    //Act
    boolean result = temperatureSensorValue.hashCode() == temperatureSensorValue2.hashCode();

    //Assert
    assertEquals(expected, result);
  }

  /**
   * Test if hashCode returns diferent from zero
   */
  @Test
  void shouldReturnNotEqualsForHashCodeDifferentFromZero() {
    //Arrange
    double temperature = 0;
    double temperature1 = 10;

    TemperatureSensorValue temperatureSensorValue = new TemperatureSensorValue(temperature);
    TemperatureSensorValue temperatureSensorValue2 = new TemperatureSensorValue(temperature1);
    int expected = temperatureSensorValue.hashCode();
    //Act
    int result = temperatureSensorValue2.hashCode();

    //Assert
    assertNotEquals(expected, result);
  }
}