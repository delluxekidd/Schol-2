//Gavin Edens, 02/08/2023, Homework 3: map editor

//Add member variables for the horizontal and vertical position of the tile. (You should probably call these variables "int x;" and "int y;".)
// Add class variables for width and height (remember that class variables are shared among all instances of the class). Add two parameters to your Tile constructor to initialize its position.

public class Tile
{
    int x;
    int y;
    static int w;
    static int h;
    public Tile(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    //Add a method that return true if the user clicked on a tile and false otherwise.

    public boolean amiclicked(int x, int y)
    {
        return (x >= this.x && x <= this.x + w && y >= this.y && y <= this.y + h);
    }


    //add a method that unmarshals the object into a Json node
    public Tile(Json ob)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
    }

    //add a method that marshals the object from a Json node
    public Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }
}