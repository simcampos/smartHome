/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor_model;

import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorModelName;
import smarthome.domain.value_object.SensorTypeID;

public interface ISensorModelFactory {

  SensorModel createSensorModel(SensorModelName sensorModelName, ModelPath modelPath,
      SensorTypeID sensorTypeID);
}
