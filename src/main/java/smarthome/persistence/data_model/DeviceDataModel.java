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
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import smarthome.domain.device.Device;
import smarthome.utils.Validator;


/**
 * The {@code DeviceDataModel} class represents the persistence model for a device in the database.
 * This class maps to the DEVICE table and defines the structure and relationships of the device
 * data.
 *
 * @Entity Indicates that this class is an entity to be managed by the JPA.
 * @Table Specifies the name of the table in the database for this entity.
 */
@Entity
@Table(name = "DEVICE")
public class DeviceDataModel {

  @Id
  private String deviceID;
  private String roomID;
  private String deviceName;
  private String deviceTypeID;
  private boolean deviceStatus;
  @Version
  private long version;

  /**
   * Default constructor for JPA.
   */
  public DeviceDataModel() {
  }

  /**
   * Constructs a new DeviceDataModel instance from a domain {@link Device} object.
   *
   * @param device The domain Device object to convert to a data model.
   * @throws IllegalArgumentException if the device parameter is null.
   */
  public DeviceDataModel(Device device) {
    Validator.validateNotNull(device, "Device");
    this.deviceID = device.getID().getID();
    this.roomID = device.getRoomID().getID();
    this.deviceName = device.getName().getName();
    this.deviceTypeID = device.getDeviceTypeID().getID();
    this.deviceStatus = device.getDeviceStatus().getStatus();
  }


  /**
   * Returns the device ID.
   *
   * @return the device ID.
   */
  public String getDeviceID() {
    return deviceID;
  }

  /**
   * Returns the room ID associated with the device.
   *
   * @return the room ID.
   */
  public String getRoomID() {
    return roomID;
  }

  /**
   * Returns the name of the device.
   *
   * @return the device name.
   */
  public String getDeviceName() {
    return deviceName;
  }

  /**
   * Returns the device type ID.
   *
   * @return the device type ID.
   */
  public String getDeviceTypeID() {
    return deviceTypeID;
  }

  /**
   * Returns the status of the device.
   *
   * @return true if the device is active, false otherwise.
   */
  public boolean getDeviceStatus() {
    return deviceStatus;
  }

  /**
   * Updates the device data model from the domain.
   */
  public boolean updateFromDomain(Device device) {
    this.deviceID = device.getID().getID();
    this.roomID = device.getRoomID().getID();
    this.deviceName = device.getName().getName();
    this.deviceTypeID = device.getDeviceTypeID().getID();
    this.deviceStatus = device.getDeviceStatus().getStatus();
    return true;
  }
}

