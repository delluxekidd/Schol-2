//Gavin Edens, 03/28/2023, Homework 5: Polymorphism

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.BorderLayout;

public class Link extends Sprite
{
    int index;
    int dir;
    int pRight;
    int pLeft;
    int pTop;
    int pBottom;
    double speed = 10;
    BufferedImage [] link_images;

    Link()
    {
        x = 100;
        y = 100;
        w = 20;
        h = 20;
        dir = 0;
        if (link_images == null)
        {
            link_images = new BufferedImage[50];
            for (int i = 1; i < 51; i++)
            {
                link_images[i - 1] = View.loadImage("images/link" + i + ".png");
            }
        }
    }

    Link(Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = (int)ob.getLong("w");
        h = (int)ob.getLong("h");
        if (link_images == null)
        {
            link_images = new BufferedImage[50];
            for (int i = 1; i < 51; i++)
            {
                link_images[i - 1] = View.loadImage("images/link" + i + ".png");
            }
        }
    }

    public Json marshall()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }

    @Override
    public String toString()
    {
        return "Link (x,y) = (" + x + ", " + y + "), w = " + w + ", h = " + h + ", right = " + right + ", left = " + left + ", top = " + top + ", bottom = " + bottom + ", pRight = " + pRight + ", pLeft = " + pLeft + ", pTop = " + pTop + ", pBottom = " + pBottom;
    }

    public void move(int dir)
    {
        if (dir == 0)
        {
            if (index > 10)
            {
                index = 0;
            }
        }
        else if (dir == 1)
        {
            if (index > 24 || index < 14)
            {
                index = 14;
            }
        }
        else if (dir == 2)
        {
            if (index > 37 || index < 27)
            {
                index = 27;
            }
        }
        else if (dir == 3)
        {
            if (index > 49 || index < 39)
            {
                index = 40;
            }
        }
    }

    public void moveUp()
    {
        y = y - (int)speed;
        index++;
        dir = 3;
        move(3);
    }

    public void moveDown()
    {
        y = y + (int)speed;
        index++;
        dir = 0;
        move(0);
    }

    public void moveLeft()
    {
        x = x - (int)speed;
        index++;
        dir = 1;
        move(1);
    }

    public void moveRight()
    {
        x = x + (int)speed;
        index++;
        dir = 2;
        move(2);
    }


    public void paintComponent(Graphics g, int vX, int vY)
    {
        g.drawImage(link_images[index], x - vX, y - vY, w, h, null);
    }

    public void update()
    {
        right = x + w;
        left = x;
        top = y;
        bottom = y + h;
    }

    public void updatePrev()
    {
        pRight = x + w;
        pLeft = x;
        pTop = y;
        pBottom = y + h;
    }

    public boolean isLink()
    {
        return true;
    }

    public boolean isTile()
    {
        return false;
    }

    public boolean isBoomerang()
    {
        return false;
    }

    public boolean isPot()
    {
        return false;
    }
}
