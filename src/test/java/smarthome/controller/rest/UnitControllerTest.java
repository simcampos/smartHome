/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import smarthome.domain.repository.IUnitRepository;
import smarthome.domain.unit.IUnitFactory;
import smarthome.domain.unit.Unit;
import smarthome.domain.value_object.UnitDescription;
import smarthome.domain.value_object.UnitSymbol;
import smarthome.utils.LoadDefaultConfiguration;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class UnitControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private IUnitFactory unitFactory;

  @MockBean
  private IUnitRepository unitRepository;

  @MockBean
  private LoadDefaultConfiguration loadConfig;


  /**
   * Tests successful retrieval of all units.
   */
  @Test
  void shouldReturnAllUnits_WhenRequested() throws Exception {
    // Arrange
    UnitDescription unitDescription = new UnitDescription("Test");
    UnitSymbol unitSymbol = new UnitSymbol("C");
    Unit unit = unitFactory.createUnit(unitDescription, unitSymbol);
    when(unitRepository.findAll()).thenReturn(List.of(unit));
    // Act & Assert
    mockMvc.perform(get("/units")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.self.href").exists())
        .andExpect(jsonPath("$._embedded.unitDTOList[0].unitID").value(unit.getID().getID()));
  }

  /**
   * Tests the scenario where no units are found.
   */
  @Test
  void shouldReturnNotFound_WhenNoUnitsAvailable() throws Exception {
    when(unitRepository.findAll()).thenReturn(List.of());
    // Act & Assert
    mockMvc.perform(get("/units")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.self.href").exists())
        .andExpect(jsonPath("$._embedded.unitDTOList").doesNotExist());
  }
}
