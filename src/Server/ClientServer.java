package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientServer {
    ArrayList<Client> clients=new ArrayList<>();
    ServerSocket serverSocket;
    private static int userId = 0;

    public ClientServer() throws IOException {
        // создаем серверный сокет на порту 1234
        serverSocket  = new ServerSocket(1234);
    }

    public void sendAll(String message){
for (Client client:clients){
    client.receive(message);
}
    }
public  void run(){
    while(true) {
        System.out.println("Waiting...");
        try {// ждем клиента из сети
            Socket socket = serverSocket.accept();
            int clientId = ++userId; // Увеличиваем ID для нового клиента
//            System.out.println("Client connected!");
            System.out.println("Client connected! ID: " + clientId);
            // создаем клиента на своей стороне
//             clients.add (new Client(socket,this));
            clients.add(new Client(socket, this, clientId));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
    public static void main(String[] args) throws IOException {
new ClientServer().run();
//        ServerSocket server = new ServerSocket(1234);

    }
}