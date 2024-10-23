package Objects;

import java.awt.Rectangle;


public class Platform extends GameObject{
    
    //jsp si on garde
    public Platform(int posX,int posY,int width,int height){
        super(posX,posY,width,height,"Platform");
        hitbox = new Rectangle(posX,posY,width,height);
    }
}
