package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.util.Iterator;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    Model model;
    GameView view;
    Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        model = new Model(this);
        view = new GameView(this, model);
        model.loadmap();
        controller = new Controller(model, view);
        setContentView(view);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        controller.resume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        controller.pause();
    }

    static class Model
    {
        ArrayList<Sprite> sprites;
        ArrayList<Sprite> spritesToBeRemoved;
        Link link;
        Context c;

        Model(Context context)
        {
            c = context;
            sprites = new ArrayList<Sprite>();
            spritesToBeRemoved = new ArrayList<Sprite>();
            link= new Link(c);
            sprites.add(link);
        }

        //add a method that loads map from a file
        public void loadmap()
        {
            System.out.println("Loading map...");
            Json ob = Json.load(c, "map.json");
            Json ob2 = ob.get("sprites");
            sprites.add(link);
            for(int i = 0; i < ob2.size(); i++)
            {
                if (ob2.get(i).getString("type").equals("tile"))
                {
                    Sprite t = new Tile(c, ob2.get(i));
                    sprites.add(t);
                }
                else if (ob2.get(i).getString("type").equals("pot"))
                {
                    Sprite t = new Pot(c, ob2.get(i));
                    sprites.add(t);
                }
            }
        }

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
            Sprite s = new Boomerang(c, link.x + 20, link.y + 20, link.dir);
            sprites.add(s);
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




    static class GameView extends SurfaceView
    {
        SurfaceHolder ourHolder;
        Paint paint;
        Model model;
        Canvas canvas;
        Controller controller;
        int x_pos;
        int y_pos;

        public GameView(Context context, Model m)
        {
            super(context);
            model = m;

            // Initialize ourHolder and paint objects
            ourHolder = getHolder();
            paint = new Paint();

            x_pos = 0;

            y_pos = 0;
        }

        void setController(Controller c)
        {
            controller = c;
        }

        public void paintComponent(Canvas g)
        {

            Iterator <Sprite> it = model.sprites.iterator();
            try{
                while(it.hasNext())
                {
                    Sprite s = it.next();
                    s.paintComponent(g, x_pos, y_pos, paint);
                }
            }catch(Exception e){
                System.out.println("Error: " + e);

            }

            //model.link.paintComponent(g, x_pos, y_pos);
        }
        public void update() {
            if (!ourHolder.getSurface().isValid())
                return;
            canvas = ourHolder.lockCanvas();

            // Draw the background color
            canvas.drawColor(Color.argb(255, 0, 0, 0));

            // Draw the sprites
            paintComponent(canvas);

            //Hide rest of map
            paint.setColor(Color.argb(255,  0, 0, 0));
            canvas.drawRect(0, 1000, 1500, 2500, paint);
            canvas.drawRect(1000, 0, 2500, 1500, paint);

            //buttons for movement
            paint.setColor(Color.argb(255,  0, 0, 255));
            canvas.drawRect(100, 1500,200, 1600, paint);
            canvas.drawRect(700, 1500,800, 1600, paint);
            canvas.drawRect(900, 1500,1000, 1600, paint);
            canvas.drawRect(800, 1350,900, 1450, paint);
            canvas.drawRect(800, 1650,900, 1750, paint);

            ourHolder.unlockCanvasAndPost(canvas);
        }

        public void moveup()
        {
            y_pos = 0;
        }

        public void movedown()
        {
            y_pos = 1000;
        }

        public void moveleft()
        {
            x_pos = 0;
        }

        public void moveright()
        {
            x_pos = 1000;
        }

        // The SurfaceView class (which GameView extends) already
        // implements onTouchListener, so we override this method
        // and pass the event to the controller.
        @Override
        public boolean onTouchEvent(MotionEvent motionEvent)
        {
            controller.onTouchEvent(motionEvent, motionEvent.getX(), motionEvent.getY());
            return true;
        }

        //method that allows classes to load their own images
    }

    static class Controller implements Runnable
    {
        volatile boolean playing;
        Thread gameThread = null;
        Model model;
        GameView view;
        Boolean up, down, left, right;

        Controller(Model m, GameView v)
        {
            up = false;
            down = false;
            left = false;
            right = false;
            model = m;
            view = v;
            view.setController(this);
            playing = true;
        }

        void update()
        {
            model.link.updatePrev();
            if (up)
            {
                model.link.moveUp();
            }
            if (down)
            {
                model.link.moveDown();
            }
            if (left)
            {
                model.link.moveLeft();
            }
            if (right)
            {
                model.link.moveRight();
            }
            if (model.link.x > 1000)
            {
                view.moveright();
            }
            if (model.link.x < 1000)
            {
                view.moveleft();
            }
            if (model.link.y > 1000)
            {
                view.movedown();
            }
            if (model.link.y < 1000)
            {
                view.moveup();
            }
            //model.link.update();
            //view.update();
        }

        @Override
        public void run()
        {
            while(playing)
            {
                //long time = System.currentTimeMillis();
                this.update();
                model.update();
                view.update();

                try {
                    Thread.sleep(5);
                } catch(Exception e) {
                    Log.e("Error:", "sleeping");
                    System.exit(1);
                }
            }
        }

        void onTouchEvent(MotionEvent motionEvent, float x, float y)
        {
            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN: // Player touched the screen
                    if (x > 700 && x < 800 && y > 1500 && y < 1600)
                    {
                        leftButtonPressed();
                    }
                    if (x > 900 && x < 1000 && y > 1500 && y < 1600)
                    {
                        rightButtonPressed();
                    }

                    if (x > 800 && x < 900 && y > 1350 && y < 1450)
                    {
                        upButtonPressed();
                    }
                    if (x > 800 && x < 900 && y > 1650 && y < 1750)
                    {
                        downButtonPressed();
                    }
                    break;

                case MotionEvent.ACTION_UP: // Player withdrew finger
                    leftButtonReleased();
                    rightButtonReleased();
                    upButtonReleased();
                    downButtonReleased();
                    if (x > 100 && x < 200 && y > 1500 && y < 1600)
                    {
                        model.shoot();
                    }
                    break;
            }
        }

        void leftButtonPressed()
        {
            left = true;
        }

        void rightButtonPressed()
        {
            right = true;
        }

        void upButtonPressed()
        {
            up = true;
        }

        void downButtonPressed()
        {
            down = true;
        }

        void downButtonReleased()
        {
            down = false;
        }

        void upButtonReleased()
        {
            up = false;
        }

        void rightButtonReleased()
        {
            right = false;
        }

        void leftButtonReleased()
        {
            left = false;
        }

        // Shut down the game thread.
        public void pause() {
            playing = false;
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Log.e("Error:", "joining thread");
                System.exit(1);
            }

        }

        // Restart the game thread.
        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }
    }
}

