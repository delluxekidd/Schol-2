//Gavin Edens, 03/28/2023, Homework 5: Polymorphism
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.BorderLayout;
public class Boomerang extends Sprite
{
    BufferedImage [] boomerangImages;
    int boomerangImageIndex;
    int dir;
    int index;

    public Boomerang(int x, int y, int direction)
    {
        this.x = x;
        this.y = y;
        dir = direction;
        w = 36;
        h = 36;
        if (boomerangImages == null)
        {
            boomerangImages = new BufferedImage[4];
            boomerangImages[0] = View.loadImage("images/boomerang1.png");
            boomerangImages[1] = View.loadImage("images/boomerang2.png");
            boomerangImages[2] = View.loadImage("images/boomerang3.png");
            boomerangImages[3] = View.loadImage("images/boomerang4.png");
        }
    }

    public boolean isLink()
    {
        return false;
    }

    public boolean isTile()
    {
        return false;
    }

    public boolean isBoomerang()
    {
        return true;
    }

    public boolean isPot()
    {
        return false;
    }

    public void moveRight()
    {
        x += 15;
        index++;
        if (index > 3)
        {
            index = 0;
        }
    }

    public void moveLeft()
    {
        x -= 15;
        index++;
        if (index > 3)
        {
            index = 0;
        }
    }

    public void moveUp()
    {
        y -= 15;
        index++;
        if (index > 3)
        {
            index = 0;
        }
    }

    public void moveDown()
    {
        y += 15;
        index++;
        if (index > 3)
        {
            index = 0;
        }
    }

    public void update()
    {
        right = x + w;
        left = x;
        top = y;
        bottom = y + h;
        if (dir == 0)
        {
            moveDown();
        }
        else if (dir == 1)
        {
            moveLeft();
        }
        else if (dir == 2)
        {
            moveRight();
        }
        else if (dir == 3)
        {
            moveUp();
        }
    }

    public void paintComponent(Graphics g, int vX, int vY)
    {
        g.drawImage(boomerangImages[index], x - vX, y - vY, w, h, null);
    }

    public Json marshall()
    {
        Json ob = Json.newObject();
        return ob;
    }

    @Override
    public String toString()
    {
        return "Tile (x,y) = (" + x + ", " + y + "), w = " + w + ", h = " + h + ", right = " + right + ", left = " + left + ", top = " + top + ", bottom = " + bottom;
    }
}
