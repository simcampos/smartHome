/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import smarthome.domain.house.House;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.utils.Validator;

@Service
public class HouseServiceImpl implements IHouseService {

  final IHouseFactory houseFactory;

  final IHouseRepository houseRepository;

  /**
   * Constructor for the HouseService class.
   *
   * @param houseFactory    the factory for creating House instances
   * @param houseRepository the repository for storing House instances
   */
  public HouseServiceImpl(IHouseFactory houseFactory, IHouseRepository houseRepository) {
    Validator.validateNotNull(houseFactory, "House factory");
    Validator.validateNotNull(houseRepository, "House repository");
    this.houseFactory = houseFactory;
    this.houseRepository = houseRepository;
  }


  /**
   * Adds a new House to the repository.
   *
   * @param address the address of the house
   * @param gps     the GPS coordinates of the house
   * @return the newly created House
   */
  @Override
  public House addHouse(Address address, GPS gps) {
    House house = houseFactory.createHouse(address, gps);
    houseRepository.save(house);
    return house;
  }

  @Override
  public House addHouse(HouseID houseID, Address address, GPS gps) {
    House house = houseFactory.createHouse(houseID, address, gps);
    houseRepository.save(house);
    return house;
  }

  @Override
  public Optional<House> getHouse() {
    return houseRepository.getTheHouse();
  }
}

