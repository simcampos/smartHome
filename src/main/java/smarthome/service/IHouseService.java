/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.service;

import java.util.Optional;
import smarthome.ddd.IService;
import smarthome.domain.house.House;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;

public interface IHouseService extends IService {

  /**
   * Adds a new house to the repository.
   *
   * @param address the address of the house.
   * @param gps     the GPS coordinates of the house.
   * @return the house that was added.
   */
  House addHouse(Address address, GPS gps);

  House addHouse(HouseID houseID, Address address, GPS gps);

  Optional<House> getHouse();
}
