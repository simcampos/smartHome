/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.jpa.data_model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import smarthome.domain.actuator_type.ActuatorType;
import smarthome.domain.value_object.ActuatorTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.persistence.data_model.ActuatorTypeDataModel;

class ActuatorTypeDataModelTest {

  /**
   * Test for the empty constructor of the {@link ActuatorTypeDataModel} class.
   */
  @Test
  void shouldInstantiateActuatorTypeDataModel_whenEmptyConstructorIsCalled() {
    // Arrange
    ActuatorTypeDataModel actuatorTypeDataModel = new ActuatorTypeDataModel();

    // Act
    assertNotNull(actuatorTypeDataModel);
  }

  /**
   * Test for the constructor of the {@link ActuatorTypeDataModel} class.
   */
  @Test
  void shouldInstantiateActuatorTypeDataModel_WhenAttributesAreValid() {
    //Arrange
    String strActuatorTypeID = "123";
    String strTypeDescription = "DewPoint";
    String strUnitID = "Celsius";

    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    UnitID unitIDDouble = mock(UnitID.class);
    ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

    when(actuatorTypeIDDouble.getID()).thenReturn(strActuatorTypeID);
    when(typeDescriptionDouble.getID()).thenReturn(strTypeDescription);
    when(unitIDDouble.getID()).thenReturn(strUnitID);

    when(actuatorTypeDouble.getID()).thenReturn(actuatorTypeIDDouble);
    when(actuatorTypeDouble.getActuatorTypeName()).thenReturn(typeDescriptionDouble);
    when(actuatorTypeDouble.getUnit()).thenReturn(unitIDDouble);

    //Act
    ActuatorTypeDataModel actuatorTypeDataModel = new ActuatorTypeDataModel(actuatorTypeDouble);

    //Assert
    assertNotNull(actuatorTypeDataModel);
  }

  /**
   * Test for the constructor of the {@link ActuatorTypeDataModel} class when actuatorType is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenActuatorTypeIsNull() {
    //Arrange
    ActuatorType actuatorTypeDouble = null;

    String expectedMessage = "Actuator Type is required";

    //Act & Assert
    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
        () -> new ActuatorTypeDataModel(actuatorTypeDouble));

    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  /**
   * Test for the getActuatorTypeID method of the {@link ActuatorTypeDataModel} class.
   */
  @Test
  void shouldReturnActuatorTypeID_WhenGetActuatorTypeID() {
    //Arrange
    String strActuatorTypeID = "123";
    String strTypeDescription = "DewPoint";
    String strUnitID = "Celsius";

    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    UnitID unitIDDouble = mock(UnitID.class);
    ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

    when(actuatorTypeIDDouble.getID()).thenReturn(strActuatorTypeID);
    when(typeDescriptionDouble.getID()).thenReturn(strTypeDescription);
    when(unitIDDouble.getID()).thenReturn(strUnitID);

    when(actuatorTypeDouble.getID()).thenReturn(actuatorTypeIDDouble);
    when(actuatorTypeDouble.getActuatorTypeName()).thenReturn(typeDescriptionDouble);
    when(actuatorTypeDouble.getUnit()).thenReturn(unitIDDouble);

    ActuatorTypeDataModel actuatorTypeDataModel = new ActuatorTypeDataModel(actuatorTypeDouble);

    //Act
    String result = actuatorTypeDataModel.getActuatorTypeID();

    //Assert
    assertEquals(strActuatorTypeID, result);
  }

  /**
   * Test for the getActuatorTypeName method of the {@link ActuatorTypeDataModel} class.
   */
  @Test
  void shouldReturnActuatorTypeName_WhenGetActuatorTypeName() {
    //Arrange
    String strActuatorTypeID = "123";
    String strTypeDescription = "DewPoint";
    String strUnitID = "Celsius";

    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    UnitID unitIDDouble = mock(UnitID.class);
    ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

    when(actuatorTypeIDDouble.getID()).thenReturn(strActuatorTypeID);
    when(typeDescriptionDouble.toString()).thenReturn(strTypeDescription);
    when(unitIDDouble.getID()).thenReturn(strUnitID);

    when(actuatorTypeDouble.getID()).thenReturn(actuatorTypeIDDouble);
    when(actuatorTypeDouble.getActuatorTypeName()).thenReturn(typeDescriptionDouble);
    when(actuatorTypeDouble.getUnit()).thenReturn(unitIDDouble);

    ActuatorTypeDataModel actuatorTypeDataModel = new ActuatorTypeDataModel(actuatorTypeDouble);

    //Act
    String result = actuatorTypeDataModel.getActuatorTypeName();

    //Assert
    assertEquals(strTypeDescription, result);
  }

  /**
   * Test for the getUnitID method of the {@link ActuatorTypeDataModel} class.
   */
  @Test
  void shouldReturnUnitID_WhenGetUnitID() {
    //Arrange
    String strActuatorTypeID = "123";
    String strTypeDescription = "DewPoint";
    String strUnitID = "Celsius";

    ActuatorTypeID actuatorTypeIDDouble = mock(ActuatorTypeID.class);
    TypeDescription typeDescriptionDouble = mock(TypeDescription.class);
    UnitID unitIDDouble = mock(UnitID.class);
    ActuatorType actuatorTypeDouble = mock(ActuatorType.class);

    when(actuatorTypeIDDouble.getID()).thenReturn(strActuatorTypeID);
    when(typeDescriptionDouble.getID()).thenReturn(strTypeDescription);
    when(unitIDDouble.getID()).thenReturn(strUnitID);

    when(actuatorTypeDouble.getID()).thenReturn(actuatorTypeIDDouble);
    when(actuatorTypeDouble.getActuatorTypeName()).thenReturn(typeDescriptionDouble);
    when(actuatorTypeDouble.getUnit()).thenReturn(unitIDDouble);

    ActuatorTypeDataModel actuatorTypeDataModel = new ActuatorTypeDataModel(actuatorTypeDouble);

    //Act
    String result = actuatorTypeDataModel.getUnitID();

    //Assert
    assertEquals(strUnitID, result);
  }
}