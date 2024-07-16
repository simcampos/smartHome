/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.house;

import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;

/**
 * Interface defining a factory for creating {@link House} instances. Provides a method to create a
 * house with specific address, zip code, and GPS coordinates.
 */
public interface IHouseFactory {

  /**
   * Creates and returns a new {@link House} instance with the provided address, zip code, and GPS
   * location.
   *
   * @param address the address of the new house
   * @param gps     the GPS coordinates of the new house
   * @return a newly created House instance
   */
  House createHouse(Address address, GPS gps);

  /**
   * Creates and returns a new {@link House} instance with the provided address, zip code, and GPS
   * location.
   *
   * @param houseID the house ID of the new house
   * @param address the address of the new house
   * @param gps     the GPS coordinates of the new house
   * @return a newly created House instance
   */
  House createHouse(HouseID houseID, Address address, GPS gps);
}

