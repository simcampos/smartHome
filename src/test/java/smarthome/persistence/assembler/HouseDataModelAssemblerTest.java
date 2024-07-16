/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.house.House;
import smarthome.domain.house.HouseFactoryImpl;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.persistence.data_model.HouseDataModel;

class HouseDataModelAssemblerTest {

  @Test
  void shouldInstantiateHouseDataModelAssembler_whenHouseFactoryIsValid() {
    // Arrange
    IHouseFactory houseFactory = mock(HouseFactoryImpl.class);

    // Act
    HouseDataModelAssembler houseDataModelAssembler = new HouseDataModelAssembler(houseFactory);

    // Assert
    assertNotNull(houseDataModelAssembler);
  }

  @Test
  void shouldThrowIllegalArgumentException_whenHouseFactoryIsNull() {
    // Arrange
    IHouseFactory houseFactory = null;
    String expectedMessage = "House Factory cannot be null.";

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new HouseDataModelAssembler(houseFactory));

    // Assert
    String actualMessage = exception.getMessage();
    assertNotNull(expectedMessage, actualMessage);
  }

  @Test
  void shouldReturnHouse_whenGivenValidHouseDataModel() {
    // Arrange
    HouseFactoryImpl houseFactoryDouble = mock(HouseFactoryImpl.class);
    when(houseFactoryDouble.createHouse(any(Address.class), any(GPS.class))).thenReturn(
        mock(House.class));

    HouseDataModelAssembler houseDataModelAssembler = new HouseDataModelAssembler(
        houseFactoryDouble);

    HouseDataModel houseDataModelDouble = mock(HouseDataModel.class);

    HouseID houseIDDouble = mock(HouseID.class);

    Address addressDouble = mock(Address.class);
    when(addressDouble.getStreet()).thenReturn("street");
    when(addressDouble.getDoorNumber()).thenReturn("1");

    GPS gpsDouble = mock(GPS.class);
    when(gpsDouble.getLatitude()).thenReturn(1.0);
    when(gpsDouble.getLongitude()).thenReturn(1.0);

    when(houseDataModelDouble.getHouseID()).thenReturn("1L");
    when(houseDataModelDouble.getStreet()).thenReturn("street");
    when(houseDataModelDouble.getDoorNumber()).thenReturn("1");
    when(houseDataModelDouble.getPostalCode()).thenReturn("1234-599");
    when(houseDataModelDouble.getCountryCode()).thenReturn("PT");
    when(houseDataModelDouble.getLatitude()).thenReturn(1.0);
    when(houseDataModelDouble.getLongitude()).thenReturn(1.0);

    House expected = houseFactoryDouble.createHouse(houseIDDouble, addressDouble, gpsDouble);

    // Act
    House actual = houseDataModelAssembler.toDomain(houseDataModelDouble);

    // Assert
    assertEquals(expected, actual);
  }

  @Test
  void shouldReturnListOfHouses_WhenGivenListOfHousesDataModels() {
    // Arrange
    HouseFactoryImpl houseFactoryDouble = mock(HouseFactoryImpl.class);
    when(houseFactoryDouble.createHouse(any(Address.class), any(GPS.class))).thenReturn(
        mock(House.class));

    HouseDataModelAssembler houseDataModelAssembler = new HouseDataModelAssembler(
        houseFactoryDouble);

    HouseDataModel houseDataModelDouble = mock(HouseDataModel.class);

    HouseID houseIDDouble = mock(HouseID.class);
    when(houseIDDouble.getID()).thenReturn("1L");

    Address addressDouble = mock(Address.class);
    when(addressDouble.getStreet()).thenReturn("street");
    when(addressDouble.getDoorNumber()).thenReturn("1");

    GPS gpsDouble = mock(GPS.class);
    when(gpsDouble.getLatitude()).thenReturn(1.0);
    when(gpsDouble.getLongitude()).thenReturn(1.0);

    when(houseDataModelDouble.getHouseID()).thenReturn("1L");
    when(houseDataModelDouble.getStreet()).thenReturn("street");
    when(houseDataModelDouble.getDoorNumber()).thenReturn("1");
    when(houseDataModelDouble.getPostalCode()).thenReturn("1234-599");
    when(houseDataModelDouble.getCountryCode()).thenReturn("PT");
    when(houseDataModelDouble.getLatitude()).thenReturn(1.0);
    when(houseDataModelDouble.getLongitude()).thenReturn(1.0);

    List<HouseDataModel> houseDataModels = List.of(houseDataModelDouble);

    House expected = houseFactoryDouble.createHouse(houseIDDouble, addressDouble, gpsDouble);

    // Act
    List<House> houses = houseDataModelAssembler.toDomain(houseDataModels);

    // Assert
    assertEquals(expected, houses.get(0));
  }


}