package org.mort11.mohackathonclient;

import android.util.Log;

import org.mort11.mohackathonclient.student.Student;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    public static Socket socket;
    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;
    private static int SERVER_PORT = 8886;
    static boolean serverRecievedLogin = false;
    static boolean accountFoundFromLogin = false;
    static boolean loginJSONReceived = false;
    static String loginJSON = "";
    static boolean studentsReceivedFromServer = false;
    public static ArrayList<String> studentJSONs = new ArrayList<>();
    public static boolean reportSentToServer = false;

    public static void connectToServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("192.168.1.13", SERVER_PORT);
                    inputStream = new DataInputStream(socket.getInputStream());
                    outputStream = new DataOutputStream(socket.getOutputStream());
                    Log.d("test", "Connected to server!!");
                }catch (IOException e){
                    e.printStackTrace();
                    Log.d("test", "Could not connect to server");
                }
            }
        }).start();
    }

    public static void closeConnection(){
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static boolean login(final String accountType, final String username, final String password) throws IOException{
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    outputStream.writeUTF("LOGIN");
                    outputStream.writeUTF(accountType);
                    outputStream.writeUTF(username);
                    outputStream.writeUTF(password);
                    outputStream.flush();
                    Log.d("test", "Wrote all data and flushed it!!");
                    accountFoundFromLogin = inputStream.readBoolean();
                    Log.d("test", "Received a boolean from server: " + accountFoundFromLogin);
                    serverRecievedLogin = true;
                    Log.d("test", "Server received and sent back data");
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("test", "error writing data!!");
                }
            }
        }).start();
        return false;
    }

    public static String getLoginJSON(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    loginJSON = Client.getInputStream().readUTF();
                    loginJSONReceived = true;
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
        while (!loginJSONReceived)
            ;
        return loginJSON;
    }

    public static void register(final String accountType, final String json) throws IOException{
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    outputStream.writeUTF("REGISTER");
                    outputStream.writeUTF(accountType);
                    outputStream.writeUTF(json);
                    outputStream.flush();
                    Log.d("test", "Account created data sent to server");
                    Client.closeConnection();
                    Client.connectToServer();
                }catch (IOException e){
                    e.printStackTrace();
                    Log.d("test", "error creating account and writing data");
                }
            }
        }).start();
    }

    public static void sendReport(final String json){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    outputStream.writeUTF(json);
                    outputStream.flush();
                    reportSentToServer = true;
                    Log.d("test", "Report flushed to server");
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static DataInputStream getInputStream(){
        return inputStream;
    }

    public static DataOutputStream getOutputStream(){
        return outputStream;
    }

    public static ArrayList<String> getStudentsFromServer(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String json = Client.getInputStream().readUTF();
                    Log.d("test" , "Going through loop, received: " + json);
                    while(!json.equals("END")){
                        studentJSONs.add(json);
                        json = Client.getInputStream().readUTF();
                    }
                    studentsReceivedFromServer = true;
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
        while(!studentsReceivedFromServer)
            ;
        Log.d("test" , "FINISHED THE WHILE LOOP! RECEIVED FROM SERVER!!");
        return studentJSONs;
    }

}
