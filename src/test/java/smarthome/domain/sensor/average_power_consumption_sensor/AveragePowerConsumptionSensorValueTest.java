/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.average_power_consumption_sensor;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AveragePowerConsumptionSensorValueTest {

  /**
   * See if the constructor works.
   */
  @Test
  void shouldInstantiateAveragePowerConsumptionSensorValue_whenValueIsValid() {
    //Arrange
    double dValue = 12.3;

    //Act
    AveragePowerConsumptionSensorValue result = new AveragePowerConsumptionSensorValue(dValue);

    //Assert
    assertNotNull(result);
  }

  /**
   * See if the getValue method works.
   */
  @Test
  void seeIfGetValueWorks() {
    //Arrange
    double dValue = 12.3;
    AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(
        dValue);
    double expected = 12.3;
    //Act
    double actualResult = averagePowerConsumptionSensorValue.getValue();
    //Assert
    Assertions.assertEquals(expected, actualResult);
  }


  /**
   * See if the toString method works.
   */

  @Test
  void seeIfToStringWorks() {
    //Arrange
    double dValue = 12.3;
    AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(
        dValue);
    String expected = "12.3";
    //Act
    String actualString = averagePowerConsumptionSensorValue.toString();

    //Assert
    Assertions.assertEquals(expected, actualString);
  }

  /**
   * See if the constructor throws an exception when the value is negative.
   */

  @Test
  void seeIfThrowsExceptionWhenNegativeValue() throws InstantiationException {
    // Arrange
    double dValue = -1;
    String expectedMessage = "Value must be positive";

    Exception exception = Assertions.assertThrows(IllegalArgumentException.class,
        () -> new AveragePowerConsumptionSensorValue(dValue));

    // Assert
    String actualMessage = exception.getMessage();
    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * See if the setValue method works with zero value.
   */
  @Test
  void seeIfGetValueWorksWithZero() {
    //Arrange
    double dValue = 0;
    AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(
        dValue);
    double expected = 0;
    //Act
    double actualResult = averagePowerConsumptionSensorValue.getValue();
    //Assert
    Assertions.assertEquals(expected, actualResult);
  }

  /**
   * See if the equals method works.
   */
  @Test
  void seeIfEqualsWorks() {
    //Arrange
    double dValue = 12.3;
    AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(
        dValue);
    AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue1 = new AveragePowerConsumptionSensorValue(
        dValue);
    //Act
    boolean actualResult = averagePowerConsumptionSensorValue.equals(
        averagePowerConsumptionSensorValue1);
    //Assert
    Assertions.assertTrue(actualResult);
  }

  @Test
  void shouldReturnEqualsUpToPrecisionOfThreeDecimals() {
    // Arrange
    double dValue = 12.3;
    AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(
        dValue);
    AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue1 = new AveragePowerConsumptionSensorValue(
        dValue + 0.001);
    // Act
    boolean actualResult = averagePowerConsumptionSensorValue.equals(
        averagePowerConsumptionSensorValue1);
    // Assert
    Assertions.assertTrue(actualResult);
  }

  @Test
  void shouldReturnFalseWhenNotInstanceOfAveragePowerConsumptionSensorValue() {
    // Arrange
    double dValue = 12.3;
    AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(
        dValue);
    // Act
    boolean actualResult = averagePowerConsumptionSensorValue.equals(new Object());
    // Assert
    Assertions.assertFalse(actualResult);
  }

  @Test
  void shouldReturnFalseWhenDifferentValues() {
    // Arrange
    double dValue = 12.3;
    AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(
        dValue);
    AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue1 = new AveragePowerConsumptionSensorValue(
        dValue + 0.002);
    // Act
    boolean actualResult = averagePowerConsumptionSensorValue.equals(
        averagePowerConsumptionSensorValue1);
    // Assert
    Assertions.assertFalse(actualResult);
  }

  @Test
  void shouldReturnFalseWhenDifferentValuesOutsideEpsilonRange() {
    // Arrange
    double dValue = 12.3;
    AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(
        dValue + 0.005);
    AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue1 = new AveragePowerConsumptionSensorValue(
        dValue + 0.003);
    // Act
    boolean actualResult = averagePowerConsumptionSensorValue.equals(
        averagePowerConsumptionSensorValue1);
    // Assert
    Assertions.assertFalse(actualResult);
  }

  @Test
  void equalsObjectsShouldHaveTheSameHashCode() {
    // Arrange
    double dValue = 12.3;
    AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(
        dValue);
    AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue2 = new AveragePowerConsumptionSensorValue(
        dValue);
    boolean expected = averagePowerConsumptionSensorValue.equals(
        averagePowerConsumptionSensorValue2);

    // Act
    boolean result = averagePowerConsumptionSensorValue.hashCode()
        == averagePowerConsumptionSensorValue2.hashCode();

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void shouldNotReturnZero_WhenValuesAreNonZero() {
    // Arrange
    double dValue = 12.3;
    AveragePowerConsumptionSensorValue value = new AveragePowerConsumptionSensorValue(dValue);

    // Act
    int hashCode = value.hashCode();

    // Assert
    assertNotEquals(0, hashCode);
  }


}
