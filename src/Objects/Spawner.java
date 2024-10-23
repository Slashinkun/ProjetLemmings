package Objects;
import Entities.BasicChick;
import Game.GameObservable;
import java.awt.Rectangle;

public class Spawner extends GameObject{
    protected GameObservable obs;
    protected int limitSpawn = 20; //nombre poussin qui doit sortir
    
    
    

    //constructeur
    public Spawner(int posX,int posY,GameObservable obs){
        super(posX, posY,"Spawner");
        this.obs = obs;
        hitbox = new Rectangle(posX,posY,100,50);
    }


    //pour faire apparaitre les poussins
    public void spawnChicks(){
        if(obs.getChicks().size() !=limitSpawn){
            obs.getChicks().add(new BasicChick(posX, posY+10));
            obs.setChickSpawn(obs.getChickSpawn()+1);
        }
        
    }
}
