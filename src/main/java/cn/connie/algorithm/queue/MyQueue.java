package cn.connie.algorithm.queue;

import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class MyQueue {
    /**
     * add(E e) 插入队尾，插入失败，抛出异常
     * offer(E e) 插入队尾，插入到失败,返回false
     */
    public static void main(String[] args) {

//        ArrayDeque<String> queue = new ArrayDeque<>();
//        ArrayBlockingQueue<String> blockQueue1 = new ArrayBlockingQueue<String>(10);
//        LinkedBlockingDeque<String> blockQueue2 = new LinkedBlockingDeque<>();
//        PriorityBlockingQueue<String> blockQueue3 = new PriorityBlockingQueue<>(); //优先级队列会将数据排序
//        DelayQueue blockQueue4 = new DelayQueue();
//        testQueue1();

        testPriority();
    }

    /**
     * 有序的队列
     */
    public static void testPriority() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < 10; i++) {
            int value = new Random().nextInt(100);
            System.out.print(value + ",");
            queue.add(Integer.valueOf(value));
        }
        System.out.println();

        while (queue.size() > 0) {
            System.out.print(queue.poll()+",");
        }
        System.out.println();

    }

    public static void testQueue() {
        BlockQueueTask.Producer producer = new BlockQueueTask.Producer();
        BlockQueueTask.Customer customer = new BlockQueueTask.Customer();
        producer.start();
        customer.start();
    }

    public static void testQueue1() {
        CustomQueueTask.Producer producer = new CustomQueueTask.Producer();
        CustomQueueTask.Customer customer = new CustomQueueTask.Customer();
        producer.start();
        customer.start();
    }

    /**
     * 加锁
     */
    public static void queueLock() {
        final ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
