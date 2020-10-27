package cn.connie.algorithm.tree;

public class Tree234Node {
    private static final int ORDER = 4; //最大的子节点的个数
    private int numItems;
    private Tree234Node parent;
    private Tree234Node[] childArray = new Tree234Node[ORDER];//最大四个节点
    private DataItem[] itemArray = new DataItem[ORDER - 1];//每个节点最大存储三个数据

    public int getNumItems() {
        return numItems;
    }

    public Tree234Node getChild(int childNum) {
        return childArray[childNum];
    }

    public DataItem getItem(int index) {
        return itemArray[index];
    }

    public Tree234Node getParent() {
        return parent;
    }

    // 判断是否为叶节点
    public boolean isLeaf() {
        return childArray[0] == null;
    }

    // 判断该节点的数据项是否已满
    public boolean isFull() {
        return (numItems == ORDER - 1);
    }

    // 连接子节点 输入要插入的子节点的位置 还有子节点
    public void connectChild(int childNum, Tree234Node child) {
        childArray[childNum] = child; // 将要插入的子节点放入当前节点的子节点数组的对应的索引的位置上
        if (child != null) {
            child.parent = this; // 如果插入的节点不为空的话 还要设置新插入节点的父节点为当前节点
        }
    }

    // 拆分子节点 输入要拆分的子节点的位置
    public Tree234Node disconnectChild(int childNum) {
        Tree234Node tempNode = childArray[childNum]; // 先将要拆分的子节点做一个备份
        childArray[childNum] = null; // 然后将当前节点的指定子节点的位置 置空
        return tempNode; // 返回备份的节点
    }

    // 从当前节点中找一个数据项的位置 输入要查找的值
    public int findItem(int key) {
        for (int j = 0; j < ORDER - 1; j++) { // 遍历当前节点的所有数据项
            if (itemArray[j] == null) // 如果当前的位置没有数据了，那么以后的位置也是没有的
                break; // 退出当前的循环
            else if (itemArray[j].iData == key) // 如果找到了对应的值
                return j; // 返回对应大的索引
        }
        return -1; // 找不到 ，返回-1
    }

    // 获取下一个子节点 传入一个当前的节点还有一个要查找的数据项的值
    public Tree234Node getNextChild(int key) {
        int j;
        int numItems = getNumItems(); // 获取当前节点的数据项的个数
        for (j = 0; j < numItems; j++) { // 遍历
            if (key < getItem(j).iData) // 如果要查找的值小于当前数据项的值
                return getChild(j); // 返回当前数据项左边的子节点
        }
        // 如果找不到 则返回最后一个子节点
        return getChild(j);
    }

    // 从当前节点插入一个数据项 输入要插入的新的数据项
    // 调用该方法的一个前提：当前节点的数据项只有两个或者更少是可以继续插入数据项的（在插入之前会进行一定的检查和判断）
    public int insertItem(DataItem newItem) {
        numItems++; // 首先是数据项的个数加1
        int newKey = newItem.iData; // 获取到数据项的值
        for (int j = ORDER - 2; j >= 0; j--) { // 遍历
            if (itemArray[j] == null) // 如果当前位置的数据项为空的话
                continue; // 继续查找
            else { // 不为空的话
                int itsKey = itemArray[j].iData; // 首先获取当前位置的数据项的值
                if (newKey < itsKey) // 如果新的数据项的值小于当前的数据项
                    itemArray[j + 1] = itemArray[j]; // 将当前的数据项放在当前位置的后一个位置上
                else { // 否则 要插入的数据项大于当前的数据项
                    itemArray[j + 1] = newItem; // 将新插入的数据项放在当前位置的后一个位置上
                    return j + 1; // 返回插入数据项的索引
                }
            }
        }
        // 如果当前节点的数据项为空 或者插入的数据项是和之前已经存在的数据相比是最小的话
        itemArray[0] = newItem;// 将当前的数据项插入首位
        return 0; // 返回插入的索引值
    }

    // 删除数据项 返回被删除的数据项 没有指定要删除的位置 所以删除最后一个
    public DataItem removeItem() {
        DataItem temp = itemArray[numItems - 1]; // 先将当前的数据项中的最后一个做一个备份
        itemArray[numItems - 1] = null; // 然后当前节点该处的数据项置为空
        numItems--; // 数据个数减1
        return temp; // 返回备份
    }


    // 打印当前节点
    public void displayNode() {
        for (int j = 0; j < numItems; j++) // 遍历并打印所有的数据项
            itemArray[j].displayItem();
        System.out.println("/");
    }

}
