/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import smarthome.ddd.IAssembler;
import smarthome.domain.actuator_model.ActuatorModelFactoryImpl;
import smarthome.domain.actuator_model.IActuatorModelFactory;
import smarthome.domain.repository.IActuatorModelRepository;
import smarthome.domain.repository.ISensorModelRepository;
import smarthome.domain.repository.ISensorTypeRepository;
import smarthome.domain.repository.IUnitRepository;
import smarthome.domain.sensor_model.ISensorModelFactory;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.sensor_model.SensorModelFactoryImpl;
import smarthome.domain.sensor_type.ISensorTypeFactory;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.sensor_type.SensorTypeFactoryImpl;
import smarthome.domain.unit.IUnitFactory;
import smarthome.domain.unit.Unit;
import smarthome.domain.unit.UnitFactoryImpl;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorModelName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.mapper.SensorTypeAssembler;
import smarthome.mapper.UnitAssembler;
import smarthome.persistence.mem.ActuatorModelRepository;
import smarthome.persistence.mem.SensorModelRepository;
import smarthome.persistence.mem.SensorTypeRepository;
import smarthome.service.ISensorTypeService;
import smarthome.service.IUnitService;
import smarthome.service.SensorTypeServiceImpl;
import smarthome.service.UnitServiceImpl;
import smarthome.utils.dto.SensorTypeDTO;
import smarthome.utils.dto.UnitDTO;
import smarthome.utils.entry_dto.SensorTypeEntryDTO;

class AddSensorTypeControllerTest {

  /**
   * Test if the constructor of AddSensorTypeController throws an exception when the
   * sensorTypeService is null.
   */
  @Test
  void shouldThrowExceptionWhenSensorTypeServiceIsNull() {
    //Arrange
    ISensorTypeService sensorTypeServiceImpl = null;
    IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler = new SensorTypeAssembler();
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    IUnitFactory unitFactory = new UnitFactoryImpl();
    IUnitService unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);
    IAssembler<Unit, UnitDTO> unitAssembler = new UnitAssembler();

    String expectedMessage = "Sensor type service is required";

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler,
            unitServiceImpl, unitAssembler));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test if the constructor of AddSensorTypeController throws an exception when the unitService is
   * null.
   */
  @Test
  void shouldThrowException_WhenUnitServiceIsNull() {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    ISensorTypeFactory sensorTypeFactory = new SensorTypeFactoryImpl();
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ISensorTypeService sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler = new SensorTypeAssembler();
    IUnitService unitServiceImpl = null;
    IAssembler<Unit, UnitDTO> unitAssembler = new UnitAssembler();

    String expectedMessage = "Unit service is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler,
            unitServiceImpl, unitAssembler));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test if the constructor of AddSensorTypeController throws an exception when the
   * sensorTypeAssembler is null.
   */
  @Test
  void shouldThrowExceptionWhenSensorTypeAssemblerIsNull() {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    ISensorTypeFactory sensorTypeFactory = new SensorTypeFactoryImpl();
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ISensorTypeService sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = null;
    IUnitFactory unitFactory = new UnitFactoryImpl();
    IUnitService unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);
    IAssembler<Unit, UnitDTO> unitAssembler = new UnitAssembler();

    String expectedMessage = "Sensor type assembler is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler,
            unitServiceImpl, unitAssembler));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test if the constructor of AddSensorTypeController throws an exception when the unitAssembler
   * is null.
   */
  @Test
  void shouldThrowExceptionWhenUnitAssemblerIsNull() {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    ISensorTypeFactory sensorTypeFactory = new SensorTypeFactoryImpl();
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ISensorTypeService sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);
    SensorTypeAssembler sensorTypeAssembler = new SensorTypeAssembler();
    IUnitFactory unitFactory = new UnitFactoryImpl();
    IUnitService unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);
    IAssembler<Unit, UnitDTO> unitAssembler = null;

    String expectedMessage = "Unit assembler is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new AddSensorTypeController(sensorTypeServiceImpl, sensorTypeAssembler,
            unitServiceImpl, unitAssembler));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test if the getSupportedUnits method returns a list of units.
   *
   * @throws InstantiationException if an instantiation error occurs.
   */
  @Test
  void shouldReturnListOfUnitsWhenUnitsLoaded() throws InstantiationException {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    ISensorTypeFactory sensorTypeFactory = new SensorTypeFactoryImpl();
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ISensorTypeService sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);

    IUnitFactory unitFactory = new UnitFactoryImpl();
    IUnitService unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);

    IAssembler<Unit, UnitDTO> unitAssembler = new UnitAssembler();
    IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler = new SensorTypeAssembler();
    ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

    ISensorModelRepository sensorModelRepository = mock(ISensorModelRepository.class);
    UnitDescription unitDescription = new UnitDescription("Celsius");
    UnitSymbol unitSymbol = new UnitSymbol("C");
    SensorModelName sensorModelName = new SensorModelName("name");
    ModelPath modelPath = new ModelPath("smart_home.domain.sensor.temperature_sensor");
    SensorTypeID sensorTypeID = new SensorTypeID(UUID.randomUUID().toString());
    SensorModel sensorModel = sensorModelFactory.createSensorModel(sensorModelName, modelPath,
        sensorTypeID);
    when(sensorModelRepository.findAll()).thenReturn(List.of(sensorModel));
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);
    when(unitRepository.findAll()).thenReturn(List.of(unit));

    IActuatorModelRepository actuatorModelRepository = mock(IActuatorModelRepository.class);
    IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();

    List<UnitDTO> expected = unitAssembler.domainToDTO(unitRepository.findAll());

    AddSensorTypeController addSensorTypeController = new AddSensorTypeController(
        sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler);
    //Act
    List<UnitDTO> actual = addSensorTypeController.getSupportedUnits();
    //Assert
    assertEquals(expected.toString(), actual.toString());

  }

  /**
   * Test if the addAndSaveSensorType method returns a SensorTypeDTO when a sensor type is added.
   *
   * @throws InstantiationException if an instantiation error occurs.
   */
  @Test
  void shouldReturnSensorTypeDTOWhenSensorTypeAdded() throws InstantiationException {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    ISensorTypeFactory sensorTypeFactory = new SensorTypeFactoryImpl();
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ISensorTypeService sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);

    IUnitFactory unitFactory = new UnitFactoryImpl();
    IUnitService unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);

    IAssembler<Unit, UnitDTO> unitAssembler = new UnitAssembler();
    IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler = new SensorTypeAssembler();
    ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

    ISensorModelRepository sensorModelRepository = mock(ISensorModelRepository.class);
    UnitDescription unitDescription = new UnitDescription("Celsius");
    UnitSymbol unitSymbol = new UnitSymbol("C");
    SensorModelName sensorModelName = new SensorModelName("name");
    ModelPath modelPath = new ModelPath("smart_home.domain.sensor.temperature_sensor");
    SensorTypeID sensorTypeID = new SensorTypeID(UUID.randomUUID().toString());
    SensorModel sensorModel = sensorModelFactory.createSensorModel(sensorModelName, modelPath,
        sensorTypeID);
    when(sensorModelRepository.findAll()).thenReturn(List.of(sensorModel));
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);
    when(unitRepository.containsOfIdentity(unit.getID())).thenReturn(true);

    IActuatorModelRepository actuatorModelRepository = mock(IActuatorModelRepository.class);
    IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();

    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unitID = unit.getID();
    AddSensorTypeController addSensorTypeController = new AddSensorTypeController(
        sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler);
    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);
    when(sensorTypeRepository.containsOfIdentity(sensorType.getID())).thenReturn(false);
    when(sensorTypeRepository.save(sensorType)).thenReturn(sensorType);

    String sensorTypeDescription = "Temperature";
    String supportedUnit = "Celsius";
    SensorTypeEntryDTO sensorTypeDataDTO = new SensorTypeEntryDTO(sensorTypeDescription,
        supportedUnit);
    SensorTypeDTO expectedSensorTypeDTO = sensorTypeAssembler.domainToDTO(sensorType);
    //Act
    SensorTypeDTO actual = addSensorTypeController.addAndSaveSensorType(sensorTypeDataDTO);
    //Assert
    assertEquals(expectedSensorTypeDTO.toString(), actual.toString());
  }


  /**
   * Test if the addAndSaveSensorType method throws an exception when the sensor type already
   * exists.
   *
   * @throws InstantiationException if an instantiation error occurs.
   */
  @Test
  void shouldThrowExceptionWhenSensorTypeAlreadyExists() throws InstantiationException {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    ISensorTypeFactory sensorTypeFactory = new SensorTypeFactoryImpl();
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ISensorTypeService sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);

    IUnitFactory unitFactory = new UnitFactoryImpl();
    IUnitService unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);

    IAssembler<Unit, UnitDTO> unitAssembler = new UnitAssembler();
    IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler = new SensorTypeAssembler();
    ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

    ISensorModelRepository sensorModelRepository = mock(ISensorModelRepository.class);
    UnitDescription unitDescription = new UnitDescription("Celsius");
    UnitSymbol unitSymbol = new UnitSymbol("C");
    SensorModelName sensorModelName = new SensorModelName("name");
    ModelPath modelPath = new ModelPath("smart_home.domain.sensor.temperature_sensor");
    SensorTypeID sensorTypeID = new SensorTypeID(UUID.randomUUID().toString());
    SensorModel sensorModel = sensorModelFactory.createSensorModel(sensorModelName, modelPath,
        sensorTypeID);
    when(sensorModelRepository.findAll()).thenReturn(List.of(sensorModel));
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);
    when(unitRepository.containsOfIdentity(unit.getID())).thenReturn(true);

    IActuatorModelRepository actuatorModelRepository = mock(IActuatorModelRepository.class);
    IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();

    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unitID = unit.getID();
    AddSensorTypeController addSensorTypeController = new AddSensorTypeController(
        sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler);
    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);
    when(sensorTypeRepository.containsOfIdentity(sensorType.getID())).thenReturn(true);

    String sensorTypeDescription = "Temperature";
    String supportedUnit = "Celsius";
    SensorTypeEntryDTO sensorTypeDataDTO = new SensorTypeEntryDTO(sensorTypeDescription,
        supportedUnit);
    SensorTypeDTO expectedSensorTypeDTO = sensorTypeAssembler.domainToDTO(sensorType);

    String expectedMessage = "Invalid sensor type data.";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> addSensorTypeController.addAndSaveSensorType(sensorTypeDataDTO));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test if the addAndSaveSensorType method throws an exception when the sensor type data is
   * invalid.
   *
   * @throws InstantiationException if an instantiation error occurs.
   */
  @Test
  void shouldThrowExceptionWhenAddingSensorTypeOfUnsupportedUnit() throws InstantiationException {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
    ISensorTypeFactory sensorTypeFactory = new SensorTypeFactoryImpl();
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ISensorTypeService sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);

    IUnitFactory unitFactory = new UnitFactoryImpl();
    IUnitService unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);

    IAssembler<Unit, UnitDTO> unitAssembler = new UnitAssembler();
    IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler = new SensorTypeAssembler();

    ISensorModelRepository sensorModelRepository = new SensorModelRepository();
    ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

    IActuatorModelRepository actuatorModelRepository = new ActuatorModelRepository();
    IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();

    AddSensorTypeController addSensorTypeController = new AddSensorTypeController(
        sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler);

    String sensorTypeDescription = "Temperature";

    String unsupportedUnit = "UnsupportedUnit";
    SensorTypeEntryDTO unsupportedSensorTypeDataDTO = new SensorTypeEntryDTO(sensorTypeDescription,
        unsupportedUnit);

    String expectedMessage = "Invalid sensor type data.";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> addSensorTypeController.addAndSaveSensorType(unsupportedSensorTypeDataDTO));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test if the addAndSaveSensorType method throws an exception when the sensor has the same
   * description but different unit.
   *
   * @throws InstantiationException if an instantiation error occurs.
   */
  @Test
  void shouldThrowExceptionWhenAddingSensorWithSameDescriptionButDifferentUnit()
      throws InstantiationException {
    //Arrange
    ISensorTypeRepository sensorTypeRepository = mock(ISensorTypeRepository.class);
    ISensorTypeFactory sensorTypeFactory = new SensorTypeFactoryImpl();
    IUnitRepository unitRepository = mock(IUnitRepository.class);
    ISensorTypeService sensorTypeServiceImpl = new SensorTypeServiceImpl(sensorTypeRepository,
        sensorTypeFactory, unitRepository);

    IUnitFactory unitFactory = new UnitFactoryImpl();
    IUnitService unitServiceImpl = new UnitServiceImpl(unitRepository, unitFactory);

    IAssembler<Unit, UnitDTO> unitAssembler = new UnitAssembler();
    IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler = new SensorTypeAssembler();
    ISensorModelFactory sensorModelFactory = new SensorModelFactoryImpl();

    ISensorModelRepository sensorModelRepository = mock(ISensorModelRepository.class);
    UnitDescription unitDescription = new UnitDescription("Celsius");
    UnitSymbol unitSymbol = new UnitSymbol("C");
    SensorModelName sensorModelName = new SensorModelName("name");
    ModelPath modelPath = new ModelPath("smart_home.domain.sensor.temperature_sensor");
    SensorTypeID sensorTypeID = new SensorTypeID(UUID.randomUUID().toString());
    SensorModel sensorModel = sensorModelFactory.createSensorModel(sensorModelName, modelPath,
        sensorTypeID);
    when(sensorModelRepository.findAll()).thenReturn(List.of(sensorModel));
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);
    when(unitRepository.containsOfIdentity(unit.getID())).thenReturn(true);

    IActuatorModelRepository actuatorModelRepository = mock(IActuatorModelRepository.class);
    IActuatorModelFactory actuatorModelFactory = new ActuatorModelFactoryImpl();

    TypeDescription typeDescription = new TypeDescription("Temperature");
    UnitID unitID = unit.getID();
    AddSensorTypeController addSensorTypeController = new AddSensorTypeController(
        sensorTypeServiceImpl, sensorTypeAssembler, unitServiceImpl, unitAssembler);
    SensorType sensorType = sensorTypeServiceImpl.createSensorType(typeDescription, unitID);
    sensorTypeServiceImpl.addSensorType(sensorType);
    when(sensorTypeRepository.containsOfIdentity(sensorType.getID())).thenReturn(false);
    when(sensorTypeRepository.save(sensorType)).thenReturn(sensorType);

    String sensorTypeDescription = "Temperature";
    String supportedUnit = "Celsius";
    SensorTypeEntryDTO sensorTypeDataDTO = new SensorTypeEntryDTO(sensorTypeDescription,
        supportedUnit);
    SensorTypeDTO expectedSensorTypeDTO = sensorTypeAssembler.domainToDTO(sensorType);

    String sensorTypeDescription2 = "Temperature";
    String supportedUnit2 = "Kelvin";
    SensorTypeEntryDTO sensorTypeDataDTO2 = new SensorTypeEntryDTO(sensorTypeDescription2,
        supportedUnit2);

    String expectedMessage = "Invalid sensor type data.";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> addSensorTypeController.addAndSaveSensorType(sensorTypeDataDTO2));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }


}