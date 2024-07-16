/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.repository;

import java.util.Optional;
import smarthome.ddd.IRepository;
import smarthome.domain.house.House;
import smarthome.domain.value_object.HouseID;

public interface IHouseRepository extends IRepository<HouseID, House> {

  boolean thereShouldBeOnlyOneHouse();

  Optional<House> getTheHouse();

}
