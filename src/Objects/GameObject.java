package Objects;

import java.awt.Rectangle;

public abstract class GameObject {
    protected int posX,posY;
    protected int height,width;
    private String type;
    protected  Rectangle hitbox; //pour les collisions

    public GameObject(int posX,int posY,int width,int height,String type){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.type = type;
    }
    public GameObject(int posX,int posY){
        this.posX = posX;
        this.posY = posY;
    }

    public GameObject(int posX,int posY,String type){
        this.posX = posX;
        this.posY = posY;
        this.type = type;
    }

    public int getPosX(){return this.posX;}
    public int getPosY(){return this.posY;}
    public int getWidth(){return this.width;}
    public int getHeight(){return this.height;}
    public String getType(){return this.type;}
    public Rectangle getHitbox(){return this.hitbox;}
}
