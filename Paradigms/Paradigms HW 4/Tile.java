//Gavin Edens, 03/10/2023, Homework 4: Debuggers and Iterators

//Add member variables for the horizontal and vertical position of the tile. (You should probably call these variables "int x;" and "int y;".)
// Add class variables for width and height (remember that class variables are shared among all instances of the class). Add two parameters to your Tile constructor to initialize its position.
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class Tile
{
    Model model;
    int x;
    int y;
    int w = 50;
    int h = 50;
    int right;
    int left;
    int top;
    int bottom;
    BufferedImage tile_image;

    Tile(Model m)
    {
        model = m;
        right = x + w;
        left = x;
        top = y;
        bottom = y + h;
        if (tile_image == null)
        {
            this.tile_image = View.loadImage("tile.png");
        }
    }

    public Tile(int x, int y)
    {
        this.x = x;
        this.y = y;
        right = x + w;
        left = x;
        top = y;
        bottom = y + h;
        if (tile_image == null)
        {
            this.tile_image = View.loadImage("tile.png");
        }
    }
    //Add a method that return true if the user clicked on a tile and false otherwise.

    public boolean amiclicked(int f, int r)
    {
        return (f >= left && f <= right && r >= top && r <= bottom);
    }


    //add a method that unmarshals the object into a Json node
    public Tile(Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");

        if (tile_image == null)
        {
            this.tile_image = View.loadImage("tile.png");
        }
    }

    //add a method that marshals the object from a Json node
    public Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }

    public void paintComponent(Graphics g, int d, int j)
    {
        g.drawImage(this.tile_image, d, j, null);
    }


    public void update()
    {
        top = y;
        bottom = y + h;
        left = x;
        right = x + w;
    }

    @Override
    public String toString()
    {
        return "Tile (x,y) = (" + x + ", " + y + "), w = " + w + ", h = " + h;
    }

}