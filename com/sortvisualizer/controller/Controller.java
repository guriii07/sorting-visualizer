package com.sortvisualizer.controller;

import com.sortvisualizer.algorithms.*;
import com.sortvisualizer.model.ArrayState;
import com.sortvisualizer.view.VisualizationPanel;
import javax.swing.*;
import java.util.Random;

public class Controller {
    private ArrayState state;
    private VisualizationPanel panel;
    private ISortingAlgorithm currentAlgo;
    private Thread sortingThread;

    public Controller(ArrayState state, VisualizationPanel panel) {
        this.state = state;
        this.panel = panel;
        this.currentAlgo = new BubbleSort(); 
    }

    public void startSorting() {
        if (sortingThread != null && sortingThread.isAlive()) {
             // If already running, just ensure we aren't paused
             if (state.isPaused()) togglePause();
             return;
        }
        
        state.setPaused(false);
        
        sortingThread = new Thread(() -> {
            currentAlgo.sort(state.getArray(), state);
            SwingUtilities.invokeLater(() -> panel.repaint());
        });
        sortingThread.start();
    }

    public void togglePause() {
        boolean isPaused = state.isPaused();
        state.setPaused(!isPaused);
    }
    
    public void generateArray(int size) {
        // Stop any existing sort before resetting
        if (sortingThread != null && sortingThread.isAlive()) {
            // Deprecated but effective for simple reset
            sortingThread.stop(); 
        }
        
        int[] arr = new int[size];
        Random rand = new Random();
        for(int i=0; i<size; i++) arr[i] = rand.nextInt(500) + 10;
        state.setArray(arr);
        state.clearHighlights();
        state.setSortedUpTo(-1);
        panel.repaint();
    }
}