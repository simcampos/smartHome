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
import smarthome.domain.repository.ISensorModelRepository;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.SensorModelDataModel;


class SensorModelRepositoryJPAImplTest {

  /**
   * Test to check if the RepositorySensorModelTypeJPAImpl is instantiated when the
   * SensorModelFactory and DataModelConverter are valid
   */
  @Test
  void shouldInstantiateRepositorySensorModelTypeJPAImpl() {
    //Arrange
    IDataModelAssembler<SensorModelDataModel, SensorModel> dataModelConverter = mock(
        IDataModelAssembler.class);
    EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);

    //Act
    ISensorModelRepository repositorySensorModelTypeJPA = new SensorModelRepositoryJPAImpl(
        dataModelConverter, entityManagerFactory);

    //Assert
    assertNotNull(repositorySensorModelTypeJPA);
  }


  /**
   * Test to check if the RepositorySensorModelTypeJPAImpl throws an IllegalArgumentException when
   * the DataModelConverter is null
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullDataModelConverter() {
    //Arrange
    IDataModelAssembler<SensorModelDataModel, SensorModel> dataModelConverter = null;
    EntityManagerFactory entityManagerFactory = mock(EntityManagerFactory.class);

    String expectedMessage = "Data Model Converter cannot be null.";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorModelRepositoryJPAImpl(dataModelConverter, entityManagerFactory));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }


}
