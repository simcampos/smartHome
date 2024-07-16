/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.house;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;

/**
 * Tests for the {@link HouseFactoryImpl} class, ensuring that houses are created correctly under
 * various conditions and that appropriate exceptions are thrown when invalid parameters are
 * provided.
 */
class ImpHouseFactoryTest {

  /**
   * Test to ensure that a House can be created successfully when
   * {@link HouseFactoryImpl#createHouse(Address, GPS)} is called with valid parameters. This test
   * verifies that no exceptions are thrown during the creation process.
   */
  @Test
  void shouldCreateHouse_WhenCreateHouseIsCalledWithValidParameters() {
    // Arrange
    Address address = mock(Address.class);
    GPS gps = mock(GPS.class);
    HouseFactoryImpl factory = new HouseFactoryImpl();

    // Act
    House result = factory.createHouse(address, gps);

    // Assert
    assertNotNull(result);
  }

  /**
   * Test to ensure that an IllegalArgumentException is thrown when
   * {@link HouseFactoryImpl#createHouse(Address, GPS)} is called with a null Address parameter.
   * This test confirms the robustness of the factory's parameter validation.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenCreateHouseIsCalledWithNullAddress() {
    // Arrange
    Address address = null;
    GPS gps = mock(GPS.class);
    HouseFactoryImpl factory = new HouseFactoryImpl();

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> factory.createHouse(address, gps),
        "Factory should throw IllegalArgumentException for null Address.");
  }

  /**
   * Test to ensure that an IllegalArgumentException is thrown when
   * {@link HouseFactoryImpl#createHouse(Address, GPS)} is called with a null GPS parameter. This
   * test checks that all critical parameters are validated before house creation.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenCreateHouseIsCalledWithNullGPS() {
    // Arrange
    Address address = mock(Address.class);
    GPS gps = null;
    HouseFactoryImpl factory = new HouseFactoryImpl();

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> factory.createHouse(address, gps),
        "Factory should throw IllegalArgumentException for null GPS.");
  }

  @Test
  void shouldCreateHouse_WhenCreateHouseIsCalledWithValidParametersAndHouseID() {
    // Arrange
    Address address = mock(Address.class);
    GPS gps = mock(GPS.class);
    HouseID houseID = mock(HouseID.class);
    HouseFactoryImpl factory = new HouseFactoryImpl();

    // Act
    House result = factory.createHouse(houseID, address, gps);

    // Assert
    assertNotNull(result);
  }
}
