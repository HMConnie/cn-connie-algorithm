package cn.connie.algorithm.queue;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockQueueTask {
    private static final ArrayBlockingQueue<Integer> BLOCK_QUEUE = new ArrayBlockingQueue<>(10);

    public static int mCount = 0;

    /**
     * put、take 多线程安全配对使用
     * offer、poll 多线程不安全配对使用
     *
     */
    public static class Producer extends Thread {

        @Override
        public void run() {
            super.run();
            while (true) {
                mCount++;
                try {
                    Thread.sleep(1000);
                    BLOCK_QUEUE.put(mCount);
                    System.out.println("Producer count = " + mCount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static class Customer extends Thread {

        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    Thread.sleep(5000);
                    Integer count = BLOCK_QUEUE.take();
                    if (count == null) {
                        continue;
                    }
                    System.out.println("Customer count = " + count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
