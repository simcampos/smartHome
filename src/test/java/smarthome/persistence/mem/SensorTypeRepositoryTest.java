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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.value_object.SensorTypeID;

class SensorTypeRepositoryTest {


  /**
   * Test of SensorTypeRepository constructor.
   */
  @Test
  void shouldInstantiateSensorTypeRepository() {
    //Arrange
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();

    //Act + Assert
    assertNotNull(sensorTypeRepository);
  }

  /**
   * Test of save method when given valid SensorType.
   */
  @Test
  void shouldSaveSensorType_whenGivenValidSensorType() {
    //Arrange
    SensorType sensorType = mock(SensorType.class);

    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();

    //Act
    SensorType savedSensorType = sensorTypeRepository.save(sensorType);

    //Assert
    assertEquals(sensorType, savedSensorType);
  }

  /**
   * Test of save method when given null SensorType.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenGivenNullSensorType() {
    //Arrange
    SensorType sensorType = null;
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    String expectedMessage = "SensorType is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> sensorTypeRepository.save(sensorType));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test of save method when SensorType already exists.
   */
  @Test
  void shouldThrowException_whenSensorTypeAlreadyExists() {
    //Arrange
    SensorType sensorType = mock(SensorType.class);

    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();

    sensorTypeRepository.save(sensorType);
    String expectedMessage = "SensorType already exists.";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> sensorTypeRepository.save(sensorType));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test of findAll method when there are SensorTypes saved.
   */
  @Test
  void shouldRetunrAllSensorTypes_whenFindAllIsCalled() {
    //Arrange
    SensorType sensorType1 = mock(SensorType.class);
    SensorTypeID sensorTypeID1 = mock(SensorTypeID.class);
    when(sensorType1.getID()).thenReturn(sensorTypeID1);

    SensorType sensorType2 = mock(SensorType.class);
    SensorTypeID sensorTypeID2 = mock(SensorTypeID.class);
    when(sensorType2.getID()).thenReturn(sensorTypeID2);

    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();

    sensorTypeRepository.save(sensorType1);
    sensorTypeRepository.save(sensorType2);
    List<SensorType> expectedList = List.of(sensorType1, sensorType2);

    //Act
    List<SensorType> allSensorTypes = sensorTypeRepository.findAll();

    //Assert
    assertEquals(expectedList, allSensorTypes);
  }

  /**
   * Test of findAll method when there are no SensorTypes saved.
   */
  @Test
  void shouldReturnEmptyList_whenNoSensorTypesAreSaved() {
    //Arrange
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();

    //Act
    List<SensorType> allSensorTypes = sensorTypeRepository.findAll();

    //Assert
    assertTrue(allSensorTypes.isEmpty());
  }

  /**
   * Test of ofIdentity method when given valid ID.
   */
  @Test
  void shouldReturnSensorType_whenGivenValidID() {
    //Arrange
    SensorType sensorType = mock(SensorType.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorType.getID()).thenReturn(sensorTypeID);

    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    sensorTypeRepository.save(sensorType);

    //Act
    SensorType returnedSensorType = sensorTypeRepository.ofIdentity(sensorTypeID).get();

    //Assert
    assertEquals(sensorType, returnedSensorType);
  }

  /**
   * Test of ofIdentity method when given invalid ID.
   */
  @Test
  void shouldReturnOptinalEmpty_whenGivenInvalidID() {
    //Arrange
    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();

    SensorType sensorType = mock(SensorType.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorType.getID()).thenReturn(sensorTypeID);

    sensorTypeRepository.save(sensorType);

    SensorTypeID nonExistentID = mock(SensorTypeID.class);

    //Act
    Optional<SensorType> returnedSensorType = sensorTypeRepository.ofIdentity(nonExistentID);

    //Assert
    assertTrue(returnedSensorType.isEmpty());
  }

  /**
   * Test of containsOfIdentity method when given valid ID.
   */
  @Test
  void shouldReturnTrue_whenGivenValidID() {
    //Arrange
    SensorType sensorType = mock(SensorType.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorType.getID()).thenReturn(sensorTypeID);

    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    sensorTypeRepository.save(sensorType);

    //Act
    boolean containsSensorType = sensorTypeRepository.containsOfIdentity(sensorTypeID);

    //Assert
    assertTrue(containsSensorType);
  }

  /**
   * Test of containsOfIdentity method when given invalid ID.
   */
  @Test
  void shouldReturnFalse_whenGivenInvalidID() {
    //Arrange
    SensorType sensorType = mock(SensorType.class);

    SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    sensorTypeRepository.save(sensorType);

    SensorTypeID nonExistentID = mock(SensorTypeID.class);

    //Act
    boolean containsSensorType = sensorTypeRepository.containsOfIdentity(nonExistentID);

    //Assert
    assertFalse(containsSensorType);
  }

}
