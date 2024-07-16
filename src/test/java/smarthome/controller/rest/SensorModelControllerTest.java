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
import smarthome.domain.repository.ISensorModelRepository;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.value_object.ModelPath;
import smarthome.domain.value_object.SensorModelName;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.service.ISensorModelService;

@SpringBootTest
@AutoConfigureMockMvc
class SensorModelControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ISensorModelService sensorModelService;

  @MockBean
  private ISensorModelRepository sensorModelRepository;


  @Test
  void shouldReturnListOfSensorModelsBySensorTypeID_whenSensorModelsExist() throws Exception {
    // Arrange
    SensorModelName sensorModelName = new SensorModelName("sensorModelName");
    ModelPath modelPath = new ModelPath("modelPath");
    SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");

    SensorModel sensorModel = sensorModelService.createSensorModel(sensorModelName, modelPath,
        sensorTypeID);

    when(sensorModelRepository.findBySensorTypeId(sensorTypeID)).thenReturn(List.of(sensorModel));

    mockMvc.perform(get("/sensor-models?sensorTypeID=" + sensorTypeID.getID()).accept(
            MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.self").exists())
        .andExpect(
            jsonPath("$._embedded.sensorModelDTOList[0].sensorModelName").value("sensorModelName"));

  }

  @Test
  void shouldReturnEmptyListOfSensorModelsBySensorTypeID_whenSensorModelsDoNotExist()
      throws Exception {
    // Arrange
    SensorTypeID sensorTypeID = new SensorTypeID("sensorTypeID");

    when(sensorModelRepository.findBySensorTypeId(sensorTypeID)).thenReturn(List.of());

    mockMvc.perform(get("/sensor-models?sensorTypeID=" + sensorTypeID.getID()).accept(
            MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isEmpty());

  }


}