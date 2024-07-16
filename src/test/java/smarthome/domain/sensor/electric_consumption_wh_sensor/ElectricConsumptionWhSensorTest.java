/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.electric_consumption_wh_sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smarthome.domain.value_object.DatePeriod;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;

class ElectricConsumptionWhSensorTest {

  /**
   * Should throw IllegalArgumentException when modelPath is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenModelPathIsNull() {
    //Arrange
    ModelPath modelPath = null;
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("ElectricConsumptionWh");
    SensorName sensorName = mock(SensorName.class);
    DatePeriod datePeriod = mock(DatePeriod.class);
    String expectedMessage = "ModelPath is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName,
            datePeriod));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should throw IllegalArgumentException when sensorName is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenSensorNameIsNull() {
    //Arrange
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("ElectricConsumptionWh");
    SensorName sensorName = null;
    DatePeriod datePeriod = mock(DatePeriod.class);
    String expectedMessage = "SensorName is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName,
            datePeriod));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should throw IllegalArgumentException when sensorTypeID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenSensorTypeIDIsNull() {
    //Arrange
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = null;
    SensorName sensorName = mock(SensorName.class);
    DatePeriod datePeriod = mock(DatePeriod.class);
    String expectedMessage = "SensorTypeID is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName,
            datePeriod));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should throw IllegalArgumentException when sensorTypeID is not ElectricConsumptionWh.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenSensorTypeIDIsNotElectricConsumptionWh() {
    //Arrange
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorName sensorName = mock(SensorName.class);
    DatePeriod datePeriod = mock(DatePeriod.class);
    String expectedMessage = "SensorTypeID must be of type 'ElectricConsumptionWh'";

    when(sensorTypeID.getID()).thenReturn("NotElectricConsumptionWh");

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName,
            datePeriod));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should throw IllegalArgumentException when deviceID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDeviceIDIsNull() {
    //Arrange
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = null;
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorName sensorName = mock(SensorName.class);
    DatePeriod datePeriod = mock(DatePeriod.class);
    String expectedMessage = "DeviceID is required";
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName,
            datePeriod));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should throw IllegalArgumentException when datePeriod is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDatePeriodIsNull() {
    //Arrange
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorName sensorName = mock(SensorName.class);
    DatePeriod datePeriod = null;
    String expectedMessage = "DatePeriod is required";

    when(sensorTypeID.getID()).thenReturn("ElectricConsumptionWh");
    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName,
            datePeriod));
    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should create ElectricConsumptionWhSensor instance when all parameters are valid.
   */
  @Test
  void shouldCreateElectricConsumptionWhSensorInstance_WhenAllParametersAreValid() {
    //Arrange
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorName sensorName = mock(SensorName.class);
    DatePeriod datePeriod = mock(DatePeriod.class);

    when(sensorTypeID.getID()).thenReturn("ElectricConsumptionWh");

    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);

    //Act & Assert
    assertEquals(sensorTypeID, electricConsumptionWhSensor.getSensorTypeID());
  }

  /**
   * Should create ElectricConsumptionWhSensor instance when all parameters are valid, including
   * SensorID
   */
  @Test
  void shouldCreateElectricConsumptionWhSensorInstance_WhenAllParametersAreValidIncludingSensorID() {
    //Arrange
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorName sensorName = mock(SensorName.class);
    DatePeriod datePeriod = mock(DatePeriod.class);
    SensorID sensorID = mock(SensorID.class);
    when(sensorID.toString()).thenReturn("1");

    when(sensorTypeID.getID()).thenReturn("ElectricConsumptionWh");

    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod, sensorID);

    //Act & Assert
    assertTrue(electricConsumptionWhSensor.toString().contains("1"));

  }

  /**
   * Should throw IllegalArgumentException when SensorID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenSensorIDIsNull() {
    //Arrange
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    SensorName sensorName = mock(SensorName.class);
    DatePeriod datePeriod = mock(DatePeriod.class);
    SensorID sensorID = null;
    String expectedMessage = "SensorID is required";

    when(sensorTypeID.getID()).thenReturn("ElectricConsumptionWh");

    //Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ElectricConsumptionWhSensor(deviceID, modelPath, sensorTypeID, sensorName,
            datePeriod, sensorID));

    //Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test getID method
   */
  @Test
  void shouldReturnElectricConsumptionWhSensorID_WhenGetIdIsCalled() {
    //Arrange
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("ElectricConsumptionWh");
    SensorName sensorName = mock(SensorName.class);
    DatePeriod datePeriod = mock(DatePeriod.class);
    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);

    //Act
    SensorID sensorID = electricConsumptionWhSensor.getID();

    //Assert
    assertTrue(electricConsumptionWhSensor.toString().contains(sensorID.toString()));
  }

  /**
   * Test getModelPath method
   */
  @Test
  void shouldReturnElectricConsumptionWhSensorModelPath_WhenGetModelPathIsCalled() {
    //Arrange
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("ElectricConsumptionWh");
    SensorName sensorName = mock(SensorName.class);
    DatePeriod datePeriod = mock(DatePeriod.class);

    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
    //Act & Assert
    assertEquals(modelPath, electricConsumptionWhSensor.getModelPath());
  }

  /**
   * Test getName method
   */
  @Test
  void shouldReturnElectricConsumptionWhSensorName_WhenGetNameIsCalled() {
    //Arrange
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("ElectricConsumptionWh");
    SensorName sensorName = mock(SensorName.class);
    DatePeriod datePeriod = mock(DatePeriod.class);

    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
    //Act & Assert
    assertEquals(sensorName, electricConsumptionWhSensor.getSensorName());
  }

  /**
   * Test getSensorTypeID method
   */
  @Test
  void shouldReturnElectricConsumptionWhSensorSensorTypeID_WhenGetSensorTypeIDisCalled() {
    //Arrange
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("ElectricConsumptionWh");
    SensorName sensorName = mock(SensorName.class);
    DatePeriod datePeriod = mock(DatePeriod.class);

    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
    //Act & AssertA
    assertEquals(sensorTypeID, electricConsumptionWhSensor.getSensorTypeID());
  }

  /**
   * Test getDeviceID
   */
  @Test
  void shouldReturnDeviceID_WhenGetDeviceIDisCalled() {
    //Arrange
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("ElectricConsumptionWh");
    SensorName sensorName = mock(SensorName.class);
    DatePeriod datePeriod = mock(DatePeriod.class);

    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);

    //Act & Assert
    assertEquals(deviceID, electricConsumptionWhSensor.getDeviceID());
  }

  /**
   * Test getValue method
   */
  @Test
  void shouldReturnElectricConsumptionWhForGivenTimePeriod_WhenGetValueIsCalled() {
    //Arrange
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("ElectricConsumptionWh");
    SensorName sensorName = mock(SensorName.class);
    DatePeriod datePeriod = mock(DatePeriod.class);

    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);

    //Act
    ElectricConsumptionWhValue value = electricConsumptionWhSensor.getValue();

    //Assert
    Assertions.assertNotNull(value);
  }

  /**
   * Test method equals of class ElectricConsumptionWhSensor when the instance is compared to
   * itself.
   */
  @Test
  void shouldReturnTrue_WhenInstanceIsComparedToItself() {
    //Arrange
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("ElectricConsumptionWh");
    SensorName sensorName = mock(SensorName.class);
    DatePeriod datePeriod = mock(DatePeriod.class);

    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);

    //Act
    boolean result = electricConsumptionWhSensor.equals(electricConsumptionWhSensor);

    //Assert
    assertTrue(result);
  }

  /**
   * Test of method equals when the instances are not equal.
   */
  @Test
  void shouldReturnFalse_WhenInstancesAreNotEqual() {
    //Arrange
    ModelPath modelPath1 = mock(ModelPath.class);
    ModelPath modelPath2 = mock(ModelPath.class);
    DeviceID deviceID1 = mock(DeviceID.class);
    DeviceID deviceID2 = mock(DeviceID.class);
    SensorTypeID sensorTypeID1 = mock(SensorTypeID.class);
    SensorTypeID sensorTypeID2 = mock(SensorTypeID.class);
    when(sensorTypeID1.getID()).thenReturn("ElectricConsumptionWh");
    when(sensorTypeID2.getID()).thenReturn("ElectricConsumptionWh");
    SensorName sensorName1 = mock(SensorName.class);
    SensorName sensorName2 = mock(SensorName.class);
    DatePeriod datePeriod1 = mock(DatePeriod.class);
    DatePeriod datePeriod2 = mock(DatePeriod.class);

    ElectricConsumptionWhSensor electricConsumptionWhSensor1 = new ElectricConsumptionWhSensor(
        deviceID1, modelPath1, sensorTypeID1, sensorName1, datePeriod1);
    ElectricConsumptionWhSensor electricConsumptionWhSensor2 = new ElectricConsumptionWhSensor(
        deviceID2, modelPath2, sensorTypeID2, sensorName2, datePeriod2);

    //Act
    boolean result = electricConsumptionWhSensor1.equals(electricConsumptionWhSensor2);

    //Assert
    Assertions.assertFalse(result);
  }

  /**
   * Test of method equals when the instance is compared to an object of a different class.
   */
  @Test
  void shouldReturnFalse_WhenComparedWithDifferentClass() {
    //Arrange
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("ElectricConsumptionWh");
    SensorName sensorName = mock(SensorName.class);
    DatePeriod datePeriod = mock(DatePeriod.class);

    ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
        deviceID, modelPath, sensorTypeID, sensorName, datePeriod);

    //Act
    boolean result = electricConsumptionWhSensor.equals(new Object());

    //Assert
    Assertions.assertFalse(result);
  }

  /**
   * Test of method toString.
   */
  @Test
  void shouldReturnElectricConsumptionWhSensorString_WhenToStringIsCalled() {
    //Arrange
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("ElectricConsumptionWh");
    SensorName sensorName = mock(SensorName.class);
    DatePeriod datePeriod = mock(DatePeriod.class);

    try (MockedConstruction<SensorID> sensorIdDouble = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.toString()).thenReturn("1");
        })) {
      ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
          deviceID, modelPath, sensorTypeID, sensorName, datePeriod);
      //Act
      String result = electricConsumptionWhSensor.toString();

      //Assert
      assertTrue(result.contains("1"));
    }
  }

  /**
   * Test of method hashcode.
   */
  @Test
  void shouldReturnHashCode_WhenHashCodeIsCalled() {
    //Arrange
    ModelPath modelPath = mock(ModelPath.class);
    DeviceID deviceID = mock(DeviceID.class);
    SensorTypeID sensorTypeID = mock(SensorTypeID.class);
    when(sensorTypeID.getID()).thenReturn("ElectricConsumptionWh");
    SensorName sensorName = mock(SensorName.class);
    DatePeriod datePeriod = mock(DatePeriod.class);

    try (MockedConstruction<SensorID> sensorIdDouble = mockConstruction(SensorID.class,
        (mock, context) -> {
          when(mock.toString()).thenReturn("1");
        })) {
      ElectricConsumptionWhSensor electricConsumptionWhSensor = new ElectricConsumptionWhSensor(
          deviceID, modelPath, sensorTypeID, sensorName, datePeriod);

      int expected = electricConsumptionWhSensor.getID().hashCode();

      //Act
      int result = electricConsumptionWhSensor.hashCode();

      //Assert
      assertEquals(expected, result);
    }
  }
}
