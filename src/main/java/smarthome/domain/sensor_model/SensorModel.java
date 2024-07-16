/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor_model;

import smarthome.ddd.IAggregateRoot;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorModelName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.Validator;

public class SensorModel implements IAggregateRoot<ModelPath> {

  private final SensorModelName name;
  private final ModelPath modelPath;
  private final SensorTypeID sensorTypeID;

  /**
   * Creates a new sensor model with the given sensor model name, model path, and sensor type ID.
   *
   * @param name The name of the sensor model.
   * @param modelPath       The path to the model.
   * @param sensorTypeID    The ID of the sensor type.
   */
  SensorModel(
      SensorModelName name, ModelPath modelPath, SensorTypeID sensorTypeID) {
    Validator.validateNotNull(name, "SensorModelName");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(sensorTypeID, "SensorTypeID");
    this.name = name;
    this.modelPath = modelPath;
    this.sensorTypeID = sensorTypeID;
  }


  /**
   * Returns the sensor type ID.
   *
   * @return The sensor type ID.
   */
  public SensorTypeID getSensorTypeID() {
    return sensorTypeID;
  }

  /**
   * Returns the sensor model name.
   *
   * @return The sensor model name.
   */
  public SensorModelName getName() {
    return name;
  }

  /**
   * Returns the model path.
   *
   * @return The model path.
   */
  public ModelPath getModelPath() {
    return modelPath;
  }

  /**
   * Returns the unique identifier of the SensorModel instance.
   *
   * @return The ModelPath that uniquely identifies the sensor model.
   */
  @Override
  public ModelPath getID() {
    return modelPath;
  }

  /**
   * Compares the sensor model to another object.
   *
   * @param object The object to compare.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof SensorModel sensorModel) {
      return modelPath.equals(sensorModel.modelPath);
    }
    return false;
  }

  /**
   * Returns the hash code of the sensor model.
   *
   * @return The hash code of the sensor model.
   */
  @Override
  public int hashCode() {
    return modelPath.hashCode();
  }

  /**
   * Returns a string representation of the sensor model.
   *
   * @return A string representation of the sensor model.
   */
  @Override
  public String toString() {
    return "SensorModel: sensorModelName="
        + name
        + ", modelPath="
        + modelPath
        + ", sensorTypeID="
        + sensorTypeID;
  }
}
