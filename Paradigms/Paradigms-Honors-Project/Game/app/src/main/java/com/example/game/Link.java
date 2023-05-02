package com.example.game;//Gavin Edens, 03/28/2023, Homework 5: Polymorphism

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.content.Context;
import android.graphics.Paint;

public class Link extends Sprite
{
    int index;
    int dir;
    int pRight;
    int pLeft;
    int pTop;
    int pBottom;
    double speed = 10;
    Bitmap [] link_images;
    Context c;

    Link(Context context)
    {
        c = context;
        x = 100;
        y = 100;
        w = 73;
        h = 85;
        dir = 0;
        if (link_images == null)
        {
            link_images = new Bitmap[50];
            for (int i = 1; i < 51; i++)
            {
                link_images[i - 1] = loadImage("link" + i);
            }
        }

        System.out.println("Link created");
    }

    public Json marshall()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        return ob;
    }

    @Override
    public String toString()
    {
        return "Link (x,y) = (" + x + ", " + y + "), w = " + w + ", h = " + h + ", right = " + right + ", left = " + left + ", top = " + top + ", bottom = " + bottom + ", pRight = " + pRight + ", pLeft = " + pLeft + ", pTop = " + pTop + ", pBottom = " + pBottom;
    }

    public void move(int dir)
    {
        if (dir == 0)
        {
            if (index > 10)
            {
                index = 0;
            }
        }
        else if (dir == 1)
        {
            if (index > 24 || index < 14)
            {
                index = 14;
            }
        }
        else if (dir == 2)
        {
            if (index > 37 || index < 27)
            {
                index = 27;
            }
        }
        else if (dir == 3)
        {
            if (index > 49 || index < 39)
            {
                index = 40;
            }
        }
    }

    public Bitmap loadImage(String name)
    {
        //load the bitmap associated with the name parameter
        System.out.println("Loading image: " + name);
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

    public void moveUp()
    {
        y = y - (int)speed;
        index++;
        dir = 3;
        move(3);
    }

    public void moveDown()
    {
        y = y + (int)speed;
        index++;
        dir = 0;
        move(0);
    }

    public void moveLeft()
    {
        x = x - (int)speed;
        index++;
        dir = 1;
        move(1);
    }

    public void moveRight()
    {
        x = x + (int)speed;
        index++;
        dir = 2;
        move(2);
    }


    public void paintComponent(Canvas g, int vX, int vY, Paint paint)
    {
        //System.out.println("link x:" + x + " y:" + y + " w:" + w + " h:" + h + " vX:" + vX + " vY:" + vY + " index:" + index + " dir:" + dir);
        Bitmap resize = Bitmap.createScaledBitmap(link_images[index], w, h, false);
        g.drawBitmap(resize, x - vX, y - vY, paint);
    }

    public void update()
    {
        right = x + w;
        left = x;
        top = y;
        bottom = y + h;
    }

    public void updatePrev()
    {
        pRight = x + w;
        pLeft = x;
        pTop = y;
        pBottom = y + h;
    }

    public boolean isLink()
    {
        return true;
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
        return false;
    }
}
