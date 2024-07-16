/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import smarthome.domain.house.House;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.persistence.mem.HouseRepository;

class HouseServiceImplTest {

  /**
   * Test that the HouseService class can be instantiated.
   */
  @Test
  void shouldInstantiateValidHouse() {
    // Arrange
    IHouseFactory houseFactoryDouble = mock(IHouseFactory.class);
    HouseRepository houseRepositoryDouble = mock(HouseRepository.class);

    // Act
    HouseServiceImpl result = new HouseServiceImpl(houseFactoryDouble, houseRepositoryDouble);

    // Assert
    assertNotNull(result);
  }

  /**
   * Test that the HouseService class throws an IllegalArgumentException when the HouseFactory is
   * null.
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenHouseFactoryIsNull() {
    // Arrange
    IHouseFactory houseFactory = null;
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    String expectedMessage = "House factory is required";
    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new HouseServiceImpl(houseFactory, houseRepository));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test that the HouseService class throws an IllegalArgumentException when the HouseRepository is
   * null.
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenHouseRepositoryIsNull() {
    // Arrange
    IHouseFactory houseFactory = mock(IHouseFactory.class);
    IHouseRepository houseRepository = null;
    String expectedMessage = "House repository is required";
    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new HouseServiceImpl(houseFactory, houseRepository));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test that the addHouse method returns a House instance when the HouseFactory and
   * HouseRepository are valid.
   */

  @Test
  void shouldReturnHouseInstanceWhenHouseFactoryAndHouseRepositoryAreValid() {
    // Arrange
    IHouseFactory houseFactory = mock(IHouseFactory.class);
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    House house = mock(House.class);
    Address address = mock(Address.class);
    GPS gps = mock(GPS.class);
    when(houseFactory.createHouse(address, gps)).thenReturn(house);
    HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
    // Act
    House result = houseServiceImpl.addHouse(address, gps);
    // Assert
    assertEquals(house, result);
  }

  /**
   * Test that the getHouse method returns a House instance when the HouseRepository is valid.
   */
  @Test
  void shouldReturnHouseInstance_WhenHouseRepositoryIsValid() {
    // Arrange
    IHouseFactory houseFactory = mock(IHouseFactory.class);
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    House house = mock(House.class);
    when(houseRepository.getTheHouse()).thenReturn(java.util.Optional.of(house));
    HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
    // Act
    java.util.Optional<House> result = houseServiceImpl.getHouse();
    // Assert
    assertEquals(java.util.Optional.of(house), result);
  }

  /**
   * Test that the getHouse method returns an empty Optional instance when the HouseRepository is
   * valid.
   */
  @Test
  void shouldReturnEmptyOptionalInstance_WhenHouseRepositoryIsValid() {
    // Arrange
    IHouseFactory houseFactory = mock(IHouseFactory.class);
    IHouseRepository houseRepository = mock(IHouseRepository.class);
    when(houseRepository.getTheHouse()).thenReturn(java.util.Optional.empty());
    HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseFactory, houseRepository);
    // Act
    java.util.Optional<House> result = houseServiceImpl.getHouse();
    // Assert
    assertEquals(java.util.Optional.empty(), result);
  }


}
