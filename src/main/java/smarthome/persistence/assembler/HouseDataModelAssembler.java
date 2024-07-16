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
import smarthome.domain.house.House;
import smarthome.domain.house.IHouseFactory;
import smarthome.domain.value_object.Address;
import smarthome.domain.value_object.GPS;
import smarthome.domain.value_object.HouseID;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;
import smarthome.persistence.data_model.HouseDataModel;
import smarthome.utils.Validator;

@Component
public class HouseDataModelAssembler implements IDataModelAssembler<HouseDataModel, House> {

  private final IHouseFactory houseFactory;

  /**
   * Class constructor
   *
   * @param houseFactory is the factory used to create House instances.
   */
  public HouseDataModelAssembler(IHouseFactory houseFactory) {
    Validator.validateNotNull(houseFactory, "House Factory");

    this.houseFactory = houseFactory;
  }

  /**
   * Converts a HouseDataModel instance to a House instance.
   *
   * @param houseDataModel is the domain entity to be converted.
   * @return a House instance.
   */
  @Override
  public House toDomain(HouseDataModel houseDataModel) {
    GPS gps = new GPS(houseDataModel.getLatitude(), houseDataModel.getLongitude());
    Address address = new Address(houseDataModel.getStreet(), houseDataModel.getDoorNumber(),
        houseDataModel.getPostalCode(), houseDataModel.getCountryCode(), new PostalCodeFactory());
    HouseID houseID = new HouseID(houseDataModel.getHouseID());

    return houseFactory.createHouse(houseID, address, gps);
  }

  /**
   * Converts a list of HouseDataModel instances to a list of House instances.
   *
   * @param houseDataModels is the list of domain entities to be converted.
   * @return a list of House instances.
   */
  @Override
  public List<House> toDomain(List<HouseDataModel> houseDataModels) {
    List<House> houses = new ArrayList<>();
    for (HouseDataModel houseDataModel : houseDataModels) {
      House house = toDomain(houseDataModel);
      houses.add(house);
    }
    return houses;
  }
}
