package com.thead.impl;

import com.thread.inter.ThreadPool;
import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class FixedThreadPool implements ThreadPool {

    static final int THREAD_AMOUNT = 4;
    int startId = 0;

    Queue<Pair<Runnable, Integer>> tasks = new ArrayDeque< >();

    public void start() {
        for (int i = 0; i < THREAD_AMOUNT; ++i)
            new Thread(() -> {
                    Pair<Runnable, Integer> r;

                    while (true) {
                       synchronized (tasks) {
                            while (tasks.isEmpty()) {
                                try {
                                    tasks.wait();
                                } catch (InterruptedException ignored) {
                                }
                            }

                            r = tasks.remove();
                        }

                        try {
                            System.err.println("Thread: " + Thread.currentThread().getName() + " with task: " + r.getValue());

                            r.getKey().run();
                        } catch (RuntimeException e) {

                        }
                    }
            }, Integer.toString(i)).start();
    }

    public void execute(Runnable runnable) {
        synchronized (tasks) {
            tasks.add(new Pair(runnable, startId++));
            tasks.notifyAll();
        }

    }
}
