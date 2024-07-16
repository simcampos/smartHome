/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.jpa.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;
import smarthome.domain.repository.IHouseRepository;
import smarthome.persistence.assembler.IDataModelAssembler;

class HouseRepositoryJPAImplTest {

  /**
   * Test to verify if the HouseRepositoryJPAImplTest is instantiated correctly
   */
  @Test
  void shouldInstantiateHouseRepositoryJPAImpl_whenAttributesAreValid() {
    //Arrange
    IDataModelAssembler dataModelAssembler = mock(IDataModelAssembler.class);
    EntityManagerFactory factory = mock(EntityManagerFactory.class);

    //Act
    IHouseRepository houseRepositoryJPA = new HouseRepositoryJPAImpl(dataModelAssembler, factory);

    //Assert
    assertNotNull(houseRepositoryJPA);
  }

  /**
   * Test to verify if the HouseRepositoryJPAImplTest throws an IllegalArgumentException when given
   * a null data model assembler
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullDataModelAssembler() {
    //Arrange
    IDataModelAssembler dataModelAssembler = null;
    EntityManagerFactory factory = mock(EntityManagerFactory.class);
    String expectedMessage = "Data model assembler cannot be null.";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new HouseRepositoryJPAImpl(dataModelAssembler, factory));
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

}