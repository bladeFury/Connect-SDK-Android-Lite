package eu.airaudio.airplay.auth;

import com.dd.plist.NSDictionary;
import com.dd.plist.PropertyListParser;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URL;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Martin on 24.05.2017.
 */
public class AuthUtils {
    static byte[] concatByteArrays(byte[]... byteArrays) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (byte[] bytes : byteArrays) {
            byteArrayOutputStream.write(bytes);
        }
        return byteArrayOutputStream.toByteArray();
    }

    static byte[] createPList(Map<String, ? extends Object> properties) throws IOException {
        ByteArrayOutputStream plistOutputStream = new ByteArrayOutputStream();
        NSDictionary root = new NSDictionary();
        for (Map.Entry<String, ? extends Object> property : properties.entrySet()) {
            root.put(property.getKey(), property.getValue());
        }
        PropertyListParser.saveAsBinary(root, plistOutputStream);
        return plistOutputStream.toByteArray();
    }

    public static byte[] postData(SocketAddress socket, String path, String contentType, byte[] data) throws IOException {
        InetSocketAddress addr = (InetSocketAddress) socket;
        URL url = new URL(String.format("http://%s:%d%s", addr.getHostName(), addr.getPort(), path));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("User-Agent", "AirPlay/320.20");
        connection.setRequestProperty("X-Apple-Session-ID", "bc926562-bc8f-455c-a2c8-273596ab6020");
        connection.setRequestProperty("Connection", "Keep-Alive");
        if (data != null) {
            connection.setRequestProperty("Content-Type", contentType);
        }

        connection.connect();
        if (data != null) {
            connection.getOutputStream().write(data);
            connection.getOutputStream().close();
        }
        int statusCode = connection.getResponseCode();


        if (statusCode != 200) {
            throw new IOException("Invalid status code " + statusCode);
        }

        try (ByteArrayOutputStream response = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[0xFFFF];

            for (int len; response.size() < connection.getContentLength() && (len = connection.getInputStream().read(buffer)) != -1; ) {
                response.write(buffer, 0, len);
            }
            connection.getInputStream().close();

            return response.toByteArray();
        }
    }

    private static String readLine(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int c;
        for (c = inputStream.read(); c != '\n' && c != -1; c = inputStream.read()) {
            byteArrayOutputStream.write(c);
        }
        if (c == -1 && byteArrayOutputStream.size() == 0) {
            return null;
        }
        String line = byteArrayOutputStream.toString("UTF-8");
        return line;
    }

    public static String randomString(final int length) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++)
            sb.append(chars[rnd.nextInt(chars.length)]);

        return sb.toString();
    }

}
