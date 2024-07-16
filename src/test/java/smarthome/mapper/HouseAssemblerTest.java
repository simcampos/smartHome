/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.house.House;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.utils.dto.HouseDTO;

class HouseAssemblerTest {

  /**
   * Test that the HouseAssembler class can convert a House object to a HouseDTO object.
   */
  @Test
  void shouldReturnAHouseDTOWhenGivenAHouse() {
    // Arrange
    String address = "Test Address, 1";
    String gps = "GPS{latitude=90.0, longitude=180.0}";
    String houseID = "1";

    House house = mock(House.class);
    when(house.getAddress()).thenReturn(mock(Address.class));
    when(house.getAddress().toString()).thenReturn(address);
    when(house.getGps()).thenReturn(mock(GPS.class));
    when(house.getGps().toString()).thenReturn(gps);
    when(house.getID()).thenReturn(mock(HouseID.class));
    when(house.getID().toString()).thenReturn(houseID);

    HouseAssembler houseAssembler = new HouseAssembler();
    String expected = address + " " + gps + " " + houseID;

    // Act
    HouseDTO result = houseAssembler.domainToDTO(house);

    // Assert
    assertEquals(expected, result.toString());
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenHouseIsNull() {
    // Arrange
    House house = null;
    HouseAssembler houseAssembler = new HouseAssembler();
    String expectedMessage = "House is required";

    // Act
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> houseAssembler.domainToDTO(house));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test that the HouseAssembler class can convert a House object list to a HouseDTO object list.
   */
  @Test
  void shouldReturnANewHouseDTOListWhenGivenAListOfHouses() {
    // Arrange
    String address = "Test Address, 1";
    String gps = "GPS{latitude=90.0, longitude=180.0}";
    String houseID = "1";

    String address2 = "Test Address2, 2";
    String gps2 = "GPS{latitude=90.0, longitude=170.0}";
    String houseID2 = "2";

    House house = mock(House.class);

    Address addressMock = mock(Address.class);
    GPS gpsMock = mock(GPS.class);
    HouseID houseIDMock = mock(HouseID.class);

    when(house.getAddress()).thenReturn(addressMock);
    when(addressMock.toString()).thenReturn(address);
    when(house.getGps()).thenReturn(gpsMock);
    when(gpsMock.toString()).thenReturn(gps);
    when(house.getID()).thenReturn(houseIDMock);
    when(houseIDMock.toString()).thenReturn(houseID);

    House house2 = mock(House.class);
    Address addressMock2 = mock(Address.class);
    GPS gpsMock2 = mock(GPS.class);
    HouseID houseIDMock2 = mock(HouseID.class);

    when(house2.getAddress()).thenReturn(addressMock2);
    when(addressMock2.toString()).thenReturn(address2);
    when(house2.getGps()).thenReturn(gpsMock2);
    when(gpsMock2.toString()).thenReturn(gps2);
    when(house2.getID()).thenReturn(houseIDMock2);
    when(houseIDMock2.toString()).thenReturn(houseID2);

    ArrayList<House> houses = new ArrayList<>();
    houses.add(house);
    houses.add(house2);

    HouseDTO houseDTO = new HouseDTO(address, gps, houseID);
    HouseDTO houseDTO2 = new HouseDTO(address2, gps2, houseID2);
    List<HouseDTO> expected = List.of(houseDTO, houseDTO2);

    HouseAssembler houseAssembler = new HouseAssembler();

    // Act
    List<HouseDTO> result = houseAssembler.domainToDTO(houses);

    // Assert
    assertEquals(expected.toString(), result.toString());
  }

  @Test
  void shouldThrowIllegalArgumentExceptionWhenHouseListIsNull() {
    // Arrange
    List<House> houses = null;
    HouseAssembler houseAssembler = new HouseAssembler();
    String expectedMessage = "The list of Houses cannot be null.";

    // Act
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> houseAssembler.domainToDTO(houses));

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

}
