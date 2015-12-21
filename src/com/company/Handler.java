package com.company;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by horbachevsky on 12/18/2015.
 */
public class Handler {

    LinkedList<Creature> creatures = new LinkedList<Creature>();


    public void move() {
        for (int i = 0; i < creatures.size(); i ++) {
            Creature tempObject = creatures.get(i);
            tempObject.move();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < creatures.size(); i ++) {
            Creature tempObject = creatures.get(i);
            tempObject.render(g);
        }
    }

    public void clearSharks() {
        for (int i = 0; i < creatures.size(); i ++) {
            Creature tempObject = creatures.get(i);
            if (tempObject.getId() == ID.Fish);

        }
    }

    public void addObject(Creature object) {
        this.creatures.add(object);
    }

    public void removeObject(Creature object) {
        this.creatures.remove(object);
    }

}
