/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.assembler;

import java.util.List;

public interface IDataModelAssembler<ID, T> {

  /**
   * Method to convert a domain entity into a DTO.
   *
   * @param domainEntity is the domain entity to be converted.
   * @return the DTO.
   */
  T toDomain(ID domainEntity);

  /**
   * Method to convert a list of domain entities into a list of DTOs.
   *
   * @param domainEntities is the list of domain entities to be converted.
   * @return the list of DTOs.
   */

  List<T> toDomain(List<ID> domainEntities);
}
