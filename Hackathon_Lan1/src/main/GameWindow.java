package main;

import fish.FishEnemy;
import singleton.FishEnemyManager;
import singleton.PlayerManager;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
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
    //Vector<FishEnemySmall> fishEnemySmallVector;


    public GameWindow() {
        vectorFishEnemy =  FishEnemyManager.getInstance().getVectorFishEnemy();
        this.setTitle("FEEDING FRENZY");
        this.setSize(800, 600);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocation(250,80);
        music();
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
        vectorFishEnemy.add(new FishEnemy(50,300,2));
        vectorFishEnemy.add(new FishEnemy(20,200,2));
        vectorFishEnemy.add(new FishEnemy(150,100,2));
        vectorFishEnemy.add(new FishEnemy(350,400,2));
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
        PlayerManager.getInstance().getPlayer().draw(g);
    }
    public void music ()
    {
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD ;
        ContinuousAudioDataStream loop = null;
        try {
            BGM = new AudioStream (new FileInputStream("sound.wav"));
            MD = BGM.getData();
            loop = new ContinuousAudioDataStream(MD);
            AudioPlayer.player.start(BGM);
        } catch (IOException error) {
            MGP.start(loop);
            System.out.println("Error");
        }

    }
    @Override
    public void run() {

        while(true) {
            PlayerManager.getInstance().getPlayer().update();
            for(FishEnemy fishEnemy : vectorFishEnemy){
                fishEnemy.update();
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
