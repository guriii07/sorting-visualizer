package com.sortvisualizer.algorithms;
import com.sortvisualizer.model.ArrayState;

public interface ISortingAlgorithm {
    void sort(int[] array, ArrayState state);
    String getName();
}