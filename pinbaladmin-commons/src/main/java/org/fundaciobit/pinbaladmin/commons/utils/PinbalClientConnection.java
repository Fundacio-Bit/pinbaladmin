package org.fundaciobit.pinbaladmin.commons.utils;

/**
 * 
 * @author anadal (u80067)
 * 19 ago 2026 12:09:32
 */
public class PinbalClientConnection {

    public final String baseUrl;
    public final String username;
    public final String password;

    public PinbalClientConnection(String baseUrl, String username, String password) {
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
    }

    public static final PinbalClientConnection getConnection(boolean isProduction) {

        return new PinbalClientConnection(Configuracio.getApiPinbalClientUrl(isProduction),
                Configuracio.getApiPinbalClientUsername(isProduction),
                Configuracio.getApiPinbalClientPassword(isProduction));

    }

    public static final PinbalClientConnection getDefaultConnection() {

        return getConnection(Constants.API_PINBAL_PRO);

    }

}
