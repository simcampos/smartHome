/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.temperature_sensor;

import java.util.Objects;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.Validator;
import smarthome.utils.ValueSimulator;
import smarthome.utils.visitor_pattern.ISensorVisitor;

@Getter
public class TemperatureSensor implements ISensor {

  private final ModelPath modelPath;
  private final SensorName sensorName;
  @Getter(AccessLevel.NONE)
  private SensorID sensorID;
  private final SensorTypeID sensorTypeID;
  @Getter(AccessLevel.NONE)
  private TemperatureSensorValue temperatureSensorValue;
  private final DeviceID deviceID;

  /**
   * Constructor of the class.
   *
   * @param deviceID     The device ID.
   * @param modelPath    The model path.
   * @param sensorName   The sensor name.
   * @param sensorTypeID The sensor type ID.
   */
  public TemperatureSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID,
      SensorName sensorName) {
    Validator.validateNotNull(modelPath, "ModelPath");
    this.modelPath = modelPath;
    Validator.validateNotNull(sensorName, "SensorName");
    this.sensorName = sensorName;
    validateSensorTypeID(sensorTypeID);
    this.sensorTypeID = sensorTypeID;
    Validator.validateNotNull(deviceID, "DeviceID");
    this.deviceID = deviceID;

    generateTemperatureID();
  }

  /**
   * Constructor of the class.
   *
   * @param deviceID     The device ID.
   * @param modelPath    The model path.
   * @param sensorName   The sensor name.
   * @param sensorTypeID The sensor type ID.
   * @param sensorID     The sensor ID.
   */
  public TemperatureSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID,
      SensorName sensorName, SensorID sensorID) {
    Validator.validateNotNull(modelPath, "ModelPath");
    this.modelPath = modelPath;
    Validator.validateNotNull(sensorName, "SensorName");
    this.sensorName = sensorName;
    validateSensorTypeID(sensorTypeID);
    this.sensorTypeID = sensorTypeID;
    Validator.validateNotNull(deviceID, "DeviceID");
    this.deviceID = deviceID;
    Validator.validateNotNull(sensorID, "SensorID");
    this.sensorID = sensorID;
  }

  /**
   * Generates a new TemperatureID.
   */
  private void generateTemperatureID() {
    this.sensorID = new SensorID(UUID.randomUUID().toString());
  }


  /**
   * Validates the sensor type ID.
   *
   * @param sensorTypeID The sensor type ID.
   */
  private void validateSensorTypeID(SensorTypeID sensorTypeID) {
    Validator.validateNotNull(sensorTypeID, "SensorTypeID");

    if (!Objects.equals(sensorTypeID.getID(), "Temperature")) {
      throw new IllegalArgumentException("SensorTypeID must be of type 'Temperature'");
    }
  }

  /**
   * Returns the value of the sensor.
   *
   * @return The value of the sensor.
   */
  @Override
  public TemperatureSensorValue getValue() {
    // Generate a random temperature as a simulation of hardware behavior
    double temperatureReading = ValueSimulator.generateRandomValue(-50.0, 50.0);
    this.temperatureSensorValue = new TemperatureSensorValue(temperatureReading);

    return temperatureSensorValue;
  }

  @Override
  public SensorID getID() {
    return sensorID;
  }

  /**
   * Checks if the object is equal to the current instance.
   *
   * @param o is the object to be compared.
   * @return true if the object is equal to the current instance, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof TemperatureSensor temperatureSensor){
      return this.sensorID.equals(temperatureSensor.sensorID);
    }
    return false;
  }

  /**
   * Returns the hash code of the sensor ID.
   *
   * @return The hash code of the sensor ID.
   */
  @Override
  public int hashCode() {
    return sensorID.hashCode();
  }

  /**
   * Returns the string representation of the sensor.
   *
   * @return The string representation of the sensor.
   */
  @Override
  public String toString() {
    return "TemperatureSensor:" +
        " modelPath=" + modelPath +
        ", sensorName=" + sensorName +
        ", sensorID=" + sensorID +
        ", sensorTypeID=" + sensorTypeID +
        ", temperatureValue=" + temperatureSensorValue +
        ", deviceID=" + deviceID;
  }

  public String accept(ISensorVisitor visitor) {
    visitor.visitTemperatureSensor(this);
    return this.toString();
  }
}
