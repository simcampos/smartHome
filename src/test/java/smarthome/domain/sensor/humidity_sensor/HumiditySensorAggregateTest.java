/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.humidity_sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.visitor_pattern.ISensorVisitor;
import smarthome.utils.visitor_pattern.SensorVisitorForDataModelImpl;

class HumiditySensorAggregateTest {

  /**
   * Tests the instantiation of HumiditySensor when the constructor arguments are valid.
   */
  @Test
  void shouldInstantiateHumiditySensor_whenConstructorArgumentsAreValid() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Humidity";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    // Act
    HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    // Assert
    assertNotNull(humiditySensor);
  }

  /**
   * Tests the instantiation of HumiditySensor when the deviceID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
    // Arrange
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Humidity";

    DeviceID deviceID = null;
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    assertEquals("DeviceID is required", exception.getMessage());
  }

  /**
   * Tests the instantiation of HumiditySensor when the modelPath is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
    // Arrange
    String deviceIDValue = "deviceID";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Humidity";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = null;
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    assertEquals("ModelPath is required", exception.getMessage());
  }

  /**
   * Tests the instantiation of HumiditySensor when the sensorTypeID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNull() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = null;

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    assertEquals("SensorTypeID is required", exception.getMessage());
  }

  /**
   * Tests the instantiation of HumiditySensor when the sensorName is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorTypeIDValue = "Humidity";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    assertEquals("SensorName is required", exception.getMessage());
  }

  /**
   * Tests the getID method of HumiditySensor.
   */
  @Test
  void shouldReturnSensorID_whenGetIDIsCalled() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Humidity";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    // Act
    SensorID result = humiditySensor.getID();

    // Assert
    assertNotNull(result);
  }

  /**
   * Tests the getModelPath method of HumiditySensor.
   */
  @Test
  void shouldReturnModelPath_whenGetModelPathIsCalled() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Humidity";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    // Act
    ModelPath result = humiditySensor.getModelPath();

    // Assert
    assertEquals(modelPath, result);
  }

  /**
   * Tests the getName method of HumiditySensor.
   */
  @Test
  void shouldReturnSensorName_whenGetNameIsCalled() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Humidity";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    // Act
    SensorName result = humiditySensor.getSensorName();

    // Assert
    assertEquals(sensorName, result);
  }

  /**
   * Tests the getSensorTypeID method of HumiditySensor.
   */
  @Test
  void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Humidity";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    // Act
    SensorTypeID result = humiditySensor.getSensorTypeID();

    // Assert
    assertEquals(sensorTypeID, result);
  }

  /**
   * Tests the getter for the sensor type ID when the sensor type ID is not of type 'Humidity'.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNotHumidity() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Temperature";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName));

    // Assert
    assertEquals("SensorTypeID must be of type 'Humidity'", exception.getMessage());
  }

  /**
   * Tests the getDeviceID method of HumiditySensor.
   */
  @Test
  void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Humidity";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    // Act
    DeviceID result = humiditySensor.getDeviceID();

    // Assert
    assertEquals(deviceID, result);
  }

  /**
   * Tests the getSensorValue method of HumiditySensor.
   */
  @Test
  void shouldReturnSensorValue_whenGetSensorValueIsCalled() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Humidity";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
        sensorName);

    // Act
    HumiditySensorValue result = humiditySensor.getValue();

    // Assert
    double value = Double.parseDouble(result.toString());
    Assertions.assertTrue(value >= 0 && value <= 100);
  }

  /**
   * Tests if the humidity sensor is instantiated correctly.
   */
  @Test
  void shouldInstantiateHumiditySensor_WithSensorID() {
    //Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Humidity";
    String sensorIDValue = "sensorID";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = new SensorID(sensorIDValue);

    //Act
    HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
        sensorName, sensorID);

    //Assert
    Assertions.assertNotNull(humiditySensor);
  }

  /**
   * Should throw an exception when the sensorID is null.
   */
  @Test
  void shouldThrowException_WhenSensorIDIsNull() {
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Humidity";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = null;

    String expectedMessage = "SensorID is required";

    //Act + Assert
    Exception exception = Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID));

    String actualMessage = exception.getMessage();

    //Assert
    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test for the constructor that takes a sensor ID as a parameter.
   */
  @Test
  void shouldReturnHumiditySensor_whenSensorIDIsGiven() {
    //Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Humidity";
    String sensorIDValue = "sensorID";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = new SensorID(sensorIDValue);

    //Act
    HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
        sensorName, sensorID);

    //Assert
    assertNotNull(humiditySensor);
  }

  /**
   * Test for sensor ID null
   */
  @Test
  void shouldThrowException_whenSensorIDIsNull() {
    //Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Humidity";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = null;

    String expectedMessage = "SensorID is required";

    //Act + Assert
    Exception exception = assertThrows(
        IllegalArgumentException.class,
        () -> new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test for device ID null
   */
  @Test
  void shouldThrowExceptionWhitValidSensorID_whenDeviceIDIsNull() {
    //Arrange
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Humidity";
    String sensorIDValue = "sensorID";

    DeviceID deviceID = null;
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = new SensorID(sensorIDValue);

    String expectedMessage = "DeviceID is required";

    //Act + Assert
    Exception exception = assertThrows(
        IllegalArgumentException.class,
        () -> new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test for model path null
   */
  @Test
  void shouldThrowExceptionWhitValidSensorID_whenModelPathIsNull() {
    //Arrange
    String deviceIDValue = "deviceID";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Humidity";
    String sensorIDValue = "sensorID";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = null;
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = new SensorID(sensorIDValue);

    String expectedMessage = "ModelPath is required";

    //Act + Assert
    Exception exception = assertThrows(
        IllegalArgumentException.class,
        () -> new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test for sensor name null
   */
  @Test
  void shouldThrowExceptionWhitValidSensorID_whenSensorNameIsNull() {
    //Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorTypeIDValue = "Humidity";
    String sensorIDValue = "sensorID";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = new SensorID(sensorIDValue);

    String expectedMessage = "SensorName is required";

    //Act + Assert
    Exception exception = assertThrows(
        IllegalArgumentException.class,
        () -> new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Test for sensor type ID null
   */
  @Test
  void shouldThrowExceptionWhitValidSensorID_whenSensorTypeIDIsNull() {
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
    Exception exception = assertThrows(
        IllegalArgumentException.class,
        () -> new HumiditySensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID));

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Visitor should be able to visit the HumiditySensor
   */
  @Test
  void shouldAcceptVisitorAndReturnInstanceOfObjectInString() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Humidity";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
        sensorName);
    String expected = humiditySensor.toString();
    ISensorVisitor visitor = mock(SensorVisitorForDataModelImpl.class);
    // Act
    String result = humiditySensor.accept(visitor);

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Should return expected string
   */
  @Test
  void shouldReturnExpectedString() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Humidity";
    String sensorIDValue = "sensorID";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = new SensorID(sensorIDValue);

    HumiditySensor humiditySensor = new HumiditySensor(deviceID, modelPath, sensorTypeID,
        sensorName, sensorID);
    String expected = "HumiditySensor{" +
        "_modelPath=" + modelPath +
        ", _sensorName=" + sensorName +
        ", _sensorID=" + sensorID +
        ", _sensorTypeID=" + sensorTypeID +
        ", _deviceID=" + deviceID +
        '}';
    // Act
    String result = humiditySensor.toString();

    // Assert
    assertEquals(expected, result);
  }


}



