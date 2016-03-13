package graphics;

import java.awt.*;
import java.util.Vector;
import main.GameObject;

/**
 * Created by VinhNguyenDinh on 03/13/2016.
 */
public class Animation extends GameObject {
    Vector<Image> imageVector;
    private int delta;
    private int countTime;
    private int index;

    public Animation(int begin, int end, int delta) {
        imageVector = new Vector<>();
        this.delta = delta;
        this.countTime = 0;
        this.index = 0;
        for(int i = begin; i <= end; i++) {
            //imageVector.add()
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {

    }

    public void draw(Graphics g, int x, int y) {
        g.drawImage(imageVector.get(index),x,y,null);
        countTime += 17;
        if(countTime >= delta) {
            countTime = 0;
            index++;
            if (index >= imageVector.size()) {
                index = 0;
            }
        }
    }
}
