package Game;

import Entities.BasicChick;
import Objects.ChickExit;
import Objects.GameObject;
import Objects.Spawner;
import java.util.ArrayList;
import java.util.List;


public class GameObservable {
    private int[][] world;  //c'est pour representer le jeu
    private List<CObserver> observers;
    private List<GameObject> objects;
    private Spawner spw;  //je savais pas comment mettre un spawner dans le jeu
    private List<BasicChick> chicks;  //ça devrait etre une liste d'Entity
    

    private int chickSpawn = 0;
    private int minChickExited = 10;
   

    private ChickExit ext;
    

    //pour mettre a jour le jeu
   public void updateGame(){
    spawn();
        
    updateChicks(); // physique des poussins
    checkCollision(); //collision
    checkChickExited(); 
   }

    public GameObservable(){
        world = new int[1280][720];
        observers = new ArrayList<>();
        objects = new ArrayList<>();
        chicks = new ArrayList<>();
        spw = new Spawner(10, 10, this);
        objects.add(spw);
        ext = new ChickExit(1000, 720-200, this);
        objects.add(ext);
    }

    public GameObservable(Spawner sp,ChickExit ext){
        world = new int[1280][720];
        observers = new ArrayList<>();
        objects = new ArrayList<>();
        chicks = new ArrayList<>();
        this.spw = sp;
        this.ext = ext;
    }


    public void updateChicks(){
        if(!chicks.isEmpty())
        for (BasicChick entity : chicks) {
            entity.moveChick();
        }
    }

    public boolean checkWin(){
        if(ext.nbChickExited() == minChickExited){
            return true;
        }
        return false;
    }

    public void checkCollision(){
        if(!chicks.isEmpty()){
        for (BasicChick basicChick : chicks) {
            
            for (GameObject obj : objects) {

                //si les deux carres se touchent, on dit que il est sur un sol
                if(basicChick.getHitbox().intersects(obj.getHitbox()) && obj.getType() != "Spawner"){
                    basicChick.is_on_floor();
                }
            }
        }
        }
    }

    public void spawn(){
       spw.spawnChicks();
       
    }


    public void checkChickExited(){
        ext.chickExited();
    }

    public int getChickSpawn(){
        return this.chickSpawn;
    }
    public void setChickSpawn(int value){
        this.chickSpawn = value;
    }

   

    public int[][] getWorld() {
        return world;
    }

    public List<GameObject> getObjects(){
        return this.objects;
    }

    public List<BasicChick> getChicks(){
        return this.chicks;
    }

    public void addGameObjects(GameObject ob){
        objects.add(ob);
    }

    public void addChicks(BasicChick ent){
        chicks.add(ent);
    }

    
    
    
    public void registerObserver(CObserver o) {
        observers.add(o); 
    }

    public void unregisterObserver(CObserver o) {
        observers.remove(o); 
    }

    private void notifyObservers() {
        for (CObserver obs : observers) {
            obs.update(); 
        }
    }
}
