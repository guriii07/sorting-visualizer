package com.sortvisualizer.algorithms;
import com.sortvisualizer.model.ArrayState;

public class SelectionSort implements ISortingAlgorithm {
    @Override
    public String getName() { return "Selection Sort"; }

    @Override
    public void sort(int[] array, ArrayState state) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                // Visual: Compare current minimum with scanner
                state.setComparing(min_idx, j);
                state.sleep(); 

                if (array[j] < array[min_idx]) {
                    min_idx = j; // Found new minimum
                }
            }
            
            // Visual: Swap found minimum with the first element
            if (min_idx != i) {
                state.setSwapping(i, min_idx);
                int temp = array[min_idx];
                array[min_idx] = array[i];
                array[i] = temp;
                state.sleep();
            }
        }
        state.clearHighlights();
    }
}