package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientReceiveThread extends Thread{
    private Socket socket;

    ClientReceiveThread(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String receiveData;
            String[] split;

            // 소켓으로 받은 다른 사람들의 메시지를 출력한다.
            while (true) {
                receiveData = buffer.readLine();
                split=receiveData.split(" : ");

                // 자기 메시지는 출력하지 않는다.
                if(split.length>=2&&split[0].equals(ChatClient.ID)){
                    continue;
                }
                System.out.println(receiveData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
