/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class DatePeriodTest {

  /**
   * Validates construction with valid arguments.
   */
  @Test
  void shouldThrowExceptionWhenStartDateIsNull() {
    // Arrange
    LocalDateTime startDate = null;
    LocalDateTime endDate = LocalDateTime.now();
    String expectedMessage = "Start date cannot be null.";
    // Act
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new DatePeriod(startDate, endDate));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Expects IllegalArgumentException for null endDate.
   */
  @Test
  void shouldThrowExceptionWhenEndDateIsNull() {
    // Arrange
    LocalDateTime startDate = LocalDateTime.now();
    LocalDateTime endDate = null;
    String expectedMessage = "End date cannot be null.";
    // Act
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new DatePeriod(startDate, endDate));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Expects IllegalArgumentException for startDate after endDate.
   */
  @Test
  void shouldThrowExceptionWhenStartDateIsAfterEndDate() {
    // Arrange
    LocalDateTime startDate = LocalDateTime.now();
    LocalDateTime endDate = LocalDateTime.now().minusMinutes(1);
    String expectedMessage = "Start date cannot be after end date.";
    // Act
    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> new DatePeriod(startDate, endDate));
    // Assert
    assertEquals(expectedMessage, exception.getMessage());
  }

  /**
   * Tests getDurationInMinutes method should return zero when called with same dates.
   */
  @Test
  void shouldReturnZeroWhenGetDurationInMinutesIsCalledWithSameDates() {
    // Arrange
    LocalDateTime startDate = LocalDateTime.now();
    LocalDateTime endDate = LocalDateTime.now();
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    int expected = 0;
    // Act
    int result = datePeriod.getDurationInMinutes();
    // Assert
    assertEquals(expected, result);
  }

  /**
   * Tests getDurationInMinutes method should return time elapsed in minutes when called with
   * different dates.
   */
  @Test
  void shouldReturnTimeElapsedInMinutesWhenGetDurationInMinutesIsCalled() {
    // Arrange
    LocalDateTime startDate = LocalDateTime.now();
    LocalDateTime endDate = LocalDateTime.now().plusMinutes(77);
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    int expected = 77;
    // Act
    int result = datePeriod.getDurationInMinutes();
    // Assert
    assertEquals(expected, result);
  }

  /**
   * Gets start date when getStartDate is called.
   */
  @Test
  void shouldReturnStartDateWhenGetStartDateIsCalled() {
    // Arrange
    LocalDateTime startDate = LocalDateTime.now();
    LocalDateTime endDate = LocalDateTime.now().plusMinutes(1);
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    LocalDateTime expected = startDate.truncatedTo(java.time.temporal.ChronoUnit.MINUTES);
    // Act
    LocalDateTime result = datePeriod.getStartDate();
    // Assert
    assertEquals(expected, result);
  }

  /**
   * Gets end date when getEndDate is called.
   */
  @Test
  void shouldReturnEndDateWhenGetEndDateIsCalled() {
    // Arrange
    LocalDateTime startDate = LocalDateTime.now();
    LocalDateTime endDate = LocalDateTime.now().plusMinutes(1);
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    LocalDateTime expected = endDate.truncatedTo(java.time.temporal.ChronoUnit.MINUTES);
    // Act
    LocalDateTime result = datePeriod.getEndDate();
    // Assert
    assertEquals(expected, result);
  }

  /**
   * Tests getDurationInMinutes method should return one when called with one minute difference.
   */
  @Test
  void shouldReturnDurationInMinutesWhenGetDurationInMinutesIsCalled() {
    // Arrange
    LocalDateTime startDate = LocalDateTime.now();
    LocalDateTime endDate = LocalDateTime.now().plusMinutes(1);
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    // Act
    int result = datePeriod.getDurationInMinutes();
    // Assert
    assertEquals(1, result);
  }

  /**
   * Tests equals method should return true when comparing two datePeriods with same startDate and
   * endDate.
   */
  @Test
  void shouldReturnTrueWhenEqualsIsCalledWithSameDatePeriod() {
    // Arrange
    LocalDateTime startDate = LocalDateTime.now();
    LocalDateTime endDate = LocalDateTime.now().plusMinutes(1);
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    DatePeriod datePeriod2 = new DatePeriod(startDate, endDate);
    // Act
    boolean result = datePeriod.equals(datePeriod2);
    // Assert
    assertTrue(result);
  }

  /**
   * Tests equals method should return false when comparing two datePeriods with same startDate but
   * different endDate.
   */
  @Test
  void shouldReturnFalseWhenEqualsIsCalledWithDifferentEndDate() {
    // Arrange
    LocalDateTime startDate = LocalDateTime.now();
    LocalDateTime endDate = LocalDateTime.now().plusMinutes(1);
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    DatePeriod datePeriod2 = new DatePeriod(startDate, endDate.plusMinutes(1));
    // Act
    boolean result = datePeriod.equals(datePeriod2);
    // Assert
    assertFalse(result);
  }

  /**
   * Tests equals method should return false when comparing two datePeriods with same endDate but
   * different startDate.
   */
  @Test
  void shouldReturnFalseWhenEqualsIsCalledWithDifferentStartDate() {
    // Arrange
    LocalDateTime startDate = LocalDateTime.now();
    LocalDateTime endDate = LocalDateTime.now().plusMinutes(1);
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    DatePeriod datePeriod2 = new DatePeriod(startDate.plusMinutes(1), endDate);
    // Act
    boolean result = datePeriod.equals(datePeriod2);
    // Assert
    assertFalse(result);
  }

  /**
   * Tests equals method should return false when comparing two datePeriods with different startDate
   * and endDate.
   */
  @Test
  void shouldReturnFalseWhenEqualsIsCalledWithDifferentDatePeriod() {
    // Arrange
    LocalDateTime startDate = LocalDateTime.now();
    LocalDateTime endDate = LocalDateTime.now().plusMinutes(1);
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    DatePeriod datePeriod2 = new DatePeriod(startDate, endDate.plusMinutes(1));
    // Act
    boolean result = datePeriod.equals(datePeriod2);
    // Assert
    assertFalse(result);
  }

  /**
   * Tests equals method should return false when comparing with different object.
   */
  @Test
  void shouldReturnFalseWhenEqualsIsCalledWithDifferentObject() {
    // Arrange
    LocalDateTime startDate = LocalDateTime.now();
    LocalDateTime endDate = LocalDateTime.now().plusMinutes(1);
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    // Act
    boolean result = datePeriod.equals(new Object());
    // Assert
    assertFalse(result);
  }

  /**
   * Tests to String method should return string when called.
   */
  @Test
  void shouldReturnStringWhenToStringIsCalled() {
    // Arrange
    LocalDateTime startDate = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    LocalDateTime endDate = LocalDateTime.now().plusMinutes(1).truncatedTo(ChronoUnit.MINUTES);
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    String expected = "DatePeriod:startDate=" + startDate + ", endDate=" + endDate;
    // Act
    String result = datePeriod.toString();
    // Assert
    assertEquals(expected, result);
  }

  /**
   * Tests hashCode method should return same hash code when called with same datePeriod.
   */
  @Test
  void shouldReturnSameHashCodeWhenHashCodeIsCalledWithSameDatePeriod() {
    // Arrange
    LocalDateTime startDate = LocalDateTime.of(2022, 1, 1, 1, 1);
    LocalDateTime endDate = LocalDateTime.of(2022, 1, 1, 1, 2);

    DatePeriod datePeriod = new DatePeriod(startDate, endDate);

    int startDateHashCode = startDate.hashCode();
    int endDateHashCode = endDate.hashCode();

    int expected = startDateHashCode + endDateHashCode;

    // Act
    int result = datePeriod.hashCode();

    // Assert
    assertEquals(expected, result);
  }


  /**
   * Tests hashCode method should return different hash code when called with different datePeriod.
   */
  @Test
  void shouldNotReturnTheSameHashCode_WhenHasCodeIsDifferent() {
    // Arrange
    LocalDateTime startDate = LocalDateTime.now();
    LocalDateTime endDate = LocalDateTime.now().plusMinutes(1);
    DatePeriod datePeriod = new DatePeriod(startDate, endDate);
    // Act
    int expected = startDate.hashCode() - endDate.hashCode();
    int result = datePeriod.hashCode();
    // Assert
    assertNotEquals(expected, result);
  }
}
