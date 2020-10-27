package cn.connie.algorithm.stack;

import java.util.Arrays;
import java.util.Stack;

public class MyStack {




    public static void main(String[] args){
        char[] chars = {'C','H','I','N','A'};
        System.out.println("args = " + Arrays.toString(chars) + "");
        Stack stack = new Stack();
        for (char c:chars) {
            stack.push(c);
        }

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }


}
