package fish;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by VinhNguyenDinh on 03/13/2016.
 */
public class Player extends FishObject {
    private int level;
    private int direction; // 1.Left - 2.Right
    public Player(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
        this.direction = 1;
        if(direction == 1) {
            try {
                this.sprite = ImageIO.read(new File("Resources/image 911.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(direction == 2) {
            try {
                this.sprite = ImageIO.read(new File("Resources/image 919.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void draw(Graphics g) {
        super.draw(g);
    }

    public void move(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void update() {
        super.update();
        this.move(this.positionX, this.positionY);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
