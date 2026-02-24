package com.sortvisualizer.algorithms;
import com.sortvisualizer.model.ArrayState;

public class MergeSort implements ISortingAlgorithm {
    @Override
    public String getName() { return "Merge Sort"; }

    @Override
    public void sort(int[] array, ArrayState state) {
        sort(array, 0, array.length - 1, state);
    }

    private void sort(int[] array, int l, int r, ArrayState state) {
        if (l < r) {
            int m = l + (r - l) / 2;
            sort(array, l, m, state);
            sort(array, m + 1, r, state);
            merge(array, l, m, r, state);
        }
    }

    private void merge(int[] array, int l, int m, int r, ArrayState state) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) L[i] = array[l + i];
        for (int j = 0; j < n2; ++j) R[j] = array[m + 1 + j];

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            state.setComparing(l + i, m + 1 + j);
            state.sleep();
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            state.setSwapping(k, k); // Highlight write
            k++;
        }
        while (i < n1) array[k++] = L[i++];
        while (j < n2) array[k++] = R[j++];
        
        state.clearHighlights();
    }
}