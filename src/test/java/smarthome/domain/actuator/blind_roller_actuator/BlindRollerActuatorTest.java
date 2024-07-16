/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator.blind_roller_actuator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smarthome.domain.actuator.switch_actuator.SwitchActuatorValue;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.ModelPath;
import smarthome.persistence.data_model.ActuatorDataModel;
import smarthome.utils.visitor_pattern.ActuatorVisitorForDataModelImpl;
import smarthome.utils.visitor_pattern.IActuatorVisitor;

class BlindRollerActuatorTest {

  /**
   * Test the first constructor, of class BlindRollerActuator.
   */
  @Test
  void shouldCreateBlindRollerActuator_WhenConstructorArgumentsAreValid() {
    // Arrange
    DeviceID deviceIDDouble = mock(DeviceID.class);
    ModelPath modelPathDouble = mock(ModelPath.class);
    ActuatorName actuatorNameDouble = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    when(actuatorTypeIDDouble.getID()).thenReturn("BlindRoller");

    try (MockedConstruction<ActuatorID> mocked =
        mockConstruction(
            ActuatorID.class,
            (mock, context) -> {
              when(mock.getID()).thenReturn("123");
            })) {
      // Act
      BlindRollerActuator blindRollerActuator =
          new BlindRollerActuator(
              deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

      // Assert
      assertNotNull(blindRollerActuator);
    }
  }

  /**
   * Test the second constructor, of class BlindRollerActuator.
   */
  @Test
  void shouldCreateBlindRollerActuator_WhenSecondConstructorArgumentsAreValid() {
    // Arrange
    DeviceID deviceIDDouble = mock(DeviceID.class);
    ModelPath modelPathDouble = mock(ModelPath.class);
    ActuatorName actuatorNameDouble = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    ActuatorID actuatorIDDouble = mock(ActuatorID.class);

    when(actuatorTypeIDDouble.getID()).thenReturn("BlindRoller");

    try (MockedConstruction<ActuatorID> mocked =
        mockConstruction(
            ActuatorID.class,
            (mock, context) -> {
              when(mock.getID()).thenReturn("123");
            })) {
      // Act
      BlindRollerActuator blindRollerActuator =
          new BlindRollerActuator(
              deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble,
              actuatorIDDouble
          );

      // Assert
      assertNotNull(blindRollerActuator);
    }
  }

  /**
   * Test for invalid deviceID
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDeviceIDIsNull() {
    // Arrange
    ModelPath modelPathDouble = mock(ModelPath.class);
    ActuatorName actuatorNameDouble = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);

    String expectedMessage = "DeviceID is required";

    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new BlindRollerActuator(
                  null, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);
            });

    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test for invalid actuatorTypeID
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenActuatorTypeIDIsNull() {
    // Arrange
    DeviceID deviceIDDouble = mock(DeviceID.class);
    ModelPath modelPathDouble = mock(ModelPath.class);
    ActuatorName actuatorNameDouble = mock(ActuatorName.class);

    String expectedMessage = "ActuatorTypeID is required";

    // Act & Assert
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new BlindRollerActuator(deviceIDDouble, modelPathDouble, null, actuatorNameDouble);
            });

    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test for an invalid actuatorTypeID of another type
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenActuatorTypeIDIsNotBlindRoller() {
    // Arrange
    String deviceID = "1";
    String modelPath = "SmartHomeDDD.domain.Actuator.BlindRollerActuator.BlindRollerActuator";
    String actuatorName = "BlindRoller";
    String actuatorTypeID = "2";

    DeviceID deviceIDObject = new DeviceID(deviceID);
    ModelPath modelPathObject = new ModelPath(modelPath);
    ActuatorName actuatorNameObject = new ActuatorName(actuatorName);
    ActuatorTypeID actuatorTypeIDObject = new ActuatorTypeID(actuatorTypeID);

    String expectedMessage = "The value of 'actuatorTypeID' should be 'BlindRoller'.";

    // Act+Assert
    Exception exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> {
              new BlindRollerActuator(
                  deviceIDObject, modelPathObject, actuatorTypeIDObject, actuatorNameObject);
            });

    String actualMessage = exception.getMessage();

    assertEquals(expectedMessage, actualMessage);
  }

  /**
   * Should return SwitchActuatorID when getActuatorID is called.
   */
  @Test
  void shouldReturnActuatorID_WhenGetActuatorIDIsCalled() {
    // Arrange
    DeviceID deviceIDDouble = mock(DeviceID.class);
    ModelPath modelPathDouble = mock(ModelPath.class);
    ActuatorName actuatorNameDouble = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    when(actuatorTypeIDDouble.getID()).thenReturn("BlindRoller");

    try (MockedConstruction<ActuatorID> mocked =
        mockConstruction(
            ActuatorID.class,
            (mock, context) -> {
              when(mock.getID()).thenReturn("123");
            })) {
      BlindRollerActuator blindRollerActuator =
          new BlindRollerActuator(
              deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

      // Act
      ActuatorID actuatorID = blindRollerActuator.getID();

      // Assert
      assertNotNull(actuatorID);
    }
  }

  /**
   * Should return the actuator name when getActuatorName is called.
   */
  @Test
  void shouldReturnActuatorName_WhenGetActuatorNameIsCalled() {
    // Arrange
    DeviceID deviceIDDouble = mock(DeviceID.class);
    ModelPath modelPathDouble = mock(ModelPath.class);
    ActuatorName actuatorNameDouble = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    when(actuatorTypeIDDouble.getID()).thenReturn("BlindRoller");

    try (MockedConstruction<ActuatorID> mocked =
        mockConstruction(
            ActuatorID.class,
            (mock, context) -> {
              when(mock.getID()).thenReturn("123");
            })) {
      BlindRollerActuator blindRollerActuator =
          new BlindRollerActuator(
              deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

      // Act
      ActuatorName actuatorName = blindRollerActuator.getName();

      // Assert
      assertNotNull(actuatorName);
    }
  }

  /**
   * Should return the model path when getModelPath is called.
   */
  @Test
  void shouldReturnModelPath_WhenGetModelPathIsCalled() {
    // Arrange
    DeviceID deviceIDDouble = mock(DeviceID.class);
    ModelPath modelPathDouble = mock(ModelPath.class);
    ActuatorName actuatorNameDouble = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    when(actuatorTypeIDDouble.getID()).thenReturn("BlindRoller");

    try (MockedConstruction<ActuatorID> mocked =
        mockConstruction(
            ActuatorID.class,
            (mock, context) -> {
              when(mock.getID()).thenReturn("123");
            })) {
      BlindRollerActuator blindRollerActuator =
          new BlindRollerActuator(
              deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

      // Act
      ModelPath modelPath = blindRollerActuator.getModelPath();

      // Assert
      assertNotNull(modelPath);
    }
  }

  /**
   * Should return the actuator type ID when getActuatorTypeID is called.
   */
  @Test
  void shouldReturnActuatorTypeID_WhenGetActuatorTypeIDIsCalled() {
    // Arrange
    DeviceID deviceIDDouble = mock(DeviceID.class);
    ModelPath modelPathDouble = mock(ModelPath.class);
    ActuatorName actuatorNameDouble = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    when(actuatorTypeIDDouble.getID()).thenReturn("BlindRoller");

    try (MockedConstruction<ActuatorID> mocked =
        mockConstruction(
            ActuatorID.class,
            (mock, context) -> {
              when(mock.getID()).thenReturn("123");
            })) {
      BlindRollerActuator blindRollerActuator =
          new BlindRollerActuator(
              deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

      // Act
      ActuatorTypeID actuatorTypeID = blindRollerActuator.getActuatorTypeID();

      // Assert
      assertNotNull(actuatorTypeID);
    }
  }

  /**
   * Should set value to true when setValue is called with true.
   */
  @Test
  void shouldSetValueToTrue_WhenSetValueIsCalledWithTrue() {
    // Arrange
    DeviceID deviceIDDouble = mock(DeviceID.class);
    ModelPath modelPathDouble = mock(ModelPath.class);
    ActuatorName actuatorNameDouble = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    when(actuatorTypeIDDouble.getID()).thenReturn("BlindRoller");

    try (MockedConstruction<ActuatorID> mocked =
        mockConstruction(
            ActuatorID.class,
            (mock, context) -> {
              when(mock.getID()).thenReturn("123");
            })) {//Act
      BlindRollerValue value = mock(BlindRollerValue.class);
      when(value.toString()).thenReturn("true");

      BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDDouble,
          modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

      //Assert
      assertNotNull(blindRollerActuator.setValue(value));
    }
  }

  /**
   * Should set value to false when setValue is called with false.
   */
  @Test
  void shouldSetValueToFalse_WhenSetValueIsCalledWithFalse() {
    // Arrange
    DeviceID deviceIDDouble = mock(DeviceID.class);
    ModelPath modelPathDouble = mock(ModelPath.class);
    ActuatorName actuatorNameDouble = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    when(actuatorTypeIDDouble.getID()).thenReturn("BlindRoller");

    try (MockedConstruction<ActuatorID> mocked =
        mockConstruction(
            ActuatorID.class,
            (mock, context) -> {
              when(mock.getID()).thenReturn("123");
            })) {//Act
      BlindRollerValue value = mock(BlindRollerValue.class);
      when(value.toString()).thenReturn("false");

      BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceIDDouble,
          modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

      //Assert
      assertNotNull(blindRollerActuator.setValue(value));
    }
  }

  /**
   * Should return deviceID when getDeviceID is called.
   */
  @Test
  void shouldReturnDeviceID_WhenGetDeviceIDIsCalled() {
    // Arrange
    DeviceID deviceIDDouble = mock(DeviceID.class);
    ModelPath modelPathDouble = mock(ModelPath.class);
    ActuatorName actuatorNameDouble = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    when(actuatorTypeIDDouble.getID()).thenReturn("BlindRoller");

    try (MockedConstruction<ActuatorID> mocked =
        mockConstruction(
            ActuatorID.class,
            (mock, context) -> {
              when(mock.getID()).thenReturn("123");
            })) {
      BlindRollerActuator blindRollerActuator =
          new BlindRollerActuator(
              deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

      // Act
      DeviceID deviceID = blindRollerActuator.getDeviceID();

      // Assert
      assertNotNull(deviceID);
    }
  }

  /**
   * Should return true when instances are same object.
   */
  @Test
  void shouldReturnTrue_WhenInstancesAreSameObject() {
    // Arrange
    DeviceID deviceIDDouble = mock(DeviceID.class);
    ModelPath modelPathDouble = mock(ModelPath.class);
    ActuatorName actuatorNameDouble = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    when(actuatorTypeIDDouble.getID()).thenReturn("BlindRoller");

    try (MockedConstruction<ActuatorID> mocked =
        mockConstruction(
            ActuatorID.class,
            (mock, context) -> {
              when(mock.getID()).thenReturn("123");
            })) {
      BlindRollerActuator blindRollerActuator =
          new BlindRollerActuator(
              deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

      // Act
      boolean result = blindRollerActuator.equals(blindRollerActuator);

      // Assert
      assertTrue(result);
    }
  }

  /**
   * should return false when instances are different objects.
   */
  @Test
  void shouldReturnFalse_WhenInstancesAreDifferentObjects() {
    // Arrange
    DeviceID deviceIDDouble = mock(DeviceID.class);
    ModelPath modelPathDouble = mock(ModelPath.class);
    ActuatorName actuatorNameDouble = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    when(actuatorTypeIDDouble.getID()).thenReturn("BlindRoller");

    try (MockedConstruction<ActuatorID> mocked =
        mockConstruction(
            ActuatorID.class,
            (mock, context) -> {
              when(mock.getID()).thenReturn("123");
            })) {
      BlindRollerActuator blindRollerActuator =
          new BlindRollerActuator(
              deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

      BlindRollerActuator blindRollerActuator2 =
          new BlindRollerActuator(
              deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

      // Act
      boolean result = blindRollerActuator.equals(blindRollerActuator2);

      // Assert
      assertFalse(result);
    }
  }

  /**
   * should return false when an object is not an instance of blind roller actuator.
   */
  @Test
  void shouldReturnFalse_WhenObjectIsNotInstanceOfBlindRollerActuator() {
    // Arrange
    Object object = new Object();

    DeviceID deviceIDDouble = mock(DeviceID.class);
    ModelPath modelPathDouble = mock(ModelPath.class);
    ActuatorName actuatorNameDouble = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    when(actuatorTypeIDDouble.getID()).thenReturn("BlindRoller");

    try (MockedConstruction<ActuatorID> mocked =
        mockConstruction(
            ActuatorID.class,
            (mock, context) -> {
              when(mock.getID()).thenReturn("123");
            })) {
      BlindRollerActuator blindRollerActuator =
          new BlindRollerActuator(
              deviceIDDouble, modelPathDouble, actuatorTypeIDDouble, actuatorNameDouble);

      // Act
      boolean result = blindRollerActuator.equals(object);

      // Assert
      assertFalse(result);
    }
  }

  /**
   * should return actuator hashcode.
   */
  @Test
  void shouldReturnSwitchActuatorHashCode() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);

    when(actuatorTypeID.getID()).thenReturn("BlindRoller");

    try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("123");
        })) {
      BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceID, modelPath,
          actuatorTypeID, actuatorName);

      int expectedHashCode = blindRollerActuator.hashCode();

      //Act
      int hashCode = blindRollerActuator.hashCode();

      //Assert
      assertEquals(expectedHashCode, hashCode);
    }
  }

  /**
   * should return switch actuator in string format.
   */
  @Test
  void shouldReturnSwitchActuatorInStringFormat() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    SwitchActuatorValue switchActuatorValue = mock(SwitchActuatorValue.class);

    when(actuatorTypeID.getID()).thenReturn("BlindRoller");
    when(actuatorTypeID.toString()).thenReturn("BlindRoller");
    when(deviceID.toString()).thenReturn("123");
    when(actuatorName.toString()).thenReturn("BlindRoller Actuator");
    when(modelPath.toString()).thenReturn("modelPath");
    when(switchActuatorValue.toString()).thenReturn("true");

    try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class,
        (mock, context) -> {
          when(mock.getID()).thenReturn("123");
          when(mock.toString()).thenReturn("123");
        })) {
      BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceID, modelPath,
          actuatorTypeID, actuatorName);
      blindRollerActuator.setValue(switchActuatorValue);

      String expectedString = "BlindRollerActuator:actuatorID=123, deviceID=123, modelPath=modelPath, actuatorTypeID=BlindRoller, actuatorName=BlindRoller Actuator";

      //Act
      String result = blindRollerActuator.toString();

      //Assert
      assertEquals(expectedString, result);
    }
  }


  /**
   * Test of accept method, of class BlindRollerActuator.
   */
  @Test
  void shouldReturnString_WhenAcceptIsCalled() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("BlindRoller");

    ActuatorDataModel actuatorDataModel = mock(ActuatorDataModel.class);
    BlindRollerActuator blindRollerActuator = new BlindRollerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName);

    IActuatorVisitor visitor = new ActuatorVisitorForDataModelImpl(actuatorDataModel);

    String expected = blindRollerActuator.toString();

    //Act
    String result = blindRollerActuator.accept(visitor);

    //Assert
    assertEquals(expected, result);
  }
}
