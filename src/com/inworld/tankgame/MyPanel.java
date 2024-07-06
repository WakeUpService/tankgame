package com.inworld.tankgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    Hero hero = null;
    Vector<EnemyTank> enemyTanks = new Vector<>();

    Vector<Bomb> bombs = new Vector<>();
    int enemyTankSize = 3;
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel() {
        hero = new Hero(100, 100);
        //hero.setSpeed(4);
        for (int i = 0; i < enemyTankSize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTank.setDirect(2);
            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirect());
            enemyTank.shots.add(shot);
            new Thread(shot).start();
            enemyTanks.add(enemyTank);
        }
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);

        if (hero.shot != null && hero.shot.isLive == true) {
            g.fill3DRect(hero.shot.x, hero.shot.y, 5, 5, false);
        }
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if(bomb.life > 6){
                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            } else if (bomb.life > 3) {
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
            } else {
                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
            }
            bomb.lifeDown();
            if(bomb.life == 0){
                bombs.remove(bomb);
            }
        }

        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.isLive) {
                        g.fill3DRect(shot.x, shot.y, 3, 3, false);
                    } else {
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }
    }

    //fuction of drawing a tank

    /**
     * @param x         x of a tank
     * @param y         y of a tank
     * @param g         the pen
     * @param direction the dirciton of a tank
     * @param type      hero tank or enemy tank
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type) {
        //different tank different color
        switch (type) {
            case 0: //hero tank
                g.setColor(Color.yellow);
                break;
            case 1: //hero tank
                g.setColor(Color.cyan);
                break;
        }
        //different tank different dirciton
        switch (direction) {
            case 0: //up direction
                g.fill3DRect(x, y, 10, 60, false);//left wheel
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//driver room
                g.fill3DRect(x + 30, y, 10, 60, false);//right wheel
                g.fillOval(x + 10, y + 20, 20, 20);//driver room's cover
                g.drawLine(x + 20, y + 30, x + 20, y);//barrel
                break;
            case 1: //right direction
                g.fill3DRect(x, y, 60, 10, false);//left wheel
                g.fill3DRect(x, y + 30, 60, 10, false);//driver room
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//right wheel
                g.fillOval(x + 20, y + 10, 20, 20);//driver room's cover
                g.drawLine(x + 30, y + 20, x + 60, y + 20);//barrel
                break;
            case 2: //down direction
                g.fill3DRect(x, y, 10, 60, false);//left wheel
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//driver room
                g.fill3DRect(x + 30, y, 10, 60, false);//right wheel
                g.fillOval(x + 10, y + 20, 20, 20);//driver room's cover
                g.drawLine(x + 20, y + 30, x + 20, y + 60);//barrel
                break;
            case 3: //left direction
                g.fill3DRect(x, y, 60, 10, false);//left wheel
                g.fill3DRect(x, y + 30, 60, 10, false);//driver room
                g.fill3DRect(x + 10, y + 10, 40, 20, false);//right wheel
                g.fillOval(x + 20, y + 10, 20, 20);//driver room's cover
                g.drawLine(x + 30, y + 20, x, y + 20);//barrel
                break;

            default:
                System.out.println("currently nothing to do");
        }

    }

    public void hitTank(Shot s, EnemyTank enemyTank) {
        switch (enemyTank.getDirect()) {
            case 0:
            case 2:
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 40
                        && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 60) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 60
                        && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 40) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                    Bomb bomb = new Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirect(0);
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            hero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            hero.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            hero.shotEnemyTank();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (hero.shot != null && hero.shot.isLive) {
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    hitTank(hero.shot, enemyTank);
                }
            }
            this.repaint();
        }
    }
}
