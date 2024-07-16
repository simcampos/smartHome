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
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;

public interface IActuatorModelRepository extends IRepository<ModelPath, ActuatorModel> {

  List<ActuatorModel> findBy_actuatorTypeID(ActuatorTypeID actuatorModelID);
}
