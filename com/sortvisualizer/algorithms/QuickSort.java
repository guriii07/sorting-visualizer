package com.sortvisualizer.algorithms;
import com.sortvisualizer.model.ArrayState;

public class QuickSort implements ISortingAlgorithm {
    @Override
    public String getName() { return "Quick Sort"; }

    @Override
    public void sort(int[] array, ArrayState state) {
        sort(array, 0, array.length - 1, state);
    }

    private void sort(int[] array, int low, int high, ArrayState state) {
        if (low < high) {
            int pi = partition(array, low, high, state);
            sort(array, low, pi - 1, state);
            sort(array, pi + 1, high, state);
        }
    }

    private int partition(int[] array, int low, int high, ArrayState state) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            state.setComparing(j, high);
            state.sleep();
            if (array[j] < pivot) {
                i++;
                state.setSwapping(i, j);
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                state.sleep();
            }
        }
        state.setSwapping(i + 1, high);
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
}