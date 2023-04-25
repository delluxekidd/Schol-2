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

class View extends JPanel
{
	Link link;
	Model model;
	Tile tile;
	int x_pos;
	int y_pos;
	//add a label that lets the user know if the are in edit mode or not
	JLabel editLabel;

	View(Controller c, Model m, Link l, Tile t)
	{
		System.out.println("View");
		link = l;
		model = m;
		tile = t;
		editLabel = new JLabel("Edit Mode: Off");
		//make the text white
		editLabel.setForeground(Color.WHITE);
		this.add(editLabel, BorderLayout.SOUTH);
		c.setView(this);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(new Color(128, 255, 255));
		for(int i = 0; i < model.tiles.size(); i++)
		{
			Tile t = model.tiles.get(i);
			t.update();
			t.paintComponent(g, t.x - x_pos, t.y - y_pos);
			model.v_x = x_pos;
			model.v_y = y_pos;
		}
		link.paintComponent(g);
	}

	public void update()
	{
		this.repaint();
	}

	public void moveup()
	{
		y_pos = 0;
	}

	public void movedown()
	{
		y_pos = 1000;
	}

	public void moveleft()
	{
		x_pos = 0;
	}

	public void moveright()
	{
		x_pos = 1000;
	}

	public static BufferedImage loadImage(String filename)
	{
		BufferedImage image = null;
		try
		{
			image = ImageIO.read(new File(filename));
		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}

		return image;
	}


    @Override
    public String toString()
    {
        return "View (x,y) = (" + x_pos + ", " + y_pos + ")";
    }
}
