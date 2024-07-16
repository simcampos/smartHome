/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import smarthome.domain.repository.ISensorTypeRepository;
import smarthome.domain.repository.IUnitRepository;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.sensor_type.SensorTypeFactoryImpl;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;

class SensorTypeServiceImplTest {

  @Test
  void shouldInstantiateSensorTypeServiceWhenGivenValidParameters() {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);

    //Act
    SensorTypeServiceImpl result = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);

    //Assert
    assertNotNull(result);
  }

  @Test
  void shouldThrowExceptionWhenSensorTypeRepositoryIsNull() {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = null;
    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    String expectedMessage = "Sensor type repository is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenSensorTypeFactoryIsNull() {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    SensorTypeFactoryImpl sensorTypeFactory = null;
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    String expectedMessage = "Sensor type factory is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenunitTypeRepositoryIsNull() {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    IUnitRepository unitRepository = null;
    String expectedMessage = "Unit repository is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new SensorTypeServiceImpl(sensorTypeRepository, sensorTypeFactory, unitRepository));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldCreateValidSensorTypeWhenGivenValidParameters() {
    //Arrange
    TypeDescription typeDescription = mock(TypeDescription.class);
    UnitID unitID = mock(UnitID.class);
    SensorType sensorType = mock(SensorType.class);

    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    when(sensorTypeFactory.createSensorType(typeDescription, unitID)).thenReturn(sensorType);

    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(true);

    SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    //Act
    SensorType resultSensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    //Assert
    assertEquals(sensorType, resultSensorType);
  }

  @Test
  void shouldThrowExceptionWhenunitIDIsInvalid() {
    //Arrange
    TypeDescription typeDescription = mock(TypeDescription.class);
    UnitID unitID = mock(UnitID.class);
    SensorType sensorType = mock(SensorType.class);

    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    when(sensorTypeFactory.createSensorType(typeDescription, unitID)).thenReturn(sensorType);

    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    when(unitRepository.containsOfIdentity(unitID)).thenReturn(false);

    SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    String expectedMessage = "Please enter a valid unit type.";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> sensorTypeServiceImpl.createSensorType(typeDescription, unitID));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldSaveSensorTypeWhenGivenValidParameters() {
    //Arrange
    SensorType sensorType = mock(SensorType.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    when(sensorTypeRepository.save(sensorType)).thenReturn(sensorType);

    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);

    SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    //Act
    SensorType resultSensorType = sensorTypeServiceImpl.addSensorType(sensorType);
    //Assert
    assertEquals(sensorType, resultSensorType);
  }

  @Test
  void shouldThrowExceptionWhenSavingNullSensorType() {
    //Arrange
    SensorType sensorType = null;
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);

    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);

    SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    String expectedMessage = "Please enter a valid sensor type.";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> sensorTypeServiceImpl.addSensorType(sensorType));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldFindSensorTypeByIDWhenSensorTypeExistInRepository() {
    //Arrange
    SensorType sensorType = mock(SensorType.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    when(sensorTypeRepository.ofIdentity(sensorTypeID)).thenReturn(Optional.of(sensorType));

    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);

    SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    //Act
    SensorType resultSensorType = sensorTypeServiceImpl.getSensorTypeByID(sensorTypeID).get();
    //Assert
    assertEquals(sensorType, resultSensorType);
  }

  @Test
  void shouldThrowExceptionWhenFindingSensorTypeByNullID() {
    //Arrange
    SensorTypeID sensorTypeID = null;
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);

    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);

    SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    String expectedMessage = "Please enter a valid sensor type ID.";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> sensorTypeServiceImpl.getSensorTypeByID(sensorTypeID));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void shouldReturnOptionalEmptyWhenFindingSensorTypeByNonExistentID() {
    //Arrange
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    when(sensorTypeRepository.ofIdentity(sensorTypeID)).thenReturn(Optional.empty());

    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);

    SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    //Act
    Optional<SensorType> resultSensorType = sensorTypeServiceImpl.getSensorTypeByID(sensorTypeID);
    //Assert
    assertTrue(resultSensorType.isEmpty());
  }

  @Test
  void shouldReturnAllSensorTypesWhenFindingAllSensorTypes() {
    //Arrange
    SensorType sensorType = mock(SensorType.class);
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    when(sensorTypeRepository.findAll()).thenReturn(List.of(sensorType));

    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    List<SensorType> expectedSensorTypes = List.of(sensorType);

    SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    //Act
    List<SensorType> resultSensorTypes = sensorTypeServiceImpl.getAllSensorTypes();
    //Assert
    assertEquals(expectedSensorTypes, resultSensorTypes);
  }

  @Test
  void shouldReturnEmptyListWhenNoSensorTypesExist() {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    when(sensorTypeRepository.findAll()).thenReturn(List.of());

    SensorTypeFactoryImpl sensorTypeFactory = mock(SensorTypeFactoryImpl.class);
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    List<SensorType> expectedSensorTypes = List.of();

    SensorTypeServiceImpl sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    //Act
    List<SensorType> resultSensorTypes = sensorTypeServiceImpl.getAllSensorTypes();
    //Assert
    assertEquals(expectedSensorTypes, resultSensorTypes);
  }

}