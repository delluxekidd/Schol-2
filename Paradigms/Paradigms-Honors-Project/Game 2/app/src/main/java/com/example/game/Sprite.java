package com.example.game;//Gavin Edens, 03/28/2023, Homework 5: Polymorphism

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Sprite
{
    int x;
    int y;
    int top;
    int right;
    int bottom;
    int left;
    int w;
    int h;

    public abstract void update();
    public abstract void paintComponent(Canvas g, int vX, int vY, Paint paint);

    public abstract Json marshall();

    public abstract boolean isLink();
    public abstract boolean isTile();
    public abstract boolean isBoomerang();
    public abstract boolean isPot();
}