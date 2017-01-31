package com.andersen.integrtest;

import org.junit.Assert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

class TcpClient implements Runnable {
    @Override
    public void run() {
        try {
            int count = 0;

            try (
                    Socket clientSocket = new Socket("127.0.0.1", 9898);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"))) {
                //noinspection InfiniteLoopStatement
                while (count < 10) {
                    String line = "hello world";
                    out.write(line);
                    out.write("\n");
                    out.flush();
                    String inline = in.readLine();
                    Assert.assertEquals("HELLO WORLD", inline);
                    count++;
                }
                clientSocket.close();

            } catch (Exception x) {
                x.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
