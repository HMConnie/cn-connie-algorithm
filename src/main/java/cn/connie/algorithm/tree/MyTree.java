package cn.connie.algorithm.tree;

/**
 * 手写二叉树
 */
public class MyTree {

    public static void main(String[] args) {

        CustomTree customTree = new CustomTree();
        customTree.insert(6);
        customTree.insert(4);
        customTree.insert(8);
        customTree.insert(5);
        customTree.insert(7);
        customTree.insert(10);
        customTree.insert(9);
        customTree.insert(11);

        System.out.println("size = " + customTree.size());

        customTree.remove(8);
        Node node = customTree.find(5);
        System.out.println("node = " + node.iData);

        customTree.iterator().printPostOrder(customTree.root);
    }


    public static class CustomTree {

        private Node root;//根节点
        private int length;

        public CustomTree() {

        }

        /**
         * 不能插入重复的数据
         */
        public void insert(int data) {
            if (exist(data)) {
                return;
            }
            Node newNode = new Node();
            newNode.iData = data;
            if (root == null) {
                root = newNode;
            } else {
                Node parent = root;
                while (true) {
                    if (data < parent.iData) {
                        if (parent.leftChild == null) {
                            break;
                        }
                        parent = parent.leftChild;
                    } else {
                        if (parent.rightChild == null) {
                            break;
                        }
                        parent = parent.rightChild;
                    }
                }

                if (parent.iData > data) {
                    parent.leftChild = newNode;
                } else {
                    parent.rightChild = newNode;
                }
            }
            length++;
        }

        public void remove(int data) {
            if (root == null) {
                return;
            }

            Node current = root;
            Node parent = root;
            boolean isLeftChild = false;
            while (current.iData != data) {
                parent = current;
                if (data < current.iData) {
                    current = current.leftChild;
                    isLeftChild = true;
                } else {
                    current = current.rightChild;
                    isLeftChild = false;
                }
                if (current == null) {
                    return;//没有查找到节点
                }
            }

            if (current.leftChild == null && current.rightChild == null) { //1,左右都空,直接删除
                if (current.equals(root)) {
                    root = null;
                } else {
                    if (isLeftChild) {
                        parent.leftChild = null;
                    } else {
                        parent.rightChild = null;
                    }
                }
            } else if (current.leftChild == null && current.rightChild != null) {//2,左空右不为空,直接移动右节点到当前节点
                if (current.equals(root)) {
                    root = root.rightChild;
                } else {
                    if (isLeftChild) {
                        parent.leftChild = current.rightChild;
                    } else {
                        parent.rightChild = current.rightChild;
                    }
                }

            } else if (current.leftChild != null && current.rightChild == null) {//3,左空右不为空,直接移动左节点到当前节点
                if (current.equals(root)) {
                    root = root.leftChild;
                } else {
                    if (isLeftChild) {
                        parent.leftChild = current.leftChild;
                    } else {
                        parent.rightChild = current.leftChild;
                    }
                }

            } else {//4,左右都不会为空，寻找右子树的最小节点移动到当前节点
                Node rightMinChild = current.rightChild; //寻找右子树的最小的节点
                Node rightMinParent = current.rightChild;//右子树的最小的节点父级节点
                while (rightMinChild.leftChild != null) {
                    rightMinParent = rightMinChild;
                    rightMinChild = rightMinChild.leftChild;
                }
                if (rightMinChild.equals(current.rightChild)) {//无右子树，只有一个右节点
                    rightMinChild.leftChild = current.leftChild;
                } else {//有右子树
                    rightMinParent.leftChild = rightMinChild.rightChild;//删除右子树最小的节点
                    rightMinChild.leftChild = current.leftChild;
                    rightMinChild.rightChild = current.rightChild;
                }

                if (current.equals(root)) {
                    root = rightMinChild;
                } else {
                    if (isLeftChild) {//删除当前节点的父级节点左右子节点重新赋值
                        parent.leftChild = rightMinChild;
                    } else {
                        parent.rightChild = rightMinChild;
                    }
                }


            }

            length--;

        }


        public Node find(int data) {
            if (root == null) {
                return null;
            }
            Node current = root;
            while (data != current.iData) {
                if (data < current.iData) {
                    current = current.leftChild;
                } else {
                    current = current.rightChild;
                }
                if (current == null) {
                    break;
                }
            }
            return current;
        }

        public boolean exist(int data) {
            return find(data) != null;
        }

        public int size() {
            return this.length;
        }

        public Iterator iterator() {
            return new Iterator(root);
        }

        private static class Iterator {

            private Node parent;//最左边的子节点的父级节点
            private Node current;//最左边的子节点

            public Iterator(Node root) {
                this.current = root;
                this.parent = root;
                while (current.leftChild != null) {
                    parent = current;
                    current = current.leftChild;
                }
            }


            public boolean hasNext() {
                return current != null;
            }

            /**
             * 先序遍历  左-中-右
             */
            public Node next() {
                return null;
            }

            /**
             * 先序遍历：遍历顺序规则为【根左右】
             */
            public void printPreOrder(Node current) {
                if (current == null) {
                    return;
                }
                System.out.print(current.iData + " ");
                printPreOrder(current.leftChild);
                printPreOrder(current.rightChild);
            }

            /**
             * 中序遍历：遍历顺序规则为【左根右】
             */
            public void printMidOrder(Node current) {
                if (current == null) {
                    return;
                }
                printMidOrder(current.leftChild);
                System.out.print(current.iData + " ");
                printMidOrder(current.rightChild);
            }

            /**
             * 后序遍历：遍历顺序规则为【左右根】
             */
            public void printPostOrder(Node current) {
                if (current == null) {
                    return;
                }
                printPostOrder(current.leftChild);
                printPostOrder(current.rightChild);
                System.out.print(current.iData + " ");
            }

        }
    }


    /**
     * 二叉树是基于数组和链表
     */
    private static class Node {

        Node leftChild;
        Node rightChild;//二叉树利用链表结构，有利于快速插入和删除
        int iData;//索引值，二叉树利用数组的索引结构，有利于快速查询
    }
}
