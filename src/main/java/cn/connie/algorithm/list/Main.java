package cn.connie.algorithm.list;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        testSortLinkedList();
    }

    public static void testSortLinkedList() {
        SortLinkedList<SortedModel> list = new SortLinkedList<>(new SortedModel.SortedComparator());
        list.insert(new SortedModel("哈哈哈", 11));
        list.insert(new SortedModel("哈哈哈", 3));
        list.insert(new SortedModel("哈哈哈", 4));
        list.insert(new SortedModel("哈哈哈", 22));
        list.insert(new SortedModel("哈哈哈", 2));
        println(list);
    }

    private static void testDoubleLinkedList() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        println(list);

        list.remove(0);
        println(list);

        list.remove(1);
        println(list);

        list.remove(0);
        println(list);

        list.remove(1);
        println(list);

        list.remove(0);
        println(list);
    }

    private static void testSingleLinkedList() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        list.insert(1);
        list.insert(2);
        list.insert(3);

        println(list);
//        list.remove(1);
//        println(list);

        list.remove(0);
        list.remove(0);
        list.remove(0);
        println(list);
//        System.out.println("find (1) = " + list.indexOf(1));
    }

    private static void println(SortLinkedList<SortedModel> list) {
        SortedModel[] array = new SortedModel[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        System.out.println("array = " + Arrays.toString(array));
    }

    private static void println(CustomLinkedList<Integer> list) {
        Integer[] array = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        System.out.println("array = " + Arrays.toString(array));
    }

    private static void println(DoubleLinkedList<Integer> list) {
        Integer[] array = new Integer[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        System.out.println("array = " + Arrays.toString(array));
    }
}
