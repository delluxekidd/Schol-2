package com.example.game;//Gavin Edens, 03/28/2023, Homework 5: Polymorphism
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.content.Context;
import android.graphics.Paint;

public class Pot extends Sprite
{
    Bitmap potImage;
    Bitmap brokenPotImage;
    Boolean isBroken;
    int frames = 0;
    int yDir;
    int xDir;
    String filename = "pot";
    String brokenFilename = "pot_broken";
    Context c;


    public Pot(Context context, int x, int y)
    {
        c = context;
        this.x = x;
        this.y = y;
        w = 48;
        h = 48;
        isBroken = false;
        if (potImage == null)
        {
            potImage = loadImage(filename);
        }
        if (brokenPotImage == null)
        {
            brokenPotImage = loadImage(brokenFilename);
        }
    }

    public Pot(Context context, Json ob)
    {
        c = context;
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = 48;
        h = 48;
        top = y;
        bottom = y + h;
        left = x;
        right = x + w;
        isBroken = false;
        if (potImage == null)
        {
            potImage = loadImage(filename);
        }
        if (brokenPotImage == null)
        {
            brokenPotImage = loadImage(brokenFilename);
        }
    }

    public boolean isLink()
    {
        return false;
    }

    public boolean isTile()
    {
        return false;
    }

    public boolean isBoomerang()
    {
        return false;
    }

    public boolean isPot()
    {
        return true;
    }

    public void breakPot()
    {
        isBroken = true;
        yDir = 0;
        xDir = 0;
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

    public void collided(int dir)
    {
        if (dir == 0)
        {
            yDir = 10;
        }
        if (dir == 1)
        {
            xDir = -10;
        }
        if (dir == 2)
        {
            xDir = 10;
        }
        if (dir == 3)
        {
            yDir = -10;
        }
    }

    public void update()
    {
        if (!isBroken)
        {
            x += xDir;
            y += yDir;
        }
        right = x + w;
        left = x;
        top = y;
        bottom = y + h;
        if (isBroken)
        {
            frames++;
        }
    }

    public boolean removePot()
    {
        return frames > 50;
    }

    public void paintComponent(Canvas g, int vX, int vY, Paint paint)
    {
        Bitmap resize = Bitmap.createScaledBitmap(brokenPotImage, w, h, false);
        Bitmap resize2 = Bitmap.createScaledBitmap(potImage, w, h, false);
        if (isBroken)
            g.drawBitmap(resize, x - vX, y - vY, paint);
        else
            g.drawBitmap(resize2, x - vX, y - vY, paint);
    }

    public Json marshall()
    {
        Json ob = Json.newObject();
        ob.add("type", "pot");
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }

    @Override
    public String toString()
    {
        return "Tile (x,y) = (" + x + ", " + y + "), w = " + w + ", h = " + h + ", right = " + right + ", left = " + left + ", top = " + top + ", bottom = " + bottom;
    }
}
