/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.repository;

import java.util.List;
import smarthome.ddd.IRepository;
import smarthome.domain.device.Device;
import smarthome.domain.value_object.DeviceID;
import smarthome.domain.value_object.DeviceTypeID;
import smarthome.domain.value_object.RoomID;

/**
 * Represents a repository for managing Device instances.
 */
public interface IDeviceRepository extends IRepository<DeviceID, Device> {

  List<Device> findByRoomID(RoomID roomId);

  Device update(Device device);

  List<Device> findByDeviceTypeID(DeviceTypeID deviceTypeID);

}
