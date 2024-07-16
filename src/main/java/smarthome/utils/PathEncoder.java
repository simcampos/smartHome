/* 
 * School Project, educational software development.
 * This school project is open source and does not have a specific license.
 * It is intended for educational purposes only and should not be trusted for commercial purposes.
 * First see if it works.  Copyright (C) 2024
 * For any inquiries or further information, contact amm@isep.ipp.pt.
 */ 

/**
 * Class to encode and decode system paths
 */

package smarthome.utils;

import java.util.Base64;

public class PathEncoder {

  public static String encode(String modelPath) {
    Validator.validateNotNull(modelPath, "Model Path");
    return Base64.getEncoder().encodeToString(modelPath.getBytes());
  }

  public static String decode(String token) {
    Validator.validateNotNull(token, "token");
    byte[] decodedBytes = Base64.getDecoder().decode(token);
    return new String(decodedBytes);
  }
}
