package com.company;

import java.awt.*;

/**
 * Created by horbachevsky on 12/19/2015.
 */
public class HUD {

    public static int HEALTH = 200;

    public void move() {
        HEALTH --;
        HEALTH = Game.clamp(HEALTH, 0, 200);
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 400, 32);
        g.setColor(Color.green);
        g.fillRect(15, 15, (int)HEALTH * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 400, 32);
    }
}
