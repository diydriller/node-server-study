package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.ArrayList;

public class ChatClient {
    public static String ID;

    public static void main(String [] args) {
        try {

            Socket socket = new Socket("192.168.0.5", 1234);

            // 수신 스레드와 송신 스레드
            ClientReceiveThread receiveThread = new ClientReceiveThread(socket);
            ClientSendThread sendThread = new ClientSendThread(socket);

            receiveThread.start();
            sendThread.start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
