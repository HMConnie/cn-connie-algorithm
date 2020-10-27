package cn.connie.algorithm.list;

/**
 * 单链表
 */
public class CustomLinkedList<T> {
    private int pos;//索引下标
    private int count;//数组长度
    private Node<T> headNode;//头节点

    public CustomLinkedList() {
        this.pos = 0;
        this.count = 0;
    }

    public boolean isEmpty() {
        return this.count == 0 && this.headNode == null;
    }

    public int size() {
        return this.count;
    }

    public void insert(T data) {
        if (this.isEmpty()) {
            headNode = new Node<>(data);
            this.count++;
        } else {
            this.pos = 0;
            headNode.add(data);
        }
    }


    public void remove(int index) {
        if (this.isEmpty()) {
            return;
        }
        if (index < 0 || index >= this.count) {
            return;
        }
        if (index == 0) {
            Node temp = this.headNode;
            this.headNode = this.headNode.next;
            temp.next = null;
            this.count--;
        } else {
            this.pos = 0;
            headNode.remove(headNode, index);
        }

    }

    public void replace(int index, T data) {
        if (data == null || index < 0 || index >= count) {
            return;
        }

        this.pos = 0;
        this.headNode.replace(index, data);
    }

    public T get(int index) {
        if (index < 0 || index >= this.count) {
            return null;
        }
        this.pos = 0;
        return headNode.get(index);
    }

    public boolean contains(T data) {
        if (data == null) {
            return false;
        }

        this.pos = 0;
        return headNode.contains(data);
    }

    public int indexOf(T data) {
        if (data == null || this.isEmpty()) {
            return -1;
        }
        this.pos = 0;
        return headNode.find(data);
    }

    private class Node<T> {
        private T data;
        private Node<T> next;


        public Node(T data) {
            this.data = data;
        }

        /**
         * 将数据添加到最后到一个节点
         */
        private void add(T data) {
            if (this.next == null) {
                this.next = new Node(data);
                CustomLinkedList.this.count++;
            } else {
                this.next.add(data);//递归添加
            }
        }

        private void remove(Node<T> pre, int index) {
            if (CustomLinkedList.this.pos++ == index) {
                // 把前一个节点指向下一个节点,删除当前的next指向
                pre.next = this.next;
                this.next = null;
                CustomLinkedList.this.count--;
            } else {
                this.next.remove(this, index);//递归删除
            }
        }

        private void replace(int index, T data) {
            if (CustomLinkedList.this.pos++ == index) {
                this.data = data;
            } else {
                this.next.replace(index, data);//递归替换
            }

        }

        private T get(int index) {
            if (CustomLinkedList.this.pos++ == index) {
                return this.data;
            } else {
                return this.next.get(index);//递归获取
            }
        }

        private int find(T data) {
            if (this.data.equals(data)) {
                return pos;
            } else {
                if (this.next != null) {
                    CustomLinkedList.this.pos++;
                    return this.next.find(data);//递归寻找
                }
                return -1;
            }
        }

        private boolean contains(T data) {
            if (this.data.equals(data)) {
                return true;
            } else {
                if (this.next != null) {
                    return this.next.contains(data);
                }
                return false;
            }
        }


    }


}
