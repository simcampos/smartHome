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
import smarthome.domain.device.Device;
import smarthome.domain.device.IDeviceFactory;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceName;
import smarthome.domain.value_object.DeviceStatus;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;
import smarthome.persistence.data_model.DeviceDataModel;
import smarthome.utils.Validator;

/**
 * The {@code DeviceDataModelConverter} class provides methods to convert {@link DeviceDataModel}
 * objects into their domain representation {@link Device}, and vice versa. This class is part of
 * the data access layer and facilitates the transformation between persistence models and domain
 * models, enabling the separation of concerns.
 */
@Component
public class DeviceDataModelAssembler implements IDataModelAssembler<DeviceDataModel, Device> {

  private final IDeviceFactory deviceFactory;

  /**
   * Constructs a DeviceDataModelConverter with a specific {@link IDeviceFactory}.
   *
   * @param deviceFactory The factory used to create Device domain objects.
   */
  public DeviceDataModelAssembler(IDeviceFactory deviceFactory) {
    Validator.validateNotNull(deviceFactory, "Device Factory");
    this.deviceFactory = deviceFactory;
  }

  /**
   * Converts a {@link DeviceDataModel} object to a {@link Device} domain object.
   *
   * @param deviceDataModel The device data model to convert.
   * @return The corresponding Device domain object.
   */
  @Override
  public Device toDomain(DeviceDataModel deviceDataModel) {
    Validator.validateNotNull(deviceDataModel, "Device Data Model");

    RoomID roomID = new RoomID(deviceDataModel.getRoomID());
    DeviceName deviceName = new DeviceName(deviceDataModel.getDeviceName());
    DeviceTypeID deviceTypeID = new DeviceTypeID(deviceDataModel.getDeviceTypeID());
    DeviceStatus deviceStatus = new DeviceStatus(deviceDataModel.getDeviceStatus());
    DeviceID deviceID = new DeviceID(deviceDataModel.getDeviceID());

    Device device = deviceFactory.createDevice(deviceID, roomID, deviceName, deviceStatus,
        deviceTypeID);
    return device;
  }

  /**
   * Converts a list of {@link DeviceDataModel} objects to a list of {@link Device} domain objects.
   *
   * @param deviceDataModels The list of device data models to convert.
   * @return A list containing the corresponding Device domain objects.
   */
  @Override
  public List<Device> toDomain(List<DeviceDataModel> deviceDataModels) {

    List<Device> listDomain = new ArrayList<>();
    for (DeviceDataModel deviceDataModel : deviceDataModels) {
      Device device = toDomain(deviceDataModel);
      listDomain.add(device);
    }
    return listDomain;
  }
}
