package basic;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class BasicClient {
    public static void main(String[] args) {
        try {

            Socket socket = new Socket("192.168.0.5", 1234);

            // 수신 스레드와 송신 스레드
            ClientReceiveThread receiveThread=new ClientReceiveThread(socket);
            ClientSendThread sendThread=new ClientSendThread(socket);

            sendThread.start();
            receiveThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
