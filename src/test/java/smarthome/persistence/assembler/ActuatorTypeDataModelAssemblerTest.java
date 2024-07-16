/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.assembler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.actuator_type.IActuatorTypeFactory;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.persistence.data_model.ActuatorTypeDataModel;

class ActuatorTypeDataModelAssemblerTest {

  /**
   * Test to ensure that an exception is thrown when the ActuatorTypeFactory is null.
   */
  @Test
  void shouldThrowException_whenActuatorTypeFactoryIsNull() {
    // Arrange
    IActuatorTypeFactory actuatorTypeFactory = null;

    String expected = "Actuator Type Factory is required";

    // Act
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> new ActuatorTypeDataModelAssembler(actuatorTypeFactory));

    // Assert
    String actual = exception.getMessage();

    assertEquals(expected, actual);
  }

  /**
   * Test to ensure that an ActuatorTypeDataModelAssembler can be instantiated successfully.
   */
  @Test
  void shouldInstantiateActuatorTypeDataModelAssembler_whenActuatorTypeFactoryIsValid() {
    // Arrange
    IActuatorTypeFactory actuatorTypeFactory = mock(IActuatorTypeFactory.class);

    // Act
    ActuatorTypeDataModelAssembler actuatorTypeDataModelAssembler = new ActuatorTypeDataModelAssembler(
        actuatorTypeFactory);

    // Assert
    assertNotNull(actuatorTypeDataModelAssembler);
  }

  /**
   * Test to ensure that an ActuatorType can be converted to a DataModel successfully.
   */
  @Test
  void shouldConvertActuatorTypeDataModelToDomain_WhenActuatorTypeDataModelIsValid() {
    // Arrange
    String actuatorTypeID = "1";
    String actuatorTypeName = "Temperature";
    String unitID = "Celsius";

    ActuatorTypeDataModel actuatorTypeDataModelDouble = mock(ActuatorTypeDataModel.class);
    when(actuatorTypeDataModelDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
    when(actuatorTypeDataModelDouble.getActuatorTypeName()).thenReturn(actuatorTypeName);
    when(actuatorTypeDataModelDouble.getUnitID()).thenReturn(unitID);

    IActuatorTypeFactory actuatorTypeFactory = mock(IActuatorTypeFactory.class);
    ActuatorType expected = new ActuatorType(new TypeDescription(actuatorTypeName),
        new UnitID(unitID), new ActuatorTypeID(actuatorTypeID));

    when(actuatorTypeFactory.createActuatorType(any(TypeDescription.class), any(UnitID.class),
        any(ActuatorTypeID.class)))
        .thenReturn(expected);

    ActuatorTypeDataModelAssembler actuatorTypeDataModelAssembler = new ActuatorTypeDataModelAssembler(
        actuatorTypeFactory);

    // Act
    ActuatorType result = actuatorTypeDataModelAssembler.toDomain(actuatorTypeDataModelDouble);

    // Assert
    assertEquals(expected, result);
  }

  /**
   * Test to ensure that a list of two ActuatorTypeDataModels can be converted to a list of
   * ActuatorTypes successfully.
   */
  @Test
  void shouldConvertListOfActuatorTypeDataModelsToDomain_WhenListOfActuatorTypeDataModelsIsValid() {
    // Arrange
    String actuatorTypeID = "1";
    String actuatorTypeName = "Temperature";
    String unitID = "Celsius";

    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    TypeDescription actuatorTypeNameDouble = mock(TypeDescription.class);
    UnitID unitIDDouble = mock(UnitID.class);

    ActuatorTypeDataModel actuatorTypeDataModelDouble = mock(ActuatorTypeDataModel.class);

    when(actuatorTypeDataModelDouble.getActuatorTypeID()).thenReturn(actuatorTypeID);
    when(actuatorTypeDataModelDouble.getActuatorTypeName()).thenReturn(actuatorTypeName);
    when(actuatorTypeDataModelDouble.getUnitID()).thenReturn(unitID);

    List<ActuatorTypeDataModel> actuatorTypeDataModels = List.of(actuatorTypeDataModelDouble,
        actuatorTypeDataModelDouble);

    IActuatorTypeFactory actuatorTypeFactory = mock(IActuatorTypeFactory.class);
    ActuatorTypeDataModelAssembler actuatorTypeDataModelAssembler = new ActuatorTypeDataModelAssembler(
        actuatorTypeFactory);

    ActuatorType expected = actuatorTypeFactory.createActuatorType(actuatorTypeNameDouble,
        unitIDDouble, actuatorTypeIDDouble);

    // Act
    List<ActuatorType> result = actuatorTypeDataModelAssembler.toDomain(actuatorTypeDataModels);

    // Assert
    assertEquals(2, result.size());
    assertEquals(expected, result.get(0));
    assertEquals(expected, result.get(1));
    assertEquals(actuatorTypeDataModels.size(), result.size());
  }
}