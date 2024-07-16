/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

/**
 * Represents a geographical location using GPS coordinates. This class ensures that latitude and
 * longitude values are within valid ranges and that they are precise up to five decimal places.
 */
package smarthome.domain.value_object;

import smarthome.ddd.IValueObject;

public class GPS implements IValueObject {

  private final double latitude;

  private final double longitude;

  /**
   * Constructs a GPS object with specified latitude and longitude after validating them.
   *
   * @param latitude  the latitude value to set, must be between -90 and 90 degrees.
   * @param longitude the longitude value to set, must be between -180 and 180 degrees.
   * @throws IllegalArgumentException if either latitude or longitude is invalid.
   */
  public GPS(double latitude, double longitude) {
    validationLatitude(latitude);
    validationLongitude(longitude);
    this.latitude = latitude;
    this.longitude = longitude;
  }

  /**
   * Validates and sets the latitude value after checking its range and precision.
   *
   * @param latitude the latitude value to validate and set.
   * @throws IllegalArgumentException if the latitude is out of range or too precise.
   */
  private void validationLatitude(double latitude) {
    if (!validLatitudeForFiveDecimalNumber(latitude) || latitude < -90 || latitude > 90) {
      throw new IllegalArgumentException("Please enter a valid latitude.");
    }
  }

  /**
   * Validates and sets the longitude value after checking its range and precision.
   *
   * @param longitude the longitude value to validate and set.
   * @throws IllegalArgumentException if the longitude is out of range or too precise.
   */
  private void validationLongitude(double longitude) {
    if (!validLongitudeForFiveDecimalNumber(longitude) || longitude < -180 || longitude > 180) {
      throw new IllegalArgumentException("Please enter a valid longitude.");
    }
  }

  /**
   * Checks if the given latitude has five or fewer decimal places.
   *
   * @param latitude the latitude value to check.
   * @return true if the latitude has five or fewer decimal places, false otherwise.
   */
  private boolean validLatitudeForFiveDecimalNumber(double latitude) {
    String latitudeStr = Double.toString(latitude);
    int decimalPointIndex = latitudeStr.indexOf('.');
    int digitsAfterDecimal = latitudeStr.length() - decimalPointIndex - 1;
    return digitsAfterDecimal <= 5;
  }

  /**
   * Checks if the given longitude has five or fewer decimal places.
   *
   * @param longitude the longitude value to check.
   * @return true if the longitude has five or fewer decimal places, false otherwise.
   */
  private boolean validLongitudeForFiveDecimalNumber(double longitude) {
    String longitudeStr = Double.toString(longitude);
    int decimalPointIndex = longitudeStr.indexOf('.');
    int digitsAfterDecimal = longitudeStr.length() - decimalPointIndex - 1;
    return digitsAfterDecimal <= 5;
  }

  /**
   * Checks if this GPS object is equal to another object.
   *
   * @param object the object to compare with.
   * @return true if both objects are GPS instances with the same latitude and longitude.
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (object instanceof GPS gps) {
      return this.latitude == gps.latitude && this.longitude == gps.longitude;
    }
    return false;
  }

  /**
   * Returns the hash code of this GPS object.
   */
  @Override
  public int hashCode() {
    return Double.hashCode(this.latitude) + Double.hashCode(this.longitude);
  }

  /**
   * Gets the latitude value of this GPS location.
   *
   * @return the latitude value.
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * Gets the longitude value of this GPS location.
   *
   * @return the longitude value.
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * Returns a string representation of the GPS location.
   *
   * @return a string in the format "GPS{latitude=VALUE, longitude=VALUE}".
   */
  @Override
  public String toString() {
    return
        "Latitude= " + latitude +
        ", Longitude= " + longitude;
  }
}
