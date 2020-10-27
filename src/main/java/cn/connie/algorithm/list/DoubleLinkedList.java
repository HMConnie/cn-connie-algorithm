package cn.connie.algorithm.list;

/**
 * 双向链表
 */
public class DoubleLinkedList<T> {

    private int length;
    private Node<T> head;
    private Node<T> last;

    public DoubleLinkedList() {
        this.head = new Node<>();
        this.last = new Node<>();
        this.length = 0;
    }

    public int size() {
        return this.length;
    }

    public boolean isEmpty() {
        return this.length == 0;
    }


    public void insert(T data) {
        if (data == null) {
            return;
        }
        if (isEmpty()) {
            head = new Node<>(data);
            last = head;
            length++;
        } else {
            Node<T> newNode = new Node<>(data);
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
            length++;
        }
    }

    public void remove(int index) {
        if (index < 0 || index >= length) {
            return;
        }
        int targetCount = 0;
        Node targetNode = head;
        while (targetCount < length) {
            if (targetCount == index) {
                break;
            }
            targetNode = targetNode.next;
            targetCount++;
        }
        remove(targetNode, targetCount);
    }

    public void remove(T data) {
        if (data == null || isEmpty()) {
            return;
        }
        int targetCount = 0;
        Node targetNode = head;
        while (targetCount < length) {
            if (targetNode.data.equals(data)) {
                break;
            }
            targetNode = targetNode.next;
            targetCount++;
        }

        remove(targetNode, targetCount);
    }

    /***
     * 删除操作
     */
    private void remove(Node<T> targetNode, int targetCount) {
        if (targetNode == null || targetCount < 0 || targetCount >= length) {
            return;
        }
        if (targetCount == 0 && targetCount == length - 1) {//删除最后一个元素
            head = null;
            last = null;
        } else if (targetCount == 0) {//移除第一个
            targetNode.next.prev = null;
            head = targetNode.next;
        } else if (targetCount == length - 1) {//移除最后一个
            targetNode.prev.next = null;
            last = targetNode.prev;
        } else {
            targetNode.prev.next = targetNode.next;
            targetNode.next.prev = targetNode.prev;
        }
        length--;
    }

    public T get(int index) {
        if (index < 0 || index >= length) {
            return null;
        }
        int targetCount = 0;
        Node targetNode = head;
        while (targetCount < length) {
            if (targetCount == index) {
                break;
            }
            targetNode = targetNode.next;
            targetCount++;
        }
        return targetNode != null ? (T) targetNode.data : null;
    }

    private class Node<T> {

        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node() {
        }

        public Node(T data) {
            this.data = data;
        }
    }
}
