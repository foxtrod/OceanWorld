package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * Created by horbachevsky on 12/21/2015.
 */
public class Plankton extends Creature {

    private Handler handler;

    public Plankton(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        velX = 3;
        velY = 3;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y);
    }

    public void move() {
    }

    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, 10, 10);
    }

}
