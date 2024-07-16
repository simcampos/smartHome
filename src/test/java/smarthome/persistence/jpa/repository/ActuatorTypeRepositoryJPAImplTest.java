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
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.repository.IActuatorTypeRepository;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.ActuatorTypeDataModel;

class ActuatorTypeRepositoryJPAImplTest {

  /**
   * Test to verify if the RepositoryActuatorTypeJPAImpl is instantiated correctly
   */
  @Test
  void shouldInstantiateRepositoryActuatorTypeJPAImpl_WHenGivenDataModelAssembler() {
    //Arrange
    IDataModelAssembler<ActuatorTypeDataModel, ActuatorType> dataModelAssembler = mock(
        IDataModelAssembler.class);
    EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);

    //Act
    IActuatorTypeRepository repositoryActuatorTypeJPA = new ActuatorTypeRepositoryJPAImpl(
        dataModelAssembler, entityManagerFactory);

    //Assert
    assertNotNull(repositoryActuatorTypeJPA);
  }

  /**
   * Test to verify if the RepositoryActuatorTypeJPAImpl throws an IllegalArgumentException when
   * given a null data model assembler
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullDataModelConverter() {
    //Arrange
    IDataModelAssembler<ActuatorTypeDataModel, ActuatorType> dataModelAssembler = null;
    EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);

    String expectedMessage = "The data model assembler must not be null.";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ActuatorTypeRepositoryJPAImpl(dataModelAssembler, entityManagerFactory));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }
}