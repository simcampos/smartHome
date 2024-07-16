/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.house;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;

class HouseTest {

  /**
   * Validates construction with valid arguments.
   */
  @Test
  void shouldInstantiateHouseWhenGivenValidParameters() {
    // Arrange
    Address address = mock(Address.class);
    GPS gps = mock(GPS.class);

    // Act
    try (MockedConstruction<HouseID> houseID = mockConstruction(HouseID.class)) {
      House house = new House(address, gps);

      // Assert
      assertNotNull(house);
    }
  }

  /**
   * Should return address when getAddress is called.
   */
  @Test
  void shouldReturnAddressWhenGetAddressIsCalled() {
    // Arrange
    Address address = mock(Address.class);
    GPS gps = mock(GPS.class);

    // Act
    try (MockedConstruction<HouseID> houseID = mockConstruction(HouseID.class)) {
      House house = new House(address, gps);

      // Assert
      assertEquals(address, house.getAddress());
    }
  }

  /**
   * Should return gps when getGps is called.
   */
  @Test
  void shoudReturnGpsWhenGetGpsIsCalled() {
    // Arrange
    Address address = mock(Address.class);
    GPS gps = mock(GPS.class);

    // Act
    try (MockedConstruction<HouseID> houseID = mockConstruction(HouseID.class)) {
      House house = new House(address, gps);

      // Assert
      assertEquals(gps, house.getGps());
    }
  }

  /**
   * Should return houseID when getID is called.
   */
  @Test
  void shouldReturnHouseIDWhenGetIDIsCalled() {
    // Arrange
    Address address = mock(Address.class);
    GPS gps = mock(GPS.class);

    try (MockedConstruction<HouseID> houseID = mockConstruction(HouseID.class)) {
      House house = new House(address, gps);
      HouseID expectedHouseID = houseID.constructed().get(0);

      int instantiatedInstances = houseID.constructed().size();

      // Act
      HouseID houseIDReturned = house.getID();
      // Assert
      assertEquals(expectedHouseID, houseIDReturned);
      assertEquals(1, instantiatedInstances);
    }
  }

  /**
   * Should return string of object when toString is called.
   */
  @Test
  void shouldReturnStringOfObjectWhenToStringIsCalled() {
    // Arrange
    Address addressDouble = mock(Address.class);
    GPS gpsdouble = mock(GPS.class);

    try (MockedConstruction<HouseID> houseID = mockConstruction(HouseID.class)) {
      House house = new House(addressDouble, gpsdouble);
      HouseID houseIDDouble = houseID.constructed().get(0);
      String expected = "House:" +
          "houseID=" + houseIDDouble +
          ", address=" + addressDouble +
          ", gps=" + gpsdouble;

      //Act
      String actual = house.toString();

      //Assert
      assertEquals(expected, actual);

    }
  }

  /**
   * HashCode for entity is derived from his ID. To ensure that equals and hashCode are consistent
   * we must ensure that hashCode is derived from the same property as equals.
   */
  @Test
  void shouldReturnHashCodeLinkedToHouseID() {
    // Arrange
    Address addressDouble = mock(Address.class);
    GPS gpsdouble = mock(GPS.class);

    try (MockedConstruction<HouseID> houseID = mockConstruction(HouseID.class)) {
      House house = new House(addressDouble, gpsdouble);
      HouseID houseIDDouble = houseID.constructed().get(0);
      int expected = houseIDDouble.hashCode();

      //Act
      int actual = house.hashCode();

      //Assert
      assertEquals(expected, actual);
    }
  }

  /**
   * Should return true when comparing same house.
   */
  @Test
  void shouldReturnTrueWhenComparingTwoEqualHouses() {
    // Arrange
    Address addressDouble = mock(Address.class);
    GPS gpsDouble = mock(GPS.class);
    HouseID houseIDDouble = mock(HouseID.class);

    try (MockedConstruction<HouseID> mockedHouseID = mockConstruction(HouseID.class)) {
      House house1 = new House(houseIDDouble, addressDouble, gpsDouble);
      House house2 = new House(houseIDDouble, addressDouble, gpsDouble);
      boolean expected = true;

      // Act
      boolean actual = house1.equals(house2);

      // Assert
      assertEquals(expected, actual);
    }
  }

  /**
   * Should return false when comparing different house.
   */
  @Test
  void shouldReturnFalseWhenComparingDifferentHouse() {
    // Arrange
    Address addressDouble = mock(Address.class);
    GPS gpsdouble = mock(GPS.class);

    try (MockedConstruction<HouseID> houseID = mockConstruction(HouseID.class)) {
      House house = new House(addressDouble, gpsdouble);
      House house2 = new House(addressDouble, gpsdouble);
      boolean expected = false;

      //Act
      boolean actual = house.equals(house2);

      //Assert
      assertEquals(expected, actual);
    }
  }
}
