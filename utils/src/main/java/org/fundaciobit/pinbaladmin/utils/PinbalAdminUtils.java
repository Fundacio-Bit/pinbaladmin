package org.fundaciobit.pinbaladmin.utils;

/**
 * 
 * @author anadal
 *
 */
public class PinbalAdminUtils {

  public static boolean isNumber(String af) {

    try {
      Integer.parseInt(af);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }

  }

}
