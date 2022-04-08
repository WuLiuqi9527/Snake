package com.snake;

import java.awt.*;

public class ResourceMgr {

    public static final int WIDTH, HEIGHT;
    public static final int SX, SY;

    static {
        WIDTH = 800;
        HEIGHT = 800;
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        SX = (screenWidth - WIDTH) / 2;
        SY = (screenHeight - HEIGHT) / 2;
    }
}
