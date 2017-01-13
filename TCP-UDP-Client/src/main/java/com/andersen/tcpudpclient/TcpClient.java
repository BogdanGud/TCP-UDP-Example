package com.andersen.tcpudpclient;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

class TcpClient {
    void start(String internetAddress, int serverPort) {
        try (
                Socket clientSocket = new Socket(internetAddress, serverPort);
                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"))) {

            System.out.println("Type something and press enter: ");
            //noinspection InfiniteLoopStatement
            while (true) {
                String line;
                line = keyboard.readLine();
                out.write(line);
                out.write("\n");
                out.flush();
                String inline = in.readLine();
                System.out.println("Answer from server: " + inline);
                System.out.println("Type something and press enter: ");
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
