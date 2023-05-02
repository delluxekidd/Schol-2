//Gavin Edens, 03/28/2023, Homework 5: Polymorphism
import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{

	Model model;
	Controller controller;
	View view;

	public Game()
	{
		model = new Model();
		view = new View(model);
		controller = new Controller(model, view);
		model.loadmap();
		this.setTitle("A5 - Polymorphism");
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
			view.update();
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