package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{

    public static final int WIDTH = 600, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    Image image = new ImageIcon("D:\\Java\\ShittyGame\\src\\Game_Over_Title_Card.png").getImage();
    private Handler handler;
    private Random r;
    private HUD hud;

    public Game() {

        handler = new Handler();

        new Window(WIDTH, HEIGHT, "Fishy fishy!", this);

        hud = new HUD();

        r = new Random();

        handler.addObject(new Shark(r.nextInt(500), r.nextInt(400), ID.Shark, handler));
        handler.addObject(new Fish(r.nextInt(500), r.nextInt(400), ID.Fish, handler));

    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                move();
                delta --;
            }
            if (running) {
                render();
            }
            frames ++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void move() {
        handler.move();
        hud.move();
        handler.clearSharks();
        handler.addObject(new Plankton(r.nextInt(500), r.nextInt(400), ID.Plankton, handler));
        if (HUD.HEALTH == 0) {
            handler.addObject(new Fish(r.nextInt(500), r.nextInt(400), ID.Fish, handler));
            HUD.HEALTH = 100;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(image, 300, 200, null);
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.blue);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max) {
        if (var >= max) {
            return var = max;
        }
        else if(var <= min){
            return var = min;
        }
        return var;
    }

    public static void main(String[] args) {
        new Game();
    }


}
