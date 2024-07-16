/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.spring_data.actuator_model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.persistence.data_model.ActuatorModelDataModel;

public interface IRepositoryActuatorModelSpringData extends
    JpaRepository<ActuatorModelDataModel, String> {


  List<ActuatorModelDataModel> findByActuatorTypeID(String id);
}
