package com.sortvisualizer;

import com.sortvisualizer.controller.Controller;
import com.sortvisualizer.model.ArrayState;
import com.sortvisualizer.view.VisualizationPanel;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } 
        catch (Exception e) {}

        SwingUtilities.invokeLater(() -> {
            // 1. Setup Data
            ArrayState state = new ArrayState(100); 
            VisualizationPanel panel = new VisualizationPanel(state);
            Controller controller = new Controller(state, panel);
            controller.generateArray(100);

            // 2. Setup Frame
            JFrame frame = new JFrame("Fixed Sorting Visualizer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // 3. Setup Buttons
            JPanel buttonPanel = new JPanel();
            buttonPanel.setBackground(new Color(40, 40, 40)); 
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JComboBox<String> algoBox = new JComboBox<>(controller.getAlgorithmNames());
            algoBox.addActionListener(e -> controller.setAlgorithm(algoBox.getSelectedIndex()));
            JButton startBtn = new JButton("Start");
            JButton pauseBtn = new JButton("Pause/Resume");
            JButton resetBtn = new JButton("New Array");
            JSlider speedSlider = new JSlider(1, 100, 50);
            speedSlider.setBackground(new Color(40, 40, 40));

            startBtn.addActionListener(e -> controller.startSorting());
            pauseBtn.addActionListener(e -> controller.togglePause());
            resetBtn.addActionListener(e -> controller.generateArray(100));
            speedSlider.addChangeListener(e -> state.setDelay(101 - speedSlider.getValue()));

            buttonPanel.add(new JLabel("Algo:") {{ setForeground(Color.WHITE); }});
            buttonPanel.add(algoBox);
            buttonPanel.add(startBtn);
            buttonPanel.add(pauseBtn);
            buttonPanel.add(resetBtn);
            buttonPanel.add(new JLabel("Speed:") {{ setForeground(Color.WHITE); }});
            buttonPanel.add(speedSlider);

            // 4. Assemble
            frame.add(panel, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);

            // 5. Timer for Animation Loop (60 FPS)
            Timer timer = new Timer(16, e -> panel.repaint());
            timer.start();

            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
