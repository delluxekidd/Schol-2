//Gavin Edens, 03/28/2023, Homework 5: Polymorphism

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.BorderLayout;
//import iterator pattern
import java.util.Iterator;

class View extends JPanel
{
	Model model;
	int x_pos;
	int y_pos;
	//add a label that lets the user know if the are in edit mode or not
	JLabel editLabel;

	View(Model m)
	{
		model = m;
		editLabel = new JLabel("Edit Mode: Off | Pot Edit Mode: Off");
		//make the text white
		editLabel.setForeground(Color.WHITE);
		this.add(editLabel, BorderLayout.SOUTH);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(new Color(128, 255, 255));
		Iterator <Sprite> it = model.sprites.iterator();
		try{
		while(it.hasNext())
		{
			Sprite s = it.next();
			s.paintComponent(g, x_pos, y_pos);
		}
	}catch(Exception e){
		System.out.println("Error: " + e);

	}
	}

	public void update()
	{
		//this.repaint();
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