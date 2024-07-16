/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.jpa.data_model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import smarthome.domain.house.House;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.postal_code.IPostalCode;
import smarthome.persistence.data_model.HouseDataModel;

class HouseDataModelTest {

  /**
   * Test to check if the HouseDataModel is created with all the house information in the correct
   * fields.
   */
  @Test
  void shouldCreateHouseDataModelWithAllHouseInformationInCorrectFields() {
    // Arrange
    String houseID = "1";
    double latitude = 10.0;
    double longitude = 20.0;
    String street = "Rua do Ouro";
    String doorNumber = "123";
    String countryCode = "PT";
    String postalCode = "4000-007";

    HouseID houseIDDouble = mock(HouseID.class);
    when(houseIDDouble.getID()).thenReturn(houseID);
    GPS gps = mock(GPS.class);
    when(gps.getLatitude()).thenReturn(latitude);
    when(gps.getLongitude()).thenReturn(longitude);
    Address address = mock(Address.class);
    when(address.getStreet()).thenReturn(street);
    when(address.getDoorNumber()).thenReturn(doorNumber);
    when(address.getCountryCode()).thenReturn(countryCode);
    IPostalCode postalCode1 = mock(IPostalCode.class);
    when(address.getPostalCode()).thenReturn(postalCode1);
    when(postalCode1.getCode()).thenReturn(postalCode);

    House house = mock(House.class);
    when(house.getID()).thenReturn(houseIDDouble);
    when(house.getGps()).thenReturn(gps);
    when(house.getAddress()).thenReturn(address);

    String expected = "HouseDataModel: " +
        "houseID='" + houseID + '\'' +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", street='" + street + '\'' +
        ", doorNumber='" + doorNumber + '\'' +
        ", countryCode='" + countryCode + '\'' +
        ", postalCode='" + postalCode + '.';
    // Act
    HouseDataModel houseDataModel = new HouseDataModel(house);
    // Assert
    assertEquals(expected, houseDataModel.toString());
  }

  /**
   * Test to check if the HouseDataModel throws an exception when the house passed is null.
   */
  @Test
  void shouldThrowExceptionWHenHouseIsNull() {
    // Arrange
    House house = null;
    String expected = "House is required";
    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new HouseDataModel(house));
    // Assert
    assertEquals(expected, exception.getMessage());
  }


}