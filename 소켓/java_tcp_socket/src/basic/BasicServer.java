package basic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BasicServer {

    public static void main(String[] args){
        try{

            ServerSocket server=new ServerSocket(1234);
            Socket socket=server.accept();

            // 수신 스레드와 송신 스레드
            ServerReceiveThread receiveThread=new ServerReceiveThread(socket);
            ServerSendThread sendThread=new ServerSendThread(socket);

            sendThread.start();
            receiveThread.start();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
