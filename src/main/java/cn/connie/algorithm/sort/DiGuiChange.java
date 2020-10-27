package cn.connie.algorithm.sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 如果abc,实现字母调换出现不同单词
 * 例如 abc  -> abc,acb,bac,bca,cab,cba六种排列组合单词
 */
public class DiGuiChange {

    private static final char[] CHARS = new char[100];
    private static int SIZE;
    private static int COUNT;

    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = reader.readLine();
        SIZE = line.length();
        COUNT = 0;
        for (int i = 0; i < SIZE; i++) {
            CHARS[i] = line.charAt(i);
        }
        doChange(SIZE);
    }

    private static void doChange(int n) {
        if (n == 1) {
            return;
        }
        for (int i = 0; i < n; i++) {
            doChange(n - 1);
            if (n == 2) {
                println();
            }
            move(n);
        }

    }

    private static void move(int n) {
        int position = SIZE - n;
        char temp = CHARS[position];
        int j;
        for (j = position + 1; j < SIZE; j++) {
            CHARS[j - 1] = CHARS[j];
        }
        CHARS[j - 1] = temp;
    }

    private static void println() {
        if (COUNT < 99) {
            System.out.print(" ");
        }
        if (COUNT < 9) {
            System.out.print("  ");
        }
        System.out.print(++COUNT + ":");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(CHARS[i]);
        }
        System.out.print("  ");
        System.out.flush();
        if (COUNT % 6 == 0) {
            System.out.println();
        }
    }
}
