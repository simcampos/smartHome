/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.house;

import java.util.UUID;
import smarthome.ddd.IAggregateRoot;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.utils.Validator;

/**
 * Represents a house in the Smart Home domain. This class includes details about the house's
 * identification, location, and geographical positioning. It implements the AggregateRoot interface
 * with HouseID as its identifier.
 */
public class House implements IAggregateRoot<HouseID> {

  private HouseID id;
  private final Address address;
  private final GPS gps;

  /**
   * Constructs a new House instance with the specified address, zip code, and GPS coordinates.
   * Validates the provided address, zip code, and GPS coordinates to ensure they are not null.
   *
   * @param address The physical address of the house. Must not be null.
   * @param gps     The GPS coordinates of the house. Must not be null.
   * @throws IllegalArgumentException if any of the parameters are null.
   */
  House(Address address, GPS gps) {
    generateID();
    Validator.validateNotNull(address, "Address");
    Validator.validateNotNull(gps, "GPS");
    this.address = address;
    this.gps = gps;

  }

  House(HouseID houseID, Address address, GPS gps) {
    Validator.validateNotNull(houseID, "HouseID");
    Validator.validateNotNull(address, "Address");
    Validator.validateNotNull(gps, "GPS");

    this.id = houseID;
    this.address = address;
    this.gps = gps;
  }


  /**
   * Generates a unique identifier for the House instance.
   */
  private void generateID() {
    id = new HouseID(UUID.randomUUID().toString());
  }


  /**
   * Returns the unique identifier of the House instance.
   *
   * @return The HouseID that uniquely identifies the house.
   */
  @Override
  public HouseID getID() {
    return id;
  }

  /**
   * Returns the address of the house.
   *
   * @return The Address of the house.
   */
  public Address getAddress() {
    return address;
  }

  /**
   * Returns the GPS coordinates of the house.
   *
   * @return The GPS coordinates of the house.
   */
  public GPS getGps() {
    return gps;
  }

  /**
   * Checks if this House instance is equal to another object. Equality is based solely on the
   * unique identifier of the house (_houseID). This method overrides the
   * {@link Object#equals(Object)} method.
   *
   * @param o the object to be compared with this House instance for equality
   * @return true if the specified object is a House and has the same _id as this house; false
   * otherwise
   */
  public boolean equals(Object o) {
    if (o instanceof House houseObject) {
      return id.equals(houseObject.id);
    }
    return false;
  }

  /**
   * Returns a string representation of this House instance. The string includes the class name,
   * along with the _houseID, _address, _zipCode, and _gps properties. This method overrides the
   * {@link Object#toString()} method.
   *
   * @return a string representation of this House instance, containing values of its properties
   */
  public String toString() {
    return "House:" +
        "houseID=" + id +
        ", address=" + address +
        ", gps=" + gps;
  }

  /**
   * Returns a hash code value for the House instance. The hash code is based on the unique
   * identifier of the house (_houseID). This method overrides the {@link Object#hashCode()}
   * method.
   *
   * @return a hash code value for this House instance
   */
  public int hashCode() {
    return id.hashCode();
  }
}
