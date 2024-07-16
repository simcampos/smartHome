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
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.repository.IActuatorModelRepository;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.ActuatorModelDataModel;

class ActuatorModelRepositoryJPImplTest {

  /**
   * Test to verify if the RepositoryActuatorModelJPAImpl is instantiated correctly
   */
  @Test
  void shouldInstantiateRepositoryActuatorModelJPAImpl() {
    //Arrange
    IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> dataModelConverter = mock(
        IDataModelAssembler.class);

    EntityManagerFactory factory = mock(EntityManagerFactory.class);

    //Act
    IActuatorModelRepository repositoryActuatorModelJPA = new ActuatorModelRepositoryJPAImpl(
        dataModelConverter, factory);

    //Assert
    assertNotNull(repositoryActuatorModelJPA);
  }

  /**
   * Test to verify if the RepositoryActuatorModelJPAImpl throws an IllegalArgumentException when
   * given a null data model converter
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullDataModelConverter() {
    //Arrange
    IDataModelAssembler<ActuatorModelDataModel, ActuatorModel> dataModelConverter = null;
    EntityManagerFactory factory = mock(EntityManagerFactory.class);

    String expectedMessage = "Data model converter cannot be null";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new ActuatorModelRepositoryJPAImpl(dataModelConverter, factory));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }
}
