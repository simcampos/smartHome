/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator_model;

import org.springframework.stereotype.Component;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;

@Component
public class ActuatorModelFactoryImpl implements IActuatorModelFactory {

  @Override
  public ActuatorModel createActuatorModel(ModelPath modelPath, ActuatorModelName actuatorModelName,
      ActuatorTypeID typeID) {
    return new ActuatorModel(modelPath, actuatorModelName, typeID);
  }


}
