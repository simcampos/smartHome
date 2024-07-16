/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.mem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import smarthome.domain.house.House;
import smarthome.domain.value_object.HouseID;

/**
 * Unit tests for the HouseRepository class.
 */
class HouseRepositoryTest {

  /**
   * Tests the save method of the HouseRepository when given a valid house.
   */
  @Test
  void shouldSaveHouseWhenGivenValidHouse() {
    // Arrange
    House house = mock(House.class);
    HouseID houseID = mock(HouseID.class);
    when(house.getID()).thenReturn(houseID);
    HouseRepository houseRepository = new HouseRepository();

    // Act
    House savedHouse = houseRepository.save(house);

    // Assert
    assertEquals(house, savedHouse);
  }

  /**
   * Tests the save method of the HouseRepository when given a null house.
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenGivenNullHouse() {
    // Arrange
    House house = null;
    HouseRepository houseRepository = new HouseRepository();

    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> houseRepository.save(house));
    assertEquals("House is required", exception.getMessage());
  }

  /**
   * Tests the save method of the HouseRepository when a house with the same ID already exists.
   */
  @Test
  void shouldThrowIllegalArgumentExceptionWhenHouseAlreadyExists() {
    // Arrange
    House house = mock(House.class);
    HouseID houseID = mock(HouseID.class);
    when(house.getID()).thenReturn(houseID);
    HouseRepository houseRepository = new HouseRepository();
    houseRepository.save(house);

    House secondHouse = mock(House.class);
    HouseID secondHouseID = mock(HouseID.class);
    when(secondHouse.getID()).thenReturn(secondHouseID);


    // Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> houseRepository.save(secondHouse));
    assertEquals("The system supports only one house.", exception.getMessage());
  }

  /**
   * Tests the findAll method of the HouseRepository when houses are saved.
   */
  @Test
  void shouldReturnAllHousesWhenFindAllIsCalled() {
    // Arrange
    House firstHouse = mock(House.class);
    HouseID firstHouseID = mock(HouseID.class);
    when(firstHouse.getID()).thenReturn(firstHouseID);


    HouseRepository houseRepository = new HouseRepository();

    houseRepository.save(firstHouse);
    List<House> expectedList = List.of(firstHouse);

    // Act
    List<House> allHouses = houseRepository.findAll();

    // Assert
    assertEquals(expectedList, allHouses);
  }

  /**
   * Tests the findAll method of the HouseRepository when no houses are saved.
   */
  @Test
  void shouldReturnEmptyListWhenNoHousesAreSaved() {
    // Arrange
    HouseRepository houseRepository = new HouseRepository();

    // Act
    List<House> allHouses = houseRepository.findAll();

    // Assert
    assertTrue(allHouses.isEmpty());
  }

  /**
   * Tests the ofIdentity method of the HouseRepository when given a valid house ID.
   */
  @Test
  void shoudReturnHouseWhenGivenValidHouseID() {
    // Arrange
    House house = mock(House.class);
    HouseID houseID = mock(HouseID.class);
    when(house.getID()).thenReturn(houseID);
    HouseRepository houseRepository = new HouseRepository();
    houseRepository.save(house);

    // Act
    House returnedHouse = houseRepository.ofIdentity(houseID).get();

    // Assert
    assertEquals(house, returnedHouse);
  }

  /**
   * Tests the ofIdentity method of the HouseRepository when given an invalid house ID.
   */
  @Test
  void shouldReturnOptinalEmptyWhenGivenInvalidHouseID() {
    // Arrange
    HouseRepository houseRepository = new HouseRepository();

    House house = mock(House.class);
    HouseID houseID = mock(HouseID.class);
    when(house.getID()).thenReturn(houseID);
    houseRepository.save(house);

    HouseID nonExistentHouseID = mock(HouseID.class);

    // Act
    Optional<House> returnedHouse = houseRepository.ofIdentity(nonExistentHouseID);

    // Assert
    assertTrue(returnedHouse.isEmpty());
  }

  /**
   * Tests the containsOfIdentity method of the HouseRepository when given a valid house ID.
   */
  @Test
  void shouldReturnTrueWhenGivenValidHouseID() {
    // Arrange
    House house = mock(House.class);
    HouseID houseID = mock(HouseID.class);
    when(house.getID()).thenReturn(houseID);
    HouseRepository houseRepository = new HouseRepository();
    houseRepository.save(house);

    // Act
    boolean containsHouse = houseRepository.containsOfIdentity(houseID);

    // Assert
    assertTrue(containsHouse);
  }

  /**
   * Tests the containsOfIdentity method of the HouseRepository when given an invalid house ID.
   */
  @Test
  void shouldReturnFalseWhenGivenInvalidHouseID() {
    // Arrange
    HouseRepository houseRepository = new HouseRepository();

    House house = mock(House.class);
    HouseID houseID = mock(HouseID.class);
    when(house.getID()).thenReturn(houseID);
    houseRepository.save(house);

    HouseID nonExistentHouseID = mock(HouseID.class);

    // Act
    boolean containsHouse = houseRepository.containsOfIdentity(nonExistentHouseID);

    // Assert
    assertFalse(containsHouse);
  }

}

