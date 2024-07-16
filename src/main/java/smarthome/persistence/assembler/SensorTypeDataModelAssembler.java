/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.assembler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import smarthome.domain.sensor_type.ISensorTypeFactory;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.persistence.data_model.SensorTypeDataModel;
import smarthome.utils.Validator;

@Component
public class SensorTypeDataModelAssembler implements
    IDataModelAssembler<SensorTypeDataModel, SensorType> {

  private final ISensorTypeFactory sensorTypeFactory;

  /**
   * Constructor of SensorTypeDataModelConverter
   *
   * @param sensorTypeFactory the sensorTypeFactory to be used
   */
  public SensorTypeDataModelAssembler(ISensorTypeFactory sensorTypeFactory) {
    Validator.validateNotNull(sensorTypeFactory, "Sensor Type Factory");
    this.sensorTypeFactory = sensorTypeFactory;
  }

  /**
   * Method to convert a domain entity into a DataModel.
   *
   * @param sensorTypeDataModel is the domain entity to be converted.
   * @return the DataModel.
   */
  @Override
  public SensorType toDomain(SensorTypeDataModel sensorTypeDataModel) {
    Validator.validateNotNull(sensorTypeDataModel, "Sensor Type Data Model");

    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeDataModel.getSensorTypeID());
    TypeDescription typeDescription = new TypeDescription(sensorTypeDataModel.getTypeDescription());
    UnitID unitID = new UnitID(sensorTypeDataModel.getUnitID());

    return sensorTypeFactory.createSensorType(sensorTypeID, typeDescription, unitID);
  }

  /**
   * Method to convert a list of domain entities into a list of DataModels.
   *
   * @param sensorTypeDataModels is the list of domain entities to be converted.
   * @return the list of DataModels.
   */
  @Override
  public List<SensorType> toDomain(List<SensorTypeDataModel> sensorTypeDataModels) {

    List<SensorType> sensorTypes = new ArrayList<>();

    for (SensorTypeDataModel sensorTypeDataModel : sensorTypeDataModels) {
      SensorType sensorType = toDomain(sensorTypeDataModel);
      sensorTypes.add(sensorType);
    }

    return sensorTypes;
  }
}
