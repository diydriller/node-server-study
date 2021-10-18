package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSendThread  extends Thread{
    private Socket socket;

    ClientSendThread(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter sender = new PrintWriter(socket.getOutputStream());
            String sendData;

            // 사용자의 ID를 입력받는다.
            System.out.println("사용할 ID 입력해주세요 : ");
            ChatClient.ID = buffer.readLine();

            // 사용자의 입장메시지를 소켓으로 전달한다.
            sender.println("ID>>>>>" + ChatClient.ID);
            sender.flush();

            // 사용자의 유저메시지를 소켓으로 전달한다.
            while(true) {
                sendData = buffer.readLine();
                if (sendData.equals("exit")) {
                    break;
                }
                sender.println(sendData);
                sender.flush();
            }

            sender.close();
            buffer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
