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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DimensionTest {

  /**
   * Test to check if the object is created with valid arguments.
   */
  @Test
  void shouldGetValidObject_whenUsingValidArguments() {
    //Arrange
    int width = 13;
    int height = 15;
    int depth = 17;

    //Act
    Dimension dimension = new Dimension(width, height, depth);

    //Assert
    assertNotNull(dimension);
  }

  /**
   * Test to check if the object is created with invalid width
   */
  @Test
  void shouldThrowIllegalArgumentException_whenUsingInvalidWidth() {
    //Arrange
    int width = -13;
    int height = 15;
    int depth = 17;

    String exepectedMessage = "'Width' must be positive.";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new Dimension(width, height, depth);
    });

    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(exepectedMessage));
  }

  /**
   * Test to check if the object is created with width equal to zero.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenUsingWidthEqualToZero() {
    //Arrange
    int width = 0;
    int height = 15;
    int depth = 17;

    String exepectedMessage = "'Width' must be positive.";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new Dimension(width, height, depth);
    });

    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(exepectedMessage));
  }

  /**
   * Test to check if the object is created with invalid height
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenUsingInvalidHeight() {
    //Arrange
    int width = 13;
    int height = -15;
    int depth = 17;

    String exepectedMessage = "'Height' must be positive.";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new Dimension(width, height, depth);
    });

    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(exepectedMessage));
  }

  /**
   * Test to check if the object is created with height equal to zero.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenUsingHeightEqualToZero() {
    //Arrange
    int width = 13;
    int height = 0;
    int depth = 17;

    String exepectedMessage = "'Height' must be positive.";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new Dimension(width, height, depth);
    });

    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(exepectedMessage));
  }

  /**
   * Test to check if the object is created with invalid depth
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenUsingInvalidDepth() {
    //Arrange
    int width = 13;
    int height = 15;
    int depth = -17;

    String expectedMessage = "'Depth' must be positive";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new Dimension(width, height, depth);
    });

    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Test to check if the object is created with depth equal to zero.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenUsingDepthEqualToZero() {
    //Arrange
    int width = 13;
    int height = 15;
    int depth = 0;

    String expectedMessage = "'Depth' must be positive";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new Dimension(width, height, depth);
    });

    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Test to check if the object is equal to itself.
   */
  @Test
  void shouldReturnTrue_whenComparingTheSameDimensionObject() {
    //Arrange
    int width = 13;
    int height = 15;
    int depth = 17;

    Dimension dimension = new Dimension(width, height, depth);

    //Act
    boolean result = dimension.equals(dimension);

    //Assert
    assertTrue(result);
  }

  /**
   * Test to check if the object is equal to another object with the same values.
   */
  @Test
  void shouldReturnTrue_whenComparingTwoEqualDimensions() {
    //Arrange
    int width = 13;
    int height = 15;
    int depth = 17;

    Dimension dimension = new Dimension(width, height, depth);
    Dimension dimension2 = new Dimension(width, height, depth);

    //Act
    boolean result = dimension.equals(dimension2);

    //Assert
    assertTrue(result);
  }

  /**
   * Test to check if the Dimension object is different from another object with different type.
   */
  @Test
  void shouldReturnFalse_whenComparingDifferentObjects() {
    //Arrange
    int width = 13;
    int height = 15;
    int depth = 17;

    Dimension dimension = new Dimension(width, height, depth);
    String dimension2 = "Dimension";

    //Act
    boolean result = dimension.equals(new Object());

    //Assert
    assertFalse(result);
  }


  /**
   * Test equals method when width is different.
   */
  @Test
  void shouldReturnFalse_whenComparingDifferentWidth() {
    //Arrange
    int width = 13;
    int height = 15;
    int depth = 17;

    Dimension dimension = new Dimension(width, height, depth);
    Dimension dimension2 = new Dimension(14, height, depth);

    //Act
    boolean result = dimension.equals(dimension2);

    //Assert
    assertFalse(result);
  }

  /**
   * Test equals method when height is different.
   */
  @Test
  void shouldReturnFalse_whenComparingDifferentHeight() {
    //Arrange
    int width = 13;
    int height = 15;
    int depth = 17;

    Dimension dimension = new Dimension(width, height, depth);
    Dimension dimension2 = new Dimension(width, 16, depth);

    //Act
    boolean result = dimension.equals(dimension2);

    //Assert
    assertFalse(result);
  }

  /**
   * Test equals method when depth is different.
   */

  @Test
  void shouldReturnFalse_whenComparingDifferentDepth() {
    //Arrange
    int width = 13;
    int height = 15;
    int depth = 17;

    Dimension dimension = new Dimension(width, height, depth);
    Dimension dimension2 = new Dimension(width, height, 18);

    //Act
    boolean result = dimension.equals(dimension2);

    //Assert
    assertFalse(result);
  }

  /**
   * Test to set dimension to string.
   */
  @Test
  void shouldReturnDimensionToString() {
    //Arrange
    int width = 13;
    int height = 15;
    int depth = 17;

    Dimension dimension = new Dimension(width, height, depth);

    String expected = "Width: 13, Height: 15, Depth: 17";

    //Act
    String result = dimension.toString();

    //Assert
    assertEquals(result, expected);
  }

  /**
   * Test hashCode method.
   */
  @Test
  void shouldReturnHashCode_whenCallingHashCode() {
    //Arrange
    int width = 13;
    int height = 15;
    int depth = 17;
    Dimension dimension = new Dimension(width, height, depth);

    int expectedHashCode =
        Integer.hashCode(width) + Integer.hashCode(height) + Integer.hashCode(depth);

    //Act
    int result = dimension.hashCode();

    //Assert
    assertEquals(expectedHashCode, result);
  }

  /**
   * Test hashCode method for different objects.
   */
  @Test
  void shouldReturnHashCode_whenCallingHashCodeWithDifferentHashCode() {
    //Arrange
    int width = 13;
    int height = 15;
    int depth = 17;
    Dimension dimension = new Dimension(width, height, depth);

    int expectedHashCode =
        Integer.hashCode(width) - Integer.hashCode(height) + Integer.hashCode(depth);

    //Act
    int result = dimension.hashCode();

    //Assert
    assertNotEquals(expectedHashCode, result);
  }

  /**
   * Test getWidth method.
   */
  @Test
  void shouldReturnWidth_whenCallingGetWidth() {
    //Arrange
    int width = 13;
    int height = 15;
    int depth = 17;
    Dimension dimension = new Dimension(width, height, depth);

    //Act
    int result = dimension.getWidth();

    //Assert
    assertEquals(width, result);
  }

  /**
   * Test getHeight method.
   */
  @Test
  void shouldReturnHeight_whenCallingGetHeight() {
    //Arrange
    int width = 13;
    int height = 15;
    int depth = 17;
    Dimension dimension = new Dimension(width, height, depth);

    //Act
    int result = dimension.getHeight();

    //Assert
    assertEquals(height, result);
  }

  /**
   * Test getDepth method.
   */
  @Test
  void shouldReturnDepth_whenCallingGetDepth() {
    //Arrange
    int width = 13;
    int height = 15;
    int depth = 17;
    Dimension dimension = new Dimension(width, height, depth);

    //Act
    int result = dimension.getDepth();

    //Assert
    assertEquals(depth, result);
  }


}
