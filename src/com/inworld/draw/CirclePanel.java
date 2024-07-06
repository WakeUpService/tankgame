package com.inworld.draw;

import javax.swing.*;
import java.awt.*;

public class CirclePanel extends JPanel {
    @Override
    public void paint(Graphics g) {

        super.paint(g);
        //System.out.println("paint is doing....");
        g.drawOval(0,0,100,100);
    }
}
