/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PathEncoderTest {

  /**
   * See if it works
   */
  @Test
  void shouldEncodeModelPath() {
    // Arrange
    String modelPath = "path";
    String expectedEncodedPath = "cGF0aA=="; // Base64 encoding of "path"

    // Act
    String encodedPath = PathEncoder.encode(modelPath);

    // Assert
    assertEquals(expectedEncodedPath, encodedPath);
  }

  @Test
  void shouldDecodeEncodedModelPath() {
    // Arrange
    String encodedPath = "cGF0aA=="; // Base64 encoding of "path"
    String expectedDecodedPath = "path";

    // Act
    String decodedPath = PathEncoder.decode(encodedPath);

    // Assert
    assertEquals(expectedDecodedPath, decodedPath);
  }

  @Test
  void shouldThrowExceptionWhenEncodingNull() {
    // Arrange
    String modelPath = null;

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      PathEncoder.encode(modelPath);
    });

    String expectedMessage = "Model Path";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void shouldThrowExceptionWhenDecodingNull() {
    // Arrange
    String encodedPath = null;

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      PathEncoder.decode(encodedPath);
    });

    String expectedMessage = "token";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

}