package com.example.quiz.structure.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BubbleSort {

    public static void bubbleSort(int[] sort) {
        for (int i = 0; i < sort.length - 1; i++) {
            for (int j = 0; j < sort.length - 1 - i; j++) {
                if (sort[j] > sort[j + 1]) {
                    int temp = sort[j];
                    sort[j] = sort[j + 1];
                    sort[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(sort));
    }
}
