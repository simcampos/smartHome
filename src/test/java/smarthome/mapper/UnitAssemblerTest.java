/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitID;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.utils.dto.UnitDTO;

class UnitAssemblerTest {

  /**
   * Test if the constructor of the UnitAssembler class can be called.
   */
  @Test
  void shouldInstantiateANewUnitAssembler() {
    // Arrange
    UnitAssembler unitAssembler = new UnitAssembler();

    // Act + Assert
    assertNotNull(unitAssembler);
  }

  /**
   * Test if the domainToDTO method returns a UnitDTO object when the unit is valid.
   */
  @Test
  void shouldReturnunitTypeDTO_WhenDomainToDTOIsCalledWithunitType() {
    // Arrange
    UnitAssembler unitAssembler = new UnitAssembler();

    UnitID unitID = mock(UnitID.class);
    UnitDescription unitDescription = mock(UnitDescription.class);
    Unit unit = mock(Unit.class);
    UnitSymbol unitSymbol = mock(UnitSymbol.class);

    when(unit.getID()).thenReturn(unitID);
    when(unitID.toString()).thenReturn("f47b3b2d-0b7d-4e7b-8b8f-5f3b6d6b1f1d");
    when(unit.getUnitDescription()).thenReturn(unitDescription);
    when(unitDescription.toString()).thenReturn("Celcius");
    when(unit.getUnitSymbol()).thenReturn(unitSymbol);
    when(unitSymbol.toString()).thenReturn("C");

    String expected = unitID + " " + unitDescription + " " + unitSymbol;
    // Act
    UnitDTO unitDTO = unitAssembler.domainToDTO(unit);

    // Assert
    assertEquals(expected, unitDTO.toString());
  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the unitType is
   * null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDomainToDTOIsCalledWithNullunitType() {
    // Arrange
    UnitAssembler unitAssembler = new UnitAssembler();
    String expectedMessage = "Unit is required";
    Unit unit = null;
    // Act
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> unitAssembler.domainToDTO(unit));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Test if the domainToDTO method returns a UnitDTO object when the unit is valid.
   */
  @Test
  void shouldReturnunitTypeDTOList_WhenDomainToDTOIsCalledWithListOfunitType()
  {
    // Arrange
    UnitAssembler unitAssembler = new UnitAssembler();

    UnitID unitID = mock(UnitID.class);
    UnitDescription unitDescription = mock(UnitDescription.class);
    UnitSymbol unitSymbol = mock(UnitSymbol.class);

    String unitIDData = "f47b3b2d-0b7d-4e7b-8b8f-5f3b6d6b1f1d";
    String unitTypeDescriptionData = "Celcius";
    String unitTypeSymbolData = "C";

    Unit unit = mock(Unit.class);
    when(unit.getID()).thenReturn(unitID);
    when(unitID.toString()).thenReturn(unitIDData);
    when(unit.getUnitDescription()).thenReturn(unitDescription);
    when(unitDescription.toString()).thenReturn(unitTypeDescriptionData);
    when(unit.getUnitSymbol()).thenReturn(unitSymbol);
    when(unitSymbol.toString()).thenReturn(unitTypeSymbolData);

    Unit unit2 = mock(Unit.class);
    UnitID unitID2 = mock(UnitID.class);
    UnitSymbol unitSymbol2 = mock(UnitSymbol.class);
    String unitIDData2 = "f47b3b2d-0b7d-4e7b-8b8f-5f3b6d6b1f1e";
    String unitTypeDescriptionData2 = "Percentage";
    String unitTypeSymbolData2 = "%";

    UnitDescription unitDescription2 = mock(UnitDescription.class);
    when(unit2.getID()).thenReturn(unitID2);
    when(unitID2.toString()).thenReturn(unitIDData2);
    when(unit2.getUnitDescription()).thenReturn(unitDescription2);
    when(unitDescription2.toString()).thenReturn(unitTypeDescriptionData2);
    when(unit2.getUnitSymbol()).thenReturn(unitSymbol2);
    when(unitSymbol2.toString()).thenReturn(unitTypeSymbolData2);

    List<Unit> unitList = Arrays.asList(unit, unit2);

    // Expected list
    UnitDTO unitDTO =
        new UnitDTO(unitIDData, unitTypeDescriptionData, unitTypeSymbolData);
    UnitDTO unitDTO2 =
        new UnitDTO(
            unitIDData2, unitTypeDescriptionData2, unitTypeSymbolData2);
    List<UnitDTO> expectedList = List.of(unitDTO, unitDTO2);

    // Act
    List<UnitDTO> actualList = unitAssembler.domainToDTO(unitList);

    // Assert
    assertEquals(expectedList.toString(), actualList.toString());
  }

  /**
   * Test if the domainToDTO method throws an IllegalArgumentException when the list of
   * unitTypes is null.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenDomainToDTOIsCalledWithNullListOfunitType() {
    // Arrange
    UnitAssembler unitAssembler = new UnitAssembler();
    String expectedMessage = "The list of Units cannot be null.";
    List<Unit> unitList = null;
    // Act
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> unitAssembler.domainToDTO(unitList));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Should return an empty list when the list of unitTypes is empty.
   */
  @Test
  void shouldReturnEmptyList_WhenDomainToDTOIsCalledWithEmptyListOfunitType()
  {
    // Arrange
    UnitAssembler unitAssembler = new UnitAssembler();
    List<Unit> unitList = List.of();
    // Act
    List<UnitDTO> actualList = unitAssembler.domainToDTO(unitList);
    // Assert
    assertEquals(0, actualList.size());
  }
}
