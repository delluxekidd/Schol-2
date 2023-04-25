//Gavin Edens, 03/28/2023, Homework 5: Polymorphism

import java.util.ArrayList;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;


public abstract class Sprite
{
    int x;
    int y;
    int top;
    int right;
    int bottom;
    int left;
    int w;
    int h;

    public abstract void update();
    public abstract void paintComponent(Graphics g, int vX, int vY);

    public abstract Json marshall();

    public abstract boolean isLink();
    public abstract boolean isTile();
    public abstract boolean isBoomerang();
    public abstract boolean isPot();
}