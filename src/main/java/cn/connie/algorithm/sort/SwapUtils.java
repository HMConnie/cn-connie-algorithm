package cn.connie.algorithm.sort;

public class SwapUtils {

    private SwapUtils() {

    }

    /**
     * 交换数组中i和j的值
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        if (i > arr.length - 1 || i < 0) {
            return;
        }
        if (j > arr.length - 1 || j < 0) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
