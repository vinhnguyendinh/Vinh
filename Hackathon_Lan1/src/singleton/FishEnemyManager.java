package singleton;

import Scene.Coral;
import fish.FishCaDoc;
import fish.FishEnemy;
import fish.FishEnemySmall;
import fish.JellyFish;

import java.util.Vector;

/**
 * Created by Anh on 3/14/2016.
 */
public class FishEnemyManager {
    private Vector<FishEnemy> vectorFishEnemy;
    private Vector<FishEnemySmall> vectorFishEnemySmall;
    private Vector<FishCaDoc> vectorFishCaDoc;
    private  Vector<JellyFish> vectorJellyFish;
    private  Vector<Coral> vectorCoral;

    private static FishEnemyManager ourInstance = new FishEnemyManager();


    public static FishEnemyManager getInstance(){
        return  ourInstance;
    }

    private FishEnemyManager(){
        vectorFishEnemy = new Vector<FishEnemy>();
        vectorFishEnemySmall = new Vector<FishEnemySmall>();
        vectorFishCaDoc = new Vector<FishCaDoc>();
        vectorJellyFish = new Vector<JellyFish>();
        vectorCoral = new Vector<Coral>();
    }

    public Vector<FishEnemy> getVectorFishEnemy(){
        return vectorFishEnemy;
    }

    public Vector<FishEnemySmall> getVectorFishEnemySmall(){
        return  vectorFishEnemySmall;
    }

    public Vector<FishCaDoc> getVectorFishCaDoc(){
        return  vectorFishCaDoc;
    }

    public Vector<JellyFish> getVectorJellyFish(){
        return  vectorJellyFish;
    }

    public  Vector<Coral> getVectorCoral(){
        return vectorCoral;
    }
}
