/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator.set_integer_actuator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smarthome.domain.value_object.ActuatorID;
import smarthome.domain.value_object.ActuatorName;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.IntegerLimits;
import smarthome.domain.value_object.ModelPath;
import smarthome.persistence.data_model.ActuatorDataModel;
import smarthome.utils.visitor_pattern.ActuatorVisitorForDataModelImpl;
import smarthome.utils.visitor_pattern.IActuatorVisitor;

class SetIntegerActuatorTest {

  /**
   * Test to verify that the SetIntegerActuator class can be instantiated when the constructor
   * arguments are valid.
   */
  @Test
  void shouldCreateSetIntegerActuator_WhenConstructorArgumentsAreValid() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger");
    IntegerLimits limits = mock(IntegerLimits.class);

    // Act
    SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID,
        actuatorName, limits);

    // Assert
    assertNotNull(actuator);
  }

  /**
   * Test the second constructor.
   */
  @Test
  void shouldCreateSetIntegerActuator_WhenSecondConstructorArgumentsAreValid() {
    // Arrange
    ActuatorID actuatorID = mock(ActuatorID.class);
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger");
    IntegerLimits limits = mock(IntegerLimits.class);

    // Act
    SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID,
        actuatorName, limits, actuatorID);

    // Assert
    assertNotNull(actuator);
  }

  /**
   * Test for invalid actuatorID
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenActuatorIDIsNull() {
    // Arrange
    ActuatorID actuatorID = null;
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger"); // Ensure getID() does not return null
    IntegerLimits limits = mock(IntegerLimits.class);
    String expectedMessage = "ActuatorID is required";

    // Act & Assert
    Exception exception = org.junit.jupiter.api.Assertions.assertThrows(Exception.class, () -> {
      new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits,
          actuatorID);
    });

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the
   * deviceID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDeviceIDIsNull() {
    // Arrange
    DeviceID deviceID = null;
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger");
    IntegerLimits limits = mock(IntegerLimits.class);
    String expectedMessage = "DeviceID is required";

    // Act & Assert
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class, () -> {
          new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the
   * deviceID is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDeviceIDIsNullForSecondConstructor() {
    // Arrange
    DeviceID deviceID = null;
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger");
    IntegerLimits limits = mock(IntegerLimits.class);
    String expectedMessage = "DeviceID is required";
    ActuatorID actuatorID = mock(ActuatorID.class);

    // Act & Assert
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class, () -> {
          new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits,
              actuatorID);
        });

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the
   * modelPath is null.
   */
  @Test
  void testSetIntegerActuatorThrowsIllegalArgumentException_WhenModelPathIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = null;
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    IntegerLimits limits = mock(IntegerLimits.class);
    String expectedMessage = "ModelPath is required";

    // Act & Assert
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class, () -> {
          new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the
   * modelPath is null.
   */
  @Test
  void testSetIntegerActuatorThrowsIllegalArgumentException_WhenModelPathIsNullForSecondConstructor() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = null;
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    IntegerLimits limits = mock(IntegerLimits.class);
    String expectedMessage = "ModelPath is required";
    ActuatorID actuatorID = mock(ActuatorID.class);

    // Act & Assert
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class, () -> {
          new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits,
              actuatorID);
        });

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the
   * actuatorName is null.
   */
  @Test
  void testSetIntegerActuatorThrowsIllegalArgumentException_WhenActuatorNameIsNullForSecondConstructor() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = null;
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    IntegerLimits limits = mock(IntegerLimits.class);
    String expectedMessage = "ActuatorName is required";
    ActuatorID actuatorID = mock(ActuatorID.class);

    // Act & Assert
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class, () -> {
          new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits,
              actuatorID);
        });

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the
   * actuatorName is null.
   */
  @Test
  void testSetIntegerActuatorThrowsIllegalArgumentException_WhenActuatorNameIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = null;
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    IntegerLimits limits = mock(IntegerLimits.class);
    String expectedMessage = "ActuatorName is required";

    // Act & Assert
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class, () -> {
          new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the
   * actuatorTypeID is null.
   */
  @Test
  void testSetIntegerActuatorThrowsIllegalArgumentException_WhenActuatorTypeIDIsNullForSecondConstructor() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = null;
    IntegerLimits limits = mock(IntegerLimits.class);
    String expectedMessage = "ActuatorTypeID is required";
    ActuatorID actuatorID = mock(ActuatorID.class);

    // Act & Assert
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class, () -> {
          new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits,
              actuatorID);
        });

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the
   * actuatorTypeID is null.
   */
  @Test
  void testSetIntegerActuatorThrowsIllegalArgumentException_WhenActuatorTypeIDIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = null;
    IntegerLimits limits = mock(IntegerLimits.class);
    String expectedMessage = "ActuatorTypeID is required";

    // Act & Assert
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class, () -> {
          new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the
   * actuatorTypeID is not SetInteger.
   */
  @Test
  void testSetIntegerActuatorThrowsIllegalArgumentException_WhenActuatorTypeIDIsNotSetInteger() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("NotSetInteger");
    IntegerLimits limits = mock(IntegerLimits.class);
    String expectedMessage = "ActuatorTypeID must be SetInteger";

    // Act & Assert
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class, () -> {
          new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the
   * actuatorTypeID is not SetInteger.
   */
  @Test
  void testSetIntegerActuatorThrowsIllegalArgumentException_WhenActuatorTypeIDIsNotSetIntegerForSecondConstructor() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("NotSetInteger");
    IntegerLimits limits = mock(IntegerLimits.class);
    String expectedMessage = "ActuatorTypeID must be SetInteger";
    ActuatorID actuatorID = mock(ActuatorID.class);

    // Act & Assert
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class, () -> {
          new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits,
              actuatorID);
        });

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the
   * limits is null.
   */
  @Test
  void testSetIntegerActuatorThrowsIllegalArgumentException_WhenLimitsIsNullForSecondConstructor() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger");
    IntegerLimits limits = null;
    String expectedMessage = "SetIntegerActuatorLimits is required";
    ActuatorID actuatorID = mock(ActuatorID.class);

    // Act & Assert
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class, () -> {
          new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits,
              actuatorID);
        });

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to verify that the SetIntegerActuator class throws an IllegalArgumentException when the
   * limits is null.
   */
  @Test
  void testSetIntegerActuatorThrowsIllegalArgumentException_WhenLimitsIsNull() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger");
    IntegerLimits limits = null;
    String expectedMessage = "SetIntegerActuatorLimits is required";

    // Act & Assert
    IllegalArgumentException exception = org.junit.jupiter.api.Assertions.assertThrows(
        IllegalArgumentException.class, () -> {
          new SetIntegerActuator(deviceID, modelPath, actuatorTypeID, actuatorName, limits);
        });

    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test to get the actuator ID.
   */
  @Test
  void shouldReturnActuatorID_WhenGetIDisCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger");
    IntegerLimits limits = mock(IntegerLimits.class);
    SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID,
        actuatorName, limits);

    // Act
    ActuatorID actuatorID = actuator.getID();

    // Assert
    assertTrue(actuator.toString().contains(actuatorID.toString()));
  }

  /**
   * Test to get the actuator name.
   */
  @Test
  void shouldReturnActuatorName_WhenGetNameIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger");
    IntegerLimits limits = mock(IntegerLimits.class);
    SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID,
        actuatorName, limits);

    // Act
    ActuatorName name = actuator.getName();

    // Assert
    assertEquals(actuatorName, name);
  }

  /**
   * Test to get the model path.
   */
  @Test
  void shouldReturnModelPath_WhenGetModelPathIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger");
    IntegerLimits limits = mock(IntegerLimits.class);
    SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID,
        actuatorName, limits);

    // Act
    ModelPath path = actuator.getModelPath();

    // Assert
    assertEquals(modelPath, path);
  }

  /**
   * Test to get the actuator type ID.
   */
  @Test
  void shouldReturnActuatorTypeID_WhenGetActuatorTypeIDIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger");
    IntegerLimits limits = mock(IntegerLimits.class);
    SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID,
        actuatorName, limits);

    // Act
    ActuatorTypeID typeID = actuator.getActuatorTypeID();

    // Assert
    assertEquals(actuatorTypeID, typeID);
  }

  /**
   * Test to get the device ID.
   */
  @Test
  void shouldReturnDeviceID_WhenGetDeviceIDIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger");
    IntegerLimits limits = mock(IntegerLimits.class);
    SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID,
        actuatorName, limits);

    // Act
    DeviceID id = actuator.getDeviceID();

    // Assert
    assertEquals(deviceID, id);
  }

  /**
   * Test to get the limits.
   */
  @Test
  void shouldReturnLimits_WhenGetLimitsIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger");
    IntegerLimits limits = mock(IntegerLimits.class);
    SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID,
        actuatorName, limits);

    // Act
    IntegerLimits actuatorLimits = actuator.getLimits();

    // Assert
    assertEquals(limits, actuatorLimits);
  }

  /**
   * Test method equals when the instance is compared to itself.
   */
  @Test
  void shouldReturnTrue_WhenTheInstanceIsComparedToItself() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger");
    IntegerLimits limits = mock(IntegerLimits.class);
    SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID,
        actuatorName, limits);

    // Act
    boolean result = actuator.equals(actuator);

    // Assert
    assertTrue(result);
  }

  /**
   * Test of method equals when the instances are not equal.
   */
  @Test
  void shouldReturnFalse_whenInstancesAreNotEqual() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger");
    IntegerLimits limits = mock(IntegerLimits.class);
    SetIntegerActuator actuator1 = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID,
        actuatorName, limits);
    SetIntegerActuator actuator2 = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID,
        actuatorName, limits);

    // Act
    boolean result = actuator1.equals(actuator2);

    // Assert
    assertFalse(result);
  }

  /**
   * Test of method equals when the instance is compared to an object of a different class.
   */
  @Test
  void shouldReturnFalse_whenInstanceIsComparedToAnObjectOfDifferentClass() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger");
    IntegerLimits limits = mock(IntegerLimits.class);
    SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID,
        actuatorName, limits);

    // Act
    boolean result = actuator.equals(new Object());

    // Assert
    assertFalse(result);
  }

  /**
   * Test of method toString.
   */
  @Test
  void shouldReturnString_WhenToStringIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger");
    IntegerLimits limits = mock(IntegerLimits.class);

    try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class,
        (mock, context) -> {
          when(mock.toString()).thenReturn("ActuatorID");
        })) {
      SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID,
          actuatorName, limits);

      //Act
      String result = actuator.toString();

      //Assert
      assertTrue(result.contains("ActuatorID"));
    }
  }

  /**
   * Test of method hashcode.
   */
  @Test
  void shouldReturnHashCode_WhenHashCodeIsCalled() {
    // Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger");
    IntegerLimits limits = mock(IntegerLimits.class);

    try (MockedConstruction<ActuatorID> mocked = mockConstruction(ActuatorID.class,
        (mock, context) -> {
          when(mock.toString()).thenReturn("1");
        })) {
      SetIntegerActuator actuator = new SetIntegerActuator(deviceID, modelPath, actuatorTypeID,
          actuatorName, limits);

      int expected = actuator.getID().hashCode();

      //Act
      int result = actuator.hashCode();

      //Assert
      assertEquals(expected, result);
    }
  }

  /**
   * Test of accept method, of class {@link SetIntegerActuator}.
   */
  @Test
  void shouldReturnString_WhenAcceptIsCalled() {
    //Arrange
    DeviceID deviceID = mock(DeviceID.class);
    ModelPath modelPath = mock(ModelPath.class);
    ActuatorName actuatorName = mock(ActuatorName.class);
    ActuatorTypeID actuatorTypeID = mock(ActuatorTypeID.class);
    when(actuatorTypeID.getID()).thenReturn("SetInteger");

    IntegerLimits limits = mock(IntegerLimits.class);

    SetIntegerActuator setIntegerActuator = new SetIntegerActuator(deviceID, modelPath,
        actuatorTypeID, actuatorName, limits);

    ActuatorDataModel actuatorDataModel = mock(ActuatorDataModel.class);

    IActuatorVisitor visitor = new ActuatorVisitorForDataModelImpl(actuatorDataModel);

    String expected = setIntegerActuator.toString();

    //Act
    String result = setIntegerActuator.accept(visitor);

    //Assert
    assertEquals(expected, result);
  }
}
