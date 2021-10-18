package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerReceiveThread extends Thread {

    private Socket socket;
    ServerReceiveThread(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run(){
        try{

            // inputStreamReader - byte stream에서 character stream으로 변환한다.
            // bufferedReader - 버퍼를 가진 stream으로 문자열로 저장할수 있게 한다.
            // decorator pattern으로 표현했다.
            BufferedReader buffer=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String receiveData;

            // 소켓으로부터 문자열을 읽어온다.
            while(true){
                receiveData=buffer.readLine();

                if(receiveData==null){
                    break;
                }
                else{
                    System.out.println(receiveData);
                }
            }

            buffer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
