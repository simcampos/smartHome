/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.controller.cli;

import static smarthome.utils.Validator.validateNotNull;

import java.util.List;
import smarthome.ddd.IAssembler;
import smarthome.domain.sensor.ISensor;
import smarthome.domain.sensor_model.SensorModel;
import smarthome.domain.sensor_type.SensorType;
import smarthome.domain.value_object.SensorTypeID;
import smarthome.mapper.sensor_vo_assembler.ISensorVOAssembler;
import smarthome.mapper.sensor_vo_assembler.SensorVOAssemblerImpl;
import smarthome.service.ISensorModelService;
import smarthome.service.ISensorService;
import smarthome.service.ISensorTypeService;
import smarthome.utils.Validator;
import smarthome.utils.dto.SensorDTO;
import smarthome.utils.dto.SensorModelDTO;
import smarthome.utils.dto.SensorTypeDTO;
import smarthome.utils.entry_dto.sensor_entry_dto.ISensorEntryDTO;


public class AddSensorToDeviceController {

  private final ISensorModelService sensorModelService;
  private final IAssembler<SensorModel, SensorModelDTO> sensorModelAssembler;
  private final ISensorTypeService sensorTypeService;
  private final IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler;
  private final IAssembler<ISensor, SensorDTO> sensorAssembler;
  private final ISensorService sensorService;

  /**
   * Constructor.
   *
   * @param sensorModelService   is the sensor model service.
   * @param sensorModelAssembler is the sensor model assembler.
   * @param sensorTypeService    is the sensor type service.
   * @param sensorTypeAssembler  is the sensor type assembler.
   * @param sensorAssembler      is the sensor assembler.
   * @param sensorService        is the sensor service.
   */
  public AddSensorToDeviceController(
      ISensorModelService sensorModelService,
      IAssembler<SensorModel, SensorModelDTO> sensorModelAssembler,
      ISensorTypeService sensorTypeService,
      IAssembler<SensorType, SensorTypeDTO> sensorTypeAssembler,
      IAssembler<ISensor, SensorDTO> sensorAssembler,
      ISensorService sensorService) {

    Validator.validateNotNull(sensorModelService, "Sensor model service");
    Validator.validateNotNull(sensorModelAssembler, "Sensor model assembler");
    Validator.validateNotNull(sensorTypeService, "Sensor type service");
    Validator.validateNotNull(sensorTypeAssembler, "Sensor type assembler");
    Validator.validateNotNull(sensorAssembler, "Sensor assembler");
    Validator.validateNotNull(sensorService, "Sensor service");

    this.sensorModelService = sensorModelService;
    this.sensorModelAssembler = sensorModelAssembler;
    this.sensorTypeService = sensorTypeService;
    this.sensorTypeAssembler = sensorTypeAssembler;
    this.sensorAssembler = sensorAssembler;
    this.sensorService = sensorService;
  }

  /**
   * Gets all sensor types.
   *
   * @return a list of sensor types.
   */
  public List<SensorTypeDTO> getSensorTypes() {
    List<SensorType> sensorTypeList = sensorTypeService.getAllSensorTypes();
    if (sensorTypeList.isEmpty()) {
      throw new IllegalArgumentException("No sensor types found.");
    }
    List<SensorTypeDTO> sensorTypeDTOList = sensorTypeAssembler.domainToDTO(sensorTypeList);
    return List.copyOf(sensorTypeDTOList);
  }

  /**
   * Gets all sensor models.
   *
   * @return a list of sensor models.
   */
  public List<SensorModelDTO> getSensorModels(SensorTypeDTO sensorTypeDTO){
    SensorTypeID sensorTypeID = new SensorTypeID(sensorTypeDTO.sensorTypeID);

    if (sensorTypeService.getSensorTypeByID(sensorTypeID).isEmpty()) {
      throw new IllegalArgumentException("Sensor type with ID " + sensorTypeID + " not found.");
    }

    List<SensorModel> sensorModels =
        sensorModelService.getSensorModelsBySensorTypeId(sensorTypeID);
    if (sensorModels == null || sensorModels.isEmpty()) {
      throw new IllegalArgumentException("No sensor models found.");
    }
    List<SensorModelDTO> sensorModelDTOList = sensorModelAssembler.domainToDTO(sensorModels);

    return List.copyOf(sensorModelDTOList);
  }

  /**
   * Adds a sensor to a device.
   *
   * @param sensorDataDTOImp is the sensor data to add.
   * @return the sensor DTO.
   */
  public SensorDTO addSensorToDevice(ISensorEntryDTO sensorDataDTOImp) {
    validateNotNull(sensorDataDTOImp, "Sensor data DTO");
    ISensorVOAssembler sensorVOAssembler = new SensorVOAssemblerImpl();
    Object[] sensorParameters = sensorVOAssembler.getSensorParameters(sensorDataDTOImp);

    ISensor sensor = sensorService.addSensor(sensorParameters);

    return sensorAssembler.domainToDTO(sensor);
  }

}
