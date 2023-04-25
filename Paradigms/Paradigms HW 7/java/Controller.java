//Gavin Edens, 03/28/2023, Homework 5: Polymorphism

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	Model model;
	View view;

	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keyQ;
	boolean keyLowerQ;
	boolean enableEdit;
	boolean potEdit;

	Controller(Model m, View v)
	{
		model = m;
		view = v;
	}

	public void actionPerformed(ActionEvent e)
	{
	}

	public void enableEdit()

	{
		if (enableEdit)
		{
			enableEdit = false;
			view.editLabel.setText("Edit Mode: Off | Pot Edit Mode: Off");
		}
		else
		{
			enableEdit = true;
			view.editLabel.setText("Edit Mode: On | Pot Edit Mode: Off");
		}
	}

	public void enablePotEdit()
	{
		if (potEdit && enableEdit)
		{
			potEdit = false;
			view.editLabel.setText("Edit Mode: On | Pot Edit Mode: Off");
		}
		else if (!potEdit && enableEdit)
		{
			potEdit = true;
			view.editLabel.setText("Edit Mode: On | Pot Edit Mode: On");
		}
		else
		{
			potEdit = false;
			view.editLabel.setText("Edit Mode: Off | Pot Edit Mode: Off");
		}
	}

	public void mousePressed(MouseEvent e)
	{
		if (enableEdit == true)
		{
			int mx = e.getX() + view.x_pos;
			int my = e.getY() + view.y_pos;
			int x = (e.getX() - e.getX() % 50) + view.x_pos;
			int y = (e.getY() - e.getY() % 50) + view.y_pos;
			if (potEdit)
				model.addPot(mx, my);
			else
				model.addTile(x, y, mx , my);
		}
	}

	public void mouseReleased(MouseEvent e) {    }
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			/*case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;*/
			case KeyEvent.VK_Q: System.exit(0); break;
			case KeyEvent.VK_ESCAPE: System.exit(0); break;
			case KeyEvent.VK_S: model.marshal(); break;
			case KeyEvent.VK_E: enableEdit(); break;
			case KeyEvent.VK_P: enablePotEdit(); break;

			//move link character when user presses arrow keys
			case KeyEvent.VK_RIGHT:{ keyRight = true; break;}
			case KeyEvent.VK_LEFT:{ keyLeft = true; break;}
			case KeyEvent.VK_UP:{keyUp = true; break;}
			case KeyEvent.VK_DOWN:{ keyDown = true; break;}
			//case KeyEvent.VK_L: model.loadmap(); break;

			//These are the interactions for the link character
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_CONTROL: model.shoot(); break;
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		model.link.updatePrev();

		if (model.link.x > 1000)
		{
			view.moveright();
		}
		if (model.link.x < 1000)
		{
			view.moveleft();
		}
		if (model.link.y > 1000)
		{
			view.movedown();
		}
		if (model.link.y < 1000)
		{
			view.moveup();
		}
		if (keyRight == true)
		{
			model.link.moveRight();
		}
		else if (keyLeft == true)
		{
			model.link.moveLeft();
		}
		else if (keyUp == true)
		{
			model.link.moveUp();
		}
		else if (keyDown == true)
		{
			model.link.moveDown();
		}
		//model.link.update();
		view.update();

	}
}
