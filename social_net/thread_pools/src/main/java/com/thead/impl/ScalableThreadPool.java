package com.thead.impl;

import com.thread.inter.ThreadPool;
import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

public class ScalableThreadPool implements ThreadPool {

    static final int RESERVE = 3;

    int startId = 0, threadAmount = 0;
    Integer needClose = 0;

    Queue<Pair<Runnable, Integer>> tasks = new ArrayDeque< >();

    int min, max;

    public ScalableThreadPool(int min, int max) {

        if(min > max || min <= 0)
            throw new IllegalArgumentException("min less max or min less or equal nil");

        this.max = max;
        this.min = min;
    }

    @Override
    public void start() {
        for (int i = 0; i < min; ++i)
            new Thread(new ThreadUnit()).start();

        threadAmount = min;
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (tasks) {

            while (addThread());

            synchronized (needClose) {
                needClose = Math.min(threadAmount - tasks.size() + RESERVE, 0);
                System.err.println("needClose = " + needClose + "th am = " + threadAmount + "tsk sz = " + tasks.size());
            }

            tasks.add(new Pair(runnable, startId++));
            tasks.notifyAll();
        }
    }

    private boolean addThread() {
        if(threadAmount >= max || threadAmount + RESERVE >= tasks.size() )
            return false;

        System.err.println("added threadd");

        new Thread(new ThreadUnit()).start();

        ++threadAmount;

        return true;
    }

    class ThreadUnit implements Runnable {

        @Override
        public void run() {
            Pair<Runnable, Integer> r;

            while (true) {
                synchronized (tasks) {



                    while (tasks.isEmpty()) {
                        synchronized (needClose) {
                            if (needClose > 0) {
                                --needClose;
                                --threadAmount;
                                System.err.println("removed threadd");
                                return;
                            }
                        }
                        try {
                            tasks.wait();
                        } catch (InterruptedException ignored) {
                        }
                    }

                    r = tasks.remove();
                }

                try {
                    System.err.println("Thread: " + Thread.currentThread().getName() + " with task: " + r.getValue() + "; ammount = " + Thread.activeCount());

                    r.getKey().run();
                } catch (RuntimeException e) {

                }
            }
        }
    }
}