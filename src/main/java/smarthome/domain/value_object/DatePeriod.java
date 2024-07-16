/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import smarthome.ddd.IValueObject;

public class DatePeriod implements IValueObject {

  private final LocalDateTime startDate;
  private final LocalDateTime endDate;

  /**
   * Constructs a new DatePeriod.
   *
   * @param startDate the start date.
   * @param endDate the end date.
   */
  public DatePeriod(LocalDateTime startDate, LocalDateTime endDate) {
    validateTimePeriod(startDate, endDate);
    this.startDate = startDate.truncatedTo(ChronoUnit.MINUTES);
    this.endDate = endDate.truncatedTo(ChronoUnit.MINUTES);
  }

  /**
   * Validates the time period.
   *
   * @param startDate the start date.
   * @param endDate the end date.
   */
  private void validateTimePeriod(LocalDateTime startDate, LocalDateTime endDate) {
    if (startDate == null) {
      throw new IllegalArgumentException("Start date cannot be null.");
    }
    if (endDate == null) {
      throw new IllegalArgumentException("End date cannot be null.");
    }
    if (startDate.isAfter(endDate)) {
      throw new IllegalArgumentException("Start date cannot be after end date.");
    }
  }

  /**
   * Returns the start date.
   *
   * @return the start date.
   */
  public LocalDateTime getStartDate() {
    return startDate;
  }

  /**
   * Returns the end date.
   *
   * @return the end date.
   */
  public LocalDateTime getEndDate() {
    return endDate;
  }

  /**
   * Returns the duration in minutes.
   *
   * @return the duration in minutes.
   */
  public int getDurationInMinutes() {
    return (int) ChronoUnit.MINUTES.between(startDate, endDate);
  }

  /**
   * Indicates whether some other object is "equal to" this one.
   *
   * @param o the reference object with which to compare.
   * @return true if this object is the same as the obj argument; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof DatePeriod objectDatePeriod) {
      return startDate.equals(objectDatePeriod.startDate) && endDate.equals(
          objectDatePeriod.endDate);
    }
    return false;
  }

  /**
   * Returns a string representation of the object.
   *
   * @return a string representation of the object.
   */
  @Override
  public String toString() {
    return "DatePeriod:" +
        "startDate=" + startDate +
        ", endDate=" + endDate;
  }

  /**
   * Returns a hash code value for the object.
   *
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    return startDate.hashCode() + endDate.hashCode();
  }
}
