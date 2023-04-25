//Gavin Edens, 03/28/2023, Homework 5: Polymorphism
import java.util.ArrayList;
//import iterator pattern
import java.util.Iterator;

class Model
{
	ArrayList<Sprite> sprites;
	ArrayList<Sprite> spritesToBeRemoved;
	Sprite linkSprite;
	Link link;

	Model()
	{
		sprites = new ArrayList<Sprite>();
		spritesToBeRemoved = new ArrayList<Sprite>();
		linkSprite= new Link();
		link = (Link)linkSprite;
	}

	//add a method that loads map from a file
	public void loadmap()
	{
		Json ob = Json.load("map.json");
		Json ob2 = ob.get("sprites");
		sprites.add(link);
		for(int i = 0; i < ob2.size(); i++)
		{
			if (ob2.get(i).getString("type").equals("tile"))
			{
				Sprite t = new Tile(ob2.get(i));
				sprites.add(t);
			}
			else if (ob2.get(i).getString("type").equals("pot"))
			{
				Sprite t = new Pot(ob2.get(i));
				sprites.add(t);
			}
		}
	}

	public void addTile(int x, int y, int mx, int my)
	{
		//check to see if tile already exists if it does remove on user click
		for (int i = 0; i < sprites.size(); i++)
		{
			if (sprites.get(i).isTile())
			{
				Tile t = (Tile)sprites.get(i);
				if (t.amiclicked(mx, my))
				{
					sprites.remove(i);
					return;
				}
			}
		}
		Sprite t = new Tile(x, y);
		sprites.add(t);
	}

	public void addPot(int mx, int my)
	{
		Sprite s = new Pot(mx, my);
		sprites.add(s);
	}

	// public boolean isCollision(Sprite a)
	// {
	// 	if (a.isTile() && link.right > a.left && link.left < a.right  && link.bottom > a.top && link.top < a.bottom)
	// 	{
	// 		return true;
	// 	}
	// 	return false;
	// }

	public boolean collision(Sprite a, Sprite b)
	{
		if (a.right > b.left && a.left < b.right  && a.bottom > b.top && a.top < b.bottom)
		{
			if (a.isLink() && b.isTile())
			{
				snapToTile(b);
			}

			if (a.isTile() && b.isLink())
			{
				snapToTile(a);
			}

			if (a.isBoomerang())
			{
				if (b.isTile())
				{
					spritesToBeRemoved.add(a);
				}
				else if (b.isPot())
				{
					spritesToBeRemoved.add(a);
					Pot c = (Pot)b;
					c.isBroken = true;
				}
			}

			if (b.isBoomerang())
			{
				if (a.isTile())
				{
					spritesToBeRemoved.add(b);
				}
				if (a.isPot())
				{
					spritesToBeRemoved.add(b);
					Pot c = (Pot)a;
					c.isBroken = true;
				}
			}

			if (a.isPot())
			{
				if (b.isTile())
				{
					Pot c = (Pot)a;
					c.breakPot();
				}

				if (b.isLink())
				{
					Pot c = (Pot)a;
					c.collided(link.dir);
				}
			}

			if (b.isPot())
			{
				if (a.isTile())
				{
					Pot c = (Pot)b;
					c.breakPot();
				}

				if (a.isLink())
				{
					Pot c = (Pot)b;
					c.collided(link.dir);
				}
			}

		}
		return false;
	}

	public void snapToTile(Sprite t)
	{
		if (link.right > t.left && link.pRight <= t.left && link.right < t.right)
		{
			link.x = t.left - link.w;
		}

		if (link.left < t.right && link.pLeft >= t.right && link.left > t.left)
		{
			link.x = t.right;
		}

		if (link.bottom > t.top && link.pBottom <= t.top && link.bottom < t.bottom)
		{
			link.y = t.top - link.h;
		}

		if (link.top < t.bottom && link.pTop >= t.bottom && link.top > t.top)
		{
			link.y = t.bottom;
		}
	}

	public void shoot()
	{
		Sprite s = new Boomerang(link.x + 20, link.y + 20, link.dir);
		sprites.add(s);
	}

	public void marshal()
	{
		//push the map to a file
		Json ob = Json.newObject();
		Json ob2 = Json.newList();
		for(int i = 0; i < sprites.size(); i++)
		{
			if (sprites.get(i).isTile() || sprites.get(i).isPot())
			{
				Sprite t = sprites.get(i);
				ob2.add(t.marshall());
			}
		}
		ob.add("sprites", ob2);
		ob.save("map.json");
	}

	public void update()
	{
		for (int i = 0; i < sprites.size(); i++)
		{
			Sprite t = sprites.get(i);
			t.update();
			if (t.isPot() && ((Pot)t).removePot())
			{
				spritesToBeRemoved.add(sprites.get(i));
			}
			for (int j = 0; j < sprites.size(); j++)
			{
				Sprite s = sprites.get(j);
				collision(t, s);
			}
		}
		if (spritesToBeRemoved != null)
		{
			Iterator<Sprite> it = spritesToBeRemoved.iterator();
			while (it.hasNext())
			{
				Sprite s = it.next();
				sprites.remove(s);
			}
			spritesToBeRemoved.clear();
		}
	}
}
