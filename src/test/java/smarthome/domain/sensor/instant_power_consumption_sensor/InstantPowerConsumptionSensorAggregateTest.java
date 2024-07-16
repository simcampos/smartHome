/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor.instant_power_consumption_sensor;

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

class InstantPowerConsumptionSensorAggregateTest {

  /**
   * Test to verify that the InstantPowerConsumptionSensor is instantiated when all parameters are
   * valid.
   */
  @Test
  void shouldInstantiateInstantPowerConsumptionSensor_WhenAllParametersAreValid() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    // act
    InstantPowerConsumptionSensor instantPowerConsumptionSensor =
        new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // assert
    assertNotNull(instantPowerConsumptionSensor);
  }


  /**
   * Test to verify that the InstantPowerConsumptionSensor throws an exception when the model path
   * is null.
   */
  @Test
  void shouldThrowException_WhenDeviceIDIsNull() {
    // Arrange
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";

    DeviceID deviceID = null;
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    String expectedMessage = "DeviceID is required";

    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
            });

    String actualMessage = exception.getMessage();

    // assert
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Test to verify that the InstantPowerConsumptionSensor throws an exception when the model path
   * is null.
   */
  @Test
  void shouldThrowException_WhenModelPathIsNull() {
    // Arrange
    String deviceIDName = "123B";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = null;
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    String expectedMessage = "ModelPath is required";

    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
            });

    String actualMessage = exception.getMessage();

    // assert
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Test to verify that the InstantPowerConsumptionSensor throws an exception when the sensor name
   * is null.
   */
  @Test
  void shouldThrowException_WhenSensorNameIsNull() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    String expectedMessage = "SensorName is required";

    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
            });

    String actualMessage = exception.getMessage();

    // assert
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Test to verify that the InstantPowerConsumptionSensor throws an exception when the sensor type
   * ID is null.
   */
  @Test
  void shouldThrowException_WhenSensorTypeIDIsNull() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = null;

    String expectedMessage = "SensorTypeID is required";

    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
            });

    String actualMessage = exception.getMessage();

    // assert
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void shouldThrowException_WhenSensorTypeIsNotCorrect() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "Temperature";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    String expectedMessage = "SensorTypeID must be of type 'InstantPowerConsumption'";

    // Act + Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
            });

    String actualMessage = exception.getMessage();

    // assert
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Test to verify that the InstantPowerConsumptionSensor generates an ID when the sensor is
   * instantiated.
   */
  @Test
  void shouldGenerateInstantPowerConsumptionID_WhenInstantPowerConsumptionSensorIsInstantiated() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    InstantPowerConsumptionSensor instantPowerConsumptionSensor =
        new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    SensorID result = instantPowerConsumptionSensor.getID();

    // Assert
    assertNotNull(result);
  }

  /**
   * Test to verify that the InstantPowerConsumptionSensor returns the sensor name.
   */
  @Test
  void shouldReturnSensorName_WhenGetSensorNameIsCalled() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    InstantPowerConsumptionSensor instantPowerConsumptionSensor =
        new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    String expectedName = sensorName.toString();

    // Act
    SensorName result = instantPowerConsumptionSensor.getSensorName();

    // Assert
    assertEquals(expectedName, result.toString());
  }

  /**
   * Test to verify that the InstantPowerConsumptionSensor returns the model path.
   */
  @Test
  void shouldGetInstantPowerConsumptionModelPath() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    String expectedModelPath = modelPath.toString();

    InstantPowerConsumptionSensor instantPowerConsumptionSensor =
        new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    ModelPath result = instantPowerConsumptionSensor.getModelPath();

    // Assert
    assertEquals(expectedModelPath, result.toString());
  }

  /**
   * Test to verify that the InstantPowerConsumptionSensor returns the sensor type ID.
   */
  @Test
  void shouldGetInstantPowerConsumptionSensorTypeID() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    String expectedSensorTypeID = sensorTypeID.toString();

    InstantPowerConsumptionSensor instantPowerConsumptionSensor =
        new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    SensorTypeID result = instantPowerConsumptionSensor.getSensorTypeID();

    // Assert
    assertEquals(expectedSensorTypeID, result.toString());
  }

  @Test
  void shouldGetDeviceID() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    String expectedDeviceID = deviceID.toString();

    InstantPowerConsumptionSensor instantPowerConsumptionSensor =
        new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    DeviceID result = instantPowerConsumptionSensor.getDeviceID();

    // Assert
    assertEquals(expectedDeviceID, result.toString());
  }

  @Test
  void shouldGetInstantPowerConsumptionValue() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    InstantPowerConsumptionSensor instantPowerConsumptionSensor =
        new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    InstantPowerConsumptionValue result = instantPowerConsumptionSensor.getValue();

    // Assert
    double value = Double.parseDouble(result.toString());
    assertTrue(value > 0 && value <= 100);
  }

  /**
   * Tests if the equals method returns true when the instances are the same object.
   */
  @Test
  void testEquals_SameObject() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    InstantPowerConsumptionSensor instantPowerConsumptionSensor =
        new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    boolean result = instantPowerConsumptionSensor.equals(instantPowerConsumptionSensor);

    // Assert
    assertTrue(result);
  }

  /**
   * Tests if the equals method returns false when the objects are not the same.
   */
  @Test
  void testEquals_DifferentObject() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    InstantPowerConsumptionSensor instantPowerConsumptionSensor =
        new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
    InstantPowerConsumptionSensor instantPowerConsumptionSensor2 =
        new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    boolean result = instantPowerConsumptionSensor.equals(instantPowerConsumptionSensor2);

    // Assert
    assertFalse(result);
  }

  /**
   * Tests if the equals method returns false when the object is not an instance of SwitchSensor.
   */
  @Test
  void testEquals_NotInstanceOfSwitchSensor() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    InstantPowerConsumptionSensor instantPowerConsumptionSensor =
        new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    // Act
    boolean result = instantPowerConsumptionSensor.equals(new Object());

    // Assert
    assertFalse(result);
  }

  /**
   * Tests if the toString method returns the SwitchSensor in string format.
   */
  @Test
  void testToString() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    InstantPowerConsumptionSensor instantPowerConsumptionSensor =
        new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    String expected = "InstantPowerConsumptionSensor: " +
        "modelPath=" + modelPath +
        ", sensorName=" + sensorName +
        ", sensorID=" + instantPowerConsumptionSensor.getID() +
        ", sensorTypeID=" + sensorTypeID +
        ", deviceID=" + deviceID;

    // Act
    String result = instantPowerConsumptionSensor.toString();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Tests if the hashCode method returns the hash code of the SwitchSensor.
   */
  @Test
  void testHashCode() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    InstantPowerConsumptionSensor instantPowerConsumptionSensor =
        new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);

    int expected = instantPowerConsumptionSensor.getID().hashCode();

    // Act
    int result = instantPowerConsumptionSensor.hashCode();

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Test to verify that the InstantPowerConsumptionSensor is instantiated when all parameters are
   * valid, including SensorID.
   */
  @Test
  void shouldInstantiateInstantPowerConsumptionSensor_WhenAllParametersAreValidIncludingSensorID() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";
    String sensorIDName = "1234";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);
    SensorID sensorID = new SensorID(sensorIDName);

    // act
    InstantPowerConsumptionSensor instantPowerConsumptionSensor =
        new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);

    // assert
    assertNotNull(instantPowerConsumptionSensor);
  }

  /**
   * Test to verify that the InstantPowerConsumptionSensor throws an exception when the sensor ID is
   * null
   */
  @Test
  void shouldThrowException_WhenSensorIDIsNull() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";
    SensorID sensorID = null;

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    String expectedMessage = "SensorID is required";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);
    });

    // assert
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Test to verify that the InstantPowerConsumptionSensor throws an exception when the sensor type
   * is null Whit valid sensor ID.
   */
  @Test
  void shouldThrowExceptionWhitValidSensorID_WhenSensorTypeIDisNull() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String sensorIDName = "1234";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorID sensorID = new SensorID(sensorIDName);
    SensorTypeID sensorTypeID = null;

    String expectedMessage = "SensorTypeID is required";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);
    });
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Test to verify that the InstantPowerConsumptionSensor throws an exception when the sensor name
   * is null Whit valid sensor ID.
   */
  @Test
  void shouldThrowExceptionWhitValidSensorID_WhenSensorNameIsNull() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";
    String sensorIDName = "1234";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorID sensorID = new SensorID(sensorIDName);
    SensorName sensorName = null;
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    String expectedMessage = "SensorName is required";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);
    });
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Test to verify that the InstantPowerConsumptionSensor throws an exception when the model path
   * is null Whit valid sensor ID.
   */
  @Test
  void shouldThrowExceptionWhitValidSensorID_WhenModelPathIsNull() {
    // Arrange
    String deviceIDName = "123B";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";
    String sensorIDName = "1234";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = null;
    SensorID sensorID = new SensorID(sensorIDName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    String expectedMessage = "ModelPath is required";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);
    });
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Test to verify that the InstantPowerConsumptionSensor throws an exception when the device ID is
   * null Whit valid sensor ID.
   */
  @Test
  void shouldThrowExceptionWhitValidSensorID_WhenDeviceIDIsNull() {
    // Arrange
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";
    String sensorIDName = "1234";

    DeviceID deviceID = null;
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorID sensorID = new SensorID(sensorIDName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    String expectedMessage = "DeviceID is required";

    // Act + Assert
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName, sensorID);
    });
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void shouldAcceptVisitorAndReturnInstanceOfObjectInString() {
    // Arrange
    String deviceIDName = "123B";
    String modelPathName = "SmartHome.sensors.InstantPowerConsumptionSensor";
    String name = "InstantPowerConsumptionSensor";
    String typeID = "InstantPowerConsumption";

    DeviceID deviceID = new DeviceID(deviceIDName);
    ModelPath modelPath = new ModelPath(modelPathName);
    SensorName sensorName = new SensorName(name);
    SensorTypeID sensorTypeID = new SensorTypeID(typeID);

    InstantPowerConsumptionSensor instantPowerConsumptionSensor =
        new InstantPowerConsumptionSensor(deviceID, modelPath, sensorTypeID, sensorName);
    String expected = instantPowerConsumptionSensor.toString();
    ISensorVisitor mockVisitor = mock(ISensorVisitor.class);
    // act
    String result = instantPowerConsumptionSensor.accept(mockVisitor);
    // assert
    assertEquals(expected, result);
  }

}
