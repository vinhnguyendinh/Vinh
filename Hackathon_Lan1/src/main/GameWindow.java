package main;

import Scene.Coral;
import fish.FishCaDoc;
import fish.FishEnemy;
import fish.FishEnemySmall;
import fish.JellyFish;
import singleton.FishEnemyManager;
import singleton.PlayerManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by VinhNguyenDinh on 03/13/2016.
 */
public class GameWindow extends Frame implements Runnable {
    Graphics seconds;
    Image image;
    BufferedImage background;

    Vector<FishEnemy> vectorFishEnemy;
    Vector<FishEnemySmall> vectorFishEnemySmall;
    Vector<FishCaDoc> vectorFishCaDoc;
    Vector<JellyFish> vectorJellyFish;
    Vector<Coral> vectorCoral;

    public GameWindow() {
        vectorFishEnemy =  FishEnemyManager.getInstance().getVectorFishEnemy();
        vectorFishEnemySmall = FishEnemyManager.getInstance().getVectorFishEnemySmall();
        vectorFishCaDoc = FishEnemyManager.getInstance().getVectorFishCaDoc();
        vectorJellyFish = FishEnemyManager.getInstance().getVectorJellyFish();
        vectorCoral = FishEnemyManager.getInstance().getVectorCoral();

        this.setTitle("FEEDING FRENZY");
        this.setSize(800,600);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocation(250,80);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
        try {
            background = ImageIO.read(new File("Resources/image 672.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        initFish();
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                PlayerManager.getInstance().getPlayer().move(e.getX(),e.getY());
                // Hàm ẩn chuột
                BufferedImage cursorImg = new BufferedImage(PlayerManager.getInstance().getPlayer().getPositionX(), PlayerManager.getInstance().getPlayer().getPositionY(), BufferedImage.TYPE_INT_ARGB);
                Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
                setCursor(blankCursor);
            }
        });
    }
    private void initFish() {

        vectorFishEnemy.add(new FishEnemy(200,300,2));
        vectorFishEnemy.add(new FishEnemy(400,200,2));
        vectorFishEnemy.add(new FishEnemy(550,100,2));
        vectorFishEnemy.add(new FishEnemy(150,400,2));

        vectorFishEnemySmall.add(new FishEnemySmall(100,200,4));
        vectorFishEnemySmall.add(new FishEnemySmall(150,50,2));

        vectorFishCaDoc.add(new FishCaDoc(50,70,5));
        vectorFishCaDoc.add(new FishCaDoc(50,150,3));

        vectorJellyFish.add(new JellyFish(300,550,3));
        vectorJellyFish.add(new JellyFish(50,600,2));

        vectorCoral.add(new Coral(330,400,2));
        vectorCoral.add(new Coral(200,100,1));
    }

    @Override
    public void update(Graphics g){
        if(image == null){
            image = createImage(this.getWidth(), this.getHeight());
            seconds= image.getGraphics();
        }
        seconds.setColor(getBackground());
        seconds.fillRect(0,0,getWidth(),getHeight());
        seconds.setColor(getForeground());
        paint(seconds);
        g.drawImage(image,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background,0,0,null);

        for(FishEnemy fishEnemy : vectorFishEnemy){
            fishEnemy.draw(g);
        }

        for(FishEnemySmall fishEnemySmall : vectorFishEnemySmall){
            fishEnemySmall.draw(g);
        }

        for(FishCaDoc fishCaDoc : vectorFishCaDoc){
            fishCaDoc.draw(g);
        }

        for(JellyFish jellyFish : vectorJellyFish){
            jellyFish.draw(g);
        }

        for(Coral coral : vectorCoral){
            coral.draw(g);
        }

        PlayerManager.getInstance().getPlayer().draw(g);
    }

    @Override
    public void run() {
        while(true) {
            PlayerManager.getInstance().getPlayer().update();
            for(FishEnemy fishEnemy : vectorFishEnemy){
                fishEnemy.update();
            }

            for(FishEnemySmall fishEnemySmall : vectorFishEnemySmall){
                fishEnemySmall.update();
            }

            for(FishCaDoc fishCaDoc : vectorFishCaDoc){
                fishCaDoc.update();
            }

            for(JellyFish jellyFish : vectorJellyFish){
                jellyFish.update();
            }

            for(Coral coral : vectorCoral){
                coral.update();
            }

            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
