//Gavin Edens, 02/08/2023, Homework 3: map editor

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
	BufferedImage tile_image;
	Model model;
	int x_pos;
	int y_pos;

	View(Controller c, Model m)
	{
		model = m;
		c.setView(this);
		try
		{
			this.tile_image = ImageIO.read(new File("tile.png"));
		}
		catch(Exception e)
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}

	public void paintComponent(Graphics g)
	{
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(new Color(128, 255, 255));
		for(int i = 0; i < model.tiles.size(); i++)
		{
			Tile t = model.tiles.get(i);
			g.drawImage(tile_image, t.x - x_pos, t.y - y_pos, null);
		}
	}

	public void update()
	{
		this.repaint();
	}

	public void moveup()
	{
		if (x_pos == 0)
		{
			x_pos += 1000;
		}

	}

	public void movedown()
	{
		if (x_pos == 1000)
		{
			x_pos -= 1000;
		}
	}

	public void moveleft()
	{
		if (y_pos == 1000)
		{
			y_pos -= 1000;
		}
	}

	public void moveright()
	{
		if (y_pos == 0)
		{
			y_pos += 1000;
		}
	}
}
