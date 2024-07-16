/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.dew_point_sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.visitor_pattern.ISensorVisitor;
import smarthome.utils.visitor_pattern.SensorVisitorForDataModelImpl;

class DewPointSensorAggregateTest {

  /**
   * Test to check if the DewPointSensor is instantiated correctly.
   */
  @Test
  void shouldInstantiateDewPointSensor_WhenParametersAreValid() {
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "DewPoint";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    // act
    DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    //assert
    assertNotNull(dewPointSensor);

  }


  /**
   * Test to check if the DewPointSensor throws an exception when the DeviceID is null.
   */
  @Test
  void shouldThrowException_WhenDeviceIDIsNull() {
    //Arrange
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "DewPoint";

    DeviceID deviceID = null;
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    String expectedMessage = "DeviceID is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
    );

    String actualMessage = exception.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);

  }

  /**
   * Test to check if the DewPointSensor throws an exception when the ModelPath is null.
   */
  @Test
  void shouldThrowException_WhenModelPathIsNull() {
    //Arrange
    String deviceIDName = "123A";
    String name = "DewPointSensor";
    String typeID = "DewPoint";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = null;
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    String expectedMessage = "ModelPath is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
    );

    String actualMessage = exception.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test to check if the DewPointSensor throws an exception when the SensorName is null.
   */
  @Test
  void shouldThrowException_WhenSensorNameIsNull() {
    //Arrange
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String typeID = "DewPoint";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    String expectedMessage = "SensorName is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
    );

    String actualMessage = exception.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test to check if the DewPointSensor throws an exception when the SensorTypeID is null.
   */
  @Test
  void shouldThrowException_WhenSensorTypeIDIsNull() {
    //Arrange
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = null;

    String expectedMessage = "SensorTypeID is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
    );

    String actualMessage = exception.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  void shouldThrowException_WhenSensorTypeIDIsInvalid() {
    //Arrange
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "Fahrenheit";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    String expectedMessage = "SensorTypeID must be 'DewPoint'";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName)
    );

    String actualMessage = exception.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);

  }

  /**
   * Should return Sensor ID.
   */
  @Test
  void shouldGetDewPointID() {
    //Arrange
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "DewPoint";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    //Act
    SensorID result = dewPointSensor.getID();

    //Assert
    assertNotNull(result);
  }

  /**
   * Should get sensor name.
   */
  @Test
  void shouldGetDewPointName() {
    //Arrange
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "DewPoint";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    //Act
    SensorName result = dewPointSensor.getSensorName();

    //Assert
    assertEquals(sensorName, result);

  }

  /**
   * Should get model Path.
   */
  @Test
  void shouldGetDewPointModelPath() {
    //Arrange
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "DewPoint";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    //Act
    ModelPath result = dewPointSensor.getModelPath();

    //Assert
    assertEquals(modelPath, result);
  }

  /**
   * Should get sensorType ID.
   */
  @Test
  void shouldGetDewPointSensorTypeID() {
    //Arrange
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "DewPoint";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    //Act
    SensorTypeID result = dewPointSensor.getSensorTypeID();

    //Assert
    assertEquals(sensorTypeID, result);
  }

  /**
   * Should return device ID.
   */
  @Test
  void shouldGetDeviceID() {
    //Arrange
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "DewPoint";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    //Act
    DeviceID result = dewPointSensor.getDeviceID();

    //Assert
    assertEquals(deviceID, result);
  }

  /**
   * Should return dew point value.
   */
  @Test
  void shouldGetDewPointValue() {
    //Arrange
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "DewPoint";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    //Act
    DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    //Assert
    int value = Integer.parseInt(dewPointSensor.getValue().toString());
    assertTrue(value >= -70 && value <= 70);
  }

  /**
   * Tests method equals when the instance is compared to itself.
   */
  @Test
  void shouldReturnTrue_WhenComparingTheSameInstance() {
    //Arrange
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "DewPoint";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    //Act
    boolean result = dewPointSensor.equals(dewPointSensor);

    //Assert
    assertTrue(result);
  }


  /**
   * Tests method equals when there are two different objects.
   */
  @Test
  void shouldReturnFalse_WhenThereTwoDifferentObjects() {
    //Arrange
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "DewPoint";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    //Act
    boolean result = dewPointSensor.equals(new Object());

    //Assert
    assertFalse(result);
  }


  /**
   * Tests equals when instances is compared to a null object.
   */
  @Test
  void shouldReturnFalse_WhenComparedToNull() {
    //Arrange
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "DewPoint";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    //Act
    boolean result = dewPointSensor.equals(null);

    //Assert
    assertFalse(result);
  }


  /**
   * Tests equals when instances is compared to an object of a different class.
   */
  @Test
  void shouldReturnFalse_WhenComparedToDifferentClass() {
    //Arrange
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "DewPoint";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    //Act
    boolean result = dewPointSensor.equals(new Object());

    //Assert
    assertFalse(result);
  }

  /**
   * Test hashCode method.
   */
  @Test
  void shouldReturnHashCode_whenHashCodeIsCalled() {
    //Arrange
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "DewPoint";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    SensorID sensorID = dewPointSensor.getID();
    int expected = sensorID.hashCode();

    //Act
    int result = dewPointSensor.hashCode();

    //Assert
    assertEquals(expected, result);
  }

  /**
   * Test toString method.
   */
  @Test
  void shouldReturnString_whenToStringIsCalled() {
    //Arrange
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "DewPoint";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID,
        sensorName);
    DewPointValue dewPointValue = dewPointSensor.getValue();

    String expected = "DewPointSensor:" +
        " modelPath=" + modelPath +
        ", sensorName=" + sensorName +
        ", sensorID=" + dewPointSensor.getID() +
        ", sensorTypeID=" + sensorTypeID +
        ", dewPointValue=" + dewPointValue +
        ", deviceID=" + deviceID;

    //Act
    String result = dewPointSensor.toString();

    //Assert
    assertEquals(expected, result);
  }

  /**
   * Test to check if the DewPointSensor is instantiated correctly with a SensorID.
   */
  @Test
  void shouldInstantiateDewPointSensor_WhenParametersAreValidWithSensorID() {
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "DewPoint";
    String sensorIDName = "1234";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);
    SensorID sensorID = new SensorID(sensorIDName);

    // act
    DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID,
        sensorName, sensorID);

    //assert
    assertNotNull(dewPointSensor);

  }

  /**
   * Test to check if the DewPointSensor throws an exception when the SensorID is null
   */
  @Test
  void shouldThrowException_WhenSensorIDIsNull() {
    //Arrange
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "DewPoint";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);
    SensorID sensorID = null;

    String expectedMessage = "SensorID is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID)
    );
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);

  }

  /**
   * Test DewPointSensor whit valid SensorID but null DeviceID.
   */
  @Test
  void shouldThrowException_WhenDeviceIDIsNullWithSensorID() {
    //Arrange
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "DewPoint";
    String sensorIDName = "1234";

    DeviceID deviceID = null;
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);
    SensorID sensorID = new SensorID(sensorIDName);

    String expectedMessage = "DeviceID is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID)
    );
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test DewPointSensor whit valid SensorID but null ModelPath.
   */
  @Test
  void shouldThrowException_WhenModelPathIsNullWithSensorID() {
    //Arrange
    String deviceIDName = "123A";
    String name = "DewPointSensor";
    String typeID = "DewPoint";
    String sensorIDName = "1234";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = null;
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);
    SensorID sensorID = new SensorID(sensorIDName);

    String expectedMessage = "ModelPath is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID)
    );
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test DewPointSensor whit valid SensorID but null SensorName.
   */
  @Test
  void shouldThrowException_WhenSensorNameIsNullWithSensorID() {
    //Arrange
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String typeID = "DewPoint";
    String sensorIDName = "1234";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);
    SensorID sensorID = new SensorID(sensorIDName);

    String expectedMessage = "SensorName is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID)
    );
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test DewPointSensor whit valid SensorID but null SensorTypeID.
   */
  @Test
  void shouldThrowException_WhenSensorTypeIDIsNullWithSensorID() {
    //Arrange
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String sensorIDName = "1234";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = null;
    SensorID sensorID = new SensorID(sensorIDName);

    String expectedMessage = "SensorTypeID is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new DewPointSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID)
    );
    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  void shouldAcceptVisitorAndReturnInstanceOfObjectInString() {
    String deviceIDName = "123A";
    String modelPathName = "SmartHome.sensors.DewPointSensor";
    String name = "DewPointSensor";
    String typeID = "DewPoint";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    DewPointSensor dewPointSensor = new DewPointSensor(deviceID, modelPath, sensorTypeID,
        sensorName);
    String expected = dewPointSensor.toString();
    ISensorVisitor visitor = mock(SensorVisitorForDataModelImpl.class);
    //Act
    String result = dewPointSensor.accept(visitor);

    //assert
    assertEquals(expected, result);

  }
}
