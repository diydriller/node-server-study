package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerSendThread extends Thread {

    private Socket socket;
    ServerSendThread(Socket socket){
        this.socket=socket;
    }


    @Override
    public void run(){
        try{
            // inputStreamReader - byte stream에서 character stream으로 변환한다.
            // bufferedReader - 버퍼를 가진 stream으로 문자열로 저장할수 있게 한다.
            // decorator pattern으로 표현했다.
            BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));

            // PrintWriter - 문자열을 소켓의 byte stream으로 출력한다.
            PrintWriter sender=new PrintWriter(socket.getOutputStream());
            String sendData;

            // 사용자로부터 문자열을 읽어와 소켓으로 전달한다.
            while(true){
                sendData= buffer.readLine();
                sender.println(sendData);
                sender.flush();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
