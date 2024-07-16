/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.assembler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import smarthome.domain.device_type.DeviceType;
import smarthome.domain.device_type.IDeviceTypeFactory;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.TypeDescription;
import smarthome.persistence.data_model.DeviceTypeDataModel;
import smarthome.utils.Validator;

@Component
public class DeviceTypeDataModelAssembler implements
    IDataModelAssembler<DeviceTypeDataModel, DeviceType> {

  private final IDeviceTypeFactory deviceTypeFactory;

  /**
   * Constructor of DeviceTypeDataModelAssembler
   *
   * @param deviceTypeFactory the deviceTypeFactory to be used
   */
  public DeviceTypeDataModelAssembler(IDeviceTypeFactory deviceTypeFactory) {
    Validator.validateNotNull(deviceTypeFactory, "Device Type Factory");
    this.deviceTypeFactory = deviceTypeFactory;
  }

  /**
   * Method to convert a domain entity into a DataModel.
   *
   * @param deviceTypeDataModel is the domain entity to be converted.
   * @return the DataModel.
   */
  @Override
  public DeviceType toDomain(DeviceTypeDataModel deviceTypeDataModel) {
    Validator.validateNotNull(deviceTypeDataModel, "Device Type Data Model");

    DeviceTypeID deviceTypeID = new DeviceTypeID(deviceTypeDataModel.getDeviceTypeID());
    TypeDescription typeDescription = new TypeDescription(
        deviceTypeDataModel.getDeviceTypeDescription());
    return deviceTypeFactory.createDeviceType(deviceTypeID, typeDescription);
  }

  /**
   * Method to convert a list of domain entities into a list of DataModels.
   *
   * @param deviceTypeDataModels is the list of domain entities to be converted.
   * @return the list of DataModels.
   */
  @Override
  public List<DeviceType> toDomain(List<DeviceTypeDataModel> deviceTypeDataModels) {

    List<DeviceType> deviceTypes = new ArrayList<>();

    for (DeviceTypeDataModel deviceTypeDataModel : deviceTypeDataModels) {
      DeviceType deviceType = toDomain(deviceTypeDataModel);
      deviceTypes.add(deviceType);
    }

    return deviceTypes;
  }
}
