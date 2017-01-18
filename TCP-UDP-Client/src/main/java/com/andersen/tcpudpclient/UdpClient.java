package com.andersen.tcpudpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

class UdpClient {
    void start(String internetAddress, int serverPort) {
        byte[] sendData;
        byte[] receiveData = new byte[1024];
        try (BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
             DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(internetAddress);
            //noinspection InfiniteLoopStatement
            while (true) {
                System.out.println("Type something and press enter: ");
                String sentence = keyboard.readLine();
                sendData = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, serverPort);
                socket.send(sendPacket);
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String incomingString = new String(receivePacket.getData());
                System.out.println("Answer from server: " + incomingString.trim());
            }
        } catch (UnknownHostException ex) {
            System.out.println("UnknownHostException caught " + ex);
        } catch (IOException e) {
            System.out.println("IOException" + e);
        }
    }
}
