//Gavin Edens, 02/08/2023, Homework 3: map editor
import java.util.ArrayList;

class Model
{

	ArrayList<Tile> tiles;
	Model()
	{
		tiles = new ArrayList<Tile>();
	}

	Model(Json ob)
	{
		tiles = new ArrayList<Tile>();
		Json ob2 = ob.get("tiles");
		for(int i = 0; i < ob2.size(); i++)
		{
			Tile t = new Tile(ob2.get(i));
			tiles.add(t);
		}

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

	public void addTile(int x, int y)
	{
		//check to see if tile already exists if it does remove on user click
		for(int i = 0; i < tiles.size(); i++)
		{
			Tile t = tiles.get(i);
			if(t.amiclicked(x, y))
			{
				tiles.remove(i);
				return;
			}
		}
		Tile t = new Tile(x, y);
		tiles.add(t);
	}

	public void update()
	{
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
