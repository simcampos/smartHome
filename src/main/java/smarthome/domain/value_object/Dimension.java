/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.value_object;

import smarthome.ddd.IValueObject;

public class Dimension implements IValueObject {

  private final int width;
  private final int height;
  private final int depth;

  /**
   * Constructor of the class Dimension.
   *
   * @param width  is the width of the room.
   * @param height is the height of the room.
   * @param depth  is the depth of the room.
   */
  public Dimension(int width, int height, int depth) {
    validateWidth(width);
    validateHeight(height);
    validateDepth(depth);
    this.width = width;
    this.height = height;
    this.depth = depth;

  }

  /**
   * Method to set the width of the room and verifies if the value is positive.
   *
   * @param width is the width of the room.
   */
  private void validateWidth(int width) {
    if (width <= 0) {
      throw new IllegalArgumentException("'Width' must be positive.");
    }
  }

  /**
   * Method to set the height of the room and verifies if the value is positive.
   *
   * @param height is the height of the room.
   */
  private void validateHeight(int height) {
    if (height <= 0) {
      throw new IllegalArgumentException("'Height' must be positive.");
    }
  }

  /**
   * Method to set the depth of the room and verifies if the value is positive.
   *
   * @param depth is the depth of the room.
   */
  private void validateDepth(int depth) {
    if (depth <= 0) {
      throw new IllegalArgumentException("'Depth' must be positive");
    }
  }

  /**
   * Method to compares two objects.
   *
   * @param object is the object to compare.
   * @return true if the objects are equal, false if they are different.
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }

    if (object instanceof Dimension dimension) {

      return this.width == dimension.width
          && this.height == dimension.height && this.depth == dimension.depth;
    }
    return false;
  }

  /**
   * Method to return the hash code of the object.
   */
  @Override
  public int hashCode() {
    return Integer.hashCode(this.width) + Integer.hashCode(this.height) + Integer.hashCode(
        this.depth);
  }

  /**
   * Method to return the values of the object in a string.
   *
   * @return the values of the object in a string.
   */
  @Override
  public String toString() {
    return "Width: " + this.width + ", Height: " + this.height + ", Depth: " + this.depth;
  }

  /**
   * Method to return the width of the room.
   *
   * @return the width of the room.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Method to return the height of the room.
   *
   * @return the height of the room.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Method to return the depth of the room.
   *
   * @return the depth of the room.
   */
  public int getDepth() {
    return depth;
  }
}
