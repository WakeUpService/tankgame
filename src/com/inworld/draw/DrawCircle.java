package com.inworld.draw;

import javax.swing.*;
import java.awt.*;

public class DrawCircle extends JFrame {
    private MyPanel mp = null;

    public static void main(String[] args) {
        new DrawCircle();
    }
    public DrawCircle(){
        mp = new MyPanel();
        this.add(mp);
        this.setSize(400,300);
        this.setVisible(true);

    }

}

class MyPanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(10, 10, 100, 100);
    }
}
