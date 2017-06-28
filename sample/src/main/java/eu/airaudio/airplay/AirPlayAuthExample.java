package eu.airaudio.airplay;

import eu.airaudio.airplay.auth.AirPlayAuth;
import eu.airaudio.airplay.auth.AuthUtils;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

/**
 * Created by Martin on 08.05.2017.
 */
public class AirPlayAuthExample {

    // Generated via {@code AirPlayAuth.generateNewAuthToken()}
    private static final String STORED_AUTH_TOKEN = "I1Y67NO1F5VFAVPG@302e020100300506032b657004220420eb92ab919f68cc716f7f85a609531c3de74f87c9f1c9007c35516b4f5ef1fa25";

    public static final String IP = "192.168.1.184";
    public static final int PORT = 7000;

    public static void main() throws Exception {

        System.out.println("Used AuthKey: " + STORED_AUTH_TOKEN);
        AirPlayAuth airPlayAuth = new AirPlayAuth(new InetSocketAddress(IP, PORT), STORED_AUTH_TOKEN);
        SocketAddress socket;
        try {
            socket = airPlayAuth.authenticate();
        } catch (Exception e) {
            System.out.println("Authentication failed - start pairing..");

            airPlayAuth.startPairing();

            System.out.println("Enter PIN:");
            String pin = "";

            airPlayAuth.doPairing(pin);

            socket = airPlayAuth.authenticate();
        }


        String content = "Content-Location: http://111.202.98.149/vmind.qqvideo.tc.qq.com/p0200vq0ihv.p202.1.mp4?vkey=643BC7DF3C406143CA7A68C346C9D4FA1FE4B5E5EE9C2139AFAC03FDC90D9EE02094999DCB9348F322FDC75D4A70D52B40F2BC095CBB50603FCB3E3169D709A2C91BE6B1ACF051124692C6B833B6F62404D2F72F961EF865&platform=&sdtfrom=&fmt=hd&level=0\r\n" +
                "Start-Position: 0.000000\r\n";

        AuthUtils.postData(socket, "/play", "text/parameters", content.getBytes("UTF-8"));

    }
}
