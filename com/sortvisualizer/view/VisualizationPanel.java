package com.sortvisualizer.view;

import com.sortvisualizer.model.ArrayState;
import javax.swing.*;
import java.awt.*;

public class VisualizationPanel extends JPanel {
    private ArrayState state;

    public VisualizationPanel(ArrayState state) {
        this.state = state;
        setBackground(new Color(30, 30, 30)); // Dark Theme
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int[] array = state.getArray();
        if (array == null) return;

        int width = getWidth();
        int height = getHeight();
        int barWidth = Math.max(2, width / array.length);
        
        int maxVal = 1;
        for(int v : array) maxVal = Math.max(maxVal, v);

        for (int i = 0; i < array.length; i++) {
            int barHeight = (int) (((double)array[i] / maxVal) * (height - 50));
            int x = i * barWidth;
            int y = height - barHeight;

            // Color Logic
            if (i == state.getSwappingIndex1() || i == state.getSwappingIndex2()) {
                g2d.setColor(Color.RED);
            } else if (i == state.getComparingIndex1() || i == state.getComparingIndex2()) {
                g2d.setColor(Color.YELLOW);
            } else if (state.getSortedUpTo() != -1 && i >= state.getSortedUpTo()) {
                g2d.setColor(Color.GREEN);
            } else {
                g2d.setColor(new Color(100, 149, 237)); // Blue
            }
            g2d.fillRect(x, y, barWidth - 1, barHeight);
        }
    }
}