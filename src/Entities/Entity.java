package Entities;

import Game.CObserver;
import java.awt.Rectangle;
import javax.swing.JComponent;


//c'est la classe pour tout les poussins
public abstract class Entity extends JComponent implements CObserver{
    protected int posX,posY;
    protected String type;
    protected int direction = -1; //par defaut -1 pour la droite
    protected boolean is_on_floor = false; //c'est dire si il touche un sol
    protected final int gravity = 2;  
    protected Rectangle hitbox;  //c'est le carre pour voir si il y a une collision
    
    
    
    //constructeur
    
    public Entity(int posX,int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public Entity(int posX,int posY,String type){
        this.posX = posX;
        this.posY = posY;
        this.type = type;
    

    }


    //getters et setters
    public int getPosX(){return this.posX;}
    public int getPosY(){return this.posY;}
    public int getDirection(){return this.direction;}
    public String getType(){return this.type;}
    public Rectangle getHitbox(){return this.hitbox;}

    
    
    

}
