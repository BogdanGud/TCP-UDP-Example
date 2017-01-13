package com.andersen.tcpudp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class TCPSocketService extends Thread {
    private int clientNumber = 0;

    void startListener(int portNumber) {
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("The socket-listener is running");
            //noinspection InfiniteLoopStatement
            while (true) {
                Socket incoming = serverSocket.accept();
                Thread t = new Thread(new MyListener(incoming, clientNumber++));
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class MyListener implements Runnable {
        Socket socket;
        private int clientNumber;

        MyListener(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            log("New connection at " + socket);
        }

        public void run() {
            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"))) {
                while (true) {
                    String input = in.readLine();
                    log("Client ID: " + clientNumber + ", incoming message: " + input);
                    if (input == null) {
                        break;
                    }
                    String output = input.toUpperCase();
                    out.write(output);
                    out.write("\n");
                    out.flush();
                }
            } catch (IOException e) {
                log("Error handling client: " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e1) {
                    log("Couldn't close a socket" + e1);
                }
                log("Connection closed");
            }
        }

        private void log(String message) {
            System.out.println(message);
        }
    }
}
