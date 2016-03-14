package singleton;

import fish.FishEnemy;

import java.util.Vector;

/**
 * Created by Anh on 3/14/2016.
 */
public class FishEnemyManager {
    private Vector<FishEnemy> vectorFishEnemy;
    private static FishEnemyManager ourInstance = new FishEnemyManager();


    public static FishEnemyManager getInstance(){
        return  ourInstance;
    }

    private FishEnemyManager(){
        vectorFishEnemy = new Vector<FishEnemy>();

    }

    public Vector<FishEnemy> getVectorFishEnemy(){
        return vectorFishEnemy;
    }
}
