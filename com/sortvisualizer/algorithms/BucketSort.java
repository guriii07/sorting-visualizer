package com.sortvisualizer.algorithms;
import com.sortvisualizer.model.ArrayState;
import java.util.ArrayList;
import java.util.Collections;

public class BucketSort implements ISortingAlgorithm {
    @Override
    public String getName() { return "Bucket Sort"; }

    @Override
    public void sort(int[] array, ArrayState state) {
        int n = array.length;
        if (n <= 0) return;

        int max = 0;
        for (int i : array) max = Math.max(max, i);
        
        // 1. Create buckets
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>(n);
        for (int i = 0; i < n; i++) buckets.add(new ArrayList<>());

        // 2. Scatter
        for (int i = 0; i < n; i++) {
            int bucketIdx = (array[i] * n) / (max + 1);
            buckets.get(bucketIdx).add(array[i]);
            state.setComparing(i, i); // Visual scatter
            state.sleep();
        }

        // 3. Sort Buckets & Gather
        int index = 0;
        for (ArrayList<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int val : bucket) {
                array[index++] = val;
                state.setSwapping(index-1, index-1); // Visual gather
                state.sleep();
            }
        }
    }
}