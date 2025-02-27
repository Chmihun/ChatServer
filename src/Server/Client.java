package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

class Client implements Runnable {
    private Socket socket;
    private Scanner in;
    private PrintStream out;
    private ClientServer server;
    private int id;

    public Client(Socket socket, ClientServer server,int id) {
        this.server = server;
        this.socket = socket;  // запускаем поток
        this.id=id;

        new Thread(this).start();

    }

    public int getId() {
        return id;
    }

    public void receive(String message) {
        out.println(message);
    }

    public void run() {
        try {
            // получаем потоки ввода и вывода
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            // создаем удобные средства ввода и вывода
            in = new Scanner(is);
            out = new PrintStream(os);

            // читаем из сети и пишем в сеть
//            out.println("Welcome to chat!");
            out.println("Welcome to chat! Your ID is: " + id);
//            String input = in.nextLine();

            while (in.hasNextLine()) {
                String input = in.nextLine();
                if (input.equals("bye")) {
                    socket.close();
                    break;
                }
//                server.sendAll(input);
                server.sendAll("User " + id + ": " + input);
            }
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}