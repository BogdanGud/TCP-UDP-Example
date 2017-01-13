package com.andersen.tcpudp;

import java.io.IOException;
import java.net.*;

class UDPSocketService {
    void start() {
        try (DatagramSocket serverSocket = new DatagramSocket(9898)) {
            byte[] sendData;
            //noinspection InfiniteLoopStatement
            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                System.out.println("Received form client: " + sentence.trim());
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                String capitalizedSentence = sentence.toUpperCase();
                sendData = capitalizedSentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        } catch (IOException ex) {
            System.out.println("IOException caught: " + ex);
        }
    }
}
