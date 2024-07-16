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
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.TypeDescription;

class ActuatorTypeRepositoryTest {

  /**
   * Test the save method with valid ActuatorType.
   */
  @Test
  void shouldSaveActuatorType_whenGivenValidActuatorType() {
    //Arrange
    ActuatorType actuatorType = mock(ActuatorType.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

    when(actuatorType.getID()).thenReturn(actuatorTypeID);
    ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();

    //Act
    ActuatorType savedActuatorType = actuatorTypeRepository.save(actuatorType);

    //Assert
    assertEquals(actuatorType, savedActuatorType);
  }

  /**
   * Test the save method with null ActuatorType.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullActuatorType() {
    //Arrange
    ActuatorType actuatorType = null;
    ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();

    String expectedMessage = "ActuatorType is required";

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      //Act
      actuatorTypeRepository.save(actuatorType);
    });

    String actualMessage = exception.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test the save method with an ActuatorType that already exists.
   */
  @Test
  void shouldThrowException_WhenSActuatorTypeAlreadyExists() {
    //Arrange
    ActuatorType actuatorType = mock(ActuatorType.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorType.getID()).thenReturn(actuatorTypeID);

    ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();

    actuatorTypeRepository.save(actuatorType);

    String expectedMessage = "ActuatorType already exists.";

    //Act
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      actuatorTypeRepository.save(actuatorType);
    });

    String actualMessage = exception.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Tests if all the actuators type are returned correctly.
   */
  @Test
  void shouldReturnAllActuatorTypes() {
    //Arrange
    ActuatorType actuatorType = mock(ActuatorType.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorType.getID()).thenReturn(actuatorTypeID);

    ActuatorType secondActuatorType = mock(ActuatorType.class);
    ActuatorTypeID secondActuatorTypeID = mock(ActuatorTypeID.class);
    when(secondActuatorType.getID()).thenReturn(secondActuatorTypeID);

    ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();

    actuatorTypeRepository.save(actuatorType);
    actuatorTypeRepository.save(secondActuatorType);
    List<ActuatorType> expectedList = List.of(actuatorType, secondActuatorType);

    //Act
    List<ActuatorType> allActuatorTypes = actuatorTypeRepository.findAll();

    //Assert
    assertEquals(expectedList, allActuatorTypes);
  }

  /**
   * Test the findAll method when there are no ActuatorTypes saved.
   */
  @Test
  void shouldReturnEmptyList_whenNoActuatorTypesSaved() {
    //Arrange
    ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();

    //Act
    List<ActuatorType> allActuatorTypes = actuatorTypeRepository.findAll();

    //Assert
    assertTrue(allActuatorTypes.isEmpty());
  }

  /**
   * Test the ofIdentity method with an invalid ActuatorTypeID.
   */
  @Test
  void shouldReturnOptionalEmpty_whenGivenInvalidActuatorTypeID() {
    //Arrange
    ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();

    ActuatorType actuatorType = mock(ActuatorType.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorType.getID()).thenReturn(actuatorTypeID);

    actuatorTypeRepository.save(actuatorType);

    ActuatorTypeID invalidActuatorTypeID = mock(ActuatorTypeID.class);

    //Act
    Optional<ActuatorType> returnedActuatorType = actuatorTypeRepository.ofIdentity(
        invalidActuatorTypeID);

    //Assert
    assertTrue(returnedActuatorType.isEmpty());
  }

  /**
   * Test the ofIdentity method with a valid ActuatorTypeID.
   */
  @Test
  void shouldReturnTrue_WhenGivenValidActuatorTypeID() {
    //Arrange
    ActuatorType actuatorType = mock(ActuatorType.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorType.getID()).thenReturn(actuatorTypeID);

    ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
    actuatorTypeRepository.save(actuatorType);

    //Act
    boolean containsActuatorType = actuatorTypeRepository.containsOfIdentity(actuatorTypeID);

    //Assert
    assertTrue(containsActuatorType);
  }

  /**
   * Test the ofIdentity method with invalid ActuatorTypeID.
   */
  @Test
  void shouldReturnFalse_whenGivenInvalidActuatorTypeID() {
    //Arrange
    ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();

    ActuatorType actuatorType = mock(ActuatorType.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorType.getID()).thenReturn(actuatorTypeID);

    actuatorTypeRepository.save(actuatorType);

    ActuatorTypeID invalidActuatorTypeID = mock(ActuatorTypeID.class);

    //Act
    boolean containsActuatorType = actuatorTypeRepository.containsOfIdentity(invalidActuatorTypeID);

    //Assert
    assertFalse(containsActuatorType);
  }

  /**
   * Test the existsOfName method with a valid ActuatorTypeName.
   */
  @Test
  void shouldReturnTrue_whenGivenValidActuatorTypeName() {
    //Arrange
    ActuatorType actuatorType = mock(ActuatorType.class);

    TypeDescription actuatorTypeNameMock = mock(TypeDescription.class);
    when(actuatorType.getActuatorTypeName()).thenReturn(actuatorTypeNameMock);

    ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
    actuatorTypeRepository.save(actuatorType);

    //Act
    boolean actuatorTypeExists = actuatorTypeRepository.existsOfName(actuatorTypeNameMock);

    //Assert
    assertTrue(actuatorTypeExists);
  }

  /**
   * Test the existsOfName method with a null ActuatorTypeName.
   */
  @Test
  void shouldReturnFalse_whenGivenNullActuatorTypeName() {
    //Arrange
    ActuatorType actuatorType = mock(ActuatorType.class);

    TypeDescription actuatorTypeNameMock = mock(TypeDescription.class);
    when(actuatorType.getActuatorTypeName()).thenReturn(actuatorTypeNameMock);

    ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
    actuatorTypeRepository.save(actuatorType);

    //Act
    boolean actuatorTypeExists = actuatorTypeRepository.existsOfName(null);

    //Assert
    assertFalse(actuatorTypeExists);
  }


}