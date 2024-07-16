/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.average_power_consumption_sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import smarthome.ddd.IValueObject;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.visitor_pattern.ISensorVisitor;

class AveragePowerConsumptionAggregateSensorTest {

  /**
   * See if the constructor works.
   */
  @Test
  void shouldInstantiateAveragePowerConsumptionSensor() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    // Act
    AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(
        deviceID, modelPath, sensorTypeID, sensorName);
    // Assert
    Assertions.assertNotNull(averagePowerConsumptionSensor);

  }

  /**
   * tests if Exception is thrown for null Sensor type.
   */
  @Test
  void shouldThrowExceptionForNullSensorTypeOfPowerConsumptionSensor() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = null;

    // Act + Assert
    Exception e = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    Assertions.assertEquals("SensorTypeID is required", e.getMessage());
  }

  @Test
  void shouldThrowExceptionForNullSensorTypeOfPowerConsumptionSensorForSecondConstructor() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorIDValue = "sensorID";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = null;
    SensorID sensorID = new SensorID(sensorIDValue);

    // Act + Assert
    Exception e = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName,
            sensorID));

    // Assert
    Assertions.assertEquals("SensorTypeID is required", e.getMessage());
  }

  /**
   * tests if Exception is thrown when sensorType is different
   */
  @Test
  void shouldThrowExceptionForDifferentSensorTypeOfPowerConsumptionSensor() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "SunriseTime";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    // Act + Assert
    Exception e = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    Assertions.assertEquals("SensorTypeID must be 'AveragePowerConsumption'.", e.getMessage());
  }

  /**
   * tests if Exception is thrown for null DeviceID.
   */
  @Test
  void shouldThrowExceptionForNullDeviceIDOfPowerConsumptionSensor() {
    // Arrange
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = null;
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    // Act + Assert
    Exception e = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    Assertions.assertEquals("DeviceID is required", e.getMessage());
  }

  /**
   * tests if Exception is thrown for null ModelPath.
   */
  @Test
  void shouldThrowExceptionForNullModelPathOfPowerConsumptionSensor() {
    // Arrange
    String deviceIDValue = "deviceID";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = null;
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    // Act + Assert
    Exception e = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    Assertions.assertEquals("ModelPath is required", e.getMessage());
  }

  /**
   * tests if Exception is thrown for null SensorName.
   */
  @Test
  void shouldThrowExceptionForNullSensorNameOfPowerConsumptionSensor() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    // Act + Assert
    Exception e = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    Assertions.assertEquals("SensorName is required", e.getMessage());
  }

  /**
   * See if the getAverageValue method works.
   */
  @Test
  void shouldReturnAveragePowerConsumptionForAGivenPeriod() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    double value = 1250.0;

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    LocalDateTime initialTime = LocalDateTime.of(2024, 2, 29, 10, 10, 5);
    LocalDateTime finalTime = LocalDateTime.of(2024, 2, 29, 10, 10, 10);

    averagePowerConsumptionSensor.addReading(initialTime, 1000);
    averagePowerConsumptionSensor.addReading(finalTime, 1500);

    // Act
    IValueObject average = averagePowerConsumptionSensor.getValue(initialTime, finalTime);

    // Assert
    Assertions.assertEquals(value, Double.parseDouble(average.toString()), 0.01);
  }

  /**
   * See if the getAverageValue method works with another time.
   */
  @Test
  void shouldReturnAveragePowerConsumptionForAGivenPeriodOnADiferenteFormat() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    double value = 1500.0;

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    LocalDateTime initialTime = LocalDateTime.now().minusHours(2);
    LocalDateTime finalTime = LocalDateTime.now();

    averagePowerConsumptionSensor.addReading(initialTime, 1000);
    averagePowerConsumptionSensor.addReading(finalTime, 2000);

    // Act
    IValueObject average = averagePowerConsumptionSensor.getValue(initialTime, finalTime);

    // Assert
    Assertions.assertEquals(value, Double.parseDouble(average.toString()), 0.01);
  }

  /**
   * See if the getAverageValue method works with more values than the initial and final time.
   */
  @Test
  void shouldReturnAveragePowerConsumptionForAGivenPeriodWithThreeReadings() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    double value = 1500.0;

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    LocalDateTime initialTime = LocalDateTime.now().minusHours(2);
    LocalDateTime secondTime = LocalDateTime.now().minusHours(1);
    LocalDateTime finalTime = LocalDateTime.now();

    averagePowerConsumptionSensor.addReading(initialTime, 1000);
    averagePowerConsumptionSensor.addReading(secondTime, 1500);
    averagePowerConsumptionSensor.addReading(finalTime, 2000);

    // Act
    IValueObject average = averagePowerConsumptionSensor.getValue(initialTime, finalTime);

    // Assert
    Assertions.assertEquals(value, Double.parseDouble(average.toString()), 0.01);
  }

  /**
   * See if the getAverageValue method works with readings out of range of the initial and final
   * times.
   */
  @Test
  void shouldReturnAveragePowerConsumptionForAGivenPeriodWithExtraPeriodReadings()
      throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    double value = 1400.0;

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    LocalDateTime OtherTime1 = LocalDateTime.now().minusHours(3);
    LocalDateTime initialTime = LocalDateTime.now().minusHours(2);
    LocalDateTime OtherTime2 = LocalDateTime.now().minusHours(1);
    LocalDateTime finalTime = LocalDateTime.now();
    LocalDateTime OtherTime3 = LocalDateTime.now().plusHours(1);

    averagePowerConsumptionSensor.addReading(OtherTime1, 500);
    averagePowerConsumptionSensor.addReading(initialTime, 1000);
    averagePowerConsumptionSensor.addReading(OtherTime2, 1200);
    averagePowerConsumptionSensor.addReading(finalTime, 2000);
    averagePowerConsumptionSensor.addReading(OtherTime3, 400);

    // Act
    IValueObject average = averagePowerConsumptionSensor.getValue(initialTime, finalTime);

    // Assert
    Assertions.assertEquals(value, Double.parseDouble(average.toString()), 0.01);
  }

  /**
   * See if the getAverageValue method works with non-sequential readings.
   */
  @Test
  void shouldReturnAveragePowerConsumptionForAGivenPeriodWithNonSequentialReadings()
      throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    double value = 1133.33;

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    LocalDateTime initialTime = LocalDateTime.now();
    LocalDateTime finalTime = LocalDateTime.now().plusHours(2);
    LocalDateTime otherTime1 = LocalDateTime.now().minusHours(1);
    LocalDateTime otherTime2 = LocalDateTime.now().plusHours(3);
    LocalDateTime otherTime3 = LocalDateTime.now().plusHours(1);

    averagePowerConsumptionSensor.addReading(otherTime2, 500);
    averagePowerConsumptionSensor.addReading(initialTime, 1000);
    averagePowerConsumptionSensor.addReading(otherTime1, 1200);
    averagePowerConsumptionSensor.addReading(finalTime, 2000);
    averagePowerConsumptionSensor.addReading(otherTime3, 400);

    // Act
    IValueObject average = averagePowerConsumptionSensor.getValue(initialTime, finalTime);

    // Assert
    assertEquals(value, Double.parseDouble(average.toString()), 0.01);
  }

  /**
   * Tests if Exception is thrown for initial time after final time.
   */
  @Test
  void shouldThrowExceptionWhenInitialTimeAfterFinalTime() throws InstantiationException {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    String expectedMessage = "Initial time must be before final time";

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    LocalDateTime initialTime = LocalDateTime.now().plusHours(3);
    LocalDateTime finalTime = LocalDateTime.now();

    Exception exception =
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> averagePowerConsumptionSensor.getValue(initialTime, finalTime));

    // Assert
    String actualMessage = exception.getMessage();
    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Tests if Exception is thrown for initial time equals to final time.
   */
  @Test
  void shouldReturnAverageValueWhenInitialEqualsToFinalTime() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    String expectedMessage = "There is already a reading for this time";

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    LocalDateTime initialTime = LocalDateTime.now();

    averagePowerConsumptionSensor.addReading(initialTime, 1000);

    Exception exception =
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> averagePowerConsumptionSensor.addReading(initialTime, 1000));

    // Assert
    String actualMessage = exception.getMessage();
    Assertions.assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * See if the getValue method works but value is not a dummy, for this instance.
   */
  @Test
  void shouldReturnAverageValueForThisInstant() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
    double expectedAverage = 0;

    // Act
    IValueObject averageValue = averagePowerConsumptionSensor.getValue();
    // Assert
    Assertions.assertEquals(expectedAverage, Double.parseDouble(averageValue.toString()), 0.01);
  }

  /**
   * See if the addReading method works.
   */
  @Test
  void shouldReturnReading() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
    LocalDateTime initialTime = LocalDateTime.now();
    // Act
    double reading = averagePowerConsumptionSensor.addReading(initialTime, 500);
    // Assert
    Assertions.assertEquals(500, reading, 0.01);
  }

  /**
   * Test to verify that the {@link AveragePowerConsumptionSensor} generates an ID when the sensor
   * is instantiated.
   */
  @Test
  void shouldGenerateSensorID_WhenAveragePowerConsumptionSensorIsInstantiated() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
    SensorID result = averagePowerConsumptionSensor.getID();

    Assertions.assertTrue(averagePowerConsumptionSensor.toString().contains(result.toString()));
  }

  /**
   * Test to verify that the {@link AveragePowerConsumptionSensor} returns the sensor name.
   */
  @Test
  void shouldGetSensorName_WhenAveragePowerConsumptionSensorIsInstantiated() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    //Act
    SensorName result = averagePowerConsumptionSensor.getSensorName();

    //Assert
    Assertions.assertEquals(sensorName.toString(), result.toString());
  }

  /**
   * Test to verify that the {@link AveragePowerConsumptionSensor} returns the model path.
   */
  @Test
  void shouldGetModelPath_WhenAveragePowerConsumptionSensorIsInstantiated() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
    ModelPath result = averagePowerConsumptionSensor.getModelPath();

    Assertions.assertEquals(modelPath.toString(), result.toString());
  }

  /**
   * Test to verify that the {@link AveragePowerConsumptionSensor} returns the device ID.
   */
  @Test
  void shouldGetDeviceID_WhenAveragePowerConsumptionSensorIsInstantiated() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
    DeviceID result = averagePowerConsumptionSensor.getDeviceID();

    Assertions.assertEquals(deviceID.toString(), result.toString());
  }

  /**
   * Test to verify that the {@link AveragePowerConsumptionSensor} returns the sensor type ID.
   */
  @Test
  void shouldGetSensorTypeID_WhenAveragePowerConsumptionSensorIsInstantiated() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
    SensorTypeID result = averagePowerConsumptionSensor.getSensorTypeID();

    Assertions.assertEquals(sensorTypeID.toString(), result.toString());
  }

  /**
   * Test to verify that the {@link AveragePowerConsumptionSensor} returns true when comparing the
   * same sensor.
   */
  @Test
  void shouldReturnTrueWhenComparingSameAveragePowerConsumptionSensors() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID1 = new DeviceID(deviceIDValue);
    ModelPath modelPath1 = new ModelPath(modelPathValue);
    SensorName sensorName1 = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID1 = new SensorTypeID(sensorTypeIDValue);

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID1, modelPath1, sensorTypeID1, sensorName1);
    // Act
    boolean result = averagePowerConsumptionSensor.equals(averagePowerConsumptionSensor);

    // Assert
    assertTrue(result);
  }

  /**
   * Test to verify that the {@link AveragePowerConsumptionSensor} returns false when comparing
   * different sensors.
   */
  @Test
  void shouldReturnFalseWhenComparingDifferentAveragePowerConsumptionSensors() {
    // Arrange
    String deviceIDValue1 = "deviceID1";
    String modelPathValue1 = "modelPath1";
    String sensorNameValue1 = "sensorName1";
    String sensorTypeIDValue1 = "AveragePowerConsumption";

    DeviceID deviceID1 = new DeviceID(deviceIDValue1);
    ModelPath modelPath1 = new ModelPath(modelPathValue1);
    SensorName sensorName1 = new SensorName(sensorNameValue1);
    SensorTypeID sensorTypeID1 = new SensorTypeID(sensorTypeIDValue1);

    String deviceIDValue2 = "deviceID2";
    String modelPathValue2 = "modelPath2";
    String sensorNameValue2 = "sensorName2";
    String sensorTypeIDValue2 = "AveragePowerConsumption";

    DeviceID deviceID2 = new DeviceID(deviceIDValue2);
    ModelPath modelPath2 = new ModelPath(modelPathValue2);
    SensorName sensorName2 = new SensorName(sensorNameValue2);
    SensorTypeID sensorTypeID2 = new SensorTypeID(sensorTypeIDValue2);

    AveragePowerConsumptionSensor averagePowerConsumptionSensor1 =
        new AveragePowerConsumptionSensor(deviceID1, modelPath1, sensorTypeID1, sensorName1);
    AveragePowerConsumptionSensor averagePowerConsumptionSensor2 =
        new AveragePowerConsumptionSensor(deviceID2, modelPath2, sensorTypeID2, sensorName2);
    // Act
    boolean result = averagePowerConsumptionSensor1.equals(averagePowerConsumptionSensor2);

    // Assert
    Assertions.assertFalse(result);
  }

  /**
   * Test to verify that the {@link AveragePowerConsumptionSensor} returns false when comparing with
   * null.
   */
  @Test
  void shouldReturnFalseWhenComparingWithNull() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
    // Act
    boolean result = averagePowerConsumptionSensor.equals(null);

    // Assert
    Assertions.assertFalse(result);
  }

  /**
   * Test to verify that the {@link AveragePowerConsumptionSensor} returns false when comparing with
   * different type.
   */
  @Test
  void shouldReturnFalseWhenComparingWithDifferentType() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
    // Act
    boolean result = averagePowerConsumptionSensor.equals(new Object());

    // Assert
    Assertions.assertFalse(result);
  }

  /**
   * Test to verify that the {@link AveragePowerConsumptionSensor} returns the same hash code for
   * the same sensor.
   */
  @Test
  void hashCodeShouldBeDerivedFromSensorID() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    AveragePowerConsumptionSensor averagePowerConsumptionSensor =
        new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
    // Act
    int expectedHashCode = averagePowerConsumptionSensor.getID().hashCode();

    int result = averagePowerConsumptionSensor.hashCode();

    // Assert
    Assertions.assertEquals(expectedHashCode, result);
  }

  /**
   * Should instantiate average power consumption sensor.
   */
  @Test
  void shouldReturnAveragePowerConsumptionSensor_WithValidParametersIncludingSensorID() {
    //Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";
    String sensorIDValue = "sensorID";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = new SensorID(sensorIDValue);

    //Act
    AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(
        deviceID, modelPath, sensorTypeID, sensorName, sensorID);

    //Assert
    assertNotNull(averagePowerConsumptionSensor);
  }

  /**
   * Should throw exception when sensorID null.
   */
  @Test
  void shouldThrowException_WhenSensorIDIsNull() {
    //Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = null;

    String expectedMessage = "SensorID is required";

    //Act + Assert
    Exception e = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName,
            sensorID));

    String actualMessage = e.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when model path null.
   */
  @Test
  void shouldThrowException_WhenModelPathIsNull() {
    //Arrange
    String deviceIDValue = "deviceID";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";
    String sensorIDValue = "sensorID";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = null;
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = new SensorID(sensorIDValue);

    String expectedMessage = "ModelPath is required";

    //Act + Assert
    Exception e = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName,
            sensorID));

    String actualMessage = e.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when sensor name null.
   */
  @Test
  void shouldThrowException_WhenSensorNameIsNull() {
    //Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorTypeIDValue = "AveragePowerConsumption";
    String sensorIDValue = "sensorID";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = new SensorID(sensorIDValue);

    String expectedMessage = "SensorName is required";

    //Act + Assert
    Exception e = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName,
            sensorID));

    String actualMessage = e.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when sensor type ID null.
   */
  @Test
  void shouldThrowException_WhenSensorTypeIDIsNull() {
    //Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorIDValue = "sensorID";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = null;
    SensorID sensorID = new SensorID(sensorIDValue);

    String expectedMessage = "SensorTypeID is required";

    //Act + Assert
    Exception e = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName,
            sensorID));

    String actualMessage = e.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should throw exception when device ID null.
   */
  @Test
  void shouldThrowException_WhenDeviceIDIsNull() {
    //Arrange
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";
    String sensorIDValue = "sensorID";

    DeviceID deviceID = null;
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = new SensorID(sensorIDValue);

    String expectedMessage = "DeviceID is required";

    //Act + Assert
    Exception e = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new AveragePowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName,
            sensorID));

    String actualMessage = e.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  void shouldAcceptVisitorAndReturnInstanceOfObjectInString() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "AveragePowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    AveragePowerConsumptionSensor averagePowerConsumptionSensor = new AveragePowerConsumptionSensor(
        deviceID, modelPath, sensorTypeID, sensorName);
    ISensorVisitor visitor = mock(ISensorVisitor.class);
    String expected = averagePowerConsumptionSensor.toString();
    // Act
    String result = averagePowerConsumptionSensor.accept(visitor);
    // Assert
    assertEquals(expected, result);
  }

}
