package org.fundaciobit.pinbaladmin.model;

public class PinbalAdminDaoManager {
  
  private static IPinbalAdminDaoManagers instance = null;
  
  public static void setDaoManagers(IPinbalAdminDaoManagers managers) {
    instance = managers;
  }
  
  public static IPinbalAdminDaoManagers getDaoManagers() throws Exception {
    if(instance == null) {
      throw new Exception("Ha de inicialitzar el sistema de Managers cridant "
          + " al mètode PinbalAdminDaoManager.setDaoManagers(...)");
    }
    return instance;
  }
  
}
