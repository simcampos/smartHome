/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.switch_sensor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import smarthome.domain.sensor.dew_point_sensor.DewPointSensor;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorID;
import smarthome.domain.value_object.SensorName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.utils.visitor_pattern.ISensorVisitor;

class SwitchSensorAggregateTest {

  /**
   * Tests the instantiation of SwitchSensor when the constructor arguments are valid.
   */
  @Test
  void shouldInstantiateSwitchSensor_whenConstructorArgumentsAreValid() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    // Act
    SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Assert
    assertNotNull(switchSensor);
  }

  /**
   * Tests the instantiation of SwitchSensor when the deviceID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceIDIsNull() {
    // Arrange
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";

    DeviceID deviceID = null;
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
    });

    // Assert
    assertEquals("DeviceID is required", exception.getMessage());
  }

  /**
   * Tests the instantiation of SwitchSensor when the modelPath is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenModelPathIsNull() {
    // Arrange
    String deviceIDValue = "deviceID";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = null;
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
    });

    // Assert
    assertEquals("ModelPath is required", exception.getMessage());
  }

  /**
   * Tests the instantiation of SwitchSensor when the sensorTypeID is null.
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
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
    });

    // Assert
    assertEquals("SensorTypeID is required", exception.getMessage());
  }

  /**
   * Tests the instantiation of SwitchSensor when the sensorName is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorNameIsNull() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
    });

    // Assert
    assertEquals("SensorName is required", exception.getMessage());
  }

  /**
   * Tests the instantiation of SwitchSensor when the constructor arguments are valid with
   * SensorID.
   */
  @Test
  void shouldInstantiateSwitchSensor_whenConstructorArgumentsAreValidWithSensorID() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";
    String sensorIDValue = "SensorID";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = new SensorID(sensorIDValue);

    // Act
    SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName,
        sensorID);

    // Assert
    assertNotNull(switchSensor);
  }

  /**
   * Tests the instantiation of SwitchSensor when the sensorID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorIDIsNull() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = null;

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);
    });

    // Assert
    assertEquals("SensorID is required", exception.getMessage());
  }

  /**
   * Tests the instantiation of SwitchSensor when the deviceID is null with SensorID.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenDeviceIDIsNullWithSensorID() {
    // Arrange
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";
    String sensorIDValue = "SensorID";

    DeviceID deviceID = null;
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = new SensorID(sensorIDValue);

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);
    });

    // Assert
    assertEquals("DeviceID is required", exception.getMessage());
  }

  /**
   * Tests the instantiation of SwitchSensor when the modelPath is null with SensorID.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenModelPathIsNullWithSensorID() {
    // Arrange
    String deviceIDValue = "deviceID";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";
    String sensorIDValue = "SensorID";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = null;
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = new SensorID(sensorIDValue);

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);
    });

    // Assert
    assertEquals("ModelPath is required", exception.getMessage());
  }

  /**
   * Tests the instantiation of SwitchSensor when the sensorTypeID is null with SensorID.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNullWithSensorID() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorIDValue = "SensorID";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = null;
    SensorID sensorID = new SensorID(sensorIDValue);

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);
    });

    // Assert
    assertEquals("SensorTypeID is required", exception.getMessage());
  }

  /**
   * Tests the instantiation of SwitchSensor when the sensorName is null with SensorID.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorNameIsNullWithSensorID() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorTypeIDValue = "Switch";
    String sensorIDValue = "SensorID";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = new SensorID(sensorIDValue);

    // Act
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);
    });

    // Assert
    assertEquals("SensorName is required", exception.getMessage());
  }

  /**
   * Tests the getID method of SwitchSensor.
   */
  @Test
  void shouldReturnSensorID_whenGetIDIsCalled() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    SensorID result = switchSensor.getID();

    // Assert
    assertNotNull(result);
  }

  /**
   * Tests the getSensorName method of SwitchSensor.
   */
  @Test
  void shouldReturnSensorName_whenGetSensorNameIsCalled() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    SensorName result = switchSensor.getSensorName();

    // Assert
    assertEquals(sensorName, result);
  }

  /**
   * Tests the getModelPath method of SwitchSensor.
   */
  @Test
  void shouldReturnModelPath_whenGetModelPathIsCalled() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    ModelPath result = switchSensor.getModelPath();

    // Assert
    assertEquals(modelPath, result);
  }

  /**
   * Tests the getSensorTypeID method of SwitchSensor.
   */
  @Test
  void shouldReturnSensorTypeID_whenGetSensorTypeIDIsCalled() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    SensorTypeID result = switchSensor.getSensorTypeID();

    // Assert
    assertEquals(sensorTypeID, result);
  }

  /**
   * Tests the getter for the sensor type ID when the sensor type ID is not of type 'Switch'.
   */
  @Test
  void shouldThrowIllegalArgumentException_whenSensorTypeIDIsNotOfSwitchType() {
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
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
      new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
    });

    // Assert
    assertEquals("SensorTypeID must be of type 'Switch'", exception.getMessage());
  }

  /**
   * Tests the getDeviceID method of SwitchSensor.
   */
  @Test
  void shouldReturnDeviceID_whenGetDeviceIDIsCalled() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    DeviceID result = switchSensor.getDeviceID();

    // Assert
    assertEquals(deviceID, result);
  }

  /**
   * Tests the getValue method of SwitchSensor.
   */
  @Test
  void shouldReturnSensorValue_whenGetValueIsCalled() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    SwitchSensorValue result = switchSensor.getValue();

    // Assert
    String state = result.toString();
    assertTrue("On".equals(state) || "Off".equals(state), "State should be 'On' or 'Off'.");
  }

  /**
   * Tests if the equals method returns true when the instances are the same object.
   */
  @Test
  void shouldReturnTrue_WhenInstancesAreSameObject() {
    //Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

    //Act
    boolean result = switchSensor.equals(switchSensor);

    //Assert
    assertTrue(result);
  }

  /**
   * Tests if the equals method returns false when the objects are not the same.
   */
  @Test
  void shouldReturnFalse_WhenObjectIsNotTheSame() {
    //Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);
    SwitchSensor switchSensor1 = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

    //Act
    boolean result = switchSensor.equals(switchSensor1);

    //Assert
    assertFalse(result);
  }

  /**
   * Tests if the equals method returns false when the object is not an instance of SwitchSensor.
   */
  @Test
  void shouldReturnFalse_WhenObjectIsNotInstanceOfSwitchSensor() {
    //Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    DewPointSensor dewPointSensor = mock(DewPointSensor.class);

    SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

    //Act
    boolean result = switchSensor.equals(new Object());

    //Assert
    assertFalse(result);
  }

  /**
   * Tests if the toString method returns the SwitchSensor in string format.
   */
  @Test
  void shouldReturnSwitchSensorInString_WhenToStringMethodIsCalled() {
    //Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

    String expected = "SwitchSensor: DeviceID= " + deviceIDValue + " ModelPath= " + modelPathValue
        + " SensorTypeID= " + sensorTypeIDValue + " SensorName= " + sensorNameValue + " SensorID= "
        + switchSensor.getID();
    //Act
    String result = switchSensor.toString();

    //Assert
    assertEquals(expected, result);
  }

  /**
   * Tests if the hashCode method returns the hash code of the SwitchSensor.
   */
  @Test
  void shouldReturnHashCode_WhenHashCodeMethodIsCalled() {
    //Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

    int expected = switchSensor.getID().hashCode();

    //Act
    int result = switchSensor.hashCode();

    //Assert
    assertEquals(expected, result);
  }

  /**
   * Tests the instantiation of SwitchSensor when the constructor arguments are valid.
   */
  @Test
  void shouldInstantiateSwitchSensor_whenSensorIDIsValid() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";
    String sensorIDValue = "SensorID";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = new SensorID(sensorIDValue);

    // Act
    SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName,
        sensorID);

    // Assert
    assertNotNull(switchSensor);
  }

  /**
   * Tests the instantiation of SwitchSensor when the sensorID is null.
   */
  @Test
  void shouldThrowException_WhenSensorIDIsNull() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);
    SensorID sensorID = null;

    String expectedMessage = "SensorID is required";

    //Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);
    });

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  @Test
  void shouldAcceptVisitorAndReturnInstanceOfObjectInString() {
    // Arrange
    String deviceIDValue = "deviceID";
    String modelPathValue = "modelPath";
    String sensorNameValue = "sensorName";
    String sensorTypeIDValue = "Switch";

    DeviceID deviceID = new DeviceID(deviceIDValue);
    ModelPath modelPath = new ModelPath(modelPathValue);
    SensorName sensorName = new SensorName(sensorNameValue);
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeIDValue);

    SwitchSensor switchSensor = new SwitchSensor(deviceID, modelPath, sensorTypeID, sensorName);

    ISensorVisitor visitor = mock(ISensorVisitor.class);

    String expected = switchSensor.toString();
    // Act

    String result = switchSensor.accept(visitor);
    // Assert
    assertEquals(expected, result);
  }
}
