/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.electric_consumption_wh_sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.visitor_pattern.ISensorVisitor;
import smarthome.utils.visitor_pattern.SensorVisitorForDataModelImpl;

class ElectricConsumptionWhSensorAggregateTest {

  /**
   * Test getID method
   */
  @Test
  void shouldReturnElectricConsumptionWhSensorID_WhenGetIDisCalled() {
    //Arrange
    ModelPath modelPath = new ModelPath("modelPath");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
    SensorName sensorName = new SensorName("sensorName");
    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);

    //Act
    SensorID sensorID = electricConsumptionWhSensor.getID();
    //Assert
    assertTrue(electricConsumptionWhSensor.toString().contains(sensorID.toString()));
  }

  /**
   * Test getName method
   */
  @Test
  void shouldReturnElectricConsumptionWhSensorModelPath_whenGetModelPathIsCalled() {
    //Arrange
    ModelPath modelPath = new ModelPath("modelPath");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
    SensorName sensorName = new SensorName("sensorName");
    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    //Act
    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
    //Assert
    assertEquals(modelPath, electricConsumptionWhSensor.getModelPath());
  }

  /**
   * Test getSensorTypeID method
   */
  @Test
  void shouldReturnElectricConsumptionWhSensorSensorTypeID_WhenGetSensorTypeIDisCalled() {
    //Arrange
    ModelPath modelPath = new ModelPath("modelPath");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
    SensorName sensorName = new SensorName("sensorName");
    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    //Act
    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
    //Assert
    assertEquals(sensorTypeID, electricConsumptionWhSensor.getSensorTypeID());
  }

  /**
   * Test getName method
   */
  @Test
  void shouldReturnElectricConsumptionWhSensorSensorName_WhenGetNameIsCalled() {
    //Arrange
    ModelPath modelPath = new ModelPath("modelPath");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
    SensorName sensorName = new SensorName("sensorName");
    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    //Act
    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
    //Assert
    assertEquals(sensorName, electricConsumptionWhSensor.getSensorName());
  }

  /**
   * Test getDeviceID method
   */
  @Test
  void shouldReturnDeviceID_WhenGetDeviceID() {
    //Arrange
    ModelPath modelPath = new ModelPath("modelPath");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
    SensorName sensorName = new SensorName("sensorName");
    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    //Act
    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
    //Assert
    assertEquals(deviceID, electricConsumptionWhSensor.getDeviceID());
  }

  /**
   * Test getValue method
   */
  @Test
  void shouldReturnElectricConsumptionWhForGivenTimePeriod_WhenGetValue() {
    //Arrange
    ModelPath modelPath = new ModelPath("modelPath");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
    SensorName sensorName = new SensorName("sensorName");
    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
    int consumptionInWh = datePeriod.getDurationInMinutes() * 5;
    ElectricConsumptionWhValue expectedValue = new ElectricConsumptionWhValue(consumptionInWh);
    //Act
    ElectricConsumptionWhValue value = electricConsumptionWhSensor.getValue();
    //Assert
    assertEquals(expectedValue, value);
  }

  /**
   * Should return value when date period is zero.
   */
  @Test
  void shouldReturnZero_WhenDatePeriodIsZero() {
    //Arrange
    ModelPath modelPath = new ModelPath("modelPath");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
    SensorName sensorName = new SensorName("sensorName");
    LocalDateTime startDate = LocalDateTime.now();
    LocalDateTime endDate = LocalDateTime.now();
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
    ElectricConsumptionWhValue expectedValue = new ElectricConsumptionWhValue(0);
    //Act
    ElectricConsumptionWhValue value = electricConsumptionWhSensor.getValue();
    //Assert
    assertEquals(expectedValue, value);
  }

  /**
   * Test method equals of class ElectricConsumptionWhSensor when the instance is compared to
   * itself.
   */
  @Test
  void shouldReturnTrue_whenInstanceIsComparedToItself() {
    // Arrange
    ModelPath modelPath = new ModelPath("modelPath");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
    SensorName sensorName = new SensorName("sensorName");
    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);

    // Act
    boolean result = electricConsumptionWhSensor.equals(electricConsumptionWhSensor);
    // Assert
    assertTrue(result);
  }

  /**
   * Test of method equals when the instances are not equal.
   */
  @Test
  void shouldReturnFalse_whenInstancesAreNotEqual() {
    // Arrange
    ModelPath modelPath1 = new ModelPath("modelPath1");
    ModelPath modelPath2 = new ModelPath("modelPath2");
    DeviceID deviceID1 = new DeviceID("deviceID1");
    DeviceID deviceID2 = new DeviceID("deviceID2");
    SensorTypeID sensorTypeID1 = new SensorTypeID("ElectricConsumptionWh");
    SensorTypeID sensorTypeID2 = new SensorTypeID("ElectricConsumptionWh");
    SensorName sensorName1 = new SensorName("sensorName1");
    SensorName sensorName2 = new SensorName("sensorName2");
    LocalDateTime startDate1 = LocalDateTime.now().minusDays(1);
    LocalDateTime startDate2 = LocalDateTime.now().minusDays(2);
    LocalDateTime endDate1 = LocalDateTime.now();
    LocalDateTime endDate2 = LocalDateTime.now().minusDays(1);
    DatePeriod datePeriod1 = new DatePeriod(startDate1, endDate1);
    DatePeriod datePeriod2 = new DatePeriod(startDate2, endDate2);

    ElectricConsumptionWhSensor electricConsumptionWhSensor1 = new ElectricConsumptionWhSensor(
        deviceID1, modelPath1, sensorTypeID1, sensorName1, datePeriod1);
    ElectricConsumptionWhSensor electricConsumptionWhSensor2 = new ElectricConsumptionWhSensor(
        deviceID2, modelPath2, sensorTypeID2, sensorName2, datePeriod2);

    // Act
    boolean result = electricConsumptionWhSensor1.equals(electricConsumptionWhSensor2);

    // Assert
    assertFalse(result);
  }

  /**
   * Test of method equals when the instance is compared to an object of a different class.
   */
  @Test
  void shouldReturnFalse_whenComparedWithDifferentClass() {
    // Arrange
    ModelPath modelPath = new ModelPath("modelPath");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
    SensorName sensorName = new SensorName("sensorName");
    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);

    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);

    // Act
    boolean result = electricConsumptionWhSensor.equals(new Object());
    // Assert
    assertFalse(result);
  }

  /**
   * Test of method toString.
   */
  @Test
  void shouldReturnString_whenToStringIsCalled() {
    // Arrange
    ModelPath modelPath = new ModelPath("modelPath");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
    SensorName sensorName = new SensorName("sensorName");
    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);

    SensorID sensorID = electricConsumptionWhSensor.getID();
    ElectricConsumptionWhValue electricConsumptionWhValue = electricConsumptionWhSensor.getValue();
    String expected =
        deviceID + " " + modelPath + " " + sensorTypeID + " " + electricConsumptionWhValue + " "
            + sensorName + " " + sensorID + " " + datePeriod;
    // Act

    String result = electricConsumptionWhSensor.toString();
    // Assert
    assertEquals(expected, result);
  }

  /**
   * Test of method hashcode.
   */
  @Test
  void shouldReturnHashCode_whenHashCodeIsCalled() {
    // Arrange
    ModelPath modelPath = new ModelPath("modelPath");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
    SensorName sensorName = new SensorName("sensorName");
    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
    SensorID sensorID = electricConsumptionWhSensor.getID();
    int expected = sensorID.getID().hashCode();
    // Act
    int result = electricConsumptionWhSensor.hashCode();
    // Assert
    assertEquals(expected, result);
  }

  /**
   * Test for the constructor that takes a sensor ID as a parameter.
   */
  @Test
  void shouldReturnElectricConsumptionWhSensor_whenSensorIDIsGiven() {
    // Arrange
    String idName = "sensorID";
    String deviceIDName = "deviceID";
    String modelPathName = "SmartHome.sensors.ElectricConsumptionWh";
    String sensorTypeIDName = "ElectricConsumptionWh";
    String name = "ElectricConsumptionWhSensor";

    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();

    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    ModelPath modelPath = new ModelPath(modelPathName);
    DeviceID deviceID = new DeviceID(deviceIDName);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDName);
    SensorName sensorName = new SensorName(name);
    SensorID sensorID = new SensorID(idName);

    // Act
    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod, sensorID);

    // Assert
    assertNotNull(electricConsumptionWhSensor);
  }

  /**
   * Test for model path null, whit Sensor ID.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenModelPathIsNullWithSensorID() {
    // Arrange
    String idName = "sensorID";
    String deviceIDName = "deviceID";
    String sensorTypeIDName = "ElectricConsumptionWh";
    String name = "ElectricConsumptionWhSensor";

    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();

    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = null;
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDName);
    SensorName sensorName = new SensorName(name);
    SensorID sensorID = new SensorID(idName);

    String expectedMessage = "ModelPath is required";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName,
            datePeriod, sensorID));
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test for sensor name null, whit Sensor ID.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorNameIsNullWithSensorID() {
    // Arrange
    String idName = "sensorID";
    String deviceIDName = "deviceID";
    String modelPathName = "SmartHome.sensors.ElectricConsumptionWh";
    String sensorTypeIDName = "ElectricConsumptionWh";

    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();

    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDName);
    SensorName sensorName = null;
    SensorID sensorID = new SensorID(idName);

    String expectedMessage = "SensorName is required";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName,
            datePeriod, sensorID));
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test for sensor type ID null, whit Sensor ID.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNullWithSensorID() {
    // Arrange
    String idName = "sensorID";
    String deviceIDName = "deviceID";
    String modelPathName = "SmartHome.sensors.ElectricConsumptionWh";
    String name = "ElectricConsumptionWhSensor";

    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();

    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorTypeID sensorTypeID = null;
    SensorName sensorName = new SensorName(name);
    SensorID sensorID = new SensorID(idName);

    String expectedMessage = "SensorTypeID is required";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName,
            datePeriod, sensorID));
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test for sensor ID null, whit Sensor ID.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorIDIsNullWithSensorID() {
    // Arrange
    String deviceIDName = "deviceID";
    String modelPathName = "SmartHome.sensors.ElectricConsumptionWh";
    String sensorTypeIDName = "ElectricConsumptionWh";
    String name = "ElectricConsumptionWhSensor";

    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();

    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDName);
    SensorName sensorName = new SensorName(name);
    SensorID sensorID = null;

    String expectedMessage = "SensorID is required";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName,
            datePeriod, sensorID));
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test for device ID null, whit Sensor ID.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceIDIsNullWithSensorID() {
    // Arrange
    String idName = "sensorID";
    String modelPathName = "SmartHome.sensors.ElectricConsumptionWh";
    String sensorTypeIDName = "ElectricConsumptionWh";
    String name = "ElectricConsumptionWhSensor";

    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();

    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    DeviceID deviceID = null;
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDName);
    SensorName sensorName = new SensorName(name);
    SensorID sensorID = new SensorID(idName);

    String expectedMessage = "DeviceID is required";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName,
            datePeriod, sensorID));
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test for date period null, whit Sensor ID.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDatePeriodIsNullWithSensorID() {
    // Arrange
    String idName = "sensorID";
    String deviceIDName = "deviceID";
    String modelPathName = "SmartHome.sensors.ElectricConsumptionWh";
    String sensorTypeIDName = "ElectricConsumptionWh";
    String name = "ElectricConsumptionWhSensor";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDName);
    SensorName sensorName = new SensorName(name);
    SensorID sensorID = new SensorID(idName);

    String expectedMessage = "DatePeriod is required";

    // Act & Assert
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName, null,
            sensorID));
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should Return the date period of the sensor.
   */
  @Test
  void shouldReturnDatePeriod_whenGetDatePeriodIsCalled() {
    // Arrange
    String idName = "sensorID";
    String deviceIDName = "deviceID";
    String modelPathName = "SmartHome.sensors.ElectricConsumptionWh";
    String sensorTypeIDName = "ElectricConsumptionWh";
    String name = "ElectricConsumptionWhSensor";

    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();

    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDName);
    SensorName sensorName = new SensorName(name);
    SensorID sensorID = new SensorID(idName);

    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod, sensorID);

    // Act
    DatePeriod result = electricConsumptionWhSensor.getDatePeriod();

    // Assert
    assertEquals(datePeriod, result);
  }

  /**
   * Shoud accept the visitor.
   */

  @Test
  void shouldAcceptVisitorAndReturnInstanceOfObjectInString() {
    //Arrange
    ModelPath modelPath = new ModelPath("modelPath");
    DeviceID deviceID = new DeviceID("deviceID");
    SensorTypeID sensorTypeID = new SensorTypeID("ElectricConsumptionWh");
    SensorName sensorName = new SensorName("sensorName");
    LocalDateTime startDate = LocalDateTime.now().minusDays(1);
    LocalDateTime endDate = LocalDateTime.now();
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
    String expected = electricConsumptionWhSensor.toString();

    ISensorVisitor visitor = mock(SensorVisitorForDataModelImpl.class);
    //Act
    String result = electricConsumptionWhSensor.accept(visitor);
    //Assert
    assertEquals(expected, result);
  }
}