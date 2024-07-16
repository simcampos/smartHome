/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.data_model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.persistence.Table;
import smarthome.domain.house.House;
import smarthome.utils.Validator;

/**
 * Class to represent the house data model
 */
@Entity
@Table(name = "House")
public class HouseDataModel {

  @Id
  private String houseID;
  private double latitude;
  private double longitude;
  private String street;
  private String doorNumber;
  private String countryCode;
  private String postalCode;
  @Version
  private long version;

  /**
   * Empty constructor so that JPA can instantiate the class
   */
  public HouseDataModel() {
  }

  /**
   * Constructor to create a house data model This constructor will validate the house and set the
   * house data model
   *
   * @param house
   */
  public HouseDataModel(House house) {
    Validator.validateNotNull(house, "House");
    this.houseID = house.getID().getID();
    this.latitude = house.getGps().getLatitude();
    this.longitude = house.getGps().getLongitude();
    this.street = house.getAddress().getStreet();
    this.doorNumber = house.getAddress().getDoorNumber();
    this.countryCode = house.getAddress().getCountryCode();
    this.postalCode = house.getAddress().getPostalCode().getCode();
  }

  /**
   * Method to return the house ID.
   *
   * @return
   */
  public String getHouseID() {
    return this.houseID;
  }

  /**
   * Method to return the latitude.
   *
   * @return
   */
  public double getLatitude() {
    return this.latitude;
  }

  /**
   * Method to return the longitude.
   *
   * @return
   */
  public double getLongitude() {
    return this.longitude;
  }

  /**
   * Method to return the street.
   *
   * @return
   */
  public String getStreet() {
    return this.street;
  }

  /**
   * Method to return the door number.
   *
   * @return
   */
  public String getDoorNumber() {
    return this.doorNumber;
  }

  /**
   * Method to return the country code.
   *
   * @return
   */
  public String getCountryCode() {
    return this.countryCode;
  }

  /**
   * Method to return the postal code.
   *
   * @return
   */
  public String getPostalCode() {
    return this.postalCode;
  }

  @Override
  public String toString() {
    return "HouseDataModel: " +
        "houseID='" + houseID + '\'' +
        ", latitude=" + latitude +
        ", longitude=" + longitude +
        ", street='" + street + '\'' +
        ", doorNumber='" + doorNumber + '\'' +
        ", countryCode='" + countryCode + '\'' +
        ", postalCode='" + postalCode + '.';
  }

}
