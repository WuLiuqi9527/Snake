package com.snake;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageMgr {

    public static BufferedImage snakeU, snakeD, snakeL, snakeR;
    public static BufferedImage food, body;

    static {
        try {
            snakeU = ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/up.png"));
            snakeD = ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/down.png"));
            snakeL = ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/left.png"));
            snakeR = ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/right.png"));
            food = ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/food.png"));
            body = ImageIO.read(ImageMgr.class.getClassLoader().getResourceAsStream("images/body.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
