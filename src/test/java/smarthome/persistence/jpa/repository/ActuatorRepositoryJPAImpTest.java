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
import smarthome.domain.actuator.IActuator;
import smarthome.domain.repository.IActuatorRepository;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.ActuatorDataModel;
import smarthome.utils.visitor_pattern.IActuatorVisitorForDataModel;

class ActuatorRepositoryJPAImpTest {

  /**
   * Test to verify if the RepositoryActuatorJPAImpl is instantiated correctly
   */
  @Test
  void shouldInstantiateRepositoryActuatorJPAImpl_WHenGivenDataModelAssembler() {
    //Arrange
    IDataModelAssembler<ActuatorDataModel, IActuator> dataModelConverter = mock(
        IDataModelAssembler.class);
    IActuatorVisitorForDataModel actuatorVisitorForDataModel = mock(
        IActuatorVisitorForDataModel.class);
    EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);

    //Act
    IActuatorRepository repositoryActuatorJPA = new ActuatorRepositoryJPAImp(
        dataModelConverter, actuatorVisitorForDataModel, entityManagerFactory);

    //Assert
    assertNotNull(repositoryActuatorJPA);
  }

  /**
   * Test to verify if the RepositoryActuatorJPAImpl throws an IllegalArgumentException when given a
   * null data model assembler
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullDataModelConverter() {
    //Arrange
    IDataModelAssembler<ActuatorDataModel, IActuator> dataModelConverter = null;
    IActuatorVisitorForDataModel actuatorVisitorForDataModel = mock(
        IActuatorVisitorForDataModel.class);
    EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);

    String expectedMessage = "Data model converter cannot be null";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ActuatorRepositoryJPAImp(dataModelConverter, actuatorVisitorForDataModel,
            entityManagerFactory));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }
}