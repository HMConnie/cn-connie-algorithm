package cn.connie.algorithm.sort;

import java.util.*;

public class Main {

    /**
     * N个人，M次传递之后拿到热土豆的人出列，知道最后一个
     */
    private static void hotPotato(int n, int m) {

        LinkedList<Integer> linkedList = new LinkedList();
        for (int i = 0; i < n; i++) {
            linkedList.add(i + 1);
        }
        hotPotatoOut(linkedList, m);
    }

    /***
     *
     *
     *
     *
     */
    private static void hotPotatoOut(LinkedList<Integer> linkedList, int m) {
        if (linkedList == null || linkedList.isEmpty()) return;

        Iterator<Integer> iterator = linkedList.iterator();
        int count = 0;
        System.out.print("remove[ ");
        while (linkedList.size() > 1) {
            if (!iterator.hasNext()) {
                iterator = linkedList.iterator();
            }
            int item = -1;
            if (count++ <= m) {
                item = iterator.next();
            }
            if (count > m) {
                iterator.remove();
                count = 0;
                System.out.print(linkedList.size() > 1 ? item + " , " : item);
            }
        }
        System.out.print(" ]");
        System.out.println(" alive " + linkedList.get(0) + " ");
    }


    private static void printIterator(Iterator iterator) {
        if (iterator == null || !iterator.hasNext()) return;

        System.out.println(iterator.next());

        printIterator(iterator);
    }

    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//        hotPotato(100000, 3);
//        long dx = System.currentTimeMillis() - start;
//
//        System.out.println("dx = " + dx);

        moveCalc();

    }

    /**
     * 左移：左移一位相当于乘以2的一次方，左移n位相当于乘以2的n次方
     * 右移：右一位相当于除以2的一次方，右移n位相当于除以2的n次方
     */

    public static void moveCalc() {

//        List<String> list = new ArrayList<String>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        int mid = list.size() >> 1; //取半
//        System.out.println("mid = [" + mid + "]");

        // 左移：左移一位相当于乘以2的一次方，左移n位相当于乘以2的n次方
        // 右移：右一位相当于除以2的一次方，右移n位相当于除以2的n次方
//        int mid10 = 10 >> 1;
//        int mid10_2 = 10 / 2;
//
//
//
//
//        System.out.println("mid10 = [" + mid10 + "]");
//        System.out.println("mid10_2 = [" + mid10_2 + "]");

//        int mov16 = 16 >> 3;
//        System.out.println("mov16 = [" + mov16 + "]");

//        float move4 = 2 << 2;
//        System.out.println("move4=" + move4);
        int[] arr = new int[]{55, 99, 18, 92, 88};
        sort(arr);
    }

    /**
     * 插入排序
     *
     * @param array
     * @return
     */
    public static int[] sort(int[] array) {
        int[] newArray = Arrays.copyOf(array, array.length);
        for (int i = 0; i < newArray.length; i++) {
            for (int j = i + 1; j < newArray.length; j++) {
                int temp = newArray[i];
                if (Integer.valueOf(temp).compareTo(Integer.valueOf(newArray[j])) < 0) {
                    newArray[i] = newArray[j];
                    newArray[j] = temp;
                }
            }
        }
        return newArray;
    }
}
