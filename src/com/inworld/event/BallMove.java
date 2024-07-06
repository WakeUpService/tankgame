package com.inworld.event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BallMove extends JFrame {
    MyPanel mp = null;

    public static void main(String[] args) {
        BallMove ballMove = new BallMove();
    }

    public BallMove() {
        mp = new MyPanel();
        this.add(mp);
        this.setSize(400, 300);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

class MyPanel extends JPanel implements KeyListener {
    private int x = 10;
    private int y = 10;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, 20, 20);//defaut color is black
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println((char)e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            y++;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            y--;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x++;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            x--;
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}