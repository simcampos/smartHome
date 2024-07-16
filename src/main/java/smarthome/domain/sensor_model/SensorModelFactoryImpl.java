/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor_model;

import org.springframework.stereotype.Component;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorModelName;
import smarthome.domain.value_object.SensorTypeID;

/**
 * Implementation of the {@link ISensorModelFactory} interface, responsible for creating
 * {@link SensorModel} instances. This factory encapsulates the logic for sensor model creation,
 * ensuring that all necessary validations and initializations are performed before a
 * {@link SensorModel} object is returned to the caller.
 */
@Component
public class SensorModelFactoryImpl implements ISensorModelFactory {

  /**
   * Creates a new {@link SensorModel} instance using the provided sensor model name, model path,
   * and sensor type ID. This method ensures that a {@link SensorModel} object is instantiated with
   * valid and non-null parameters, leveraging the SensorModel constructor for validation and
   * initialization.
   *
   * @param sensorModelName the name of the sensor model, must not be null
   * @param modelPath       the path of the sensor model, must not be null
   * @param sensorTypeID    the type ID of the sensor model, must not be null
   * @return a fully initialized {@link SensorModel} instance
   * @throws IllegalArgumentException if any of the parameters are null, handled by the
   *                                  {@link SensorModel} constructor
   */
  @Override
  public SensorModel createSensorModel(SensorModelName sensorModelName, ModelPath modelPath,
      SensorTypeID sensorTypeID) {
    return new SensorModel(sensorModelName, modelPath, sensorTypeID);
  }
}
