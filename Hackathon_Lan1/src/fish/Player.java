package fish;

import graphics.Animation;
import singleton.FishEnemyManager;
import singleton.GameManager;
import java.awt.*;
/**
 * Created by VinhNguyenDinh on 03/13/2016.
 */
public class Player extends FishObject {
    private int level;
    private int direction; // 1.Left - 2.Right
    private int start;
    private int end;
    private Animation anim;
    private boolean check = true;

    private void initAnimation() {
        if(level == 1) {
            start = 180; end = 206;
        }
        else if(level == 2) {
            start = 257; end = 285;
        }
        else if(level == 3) {
            start = 557; end = 583;
        }
        anim  = new Animation(start,end,50);
    }

    public Player(int positionX, int positionY, int speed) {
        super(positionX, positionY, speed);
        this.direction = 1;
        this.level = 1;
        initAnimation();
    }

    public void draw(Graphics g) {
        anim.draw(g, positionX + GameManager.getInstance().getLocationX()
                , positionY + GameManager.getInstance().getLocationY());
    }

    public void move(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public void update() {
        super.update();
        this.move(this.positionX, this.positionY);
        if(checkCollisionEnemy()) {
            // Vẽ hình cá đớp mồi
           // anim.setEnd(226);
        }

        //else end = 206;
    }

    public boolean checkCollisionEnemy() {
        Rectangle rectPlayer = new Rectangle(positionX, positionY, anim.getWidth(), anim.getHeight());
        for (FishEnemy fishEnemy : FishEnemyManager.getInstance().getVectorFishEnemy()) {
            Rectangle rectFishEnemy = new Rectangle(fishEnemy.getPositionX(), fishEnemy.getPositionY(), fishEnemy.getWidth(), fishEnemy.getHeight());
            if (rectPlayer.intersects(rectFishEnemy)) {
                FishEnemyManager.getInstance().getVectorFishEnemy().remove(FishEnemyManager.getInstance().getVectorFishEnemy().indexOf(fishEnemy));
                return true;
            }
        }
        return false;
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
