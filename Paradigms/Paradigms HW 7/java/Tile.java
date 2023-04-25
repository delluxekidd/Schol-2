//Gavin Edens, 03/28/2023, Homework 5: Polymorphism

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.BorderLayout;

//Add member variables for the horizontal and vertical position of the tile. (You should probably call these variables "int x;" and "int y;".)
// Add class variables for width and height (remember that class variables are shared among all instances of the class). Add two parameters to your Tile constructor to initialize its position.
public class Tile extends Sprite
{
    BufferedImage tileImage;
    String filename = "images/tile.png";

    Tile()
    {
        w = 50;
        h = 50;
        right = x + w;
        left = x;
        top = y;
        bottom = y + h;
        if (tileImage == null)
        {
            this.tileImage = View.loadImage(filename);
        }
    }

    public Tile(int mx, int my)
    {
        x = mx;
        y = my;
        w = 50;
        h = 50;
        right = x + w;
        left = x;
        top = y;
        bottom = y + h;
        if (tileImage == null)
        {
            this.tileImage = View.loadImage(filename);
        }
    }


    //add a method that unmarshalls the object into a Json node
    public Tile(Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = 50;
        h = 50;
        top = y;
        bottom = y + h;
        left = x;
        right = x + w;
        if (tileImage == null)
        {
            this.tileImage = View.loadImage(filename);
        }
    }

    //Add a method that return true if the user clicked on a tile and false otherwise.
    public boolean amiclicked(int f, int r)
    {
        return (f >= left && f <= right && r >= top && r <= bottom);
    }

    public boolean isTile()
    {
        return true;
    }

    public boolean isLink()
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

    //add a method that marshals the object from a Json node
    public Json marshall()
    {
        Json ob = Json.newObject();
        ob.add("type", "tile");
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }

    public void paintComponent(Graphics g, int vX, int vY)
    {
        g.drawImage(tileImage, x - vX, y - vY, null);
    }

    @Override
    public String toString()
    {
        return "Tile (x,y) = (" + x + ", " + y + "), w = " + w + ", h = " + h + ", right = " + right + ", left = " + left + ", top = " + top + ", bottom = " + bottom;
    }

    public void update()
    {
    }
}