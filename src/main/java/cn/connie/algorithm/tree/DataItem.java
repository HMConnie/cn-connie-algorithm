package cn.connie.algorithm.tree;

public class DataItem {
    public int iData;

    public DataItem(int iData) {
        this.iData = iData;
    }

    // 显示数据
    public void displayItem() {
        System.out.print("/" + iData);
    }
}
