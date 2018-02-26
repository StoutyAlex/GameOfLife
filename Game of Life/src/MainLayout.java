import javax.swing.*;

/**
 * Main Swing layout for the
 * GUI of the game
 */
public class MainLayout extends JPanel{

    /**
     * Start button
     */
    java.awt.Button start;

    /**
     * Stop button
     */
    java.awt.Button stop;

    /**
     * Clear button
     */
    java.awt.Button clear;

    /**
     * Control panel containing buttons and switched
     */
    javax.swing.JPanel controlPanel;

    /**
     * Grid panel containing cells
     */
    javax.swing.JPanel gridPanel;

    /**
     * Speed slider for the game loop speed
     */
    javax.swing.JSlider speedSlider;


    MainLayout(int x, int y) {
        createUIComponents();
    }

    private void createUIComponents() {

        gridPanel = new javax.swing.JPanel();
        controlPanel = new javax.swing.JPanel();
        start = new java.awt.Button();
        stop = new java.awt.Button();
        clear = new java.awt.Button();
        speedSlider = new javax.swing.JSlider();

        speedSlider.setMinimum(50);
        speedSlider.setMaximum(1000);

        setLayout(new java.awt.BorderLayout());

        gridPanel.setLayout(null);
        add(gridPanel, java.awt.BorderLayout.CENTER);

        start.setLabel("Start");
        controlPanel.add(start);

        stop.setLabel("Stop");
        controlPanel.add(stop);

        clear.setLabel("Clear");
        controlPanel.add(clear);
        controlPanel.add(speedSlider);

        add(controlPanel, java.awt.BorderLayout.PAGE_END);
    }
}
