package com.andersen.tcpudpclient;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(("1 - start TCP client \n" +
                    "2 - start UDP client \n"));
            String input = reader.readLine();

            switch (input) {
                case "1":
                    startTcpClient();
                    break;
                case "2":
                    startUdpClient();
                    break;
            }

        } catch (IOException e) {
            System.out.println("IOException caught" + e);
        }
    }

    private static void startUdpClient() {
        UdpClient udpClient = new UdpClient();
        udpClient.start("localhost", 9898);
    }

    private static void startTcpClient() {
        TcpClient tcpClient = new TcpClient();
        tcpClient.start("localhost", 9898);
    }
}
