package cn.connie.algorithm.sort;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomLinkedList<T> {

    private int theSize;
    private int modCount;
    private Node<T> beginMarker;
    private Node<T> endMarker;


    public CustomLinkedList() {
        doClear();
    }

    private void doClear() {
        beginMarker = new Node<T>(null, null, null);
        endMarker = new Node<T>(beginMarker, null, null);
        beginMarker.next = endMarker;
        theSize = 0;
        modCount++;
    }


    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    private void clear() {
        doClear();
    }

    public Iterator iterator() {
        return new LinkedListIterator();
    }

    public void addBefore(Node<T> node, T data) {
        Node<T> newNode = new Node<T>(node.pre, node, data);
        newNode.pre.next = newNode;
        node.pre = newNode;
        theSize++;
        modCount++;
    }

    public T remove(Node<T> node) {
        node.next.pre = node.pre;
        node.pre.next = node.next;
        theSize--;
        modCount++;
        return node.data;
    }


    private Node<T> getNode(int idx, int lower, int higher) {

        Node<T> node;

        if (idx < lower || idx > higher) {
            throw new IndexOutOfBoundsException();
        }

        if (idx < (size() / 2)) {
            node = beginMarker.next;
            for (int i = 0; i < idx; i++) {
                node = node.next;
            }
        } else {
            node = endMarker;
            for (int i = size(); i > idx; i--) {
                node = node.pre;
            }
        }

        return node;

    }


    public static class Node<T> {

        public Node<T> pre;
        public Node<T> next;
        public T data;

        public Node(Node<T> pre, Node<T> next, T data) {
            this.pre = pre;
            this.next = next;
            this.data = data;
        }


    }

    public class LinkedListIterator implements Iterator<T> {

        private Node<T> current = beginMarker.next;

        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext() {
            return current != endMarker;
        }

        public T next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T data = current.data;
            current = current.next;
            okToRemove = true;
            return data;
        }

        public void remove() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }

            if (!okToRemove) {
                throw new IllegalStateException();
            }
            CustomLinkedList.this.remove(current);
            expectedModCount++;
            okToRemove = false;

        }
    }
}
