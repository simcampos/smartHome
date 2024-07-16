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

class TypeDescriptionTest {

  /**
   * Test the constructor
   */
  @Test
  void shouldInstantiateTypeDescriptionWhenGivenValidDescription() {
    //Arrange
    String description = "This is a valid description";
    //Act
    TypeDescription typeDescription = new TypeDescription(description);
    //Assert
    assertNotNull(typeDescription);
  }

  /**
   * Should throw IllegalArgumentException when given null.
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenGivenNullDescription() {
    //Arrange
    String description = null;
    String expectedMessage = "The value of 'description' should not null, blank, or empty.";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new TypeDescription(description));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should throw IllegalArgumentException when given blank.
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenGivenBlankDescription() {
    //Arrange
    String description = " ";
    String expectedMessage = "The value of 'description' should not null, blank, or empty.";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new TypeDescription(description));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should throw IllegalArgumentException when given description with more than 50 characters.
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenGivenDescriptionWithMoreThan50Characters() {
    //Arrange
    String description = "This is a description with more than 50 characters. This is a description with more than 50 characters. This is a description with more than 50 characters.";
    String expectedMessage = "The description cannot have more than 50 characters.";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new TypeDescription(description));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should instantiate type description with given description with 50 characters.
   */
  @Test
  void shouldInstantiateTypeDescriptionWhenGivenDescriptionWith50Characters() {
    //Arrange
    String description = "This is a description with exactly 50 characters..";
    //Act
    TypeDescription typeDescription = new TypeDescription(description);
    //Assert
    assertNotNull(typeDescription);
  }

  /**
   * Test the getDescription method.
   */
  @Test
  void shouldReturnDescriptionWhenGivenValidDescription() {
    //Arrange
    String description = "This is a valid description";
    TypeDescription typeDescription = new TypeDescription(description);
    //Act
    String result = typeDescription.getDescription();
    //Assert
    assertEquals(description, result);
  }

  /**
   * Test the equals method when two objects have the same type description.
   */
  @Test
  void shouldReturnTrueWhenGivenSameTypeDescription() {
    //Arrange
    String description = "This is a valid description";
    TypeDescription typeDescription = new TypeDescription(description);
    TypeDescription sameTypeDescription = new TypeDescription(description);
    //Act
    boolean result = typeDescription.equals(sameTypeDescription);
    //Assert
    assertTrue(result);
  }

  /**
   * Tests if the TypeDescription is returned when description have special characters.
   */
  @Test
  void shouldInstantiateTypeDescriptionWhenGivenDescriptionWithNumbersAndSpecialCharacters() {
    //Arrange
    String description = "Description with numbers 123 and characters !@#";
    //Act
    TypeDescription typeDescription = new TypeDescription(description);
    //Assert
    assertNotNull(typeDescription);
  }

  /**
   * Test the getId method
   */
  @Test
  void shouldReturnTheIdAsAsString_whenGetIDIsCalled() {
    //Arrange
    String description = "This is a valid description";
    TypeDescription typeDescription = new TypeDescription(description);

    //Act
    String result = typeDescription.getID();

    //Assert
    assertEquals(description, result);
  }

  /**
   * Test the hashCode method
   */
  @Test
  void shouldReturnTheHashCodeOfTheDescription_whenHashCodeIsCalled() {
    //Arrange
    String description = "This is a valid description";
    TypeDescription typeDescription = new TypeDescription(description);

    //Act
    int result = typeDescription.hashCode();

    //Assert
    assertEquals(description.hashCode(), result);
  }

  /**
   * Test the toString method
   */
  @Test
  void shouldReturnTheDescription_whenToStringIsCalled() {
    //Arrange
    String description = "123";
    TypeDescription typeDescription = new TypeDescription(description);

    //Act
    String result = typeDescription.toString();

    //Assert
    assertEquals(description, result);
  }

  @Test
  void shouldReturnFalse_WhenGivenDifferentTypeDescription() {
    //Arrange
    String description = "This is a valid description";
    String differentDescription = "This is a different description";

    TypeDescription typeDescription = new TypeDescription(description);
    TypeDescription differentTypeDescription = new TypeDescription(differentDescription);

    //Act
    boolean result = typeDescription.equals(differentTypeDescription);

    //Assert
    assertFalse(result);
  }

  @Test
  void shouldReturnTrue_WhenComparingTheSameObjects() {
    //Arrange
    String description = "This is a valid description";
    TypeDescription typeDescription = new TypeDescription(description);

    //Act
    boolean result = typeDescription.equals(typeDescription);

    //Assert
    assertTrue(result);
  }

  /**
   * Tests if the equals method returns false when the type description is compared to another type
   * description with a different ID.
   */
  @Test
  void shouldReturnFalse_WhenTypeDescriptionIsDifferentFromOtherTypeDescription() {
    //Arrange
    String description = "This is a valid description";
    String differentDescription = "This is a different description";

    TypeDescription typeDescription = new TypeDescription(description);
    TypeDescription differentTypeDescription = new TypeDescription(differentDescription);

    //Act
    boolean result = typeDescription.equals(differentTypeDescription);

    //Assert
    assertFalse(result);
  }

  /**
   * Tests if the equals method returns false when the object is not an instance of TypeDescription
   */
  @Test
  void shouldReturnFalse_WhenObjectIsNotInstanceOfTypeDescription() {
    //Arrange
    String description = "This is a valid description";
    TypeDescription typeDescription = new TypeDescription(description);

    //Act
    boolean result = typeDescription.equals(new Object());

    //Assert
    assertFalse(result);
  }
}