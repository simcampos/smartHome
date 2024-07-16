/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

package smarthome.domain.actuator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;
import org.springframework.stereotype.Component;
import smarthome.domain.value_object.ModelPath;

@Component
public class ActuatorFactoryImpl implements IActuatorFactory {

  /**
   * Create an actuator instance based on dynamic number of parameters.
   * @param parameters
   * @return IActuator
   */
  @Override
  public IActuator create(Object... parameters) {
      if (parameters.length < 4) {
        throw new IllegalArgumentException("At least 4 parameters are required.");
      }
    Class<?> actuatorClass = getActualActuatorClassFromParameters(parameters);
    Optional<Constructor<?>> constructor = getConstructorMatchingParameters(actuatorClass,
        parameters);
    return constructor.map(value -> instantiateActuator(value, parameters)).orElse(null);
  }

  /**
   * Instantiate an actuator based on the constructor and parameters.
   *
   * @param constructor
   * @param parameters
   * @return
   */

  private IActuator instantiateActuator(Constructor<?> constructor, Object... parameters) {
    try {
      return (IActuator) constructor.newInstance(parameters);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      return null;
    }
  }

  /**
   * Get the actual actuator class from the parameters.
   * @param parameters
   * @return
   */

  private Class<?> getActualActuatorClassFromParameters(Object... parameters) {
    try {
      ModelPath modelPath = getModelPathFromParameters(parameters);

      Class<?> actuatorClass = Class.forName(modelPath.toString());
      return actuatorClass;

    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException("The model path is not valid.");
    }
  }

  private ModelPath getModelPathFromParameters(Object... parameters) {
    ModelPath modelPath = (ModelPath) Arrays.stream(parameters)
        .filter(ModelPath.class::isInstance).findFirst().orElse(null);
    if (modelPath == null) {
      throw new IllegalArgumentException("Model path is required.");
    } else {
      return modelPath;
    }
  }

  /**
   * Get the constructor matching the parameters.
   *
   * @param actuatorClass
   * @param parameters
   * @return
   */

  private Optional<Constructor<?>> getConstructorMatchingParameters(Class<?> actuatorClass,
      Object... parameters) {
    Constructor<?>[] constructors = actuatorClass.getConstructors();

    for (Constructor<?> constructor : constructors) {
      if (tryConstructorAgainstParameters(constructor, parameters)) {
        return Optional.of(constructor);
      }
    }
    return Optional.empty();
  }

  /**
   * Try to match the constructor against the parameters.
   *
   * @param constructor
   * @param parameters
   * @return
   */

  private boolean tryConstructorAgainstParameters(Constructor<?> constructor,
      Object... parameters) {
    if (constructor.getParameterCount() != parameters.length) {
      return false;
    } else {

      Class<?>[] parameterTypes = constructor.getParameterTypes();
      for (int i = 0; i < parameters.length; i++) {
        if (!parameterTypes[i].isAssignableFrom(parameters[i].getClass())) {
          return false;
        }
      }
      return true;
    }
  }
}