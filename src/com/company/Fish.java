package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by horbachevsky on 12/19/2015.
 */
public class Fish extends Creature {

    Image shark = new ImageIcon("D:\\Java\\ShittyGame\\src\\karas.png").getImage();
    private Handler handler;
    private Creature fish;

    public Fish(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 3;
        velY = 3;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 38, 21);
    }

    public void move() {
        x += velX;
        y += velY;

        for (int i = 0; i < handler.creatures.size(); i ++) {
            if (handler.creatures.get(i).getId() == ID.Shark) {
                fish = handler.creatures.get(i);
            }
        }

//        if (x != fish.getX()) {
//            if (x < fish.getX()) {
//                if (x + 2 <= fish.getX()) {
//                    velX = -2;
//                }
//            } else if (x > fish.getX()) {
//                if (x - 2 >= fish.getX()) {
//                    velX = 2;
//                }
//            }
//            if (y != fish.getY()) {
//                if (y < fish.getY()) {
//                    if (y + 2 <= fish.getY()) {
//                        velY = -2;
//                    }
//                } else if (y > fish.getY()) {
//                    if (y - 2 >= fish.getY()) {
//                        velY = 2;
//                    }
//                }
//            }
//        }



        if (y <= 0 || y >= Game.HEIGHT - 55) {
            velY *= -1;
        }

        if (x <= 0 | x >= Game.WIDTH - 50) {
            velX *= -1;
        }
        intersection();
    }

    public void intersection() {
        for (int i = 0; i < handler.creatures.size(); i++) {
            Creature tempCreature = handler.creatures.get(i);

            if (tempCreature.getId() == ID.Plankton) {
                if (getBounds().intersects(tempCreature.getBounds())) {
                    HUD.HEALTH += 100;
                    handler.removeObject(tempCreature);
                }
            }
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g.drawImage(shark, x, y, null);
        g2d.draw(getBounds());
    }

}
