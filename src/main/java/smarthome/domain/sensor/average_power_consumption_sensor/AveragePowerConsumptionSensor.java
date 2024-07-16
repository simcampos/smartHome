/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.average_power_consumption_sensor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.Validator;
import smarthome.utils.visitor_pattern.ISensorVisitor;

@Getter
public class AveragePowerConsumptionSensor implements ISensor {

  /**
   * This class represents a PowerConsumptionSensor in the Smart Home System. It includes details
   * about the sensor's identification, type, and power consumption values. It implements the
   * ISensor interface with SensorID as its identifier.
   */

  private final HashMap<LocalDateTime, Double> powerConsumptions;
  @Getter(AccessLevel.NONE)
  private AveragePowerConsumptionSensorValue averagePowerConsumptionSensorValue;
  private final SensorTypeID sensorTypeID;
  @Getter(AccessLevel.NONE)
  private SensorID sensorID;
  private final SensorName sensorName;
  private final DeviceID deviceID;
  private final ModelPath modelPath;

  /**
   * @param deviceID     The device ID.
   * @param modelPath    The model path.
   * @param sensorTypeID The sensor type ID.
   * @param sensorName   The sensor name.
   */
  public AveragePowerConsumptionSensor(
      DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName)
      throws IllegalArgumentException {
    Validator.validateNotNull(deviceID, "DeviceID");
    validateSensorTypeID(sensorTypeID);
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(sensorName, "SensorName");

    generateSensorID();

    this.deviceID = deviceID;
    this.sensorTypeID = sensorTypeID;
    this.modelPath = modelPath;
    this.sensorName = sensorName;

    averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(0);
    powerConsumptions = new HashMap<>();
  }

  /**
   * @param deviceID     The device ID.
   * @param modelPath    The model path.
   * @param sensorTypeID The sensor type ID.
   * @param sensorName   The sensor name.
   */
  public AveragePowerConsumptionSensor(
      DeviceID deviceID, ModelPath modelPath, SensorTypeID sensorTypeID, SensorName sensorName,
      SensorID sensorID)
      throws IllegalArgumentException {
    Validator.validateNotNull(deviceID, "DeviceID");
    Validator.validateNotNull(modelPath, "ModelPath");
    Validator.validateNotNull(sensorName, "SensorName");
    Validator.validateNotNull(sensorID, "SensorID");
    validateSensorTypeID(sensorTypeID);

    averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(0);
    powerConsumptions = new HashMap<>();

    this.sensorID = sensorID;
    this.deviceID = deviceID;
    this.sensorTypeID = sensorTypeID;
    this.modelPath = modelPath;
    this.sensorName = sensorName;
  }

  private void validateSensorTypeID(SensorTypeID sensorTypeID) {
    Validator.validateNotNull(sensorTypeID, "SensorTypeID");

    if (!sensorTypeID.getID().equals("AveragePowerConsumption")) {
      throw new IllegalArgumentException("SensorTypeID must be 'AveragePowerConsumption'.");
    }
  }

  private void generateSensorID() {
    sensorID = new SensorID(UUID.randomUUID().toString());
  }

  /**
   * Sets the value of the PowerConsumptionSensor.
   *
   * @param readTime the time of the reading.
   * @param reading  the reading to be set.
   * @return a new HashMap with the power consumptions.
   * @throws IllegalArgumentException if there is already a reading for this time.
   */
  protected double addReading(LocalDateTime readTime, double reading) {
    if (powerConsumptions.containsKey(readTime)) {
      throw new IllegalArgumentException("There is already a reading for this time");
    }

    this.powerConsumptions.put(readTime, reading);
    return reading;

  }

  /**
   * Gets the average value of the PowerConsumptionSensor.
   *
   * @param initialTime the initial time of the range.
   * @param finalTime   the final time of the range.
   * @return the average value of the PowerConsumptionSensor.
   * @throws IllegalArgumentException if the initial time is after the final time.
   * @throws IllegalArgumentException if the initial time is equal to the final time.
   */
  private double getAverageValue(LocalDateTime initialTime, LocalDateTime finalTime) {
    if (initialTime.isAfter(finalTime)) {
      throw new IllegalArgumentException("Initial time must be before final time");
    }
    // Calculate the average of the filtered power consumptions
    double average =
        filterPowerConsumptionsByTime(initialTime, finalTime).values().stream()
            .mapToDouble(Double::doubleValue)
            .average()
            .orElse(0);
    return average;
  }

  /**
   * Filters the power consumptions of the PowerConsumptionSensor by time.
   *
   * @param initialTime the initial time of the range.
   * @param finalTime   the final time of the range.
   * @return a new HashMap with the filtered power consumptions.
   */
  private Map<LocalDateTime, Double> filterPowerConsumptionsByTime(
      LocalDateTime initialTime, LocalDateTime finalTime) {
    // Filter the powerConsumptions map to only include entries within the specified time range
    Map<LocalDateTime, Double> filteredPowerConsumptions =
        powerConsumptions.entrySet().stream()
            .filter(
                entry ->
                    !entry.getKey().isBefore(initialTime) && !entry.getKey().isAfter(finalTime))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    return filteredPowerConsumptions;
  }

  @Override
  public SensorID getID() {
    return sensorID;
  }

  /**
   * Checks if this PowerConsumptionSensor instance is equal to another object. Equality is based
   * solely on the unique identifier of the sensor (_sensorID). This method overrides the
   * {@link Object#equals(Object)} method.
   *
   * @param o the object to be compared with this PowerConsumptionSensor instance for equality
   * @return true if the specified object is a PowerConsumptionSensor and has the same _sensorID as
   * this sensor; false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof AveragePowerConsumptionSensor averagePowerConsumptionSensor) {
      return sensorID.equals(averagePowerConsumptionSensor.sensorID);
    }
    return false;
  }

  /**
   * Overrides the hashCode method to return the hash code of the _sensorID. To keep the contract
   * with the equals method, this method must be overridden.
   */
  @Override
  public int hashCode() {
    return sensorID.hashCode();
  }

  /**
   * Returns a string representation of this PowerConsumptionSensor instance. The string includes
   * the class name, along * with the _sensorID, _sensorName, _deviceID, _modelPath, and
   * _sensorTypeID properties. This method overrides the {@link Object#toString()} method.
   */
  public String toString() {
    return "PowerConsumptionSensor:"
        + " sensorID= "
        + sensorID
        + ",  sensorName="
        + sensorName
        + ",  deviceID="
        + deviceID
        + ", modelPath="
        + modelPath
        + ", sensorTypeID="
        + sensorTypeID;
  }

  /**
   * Gets the value of the PowerConsumptionSensor with zero.
   *
   * @return the value of the PowerConsumptionSensor.
   */
  public AveragePowerConsumptionSensorValue getValue() {
    return this.averagePowerConsumptionSensorValue;
  }

  /**
   * Gets the value of the PowerConsumptionSensor for a given period.
   *
   * @param initialTime the initial time of the range.
   * @param finalTime   the final time of the range.
   * @return the value of the PowerConsumptionSensor.
   */
  public AveragePowerConsumptionSensorValue getValue(LocalDateTime initialTime,
      LocalDateTime finalTime) {
    double averageValue = getAverageValue(initialTime, finalTime);
    averagePowerConsumptionSensorValue = new AveragePowerConsumptionSensorValue(averageValue);
    return averagePowerConsumptionSensorValue;
  }

  /**
   * Accept method for the visitor pattern.
   */
  public String accept(ISensorVisitor visitor) {
    visitor.visitAveragePowerConsumptionSensor(this);
    return this.toString();
  }
}
