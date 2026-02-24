package com.sortvisualizer.algorithms;
import com.sortvisualizer.model.ArrayState;

public class BubbleSort implements ISortingAlgorithm {
    @Override
    public String getName() { return "Bubble Sort"; }

    @Override
    public void sort(int[] array, ArrayState state) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Visual: Compare
                state.setComparing(j, j + 1);
                state.sleep(); // <--- This now handles speed & pause!

                if (array[j] > array[j + 1]) {
                    // Visual: Swap
                    state.setSwapping(j, j + 1);
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    state.sleep(); 
                }
            }
            state.setSortedUpTo(n - i - 1);
        }
        state.clearHighlights();
        state.setSortedUpTo(0); // Mark all sorted
    }
}