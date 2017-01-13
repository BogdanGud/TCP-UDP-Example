package com.andersen.tcpudp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(("1 - start TCP server \n" +
                    "2 - start UDP server \n"));
            String input = reader.readLine();

            switch (input) {
                case "1":
                    startTcpServer();
                    break;
                case "2":
                    startUdpServer();
                    break;
            }

        } catch (IOException e) {
            System.out.println("IOException caught" + e);
        }
    }

    private static void startTcpServer() {
        TCPSocketService tcpServer = new TCPSocketService();
        tcpServer.startListener(9898);
    }

    private static void startUdpServer() {
        UDPSocketService udpServer = new UDPSocketService();
        udpServer.start();
    }
}
