package com.sortvisualizer.algorithms;
import com.sortvisualizer.model.ArrayState;

public class InsertionSort implements ISortingAlgorithm {
    @Override
    public String getName() { return "Insertion Sort"; }

    @Override
    public void sort(int[] array, ArrayState state) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;
            
            state.setComparing(i, j);
            state.sleep();

            while (j >= 0 && array[j] > key) {
                state.setSwapping(j + 1, j);
                array[j + 1] = array[j];
                j = j - 1;
                state.sleep();
            }
            array[j + 1] = key;
        }
    }
}