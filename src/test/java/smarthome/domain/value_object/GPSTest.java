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

class GPSTest {

  /**
   * Test of constructor, of class GPS.
   */
  @Test
  void shouldReturnExpectedGPSWhenGivenValidParameters() {
    //Arrange
    double latitude = 41.14961;
    double longitude = -8.61099;

    //Act
    GPS gps = new GPS(latitude, longitude);

    //Assert
    assertNotNull(gps);
  }

  /**
   * Validates that a GPS instance is correctly created in the positive boundary of latitude.
   */

  @Test
  void shouldReturnExpectedLatitudeWhenIsOnPositiveBoundary() {
    //Arrange
    double latitude = 90.0;
    double longitude = -8.0;

    GPS gps = new GPS(latitude, longitude);

    //Act
    boolean result = gps.equals(gps);

    //Assert
    assertTrue(result);
  }

  /**
   * Validates that a GPS instance is correctly created in the negative boundary of latitude.
   */
  @Test
  void shouldReturnExpectedLatitudeWhenIsOnNegativeBoundary() {
    //Arrange
    double latitude = -90.0;
    double longitude = -8.0;

    GPS gps = new GPS(latitude, longitude);

    //Act
    boolean result = gps.equals(gps);

    //
    assertTrue(result);
  }

  /**
   * Validates that a GPS instance is correctly created in the positive boundary of longitude.
   */
  @Test
  void shouldReturnExpectedLongitudeWhenIsOnPositiveBoundary() {
    //Arrange
    double latitude = 41.14961;
    double longitude = 180.0;

    GPS gps = new GPS(latitude, longitude);

    //Act
    boolean result = gps.equals(gps);

    //Assert
    assertTrue(result);
  }

  /**
   * Validates that a GPS instance is correctly created in the negative boundary of longitude.
   */
  @Test
  void shouldReturnExpectedLongitudeWhenIsOnNegativeBoundary() {
    //Arrange
    double latitude = 41.14961;
    double longitude = -180.0;

    GPS gps = new GPS(latitude, longitude);

    //Act
    boolean result = gps.equals(gps);

    //Assert
    assertTrue(result);
  }


  /**
   * Validates that a GPS instance is correctly created in the limit of the positive boundary of
   * longitude
   */
  @Test
  void shouldReturnExpectedLongitudeLimitPositiveBoundary() {
    //Arrange
    double latitude = 41.14961;
    double longitude = 179.99999;

    GPS gps = new GPS(latitude, longitude);

    //Act
    boolean result = gps.equals(gps);

    //Assert
    assertTrue(result);

  }

  /**
   * Validates that a GPS instance is correctly created in the limit of the negative boundary of
   * longitude
   */
  @Test
  void shouldReturnExpectedLongitudeLimitNegativeBoundary() {
    //Arrange
    double latitude = 41.14961;
    double longitude = -179.99999;

    GPS gps = new GPS(latitude, longitude);

    //Act
    boolean result = gps.equals(gps);

    //Assert
    assertTrue(result);
  }

  /**
   * Validates that a GPS instance is correctly created in the limit of the positive boundary of
   * latitude
   */
  @Test
  void shouldReturnExpectedLatitudeLimitPositiveBoundary() {
    //Arrange
    double latitude = 89.99999;
    double longitude = -8.61099;

    GPS gps = new GPS(latitude, longitude);

    //Act
    boolean result = gps.equals(gps);

    //Assert
    assertTrue(result);
  }

  /**
   * Validates that a GPS instance is correctly created in the limit of the negative boundary of
   * latitude
   */
  @Test
  void shouldReturnExpectedLatitudeLimitNegativeBoundary() {
    //Arrange
    double latitude = -89.99999;
    double longitude = -8.61099;

    GPS gps = new GPS(latitude, longitude);

    //Act
    boolean result = gps.equals(gps);

    //Assert
    assertTrue(result);
  }

  /**
   * Asserts an exception is thrown for latitudes above the positive valid range.
   */
  @Test
  void shouldThrowExceptionWhenLatitudeItsAboveBoundary() {
    //Arrange
    double latitude = 91.00001;
    double longitude = -8.61099;

    //Act & Assert
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new GPS(latitude, longitude));
    assertEquals("Please enter a valid latitude.", exception.getMessage());
  }

  /**
   * Asserts an exception is thrown for latitudes below the negative valid range.
   */
  @Test
  void shouldThrowExceptionWhenLatitudeItsBelowBoundary() {
    //Arrange
    double latitude = -90.00001;
    double longitude = -8.61099;

    //Act & Assert
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new GPS(latitude, longitude));
    assertEquals("Please enter a valid latitude.", exception.getMessage());
  }

  /**
   * Asserts an exception is thrown for longitudes above the positive valid range.
   */
  @Test
  void shouldThrowExceptionWhenLongitudeItsAboveBoundary() {
    //Arrange
    double latitude = 41.14961;
    double longitude = 181.00001;

    //Act & Assert
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new GPS(latitude, longitude));
    assertEquals("Please enter a valid longitude.", exception.getMessage());
  }

  /**
   * Asserts an exception is thrown for longitudes below the negative valid range.
   */
  @Test
  void shouldThrowExceptionWhenLongitudeItsBelowBoundary() {
    //Arrange
    double latitude = 41.14961;
    double longitude = -180.00001;

    //Act & Assert
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new GPS(latitude, longitude));
    assertEquals("Please enter a valid longitude.", exception.getMessage());
  }

  /**
   * Asserts an exception is thrown for latitudes with more than five decimal numbers.
   */
  @Test
  void shouldThrowExceptionWhenLatitudeHasMoreThanFiveDecimalNumbers() {
    //Arrange
    double latitude = 41.149611;
    double longitude = -8.61099;

    //Act & Assert
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new GPS(latitude, longitude));
    assertEquals("Please enter a valid latitude.", exception.getMessage());
  }

  /**
   * Asserts an exception is thrown for longitudes with more than five decimal numbers.
   */
  @Test
  void shouldThrowExceptionWhenLongitudeHasMoreThanFiveDecimalNumbers() {
    //Arrange
    double latitude = 41.14961;
    double longitude = -8.610991;

    //Act & Assert
    Throwable exception = assertThrows(IllegalArgumentException.class,
        () -> new GPS(latitude, longitude));
    assertEquals("Please enter a valid longitude.", exception.getMessage());
  }

  /**
   * Validates the equality of two identical GPS instances.
   */
  @Test
  void shouldReturnTrueWhenComparingTwoEqualGPS() {
    //Arrange
    double latitude = 41.14961;
    double longitude = -8.61099;
    GPS gps1 = new GPS(latitude, longitude);
    GPS gps2 = new GPS(latitude, longitude);

    //Act
    boolean isEquals = gps1.equals(gps2);

    //Assert
    assertTrue(isEquals);
  }

  /**
   * Validates that two GPS instances with different coordinates are not equal.
   */
  @Test
  void shouldReturnFalseWhenComparingTwoDifferentGPS() {
    //Arrange
    double latitude1 = 41.14961;
    double longitude1 = -8.61099;
    double latitude2 = 41.14961;
    double longitude2 = -8.61098;
    GPS gps1 = new GPS(latitude1, longitude1);
    GPS gps2 = new GPS(latitude2, longitude2);

    //Act
    boolean isEquals = gps1.equals(gps2);

    //Assert
    assertFalse(isEquals);
  }

  /**
   * Ensures a GPS instance is not equal to null.
   */
  @Test
  void shouldReturnFalseWhenComparingWithNull() {
    //Arrange
    double latitude = 41.14961;
    double longitude = -8.61099;
    GPS gps = new GPS(latitude, longitude);

    //Act
    boolean isEquals = gps.equals(null);

    //Assert
    assertFalse(isEquals);
  }

  /**
   * Verifies a GPS instance is equal to itself.
   */
  @Test
  void shouldReturnTrueWhenComparingWithItself() {
    //Arrange
    double latitude = 41.14961;
    double longitude = -8.61099;
    GPS gps = new GPS(latitude, longitude);

    //Act
    boolean isEquals = gps.equals(gps);

    //Assert
    assertTrue(isEquals);
  }

  /**
   * Test equals method when latitude is different.
   */
  @Test
  void shouldReturnFalseWhenLatitudeIsDifferent() {
    //Arrange
    double latitude1 = 41.14961;
    double longitude1 = -8.61099;
    double latitude2 = 41.14962;
    double longitude2 = -8.61099;
    GPS gps1 = new GPS(latitude1, longitude1);
    GPS gps2 = new GPS(latitude2, longitude2);

    //Act
    boolean isEquals = gps1.equals(gps2);

    //Assert
    assertFalse(isEquals);
  }

  /**
   * Test equals method when longitude is different.
   */
  @Test
  void shouldReturnFalseWhenLongitudeIsDifferent() {
    //Arrange
    double latitude1 = 41.14961;
    double longitude1 = -8.61099;
    double latitude2 = 41.14961;
    double longitude2 = -8.61100;
    GPS gps1 = new GPS(latitude1, longitude1);
    GPS gps2 = new GPS(latitude2, longitude2);

    //Act
    boolean isEquals = gps1.equals(gps2);

    //Assert
    assertFalse(isEquals);
  }

  /**
   * Validates the string representation of a GPS instance.
   */
  @Test
  void shouldReturnExpectedStringWhenCallingToString() {
    //Arrange
    double latitude = 41.14961;
    double longitude = -8.61099;

    GPS gps = new GPS(latitude, longitude);

    String expected = "Latitude= " + latitude +
        ", Longitude= " + longitude;

    //Act
    String result = gps.toString();

    //Assert
    assertEquals(expected, result);
  }

  /**
   * Validates the getter for the latitude value.
   */
  @Test
  void shouldReturnExpectedLatitudeWhenCallingGetLatitude() {
    //Arrange
    double latitude = 41.14961;
    double longitude = -8.61099;

    GPS gps = new GPS(latitude, longitude);

    //Act
    double result = gps.getLatitude();

    //Assert
    assertEquals(latitude, result, 0.00001);
  }

  /**
   * Validates the getter for the longitude value.
   */
  @Test
  void shouldReturnExpectedLongitudeWhenCallingGetLongitude() {
    //Arrange
    double latitude = 41.14961;
    double longitude = -8.61099;

    GPS gps = new GPS(latitude, longitude);

    //Act
    double result = gps.getLongitude();

    //Assert
    assertEquals(longitude, result, 0.00001);
  }

  /**
   * Validates the hash code of a GPS instance.
   */
  @Test
  void shouldReturnExpectedHashCode_WhenCallingHashCode() {
    //Arrange
    double latitude = 41.14961;
    double longitude = -8.61099;
    GPS gps = new GPS(latitude, longitude);

    int expected = Double.hashCode(latitude) + Double.hashCode(longitude);

    //Act
    int result = gps.hashCode();

    //Assert
    assertEquals(expected, result);
  }
}

