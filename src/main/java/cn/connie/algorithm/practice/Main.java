package cn.connie.algorithm.practice;

import java.util.*;

public class Main {


    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 4};
        int target = 6;

        System.out.println(Arrays.toString(sum(arr, target)));

    }


    /**
     * 题目需求：给定数组nums和一个目标target，请你在该数组中找出两个整数，并返回数组下表
     */
    private static int[] sum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] result = new int[nums.length];
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[count++] = i;
                    result[count++] = j;
                }
            }
        }
        return Arrays.copyOf(result, count);
    }


    /**
     * 采用hash算法
     */
    public static int[] sumOther(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> result = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
            if (map.containsKey(target - nums[i])) {
                result.add(i);
                result.add(map.get(target - nums[i]));
            }
        }
        Integer[] objects = result.toArray(new Integer[]{});
        int[] r = new int[result.size()];
        for (int i = 0; i < objects.length; i++) {
            r[i] = objects[i];
        }
        return r;

    }

}
