package ru.tds.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс, в котором запукается сервер и ожидает подключения клиента и оповещает об этом.
 * А также выводит сообщения, которые прислал клиент и отправил ему обратно сервер.
 * Если пользователь отключается от сервера, то сервер оповещает об этом.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket( 8079);
        System.out.println("Сервер ждет клиента...");

        try(Socket clientSocket = serverSocket.accept();
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))){

            System.out.println("Новое соединение: " + clientSocket.getInetAddress().toString());

            String string;
            while ((string = reader.readLine())!=null){
                writer.write(string);
                System.out.println("Клиент прислал на сервер:" + string);
                System.out.println("Сервер ответил: " + string);
                if(string.equals("end")){
                    break;
                }
                outputStream.flush();
            }
            System.out.println("Клиент отключился от сервера");


        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
