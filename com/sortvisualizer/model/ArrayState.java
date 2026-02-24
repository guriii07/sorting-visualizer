package com.sortvisualizer.model;

public class ArrayState {
    private int[] array;
    private int comparingIndex1 = -1, comparingIndex2 = -1;
    private int swappingIndex1 = -1, swappingIndex2 = -1;
    private int sortedUpTo = -1; 
    
    // --- CONTROL FLAGS ---
    private volatile boolean isPaused = false;
    private volatile int delayMs = 50;

    public ArrayState(int size) {
        this.array = new int[size];
    }

    // --- THE MAGIC METHOD ---
    // Algorithms call this to wait. It handles speed AND pausing automatically.
    public void sleep() {
        try {
            Thread.sleep(delayMs); 
            while (isPaused) {
                Thread.sleep(100); // Wait here while paused
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // --- Setters ---
    public void setComparing(int i, int j) { this.comparingIndex1 = i; this.comparingIndex2 = j; }
    public void setSwapping(int i, int j) { this.swappingIndex1 = i; this.swappingIndex2 = j; }
    public void setSortedUpTo(int i) { this.sortedUpTo = i; }
    public void clearHighlights() { 
        comparingIndex1 = -1; comparingIndex2 = -1; 
        swappingIndex1 = -1; swappingIndex2 = -1; 
    }
    
    // --- Getters ---
    public int[] getArray() { return array; }
    public void setArray(int[] array) { this.array = array; }
    public void setPaused(boolean p) { this.isPaused = p; }
    public boolean isPaused() { return isPaused; }
    public void setDelay(int ms) { this.delayMs = ms; }
    
    public int getComparingIndex1() { return comparingIndex1; }
    public int getComparingIndex2() { return comparingIndex2; }
    public int getSwappingIndex1() { return swappingIndex1; }
    public int getSwappingIndex2() { return swappingIndex2; }
    public int getSortedUpTo() { return sortedUpTo; }
}