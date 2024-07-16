/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.mem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import smarthome.domain.house.House;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.value_object.HouseID;
import smarthome.utils.Validator;


public class HouseRepository implements IHouseRepository {

  private final Map<HouseID, House> DATA = new LinkedHashMap<>();

  /**
   * Saves a house in the repository.
   *
   * @param house The house to be saved.
   * @return The saved house.
   * @throws IllegalArgumentException if the house is null or already exists in the repository.
   */
  @Override
  public House save(House house) {
    Validator.validateNotNull(house, "House");

    if (thereShouldBeOnlyOneHouse()) {
      DATA.put(house.getID(), house);
      return house;
    } else {
      throw new IllegalArgumentException("The system supports only one house.");
    }
  }

  /**
   * Finds all houses in the repository.
   *
   * @return A list containing all houses in the repository.
   */
  @Override
  public List<House> findAll() {
    return new ArrayList<>(DATA.values());
  }

  /**
   * Finds a house by its identity.
   *
   * @param houseID The identity of the house to be found.
   * @return An Optional containing the house if found, or empty otherwise.
   */
  @Override
  public Optional<House> ofIdentity(HouseID houseID) {
    return Optional.ofNullable(DATA.get(houseID));
  }

  /**
   * Checks if a house with the specified identity exists in the repository.
   *
   * @param houseID The identity of the house to check for existence.
   * @return true if the house exists in the repository, false otherwise.
   */
  @Override
  public boolean containsOfIdentity(HouseID houseID) {
    return DATA.containsKey(houseID);
  }

  @Override
  public boolean thereShouldBeOnlyOneHouse() {
    return findAll().isEmpty();
  }

  @Override
  public Optional<House> getTheHouse() {
    List<House> listHouse = findAll();
    if (listHouse.size() == 1) {
      House house = listHouse.get(0);
      return Optional.of(house);
    } else {
      return Optional.empty();
    }
  }
}