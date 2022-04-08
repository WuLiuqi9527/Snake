package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel {

    int length;
    int[] snakeX = new int[300];
    int[] snakeY = new int[300];
    Dir dir;
    boolean isStart = false;
    boolean isDie = false;
    Timer timer;
    int foodX, foodY;
    int score;

    public void init() {
        length = 3;
        snakeX[0] = 175;
        snakeY[0] = 275;
        snakeX[1] = 150;
        snakeY[1] = 275;
        snakeX[2] = 125;
        snakeY[2] = 275;
        dir = Dir.RIGHT;
        foodX = 300;
        foodY = 200;
        score = 0;
    }

    public GamePanel() {
        init();
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_SPACE) {
                    if (isDie) {
                        init();
                        isDie = false;
                    } else {
                        isStart = !isStart;
                        repaint();
                    }
                }
                if (keyCode == KeyEvent.VK_UP) {
                    dir = Dir.UP;
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    dir = Dir.DOWN;
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    dir = Dir.LEFT;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    dir = Dir.RIGHT;
                }
            }
        });

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isStart && !isDie) {
                    for (int i = length - 1; i > 0; i--) {
                        snakeX[i] = snakeX[i - 1];
                        snakeY[i] = snakeY[i - 1];
                    }

                    switch (dir) {
                        case UP -> snakeY[0] = snakeY[0] - 25;
                        case DOWN -> snakeY[0] = snakeY[0] + 25;
                        case LEFT -> snakeX[0] = snakeX[0] - 25;
                        case RIGHT -> snakeX[0] = snakeX[0] + 25;
                        default -> {
                        }
                    }
                    boundsCheck();

                    if (snakeX[0] == foodX && snakeY[0] == foodY) {
                        length++;
                        foodX = (int) (Math.random() * 30 + 1) * 25;//[25, 750]
                        foodY = (new Random().nextInt(26) + 4) * 25;//[100, 725]
                        score += 10;
                    }

                    for (int i = 1; i < length; i++) {
                        if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                            isDie = true;
                        }
                    }
                    repaint();
                }
            }
        });
        timer.start();
    }

    private void boundsCheck() {
        if (snakeX[0] < 25) {
            snakeX[0] = ResourceMgr.WIDTH - 25;
        }
        if (snakeX[0] > ResourceMgr.WIDTH - 25) {
            snakeX[0] = 25;
        }
        if (snakeY[0] < 25) {
            snakeY[0] = ResourceMgr.HEIGHT - 25;
        }
        if (snakeY[0] > ResourceMgr.HEIGHT - 25) {
            snakeY[0] = 25;
        }
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
        this.setBackground(new Color(208, 220, 226));
        g.setColor(new Color(219, 226, 219));

        switch (dir) {
            case UP:
                g.drawImage(ImageMgr.snakeU, snakeX[0], snakeY[0], null);
                break;
            case DOWN:
                g.drawImage(ImageMgr.snakeD, snakeX[0], snakeY[0], null);
                break;
            case LEFT:
                g.drawImage(ImageMgr.snakeL, snakeX[0], snakeY[0], null);
                break;
            case RIGHT:
                g.drawImage(ImageMgr.snakeR, snakeX[0], snakeY[0], null);
                break;
        }

        for (int i = 1; i < length; i++) {
            g.drawImage(ImageMgr.body, snakeX[i], snakeY[i], null);
        }

        if (!isStart) {
            g.setColor(new Color(114, 98, 255));
            g.setFont(new Font("微软雅黑", Font.BOLD, 16));
            g.drawString("请点击空格开始游戏", 30, 30);
        }

        g.drawImage(ImageMgr.food, foodX, foodY, null);

        g.setColor(new Color(255, 248, 248));
        g.setFont(new Font("微软雅黑", Font.BOLD, 20));
        g.drawString("积分：" + score, 620, 40);

        if (isDie) {
            g.setColor(new Color(255, 82, 68));
            g.setFont(new Font("微软雅黑",Font.BOLD,20));
            g.drawString("寄!!!请按下空格重新开始游戏",30,30);
        }
    }
}
