package cn.connie.algorithm.sort;

import java.util.Arrays;

public class Sort {

    public static void main(String[] args) {
        int[] arr = {10, 9, 8, 7, 11, 5, 4, 3, 22, 1};
//        insertSort(arr);
        int[] newArr = heapSort(arr);
        System.out.println(Arrays.toString(newArr));
    }

    /**
     * 冒泡排序,时间复杂度为O(n^2),空间复杂度为O(1)
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int forCount = 0;
        // 外层循环遍历size-1次轮询
        for (int i = 0; i < arr.length - 1; i++) {
            //内层循环做数据交换
            for (int j = 0; j < arr.length - i - 1; j++) {
                //如果current>next,则交换current和next数据
                if (arr[j] > arr[j + 1]) {
                    SwapUtils.swap(arr, j, j + 1);
                }
                ++forCount;
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("forCount = [" + forCount + "]");

        int calCount = 0;
        for (int i = 0; i < arr.length; i++) {
            calCount += i;
        }
        System.out.println("calCount = [" + calCount + "]");
    }

    /**
     * 选择排序
     */
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int forCount = 0;
        for (int i = 0; i < arr.length; i++) {
            //保存最小的数的下表索引
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
                ++forCount;
            }

            //将当前位置的数和最小的数据的位置交换
            SwapUtils.swap(arr, i, minIndex);
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("forCount = [" + forCount + "]");
    }


    /**
     * 插入排序
     */
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int forCount = 0;
        int current;
        for (int i = 0; i < arr.length - 1; i++) {
            // 当前要插入的元素
            current = arr[i + 1];
            // 插入元素的上一个元素下标
            int preIndex = i;
            //若上一个元素大于当前的数,循环左移直到跳出循环条件
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                //记录移动的之后的下表位置
                preIndex--;
                //记录循环次数
                forCount++;
            }
            arr[preIndex + 1] = current;
        }
        System.out.println(Arrays.toString(arr));
        System.out.println("forCount = [" + forCount + "]");
    }

    /**************高级排序***********/


    /**
     * 归并排序算法，分治法
     */
    public static int[] mergeSort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        //精华部分:递归分割数组,然后合并排序后的数据
        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 排序、合并left和right数组
     */
    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] > right[j]) {
                result[index] = right[j++];
            } else {
                result[index] = left[i++];
            }
        }
        return result;
    }

    /**
     * 堆排序
     *
     * @return
     */
    public static int[] heapSort(int[] arr) {
        int len = arr.length;
        System.out.println("构建的堆之前");
        System.out.println(Arrays.toString(arr));
        //1.构建大顶堆。（这个构建大顶堆中有调整的递归方法）
        buildMaxHeap(arr);
        //2. 将顶部的元素，与无序区的最后一个元素交换位置。
        System.out.println("构建的堆之后");
        System.out.println(Arrays.toString(arr));


        System.out.println("开始进行堆顶和最后一个元素调换");
        while (len > 0) {
            SwapUtils.swap(arr, 0, len - 1);// 0表示大顶元素，len-1表示最后一个元素
            len--;// 无序区 的长度减少一位
            changeHeap(arr, 0);//继续调整
            System.out.println("len = " + len + " " + Arrays.toString(arr));
        }
        System.out.println("结束进行堆顶和最后一个元素调换");
        return arr;
    }

    /**
     * 构建一个最大堆
     */
    public static void buildMaxHeap(int[] arr) {
        int len = arr.length / 2;
        for (int i = len; i >= 0; i--) {
            changeHeap(arr, i);
        }
    }

    /**
     * 调整大顶堆
     */
    private static void changeHeap(int[] arr, int i) {
        int maxIndex = i;
        int len = arr.length;

        int leftIndex = i * 2;//左子树的索引
        int rightIndex = leftIndex + 1;// 右子树的索引
        //如果有左子树。且左子树大于父节点，则将最大指针指向左子树
        if (leftIndex < len && arr[leftIndex] > arr[maxIndex]) {
            maxIndex = leftIndex;
        }
        //如果有右子树。且右子树大于父节点，则将最大指针指向左子树
        if (rightIndex < len && arr[rightIndex] > arr[maxIndex]) {
            maxIndex = rightIndex;
        }
        //如果父节点不是最大值，则将父节点与最大值交换，这样才能保证我们的父节点是最大的，构建成为一个大顶堆。
        if (maxIndex != i) {
            SwapUtils.swap(arr, maxIndex, i);
            changeHeap(arr, maxIndex);
        }
    }

}
