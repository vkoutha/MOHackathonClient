package org.mort11.mohackathonclient;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    private static Socket socket;
    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;
    private static int SERVER_PORT = 8888;

    public static void connectToServer() throws IOException {
        socket = new Socket("192.168.1.13", SERVER_PORT);
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
    }

    public static boolean login(String accountType, String username, String password) throws IOException{
        outputStream.writeUTF("LOGIN");
        outputStream.writeUTF(accountType);
        outputStream.writeUTF(username);
        outputStream.writeUTF(password);
        return inputStream.readBoolean();
    }

    public static void register(String accountType, String json) throws IOException{
        outputStream.writeUTF("REGISTER");
        outputStream.writeUTF(accountType);
        outputStream.writeUTF(json);
    }

    public static void sendReport(String json) throws IOException{
        outputStream.writeUTF(json);
    }

    public static DataInputStream getInputStream(){
        return inputStream;
    }

    public static DataOutputStream getOutputStream(){
        return outputStream;
    }

}
