package org.example;

import com.google.gson.Gson;
import model.User;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        User user = User.builder().age(sc.nextInt()).name(sc.next()).userName(sc.next()).build();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        System.out.println(json);
    }
}