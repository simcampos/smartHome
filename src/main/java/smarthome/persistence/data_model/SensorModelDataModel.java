/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.data_model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.utils.Validator;

@Entity
@Table(name = "sensor_model")
public class SensorModelDataModel {

  @Id
  private String modelPath;
  private String sensorModelName;
  private String sensorTypeID;
  @Version
  private long version;


  /**
   * SensorModelDataModel constructor
   */
  public SensorModelDataModel() {
  }

  /**
   * SensorModelDataModel constructor
   *
   * @param sensorModel SensorModel object
   */
  public SensorModelDataModel(SensorModel sensorModel) {

    Validator.validateNotNull(sensorModel, "Sensor Model");
    this.modelPath = sensorModel.getModelPath().getID();
    this.sensorModelName = sensorModel.getName().getSensorModelName();
    this.sensorTypeID = sensorModel.getSensorTypeID().getID();
  }


  /**
   * Method to get model path
   *
   * @return String
   */
  public String getModelPath() {
    return this.modelPath;
  }

  /**
   * Method to get sensor model name
   *
   * @return String
   */
  public String getSensorModelName() {
    return this.sensorModelName;
  }

  /**
   * Method to get sensor type ID
   *
   * @return String
   */
  public String getSensorTypeID() {
    return this.sensorTypeID;
  }


}

