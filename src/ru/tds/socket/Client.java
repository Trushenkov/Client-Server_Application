package ru.tds.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Класс, в котором запускается клиент и пользователем вводится строчка и отправляется на сервер,
 * а сервер присылает в виде ответа эту же строчку.
 * Когда сервер присылает обратно ключевое слово, то работа класса завершается.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class Client {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try (Socket socket = new Socket("localhost", 8079);
             InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {

            while (true){
                String string;
                System.out.println("Введите строчку для отправки на сервер: ");
                string = scan.nextLine();
                writer.write(string);
                System.out.println("отправил клиент: " + string);
                System.out.println("прислал сервер: " + string);
                if (string.equals("end")){
                    break;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

