/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.sensor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.springframework.stereotype.Component;
import smarthome.domain.value_object.ModelPath;

@Component
public class SensorFactoryImpl implements ISensorFactory {

  /**
   * Creates a sensor object based on the given parameters
   *
   * @param parameters are the parameters required to create a sensor object
   * @return Sensor
   */
  @Override
  public ISensor create(Object... parameters) {
    try {
      if (parameters.length < 4) {
        throw new IllegalArgumentException("At least 4 parameters are required.");
      }

      ModelPath modelPath = (ModelPath) parameters[1];

      Class<?> sensorClass = Class.forName(modelPath.toString());
      Constructor<?> constructor = findMatchingConstructor(sensorClass, parameters);

      if (constructor != null) {
        return (ISensor) constructor.newInstance(parameters);
      } else {
        throw new InstantiationException("No matching constructor found for class: " + modelPath);
      }

    } catch (ClassNotFoundException | InstantiationException | ClassCastException |
             IllegalAccessException | InvocationTargetException ignored) {
    }
    return null;
  }

  /**
   * Find a constructor that matches the given parameters
   *
   * @param sensorClass is the specific class of the sensor to be instantiated
   * @param parameters  are the parameters required to create a sensor object
   * @return Constructor
   */
  private Constructor<?> findMatchingConstructor(Class<?> sensorClass, Object... parameters) {
    Constructor<?>[] constructors = sensorClass.getConstructors();

    for (Constructor<?> constructor : constructors) {
      Class<?>[] parameterTypes = constructor.getParameterTypes();

      if (parameterTypes.length == parameters.length) {
        boolean match = true;

        for (int i = 0; i < parameterTypes.length; i++) {
          if (!parameterTypes[i].isAssignableFrom(parameters[i].getClass())) {
            match = false;
            break;
          }
        }

        if (match) {
          return constructor;
        }
      }
    }

    return null;
  }
}
