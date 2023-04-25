//Gavin Edens, 02/08/2023, Homework 3: map editor

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;


	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keyQ;
	boolean keyLowerQ;


	Controller(Model m)
	{
		model = m;
	}

	public void actionPerformed(ActionEvent e)
	{
		//view.removeButton();
	}

	void setView(View v)
	{
		view = v;
	}

	public void mousePressed(MouseEvent e)
	{
		int x = e.getX() - e.getX() % 50 + view.x_pos;
		int y = e.getY() - e.getY() % 50 + view.y_pos;
		model.addTile(x, y);
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
			case KeyEvent.VK_W: view.moveup(); break;
			case KeyEvent.VK_A: view.moveleft(); break;
			case KeyEvent.VK_X: view.movedown(); break;
			case KeyEvent.VK_D: view.moveright(); break;
			case KeyEvent.VK_S: model.marshal(); break;
			case KeyEvent.VK_L: model.loadmap(); break;
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
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		
	}
}
