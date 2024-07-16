/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValueSimulatorTest {

  /**
   * Test if the random value is between the lower and upper bond.
   */
  @Test
  void generateRandomValueUsingDoubleWithValidBonds() {
    //Arrange
    double lowerBond = -1.0;
    double upperBond = 0.0;
    //Act
    double randomValue = ValueSimulator.generateRandomValue(lowerBond, upperBond);
    //Assert
    assertTrue(randomValue >= lowerBond && randomValue <= upperBond);
  }
  /**
   * Test if the random value is between the lower and upper bond when non-zero values.
   */
  @Test
  void generateRandomValueUsingDoubleWithValidNonZeroBonds() {
    //Arrange
    double lowerBond = 5.2;
    double upperBond = 9.7;
    //Act
    double randomValue = ValueSimulator.generateRandomValue(lowerBond, upperBond);
    //Assert
    assertTrue(randomValue >= lowerBond && randomValue <= upperBond);
  }

  /**
   * Test if exception is thrown when the lower bond is greater than the upper bond.
   */
  @Test
  void shouldThrowExceptionWhenGenerateRandomValueWhenBondsAreInverted() {
    //Arrange
    double lowerBond = 0.0;
    double upperBond = -1.0;
    String expectedMessage = "Lower bond should be less than upper bond";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> ValueSimulator.generateRandomValue(lowerBond, upperBond));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }
  /**
   * Should throw exception when the lower bond is greater than the upper bond.
   */
  @Test
  void shouldThrowExceptionWhenGenerateRandomValueUsingIntWhenBondsAreInverted() {
    //Arrange
    int lowerBond = 1;
    int upperBond = 0;
    String expectedMessage = "Lower bond should be less than upper bond";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> ValueSimulator.generateRandomValue(lowerBond, upperBond));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test if the random value is between the lower and upper bond.
   */

  @Test
  void generateRandomValueUsingIntWithValidBonds() {
    int lowerBond = -10;
    int upperBond = 0;
    int randomValue = ValueSimulator.generateRandomValue(lowerBond, upperBond);
    assertTrue(randomValue >= lowerBond && randomValue <= upperBond);
  }

  /**
   * Test if the random value is between the lower and upper bond when non-zero values.
   */
  @Test
  void generateIsolatedRandomValueUsingIntWithValidNonZeroBonds() {

    int lowerBond = 5;
    int upperBond = 10;
    int randomValue = ValueSimulator.generateRandomValue(lowerBond, upperBond);
    assertTrue(randomValue >= lowerBond && randomValue <= upperBond);
  }

  @Test
  void generateRandomBoolean() {
    boolean randomValue = ValueSimulator.generateRandomBoolean();
    assertTrue(randomValue || !randomValue);
  }
}