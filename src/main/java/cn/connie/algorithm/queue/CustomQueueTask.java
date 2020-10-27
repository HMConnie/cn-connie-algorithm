package cn.connie.algorithm.queue;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CustomQueueTask {
    private static final int MAX_COUNT = 10;
    private static final PriorityQueue<Integer> PRIORITY_QUEUE = new PriorityQueue<>(MAX_COUNT);
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final Condition FULL_CONDITION = LOCK.newCondition();
    private static final Condition EMPTY_CONDITION = LOCK.newCondition();


    /***
     * 如果队列满了，需要wait
     * 一旦有需要生产数据，需要notify
     */
    public static class Producer extends Thread {

        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    Thread.sleep(1000);
                    LOCK.lock();
                    while (PRIORITY_QUEUE.size() == MAX_COUNT) {
                        System.out.println("生产等待.....");
                        FULL_CONDITION.await();
                    }
                    PRIORITY_QUEUE.offer(1);
                    EMPTY_CONDITION.signal();//唤醒消费者
                    System.out.println("Producer 生产数据了,数据 =  " + 1);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }
        }
    }

    /***
     * 如果队列为空，需要wait
     * 一旦有消费数据，需要notify
     */
    public static class Customer extends Thread {
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    Thread.sleep(5000);
                    LOCK.lock();
                    while (PRIORITY_QUEUE.size() == 0) {
                        System.out.println("消费等待.....");
                        EMPTY_CONDITION.await();
                    }
                    int count = PRIORITY_QUEUE.poll();
                    FULL_CONDITION.signal();//生产唤醒
                    System.out.println("Customer 消费数据了 ,数据 = " + count);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    LOCK.unlock();
                }
            }
        }
    }
}
