/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.house;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;

class HouseAggregateTest {

  /**
   * Verifies that a House instance can be successfully instantiated with valid parameters. This
   * test does not expect any exceptions to be thrown during the instantiation process.
   */
  @Test
  void shouldInstantiateHouse_WhenConstructorIsCalledWithValidParameters() {
    // Arrange
    String street = "Rua do Amial";
    String doorNumber = "12";
    String zipCode = "4200-055";
    String countryCode = "PT";
    double latitude = 41.14961;
    double longitude = -8.61099;
    PostalCodeFactory factory = new PostalCodeFactory();
    Address address = new Address(street, doorNumber, zipCode, countryCode, factory);
    GPS gps = new GPS(latitude, longitude);

    // Act
    House house = new House(address, gps);

    // Assert
    assertNotNull(house);
  }

  /**
   * Ensures that an IllegalArgumentException is thrown when attempting to instantiate a House with
   * a null Address parameter, validating input parameter checks.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullAddress() {
    // Arrange
    Address address = null;
    double latitude = 41.14961;
    double longitude = -8.61099;
    GPS gps = new GPS(latitude, longitude);

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new House(address, gps));

    // Assert
    assertEquals("Address is required", exception.getMessage());
  }

  /**
   * Ensures that an IllegalArgumentException is thrown when attempting to instantiate a House with
   * a null Address parameter, validating input parameter checks.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenSecondConstructorIsCalledWithNullAddress() {
    // Arrange
    Address address = null;
    double latitude = 41.14961;
    double longitude = -8.61099;
    GPS gps = new GPS(latitude, longitude);

    HouseID houseID = new HouseID("68");

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new House(houseID, address, gps));

    // Assert
    assertEquals("Address is required", exception.getMessage());
  }


  /**
   * Ensures that an IllegalArgumentException is thrown when attempting to instantiate a House with
   * a null GPS parameter, validating input parameter checks.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenConstructorIsCalledWithNullGPS() {
    // Arrange
    String street = "Rua do Amial";
    String doorNumber = "12";
    String zipCode = "4200-055";
    String countryCode = "PT";
    PostalCodeFactory factory = new PostalCodeFactory();
    Address address = new Address(street, doorNumber, zipCode, countryCode, factory);
    GPS gps = null;

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new House(address, gps));

    // Assert
    assertEquals("GPS is required", exception.getMessage());
  }

  /**
   * Ensures that an IllegalArgumentException is thrown when attempting to instantiate a House with
   * a null GPS parameter, validating input parameter checks.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenSecondConstructorIsCalledWithNullGPS() {
    // Arrange
    String street = "Rua do Amial";
    String doorNumber = "12";
    String zipCode = "4200-055";
    String countryCode = "PT";
    PostalCodeFactory factory = new PostalCodeFactory();
    Address address = new Address(street, doorNumber, zipCode, countryCode, factory);
    GPS gps = null;

    HouseID houseID = new HouseID("68");

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new House(houseID, address, gps));

    // Assert
    assertEquals("GPS is required", exception.getMessage());
  }

  /**
   * Tests that the {@link House#getID()} method returns the correct HouseID of the instantiated
   * House. This test verifies that the HouseID is properly set during House construction.
   */
  @Test
  void shouldReturnHouseID_WhenGetIDIsCalled() {
    // Arrange
    String street = "Rua do Amial";
    String doorNumber = "12";
    String zipCode = "4200-055";
    String countryCode = "PT";
    double latitude = 41.14961;
    double longitude = -8.61099;
    PostalCodeFactory factory = new PostalCodeFactory();
    Address address = new Address(street, doorNumber, zipCode, countryCode, factory);
    GPS gps = new GPS(latitude, longitude);
    House house = new House(address, gps);

    // Act
    HouseID result = house.getID();

    // Assert
    assertTrue(house.toString().contains(result.toString()));
  }

  /**
   * Tests that the {@link House#getAddress()} method returns the correct Address of the
   * instantiated House. This test verifies that the Address is properly set during House
   * construction.
   */
  @Test
  void shouldReturnAddress_WhenGetAddressIsCalled() {
    // Arrange
    String street = "Rua do Amial";
    String doorNumber = "12";
    String zipCode = "4200-055";
    String countryCode = "PT";
    double latitude = 41.14961;
    double longitude = -8.61099;
    PostalCodeFactory factory = new PostalCodeFactory();
    Address address = new Address(street, doorNumber, zipCode, countryCode, factory);
    GPS gps = new GPS(latitude, longitude);
    House house = new House(address, gps);

    // Act
    Address result = house.getAddress();

    // Assert
    assertEquals(address, result);
  }

  /**
   * Tests that the {@link House#getGps()} method returns the correct GPS of the instantiated House.
   * This test verifies that the GPS is properly set during House construction.
   */
  @Test
  void shouldReturnGps_WhenGetGpsIsCalled() {
    // Arrange
    String street = "Rua do Amial";
    String doorNumber = "12";
    String zipCode = "4200-055";
    String countryCode = "PT";
    double latitude = 41.14961;
    double longitude = -8.61099;
    PostalCodeFactory factory = new PostalCodeFactory();
    Address address = new Address(street, doorNumber, zipCode, countryCode, factory);
    GPS gps = new GPS(latitude, longitude);
    House house = new House(address, gps);

    // Act
    GPS result = house.getGps();

    // Assert
    assertEquals(gps, result);
  }

  /**
   * Tests method equals of class House when the instance is compared to itself.
   */
  @Test
  void shouldReturnTrue_WhenComparedToItself() {
    // Arrange
    String street = "Rua do Amial";
    String doorNumber = "12";
    String zipCode = "4200-055";
    String countryCode = "PT";
    double latitude = 41.14961;
    double longitude = -8.61099;
    PostalCodeFactory factory = new PostalCodeFactory();
    Address address = new Address(street, doorNumber, zipCode, countryCode, factory);
    GPS gps = new GPS(latitude, longitude);
    House house = new House(address, gps);

    // Act
    boolean result = house.equals(house);

    // Assert
    assertTrue(result);
  }

  /**
   * Tests that two House instances with different HouseIDs are not considered equal. This test
   * ensures that the equals method correctly evaluates the identity of House instances.
   */
  @Test
  void shouldReturnFalse_WhenThereAreTwoDifferentHouses()
      throws NoSuchFieldException, IllegalAccessException {
    // Arrange
    String street = "Rua do Amial";
    String doorNumber = "12";
    String zipCode = "4200-055";
    String countryCode = "PT";
    double latitude = 41.14961;
    double longitude = -8.61099;
    PostalCodeFactory factory = new PostalCodeFactory();
    Address address = new Address(street, doorNumber, zipCode, countryCode, factory);
    GPS gps = new GPS(latitude, longitude);

    House house1 = new House(address, gps);
    House house2 = new House(address, gps);

    // Act
    boolean result = house1.equals(house2);

    // Assert
    assertFalse(result);
  }


  /**
   * Tests the equals method of class House when the instance is compared to a null object.
   */
  @Test
  void shouldReturnFalse_WhenComparedWithNullObject() {
    // Arrange
    String street = "Rua do Amial";
    String doorNumber = "12";
    String zipCode = "4200-055";
    String countryCode = "PT";
    double latitude = 41.14961;
    double longitude = -8.61099;
    PostalCodeFactory factory = new PostalCodeFactory();
    Address address = new Address(street, doorNumber, zipCode, countryCode, factory);
    GPS gps = new GPS(latitude, longitude);
    House house = new House(address, gps);

    // Act
    boolean isEqual = house.equals(null);

    // Assert
    assertFalse(isEqual, "House should not be equal to null");
  }

  /**
   * Tests the equals method of class House when the instance is compared to an object of a
   * different class.
   */
  @Test
  void shouldReturnFalse_WhenComparedWithDifferentClass() {
    // Arrange
    String street = "Rua do Amial";
    String doorNumber = "12";
    String zipCode = "4200-055";
    String countryCode = "PT";
    double latitude = 41.14961;
    double longitude = -8.61099;
    PostalCodeFactory factory = new PostalCodeFactory();
    Address address = new Address(street, doorNumber, zipCode, countryCode, factory);
    GPS gps = new GPS(latitude, longitude);

    House house = new House(address, gps);

    // Act
    boolean isEqual = house.equals(new Object());

    // Assert
    assertFalse(isEqual, "House should not be equal to an object of a different class");
  }


  /**
   * Tests that the {@link House#toString()} method returns a string representation of the House
   * instance. This test verifies that the string includes the class name, along with the HouseID,
   * Address, ZipCode, and GPS properties.
   */
  @Test
  void shouldReturnStringRepresentation_WhenToStringIsCalled() {
    // Arrange
    String street = "Rua do Amial";
    String doorNumber = "12";
    String zipCode = "4200-055";
    String countryCode = "PT";
    double latitude = 41.14961;
    double longitude = -8.61099;
    PostalCodeFactory factory = new PostalCodeFactory();
    Address address = new Address(street, doorNumber, zipCode, countryCode, factory);
    GPS gps = new GPS(latitude, longitude);

    House house = new House(address, gps);
    String expected =
        "House:houseID=" + house.getID() + ", address=" + house.getAddress() + ", gps="
            + house.getGps();

    // Act
    String result = house.toString();

    // Assert
    assertEquals(expected, result);
  }

  @Test
  void hashCodeShouldBeGeneratedFromHouseIDInLineWithOverritenEquals() {
    // Arrange
    String street = "Rua do Amial";
    String doorNumber = "12";
    String zipCode = "4200-055";
    String countryCode = "PT";
    double latitude = 41.14961;
    double longitude = -8.61099;
    PostalCodeFactory factory = new PostalCodeFactory();
    Address address = new Address(street, doorNumber, zipCode, countryCode, factory);
    GPS gps = new GPS(latitude, longitude);
    House house = new House(address, gps);
    HouseID houseID = house.getID();
    int expectedHashCode = houseID.hashCode();

    // Act
    int hashCode = house.hashCode();

    // Assert
    assertEquals(expectedHashCode, hashCode);
  }

  @Test
  void shouldThrowIllegalArgumentException_WhenHouseIDIsNull() {
    // Arrange
    HouseID houseID = null;
    String street = "Rua do Amial";
    String doorNumber = "12";
    String zipCode = "4200-055";
    String countryCode = "PT";
    double latitude = 41.14961;
    double longitude = -8.61099;
    PostalCodeFactory factory = new PostalCodeFactory();
    Address address = new Address(street, doorNumber, zipCode, countryCode, factory);
    GPS gps = new GPS(latitude, longitude);

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new House(houseID, address, gps));

    // Assert
    assertEquals("HouseID is required", exception.getMessage());
  }

  @Test
  void shouldInstantiateHouseWhenAllParameteresValidAndUsingHouseID() {
    // Arrange
    HouseID houseID = new HouseID("68");
    String street = "Rua do Amial";
    String doorNumber = "12";
    String zipCode = "4200-055";
    String countryCode = "PT";
    double latitude = 41.14961;
    double longitude = -8.61099;
    PostalCodeFactory factory = new PostalCodeFactory();
    Address address = new Address(street, doorNumber, zipCode, countryCode, factory);
    GPS gps = new GPS(latitude, longitude);
    // Act
    House house = new House(houseID, address, gps);
    //Assert
    assertNotNull(house);
  }
}