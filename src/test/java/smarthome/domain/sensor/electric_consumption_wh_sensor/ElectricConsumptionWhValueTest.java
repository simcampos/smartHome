/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.electric_consumption_wh_sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ElectricConsumptionWhValueTest {

  /**
   * This test class is responsible for testing the ElectricConsumptionWhValue class. The
   * ElectricConsumptionWhValue class represents the value of electric consumption in watt-hours.
   * The ElectricConsumptionWhValue class is a ValueObject. The ElectricConsumptionWhValue class is
   * responsible for validating the value of electric consumption in watt-hours. The
   * ElectricConsumptionWhValue class is responsible for returning the value of electric consumption
   * in watt-hours. The ElectricConsumptionWhValue class is responsible for comparing the value of
   * electric consumption in watt-hours.
   */
  @Test
  void shouldInstantiateElectricConsumptionWhValue() {
    //Arrange
    int value = 0;
    //Act
    ElectricConsumptionWhValue whValue = new ElectricConsumptionWhValue(value);
    //Assert
    assertNotNull(whValue);
  }

  /**
   * This test method is responsible for testing the ElectricConsumptionWhValue constructor. The
   * ElectricConsumptionWhValue constructor is responsible for constructing a new
   * ElectricConsumptionWhValue with the given parameters. The ElectricConsumptionWhValue
   * constructor is responsible for validating the given parameters.
   */
  @Test
  void shouldThrowExceptionWhenValueIsNegative() {
    //Arrange
    int value = -1;
    String expectedMessage = "Consumption cannot be negative.";
    //Act
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new ElectricConsumptionWhValue(value));
    //Assert
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * This test method is responsible for testing the setValue method. The setValue method is
   * responsible for setting the value of electric consumption in watt-hours. The setValue method is
   * responsible for validating the value of electric consumption in watt-hours.
   */
  @Test
  void shouldReturnElectricConsumptionWh() {
    //Arrange
    int value = 5;
    ElectricConsumptionWhValue electricConsumptionWhValue = new ElectricConsumptionWhValue(value);
    String expected = "ElectricConsumptionWh{5}";
    //Act
    String actual = electricConsumptionWhValue.toString();
    //Assert
    assertEquals(expected, actual);
  }

  /**
   * This test method is responsible for testing the toString method. The toString method is
   * responsible for returning the value of electric consumption in watt-hours.
   */
  @Test
  void shouldReturnTrueWhenElectricConsumptionWhValuesAreEqual() {
    //Arrange
    int value = 5;
    ElectricConsumptionWhValue electricConsumptionWhValue1 = new ElectricConsumptionWhValue(value);
    ElectricConsumptionWhValue electricConsumptionWhValue2 = new ElectricConsumptionWhValue(value);
    //Act
    boolean result = electricConsumptionWhValue1.equals(electricConsumptionWhValue2);
    //Assert
    assertTrue(result);
  }

  /**
   * This test method is responsible for testing the equals method. The equals method is responsible
   * for comparing the value of electric consumption in watt-hours.
   */
  @Test
  void shouldReturnFalseWhenElectricConsumptionWhValuesAreNotEqual() {
    //Arrange
    int value1 = 5;
    int value2 = 10;
    ElectricConsumptionWhValue electricConsumptionWhValue1 = new ElectricConsumptionWhValue(value1);
    ElectricConsumptionWhValue electricConsumptionWhValue2 = new ElectricConsumptionWhValue(value2);
    //Act
    boolean result = electricConsumptionWhValue1.equals(electricConsumptionWhValue2);
    //Assert
    assertFalse(result);
  }

  /**
   * This test method is responsible for testing the equals method. The equals method is responsible
   * for comparing the value of electric consumption in watt-hours.
   */
  @Test
  void shouldReturnHashCode() {
    //Arrange
    int value = 5;
    ElectricConsumptionWhValue electricConsumptionWhValue = new ElectricConsumptionWhValue(value);
    //Act
    int actual = electricConsumptionWhValue.hashCode();
    //Assert
    assertEquals(5, actual);
  }

  /**
   * This test method is responsible for testing the hashCode method. The hashCode method is
   * responsible for returning the value of electric consumption in watt-hours.
   */
  @Test
  void shouldReturnFalseWhenObjectIsNotElectricConsumptionWhValue() {
    //Arrange
    int value = 5;
    ElectricConsumptionWhValue electricConsumptionWhValue = new ElectricConsumptionWhValue(value);
    //Act
    boolean result = electricConsumptionWhValue.equals(new Object());
    //Assert
    assertFalse(result);
  }


}