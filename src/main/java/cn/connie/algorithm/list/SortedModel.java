package cn.connie.algorithm.list;

import java.util.Comparator;

public class SortedModel {

    public static class SortedComparator implements Comparator<SortedModel> {
        @Override
        public int compare(SortedModel o1, SortedModel o2) {
            return o1.age - o2.age;
        }
    }

    public String name;
    public int age;

    public SortedModel(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "SortedModel{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
