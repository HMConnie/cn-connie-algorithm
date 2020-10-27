package cn.connie.algorithm.tree;


/***
 * 234树
 */
public class Tree234 {

    public static void main(String[] args) {
        Tree234 tree234 = new Tree234();
        tree234.insert(6);
        tree234.insert(4);
        tree234.insert(8);
        tree234.insert(5);
        tree234.insert(7);
        tree234.insert(10);
        tree234.insert(9);
        tree234.insert(11);

        Object[] array = tree234.find(9);
        for (Object object : array) {
            if (object instanceof Tree234Node) {
                ((Tree234Node) object).displayNode();
            } else {
                System.out.println("find Node = " + object);
            }

        }

    }


    private Tree234Node root = new Tree234Node();

    public void insert(int iData) {
        Tree234Node curNode = root; // 找插入位置的时候表示当前的节点的局部变量
        DataItem tempItem = new DataItem(iData); // 创建一个新的数据项对象
        //查找当前要插入的节点
        while (true) {
            if (curNode.isFull()) {//如果节点已经有3个数据项了，那么需要分裂节点
                split(curNode);//分裂当前的节点
                curNode = curNode.getParent();//分裂之后之前的节点变成子节点，所以获取父节点
                curNode = curNode.getNextChild(iData);
            } else if (curNode.isLeaf()) {//当前是叶子节点,并且节点数据项也没有超过3个，则能直接插入
                break;
            } else {
                curNode = curNode.getNextChild(iData);
            }
        }
        curNode.insertItem(tempItem);
    }

    /**
     * 分裂当前的节点
     */
    private void split(Tree234Node thisNode) {
        DataItem itemB, itemC;
        Tree234Node parent, child2, child3;
        int itemIndex;

        itemC = thisNode.removeItem(); // 当前节点中最大的数据项（removeItem方法
        // 默认是删除节点中最大的数据项） 并且已经清空了当前节点的该数据项
        itemB = thisNode.removeItem(); // 当前节点中中间的数据项 并且已经清空了当前节点的该数据项
        child2 = thisNode.disconnectChild(2); // 当前节点的2号子节点 已经断开了当前节点与2号子节点的连接
        child3 = thisNode.disconnectChild(3); // 当前节点的3号子节点 已经断开了当前节点与3号子节点的连接
        Tree234Node newRight = new Tree234Node(); // 新建一个右边的子节点

        if (thisNode == root) { // 如果要拆分的节点为根的话
            root = new Tree234Node(); // 创建一个新的根
            parent = root; // 父节点等于新的根
            root.connectChild(0, thisNode); // 然后让新的根节点与之前的节点相连 连在最左边的位置上
        } else { // 不是根的话
            parent = thisNode.getParent(); // 先获取要拆分节点的父节点
        }
        itemIndex = parent.insertItem(itemB); // 将要拆分节点的中间的数据插入到父节点中 并且获取到插入的索引
        int n = parent.getNumItems(); // 获取父节点中数据项的个数

        for (int j = n - 1; j > itemIndex; j--) { //
            Tree234Node temp = parent.disconnectChild(j); // 父节点和要拆分的接待你断开连接
            parent.connectChild(j + 1, temp); // 父节点和要拆分的原节点重新连接
            // 位置为原要拆分节点的中间的数据项在父节点中位置的左边
        }

        parent.connectChild(itemIndex + 1, newRight); // 然后在原要拆分节点新的位置的右边插入新的右边节点

        newRight.insertItem(itemC); // 原节点中最大的数据项 插入新的右节点中
        newRight.connectChild(0, child2); // 新的右节点和原要拆分节点的右边的两个子节点相连 分别放在新节点的 0
        // 1 位置上
        newRight.connectChild(1, child3);
    }

    public Object[] find(int key) {
        Tree234Node curNode = root;
        int childNum;
        while (true) {
            childNum = curNode.findItem(key);//获取查找的数据项的位置
            if (childNum != -1) {
                break;
            } else if (curNode.isLeaf()) {
                childNum = -1;
                break;
            } else {
                curNode = curNode.getNextChild(key);
            }
        }
        return new Object[]{curNode, childNum};

    }
}
