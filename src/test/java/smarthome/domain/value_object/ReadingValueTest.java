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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ReadingValueTest {

  /**
   * Test if the ReadingValue is created successfully
   */
  @Test
  void shouldCreateReadingValue() {
    //Arrange
    String readingValue = "20";
    //Act
    ReadingValue readingValue1 = new ReadingValue(readingValue);
    //Assert
    assertEquals(readingValue, readingValue1.getValue());
  }

  /**
   * Test if the ReadingValue is created successfully
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenReadingValueIsNull() {
    //Arrange
    String readingValue = null;
    String expected = "Reading Value is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ReadingValue(readingValue));
    //Assert
    assertEquals(expected, exception.getMessage());
  }

  /**
   * Test if getReadingValue returns the correct value
   */
  @Test
  void shouldReturnReadingValue() {
    //Arrange
    String readingValue = "20";
    ReadingValue readingValue1 = new ReadingValue(readingValue);
    //Act
    String result = readingValue1.getValue();
    //Assert
    assertEquals(readingValue, result);
  }

  /**
   * Test if equals with Reflexivity
   */
  @Test
  void shouldReturnTrueWhenComparingReadingValueWithItself() {
    //Arrange
    String readingValue = "20";
    ReadingValue readingValue1 = new ReadingValue(readingValue);
    //Act
    boolean result = readingValue1.equals(readingValue1);
    //Assert
    assertTrue(result);
  }

  @Test
  void shouldReturnFalse_WhenEqualsIsCalledWithDifferentObject () {
    // Arrange
    String readingValue1 = "21";
    String readingValue2 = "22";

    ReadingValue readingValueObject1 = new ReadingValue(readingValue1);
    ReadingValue readingValueObject2 = new ReadingValue(readingValue2);

    // Act
    boolean result = readingValueObject1.equals(readingValueObject2);

    // Assert
    assertFalse(result);
  }

  /**
   * Test if equals with Symmetry
   */
  @Test
  void shouldReturnTrueWhenComparingTwoReadingValuesWithSameReadingValue() {
    //Arrange
    String readingValue = "20";
    ReadingValue readingValue1 = new ReadingValue(readingValue);
    ReadingValue readingValue2 = new ReadingValue(readingValue);
    //Act
    boolean result = readingValue1.equals(readingValue2);
    //Assert
    assertTrue(result);
  }

  /**
   * Test if equals with Transitivity
   */
  @Test
  void shouldReturnTrueWhenComparingThreeReadingValuesWithSameReadingValue() {
    //Arrange
    String readingValue = "20";
    ReadingValue readingValue1 = new ReadingValue(readingValue);
    ReadingValue readingValue2 = new ReadingValue(readingValue);
    ReadingValue readingValue3 = new ReadingValue(readingValue);
    //Act
    boolean result = readingValue1.equals(readingValue2) && readingValue2.equals(readingValue3);
    //Assert
    assertTrue(result);
  }

  /**
   * Test if equals with Consistency
   */
  @Test
  void shouldReturnTrueWhenComparingTwoReadingValuesWithSameReadingValueMultipleTimes() {
    //Arrange
    String readingValue = "20";
    ReadingValue readingValue1 = new ReadingValue(readingValue);
    ReadingValue readingValue2 = new ReadingValue(readingValue);
    //Act
    boolean result = readingValue1.equals(readingValue2);
    //Assert
    assertTrue(result);
  }

  /**
   * Test if equals with null
   */
  @Test
  void shouldReturnFalseWhenComparingReadingValueWithNull() {
    //Arrange
    String readingValue = "20";
    ReadingValue readingValue1 = new ReadingValue(readingValue);
    //Act
    boolean result = readingValue1.equals(null);
    //Assert
    assertFalse(result);
  }

  @Test
  void shouldReturnFalse_WhenComparingWithDifferentClass () {
    // Arrange
    String readingValue = "20";
    ReadingValue readingValueObject = new ReadingValue(readingValue);

    // Act
    boolean result = readingValueObject.equals(new Object());

    // Assert
    assertFalse(result);
  }

  /**
   * Test the hashCode method
   */
  @Test
  void shouldReturnHashCode() {
    //Arrange
    String readingValue = "20";
    ReadingValue readingValue1 = new ReadingValue(readingValue);
    //Act
    int result = readingValue1.hashCode();
    //Assert
    assertEquals(readingValue.hashCode(), result);
  }
}
