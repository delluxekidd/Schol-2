package com.example.game;//Gavin Edens, 03/28/2023, Homework 5: Polymorphism

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.content.Context;
import android.graphics.Paint;

public class Boomerang extends Sprite
{
    Bitmap [] boomerangImages;
    int boomerangImageIndex;
    int dir;
    int index;
    Context c;

    public Boomerang(Context context, int x, int y, int direction)
    {
        c = context;
        this.x = x;
        this.y = y;
        dir = direction;
        w = 36;
        h = 36;
        if (boomerangImages == null)
        {
            boomerangImages = new Bitmap[4];
            boomerangImages[0] = loadImage("boomerang1");
            boomerangImages[1] = loadImage("boomerang2");
            boomerangImages[2] = loadImage("boomerang3");
            boomerangImages[3] = loadImage("boomerang4");
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
        return true;
    }

    public boolean isPot()
    {
        return false;
    }

    public void moveRight()
    {
        x += 15;
        index++;
        if (index > 3)
        {
            index = 0;
        }
    }

    public void moveLeft()
    {
        x -= 15;
        index++;
        if (index > 3)
        {
            index = 0;
        }
    }

    public void moveUp()
    {
        y -= 15;
        index++;
        if (index > 3)
        {
            index = 0;
        }
    }

    public void moveDown()
    {
        y += 15;
        index++;
        if (index > 3)
        {
            index = 0;
        }
    }

    public void update()
    {
        right = x + w;
        left = x;
        top = y;
        bottom = y + h;
        if (dir == 0)
        {
            moveDown();
        }
        else if (dir == 1)
        {
            moveLeft();
        }
        else if (dir == 2)
        {
            moveRight();
        }
        else if (dir == 3)
        {
            moveUp();
        }
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
        Bitmap resize = Bitmap.createScaledBitmap(boomerangImages[index], w, h, false);
        g.drawBitmap(resize, x - vX, y - vY,paint);
    }

    public Json marshall()
    {
        Json ob = Json.newObject();
        return ob;
    }

    @Override
    public String toString()
    {
        return "Tile (x,y) = (" + x + ", " + y + "), w = " + w + ", h = " + h + ", right = " + right + ", left = " + left + ", top = " + top + ", bottom = " + bottom;
    }
}
