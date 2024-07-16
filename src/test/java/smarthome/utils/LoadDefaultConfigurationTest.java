/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorModelName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.service.IActuatorModelService;
import smarthome.service.IActuatorTypeService;
import smarthome.service.ISensorModelService;
import smarthome.service.ISensorTypeService;
import smarthome.service.IUnitService;


class LoadDefaultConfigurationTest {


  @Test
  void shouldLoadDefaultUnitsFromList_WhenLoadDefaultSupportedUnitsCalledWithValidPath() {
    // Arrange

    IUnitService unitService = mock(IUnitService.class);
    ISensorModelService sensorModelService = mock(ISensorModelService.class);
    ISensorTypeService sensorTypeService = mock(ISensorTypeService.class);
    IActuatorModelService actuatorModelService = mock(IActuatorModelService.class);
    IActuatorTypeService actuatorTypeService = mock(IActuatorTypeService.class);
    CvsLoader cvsLoader = mock(CvsLoader.class);
    LoadDefaultConfiguration loadDefaultConfiguration = new LoadDefaultConfiguration(unitService,
        sensorModelService, sensorTypeService, actuatorModelService, actuatorTypeService,
        cvsLoader);
    ;

    List<String[]> mockCsvData = Arrays.asList(
        new String[]{"Header1", "Header2"},
        new String[]{"Description", "C"},
        new String[]{"Description", "F"}
    );

    //Expected Value object
    UnitDescription unitDescription1 = new UnitDescription("Description");
    UnitSymbol unitSymbol1 = new UnitSymbol("C");
    UnitDescription unitDescription2 = new UnitDescription("Description");
    UnitSymbol unitSymbol2 = new UnitSymbol("F");

    // Stub the method to return the mock CSV data
    when(cvsLoader.loadCVSFileToListOfStrings(any())).thenReturn(mockCsvData);

    // Act
    loadDefaultConfiguration.loadDefaultSupportedUnits("defaultconfig/units.csv");

    // Verify that the unitService.addunitType method was called with the correct parameters
    verify(unitService).addunitType(unitDescription1, unitSymbol1);
    verify(unitService).addunitType(unitDescription2, unitSymbol2);
  }

  @Test
  void shouldLoadDefaultSensorTypesFromList_WhenLoadDefaultSupportedSensorTypesCalledWithValidPath() {
    // Arrange

    IUnitService unitService = mock(IUnitService.class);
    ISensorModelService sensorModelService = mock(ISensorModelService.class);
    ISensorTypeService sensorTypeService = mock(ISensorTypeService.class);
    IActuatorModelService actuatorModelService = mock(IActuatorModelService.class);
    IActuatorTypeService actuatorTypeService = mock(IActuatorTypeService.class);
    CvsLoader cvsLoader = mock(CvsLoader.class);
    LoadDefaultConfiguration loadDefaultConfiguration = new LoadDefaultConfiguration(unitService,
        sensorModelService, sensorTypeService, actuatorModelService, actuatorTypeService,
        cvsLoader);
    ;

    List<String[]> mockCsvData = Arrays.asList(
        new String[]{"Header1", "Header2"},
        new String[]{"Temperature", "Celsius"},
        new String[]{"Humidity", "Percent"}
    );

    //Expected Value object
    TypeDescription typeDescription1 = new TypeDescription("Temperature");
    UnitID unitID1 = new UnitID("Celsius");
    TypeDescription typeDescription2 = new TypeDescription("Humidity");
    UnitID unitID2 = new UnitID("Percent");

    // Stub the method to return the mock CSV data
    when(cvsLoader.loadCVSFileToListOfStrings(any())).thenReturn(mockCsvData);

    // Act
    loadDefaultConfiguration.loadDefaultSupportedSensorTypes("defaultconfig/sensortypes.csv");

    // Verify that the sensorTypeService.addSensorType method was called with the correct parameters
    verify(sensorTypeService).createSensorType(typeDescription1, unitID1);
    verify(sensorTypeService).createSensorType(typeDescription2, unitID2);
  }

  @Test
  void shouldLoadDefaultActuatorTypesFromList_WhenLoadDefaultSupportedActuatorTypesCalledWithValidPath() {
    // Arrange

    IUnitService unitService = mock(IUnitService.class);
    ISensorModelService sensorModelService = mock(ISensorModelService.class);
    ISensorTypeService sensorTypeService = mock(ISensorTypeService.class);
    IActuatorModelService actuatorModelService = mock(IActuatorModelService.class);
    IActuatorTypeService actuatorTypeService = mock(IActuatorTypeService.class);
    CvsLoader cvsLoader = mock(CvsLoader.class);
    LoadDefaultConfiguration loadDefaultConfiguration = new LoadDefaultConfiguration(unitService,
        sensorModelService, sensorTypeService, actuatorModelService, actuatorTypeService,
        cvsLoader);
    ;

    List<String[]> mockCsvData = Arrays.asList(
        new String[]{"Header1", "Header2"},
        new String[]{"BlindRoller", "Percent"},
        new String[]{"SetInteger", "Integer"}
    );

    //Expected Value object
    TypeDescription typeDescription1 = new TypeDescription("BlindRoller");
    TypeDescription typeDescription2 = new TypeDescription("SetInteger");

    UnitID unitID1 = new UnitID("Percent");
    UnitID unitID2 = new UnitID("Integer");

    // Stub the method to return the mock CSV data
    when(cvsLoader.loadCVSFileToListOfStrings(any())).thenReturn(mockCsvData);

    // Act
    loadDefaultConfiguration.loadDefaultSupportedActuatorTypes("defaultconfig/actuatortypes.csv");

    // Verify that the actuatorTypeService.addActuatorType method was called with the correct parameters
    verify(actuatorTypeService).createActuatorType(typeDescription1, unitID1);
    verify(actuatorTypeService).createActuatorType(typeDescription2, unitID2);
  }

  @Test
  void shouldLoadDefaultSensorModelsFromList_WhenLoadDefaultSupportedSensorModelsCalledWithValidPath() {
    // Arrange

    IUnitService unitService = mock(IUnitService.class);
    ISensorModelService sensorModelService = mock(ISensorModelService.class);
    ISensorTypeService sensorTypeService = mock(ISensorTypeService.class);
    IActuatorModelService actuatorModelService = mock(IActuatorModelService.class);
    IActuatorTypeService actuatorTypeService = mock(IActuatorTypeService.class);
    CvsLoader cvsLoader = mock(CvsLoader.class);
    LoadDefaultConfiguration loadDefaultConfiguration = new LoadDefaultConfiguration(unitService,
        sensorModelService, sensorTypeService, actuatorModelService, actuatorTypeService,
        cvsLoader);
    ;

    List<String[]> mockCsvData = Arrays.asList(
        new String[]{"Header1", "Header2", "Header3"},
        new String[]{"smarthome.domain.sensor.temperature_sensor.TemperatureSensor",
            "TemperatureSensor", "Temperature"},
        new String[]{"smarthome.domain.sensor.humidity_sensor.HumiditySensor", "HumiditySensor",
            "Humidity"}
    );

    //Expected Value object
    ModelPath modelPath = new ModelPath(
        "smarthome.domain.sensor.temperature_sensor.TemperatureSensor");
    SensorModelName sensorModelName1 = new SensorModelName("TemperatureSensor");
    SensorTypeID sensorTypeID1 = new SensorTypeID("Temperature");

    ModelPath modelPath2 = new ModelPath("smarthome.domain.sensor.humidity_sensor.HumiditySensor");
    SensorModelName sensorModelName2 = new SensorModelName("HumiditySensor");
    SensorTypeID sensorTypeID2 = new SensorTypeID("Humidity");

    // Stub the method to return the mock CSV data
    when(cvsLoader.loadCVSFileToListOfStrings(any())).thenReturn(mockCsvData);

    // Act
    loadDefaultConfiguration.loadDefaultSupportedSensorModels("defaultconfig/sensormodels.csv");

    // Verify that the sensorModelService.addSensorModel method was called with the correct parameters
    verify(sensorModelService).createSensorModel(sensorModelName1, modelPath, sensorTypeID1);
    verify(sensorModelService).createSensorModel(sensorModelName2, modelPath2, sensorTypeID2);
  }

  @Test
  void shouldLoadDefaultActuatorModelsFromList_WhenLoadDefaultSupportedActuatorModelsCalledWithValidPath() {
    // Arrange

    IUnitService unitService = mock(IUnitService.class);
    ISensorModelService sensorModelService = mock(ISensorModelService.class);
    ISensorTypeService sensorTypeService = mock(ISensorTypeService.class);
    IActuatorModelService actuatorModelService = mock(IActuatorModelService.class);
    IActuatorTypeService actuatorTypeService = mock(IActuatorTypeService.class);
    CvsLoader cvsLoader = mock(CvsLoader.class);
    LoadDefaultConfiguration loadDefaultConfiguration = new LoadDefaultConfiguration(unitService,
        sensorModelService, sensorTypeService, actuatorModelService, actuatorTypeService,
        cvsLoader);
    ;

    List<String[]> mockCsvData = Arrays.asList(
        new String[]{"Header1", "Header2", "Header3"},
        new String[]{"smarthome.domain.actuator.blind_roller.BlindRoller", "BlindRoller",
            "Percent"},
        new String[]{"smarthome.domain.actuator.set_integer.SetInteger", "SetInteger", "Integer"}
    );

    //Expected Value object
    ModelPath modelPath = new ModelPath("smarthome.domain.actuator.blind_roller.BlindRoller");
    ActuatorModelName sensorModelName1 = new ActuatorModelName("BlindRoller");
    ActuatorTypeID sensorTypeID1 = new ActuatorTypeID("Percent");

    ModelPath modelPath2 = new ModelPath("smarthome.domain.actuator.set_integer.SetInteger");
    ActuatorModelName sensorModelName2 = new ActuatorModelName("SetInteger");
    ActuatorTypeID sensorTypeID2 = new ActuatorTypeID("Integer");

    // Stub the method to return the mock CSV data
    when(cvsLoader.loadCVSFileToListOfStrings(any())).thenReturn(mockCsvData);

    // Act
    loadDefaultConfiguration.loadDefaultSupportedActuatorModels("defaultconfig/actuatormodels.csv");

    // Verify that the actuatorModelService.addActuatorModel method was called with the correct parameters
    verify(actuatorModelService).addActuatorModel(modelPath, sensorModelName1, sensorTypeID1);
    verify(actuatorModelService).addActuatorModel(modelPath2, sensorModelName2, sensorTypeID2);
  }
}