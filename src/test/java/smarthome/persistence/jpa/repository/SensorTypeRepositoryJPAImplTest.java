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
import smarthome.domain.repository.ISensorTypeRepository;
import smarthome.domain.sensor_type.SensorType;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.SensorTypeDataModel;

class SensorTypeRepositoryJPAImplTest {

  /**
   * Test to verify if the RepositorySensorTypeJPAImpl is instantiated correctly
   */
  @Test
  void shouldInstantiateRepositorySensorTypeJPAImpl() {
    //Arrange
    IDataModelAssembler<SensorTypeDataModel, SensorType> dataModelConverter = mock(
        IDataModelAssembler.class);
    EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);


    //Act
    ISensorTypeRepository repositorySensorTypeJPA = new SensorTypeRepositoryJPAImpl(
        dataModelConverter, entityManagerFactory);

    //Assert
    assertNotNull(repositorySensorTypeJPA);
  }

  /**
   * Test to verify if the RepositorySensorTypeJPAImpl throws an IllegalArgumentException when given
   * a null data model converter
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullDataModelConverter() {
    //Arrange
    IDataModelAssembler<SensorTypeDataModel, SensorType> dataModelConverter = null;
    EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);

    String expectedMessage = "Data model converter cannot be null.";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorTypeRepositoryJPAImpl(dataModelConverter, entityManagerFactory));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

}