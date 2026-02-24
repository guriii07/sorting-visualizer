package com.sortvisualizer.algorithms;
import com.sortvisualizer.model.ArrayState;
import java.util.Arrays;

public class RadixSort implements ISortingAlgorithm {
    @Override
    public String getName() { return "Radix Sort"; }

    @Override
    public void sort(int[] array, ArrayState state) {
        int m = getMax(array);
        for (int exp = 1; m / exp > 0; exp *= 10) {
            countSort(array, array.length, exp, state);
            state.sleep(); // Pause between digits
        }
    }

    private int getMax(int[] array) {
        int mx = array[0];
        for (int i = 1; i < array.length; i++)
            if (array[i] > mx) mx = array[i];
        return mx;
    }

    private void countSort(int[] array, int n, int exp, ArrayState state) {
        int[] output = new int[n];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) count[(array[i] / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            array[i] = output[i];
            state.setSwapping(i, i); // Visual feedback
            state.sleep();
        }
    }
}