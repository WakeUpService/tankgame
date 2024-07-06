package com.inworld.draw;

import javax.swing.*;

public class CircleFrame extends JFrame {
    private CirclePanel circlePanel = null;
    public CircleFrame(){
        circlePanel = new CirclePanel();
        this.add(circlePanel);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
