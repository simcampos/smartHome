/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.switch_sensor;

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
public class SwitchSensor implements ISensor {

  private final ModelPath modelPath;
  private final SensorName sensorName;
  @Getter(AccessLevel.NONE)
  private SensorID sensorID;
  private final SensorTypeID sensorTypeID;
  @Getter(AccessLevel.NONE)
  private SwitchSensorValue switchSensorValue;
  private final DeviceID deviceID;

  /**
   * @param deviceID     The device ID.
   * @param modelPath    The model path.
   * @param sensorTypeID The sensor type ID.
   * @param sensorName   The sensor name.
   */
  public SwitchSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID,
      SensorName sensorName) {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(modelPath, "ModelPath");
    validateSensorTypeID(sensorTypeID);
    Validator.validateNotNull(sensorName, "SensorName");
    generateSwitchSensorID();

    switchSensorValue = new SwitchSensorValue(false);
    this.modelPath = modelPath;
    this.sensorName = sensorName;
    this.sensorTypeID = sensorTypeID;
    this.deviceID = deviceID;

  }

  /**
   * @param deviceID     The device ID.
   * @param modelPath    The model path.
   * @param sensorTypeID The sensor type ID.
   * @param sensorName   The sensor name.
   * @param sensorID     The sensor ID.
   */
  public SwitchSensor(DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID,
      SensorName sensorName, SensorID sensorID) {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(modelPath, "ModelPath");
    validateSensorTypeID(sensorTypeID);
    Validator.validateNotNull(sensorName, "SensorName");
    Validator.validateNotNull(sensorID, "SensorID");

    this.modelPath = modelPath;
    this.sensorName = sensorName;
    this.sensorTypeID = sensorTypeID;
    this.deviceID = deviceID;
    this.sensorID = sensorID;
  }


  /**
   * generates a new HumidityID
   */
  private void generateSwitchSensorID() {
    this.sensorID = new SensorID(UUID.randomUUID().toString());
  }

  /**
   * Validates the sensor type ID.
   *
   * @param sensorTypeID The sensor type ID.
   */
  private void validateSensorTypeID(SensorTypeID sensorTypeID) {
    Validator.validateNotNull(sensorTypeID, "SensorTypeID");

    if (!Objects.equals(sensorTypeID.getID(), "Switch")) {
      throw new IllegalArgumentException("SensorTypeID must be of type 'Switch'");
    }
  }

  /**
   * Returns the sensor ID.
   *
   * @return The sensor ID.
   */
  @Override
  public SensorID getID() {
    return this.sensorID;
  }

  /**
   * Returns the sensor value.
   *
   * @return The sensor value.
   */
  @Override
  public SwitchSensorValue getValue() {
    boolean randomBoolean = ValueSimulator.generateRandomBoolean();
    this.switchSensorValue = new SwitchSensorValue(randomBoolean);

    return this.switchSensorValue;
  }

  /**
   * Compares this instance with another instance.
   *
   * @param o is the object to be compared.
   * @return true if the instances are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof SwitchSensor switchSensor) {
      return this.sensorID.equals(switchSensor.getID());
    }
    return false;
  }

  /**
   * Generates a hash code for the sensor.
   *
   * @return The hash code.
   */
  @Override
  public int hashCode() {
    return this.sensorID.hashCode();
  }

  @Override
  public String toString() {
    return "SwitchSensor: DeviceID= " + deviceID.getID() + " ModelPath= " + modelPath.getID()
        + " SensorTypeID= " + sensorTypeID.getID() + " SensorName= " + sensorName.getSensorName()
        + " SensorID= " + sensorID.getID();
  }

  public String accept(ISensorVisitor visitor) {
    visitor.visitSwitchSensor(this);
    return this.toString();
  }
}
