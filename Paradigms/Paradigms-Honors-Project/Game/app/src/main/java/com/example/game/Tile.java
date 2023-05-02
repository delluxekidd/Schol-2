package com.example.game;//Gavin Edens, 03/28/2023, Homework 5: Polymorphism

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.content.Context;
import android.graphics.Paint;

//Add member variables for the horizontal and vertical position of the tile. (You should probably call these variables "int x;" and "int y;".)
// Add class variables for width and height (remember that class variables are shared among all instances of the class). Add two parameters to your Tile constructor to initialize its position.
public class Tile extends Sprite
{
    Bitmap tileImage;
    String filename = "tile";
    Context c;

    Tile(Context context)
    {
        c = context;
        w = 50;
        h = 50;
        right = x + w;
        left = x;
        top = y;
        bottom = y + h;
        if (tileImage == null)
        {
            tileImage = loadImage(filename);
        }
    }

    public Tile(Context context, int mx, int my)
    {
        c = context;
        x = mx;
        y = my;
        w = 50;
        h = 50;
        right = x + w;
        left = x;
        top = y;
        bottom = y + h;
        if (tileImage == null)
        {
            this.tileImage = loadImage(filename);
        }
    }


    //add a method that unmarshalls the object into a Json node
    public Tile(Context context, Json ob)
    {
        c = context;
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = 50;
        h = 50;
        top = y;
        bottom = y + h;
        left = x;
        right = x + w;
        if (tileImage == null)
        {
            this.tileImage = loadImage(filename);
        }
    }

    //Add a method that return true if the user clicked on a tile and false otherwise.
    public boolean amiclicked(int f, int r)
    {
        return (f >= left && f <= right && r >= top && r <= bottom);
    }

    public boolean isTile()
    {
        return true;
    }

    public boolean isLink()
    {
        return false;
    }

    public boolean isBoomerang()
    {
        return false;
    }

    public boolean isPot()
    {
        return false;
    }

    //add a method that marshals the object from a Json node
    public Json marshall()
    {
        Json ob = Json.newObject();
        ob.add("type", "tile");
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }

    public Bitmap loadImage(String name)
    {
        //load the bitmap associated with the name parameter
        Bitmap bitmap = null;
        try
        {
            bitmap = BitmapFactory.decodeResource(c.getResources(), c.getResources().getIdentifier(name, "drawable", "com.example.game"));
        }
        catch(Exception e)
        {
            System.out.println("Error: " + e);
        }
        return bitmap;
    }

    public void paintComponent(Canvas g, int vX, int vY, Paint paint)
    {
        Bitmap resize = Bitmap.createScaledBitmap(tileImage, w, h, false);
        g.drawBitmap(resize, x - vX, y - vY,paint);
    }

    @Override
    public String toString()
    {
        return "Tile (x,y) = (" + x + ", " + y + "), w = " + w + ", h = " + h + ", right = " + right + ", left = " + left + ", top = " + top + ", bottom = " + bottom;
    }

    public void update()
    {
    }
}