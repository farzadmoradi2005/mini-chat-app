package org.example;

import com.google.gson.Gson;
import model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        PrintWriter out = new PrintWriter(socket.getOutputStream() , true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner sc = new Scanner(System.in);
        Gson gson = new Gson();
        String name = sc.nextLine();
        int age = sc.nextInt();
        String userName = sc.nextLine();
        User newUser = User.builder().name(name).age(age).userName(userName).build();
        out.println(gson.toJson(newUser));
        new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null) {
                    System.out.println("Server: " + msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                while (true) {
                    String msg = sc.nextLine();
                    out.println(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}