package com.stolicki.concurrency.deadlocks;

public class Main {

    public static final Object lock1 = new Object();
    public static final Object lock2 = new Object();

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }

    private static class Thread1 extends Thread {
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread1: Has lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
                System.out.println("Thread1: Waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread 1: Has lock1 and lock2");
                }
                System.out.println("Thread1: Released lock2");
            }
            System.out.println("Thread1: Released lock1. Exiting...");
        }
    }

    private static class Thread2 extends Thread {
        public void run() {
            synchronized (lock1) {
                System.out.println("Thread2: Has lock1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {

                }
                System.out.println("Thread2: Waiting for lock2");
                synchronized (lock2) {
                    System.out.println("Thread2: Has lock1 and lock2");
                }
                System.out.println("Thread2: releases lock2");
            }
            System.out.println("Thread2: Released lock1. Exiting...");
        }
    }
}