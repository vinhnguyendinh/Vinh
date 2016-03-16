package singleton;

import fish.FishEnemy;
import fish.FishEnemy1;

import java.util.Vector;

/**
 * Created by Anh on 3/14/2016.
 */
public class FishEnemyManager {
    private Vector<FishEnemy> vectorFishEnemy;
    private Vector<FishEnemy1> vectorFishEnemy1;
    private static FishEnemyManager ourInstance = new FishEnemyManager();

    public static FishEnemyManager getInstance(){
        return  ourInstance;
    }

    private FishEnemyManager(){
        vectorFishEnemy = new Vector<FishEnemy>();
        vectorFishEnemy1 = new Vector<FishEnemy1>();
    }

    public Vector<FishEnemy> getVectorFishEnemy(){
        return vectorFishEnemy;
    }
    public Vector<FishEnemy1> getVectorFishEnemy1(){return  vectorFishEnemy1;}
}
