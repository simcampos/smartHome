/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.persistence.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Optional;
import smarthome.domain.house.House;
import smarthome.domain.repository.IHouseRepository;
import smarthome.domain.value_object.HouseID;
import smarthome.persistence.assembler.IDataModelAssembler;
import smarthome.persistence.data_model.HouseDataModel;
import smarthome.utils.Validator;

public class HouseRepositoryJPAImpl implements IHouseRepository {

  private final IDataModelAssembler<HouseDataModel, House> dataModelAssembler;
  private final EntityManagerFactory factory;

  /**
   * HouseRepositoryJPAImpl constructor
   *
   * @param dataModelAssembler the converter to transform data models to domain models and vice
   *                           versa
   */

  public HouseRepositoryJPAImpl(IDataModelAssembler<HouseDataModel, House> dataModelAssembler,
      EntityManagerFactory factory) {
    validateDataModelAssembler(dataModelAssembler);
    this.dataModelAssembler = dataModelAssembler;
    this.factory = factory;
  }

  /**
   * Validates the data model converter.
   *
   * @param dataModelAssembler the data model converter to validate
   * @throws IllegalArgumentException if the data model converter is null
   */
  private void validateDataModelAssembler(
      IDataModelAssembler<HouseDataModel, House> dataModelAssembler) {
    if (dataModelAssembler == null) {
      throw new IllegalArgumentException("Data model assembler cannot be null.");
    }
  }

  /**
   * Method to get entity manager
   *
   * @return EntityManager
   */
  private EntityManager getEntityManager() {
    return factory.createEntityManager();
  }

  /**
   * Method to save house
   *
   * @param house the domain entity to be saved
   * @return House
   */
  @Override
  public House save(House house) {
    Validator.validateNotNull(house, "House");

    if (thereShouldBeOnlyOneHouse()) {
      HouseDataModel houseDataModel = new HouseDataModel(house);
      EntityManager em = getEntityManager();
      EntityTransaction tx = em.getTransaction();
      tx.begin();
      em.persist(houseDataModel);
      tx.commit();
      em.close();
      return house;
    } else {
      throw new IllegalArgumentException("The system supports only one house.");
    }
  }

  /**
   * Method to find all houses
   *
   * @return List<House>
   */
  @Override
  public List<House> findAll() {
    Query query = getEntityManager().createQuery(
        "SELECT e FROM HouseDataModel e");
    List<HouseDataModel> listDataModel = query.getResultList();
    return dataModelAssembler.toDomain(listDataModel);
  }

  /**
   * Method to find house by identity
   *
   * @param objectID the identity of the house
   * @return Optional<House>
   */
  @Override
  public Optional<House> ofIdentity(HouseID objectID) {
    HouseDataModel houseDataModel = getEntityManager().find(HouseDataModel.class, objectID);
    if (houseDataModel == null) {
      return Optional.empty();
    } else {
      House house = dataModelAssembler.toDomain(houseDataModel);
      return Optional.of(house);
    }
  }

  /**
   * Method to check if house contains identity
   *
   * @param objectID the identity of the house
   * @return boolean
   */
  @Override
  public boolean containsOfIdentity(HouseID objectID) {
    Optional<House> houseDataModel = ofIdentity(objectID);
    return houseDataModel.isPresent();
  }

  @Override
  public boolean thereShouldBeOnlyOneHouse() {
    return findAll().isEmpty();
  }

  @Override
  public Optional<House> getTheHouse() {
    List<House> listHouse = findAll();
    if (listHouse.size() == 1) {
      House house = listHouse.get(0);
      return Optional.of(house);
    } else {
      return Optional.empty();
    }
  }
}

