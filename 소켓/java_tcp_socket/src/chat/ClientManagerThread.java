package chat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientManagerThread extends Thread{

    private Socket socket;
    private String ID;
    ClientManagerThread(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run(){
        try{
            BufferedReader buffer=new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String text;
            while(true){
                text=buffer.readLine();

                //클라이언트의 입력이 없을때 퇴장메시지를 유저들의 소켓에 전달하고 해당 클라이언트를 퇴장시킨다.
                if(text==null){
                    System.out.println(ID+" 가 나갔습니다.");
                    for(int i=0;i<ChatServer.outputList.size();i++){
                        ChatServer.outputList.get(i).println(ID+" 가 나갔습니다.");
                        ChatServer.outputList.get(i).flush();
                    }
                    break;
                }

                // 클라이언트가 입장할때 입장메시지를 유저들의 소켓에 전달한다.
                String []split=text.split(">>>>>");
                if(split.length==2&&split[0].equals("ID")){
                    ID=split[1];
                    System.out.println(ID+" 가 입장했습니다.");
                    for(int i=0;i<ChatServer.outputList.size();i++){
                        ChatServer.outputList.get(i).println(ID+" 가 입장했습니다.");
                        ChatServer.outputList.get(i).flush();
                    }
                    continue;
                }

                // 클라이언트의 유저메시지를 유저들의 소켓에 전달한다.
                for(int i=0;i<ChatServer.outputList.size();i++){
                    ChatServer.outputList.get(i).println(ID+" : "+text);
                    ChatServer.outputList.get(i).flush();
                }
            }
            ChatServer.outputList.remove(new PrintWriter(socket.getOutputStream()));
            socket.close();

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
