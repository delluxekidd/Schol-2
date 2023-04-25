//Gavin Edens, 03/10/2023, Homework 4: Debuggers and Iterators
import java.util.ArrayList;
import java.util.Iterator;

class Model
{
	ArrayList<Tile> tiles;
	Link link;
	int v_x;
	int v_y;
	Model(Link l)
	{
		v_x = 0;
		v_y = 0;
		tiles = new ArrayList<Tile>();
		link = l;
		System.out.println("Model");
	}

	Model(Json ob, Link l)
	{
		System.out.println("Model Json");
		link = l;
		tiles = new ArrayList<Tile>();
		Json ob2 = ob.get("tiles");
	}

	//add a method that loads map from a file
	public void loadmap()
	{
		Json ob = Json.load("map.json");
		Json ob2 = ob.get("tiles");
		for(int i = 0; i < ob2.size(); i++)
		{
			Tile t = new Tile(ob2.get(i));
			tiles.add(t);
		}
	}

	public void addTile(int x, int y, int mx, int my)
	{
		//check to see if tile already exists if it does remove on user click
		Iterator<Tile> it = tiles.iterator();
		while(it.hasNext())
		{
			Tile t = it.next();
			if(t.amiclicked(mx, my))
			{
				it.remove();
				return;
			}
		}
		Tile t = new Tile(x, y);
		tiles.add(t);
	}

	public boolean isCollision()
	{
		for (int i = 0; i < tiles.size(); i++)
		{
			Tile t = tiles.get(i);
			if ((link.right > t.left - v_x && link.left < t.right - v_x ) && (link.bottom > t.top - v_y && link.top < t.bottom - v_y))
			{
				return true;
			}
		}
		return false;
	}

	public void snapToTile()
	{
			for (int i = 0; i < tiles.size(); i++)
			{
				Tile t = tiles.get(i);
				if (link.right > t.left && link.pRight <= t.left && link.right < t.right)
				{
					link.x_pos = t.left - link.x_size;
				}
				if (link.left < t.right && link.pLeft >= t.right && link.left > t.left)
				{
					link.x_pos = t.right;
				}
				if (link.bottom > t.top && link.pBottom <= t.top && link.bottom < t.bottom)
				{
					link.y_pos = t.top - link.y_size;
				}
				if (link.top < t.bottom && link.pTop >= t.bottom && link.top > t.top)
				{
					link.y_pos = t.bottom;
				}
			}
	}

	public void update()
	{
		link.update();
		//When Link collides with a Tile, repel him back out of the tile. That is, if his position before entering the tile was left of the tile, then he should snap to be adjacent to the tile on the left side. If his position before entering the tile was above the tile, then he should snap to be adjacent to the tile on the top side. If his position before entering the tile was right or the tile, then he should snap to be adjacent to the tile on the right side. And, if his position before entering the tile was below the tile, then he should snap to be adjacent to the tile on the bottom side.
		if (isCollision())
		{
			snapToTile();
		}

	}

	public void marshal()
	{
		//push the map to a file
		Json ob = Json.newObject();
		Json ob2 = Json.newList();
		for(int i = 0; i < tiles.size(); i++)
		{
			Tile t = tiles.get(i);
			ob2.add(t.marshal());
		}
		ob.add("tiles", ob2);
		ob.save("map.json");
	}
}
