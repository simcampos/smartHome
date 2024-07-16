/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.device;

import java.util.UUID;
import smarthome.ddd.IAggregateRoot;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;
import smarthome.utils.Validator;

public class Device implements IAggregateRoot<DeviceID> {

  private final RoomID roomID;
  private final DeviceName name;
  private final DeviceTypeID deviceTypeID;
  private DeviceID id;
  private DeviceStatus status;


  /**
   * Constructs a new Device instance with the specified room ID, device name, and device state.
   *
   * @param roomID       The room ID where the device is located. Must not be null.
   * @param name   The name of the device. Must not be null.
   */
  Device(RoomID roomID, DeviceName name,
      DeviceTypeID deviceTypeID) {
    Validator.validateNotNull(roomID, "RoomID");
    Validator.validateNotNull(name, "DeviceName");
    Validator.validateNotNull(deviceTypeID, "DeviceTypeID");
    this.roomID = roomID;
    this.name = name;
    setDeviceStatus();
    this.deviceTypeID = deviceTypeID;
    generateDeviceID();
  }

  /**
   * Constructs a new Device instance with the specified device ID, room ID, device name, device
   * state, and device type ID.
   *
   * @param deviceID     The device ID. Must not be null.
   * @param roomID       The room ID where the device is located. Must not be null.
   * @param name   The name of the device. Must not be null.
   * @param deviceStatus The state of the device. Must not be null.
   * @param deviceTypeID The device type ID. Must not be null.
   */
  Device(DeviceID deviceID, RoomID roomID, DeviceName name, DeviceStatus deviceStatus,
      DeviceTypeID deviceTypeID) {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(roomID, "RoomID");
    Validator.validateNotNull(name, "DeviceName");
    Validator.validateNotNull(deviceStatus, "DeviceStatus");
    Validator.validateNotNull(deviceTypeID, "DeviceTypeID");

    this.id = deviceID;
    this.roomID = roomID;
    this.name = name;
    this.status = deviceStatus;
    this.deviceTypeID = deviceTypeID;
  }

  /**
   * Device Status must be set to true by default
   */
  private void setDeviceStatus() {
    this.status = new DeviceStatus(true);
  }

  /**
   * Generates a new DeviceID object.
   */
  private void generateDeviceID() {
    id = new DeviceID(UUID.randomUUID().toString());
  }


  /**
   * Method to return deviceID
   *
   * @return _deviceID
   */
  public DeviceID getID() {
    return id;
  }

  /**
   * Method to return roomID
   *
   * @return _roomID
   */
  public RoomID getRoomID() {
    return roomID;
  }


  /**
   * Method to return deviceName
   *
   * @return _deviceName
   */
  public DeviceName getName() {
    return name;
  }

  /**
   * Method to return deviceStatus
   *
   * @return _deviceStatus
   */
  public DeviceStatus getDeviceStatus() {
    return status;
  }

  /**
   * Method to return deviceTypeID
   *
   * @return _deviceTypeID
   */
  public DeviceTypeID getDeviceTypeID() {
    return deviceTypeID;
  }

  /**
   * Compares this instance with another instance.
   *
   * @param object is the object to be compared.
   * @return true if the instances are equal, false otherwise.
   */
  @Override
  public boolean equals(Object object) {
    if (object instanceof Device device) {
      return id.equals(device.getID());
    }
    return false;
  }

  /**
   * Method to return the values of the object in a string.
   *
   * @return the values of the object in a string.
   */
  @Override
  public String toString() {
    return "Device:" +
        "roomID=" + roomID +
        ", deviceID=" + id +
        ", deviceName=" + name +
        ", deviceStatus=" + status;
  }

  /**
   * Method to deactivate the device
   *
   * @return the status of the device
   */
  public DeviceStatus deactivateDevice() {
    status = new DeviceStatus(false);
    return status;
  }

  /**
   * Generates a hash code for the Device instance.
   *
   * @return The hash code.
   */
  @Override
  public int hashCode() {
    return this.id.hashCode();
  }

}
