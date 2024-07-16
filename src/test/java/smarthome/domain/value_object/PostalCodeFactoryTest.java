/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import smarthome.domain.value_object.postal_code.IPostalCode;
import smarthome.domain.value_object.postal_code.PostalCodeFactory;

class PostalCodeFactoryTest {

  /**
   * Test to ensure that a PostalCode object is successfully created for a supported country code.
   *
   * Postal code for Portugal: PT
   */
  @Test
  void shouldCreatePostalCode_WhenCountryCodeIsSupported() {
    // Arrange
    String postalCode = "1234-599";
    String countryCode = "PT";
    PostalCodeFactory factory = new PostalCodeFactory();

    // Act
    IPostalCode result = factory.createPostalCode(postalCode, countryCode);

    // Assert
    assertNotNull(result);
    assertEquals(postalCode, result.getCode());
  }

  /**
   * Test to ensure that a PostalCode object is successfully created for a supported country code.
   *
   * Postal code for Spain: ES
   */
  @Test
  void shouldCreatePostalCode_WhenCountryCodeIsSupported2() {
    // Arrange
    String postalCode = "12345";
    String countryCode = "ES";
    PostalCodeFactory factory = new PostalCodeFactory();

    // Act
    IPostalCode result = factory.createPostalCode(postalCode, countryCode);

    // Assert
    assertNotNull(result);
    assertEquals(postalCode, result.getCode());
  }

  /**
   * Test to ensure that a PostalCode object is successfully created for a supported country code.
   *
   * Postal code for United States: US
   */
  @Test
  void shouldCreatePostalCode_WhenCountryCodeIsSupported3() {
    // Arrange
    String postalCode = "12345";
    String countryCode = "US";
    PostalCodeFactory factory = new PostalCodeFactory();

    // Act
    IPostalCode result = factory.createPostalCode(postalCode, countryCode);

    // Assert
    assertNotNull(result);
    assertEquals(postalCode, result.getCode());
  }

  /**
   * Test to ensure that a PostalCode object is successfully created for a supported country code.
   *
   * Postal code for Canada: CA
   */
  @Test
  void shouldCreatePostalCode_WhenCountryCodeIsSupported4() {
    // Arrange
    String postalCode = "K1A 0B1";
    String countryCode = "CA";
    PostalCodeFactory factory = new PostalCodeFactory();

    // Act
    IPostalCode result = factory.createPostalCode(postalCode, countryCode);

    // Assert
    assertNotNull(result);
    assertEquals(postalCode, result.getCode());
  }

  /**
   * Test to ensure a wrong postal code for a supported country code is not created.
   *
   * Postal code for Portugal: PT
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenPostalCodeIsInvalid() {
    // Arrange
    String postalCode = "1234-5999";
    String countryCode = "PT";
    PostalCodeFactory factory = new PostalCodeFactory();

    // Act & Assert
    assertThrows(IllegalArgumentException.class,
        () -> factory.createPostalCode(postalCode, countryCode));
  }

  /**
   * Test to ensure a wrong postal code for a supported country code is not created.
   *
   * Postal code for Spain: ES
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenPostalCodeIsInvalid2() {
    // Arrange
    String postalCode = "123456";
    String countryCode = "ES";
    PostalCodeFactory factory = new PostalCodeFactory();

    // Act & Assert
    assertThrows(IllegalArgumentException.class,
        () -> factory.createPostalCode(postalCode, countryCode));
  }

  /**
   * Test to ensure a wrong postal code for a supported country code is not created.
   *
   * Postal code for United States: US
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenPostalCodeIsInvalid3() {
    // Arrange
    String postalCode = "123456";
    String countryCode = "US";
    PostalCodeFactory factory = new PostalCodeFactory();

    // Act & Assert
    assertThrows(IllegalArgumentException.class,
        () -> factory.createPostalCode(postalCode, countryCode));
  }

  /**
   * Test to ensure a wrong postal code for a supported country code is not created.
   *
   * Postal code for Canada: CA
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenPostalCodeIsInvalid4() {
    // Arrange
    String postalCode = "K1A 0B12";
    String countryCode = "CA";
    PostalCodeFactory factory = new PostalCodeFactory();

    // Act & Assert
    assertThrows(IllegalArgumentException.class,
        () -> factory.createPostalCode(postalCode, countryCode));
  }

  /**
   * Test to ensure that IllegalArgumentException is thrown for an unsupported country code.
   */
  @Test
  void shouldThrowIllegalArgumentException_WhenCountryCodeIsUnsupported() {
    // Arrange
    String postalCode = "1234-566";
    String countryCode = "XX";
    PostalCodeFactory factory = new PostalCodeFactory();

    // Act & Assert
    assertThrows(IllegalArgumentException.class,
        () -> factory.createPostalCode(postalCode, countryCode));
    assertThrows(RuntimeException.class,
        () -> factory.createPostalCode(postalCode, "InvalidCountryCode"));
  }
}
