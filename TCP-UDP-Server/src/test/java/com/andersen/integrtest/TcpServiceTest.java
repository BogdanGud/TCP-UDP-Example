package com.andersen.integrtest;

import com.andersen.tcpudp.TCPSocketService;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TcpServiceTest {
    private static final int TEST_PORT = 9898;

    @Before
    public void initialize() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            new TCPSocketService().startListener(TEST_PORT);
            System.out.println("TCP Server started successfully by Test...");
        });
    }

    @Test
    public void mainTest() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executor.execute(new TcpClient());
            try {
                TimeUnit.MILLISECONDS.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
