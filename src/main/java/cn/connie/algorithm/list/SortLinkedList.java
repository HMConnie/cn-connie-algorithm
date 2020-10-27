package cn.connie.algorithm.list;

import java.util.Comparator;

/**
 * 有序单链表
 */
public class SortLinkedList<T> {


    private int length;
    private Node<T> head;
    private Comparator<T> comparator;

    public SortLinkedList(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return this.length;
    }

    public void insert(T data) {
        Node newNode = new Node(data);
        Node prev = null;//记录上一个节点
        Node current = head;//记录当前节点
        while (current != null && comparator.compare((T) newNode.data, (T) current.data) > 0) {
            prev = current;
            current = current.next;
        }
        //判断插入的上一个节点是否为空
        if (prev == null) {
            head = newNode;//上一个节点为空,则将要插入的节点为首节点
        } else {
            prev.next = newNode;//上一个的节点的next指向为新节点
        }
        newNode.next = current;//新节点的下一个节点为记录的当前节点
        length++;
    }


    public T get(int index) {
        if (index < 0 || index >= length) {
            return null;
        }
        int position = 0;
        Node targetNode = head;
        while (position < length) {
            if (position == index) {
                break;
            }
            targetNode = targetNode.next;
            position++;
        }
        return targetNode != null ? (T) targetNode.data : null;
    }

    private class Node<T> {
        public T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }
}
