package com;

import com.thead.impl.FixedThreadPool;
import com.thead.impl.ScalableThreadPool;
import com.thread.inter.ThreadPool;

import java.util.concurrent.TimeUnit;

public class Main {

    private static void fixTread() {
        ThreadPool threadPool = new FixedThreadPool();

        threadPool.start();

        for(int i = 0; i < 20; ++i) {
            threadPool.execute( () -> {
                System.err.println("Task: " + Thread.currentThread().getName() + ";");

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });


        }
    }

    private static void scalableTread() {
        ThreadPool threadPool = new ScalableThreadPool(3, 10);

        threadPool.start();

        for(int i = 0; i < 40; ++i) {
            threadPool.execute( () -> {
                System.err.println("Task: " + Thread.currentThread().getName() + ";");

                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });


            try {
                TimeUnit.SECONDS.sleep(i % 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        scalableTread();
    }
}
