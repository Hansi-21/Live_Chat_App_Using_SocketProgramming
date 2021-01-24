package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerUiController {
    public ImageView imgID;
    public TextField txtMsg;
    public TextArea messageArea;

    static ServerSocket serverSocket;
    static Socket socket;
    static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    public Label txtDate;


    public void initialize(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        serverSocket = new ServerSocket(5000);
                        System.out.println("Server Started");
                        socket = serverSocket.accept();
                        System.out.println("Client Accepted!");

                        dataInputStream = new DataInputStream(socket.getInputStream());
                        dataOutputStream = new DataOutputStream(socket.getOutputStream());
                        String messageIn = "";
                        while (!messageIn.equals("exit")){
                            messageIn= dataInputStream.readUTF();
                            messageArea.appendText("\n"+" "+messageIn.trim());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        genarateDate();
        genarateTime();
    }

    public void SendOnAction(MouseEvent mouseEvent) throws IOException {
        MessageSend();

    }

    public void MessageSend(){
        String reply="";
        reply=txtMsg.getText();
        try {
            dataOutputStream.writeUTF(reply);
        } catch (IOException e) {
            e.printStackTrace();
        }

        messageArea.appendText("\n\t\t\t\t\t\t\t\t\t"+txtMsg.getText().trim());
        txtMsg.clear();
    }

    public void AttachOnAction(MouseEvent mouseEvent) throws IOException {

    }

    public void ImojiOnAction(MouseEvent mouseEvent) {
    }

    public void SendMessageEntered(ActionEvent actionEvent) {
        MessageSend();
    }

    public void genarateTime(){



    }

    public void genarateDate(){
        DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        txtDate.setText(dateFormat.format(date));

    }
}
