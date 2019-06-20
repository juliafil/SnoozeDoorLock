import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class InternetCheck {
    public static boolean isInternetAvailable() {
        return isHostAvailable("google.com");
    }

    private static boolean isHostAvailable(String hostName) {
        try (Socket socket = new Socket()) {
            int port = 80;
            InetSocketAddress socketAddress = new InetSocketAddress(hostName, port);
            socket.connect(socketAddress, 3000);

            return true;
        } catch (IOException unknownHost) {
            return false;
        }
    }
}

