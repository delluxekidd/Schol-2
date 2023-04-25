//Gavin Edens, 03/10/2023, Homework 4: Debuggers and Iterators

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
	Link link;


	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keyQ;
	boolean keyLowerQ;
	boolean enableEdit;


	Controller(Model m, Link l)
	{
		model = m;
		link = l;
	}

	public void actionPerformed(ActionEvent e)
	{
		//view.removeButton();
	}

	void setView(View v)
	{
		view = v;
	}

	public void enableEditFunc()
	{
		if (enableEdit == true)
		{
			enableEdit = false;
			view.editLabel.setText("Edit Mode: Off");
		}
		else
		{
			enableEdit = true;
			view.editLabel.setText("Edit Mode: On");
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
			case KeyEvent.VK_E: enableEditFunc(); break;


			//move link character when user presses arrow keys
			case KeyEvent.VK_RIGHT:{ keyRight = true; break;}
			case KeyEvent.VK_LEFT:{ keyLeft = true; break;}
			case KeyEvent.VK_UP:{keyUp = true; break;}
			case KeyEvent.VK_DOWN:{ keyDown = true; break;}
			//case KeyEvent.VK_L: model.loadmap(); break;
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
		link.update();
		link.updatePrev();
		if (link.x_pos > 1000)
		{
			view.moveright();
			link.shiftRight();
		}
		if (link.x_pos < 0)
		{
			view.moveleft();
			link.shiftLeft();
		}
		if (link.y_pos > 1000)
		{
			view.movedown();
			link.shiftDown();
		}
		if (link.y_pos < 0)
		{
			view.moveup();
			link.shiftUp();
		}
		if (keyRight == true && !model.isCollision())
		{
			//link.updatePrev();
			link.moveRight();
		}
		else if (keyLeft == true && !model.isCollision())
		{
			//link.updatePrev();
			link.moveLeft();
		}
		else if (keyUp == true && !model.isCollision())
		{
			//link.updatePrev();
			link.moveUp();
		}
		else if (keyDown == true && !model.isCollision())
		{
			//link.updatePrev();
			link.moveDown();
		}
		view.update();

	}
}
