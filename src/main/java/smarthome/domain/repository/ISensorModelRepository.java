/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.repository;

import java.util.List;
import smarthome.ddd.IRepository;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorTypeID;

public interface ISensorModelRepository extends IRepository<ModelPath, SensorModel> {

  List<SensorModel> findBySensorTypeId(SensorTypeID sensorTypeID);
}
