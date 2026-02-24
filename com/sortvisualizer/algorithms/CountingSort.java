package com.sortvisualizer.algorithms;
import com.sortvisualizer.model.ArrayState;

public class CountingSort implements ISortingAlgorithm {
    @Override
    public String getName() { return "Counting Sort"; }

    @Override
    public void sort(int[] array, ArrayState state) {
        int n = array.length;
        if (n == 0) return;

        // Find max
        int max = array[0];
        for (int i = 1; i < n; i++) {
            if (array[i] > max) max = array[i];
            state.setComparing(i, i); // Just to show activity
            state.sleep();
        }

        int[] count = new int[max + 1];
        int[] output = new int[n];

        // Store count of each character
        for (int i = 0; i < n; i++) {
            count[array[i]]++;
            state.setComparing(i, i); // Highlight reading
            state.sleep();
        }

        // Change count[i] so that count[i] now contains actual position
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Build the output character array
        for (int i = n - 1; i >= 0; i--) {
            output[count[array[i]] - 1] = array[i];
            count[array[i]]--;
        }

        // Copy the output array to arr, so that arr now contains sorted numbers
        for (int i = 0; i < n; i++) {
            array[i] = output[i];
            state.setSwapping(i, i); // Highlight writing
            state.sleep();
        }
    }
}