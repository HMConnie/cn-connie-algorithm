package cn.connie.algorithm.sort;

import java.io.IOException;

public class DiGui {
    public static void main(String[] args) throws IOException {

        System.out.println("sum = " + sum(5));
        System.out.println("tailSum = " + tailSum(5, 1));
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("halfSearch = " + halfSearch(arr, 0, arr.length - 1, 2));
    }

    /**
     * 普通递归
     *
     */

    public static int sum(int n) {
        if (n == 1) {
            return 1;
        }
        int sum =  n + sum(n - 1);//函数返回依赖于表达式
        return sum;
    }

    /**
     * 尾递归
     */

    public static int tailSum(int n, int sum) {
        if (n == 1) {
            return sum;
        }
        return tailSum(n - 1, n + sum);//函数返回不依赖于表达式
    }


    /**
     * 二分搜索,必须要求数组是有序的
     *
     * @return
     */
    public static int halfSearch(int[] arr, int low, int high, int searchValue) {
        if (arr.length == 0 || low > high) {
            return -1;
        }
        int mid = (low + high) / 2;
        if (searchValue == arr[mid]) {
            return mid;
        }

        int restLow = arr[mid] < searchValue ? mid + 1 : low; //重置low下标
        int restHigh = arr[mid] < searchValue ? high : mid - 1;//重置high下标
        return halfSearch(arr, restLow, restHigh, searchValue);
    }

}
