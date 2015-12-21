package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by horbachevsky on 12/18/2015.
 */
public class Shark extends Creature {

    Random r = new Random();
    Image karas = new ImageIcon("D:\\Java\\ShittyGame\\src\\shark.jpg").getImage();
    private Handler handler;
    private Creature fish;

    public Shark(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;


    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 43, 16);
    }

    public void move() {

        for (int i = 0; i < handler.creatures.size(); i ++) {
            if (handler.creatures.get(i).getId() == ID.Fish) {
                fish = handler.creatures.get(i);
            }
        }

        x += velX;
        y += velY;

        if (x != fish.getX()) {
            if (x < fish.getX()) {
                if (x + 2 <= fish.getX()) {
                    velX = 2;
                }
            } else if (x > fish.getX()) {
                if (x - 2 >= fish.getX()) {
                    velX = -2;
                }
            }
            if (y != fish.getY()) {
                if (y < fish.getY()) {
                    if (y + 2 <= fish.getY()) {
                        velY = 2;
                    }
                } else if (y > fish.getY()) {
                    if (y - 2 >= fish.getY()) {
                        velY = -2;
                    }
                }
            }
        }


        if (y <= 0 || y >= Game.HEIGHT - 60) {
            velY *= -1;
        }

        if (x <= 0 | x >= Game.WIDTH - 55) {
            velX *= -1;
        }

        intersection();
    }


    public void intersection() {
        for (int i = 0; i < handler.creatures.size(); i ++) {
            Creature tempObject = handler.creatures.get(i);

            if (tempObject.getId() == ID.Fish) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH = 0;
                    handler.removeObject(tempObject);
                }
            }
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBounds());
        g.drawImage(karas, x, y, null);
    }

}
