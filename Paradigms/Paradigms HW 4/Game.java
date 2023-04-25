//Gavin Edens, 03/10/2023, Homework 4: Debuggers and Iterators

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.BorderLayout;


public class Game extends JFrame
{

	Model model;
	Controller controller;
	View view;
	Link link;
	Tile tile;

	public Game()
	{
		link = new Link();
		tile = new Tile(model);
		model = new Model(link);
		controller = new Controller(model, link);
		view = new View(controller, model, link, tile);
		model.loadmap();
		this.setTitle("A4 - Debuggers and Iterators");
		this.setSize(1000, 1000);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		view.addMouseListener(controller);
		this.addKeyListener(controller);
	}

	public void run()
	{
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // This will indirectly call View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen
			// Go to sleep for 40 milliseconds
			try
			{
				Thread.sleep(40);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
			if (view == null)
			{
				//exit the loop
				break;
			}
		}
	}

	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}

}
