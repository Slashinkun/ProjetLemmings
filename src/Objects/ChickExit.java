package Objects;

import Entities.BasicChick;
import Game.GameObservable;
import java.awt.Rectangle;

public class ChickExit extends GameObject {
    
    protected  GameObservable obs;
    protected  int exited = 0;


    //constructeur
    public ChickExit(int posX,int posY,GameObservable obs){
        super(posX, posY, "ChickExit");
        this.obs = obs;
        hitbox = new Rectangle(posX,posY,100,50);
    }

    public int nbChickExited(){
        return exited;
    }


    //pour voir si les poussins touchent la sortie
    public void chickExited(){
        if(!obs.getChicks().isEmpty()){
            for (BasicChick ck : obs.getChicks()) {
                if(ck.getHitbox().intersects(hitbox)){
                    obs.getChicks().remove(ck);
                    exited++;
                }
            }
        }
        
    }
}
