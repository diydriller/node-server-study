package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    public static ArrayList<PrintWriter> outputList;

    public static void main(String [] args){
        outputList=new ArrayList<>();
        try{
            ServerSocket server=new ServerSocket(1234);

            while(true){

                // 클라이언트 접속할때마다 스레드 생성
                Socket socket=server.accept();
                ClientManagerThread manager=new ClientManagerThread(socket);

                outputList.add(new PrintWriter(socket.getOutputStream()));
                manager.start();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
