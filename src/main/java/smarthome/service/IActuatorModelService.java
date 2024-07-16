/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.service;

import java.util.List;
import java.util.Optional;
import smarthome.ddd.IService;
import smarthome.domain.actuator_model.ActuatorModel;
import smarthome.domain.value_object.ActuatorModelName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.ModelPath;

public interface IActuatorModelService extends IService {

  /**
   * Get all actuator models in the repository.
   *
   * @return a list of all actuator models.
   */
  List<ActuatorModel> getAllActuatorModels();

  /**
   * Get an actuator model by its model path.
   *
   * @param modelPath the path of the actuator model.
   * @return the actuator model with the provided model path.
   */
  Optional<ActuatorModel> getActuatorModel(ModelPath modelPath);

  /**
   * Get all actuator models of a specific actuator type.
   *
   * @param actuatorTypeID the ID of the actuator type.
   * @return a list of all actuator models of the specified actuator type.
   */
  List<ActuatorModel> getActuatorModelsByActuatorTypeId(ActuatorTypeID actuatorTypeID);

  ActuatorModel addActuatorModel(ModelPath modelPath, ActuatorModelName modelName,
      ActuatorTypeID sensorTypeID1);
}
