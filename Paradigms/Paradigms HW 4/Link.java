//Gavin Edens, 03/10/2023, Homework 4: Debuggers and Iterators
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

public class Link
{
    int x_pos;
    int y_pos;
    int x_size;
    int y_size;
    int index;
    int right;
    int left;
    int top;
    int bottom;
    int pRight;
    int pLeft;
    int pTop;
    int dir;
    int pBottom;
    double speed = 10;
    BufferedImage [] link_images;

    Link()
    {
        System.out.println("Link");
        x_pos = 100;
        y_pos = 100;
        x_size = 73;
        y_size = 85;
        dir = 0;
        if (link_images == null)
        {
            link_images = new BufferedImage[50];
            for (int i = 1; i < 51; i++)
            {
                link_images[i - 1] = View.loadImage("link" + i + ".png");
            }
        }
    }

    @Override
    public String toString()
    {
        return "Link (x,y) = (" + x_pos + ", " + y_pos + "), w = " + x_size + ", h = " + y_size;
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
            if (index > 50 || index < 39)
            {
                index = 40;
            }
        else if (dir == 4)
        {
            index = 0;
        }
        }
    }


    public void shiftUp()
	{

		y_pos = 1000;

	}

	public void shiftDown()
	{
		y_pos = 0;

	}

	public void shiftLeft()
	{

		x_pos = 1000;
	}

	public void shiftRight()
	{

		x_pos = 0;

	}

    public void moveUp()
    {
        y_pos = y_pos - (int)speed;
        index++;
        move(3);
    }

    public void moveDown()
    {
        y_pos = y_pos + (int)speed;
        index++;
        move(0);
    }

    public void moveLeft()
    {
        x_pos = x_pos - (int)speed;
        index++;
        move(1);
    }

    public void moveRight()
    {
        x_pos = x_pos + (int)speed;
        index++;
        move(2);
    }


    public void paintComponent(Graphics g)
    {
        g.drawImage(link_images[index], x_pos, y_pos, null);
    }

    public void update()
    {
        right = x_pos + x_size;
        left = x_pos;
        top = y_pos;
        bottom = y_pos + y_size;
    }

    public void updatePrev()
    {
        pRight = x_pos + x_size;
        pLeft = x_pos;
        pTop = y_pos;
        pBottom = y_pos + y_size;
    }
}
