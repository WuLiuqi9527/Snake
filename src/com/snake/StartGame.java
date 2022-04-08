package com.snake;

import javax.swing.*;

public class StartGame {

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setTitle("贪吃蛇");
        jf.setBounds(ResourceMgr.SX, ResourceMgr.SY, ResourceMgr.WIDTH, ResourceMgr.HEIGHT);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GamePanel gp = new GamePanel();
        jf.add(gp);

        jf.setVisible(true);
    }
}
