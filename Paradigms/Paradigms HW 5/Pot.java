//Gavin Edens, 03/28/2023, Homework 5: Polymorphism
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.BorderLayout;
public class Pot extends Sprite
{
    BufferedImage potImage;
    BufferedImage brokenPotImage;
    Boolean isBroken;
    int frames = 0;
    int yDir;
    int xDir;
    String filename = "images/pot.png";
    String brokenFilename = "images/pot_broken.png";

    public Pot(int x, int y)
    {
        this.x = x;
        this.y = y;
        w = 48;
        h = 48;
        isBroken = false;
        if (potImage == null)
        {
            potImage = View.loadImage("images/pot.png");
        }
        if (brokenPotImage == null)
        {
            brokenPotImage = View.loadImage("images/pot_broken.png");
        }
    }

    public Pot(Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = 48;
        h = 48;
        top = y;
        bottom = y + h;
        left = x;
        right = x + w;
        isBroken = false;
        if (potImage == null)
        {
            potImage = View.loadImage("images/pot.png");
        }
        if (brokenPotImage == null)
        {
            brokenPotImage = View.loadImage("images/pot_broken.png");
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
        return false;
    }

    public boolean isPot()
    {
        return true;
    }

    public void breakPot()
    {
        isBroken = true;
        yDir = 0;
        xDir = 0;
    }

    public void collided(int dir)
    {
        if (dir == 0)
        {
            yDir = 10;
        }
        if (dir == 1)
        {
            xDir = -10;
        }
        if (dir == 2)
        {
            xDir = 10;
        }
        if (dir == 3)
        {
            yDir = -10;
        }
    }

    public void update()
    {
        x += xDir;
        y += yDir;
        right = x + w;
        left = x;
        top = y;
        bottom = y + h;
        if (isBroken)
        {
            frames++;
        }
    }

    public boolean removePot()
    {
        return frames > 50;
    }

    public void paintComponent(Graphics g, int vX, int vY)
    {
        if (isBroken)
            g.drawImage(brokenPotImage, x - vX, y - vY, null);
        else
            g.drawImage(potImage, x - vX, y - vY, null);
    }

    public Json marshall()
    {
        Json ob = Json.newObject();
        ob.add("type", "pot");
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }

    // public void startMoving(int dir)
    // {
    //     if (dir == 0)
    //     {
    //         while(isBroken)
    //         {
    //             y += 10;
    //         }
    //     }
    //     if (dir == 1)
    //     {
    //         while(isBroken)
    //         {
    //             x -= 10;
    //         }
    //     }
    //     if (dir == 2)
    //     {
    //         while(isBroken)
    //         {
    //             x += 10;
    //         }
    //     }
    //     if (dir == 3)
    //     {
    //         while(isBroken)
    //         {
    //             y -= 10;
    //         }
    //     }
    // }

    @Override
    public String toString()
    {
        return "Tile (x,y) = (" + x + ", " + y + "), w = " + w + ", h = " + h + ", right = " + right + ", left = " + left + ", top = " + top + ", bottom = " + bottom;
    }
}
