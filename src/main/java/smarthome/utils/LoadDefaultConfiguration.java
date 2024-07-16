/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import smarthome.domain.actuator_type.ActuatorType;
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

@Component
@Profile({"!docker", "!demo", "!frontendtest"})
public class LoadDefaultConfiguration {

  private IUnitService unitService;
  private ISensorModelService sensorModelService;
  private ISensorTypeService sensorTypeService;
  private IActuatorModelService actuatorModelService;
  private IActuatorTypeService actuatorTypeService;
  private CvsLoader cvsLoader;

  public LoadDefaultConfiguration(
      @Autowired IUnitService unitService,
      @Autowired ISensorModelService sensorModelService,
      @Autowired ISensorTypeService sensorTypeService,
      @Autowired IActuatorModelService actuatorModelService,
      @Autowired IActuatorTypeService actuatorTypeService,
      @Autowired CvsLoader cvsLoader) {
    this.unitService = unitService;
    this.sensorModelService = sensorModelService;
    this.sensorTypeService = sensorTypeService;
    this.actuatorModelService = actuatorModelService;
    this.actuatorTypeService = actuatorTypeService;
    this.cvsLoader = cvsLoader;

    loadDefaultUnits();
    loadDefaultModels();
    loadDefaultTypes();
  }

  private void loadDefaultUnits() {
    loadDefaultSupportedUnits("defaultconfig/units.csv");
  }

  private void loadDefaultModels (){
    loadDefaultSupportedSensorModels("defaultconfig/sensormodel.csv");
    loadDefaultSupportedActuatorModels("defaultconfig/actuatormodel.csv");
  }

  private void loadDefaultTypes(){
    loadDefaultSupportedSensorTypes("defaultconfig/sensortype.csv");
    loadDefaultSupportedActuatorTypes("defaultconfig/actuatortype.csv");
  }


  public void loadDefaultSupportedUnits(String path) {
    List<String[]> rows = cvsLoader.loadCVSFileToListOfStrings(path);
    int startAfterTheHeader = 1;

    if (!rows.isEmpty()) {
      for (int i = startAfterTheHeader; i < rows.size(); i++) {

        String unitDescription = rows.get(i)[0];
        UnitDescription unitDescription1 = new UnitDescription(unitDescription);
        String unitSymbol = rows.get(i)[1];
        UnitSymbol unitSymbol1 = new UnitSymbol(unitSymbol);
        unitService.addunitType(unitDescription1, unitSymbol1);
      }
    }
  }


  public void loadDefaultSupportedSensorTypes(String path) {
    List<String[]> rows = cvsLoader.loadCVSFileToListOfStrings(path);
    int startAfterTheHeader = 1;
    if (!rows.isEmpty()) {
      for (int i = startAfterTheHeader; i < rows.size(); i++) {
        String sensorTypeDescription = rows.get(i)[0];
        TypeDescription typeDescription = new TypeDescription(sensorTypeDescription);
        String unitDescriptionID = rows.get(i)[1];
        UnitID unitID = new UnitID(unitDescriptionID);
        sensorTypeService.createSensorType(typeDescription, unitID);
      }
    }
  }


  public void loadDefaultSupportedSensorModels(String path) {
    List<String[]> rows = cvsLoader.loadCVSFileToListOfStrings(path);
    int startAfterTheHeader = 1;
    if (!rows.isEmpty()) {
      for (int i = startAfterTheHeader; i < rows.size(); i++) {
        String sensorModelPath = rows.get(i)[0];
        ModelPath modelPath = new ModelPath(sensorModelPath);
        String sensorModelName = rows.get(i)[1];
        SensorModelName modelName = new SensorModelName(sensorModelName);
        String sensorTypeID = rows.get(i)[2];
        SensorTypeID sensorTypeID1 = new SensorTypeID(sensorTypeID);
        sensorModelService.createSensorModel(modelName, modelPath, sensorTypeID1);
      }
    }

  }

  public void loadDefaultSupportedActuatorTypes(String path) {
    List<String[]> rows = cvsLoader.loadCVSFileToListOfStrings(path);
    int startAfterTheHeader = 1;
    if (!rows.isEmpty()) {
      for (int i = startAfterTheHeader; i < rows.size(); i++) {
        String actuatorTypeDescription = rows.get(i)[0];
        TypeDescription typeDescription = new TypeDescription(actuatorTypeDescription);
        String unitDescriptionID = rows.get(i)[1];
        UnitID unitID = new UnitID(unitDescriptionID);
        ActuatorType actuatorType = actuatorTypeService.createActuatorType(typeDescription, unitID);
        actuatorTypeService.addActuatorType(actuatorType);
      }
    }
  }


  public void loadDefaultSupportedActuatorModels(String path) {
    List<String[]> rows = cvsLoader.loadCVSFileToListOfStrings(path);
    int startAfterTheHeader = 1;
    if (!rows.isEmpty()) {
      for (int i = startAfterTheHeader; i < rows.size(); i++) {
        String actuatorModelPath = rows.get(i)[0];
        ModelPath modelPath = new ModelPath(actuatorModelPath);
        String actuatorModelName = rows.get(i)[1];
        ActuatorModelName modelName = new ActuatorModelName(actuatorModelName);
        String actuatorTypeID = rows.get(i)[2];
        ActuatorTypeID sensorTypeID1 = new ActuatorTypeID(actuatorTypeID);
        actuatorModelService.addActuatorModel(modelPath, modelName, sensorTypeID1);
      }
    }

  }
}
