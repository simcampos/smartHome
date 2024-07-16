/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.utils.dto;


import org.springframework.hateoas.RepresentationModel;
import smarthome.ddd.IDTO;

public class HouseDTO extends RepresentationModel<ActuatorTypeDTO> implements IDTO {

  public final String address;
  public final String gps;
  public final String houseID;

  /**
   * Constructor for the HouseDTO class.
   *
   * @param address is the address of the House.
   * @param gps     is the GPS coordinates of the House.
   */
  public HouseDTO(String address, String gps, String houseID) {
    this.address = address;
    this.gps = gps;
    this.houseID = houseID;
  }

  @Override
  public String toString() {
    return address + " " + gps + " " + houseID;
  }
}

